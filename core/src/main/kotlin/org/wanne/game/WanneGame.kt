package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.game.dialog.DialogManager
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


class WanneGame(val android: Boolean): Game() {
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
    val am = AssetsManager()

    lateinit var client: Client
    lateinit var server: Server

    var startedGame = false

    var isSingleplayer = true
    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false

    lateinit var wanneSkin: Skin

    override fun create() {
        batch = SpriteBatch()
        config = Config(android)
        soundManager = SoundManager(config)
        dialogManager = DialogManager(config, soundManager)

        wanneSkin = createWanneSkin()

        setScreen(LoadingScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch.dispose()
    }

    fun reset() {
        // ToDo: Alle Bildschirme zurücksetzen

        //roomScreen = RoomScreen(this)
    }

    fun createUIButton(
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

    fun currentLang() = Speech.fromSaveString(config.speech)

    private fun createWanneSkin(): Skin {
        val skin = Skin()

        val generator = FreeTypeFontGenerator(Gdx.files.internal("ui/wanne/luchitas.ttf"))
        val parameter = FreeTypeFontParameter()
        parameter.size = 60
        parameter.color = Color.ORANGE
        val font = generator.generateFont(parameter)
        skin.add("Luchitas", font)

        skin.addRegions(TextureAtlas("ui/wanne/wanne.atlas"))
        skin.load(Gdx.files.internal("ui/wanne/wanne.json"))

        return skin
    }
}
