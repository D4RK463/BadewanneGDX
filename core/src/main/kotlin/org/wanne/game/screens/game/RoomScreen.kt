package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.MUSIC
import org.wanne.game.TEXTURES
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.model.Point
import org.wanne.game.model.objects.GameObject
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.stage.PointAndClickAwareStage

class RoomScreen(
    game: WanneGame
) : AbstractWalkableScreen(game) {

    // Background
    private val roomBackgroundSingle: Texture = game.am["$TEXTURES/KinderzimmerSingle.png"]
    private val roomBackgroundMulti: Texture = game.am["$TEXTURES/Kinderzimmer.png"]
    private val roomBackgroundSingleWide: Texture = game.am["$TEXTURES/Kinderzimmer169.png"]

    // Ambience Musik
    private val musicBackground: Music = game.am["$MUSIC/background.mp3"]

    override fun show() {
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())
        createPlayableCharacters()

        val limits = createRoomLimits()
        game.gameEnded = false
        game.timer.start()

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, listOf())
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            if (game.classicMode()) {
                stage.addActor(Image(roomBackgroundSingle))
            } else {
                stage.addActor(Image(roomBackgroundSingleWide))
            }
        } else {
            // ToDo: Multiplayer Hintergrund im Widescreen Format
            stage.addActor(Image(roomBackgroundMulti))
        }

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        // Klick Steuerung der Charaktere
        stage.addListener(PointAndClickListener(dialogBoard, limits))

        // Stage Config
        inventory.rearrangeObjects(!game.classicMode())

        // Reihenfolge ist wichtig
        val roomItemList: List<GameObject> = listOf(
            game.items.bed, game.items.drBear, game.items.stethoscope, game.items.scalpel,
            game.items.bell, game.items.roomWindow, game.items.drawer,
            game.items.stickers, game.items.exit, game.items.straw, game.items.door,
            game.items.pa2Poster, game.items.brucePoster, game.items.deanPoster,
            game.items.safe, game.items.rug, game.items.mario, game.items.flower, game.items.box,
            game.items.telephone, game.items.teddy, game.items.milkSucker,
            game.items.pills, game.items.note
        )
        addObjectsToStage(roomItemList)
        configureGameObjects()
        createGameUI(poolAttendant, duck)

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)

        game.startedGame = true
    }

    private fun createRoomLimits(): IntArray {
        // Raum Lauf-Limits → links, unten, rechts, oben
        return if (game.classicMode()) {
            intArrayOf(255, 129, 934, 312)
        } else {
            intArrayOf(509, 10, 1188, 262)
        }
    }

    private fun createPlayableCharacters() {
        poolAttendant = PoolAttendant(
            game.choose(Point(400F, 200F), Point(600F, 200F)),
            Player.Companion.Looking.RIGHT,
            am = game.am
        )
        duck = Duck(
            game.choose(Point(600F, 200F), Point(800F, 200F)),
            Player.Companion.Looking.LEFT,
            am = game.am
        )
    }

    private fun configureGameObjects() {
        // Der Ausgang darf nur am Ende auf sein :)
        game.items.exit.isVisible = false
//        game.items.exit.isVisible = true
//        game.items.door.isVisible = false

        if (!inventory.isObjectInInventory(game.items.note)) {
            game.items.note.isVisible = false
        }
        if (!inventory.isObjectInInventory(game.items.milkSucker)) {
            game.items.milkSucker.isVisible = false
        }
        if (!inventory.isObjectInInventory(game.items.pills)) {
            game.items.pills.isVisible = false
        }

        if (game.puzzleSolved) {
            game.items.flower.solved = true
        }

        if (game.cowIsBusy) {
            game.items.door.isVisible = false
            game.items.exit.isVisible = true
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
        super.hide()
        musicBackground.stop()

        game.items.bed.remove()
        game.items.drBear.remove()
        game.items.stethoscope.remove()
        game.items.scalpel.remove()
        game.items.mario.remove()
        game.items.bell.remove()
        game.items.roomWindow.remove()
        game.items.drawer.remove()
        game.items.stickers.remove()
        game.items.exit.remove()
        game.items.straw.remove()
        game.items.door.remove()
        game.items.pa2Poster.remove()
        game.items.brucePoster.remove()
        game.items.deanPoster.remove()
        game.items.safe.remove()
        game.items.rug.remove()
        game.items.flower.remove()
        game.items.telephone.remove()
        game.items.box.remove()
        game.items.teddy.remove()
        game.items.milkSucker.remove()
        game.items.pills.remove()
        game.items.note.remove()

        dialogBoard.remove()
    }

    override fun dispose() {
        stage.dispose()
    }
}
