package org.wanne.game.screens.util

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import org.wanne.game.model.Point

class UiButtonBuilder {

    private lateinit var texture: Sprite
    private lateinit var texturePressed: Sprite
    private var scaleX: Float = 0.2F
    private var scaleY: Float = 0.2F
    private var tooltip: TextTooltip? = null
    private var point: Point = Point(100F, 100F)
    private var useScaling: Boolean = false
    private var visible: Boolean = true

    fun withTexture(texture: Sprite): UiButtonBuilder {
        this.texture = texture
        return this
    }

    fun withTexturePressed(texturePressed: Sprite): UiButtonBuilder {
        this.texturePressed = texturePressed
        return this
    }

    fun withPoint(point: Point): UiButtonBuilder {
        this.point = point
        return this
    }

    fun withScale(scaleX: Float, scaleY: Float): UiButtonBuilder {
        this.scaleX = scaleX
        this.scaleY = scaleY
        return this
    }

    fun useScaling(useScaling: Boolean): UiButtonBuilder {
        this.useScaling = useScaling
        return this
    }

    fun withTooltip(tooltip: TextTooltip): UiButtonBuilder {
        this.tooltip = tooltip
        return this
    }

    fun setVisible(visible: Boolean): UiButtonBuilder {
        this.visible = visible
        return this
    }

    fun build(): ImageButton {
        val style = ImageButtonStyle()

        style.imageUp = TextureRegionDrawable(texture)
        style.imageDown = TextureRegionDrawable(texturePressed)

        val button = ImageButton(style)
        button.x = point.x
        button.y = point.y

        if (tooltip != null) {
            button.addListener(tooltip)
        }

        if (useScaling) {
            button.isTransform = true
            button.scaleBy(scaleX, scaleY)
        }

        button.isVisible = visible

        return button
    }

}
