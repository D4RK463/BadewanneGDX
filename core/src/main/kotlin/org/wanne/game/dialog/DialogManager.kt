package org.wanne.game.dialog

import org.wanne.game.Config
import org.wanne.game.Language
import org.wanne.game.sound.SoundManager
import org.wanne.game.sound.Speech

class DialogManager(private val config: Config, private val soundManager: SoundManager) {

    fun getFurtherDialogAndPlaySound(sentence: String?, collection: DialogCollection): Dialog? {
        val language: Language = Speech.fromSaveString(config.speech).language

        val dialog = sentence?.let { collection.get(language, it) }
        collection.soundCollection?.let { soundManager.playSound(it, dialog?.soundIndex) }

        return dialog
    }
}
