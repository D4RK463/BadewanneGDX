package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound
import org.wanne.game.Config

class SoundManager(private val config: Config) {
    private var playingSound: Sound? = null

    private var aboutToPlayingSound: Sound? = null

    fun playSound(
        collection: SoundCollection,
        index: Int?,
        volume: Float = config.soundVolume,
    ) {
        aboutToPlayingSound = index?.let { collection.get(Speech.fromSaveString(config.speech), it) }

        // Stellt sicher, dass der Sound nur einmal gespielt wird
        if (playingSound != aboutToPlayingSound) {
            stopSound()
            playingSound = aboutToPlayingSound
            playingSound?.play(volume)
        }
    }

    fun stopSound() {
        playingSound?.stop()
        playingSound = null
    }
}
