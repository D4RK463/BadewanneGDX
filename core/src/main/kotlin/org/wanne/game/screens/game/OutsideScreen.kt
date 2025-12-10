package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Cursor
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

class OutsideScreen(
    game: WanneGame
) : AbstractWalkableScreen(game) {

    // Background
    private val outsideBackgroundSingle: Texture = game.am["$TEXTURES/OutsideSingle.png"]
    private val outsideBackgroundMulti: Texture = game.am["$TEXTURES/Outside.png"]
    private val outsideBackgroundSingleWide: Texture = game.am["$TEXTURES/Outside169.png"]

    // Ambience Musik
    private val musicBackground: Music = game.am["$MUSIC/background.mp3"]
    private val radioBackground: Music = game.am["$MUSIC/funky_groove.mp3"]

    override fun show() {
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())
        createPlayableCharacters()

        val limits = createRoomLimits()
        if (!game.timer.isRunning) {
            game.timer.start()
        }

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, listOf())
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            if (game.classicMode()) {
                stage.addActor(Image(outsideBackgroundSingle))
            } else {
                stage.addActor(Image(outsideBackgroundSingleWide))
            }
        } else {
            stage.addActor(Image(outsideBackgroundMulti))
        }

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        radioBackground.volume = game.config.musicVolume
        radioBackground.isLooping = true
        radioBackground.play()

        // Klick Steuerung der Charaktere
        val listener = PointAndClickListener(dialogBoard, limits, game.network.takeUnless { game.isSingleplayer })
        stage.addListener(listener)
        game.currentListener = listener

        // Stage Config
        inventory.rearrangeObjects(!game.classicMode())

        // Reihenfolge ist wichtig
        val roomItemList: List<GameObject> = listOf(
            game.items.iceMenuLeft, game.items.iceMenuRight, game.items.honkSign, game.items.street,
            game.items.graffiti, game.items.iceman, game.items.goldBag, game.items.ice
        )
        addObjectsToStage(roomItemList)
        configureGameObjects()
        createGameUI(poolAttendant, duck)

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)
        game.currentDialogBoard = dialogBoard

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)

        game.currentStage = stage
        game.arrivedOutside = true
    }

    private fun createRoomLimits(): IntArray {
        // Raum Lauf-Limits → links, unten, rechts, oben
        return if (game.classicMode()) {
            intArrayOf(70, 129, 934, 235)
        } else {
            intArrayOf(340, 10, 1188, 235)
        }
    }

    private fun createPlayableCharacters() {
        poolAttendant = PoolAttendant(
            game.choose(Point(86F, 220F), Point(340F, 170F)),
            Player.Companion.Looking.RIGHT,
            am = game.am
        )
        duck = Duck(
            game.choose(Point(100F, 200F), Point(354F, 150F)),
            Player.Companion.Looking.RIGHT,
            am = game.am
        )
    }

    private fun configureGameObjects() {
        if (!inventory.isObjectInInventory(game.items.ice)) {
            game.items.ice.isVisible = false
        }
    }

    override fun resetPlayerAndSound() {
        stage.firstPlayerState.needToMove = false
        stage.firstPlayerState.player.stopHammerTime()
        game.soundManager.stopSound()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
        super.hide()
        musicBackground.stop()
        radioBackground.stop()

        game.items.iceMenuLeft.remove()
        game.items.iceMenuRight.remove()
        game.items.honkSign.remove()
        game.items.street.remove()
        game.items.graffiti.remove()
        game.items.iceman.remove()
        game.items.goldBag.remove()
        game.items.ice.remove()

        dialogBoard.remove()
    }

    override fun dispose() {
        stage.dispose()
    }
}
