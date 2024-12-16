package org.wanne.game

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class UiButtonBuilder {

    private lateinit var texture: Sprite
    private lateinit var texturePressed: Sprite
    private var x: Float = 0F
    private var y: Float = 0F
    private var scaleX: Float = 1F
    private var scaleY: Float = 1F
    private var tooltip: TextTooltip? = null

    fun withTexture(texture: Sprite): UiButtonBuilder {
        this.texture = texture
        return this
    }

    fun withTexturePressed(texturePressed: Sprite): UiButtonBuilder {
        this.texturePressed = texturePressed
        return this
    }

    fun withX(x: Float): UiButtonBuilder {
        this.x = x
        return this
    }

    fun withY(y: Float): UiButtonBuilder {
        this.y = y
        return this
    }

    fun withScale(scaleX: Float, scaleY: Float): UiButtonBuilder {
        this.scaleX = scaleX
        this.scaleY = scaleY
        return this
    }

    fun withTooltip(tooltip: TextTooltip): UiButtonBuilder {
        this.tooltip = tooltip
        return this
    }

    fun build(): ImageButton {
        val style = ImageButtonStyle()
        style.imageUp = TextureRegionDrawable(texture)
        style.imageDown = TextureRegionDrawable(texturePressed)

        val button = ImageButton(style)
        button.x = x
        button.y = y

        if (tooltip != null) {
            button.addListener(tooltip)
        }

        return button
    }

}
