package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.SoundCollection

const val MARIO = "mario"

class MarioSoundCollection(am: AssetsManager) : SoundCollection(am) {

    companion object {
        const val DIALOG_END = 22
    }

    init {
        addAllSoundsFor(DIALOG_END, MARIO)
    }
}
