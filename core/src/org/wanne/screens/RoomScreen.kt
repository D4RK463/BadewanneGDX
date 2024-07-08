package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.PointAndClickAwareStage
import org.wanne.game.PointAndClickListener
import org.wanne.game.WanneGame
import org.wanne.model.ActionType
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

class RoomScreen(
    private var game: WanneGame,
) : Screen {
    private lateinit var stage: PointAndClickAwareStage
    private lateinit var batch: SpriteBatch

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Objects
    private val pills = Pills()
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
    private val drBear = DrBear(gameObjectToAppear = pills)
    private val milkSucker = MilkSucker()
    private val stethoscope = Stethoscope(gameObjectToAppear = milkSucker)
    private val scalpel = Scalpel(gameObjectToAppear = pills)
    private val note = Note()
    private val mario = Mario(gameObjectToManipulate = rug, gameObjectToAppear = note)
    private val bell = Cowbell()
    private val teddy = Teddy(gameObjectToCheck = mario)
    private val flower = FireFlower(gameObjectToManipulate = mario, game = game)
    private val telephone = Telephone(winningRequiredGameObjectList = listOf(milkSucker, pills, bell))

    // Dialog System
    private val dialogBoard = DialogBoard(skin = game.skin)

    // Players
    private var poolAttendant: Player = PoolAttendant(200F, 200F, Player.Companion.Looking.RIGHT)
    private var duck: Player = Duck(600F, 200F, Player.Companion.Looking.LEFT)

    private lateinit var viewport: FitViewport

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = SpriteBatch()
        stage = PointAndClickAwareStage(viewport, poolAttendant, duck)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(roomBackgroundSingle))
        } else {
            stage.addActor(Image(roomBackgroundMulti))
        }

        // Klick Steuerung der Charaktere
        stage.addListener(PointAndClickListener(dialogBoard))

        createGameObjects()
        createGameUI()

        // Anfangs muss das Dialog-Brett nicht angezeigt werden
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)
    }

    private fun createGameObjects() {
        // Objekte hinzufügen (Reihenfolge ist wichtig)
        bed.addListener(TextTooltip("Bett", game.skin))
        stage.addActor(bed)
        drBear.addListener(TextTooltip("Arztbär", game.skin))
        stage.addActor(drBear)
        stethoscope.addListener(TextTooltip("Stethoskop", game.skin))
        stage.addActor(stethoscope)
        scalpel.addListener(TextTooltip("Skalpell", game.skin))
        stage.addActor(scalpel)
        mario.addListener(TextTooltip("Mario", game.skin))
        stage.addActor(mario)
        bell.addListener(TextTooltip("Kuhglocke", game.skin))
        stage.addActor(bell)
        roomWindow.addListener(TextTooltip("Kleines Fenster", game.skin))
        stage.addActor(roomWindow)
        drawer.addListener(TextTooltip("Holzschrank", game.skin))
        stage.addActor(drawer)
        stickers.addListener(TextTooltip("Aufkleber", game.skin))
        stage.addActor(stickers)
        straw.addListener(TextTooltip("Stroh", game.skin))
        stage.addActor(straw)
        door.addListener(TextTooltip("Tür", game.skin))
        stage.addActor(door)
        pa2Poster.addListener(TextTooltip("Poster", game.skin))
        stage.addActor(pa2Poster)
        brucePoster.addListener(TextTooltip("Bruce Lee", game.skin))
        stage.addActor(brucePoster)
        deanPoster.addListener(TextTooltip("James Dean Film", game.skin))
        stage.addActor(deanPoster)
        safe.addListener(TextTooltip("Alter Safe", game.skin))
        stage.addActor(safe)
        rug.addListener(TextTooltip("Funky Teppich", game.skin))
        stage.addActor(rug)
        flower.addListener(TextTooltip("Feuerblume", game.skin))
        stage.addActor(flower)
        telephone.addListener(TextTooltip("Rosa Telefon", game.skin))
        stage.addActor(telephone)
        box.addListener(TextTooltip("Blaue Kiste", game.skin))
        stage.addActor(box)
        teddy.addListener(TextTooltip("Teddy", game.skin))
        stage.addActor(teddy)

        // Objekte für später im Spiel
        milkSucker.addListener(TextTooltip("Milchabsauger 2000", game.skin))
        milkSucker.isVisible = false
        stage.addActor(milkSucker)
        pills.addListener(TextTooltip("Tabletten", game.skin))
        pills.isVisible = false
        stage.addActor(pills)
        note.addListener(TextTooltip("Zettel mit Telefonnummer", game.skin))
        note.isVisible = false
        stage.addActor(note)
    }

    private fun createGameUI() {
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
                    stage.currentAction.type = ActionType.LOOK_AT
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
                    stage.currentAction.type = ActionType.TALK_TO
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
                    stage.currentAction.type = ActionType.ADD_TO_INVENTORY
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
                    stage.currentAction.type = ActionType.USE
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
                    stage.currentAction.type = ActionType.COMBINE
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

                        stage.needToMove = false
                        stage.currentPlayer.stopHammerTime()
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

                        stage.needToMove = false
                        stage.currentPlayer.stopHammerTime()
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
                    stage.needToMove = false
                    stage.currentPlayer.stopHammerTime()
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

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Zeichnen
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        // Stage zeichnen mit UI, Objekten, Spielern, dem Dialog-Brett und Spieler Bewegung
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
        stage.dispose()
    }
}
