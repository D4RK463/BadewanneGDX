package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PointAndClickListener
import org.wanne.game.model.Point
import org.wanne.game.model.animation.FireAnimation
import org.wanne.game.model.animation.PowerUpAnimation
import org.wanne.game.model.objects.Bed
import org.wanne.game.model.objects.Box
import org.wanne.game.model.objects.BrucePoster
import org.wanne.game.model.objects.Cowbell
import org.wanne.game.model.objects.DeanPoster
import org.wanne.game.model.objects.Door
import org.wanne.game.model.objects.DrBear
import org.wanne.game.model.objects.Drawer
import org.wanne.game.model.objects.Exit
import org.wanne.game.model.objects.FireFlower
import org.wanne.game.model.objects.GameObject
import org.wanne.game.model.objects.Mario
import org.wanne.game.model.objects.MilkSucker
import org.wanne.game.model.objects.Note
import org.wanne.game.model.objects.PA2Poster
import org.wanne.game.model.objects.Pills
import org.wanne.game.model.objects.Rug
import org.wanne.game.model.objects.Safe
import org.wanne.game.model.objects.Scalpel
import org.wanne.game.model.objects.Stethoscope
import org.wanne.game.model.objects.Stickers
import org.wanne.game.model.objects.Straw
import org.wanne.game.model.objects.Teddy
import org.wanne.game.model.objects.Telephone
import org.wanne.game.model.objects.Window
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.screens.AbstractWalkableScreen
import org.wanne.game.stage.PointAndClickAwareStage

class RoomScreen(
    private var game: WanneGame,
) : AbstractWalkableScreen(game) {

    // Background
    private val roomBackgroundSingle: Texture = game.am.get("pictures/Backgrounds/KinderzimmerSingle.png")
    private val roomBackgroundMulti: Texture = game.am.get("pictures/Backgrounds/Kinderzimmer.png")
    private val roomBackgroundSingleWide: Texture = game.am.get("pictures/Backgrounds/Kinderzimmer169.png")

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/Kinderzimmer.mp3")

    // Animations
    private lateinit var fireAnimation: FireAnimation
    private lateinit var powerUpAnimation: PowerUpAnimation

    // Objects
    private lateinit var pills: Pills
    private lateinit var bed: Bed
    private lateinit var roomWindow: Window
    private lateinit var drawer: Drawer
    private lateinit var door: Door
    private lateinit var pa2Poster: PA2Poster
    private lateinit var brucePoster: BrucePoster
    private lateinit var deanPoster: DeanPoster
    private lateinit var rug: Rug
    private lateinit var stickers: Stickers
    private lateinit var straw: Straw
    private lateinit var box: Box
    private lateinit var safe: Safe
    private lateinit var drBear: DrBear
    private lateinit var milkSucker: MilkSucker
    private lateinit var stethoscope: Stethoscope
    private lateinit var scalpel: Scalpel
    private lateinit var note: Note
    private lateinit var mario: Mario
    private lateinit var bell: Cowbell
    private lateinit var teddy: Teddy
    private lateinit var flower: FireFlower
    private lateinit var telephone: Telephone
    private lateinit var exit: Exit

    // Players
    private lateinit var poolAttendant: PoolAttendant
    private lateinit var duck: Duck

    override fun show() {
        // Dinge erstellen
        createAnimations()
        createGameObjects()
        createPlayableCharacters()
        val limits = createRoomLimits()

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())

        stage = PointAndClickAwareStage(viewport, poolAttendant, duck, listOf(fireAnimation, powerUpAnimation))
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            if (game.classicMode()) {
                stage.addActor(Image(roomBackgroundSingle))
            } else {
                stage.addActor(Image(roomBackgroundSingleWide))
            }
        } else {
            stage.addActor(Image(roomBackgroundMulti))
        }

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        // Klick Steuerung der Charaktere
        stage.addListener(PointAndClickListener(dialogBoard, limits))

        // Stage Config
        inventory.rearrangeObjects(!game.classicMode())
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

    private fun createAnimations() {
        fireAnimation = FireAnimation(
            game.choose(Point(82F, 198F), Point(336F, 148F)),
            false,
            game.am
        )
        powerUpAnimation = PowerUpAnimation(
            game.choose(Point(82F, 345F), Point(336F, 295F)),
            false,
            game.am
        )
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

    private fun createGameObjects() {
        pills = Pills(game = game)
        bed = Bed(game = game)
        roomWindow = Window(game = game)
        drawer = Drawer(game = game)
        door = Door(game = game)
        pa2Poster = PA2Poster(game = game)
        brucePoster = BrucePoster(game = game)
        deanPoster = DeanPoster(game = game)
        rug = Rug(game = game)
        stickers = Stickers(game = game)
        straw = Straw(game = game)
        box = Box(game = game)
        safe = Safe(game = game)
        drBear = DrBear(game = game, gameObjectToAppear = pills)
        milkSucker = MilkSucker(game = game)
        stethoscope = Stethoscope(game = game, gameObjectToAppear = milkSucker)
        scalpel = Scalpel(game = game, gameObjectToAppear = pills)
        note = Note(game = game)
        mario =
            Mario(
                game = game,
                gameObjectToManipulate = rug,
                gameObjectToAppear = note,
                fireAnimation = fireAnimation,
                powerUpAnimation = powerUpAnimation
            )
        bell = Cowbell(game = game)
        teddy = Teddy(game = game, gameObjectToCheck = mario)
        flower = FireFlower(game = game, gameObjectToManipulate = mario)
        telephone = Telephone(game = game, winningRequiredGameObjectList = listOf(milkSucker, pills, bell))
        exit = Exit(game = game)
    }

    private fun configureGameObjects() {
        // Reihenfolge ist wichtig
        val roomItemList: List<GameObject> = listOf(
            bed, drBear, stethoscope, scalpel, mario, bell, roomWindow, drawer, stickers, exit,
            straw, door, pa2Poster, brucePoster, deanPoster, safe, rug, flower, telephone, box,
            teddy, milkSucker, pills, note
        )
        val inventoryItems = inventory.items

        // Alle Objekte hinzufügen, die nicht im Inventar sind
        roomItemList.filter {
            for (item in inventoryItems) {
                if (item.name == it.name) {
                    return@filter false
                }
            }
            return@filter true
        }.forEach { item -> item.addToStage(stage, game.currentSkin())}

        // Inventar Objekt hinzufügen
        inventoryItems.forEach { item -> item.addToStage(stage, game.currentSkin()) }

        // Der Ausgang darf nur am Ende auf sein :)
        exit.isVisible = false
//        exit.isVisible = true
//        door.isVisible = false

        // Wenn wir aus der Kuh Szene zurückkommen, ist die Notiz im Inventar und muss sicher bleiben.
        if (!inventory.isObjectInInventory(note)) {
            note.isVisible = false
        }
        if (!inventory.isObjectInInventory(milkSucker)) {
            milkSucker.isVisible = false
        }
        if (!inventory.isObjectInInventory(pills)) {
            pills.isVisible = false
        }

        if (game.puzzleSolved) {
            flower.solved = true
        }

        if (game.cowIsBusy) {
            door.isVisible = false
            exit.isVisible = true
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

        bed.remove()
        drBear.remove()
        stethoscope.remove()
        scalpel.remove()
        mario.remove()
        bell.remove()
        roomWindow.remove()
        drawer.remove()
        stickers.remove()
        exit.remove()
        straw.remove()
        door.remove()
        pa2Poster.remove()
        brucePoster.remove()
        deanPoster.remove()
        safe.remove()
        rug.remove()
        flower.remove()
        telephone.remove()
        box.remove()
        teddy.remove()
        milkSucker.remove()
        pills.remove()
        note.remove()

        dialogBoard.remove()
    }

    override fun dispose() {
        stage.dispose()
    }
}
