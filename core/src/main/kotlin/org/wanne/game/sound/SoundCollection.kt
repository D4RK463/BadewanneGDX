package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound
import org.wanne.game.AssetsManager

open class SoundCollection(val am: AssetsManager) {
    private val sounds =
        mapOf(
            Speech.DE_ORIGINAL to mutableListOf<Sound>(),
            Speech.DE_NEU to mutableListOf<Sound>(),
            Speech.EN to mutableListOf<Sound>(),
            Speech.DROGL to mutableListOf<Sound>(),
        )

    fun addSound(
        speech: Speech,
        sound: Sound,
    ) {
        sounds[speech]?.add(sound)
    }

    fun get(
        speech: Speech,
        index: Int,
    ): Sound? = sounds[speech]?.get(index)
}
