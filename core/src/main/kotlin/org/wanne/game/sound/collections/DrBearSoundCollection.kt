package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class DrBearSoundCollection(am: AssetsManager) : SoundCollection(am) {
    init {

        // Deutsch Alt
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Arztbaer/arztbaerLachen.mp3")) // 0
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Arztbaer/aufschneiden.mp3")) // 1
    }
}
