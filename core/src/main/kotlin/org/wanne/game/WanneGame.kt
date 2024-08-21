package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.game.sound.SoundManager
import org.wanne.screens.CowPhoneScreen
import org.wanne.screens.LoadingScreen
import org.wanne.screens.MenuScreen
import org.wanne.screens.OutsideScreen
import org.wanne.screens.PuzzleScreen
import org.wanne.screens.RoomScreen

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

    lateinit var batch: SpriteBatch

    val soundManager = SoundManager()
    val am = AssetsManager()

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
