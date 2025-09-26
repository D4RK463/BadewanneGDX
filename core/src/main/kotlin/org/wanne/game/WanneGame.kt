package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import de.damios.guacamole.Stopwatch
import org.wanne.game.dialog.DialogManager
import org.wanne.game.model.GameObjectsArchive
import org.wanne.game.model.Point
import org.wanne.game.network.Client
import org.wanne.game.network.NetworkManager
import org.wanne.game.network.Server
import org.wanne.game.screens.LoadingScreen
import org.wanne.game.screens.game.CowPhoneScreen
import org.wanne.game.screens.game.OutsideScreen
import org.wanne.game.screens.game.PuzzleScreen
import org.wanne.game.screens.game.RoomScreen
import org.wanne.game.screens.menu.ControlsScreen
import org.wanne.game.screens.menu.MainMenuScreen
import org.wanne.game.screens.menu.NetworkScreen
import org.wanne.game.screens.menu.OptionsScreen
import org.wanne.game.screens.menu.ResultScreen
import org.wanne.game.screens.video.VideoScreen
import org.wanne.game.sound.SoundManager
import org.wanne.game.sound.Speech
import java.util.*

const val VERSION = "0.7.8"

class WanneGame(val android: Boolean): Game() {
    private val random = Random()

    // Game Screens
    lateinit var roomScreen: RoomScreen
    lateinit var puzzleScreen: PuzzleScreen
    lateinit var cowPhoneScreen: CowPhoneScreen
    lateinit var outsideScreen: OutsideScreen

    // Video Screens
    val introVideoScreen: VideoScreen by lazy {
        VideoScreen(this, roomScreen, am.introVideo)
    }
    val outroVideoScreen: VideoScreen by lazy {
        VideoScreen(this, resultScreen, am.outroVideo)
    }

    // Menu Screens
    val mainMenuScreen: MainMenuScreen by lazy {
        MainMenuScreen(this)
    }
    val networkScreen: NetworkScreen by lazy {
        NetworkScreen(this)
    }
    val optionsScreen: OptionsScreen by lazy {
        OptionsScreen(this)
    }
    val controlsScreen: ControlsScreen by lazy {
        ControlsScreen(this)
    }
    val resultScreen: ResultScreen by lazy {
        ResultScreen(this)
    }

    lateinit var batch: SpriteBatch

    lateinit var config: Config
    lateinit var soundManager: SoundManager
    lateinit var dialogManager: DialogManager
    lateinit var items: GameObjectsArchive
    val am = AssetsManager()

    val network = NetworkManager(this)

    var startedGame = false
    var gameEnded = true

    var isSingleplayer = true
    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false
    var arrivedOutside = false

    var unlockDrogglWithNewPlaythrough = true

    lateinit var wanneSkin: Skin

    val timer: Stopwatch = Stopwatch.createUnstarted()

    override fun create() {
        batch = SpriteBatch()
        config = Config(android)
        soundManager = SoundManager(config)
        dialogManager = DialogManager(config, soundManager)

        unlockDrogglWithNewPlaythrough = !config.drooglUnlocked

        wanneSkin = createWanneSkin()

        setScreen(LoadingScreen(this))
    }

    override fun dispose() {
        batch.dispose()
    }

    fun reset() {
        items = GameObjectsArchive()
        items.resetItems(this)

        puzzleSolved = false
        talkedToCow = false
        possessWinningObjects = false
        cowIsBusy = false
        arrivedOutside = false

        roomScreen = RoomScreen(this)
        puzzleScreen = PuzzleScreen(this)
        outsideScreen = OutsideScreen(this)

    }

    fun currentLang() = Speech.fromSaveString(config.speech)

    private fun createWanneSkin(): Skin {
        val skin = Skin()

        val generator = FreeTypeFontGenerator(Gdx.files.internal("$SKINS/wanne/luchitas.ttf"))
        val parameter = FreeTypeFontParameter()
        parameter.size = 60
        val font = generator.generateFont(parameter)
        skin.add("Luchitas", font)

        skin.addRegions(TextureAtlas("$SKINS/wanne/wanne.atlas"))
        skin.load(Gdx.files.internal("$SKINS/wanne/wanne.json"))

        return skin
    }

    fun currentSkin(): Skin = if (classicMode()) {
        am["$SKINS/default/uiskin.json"]
    } else {
        wanneSkin
    }

    fun classicMode(): Boolean {
        return config.mode == VideoMode.CLASSIC.toString()
    }

    /**
     * Entscheidet welches der richtige String ist, anhand der aktuell
     * eingestellten Sprache, oder Droggelbecher
     */
    fun choose(german: String, english: String, useDroggelbecher: Boolean = true): String {
        return when(currentLang().language) {
            Language.DE -> german
            Language.EN -> english
            Language.DROGL -> {
                if (useDroggelbecher) {
                    randomizeDroggelbecher()
                } else {
                    english
                }
            }
        }
    }

    /**
     * Entscheidet welches der richtige Punkt ist, anhand des aktuell
     * eingestellten Video Modes
     */
    fun choose(originalPoint: Point, widePoint: Point): Point {
        return if (classicMode()) {
            originalPoint
        } else {
            widePoint
        }
    }

    fun randomizeDroggelbecher(): String {
        val sign: String = when(random.nextInt(5)) {
            0 -> ""
            1 -> "!"
            2 -> "?"
            3 -> "!?"
            4 -> "!!"
            else -> ""
        }
        return "Droggelbecher$sign"
    }
}
