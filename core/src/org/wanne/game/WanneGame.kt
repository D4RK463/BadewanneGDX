package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.wanne.screens.CowPhoneScreen
import org.wanne.screens.MenuScreen
import org.wanne.screens.PuzzleScreen
import org.wanne.screens.RoomScreen
import kotlin.coroutines.EmptyCoroutineContext

class WanneGame : Game() {
    val roomScreen: RoomScreen by lazy {
        RoomScreen(this)
    }
    val puzzleScreen: PuzzleScreen by lazy {
        PuzzleScreen(this)
    }
    lateinit var cowPhoneScreen: CowPhoneScreen

    lateinit var buttonAtlas: TextureAtlas
    lateinit var skin: Skin

    lateinit var batch: SpriteBatch

    var isSingleplayer = true

    var puzzleSolved = false

    var talkedToCow = false

    var possessWinningObjects = false

    var cowIsBusy = false

    override fun create() {
        // Assets initialisieren
        buttonAtlas = TextureAtlas("pictures/Buttons/buttons.atlas")
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))
        batch = SpriteBatch()

        // Spiel mit Menü starten
        this.setScreen(MenuScreen(this))

        val game = this
        runBlocking {
            async { initializeCowScreen(game) }
        }

    }

    private suspend fun initializeCowScreen(game: WanneGame) = coroutineScope {
        cowPhoneScreen = CowPhoneScreen(game)
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        buttonAtlas.dispose()
        skin.dispose()
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
