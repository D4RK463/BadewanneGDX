package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.stage.PointAndClickAwareStage
import org.wanne.model.animation.FireAnimation
import org.wanne.model.animation.PowerUpAnimation
import org.wanne.model.objects.Bed
import org.wanne.model.objects.Box
import org.wanne.model.objects.BrucePoster
import org.wanne.model.objects.Cowbell
import org.wanne.model.objects.DeanPoster
import org.wanne.model.objects.Door
import org.wanne.model.objects.DrBear
import org.wanne.model.objects.Drawer
import org.wanne.model.objects.Exit
import org.wanne.model.objects.FireFlower
import org.wanne.model.objects.Mario
import org.wanne.model.objects.MilkSucker
import org.wanne.model.objects.Note
import org.wanne.model.objects.PA2Poster
import org.wanne.model.objects.Pills
import org.wanne.model.objects.Rug
import org.wanne.model.objects.Safe
import org.wanne.model.objects.Scalpel
import org.wanne.model.objects.Stethoscope
import org.wanne.model.objects.Stickers
import org.wanne.model.objects.Straw
import org.wanne.model.objects.Teddy
import org.wanne.model.objects.Telephone
import org.wanne.model.objects.Window
import org.wanne.model.player.Duck
import org.wanne.model.player.Player
import org.wanne.model.player.PoolAttendant

class RoomScreen(
    private var game: WanneGame,
) : AbstractWalkableScreen(game) {
    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Raum Lauf-Limits => links,unten,rechts,oben
    private val limits = intArrayOf(255, 129, 934, 312)

    // Ambience Musik
    private val musicBackground = Gdx.audio.newMusic(Gdx.files.internal("soundsOriginal/Background/Kinderzimmer.mp3"))

    // Animations
    private val fireAnimation = FireAnimation(82F, 198F, false)
    private val powerUpAnimation = PowerUpAnimation(82F, 345F, false)

    // Objects
    private val pills = Pills()
    private val bed = Bed()
    private val roomWindow = Window()
    private val drawer = Drawer()
    private val door = Door()
    private val pa2Poster = PA2Poster()
    private val brucePoster = BrucePoster()
    private val deanPoster = DeanPoster()
    private val rug = Rug()
    private val stickers = Stickers()
    private val straw = Straw()
    private val box = Box()
    private val safe = Safe()
    private val drBear = DrBear(gameObjectToAppear = pills, game = game)
    private val milkSucker = MilkSucker()
    private val stethoscope = Stethoscope(gameObjectToAppear = milkSucker)
    private val scalpel = Scalpel(gameObjectToAppear = pills)
    private val note = Note()
    private val mario =
        Mario(
            gameObjectToManipulate = rug,
            gameObjectToAppear = note,
            fireAnimation = fireAnimation,
            powerUpAnimation = powerUpAnimation,
            game = game,
        )
    private val bell = Cowbell()
    private val teddy = Teddy(gameObjectToCheck = mario, game = game)
    private val flower = FireFlower(gameObjectToManipulate = mario, game = game)
    private val telephone = Telephone(winningRequiredGameObjectList = listOf(milkSucker, pills, bell), game = game)
    private val exit = Exit(game = game)

    // Players
    private val poolAttendant = PoolAttendant(400F, 200F, Player.Companion.Looking.RIGHT)
    private val duck = Duck(600F, 200F, Player.Companion.Looking.LEFT)

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, listOf(fireAnimation, powerUpAnimation))
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(roomBackgroundSingle))
        } else {
            stage.addActor(Image(roomBackgroundMulti))
        }

        musicBackground.volume = 0.2F
        musicBackground.isLooping = true
        musicBackground.play()

        // Klick Steuerung der Charaktere
        stage.addListener(PointAndClickListener(dialogBoard, limits))

        createGameObjects()
        createGameUI(poolAttendant, duck)

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)
    }

    private fun createGameObjects() {
        // Objekte hinzufügen (Reihenfolge ist wichtig)
        bed.addToStage(stage, game.skin)
        drBear.addToStage(stage, game.skin)
        stethoscope.addToStage(stage, game.skin)
        scalpel.addToStage(stage, game.skin)
        mario.addToStage(stage, game.skin)
        bell.addToStage(stage, game.skin)
        roomWindow.addToStage(stage, game.skin)
        drawer.addToStage(stage, game.skin)
        stickers.addToStage(stage, game.skin)
        exit.addToStage(stage, game.skin)
        straw.addToStage(stage, game.skin)
        door.addToStage(stage, game.skin)
        pa2Poster.addToStage(stage, game.skin)
        brucePoster.addToStage(stage, game.skin)
        deanPoster.addToStage(stage, game.skin)
        safe.addToStage(stage, game.skin)
        rug.addToStage(stage, game.skin)
        flower.addToStage(stage, game.skin)
        telephone.addToStage(stage, game.skin)
        box.addToStage(stage, game.skin)
        teddy.addToStage(stage, game.skin)

        // Objekte für später im Spiel
        milkSucker.addToStage(stage, game.skin)
        pills.addToStage(stage, game.skin)
        note.addToStage(stage, game.skin)

        // Der Ausgang darf nur am Ende auf sein :)
        exit.isVisible = false
//        exit.isVisible = true
//        door.isVisible = false

        // Wenn wir aus der Kuh Szene zurückkommen, ist die Notiz im Inventar und muss sicher bleiben.
        if (!stage.currentAction.inventory.isObjectInInventory(note)) {
            note.isVisible = false
        }
        if (!stage.currentAction.inventory.isObjectInInventory(milkSucker)) {
            milkSucker.isVisible = false
        }
        if (!stage.currentAction.inventory.isObjectInInventory(pills)) {
            pills.isVisible = false
        }

        if (game.puzzleSolved) {
            flower.solved = true
        }

        if (game.cowIsBusy) {
            door.isVisible = false
            exit.isVisible = true
        }
    }

    override fun resetPlayerAndSound() {
        stage.needToMove = false
        stage.currentPlayer.stopHammerTime()
        game.soundManager.stopSound()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
        musicBackground.stop()
    }

    override fun dispose() {
        stage.dispose()
    }
}
