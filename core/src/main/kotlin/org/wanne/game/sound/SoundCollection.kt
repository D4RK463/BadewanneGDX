package org.wanne.game.sound

import com.badlogic.gdx.audio.Sound
import org.wanne.game.AssetsManager
import org.wanne.game.DIALOG

open class SoundCollection(val am: AssetsManager) {
    private val sounds =
        mapOf(
            Speech.DE_ORIGINAL to mutableListOf<Sound>(),
            Speech.DE_NEU to mutableListOf<Sound>(),
            Speech.EN to mutableListOf<Sound>(),
            Speech.DROGL to mutableListOf<Sound>(),
        )

    private val activeSounds = listOf(Speech.DE_ORIGINAL, Speech.DE_NEU, Speech.EN, Speech.DROGL)

    fun addAllSoundsFor(dialogEnd: Int, directory: String) {
        activeSounds.forEach {
            for (i in 0..dialogEnd) {
                addSound(it, am["${DIALOG}/${it.dir()}/$directory/${i}.mp3"])
            }
        }
    }

    private fun addSound(
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
