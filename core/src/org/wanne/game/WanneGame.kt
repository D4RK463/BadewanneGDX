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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.wanne.screens.CowPhoneScreen
import org.wanne.screens.MenuScreen
import org.wanne.screens.PuzzleScreen
import org.wanne.screens.RoomScreen

class WanneGame : Game() {
    val roomScreen: RoomScreen by lazy {
        RoomScreen(this)
    }
    lateinit var puzzleScreen: PuzzleScreen
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

        val game = this

        runBlocking {
            initialize(game)
        }
    }

    private suspend fun initialize(game: WanneGame) =
        coroutineScope {
            async { cowPhoneScreen = CowPhoneScreen(game) }
            async { puzzleScreen = PuzzleScreen(game) }
            async {
                // Spiel mit Menü starten
                game.setScreen(MenuScreen(game))
            }
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
