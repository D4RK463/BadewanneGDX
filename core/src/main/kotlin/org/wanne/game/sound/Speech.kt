package org.wanne.game.sound

import org.wanne.game.Language

enum class Speech(val speech: String, val language: Language) {
    DE_ORIGINAL("DE_ORIGINAL", Language.DE),
    DE_NEU("DE_NEU", Language.DE),
    EN("EN", Language.EN),
    DROGL("DROGL", Language.DROGL);

    companion object {
        fun fromSaveString(speech: String): Speech {
            return when(speech) {
                "DE_ORIGINAL" -> DE_ORIGINAL
                "DE_NEU" -> DE_NEU
                "EN" -> EN
                "DROGL" -> DROGL
                else -> DE_ORIGINAL
            }
        }
    }
}
