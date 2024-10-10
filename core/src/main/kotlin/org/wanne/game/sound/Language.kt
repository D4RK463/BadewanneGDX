package org.wanne.game.sound

enum class Language(val language: String) {
    DE_ORIGINAL("DE_ORIGINAL"),
    DE_NEU("DE_NEU"),
    EN("EN"),
    DROGL("DROGL");


    companion object {
        fun fromSaveString(language: String): Language {
            return when(language) {
                "DE_ORIGINAL" -> DE_ORIGINAL
                "DE_NEU" -> DE_NEU
                "EN" -> EN
                "DROGL" -> DROGL
                else -> DE_ORIGINAL
            }
        }
    }
}
