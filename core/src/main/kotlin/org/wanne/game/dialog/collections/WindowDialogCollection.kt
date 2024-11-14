package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange

class WindowDialogCollection(am: AssetsManager) : DialogCollection(am, null) {

    init {

        // Deutsch
        addDialog(
            "Start1", Language.DE, Dialog(
                "Ich pass net durch.",
                null,
                "Hinaus sehen!",
                null,
                null
            )
        )

        addDialog(
            "Hinaus sehen!", Language.DE, Dialog(
                "Ich seh den Eiswagen, neben einem Graffiti stehen.",
                null,
                "Graffiti ansehn",
                null,
                null
            )
        )

        addDialog(
            "Graffiti ansehn", Language.DE, Dialog(
                "Ohhh... es steht 'el Barto' drauf?!?",
                null,
                null,
                null,
                null,
                StateChange.YES
            )
        )

        // Englisch
        addDialog(
            "Start1", Language.EN, Dialog(
                "I am not fitting through.",
                null,
                "Look outside!",
                null,
                null
            )
        )

        addDialog(
            "Look outside!", Language.EN, Dialog(
                "I see the ice cream van standing next to a graffiti.",
                null,
                "Look at the graffiti",
                null,
                null
            )
        )

        addDialog(
            "Look at the graffiti", Language.EN, Dialog(
                "Ohhh... it says 'el Barto'?!?",
                null,
                null,
                null,
                null,
                StateChange.YES
            )
        )
    }
}
