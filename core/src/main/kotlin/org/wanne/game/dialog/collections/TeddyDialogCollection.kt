package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.TeddySoundCollection

class TeddyDialogCollection(am: AssetsManager) : DialogCollection(am, TeddySoundCollection(am)) {

    init {

        // Deutsch
        addDialog(
            "Start1", Language.DE, Dialog(
                "Hey Teddy!",
                null,
                "Wo bin ich hier?",
                null,
                null
            )
        )

        addDialog(
            "Wo bin ich hier?", Language.DE, Dialog(
                "Teddy: ... *zitter*",
                null,
                null,
                null,
                0,
                StateChange.YES
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Kannste mir jetzt helfen?",
                null,
                "Was is los mit dir?",
                "Was weißt du über den Wächter?",
                0
            )
        )

        addDialog(
            "Was weißt du über den Wächter?", Language.DE, Dialog(
                "Teddy: AAAHHHHHH!!!!",
                null,
                null,
                null,
                1,
                StateChange.YES
            )
        )

        addDialog(
            "Was is los mit dir?", Language.DE, Dialog(
                "Teddy: Hab Angst!",
                null,
                "Warum?",
                "Was ist mit dem Arztbär los?",
                2
            )
        )

        addDialog(
            "Warum?", Language.DE, Dialog(
                "Teddy: Hab Angst, dass ich den Wächter sehe.",
                null,
                null,
                "Und was ist mit dem Arztbär los?",
                3
            )
        )

        addDialog(
            "Und was ist mit dem Arztbär los?", Language.DE, Dialog(
                "Teddy: Ach... Der hat zuviel von seiner ",
                "eigenen Medizin genommen.",
                null,
                null,
                4,
                StateChange.YES
            )
        )

        addDialog(
            "Was ist mit dem Arztbär los?", Language.DE, Dialog(
                "Teddy: Ach... Der hat zuviel von seiner ",
                "eigenen Medizin genommen.",
                null,
                null,
                4,
                StateChange.YES
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Kannste mir jetzt helfen?",
                null,
                "Was is los mit dir?",
                "Was weißt du über den Wächter?",
                null
            )
        )

    }

}
