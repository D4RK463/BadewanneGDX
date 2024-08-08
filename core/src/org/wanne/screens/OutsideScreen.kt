package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.stage.PointAndClickAwareStage
import org.wanne.model.objects.IceMenuLeft
import org.wanne.model.objects.IceMenuRight
import org.wanne.model.player.Duck
import org.wanne.model.player.Player
import org.wanne.model.player.PoolAttendant

class OutsideScreen(
    private var game: WanneGame,
) : AbstractWalkableScreen(game) {
    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/OutsideSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Outside.png"))

    // Raum Lauf-Limits => links,unten,rechts,oben
    private val limits = intArrayOf(70, 129, 934, 235)

    // Ambience Musik
    private val musicBackground = Gdx.audio.newMusic(Gdx.files.internal("soundsOriginal/Background/Kinderzimmer.mp3"))

    // Players
    private val poolAttendant = PoolAttendant(86F, 220F, Player.Companion.Looking.RIGHT)
    private val duck = Duck(100F, 200F, Player.Companion.Looking.RIGHT)

    // Objects
    private val iceMenuLeft = IceMenuLeft()
    private val iceMenuRight = IceMenuRight()

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, null)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(roomBackgroundSingle))
        } else {
            stage.addActor(Image(roomBackgroundMulti))
        }

        musicBackground.volume = 0.4F
        musicBackground.isLooping = true
        musicBackground.play()

        // Klick Steuerung der Charaktere
        stage.addListener(PointAndClickListener(dialogBoard, limits))

        createGameObjects()
        createGameUI(poolAttendant, duck)

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
    }

    private fun createGameObjects() {
        stage.initializeInventoryItems()

        // Objekte hinzufügen (Reihenfolge ist wichtig)
        iceMenuLeft.addToStage(stage, skin = game.skin)
        iceMenuRight.addToStage(stage, skin = game.skin)
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
