package org.wanne.game.screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.WanneGame
import org.wanne.game.screens.menu.Shadow

abstract class AbstractScreen(
    val game: WanneGame,
): Screen {

    fun createLabelWithShadow(
        text: String,
        posX: Float,
        posY: Float,
        fontScale: Float = 1F,
        color: Color? = null
    ): Pair<Label, Shadow> {
        val label = Label(text, game.wanneSkin)
        label.setPosition(posX, posY)
        label.setFontScale(fontScale)
        color?.apply { label.color = color }

        val shadow = Label(text, game.wanneSkin)
        shadow.setPosition(posX+2, posY-2)
        shadow.color = Color.BLACK
        shadow.setFontScale(fontScale)

        return Pair(label, shadow)
    }

}
