package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.wanne.screens.MainMenuScreen

class WanneGame : Game() {
    var batch: SpriteBatch? = null
    var font: BitmapFont? = null

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont() // use libGDX's default Arial font
        this.setScreen(MainMenuScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch!!.dispose()
        font!!.dispose()
    }
}
