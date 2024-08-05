package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound

open class SoundCollection() {

    private val sounds = mapOf(Language.DE to mutableListOf<Sound>())

    fun addSound(language: Language, sound: Sound) {
        sounds[language]?.add(sound)
    }

    fun get(language: Language, index: Int): Sound? {
        return sounds[language]?.get(index)
    }

}