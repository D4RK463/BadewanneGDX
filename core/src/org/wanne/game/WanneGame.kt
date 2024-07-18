package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.screens.MenuScreen
import org.wanne.screens.PuzzleScreen
import org.wanne.screens.RoomScreen

class WanneGame : Game() {
    lateinit var roomScreen: RoomScreen
    lateinit var puzzleScreen: PuzzleScreen

    lateinit var buttonAtlas: TextureAtlas
    lateinit var skin: Skin

    var isSingleplayer = true

    var puzzleSolved = false

    override fun create() {
        // Assets initialisieren
        buttonAtlas = TextureAtlas("pictures/Buttons/buttons.atlas")
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        // Andere Screens initialisieren
        roomScreen = RoomScreen(this)
        puzzleScreen = PuzzleScreen(this)

        // Spiel mit Menü starten
        this.setScreen(MenuScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        buttonAtlas.dispose()
        skin.dispose()
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
