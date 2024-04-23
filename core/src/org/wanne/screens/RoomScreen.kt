package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.model.*
import kotlin.system.exitProcess

class RoomScreen(var game: WanneGame) : Screen {
    private lateinit var stage: Stage

    private lateinit var batch: SpriteBatch

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Buttons
    private val buttonAtlas: TextureAtlas = TextureAtlas("pictures/Buttons/buttons.atlas")

    // Players
    private var poolAttendant: Player = PoolAttendant(200F, 200F, Player.Companion.Looking.RIGHT)
    private var duck: Player = Duck(600F, 200F, Player.Companion.Looking.LEFT)
    private var currentPlayer: CurrentPlayer = CurrentPlayer.POOL_ATTENDANT

    private var stateTime: Float = 0f

    private lateinit var viewport: FitViewport

    private var moveToPoint: Point? = null
    private var needToMove = false

    private var currentAction: Action = Action.createDefaultAction()

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = SpriteBatch()
        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage

        // Klick Steuerung der Charaktere
        stage.addListener(
            object : InputListener() {
                override fun touchDown(
                    event: InputEvent?,
                    x: Float,
                    y: Float,
                    pointer: Int,
                    button: Int,
                ): Boolean {
                    // Aktion ausführen
                    if (currentAction.type != ActionType.NOTHING) {
                        println("${currentAction.type} at $x:$y")
                    } else { // oder laufen

                        // Raum Lauf-Limits
                        val limits = IntArray(4)
                        limits[0] = 241 // links
                        limits[1] = 129 // unten
                        limits[2] = 934 // rechts
                        limits[3] = 312 // oben

                        val clickLimits = IntArray(2)
                        clickLimits[0] = 685 // oben
                        clickLimits[1] = 129 // unten

                        var moveX = x.toInt()
                        var moveY = y.toInt()

                        // Er darf sich nur bewegen, wenn der Klick innerhalb der Spiellimits liegt
                        if (moveY < clickLimits[0] && moveY > clickLimits[1]) {
                            if (moveX < limits[0]) { // links
                                moveX = limits[0]
                            } else if (moveX > limits[2]) { // rechts
                                moveX = limits[2]
                            }
                            if (moveY < limits[1]) { // unten
                                moveY = limits[1]
                            } else if (moveY > limits[3]) { // oben
                                moveY = limits[3]
                            }

                            // Koordinaten am Raster ausrichten
                            moveX = (moveX - (moveX % Player.movePixel))
                            moveY = (moveY - (moveY % Player.movePixel))

                            moveToPoint = Point(moveX, moveY)
                            needToMove = true
                        } else {
                            needToMove = false
                        }
                    }

                    currentAction = Action.createDefaultAction()

                    return true
                }
            },
        )

        createGameUI()
    }

    private data class Point(var x: Int, var y: Int)

    private enum class CurrentPlayer {
        POOL_ATTENDANT,
        DUCK,
    }

    private fun createGameUI() {
        // Buttons
        val lookButton =
            createUIButton(
                buttonAtlas.createSprite("Ansehen"),
                buttonAtlas.createSprite("AnsehenPressed"),
                5f,
                45f,
            )
        lookButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentAction = Action(ActionType.LOOK_AT)
                }
            },
        )

        val speakButton =
            createUIButton(
                buttonAtlas.createSprite("Reden"),
                buttonAtlas.createSprite("RedenPressed"),
                65f,
                45f,
            )
        speakButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentAction = Action(ActionType.TALK_TO)
                }
            },
        )

        val takeButton =
            createUIButton(
                buttonAtlas.createSprite("Nehmen"),
                buttonAtlas.createSprite("NehmenPressed"),
                120f,
                45f,
            )
        takeButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentAction = Action(ActionType.ADD_TO_INVENTORY)
                }
            },
        )

        val useButton =
            createUIButton(
                buttonAtlas.createSprite("Benutzen"),
                buttonAtlas.createSprite("BenutzenPressed"),
                180f,
                45f,
            )
        useButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentAction = Action(ActionType.USE)
                }
            },
        )

        val combineButton =
            createUIButton(
                buttonAtlas.createSprite("kombinieren"),
                buttonAtlas.createSprite("kombinierenPressed"),
                240f,
                40f,
            )
        combineButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentAction = Action(ActionType.COMBINE)
                }
            },
        )

        // Nur beim Single-Player sind die Buttons notwendig
        if (game.isSingleplayer) {
            val poolAttendantButton =
                createUIButton(
                    buttonAtlas.createSprite("Bademeister"),
                    buttonAtlas.createSprite("BademeisterPressed"),
                    5f,
                    705f,
                )
            poolAttendantButton.isVisible = false
            val duckButton =
                createUIButton(
                    buttonAtlas.createSprite("Ente"),
                    buttonAtlas.createSprite("EntePressed"),
                    5f,
                    705f,
                )

            duckButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        poolAttendant.state = Player.Companion.State.STANDING
                        currentPlayer = CurrentPlayer.DUCK

                        poolAttendantButton.isVisible = true
                        duckButton.isVisible = false

                        speakButton.isVisible = true
                        takeButton.isVisible = false
                        useButton.isVisible = false
                        combineButton.isVisible = true

                        needToMove = false
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
                        currentPlayer = CurrentPlayer.POOL_ATTENDANT

                        poolAttendantButton.isVisible = false
                        duckButton.isVisible = true

                        speakButton.isVisible = false
                        takeButton.isVisible = true
                        useButton.isVisible = true
                        combineButton.isVisible = false

                        needToMove = false
                    }
                },
            )

            speakButton.isVisible = false
            combineButton.isVisible = false

            stage.addActor(poolAttendantButton)
            stage.addActor(duckButton)
        }

        val exitButton =
            createUIButton(
                buttonAtlas.createSprite("exit"),
                buttonAtlas.createSprite("exitPressed"),
                980f,
                705f,
            )
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
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

    private fun createUIButton(
        texture: Sprite,
        texturePressed: Sprite,
        x: Float,
        y: Float,
    ): ImageButton {
        val style = ImageButtonStyle()
        style.imageUp = TextureRegionDrawable(texture)
        style.imageDown = TextureRegionDrawable(texturePressed)
        val button = ImageButton(style)
        button.x = x
        button.y = y

        return button
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Movement
        if (moveToPoint != null && needToMove) {
            if (currentPlayer == CurrentPlayer.POOL_ATTENDANT) {
                poolAttendant.walkToPoint(moveToPoint!!.x, moveToPoint!!.y)
            } else {
                duck.walkToPoint(moveToPoint!!.x, moveToPoint!!.y)
            }
        }

        // Animationen holen
        stateTime += Gdx.graphics.deltaTime
        val poolAttendantSprite: Sprite = poolAttendant.getSpriteOfCurrentState(stateTime)
        val duckSprite: Sprite = duck.getSpriteOfCurrentState(stateTime)

        // Zeichnen
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        // Background
        if (game.isSingleplayer) {
            batch.draw(roomBackgroundSingle, 0f, 0f)
        } else {
            batch.draw(roomBackgroundMulti, 0f, 0f)
        }

        // Players
        poolAttendantSprite.draw(batch)
        duckSprite.draw(batch)
        batch.end()

        stage.act()
        stage.draw()
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch.dispose()
        buttonAtlas.dispose()
        poolAttendant.dispose()
        duck.dispose()
    }
}
