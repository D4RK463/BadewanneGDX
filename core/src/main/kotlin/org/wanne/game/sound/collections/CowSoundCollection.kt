package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.SoundCollection

const val COW = "cow"

class CowSoundCollection(am: AssetsManager) : SoundCollection(am) {

    companion object {
        const val DIALOG_END = 10
    }

    init {
        addAllSoundsFor(DIALOG_END, COW)
    }
}
