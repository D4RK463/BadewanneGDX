package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.game.network.Client
import org.wanne.game.network.Server
import org.wanne.game.screens.menu.NetworkScreen
import org.wanne.game.sound.SoundManager
import org.wanne.game.screens.game.CowPhoneScreen
import org.wanne.game.screens.LoadingScreen
import org.wanne.game.screens.menu.MenuScreen
import org.wanne.game.screens.game.OutsideScreen
import org.wanne.game.screens.game.PuzzleScreen
import org.wanne.game.screens.game.RoomScreen

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
    val menuScreen: MenuScreen by lazy {
        MenuScreen(this)
    }
    val networkScreen: NetworkScreen by lazy {
        NetworkScreen(this)
    }

    lateinit var batch: SpriteBatch

    lateinit var config: Config
    lateinit var soundManager: SoundManager
    val am = AssetsManager()

    lateinit var client: Client
    lateinit var server: Server

    var isSingleplayer = true
    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false

    override fun create() {
        batch = SpriteBatch()
        config = Config()
        soundManager = SoundManager(config)

        setScreen(LoadingScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch.dispose()
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
}
