package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.Speech
import org.wanne.game.sound.SoundCollection

class DrBearSoundCollection(am: AssetsManager) : SoundCollection(am) {
    init {

        // Deutsch Alt
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Arztbaer/arztbaerLachen.mp3")) // 0
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Arztbaer/aufschneiden.mp3")) // 1
    }
}
