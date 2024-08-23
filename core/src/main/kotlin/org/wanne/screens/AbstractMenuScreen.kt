package org.wanne.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame

abstract class AbstractMenuScreen (
    private val game: WanneGame,
) : Screen {

    val skin: Skin = game.am.get("ui/uiskin.json")

    lateinit var viewport: FitViewport

    lateinit var batch: SpriteBatch

    fun createTextButton(
        label: String,
        x: Float,
        y: Float,
        width: Float = 300f,
        height: Float = 100f,
    ): TextButton {
        val button = TextButton(label, skin, "default")
        button.setPosition(x, y)
        button.setSize(width, height)

        return button
    }

}
