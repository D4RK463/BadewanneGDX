package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.model.animation.FireAnimation
import org.wanne.game.model.animation.PowerUpAnimation
import org.wanne.game.model.objects.Bed
import org.wanne.game.model.objects.Box
import org.wanne.game.model.objects.BrucePoster
import org.wanne.game.model.objects.Cowbell
import org.wanne.game.model.objects.DeanPoster
import org.wanne.game.model.objects.Door
import org.wanne.game.model.objects.DrBear
import org.wanne.game.model.objects.Drawer
import org.wanne.game.model.objects.Exit
import org.wanne.game.model.objects.FireFlower
import org.wanne.game.model.objects.Mario
import org.wanne.game.model.objects.MilkSucker
import org.wanne.game.model.objects.Note
import org.wanne.game.model.objects.PA2Poster
import org.wanne.game.model.objects.Pills
import org.wanne.game.model.objects.Rug
import org.wanne.game.model.objects.Safe
import org.wanne.game.model.objects.Scalpel
import org.wanne.game.model.objects.Stethoscope
import org.wanne.game.model.objects.Stickers
import org.wanne.game.model.objects.Straw
import org.wanne.game.model.objects.Teddy
import org.wanne.game.model.objects.Telephone
import org.wanne.game.model.objects.Window
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.screens.AbstractWalkableScreen
import org.wanne.game.stage.PointAndClickAwareStage

class RoomScreen(
    private var game: WanneGame,
) : AbstractWalkableScreen(game) {

    // Background
    private val roomBackgroundSingle: Texture = game.am.get("pictures/Backgrounds/KinderzimmerSingle.png")
    private val roomBackgroundMulti: Texture = game.am.get("pictures/Backgrounds/Kinderzimmer.png")

    // Raum Lauf-Limits => links,unten,rechts,oben
    private val limits = intArrayOf(255, 129, 934, 312)

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/Kinderzimmer.mp3")

    // Animations
    private val fireAnimation = FireAnimation(82F, 198F, false, game.am)
    private val powerUpAnimation = PowerUpAnimation(82F, 345F, false, game.am)

    // Objects
    private val pills = Pills(game = game)
    private val bed = Bed(game = game)
    private val roomWindow = Window(game = game)
    private val drawer = Drawer(game = game)
    private val door = Door(game = game)
    private val pa2Poster = PA2Poster(game = game)
    private val brucePoster = BrucePoster(game = game)
    private val deanPoster = DeanPoster(game = game)
    private val rug = Rug(game = game)
    private val stickers = Stickers(game = game)
    private val straw = Straw(game = game)
    private val box = Box(game = game)
    private val safe = Safe(game = game)
    private val drBear = DrBear(game = game, gameObjectToAppear = pills)
    private val milkSucker = MilkSucker(game = game)
    private val stethoscope = Stethoscope(game = game, gameObjectToAppear = milkSucker)
    private val scalpel = Scalpel(game = game, gameObjectToAppear = pills)
    private val note = Note(game = game)
    private val mario =
        Mario(
            game = game,
            gameObjectToManipulate = rug,
            gameObjectToAppear = note,
            fireAnimation = fireAnimation,
            powerUpAnimation = powerUpAnimation
        )
    private val bell = Cowbell(game = game)
    private val teddy = Teddy(game = game, gameObjectToCheck = mario)
    private val flower = FireFlower(game = game, gameObjectToManipulate = mario)
    private val telephone = Telephone(game = game, winningRequiredGameObjectList = listOf(milkSucker, pills, bell))
    private val exit = Exit(game = game)

    // Players
    private val poolAttendant = PoolAttendant(400F, 200F, Player.Companion.Looking.RIGHT, am = game.am)
    private val duck = Duck(600F, 200F, Player.Companion.Looking.LEFT, am = game.am)

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

        musicBackground.volume = game.config.musicVolume
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
        bed.addToStage(stage, skin)
        drBear.addToStage(stage, skin)
        stethoscope.addToStage(stage, skin)
        scalpel.addToStage(stage, skin)
        mario.addToStage(stage, skin)
        bell.addToStage(stage, skin)
        roomWindow.addToStage(stage, skin)
        drawer.addToStage(stage, skin)
        stickers.addToStage(stage, skin)
        exit.addToStage(stage, skin)
        straw.addToStage(stage, skin)
        door.addToStage(stage, skin)
        pa2Poster.addToStage(stage, skin)
        brucePoster.addToStage(stage, skin)
        deanPoster.addToStage(stage, skin)
        safe.addToStage(stage, skin)
        rug.addToStage(stage, skin)
        flower.addToStage(stage, skin)
        telephone.addToStage(stage, skin)
        box.addToStage(stage, skin)
        teddy.addToStage(stage, skin)

        // Objekte für später im Spiel
        milkSucker.addToStage(stage, skin)
        pills.addToStage(stage, skin)
        note.addToStage(stage, skin)

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
