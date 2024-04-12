package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.wanne.screens.MenuScreen

class WanneGame() : Game() {
    var batch: SpriteBatch? = null

    var isSingleplayer = true

    override fun create() {
        batch = SpriteBatch()
        this.setScreen(MenuScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch!!.dispose()
    }
}
