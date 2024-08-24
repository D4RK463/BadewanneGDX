package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.game.network.Client
import org.wanne.game.network.Server
import org.wanne.screens.menu.NetworkScreen
import org.wanne.game.sound.SoundManager
import org.wanne.screens.game.CowPhoneScreen
import org.wanne.screens.LoadingScreen
import org.wanne.screens.menu.MenuScreen
import org.wanne.screens.game.OutsideScreen
import org.wanne.screens.game.PuzzleScreen
import org.wanne.screens.game.RoomScreen

class WanneGame : Game() {
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

    val soundManager = SoundManager()
    val am = AssetsManager()
    lateinit var client: Client
    lateinit var server: Server

    val config = Config()

    var isSingleplayer = true
    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false

    override fun create() {
        batch = SpriteBatch()

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
