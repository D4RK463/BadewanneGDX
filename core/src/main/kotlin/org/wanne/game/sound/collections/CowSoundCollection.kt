package org.wanne.game.sound.collections

import com.badlogic.gdx.Gdx
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class CowSoundCollection : SoundCollection() {
    init {

        // Deutsch Alt
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/waechterhier.mp3"))) // 0
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/daskannichnichregeln.mp3"))) // 1
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/15jahre.mp3"))) // 2
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/vomAbflussInsZimmer.mp3"))) // 3
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/ichwerdedafuerbezahlt.mp3"))) // 4
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/mussdichtoeten.mp3"))) // 5
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/gutgelaunt.mp3"))) // 6
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/besorgsmir.mp3"))) // 7
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Kuh/bisgleich.mp3"))) // 8
    }
}
