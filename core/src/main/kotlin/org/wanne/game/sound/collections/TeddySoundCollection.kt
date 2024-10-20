package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.Speech
import org.wanne.game.sound.SoundCollection

class TeddySoundCollection(am: AssetsManager) : SoundCollection(am) {
    init {

        // Deutsch Alt
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Teddy/zitter.mp3")) // 0
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Teddy/ahhhhh.mp3")) // 1
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Teddy/habangst.mp3")) // 2
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Teddy/habangstdassichdenwaechtersehe.mp3")) // 3
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Teddy/achder.mp3")) // 4
    }
}
