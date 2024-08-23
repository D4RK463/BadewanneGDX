package org.wanne.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
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
import org.wanne.screens.AbstractWalkableScreen

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
    private val pills = Pills(am = game.am)
    private val bed = Bed(am = game.am)
    private val roomWindow = Window(am = game.am)
    private val drawer = Drawer(am = game.am)
    private val door = Door(am = game.am)
    private val pa2Poster = PA2Poster(am = game.am)
    private val brucePoster = BrucePoster(am = game.am)
    private val deanPoster = DeanPoster(am = game.am)
    private val rug = Rug(am = game.am)
    private val stickers = Stickers(am = game.am)
    private val straw = Straw(am = game.am)
    private val box = Box(am = game.am)
    private val safe = Safe(am = game.am)
    private val drBear = DrBear(am = game.am, gameObjectToAppear = pills, game = game)
    private val milkSucker = MilkSucker(am = game.am)
    private val stethoscope = Stethoscope(am = game.am, gameObjectToAppear = milkSucker)
    private val scalpel = Scalpel(am = game.am, gameObjectToAppear = pills)
    private val note = Note(am = game.am)
    private val mario =
        Mario(
            am = game.am,
            gameObjectToManipulate = rug,
            gameObjectToAppear = note,
            fireAnimation = fireAnimation,
            powerUpAnimation = powerUpAnimation,
            game = game,
        )
    private val bell = Cowbell(am = game.am)
    private val teddy = Teddy(am = game.am, gameObjectToCheck = mario, game = game)
    private val flower = FireFlower(am = game.am, gameObjectToManipulate = mario, game = game)
    private val telephone = Telephone(am = game.am, winningRequiredGameObjectList = listOf(milkSucker, pills, bell), game = game)
    private val exit = Exit(am = game.am, game = game)

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
//        exit.isVisible = false
        exit.isVisible = true
        door.isVisible = false

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
