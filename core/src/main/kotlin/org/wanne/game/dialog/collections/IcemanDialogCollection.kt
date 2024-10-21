package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.IcemanSoundCollection

class IcemanDialogCollection(am: AssetsManager) : DialogCollection(am, IcemanSoundCollection(am)) {

    init {

        // Deutsch
        addDialog(
            "Start1", Language.DE, Dialog(
                "Eismann: Jo Duckboy und Bath-mann, ",
                "wie steht die Sonne?",
                "Wie steht die Sonne? Echt jetz?",
                "Hell und funkelt! Und bei dir?",
                null
            )
        )

        addDialog(
            "Hell und funkelt! Und bei dir?", Language.DE, Dialog(
                "Eismann: Jo man nice und shiny!",
                null,
                "Nice, kannst'e Eis entbehren?",
                null,
                null
            )
        )

        addDialog(
            "Nice, kannst'e Eis entbehren?", Language.DE, Dialog(
                "Eismann: Jo sure man! Take soviel,",
                "wie ihr wollt, Bros, No need anyway!",
                "Mega gut, Danke.",
                null,
                null,
                StateChange.LEFT
            )
        )

        addDialog(
            "Mega gut, Danke.", Language.DE, Dialog(
                "Eismann: Jo man, Eis in the sunshine!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Wie steht die Sonne? Echt jetz?", Language.DE, Dialog(
                "Eismann: Jo man chill!",
                "Was wollt ihr?",
                "Ich hätte gerne 1 großes Eis für meinen Freund hier.",
                null,
                null
            )
        )

        addDialog(
            "Ich hätte gerne 1 großes Eis für meinen Freund hier.", Language.DE, Dialog(
                "Eismann: Jo man klar!",
                "Got the money?",
                "Ich schau mal in meiner Hose nach.",
                null,
                null,
                StateChange.RIGHT
            )
        )

        addDialog(
            "Ich schau mal in meiner Hose nach.", Language.DE, Dialog(
                "Eismann: Jo Ducky, whatever!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Eismann: Jo man, mehr Eis, Duckyboy?",
                null,
                "Danke, wir haben genug!",
                null,
                null
            )
        )

        addDialog(
            "Danke, wir haben genug!", Language.DE, Dialog(
                "Eismann: Jo man, Eis in the sunshine!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start3", Language.DE, Dialog(
                "Eismann: Jo man, got the moneys?",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start4", Language.DE, Dialog(
                "Eismann: Jo man, mehr gibs nicht!",
                null,
                null,
                null,
                null
            )
        )

    }

}
