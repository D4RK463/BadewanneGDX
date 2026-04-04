package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.SoundCollection

const val ICEMAN = "iceman"

class IcemanSoundCollection(am: AssetsManager) : SoundCollection(am) {

    companion object {
        const val DIALOG_END = 9
    }

    init {
        addAllSoundsFor(DIALOG_END, ICEMAN)
    }

}
