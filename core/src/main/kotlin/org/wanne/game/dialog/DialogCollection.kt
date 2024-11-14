package org.wanne.game.dialog

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.sound.SoundCollection

typealias StartSentence = String

open class DialogCollection(am: AssetsManager, val soundCollection: SoundCollection?) {
    private val dialogs = mapOf(
        Language.DE to mutableMapOf<StartSentence, Dialog>(),
        Language.EN to mutableMapOf<StartSentence, Dialog>(),
        Language.DROGL to mutableMapOf<StartSentence, Dialog>(),
    )

    fun addDialog(
        startSentence: StartSentence,
        language: Language,
        dialog: Dialog,
    ) {
        dialogs[language]?.put(startSentence, dialog)
    }

    fun get(
        language: Language,
        startSentence: StartSentence,
    ): Dialog? = dialogs[language]?.get(startSentence)
}
