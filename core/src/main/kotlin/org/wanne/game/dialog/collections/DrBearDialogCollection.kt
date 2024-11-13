package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.sound.collections.DrBearSoundCollection

class DrBearDialogCollection(am: AssetsManager): DialogCollection(am, DrBearSoundCollection(am)) {

    init {

        // Deutsch
        addDialog("Start1", Language.DE, Dialog(
            "Arztbär: Ohhhhhhh!?!",
            null,
            null,
            null,
            null
        ))

        addDialog("Start2", Language.DE, Dialog(
            "Arztbär: Eeeehhhhhehehehe!!",
            null,
            null,
            null,
            0
        ))


        // Englisch
        addDialog("Start1", Language.EN, Dialog(
            "Dr. Bear: Ohhhhhhh!?!",
            null,
            null,
            null,
            null
        ))

        addDialog("Start2", Language.EN, Dialog(
            "Dr. Bear: Eeeehhhhhehehehe!!",
            null,
            null,
            null,
            0
        ))

    }

}
