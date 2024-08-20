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

    var isSingleplayer = true

    var puzzleSolved = false

    var talkedToCow = false

    var possessWinningObjects = false

    var cowIsBusy = false

    private var initialLoadingDone = false
    private var currentLoad = 0
    private var percent = 0F
    val am = AssetsManager()

    override fun create() {
        batch = SpriteBatch()
    }

    override fun render() {

        // Assets initialisieren
        if (am.update(17)) { // Load some, will return true if done loading
            currentLoad+= 1
            when (currentLoad) {
                1 -> {
                    am.loadUI()
                    println("loading ui ($percent%)")
                }
                2 -> {
                    am.loadTextures()
                    println("loading textures ($percent%)")
                }
                3 -> {
                    am.loadSprites()
                    println("loading sprites ($percent%)")
                }
                4 -> {
                    am.loadMusic()
                    println("loading music ($percent%)")
                }
                5 -> {
                    am.loadSounds()
                    println("loading sounds ($percent%)")
                }
                else -> {
                    if (currentLoad > 6){
                        percent = 1F // set bar to full

                        if (!initialLoadingDone) {
                            println("loading complete ($percent%)")
                            setScreen(menuScreen)

                            cowPhoneScreen = CowPhoneScreen(this)
                            initialLoadingDone = true
                        }
                    }
                }
            }

        } else{
            percent = Interpolation.linear.apply(percent, am.progress(), 0.05f)
        }

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
