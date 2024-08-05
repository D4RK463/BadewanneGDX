package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound

class SoundManager {

    private var playingSound: Sound? = null

    private var aboutToPlayingSound: Sound? = null

    var language: Language = Language.DE

    fun playSound(collection: SoundCollection, index: Int, volume: Float = 1.0F) {
        aboutToPlayingSound = collection.get(language, index)

        // Stellt sicher, dass der Sound nur einmal gespielt wird
        if (playingSound != aboutToPlayingSound) {
            playingSound = aboutToPlayingSound
            playingSound?.play(volume);
        }
    }

    fun stopSound() {
        playingSound?.stop()
        playingSound = null
    }

}