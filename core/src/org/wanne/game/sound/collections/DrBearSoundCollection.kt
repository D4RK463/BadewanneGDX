package org.wanne.game.sound.collections

import com.badlogic.gdx.Gdx
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class DrBearSoundCollection : SoundCollection() {
    init {

        // Deutsch Alt
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Arztbaer/arztbaerLachen.mp3"))) // 0
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Arztbaer/aufschneiden.mp3"))) // 1
    }
}
