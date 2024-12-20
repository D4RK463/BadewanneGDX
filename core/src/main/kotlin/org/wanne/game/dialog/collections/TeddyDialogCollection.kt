package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.TeddySoundCollection

class TeddyDialogCollection(am: AssetsManager) : DialogCollection(am, TeddySoundCollection(am)) {

    init {
        getGermanDialog()
        getEnglishDialog()
        getDroglDialog()
    }

    private fun getGermanDialog() {
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
                "Was weisst du über den Wächter?",
                0
            )
        )

        addDialog(
            "Was weisst du über den Wächter?", Language.DE, Dialog(
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
    }


    private fun getEnglishDialog() {
        addDialog(
            "Start1", Language.EN, Dialog(
                "Hey Teddy!",
                null,
                "Where am I?",
                null,
                null
            )
        )

        addDialog(
            "Where am I?", Language.EN, Dialog(
                "Teddy: ... *shiver*",
                null,
                null,
                null,
                0,
                StateChange.YES
            )
        )

        addDialog(
            "Start2", Language.EN, Dialog(
                "Can you help me now?",
                null,
                "What's wrong with you?",
                "What do you know about the guardian?",
                0
            )
        )

        addDialog(
            "What do you know about the guardian?", Language.EN, Dialog(
                "Teddy: AAAHHHHHH!!!!",
                null,
                null,
                null,
                1,
                StateChange.YES
            )
        )

        addDialog(
            "What's wrong with you?", Language.EN, Dialog(
                "Teddy: I'm scared!",
                null,
                "Why?",
                "And what's up with Dr. Bear?",
                2
            )
        )

        addDialog(
            "Why?", Language.EN, Dialog(
                "Teddy: I'm afraid that I see the guardian.",
                null,
                null,
                "And what's up with Dr. Bear?",
                3
            )
        )

        addDialog(
            "And what's up with Dr. Bear?", Language.EN, Dialog(
                "Teddy: Well... He's taken",
                "too much of his own medicine.",
                null,
                null,
                4,
                StateChange.YES
            )
        )
    }

    private fun getDroglDialog() {
        addDialog(
            "Start1", Language.DROGL, Dialog(
                "Droggelbecher!",
                null,
                "Droggelbecher?",
                null,
                null
            )
        )

        addDialog(
            "Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: ... *drogl*",
                null,
                null,
                null,
                0,
                StateChange.YES
            )
        )

        addDialog(
            "Start2", Language.DROGL, Dialog(
                "Droggelbecher?",
                null,
                "Droggelbecher??",
                "Droggelbecher???",
                0
            )
        )

        addDialog(
            "Droggelbecher???", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!!!!",
                null,
                null,
                null,
                1,
                StateChange.YES
            )
        )

        addDialog(
            "Droggelbecher??", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                "Drogl becher?",
                "Droggelbecher Droggelbecher?",
                2
            )
        )

        addDialog(
            "Drogl becher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                "Droggelbecher?",
                3
            )
        )

        addDialog(
            "Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher, Droggelbecher. ",
                null,
                null,
                null,
                4,
                StateChange.YES
            )
        )

    }
}
