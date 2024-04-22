package org.wanne.game

import com.badlogic.gdx.Game
import org.wanne.screens.MenuScreen

class WanneGame() : Game() {
    var isSingleplayer = true

    override fun create() {
        this.setScreen(MenuScreen(this))
    }

    override fun render() {
        super.render() // important!
    }

    override fun dispose() {
    }
}
