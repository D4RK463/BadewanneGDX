package org.wanne.game.sound.mario

import com.badlogic.gdx.Gdx
import org.wanne.game.sound.Language
import org.wanne.game.sound.SoundCollection

class MarioSoundCollection: SoundCollection() {
    init {
        addSound(Language.DE, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Mario/lassmich.mp3")))
        addSound(Language.DE, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Mario/feuerverloren.mp3")))
        addSound(Language.DE, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Mario/bowserweg.mp3")))
        addSound(Language.DE, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Mario/gefuehlsstein.mp3")))
        addSound(Language.DE, Gdx.audio.newSound(Gdx.files.internal("soundsOriginal/Mario/wiemanfeuerwieder.mp3")))
    }
}