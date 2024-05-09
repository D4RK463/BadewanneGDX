package org.wanne.model.dialog

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import org.wanne.model.AbstractObject

class DialogBoard(posX: Float = 0F, posY: Float = 137F, val skin: Skin) : AbstractObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    private var label1: Label? = null
    private var label2: Label? = null
    private var label3: Label? = null

    fun prepLookAt(lookAtSentence: String) {
        label1 = Label(lookAtSentence, skin)
        label2 = null
        label3 = null

        this.isVisible = true
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Brett"))
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (this.isVisible) {
            super.draw(batch, parentAlpha)
            if (label1 != null) {
                label1?.x = 5F
                label1?.y = 200F
                label1?.draw(batch, parentAlpha)
            }

            if (label2 != null) {
                label2?.x = 5F
                label2?.y = 175F
                label2?.draw(batch, parentAlpha)
            }

            if (label3 != null) {
                label3?.x = 5F
                label3?.y = 150F
                label3?.draw(batch, parentAlpha)
            }
        }
    }

}