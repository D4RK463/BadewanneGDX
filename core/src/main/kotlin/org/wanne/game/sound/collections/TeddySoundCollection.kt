package org.wanne.game.sound.collections

import com.badlogic.gdx.Gdx
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class TeddySoundCollection : SoundCollection() {
    init {

        // Deutsch Alt
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Teddy/zitter.mp3"))) // 0
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Teddy/ahhhhh.mp3"))) // 1
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Teddy/habangst.mp3"))) // 2
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Teddy/habangstdassichdenwaechtersehe.mp3"))) // 3
        addSound(Language.DE_ORIGINAL, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Teddy/achder.mp3"))) // 4
    }
}
