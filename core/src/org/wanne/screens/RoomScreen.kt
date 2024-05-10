package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.model.Action
import org.wanne.model.ActionType
import org.wanne.model.Point
import org.wanne.model.dialog.DialogAwareStage
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.objects.Bed
import org.wanne.model.objects.Box
import org.wanne.model.objects.BrucePoster
import org.wanne.model.objects.Cowbell
import org.wanne.model.objects.DeanPoster
import org.wanne.model.objects.Door
import org.wanne.model.objects.DrBear
import org.wanne.model.objects.Drawer
import org.wanne.model.objects.FireFlower
import org.wanne.model.objects.GameObject
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
import kotlin.system.exitProcess

class RoomScreen(private var game: WanneGame) : Screen {
    private lateinit var stage: DialogAwareStage
    private lateinit var batch: SpriteBatch
    private var skin: Skin = Skin(Gdx.files.internal("ui/uiskin.json"))

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Buttons
    private val buttonAtlas: TextureAtlas = TextureAtlas("pictures/Buttons/buttons.atlas")

    // Objects
    private val bed = Bed()
    private val roomWindow = Window()
    private val drawer = Drawer()
    private val door = Door()
    private val pa2Poster = PA2Poster()
    private val brucePoster = BrucePoster()
    private val deanPoster = DeanPoster()
    private val rug = Rug()
    private val stickers = Stickers()
    private val straw = Straw()
    private val box = Box()
    private val safe = Safe()
    private val drBear = DrBear()
    private val stethoscope = Stethoscope()
    private val scalpel = Scalpel()
    private val mario = Mario()
    private val bell = Cowbell()
    private val teddy = Teddy()
    private val flower = FireFlower()
    private val telephone = Telephone()
    private val milkSucker = MilkSucker()
    private val pills = Pills()
    private val note = Note()

    // Dialog System
    private val dialogBoard = DialogBoard(skin = skin)

    // Players
    private var poolAttendant: Player = PoolAttendant(200F, 200F, Player.Companion.Looking.RIGHT)
    private var duck: Player = Duck(600F, 200F, Player.Companion.Looking.LEFT)
    private var currentPlayer: Player = poolAttendant

    private lateinit var viewport: FitViewport

    private var moveToPoint: Point? = null
    private var needToMove = false

    private var currentAction: Action = Action.createDefaultAction()
    private var lookingAtTheEnd: Player.Companion.Looking? = null
    private var doTheAction: () -> Unit = {}

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = SpriteBatch()
        stage = DialogAwareStage(viewport, poolAttendant, duck)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(roomBackgroundSingle))
        } else {
            stage.addActor(Image(roomBackgroundMulti))
        }

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
                    // Aktion
                    if (currentAction.type != ActionType.NOTHING) {
                        println("${currentAction.type} at $x:$y")

                        // Das Objekt holen, auf welches geklickt wurde
                        val hitObject = stage.hit(x, y, true)
                        println(hitObject)
                        if (hitObject is GameObject) {
                            currentAction.clickedObject = hitObject

                            // Auf das Objekt zugehen und in die richtige Richtung schauen
                            moveToPoint = currentAction.clickedObject?.getInteractPosition()?.first
                            lookingAtTheEnd = currentAction.clickedObject?.getInteractPosition()?.second
                            needToMove = true

                            // Aktion ausführen als Lambda
                            doTheAction = {
                                currentAction.action(dialogBoard)
                            }
                        } else if (hitObject is Image) { // Escape vom Dialog
                            currentAction.type = ActionType.NOTHING

                            lookingAtTheEnd = null
                            doTheAction = {}
                            dialogBoard.reset()
                        }
                    } else { // oder laufen
                        lookingAtTheEnd = null
                        doTheAction = {}
                        dialogBoard.reset()

                        // Raum Lauf-Limits
                        val limits = IntArray(4)
                        limits[0] = 255 // links
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
                            moveX = (moveX - (moveX % Player.MOVE_PIXEL))
                            moveY = (moveY - (moveY % Player.MOVE_PIXEL))

                            moveToPoint = Point(moveX, moveY)
                            needToMove = true
                        } else {
                            needToMove = false
                        }
                    }

                    // Cursor reset
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)

                    return true
                }
            },
        )

        createGameObjects()
        createGameUI()

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)
    }

    private fun createGameObjects() {
        // Objekte hinzufügen (Reihenfolge ist wichtig)
        bed.addListener(TextTooltip("Bett", skin))
        stage.addActor(bed)
        drBear.addListener(TextTooltip("Arztbär", skin))
        stage.addActor(drBear)
        stethoscope.addListener(TextTooltip("Stethoskop", skin))
        stage.addActor(stethoscope)
        scalpel.addListener(TextTooltip("Skalpell", skin))
        stage.addActor(scalpel)
        mario.addListener(TextTooltip("Mario", skin))
        stage.addActor(mario)
        bell.addListener(TextTooltip("Kuhglocke", skin))
        stage.addActor(bell)
        roomWindow.addListener(TextTooltip("Kleines Fenster", skin))
        stage.addActor(roomWindow)
        drawer.addListener(TextTooltip("Holzschrank", skin))
        stage.addActor(drawer)
        stickers.addListener(TextTooltip("Aufkleber", skin))
        stage.addActor(stickers)
        straw.addListener(TextTooltip("Stroh", skin))
        stage.addActor(straw)
        door.addListener(TextTooltip("Tür", skin))
        stage.addActor(door)
        pa2Poster.addListener(TextTooltip("Poster", skin))
        stage.addActor(pa2Poster)
        brucePoster.addListener(TextTooltip("Bruce Lee", skin))
        stage.addActor(brucePoster)
        deanPoster.addListener(TextTooltip("James Dean Film", skin))
        stage.addActor(deanPoster)
        safe.addListener(TextTooltip("Alter Safe", skin))
        stage.addActor(safe)
        rug.addListener(TextTooltip("Funky Teppich", skin))
        stage.addActor(rug)
        flower.addListener(TextTooltip("Feuerblume", skin))
        stage.addActor(flower)
        telephone.addListener(TextTooltip("Rosa Telefon", skin))
        stage.addActor(telephone)
        box.addListener(TextTooltip("Blaue Kiste", skin))
        stage.addActor(box)
        teddy.addListener(TextTooltip("Teddy", skin))
        stage.addActor(teddy)

        // Objekte für später im Spiel
        milkSucker.addListener(TextTooltip("Milchabsauger 2000", skin))
        milkSucker.isVisible = false
        stage.addActor(milkSucker)
        pills.addListener(TextTooltip("Tabletten", skin))
        pills.isVisible = false
        stage.addActor(pills)
        note.addListener(TextTooltip("Zettel mit Telefonnummer", skin))
        note.isVisible = false
        stage.addActor(note)
    }

    private fun createGameUI() {
        // Buttons
        val lookCursor = Pixmap(Gdx.files.internal("ui/cursor/Ansehen.png"))
        val lookButton =
            createUIButton(
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
                    needToMove = false
                    currentPlayer.stopHammerTime()
                    currentAction = Action(ActionType.LOOK_AT)
                }
            },
        )

        val speakCursor = Pixmap(Gdx.files.internal("ui/cursor/Reden.png"))
        val speakButton =
            createUIButton(
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
                    needToMove = false
                    currentPlayer.stopHammerTime()
                    currentAction = Action(ActionType.TALK_TO)
                }
            },
        )

        val takeCursor = Pixmap(Gdx.files.internal("ui/cursor/Nehmen.png"))
        val takeButton =
            createUIButton(
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
                    needToMove = false
                    currentPlayer.stopHammerTime()
                    currentAction = Action(ActionType.ADD_TO_INVENTORY)
                }
            },
        )

        val useCursor = Pixmap(Gdx.files.internal("ui/cursor/Benutzen.png"))
        val useButton =
            createUIButton(
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
                    needToMove = false
                    currentPlayer.stopHammerTime()
                    currentAction = Action(ActionType.USE)
                }
            },
        )

        val combineCursor = Pixmap(Gdx.files.internal("ui/cursor/kombinieren.png"))
        val combineButton =
            createUIButton(
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
                    needToMove = false
                    currentPlayer.stopHammerTime()
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
                    3f,
                    707f,
                )
            poolAttendantButton.isVisible = false
            val duckButton =
                createUIButton(
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
                        currentPlayer = duck

                        poolAttendantButton.isVisible = true
                        duckButton.isVisible = false

                        speakButton.isVisible = true
                        takeButton.isVisible = false
                        useButton.isVisible = false
                        combineButton.isVisible = true

                        needToMove = false
                        currentPlayer.stopHammerTime()
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
                        currentPlayer = poolAttendant

                        poolAttendantButton.isVisible = false
                        duckButton.isVisible = true

                        speakButton.isVisible = false
                        takeButton.isVisible = true
                        useButton.isVisible = true
                        combineButton.isVisible = false

                        needToMove = false
                        currentPlayer.stopHammerTime()
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
        exitButton.addListener(TextTooltip("raus hier", skin))
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    needToMove = false
                    currentPlayer.stopHammerTime()
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
            currentPlayer.walkToPoint(
                moveToPoint!!.x,
                moveToPoint!!.y,
                lookingAtTheEnd,
                doTheAction,
            )
        }

        // Zeichnen
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        // Stage zeichnen mit UI, Objekten, Spielern und dem Dialog-Brett
        stage.act()
        stage.draw()

        batch.end()
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
        bed.dispose()
        roomWindow.dispose()
        box.dispose()
        brucePoster.dispose()
        deanPoster.dispose()
        door.dispose()
        drawer.dispose()
        pa2Poster.dispose()
        rug.dispose()
        stickers.dispose()
        straw.dispose()
        safe.dispose()
        drBear.dispose()
        stethoscope.dispose()
        scalpel.dispose()
        mario.dispose()
        bell.dispose()
        teddy.dispose()
        flower.dispose()
        telephone.dispose()
        milkSucker.dispose()
        pills.dispose()
        note.dispose()
        dialogBoard.dispose()
    }
}
