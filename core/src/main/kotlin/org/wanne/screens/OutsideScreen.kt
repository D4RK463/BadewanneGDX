package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.stage.PointAndClickAwareStage
import org.wanne.model.objects.Graffiti
import org.wanne.model.objects.HonkSign
import org.wanne.model.objects.IceMenuLeft
import org.wanne.model.objects.IceMenuRight
import org.wanne.model.objects.Iceman
import org.wanne.model.objects.Street
import org.wanne.model.player.Duck
import org.wanne.model.player.Player
import org.wanne.model.player.PoolAttendant

class OutsideScreen(
    private var game: WanneGame,
) : AbstractWalkableScreen(game) {
    private val skin: Skin = game.am.get("ui/uiskin.json")

    // Background
    private val outsideBackgroundSingle: Texture = game.am.get("pictures/Backgrounds/OutsideSingle.png")
    private val outsideBackgroundMulti: Texture = game.am.get("pictures/Backgrounds/Outside.png")

    // Raum Lauf-Limits => links,unten,rechts,oben
    private val limits = intArrayOf(70, 129, 934, 235)

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/Kinderzimmer.mp3")

    // Players
    private val poolAttendant = PoolAttendant(86F, 220F, Player.Companion.Looking.RIGHT, am = game.am)
    private val duck = Duck(100F, 200F, Player.Companion.Looking.RIGHT, am = game.am)

    // Objects
    private val iceMenuLeft = IceMenuLeft(am = game.am)
    private val iceMenuRight = IceMenuRight(am = game.am)
    private val honkSign = HonkSign(am = game.am)
    private val street = Street(am = game.am)
    private val graffiti = Graffiti(am = game.am)
    private val iceman = Iceman(am = game.am)

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, emptyList())
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(outsideBackgroundSingle))
        } else {
            stage.addActor(Image(outsideBackgroundMulti))
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
        iceMenuLeft.addToStage(stage, skin = skin)
        iceMenuRight.addToStage(stage, skin = skin)
        honkSign.addToStage(stage, skin = skin)
        street.addToStage(stage, skin = skin)
        graffiti.addToStage(stage, skin = skin)
        iceman.addToStage(stage, skin = skin)
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
