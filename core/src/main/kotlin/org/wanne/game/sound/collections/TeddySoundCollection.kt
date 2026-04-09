package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.SoundCollection

const val TEDDY = "teddy"

class TeddySoundCollection(am: AssetsManager) : SoundCollection(am) {

    companion object {
        const val DIALOG_END = 4
    }

    init {
        addAllSoundsFor(DIALOG_END, TEDDY)
    }
}
