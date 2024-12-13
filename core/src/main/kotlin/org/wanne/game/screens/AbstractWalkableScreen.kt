package org.wanne.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.ActionType
import org.wanne.game.model.Inventory
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.stage.PointAndClickAwareStage

abstract class AbstractWalkableScreen(
    private var game: WanneGame,
) : Screen {
    lateinit var stage: PointAndClickAwareStage

    lateinit var viewport: FitViewport

    val skin: Skin = game.currentSkin()

    val dialogBoard = DialogBoard(game = game)

    val inventory = Inventory.getInstance()

    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")

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
        // ToDo: Button müssen veränderbar sein, Position und Größe
        // Buttons
        val lookCursor: Pixmap = game.am.get("ui/cursor/Ansehen.png")
        val lookButton =
            game.createUIButton(
                buttonAtlas.createSprite("Ansehen"),
                buttonAtlas.createSprite("AnsehenPressed"),
                5f,
                45f,
            )
        lookButton.addListener(TextTooltip("untersuchen", skin))
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

        val speakCursor: Pixmap = game.am.get("ui/cursor/Reden.png")
        val speakButton =
            game.createUIButton(
                buttonAtlas.createSprite("Reden"),
                buttonAtlas.createSprite("RedenPressed"),
                65f,
                45f,
            )
        speakButton.addListener(TextTooltip("ansprechen", skin))
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

        val takeCursor: Pixmap = game.am.get("ui/cursor/Nehmen.png")
        val takeButton =
            game.createUIButton(
                buttonAtlas.createSprite("Nehmen"),
                buttonAtlas.createSprite("NehmenPressed"),
                120f,
                45f,
            )
        takeButton.addListener(TextTooltip("aufnehmen", skin))
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

        val useCursor: Pixmap = game.am.get("ui/cursor/Benutzen.png")
        val useButton =
            game.createUIButton(
                buttonAtlas.createSprite("Benutzen"),
                buttonAtlas.createSprite("BenutzenPressed"),
                180f,
                45f,
            )
        useButton.addListener(TextTooltip("benutzen", skin))
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

        val combineCursor: Pixmap = game.am.get("ui/cursor/kombinieren.png")
        val combineButton =
            game.createUIButton(
                buttonAtlas.createSprite("kombinieren"),
                buttonAtlas.createSprite("kombinierenPressed"),
                240f,
                40f,
            )
        combineButton.addListener(TextTooltip("kombinieren", skin))
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
                    buttonAtlas.createSprite("Bademeister"),
                    buttonAtlas.createSprite("BademeisterPressed"),
                    3f,
                    707f,
                )
            poolAttendantButton.isVisible = false
            val duckButton =
                game.createUIButton(
                    buttonAtlas.createSprite("Ente"),
                    buttonAtlas.createSprite("EntePressed"),
                    5f,
                    705f,
                )

            duckButton.addListener(TextTooltip("wechsle zur Ente", skin))
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
            poolAttendantButton.addListener(TextTooltip("wechsle zum Bademeister", skin))
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
                buttonAtlas.createSprite("Passen"),
                buttonAtlas.createSprite("PassenPressed"),
                978f,
                705f,
            )
        exitButton.addListener(TextTooltip("zurück zum Hauptmenü", skin))
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    resetPlayerAndSound()
                    game.screen = game.mainMenuScreen
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
