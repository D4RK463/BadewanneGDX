package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.wanne.screens.MainMenuScreen
import org.wanne.screens.MenuTestScreen

class WanneGame : Game() {
    var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
//        this.setScreen(MainMenuScreen(this))
        this.setScreen(MenuTestScreen())
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
        batch!!.dispose()
    }
}
