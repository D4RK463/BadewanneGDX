package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import org.wanne.game.dialog.DialogManager
import org.wanne.game.model.GameObjectsArchive
import org.wanne.game.model.Point
import org.wanne.game.network.Client
import org.wanne.game.network.Server
import org.wanne.game.screens.LoadingScreen
import org.wanne.game.screens.game.CowPhoneScreen
import org.wanne.game.screens.game.OutsideScreen
import org.wanne.game.screens.game.PuzzleScreen
import org.wanne.game.screens.game.RoomScreen
import org.wanne.game.screens.menu.MainMenuScreen
import org.wanne.game.screens.menu.NetworkScreen
import org.wanne.game.screens.menu.OptionsScreen
import org.wanne.game.sound.SoundManager
import org.wanne.game.sound.Speech
import java.util.*

class WanneGame(val android: Boolean): Game() {
    private val random = Random()

    val roomScreen: RoomScreen by lazy {
        RoomScreen(this)
    }
    val puzzleScreen: PuzzleScreen by lazy {
        PuzzleScreen(this)
    }

    lateinit var cowPhoneScreen: CowPhoneScreen

    val outsideScreen: OutsideScreen by lazy {
        OutsideScreen(this)
    }
    val mainMenuScreen: MainMenuScreen by lazy {
        MainMenuScreen(this)
    }
    val networkScreen: NetworkScreen by lazy {
        NetworkScreen(this)
    }
    val optionsScreen: OptionsScreen by lazy {
        OptionsScreen(this)
    }

    lateinit var batch: SpriteBatch

    lateinit var config: Config
    lateinit var soundManager: SoundManager
    lateinit var dialogManager: DialogManager
    lateinit var items: GameObjectsArchive
    val am = AssetsManager()

    lateinit var client: Client
    lateinit var server: Server

    var startedGame = false

    var isSingleplayer = true
    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false
    var arrivedOutside = false

    lateinit var wanneSkin: Skin

    override fun create() {
        batch = SpriteBatch()
        config = Config(android)
        soundManager = SoundManager(config)
        dialogManager = DialogManager(config, soundManager)

        wanneSkin = createWanneSkin()

        setScreen(LoadingScreen(this))
    }

    fun initializeItemHolder() {
        this.items = GameObjectsArchive(this)
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch.dispose()
    }

    fun reset() {
        // ToDo: Alle Bildschirme zurücksetzen
    }

    fun currentLang() = Speech.fromSaveString(config.speech)

    private fun createWanneSkin(): Skin {
        val skin = Skin()

        val generator = FreeTypeFontGenerator(Gdx.files.internal("ui/wanne/luchitas.ttf"))
        val parameter = FreeTypeFontParameter()
        parameter.size = 60
        val font = generator.generateFont(parameter)
        skin.add("Luchitas", font)

        skin.addRegions(TextureAtlas("ui/wanne/wanne.atlas"))
        skin.load(Gdx.files.internal("ui/wanne/wanne.json"))

        return skin
    }

    fun currentSkin(): Skin = if (classicMode()) {
        am.get("ui/default/uiskin.json")
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
    fun choose(german: String, english: String): String {
        return when(currentLang().language) {
            Language.DE -> german
            Language.EN -> english
            Language.DROGL -> randomizeDroggelbecher()
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

    /**
     * Entscheidet welches der richtige Punkt ist, anhand des aktuell
     * eingestellten Video Modes
     */
    fun choose(originalPoint: Point, widePoint: Point): Point {
        return if (config.mode == VideoMode.CLASSIC.name) {
            originalPoint
        } else {
            widePoint
        }
    }
}
