package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound

open class SoundCollection {
    private val sounds =
        mapOf(
            Language.DE_ORIGINAL to mutableListOf<Sound>(),
            Language.DE_NEU to mutableListOf<Sound>(),
            Language.EN to mutableListOf<Sound>(),
            Language.DROGL to mutableListOf<Sound>(),
        )

    fun addSound(
        language: Language,
        sound: Sound,
    ) {
        sounds[language]?.add(sound)
    }

    fun get(
        language: Language,
        index: Int,
    ): Sound? = sounds[language]?.get(index)
}
