package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.stage.PointAndClickAwareStage
import org.wanne.model.ActionType
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Duck
import org.wanne.model.player.Player
import org.wanne.model.player.PoolAttendant
import kotlin.system.exitProcess

abstract class AbstractWalkableScreen(
    private var game: WanneGame,
) : Screen {
    lateinit var stage: PointAndClickAwareStage

    lateinit var viewport: FitViewport

    val dialogBoard = DialogBoard(skin = game.skin)

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

        // Stage zeichnen mit UI, Objekten, Spielern, dem Dialog-Brett und Spieler Bewegung
        stage.act()
        stage.draw()

        game.batch.end()
    }

    fun createGameUI(
        poolAttendant: PoolAttendant,
        duck: Duck,
    ) {
        // Buttons
        val lookCursor = Pixmap(Gdx.files.internal("ui/cursor/Ansehen.png"))
        val lookButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("Ansehen"),
                game.buttonAtlas.createSprite("AnsehenPressed"),
                5f,
                45f,
            )
        lookButton.addListener(TextTooltip("untersuchen", game.skin))
        lookButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(lookCursor, 0, 0))
                    stage.currentAction.type = ActionType.LOOK_AT
                    resetPlayerAndSound()
                }
            },
        )

        val speakCursor = Pixmap(Gdx.files.internal("ui/cursor/Reden.png"))
        val speakButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("Reden"),
                game.buttonAtlas.createSprite("RedenPressed"),
                65f,
                45f,
            )
        speakButton.addListener(TextTooltip("ansprechen", game.skin))
        speakButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(speakCursor, 0, 0))
                    stage.currentAction.type = ActionType.TALK_TO
                    resetPlayerAndSound()
                }
            },
        )

        val takeCursor = Pixmap(Gdx.files.internal("ui/cursor/Nehmen.png"))
        val takeButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("Nehmen"),
                game.buttonAtlas.createSprite("NehmenPressed"),
                120f,
                45f,
            )
        takeButton.addListener(TextTooltip("aufnehmen", game.skin))
        takeButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(takeCursor, 0, 0))
                    stage.currentAction.type = ActionType.ADD_TO_INVENTORY
                    resetPlayerAndSound()
                }
            },
        )

        val useCursor = Pixmap(Gdx.files.internal("ui/cursor/Benutzen.png"))
        val useButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("Benutzen"),
                game.buttonAtlas.createSprite("BenutzenPressed"),
                180f,
                45f,
            )
        useButton.addListener(TextTooltip("benutzen", game.skin))
        useButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(useCursor, 0, 0))
                    stage.currentAction.type = ActionType.USE
                    resetPlayerAndSound()
                }
            },
        )

        val combineCursor = Pixmap(Gdx.files.internal("ui/cursor/kombinieren.png"))
        val combineButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("kombinieren"),
                game.buttonAtlas.createSprite("kombinierenPressed"),
                240f,
                40f,
            )
        combineButton.addListener(TextTooltip("kombinieren", game.skin))
        combineButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    Gdx.graphics.setCursor(Gdx.graphics.newCursor(combineCursor, 0, 0))
                    stage.currentAction.type = ActionType.COMBINE
                    resetPlayerAndSound()
                }
            },
        )

        // Nur beim Single-Player sind die Buttons notwendig
        if (game.isSingleplayer) {
            val poolAttendantButton =
                game.createUIButton(
                    game.buttonAtlas.createSprite("Bademeister"),
                    game.buttonAtlas.createSprite("BademeisterPressed"),
                    3f,
                    707f,
                )
            poolAttendantButton.isVisible = false
            val duckButton =
                game.createUIButton(
                    game.buttonAtlas.createSprite("Ente"),
                    game.buttonAtlas.createSprite("EntePressed"),
                    5f,
                    705f,
                )

            duckButton.addListener(TextTooltip("wechsle zur Ente", game.skin))
            duckButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        poolAttendant.state = Player.Companion.State.STANDING
                        stage.currentPlayer = duck

                        poolAttendantButton.isVisible = true
                        duckButton.isVisible = false

                        speakButton.isVisible = true
                        takeButton.isVisible = false
                        useButton.isVisible = false
                        combineButton.isVisible = true

                        resetPlayerAndSound()
                    }
                },
            )
            poolAttendantButton.addListener(TextTooltip("wechsle zum Bademeister", game.skin))
            poolAttendantButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        duck.state = Player.Companion.State.STANDING
                        stage.currentPlayer = poolAttendant

                        poolAttendantButton.isVisible = false
                        duckButton.isVisible = true

                        speakButton.isVisible = false
                        takeButton.isVisible = true
                        useButton.isVisible = true
                        combineButton.isVisible = false

                        resetPlayerAndSound()
                    }
                },
            )

            speakButton.isVisible = false
            combineButton.isVisible = false

            stage.addActor(poolAttendantButton)
            stage.addActor(duckButton)
        }

        val exitButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("exit"),
                game.buttonAtlas.createSprite("exitPressed"),
                980f,
                705f,
            )
        exitButton.addListener(TextTooltip("raus hier", game.skin))
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    resetPlayerAndSound()
                    exitProcess(0)
                }
            },
        )

        stage.addActor(lookButton)
        stage.addActor(speakButton)
        stage.addActor(takeButton)
        stage.addActor(useButton)
        stage.addActor(combineButton)
        stage.addActor(exitButton)
    }

    abstract fun resetPlayerAndSound()

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport.update(width, height, true)
    }
}
