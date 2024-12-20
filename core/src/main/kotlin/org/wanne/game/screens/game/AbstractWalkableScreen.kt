package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.screens.util.UiButtonBuilder
import org.wanne.game.WanneGame
import org.wanne.game.model.ActionType
import org.wanne.game.model.Inventory
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.GameObject
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.stage.PointAndClickAwareStage

abstract class AbstractWalkableScreen(
    private var game: WanneGame,
) : Screen {
    lateinit var stage: PointAndClickAwareStage

    lateinit var viewport: FitViewport

    val dialogBoard = DialogBoard(game = game)

    val inventory = Inventory.getInstance()

    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")

    // Buttons
    private lateinit var lookButton: ImageButton
    private lateinit var speakButton: ImageButton
    private lateinit var takeButton: ImageButton
    private lateinit var useButton: ImageButton
    private lateinit var combineButton: ImageButton
    private lateinit var poolAttendantButton: ImageButton
    private lateinit var duckButton: ImageButton
    private lateinit var exitButton: ImageButton

    // Cursor
    val lookCursor: Pixmap = game.am.get("ui/cursor/Ansehen.png")
    val speakCursor: Pixmap = game.am.get("ui/cursor/Reden.png")
    val takeCursor: Pixmap = game.am.get("ui/cursor/Nehmen.png")
    val useCursor: Pixmap = game.am.get("ui/cursor/Benutzen.png")
    val combineCursor: Pixmap = game.am.get("ui/cursor/kombinieren.png")

    // Players
    lateinit var poolAttendant: PoolAttendant
    lateinit var duck: Duck

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

    fun addObjectsToStage(roomItemList: List<GameObject>) {
        val inventoryItems = inventory.items

        // Alle Objekte hinzufügen, die nicht im Inventar sind
        roomItemList.filter {
            for (item in inventoryItems) {
                if (item.name == it.name) {
                    return@filter false
                }
            }
            return@filter true
        }.forEach { item ->
            item.initialize()
            item.addToStage(stage, game.currentSkin())
        }

        // Inventar Objekt hinzufügen
        inventoryItems.forEach { item -> item.addToStage(stage, game.currentSkin()) }
    }

    fun createGameUI(
        poolAttendant: PoolAttendant,
        duck: Duck,
    ) {
        // Buttons
        lookButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("Ansehen"))
            .withTexturePressed(buttonAtlas.createSprite("AnsehenPressed"))
            .withPoint(game.choose(Point(5F, 45F), Point(130F, 640F)))
            .withTooltip(TextTooltip(game.choose("ansehen", "look at"), game.currentSkin()))
            .withScale(0.1F, 0.1F)
            .useScaling(!game.classicMode())
            .build()
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

        speakButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("Reden"))
            .withTexturePressed(buttonAtlas.createSprite("RedenPressed"))
            .withPoint(game.choose(Point(65F, 45F), Point(80F, 565F)))
            .withTooltip(TextTooltip(game.choose("ansprechen", "speak to"), game.currentSkin()))
            .useScaling(!game.classicMode())
            .build()
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

        takeButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("Nehmen"))
            .withTexturePressed(buttonAtlas.createSprite("NehmenPressed"))
            .withPoint(game.choose(Point(120F, 45F), Point(10F, 520F)))
            .withTooltip(TextTooltip(game.choose("aufnehmen", "take"), game.currentSkin()))
            .useScaling(!game.classicMode())
            .build()
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

        useButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("Benutzen"))
            .withTexturePressed(buttonAtlas.createSprite("BenutzenPressed"))
            .withPoint(game.choose(Point(180F, 45F), Point(80F, 565F)))
            .withTooltip(TextTooltip(game.choose("benutzen", "use"), game.currentSkin()))
            .useScaling(!game.classicMode())
            .build()
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

        combineButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("kombinieren"))
            .withTexturePressed(buttonAtlas.createSprite("kombinierenPressed"))
            .withPoint(game.choose(Point(240F, 40F), Point(10F, 520F)))
            .withTooltip(TextTooltip(game.choose("kombinieren", "combine"), game.currentSkin()))
            .useScaling(!game.classicMode())
            .build()
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
            poolAttendantButton = UiButtonBuilder()
                .withTexture(buttonAtlas.createSprite("Bademeister"))
                .withTexturePressed(buttonAtlas.createSprite("BademeisterPressed"))
                .withPoint(game.choose(Point(3F, 707F), Point(10F, 620F)))
                .withTooltip(TextTooltip(game.choose("wechsle zum Bademeister", "change to Pool Attendant"), game.currentSkin()))
                .setVisible(false)
                .withScale(0.4F, 0.4F)
                .useScaling(!game.classicMode())
                .build()

            duckButton = UiButtonBuilder()
                .withTexture(buttonAtlas.createSprite("Ente"))
                .withTexturePressed(buttonAtlas.createSprite("EntePressed"))
                .withPoint(game.choose(Point(5F, 705F), Point(12F, 618F)))
                .withTooltip(TextTooltip(game.choose("wechsle zur Ente", "change to Duck"), game.currentSkin()))
                .useScaling(!game.classicMode())
                .withScale(0.4F, 0.4F)
                .build()

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

        exitButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("Passen"))
            .withTexturePressed(buttonAtlas.createSprite("PassenPressed"))
            .withPoint(game.choose(Point(978F, 705F), Point(1200F, 640F)))
            .withTooltip(TextTooltip(game.choose("zurück zum Hauptmenü", "back to the Main menu"), game.currentSkin()))
            .useScaling(!game.classicMode())
            .build()
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

    override fun hide() {
        lookButton.remove()
        speakButton.remove()
        takeButton.remove()
        useButton.remove()
        combineButton.remove()
        poolAttendantButton.remove()
        duckButton.remove()
        exitButton.remove()
    }

}
