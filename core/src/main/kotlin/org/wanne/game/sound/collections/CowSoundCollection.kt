package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class CowSoundCollection(am: AssetsManager) : SoundCollection(am) {
    init {

        // Deutsch Alt
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/waechterhier.mp3")) // 0
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/daskannichnichregeln.mp3")) // 1
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/15jahre.mp3")) // 2
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/vomAbflussInsZimmer.mp3")) // 3
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/ichwerdedafuerbezahlt.mp3")) // 4
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/mussdichtoeten.mp3")) // 5
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/gutgelaunt.mp3")) // 6
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/besorgsmir.mp3")) // 7
        addSound(Language.DE_ORIGINAL, am.get("soundsOriginal/Kuh/bisgleich.mp3")) // 8
    }
}
