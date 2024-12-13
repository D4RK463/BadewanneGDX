package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.IcemanSoundCollection

class IcemanDialogCollection(am: AssetsManager) : DialogCollection(am, IcemanSoundCollection(am)) {

    init {
        addGermanDialog()
        addEnglishDialog()
        addDroglDialog()
    }

    private fun addGermanDialog() {
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

    private fun addEnglishDialog() {
        addDialog(
            "Start1", Language.EN, Dialog(
                "Iceman: Jo Duckboy and Bath-mann, ",
                "how is the sun hanging?",
                "How is the sun hanging? Seriously?",
                "Bright and sparkling! And yours?",
                null
            )
        )

        addDialog(
            "Bright and sparkling! And yours?", Language.EN, Dialog(
                "Iceman: Jo man nice and shiny!",
                null,
                "Nice, can you spare some ice cream?",
                null,
                null
            )
        )

        addDialog(
            "Nice, can you spare some ice cream?", Language.EN, Dialog(
                "Iceman: Jo sure man! Take as much,",
                "as you like, Bros, No need anyway!",
                "Awesome, thank you.",
                null,
                null,
                StateChange.LEFT
            )
        )

        addDialog(
            "Awesome, thank you.", Language.EN, Dialog(
                "Iceman: Jo man, ice in the sunshine!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "How is the sun hanging? Seriously?", Language.EN, Dialog(
                "Iceman: Jo man chill!",
                "What do you want?",
                "I would like 1 large ice cream for my friend here.",
                null,
                null
            )
        )

        addDialog(
            "I would like 1 large ice cream for my friend here.", Language.EN, Dialog(
                "Iceman: Jo man sure!",
                "Got the money?",
                "Let me check my wallet.",
                null,
                null,
                StateChange.RIGHT
            )
        )

        addDialog(
            "Let me check my wallet.", Language.EN, Dialog(
                "Iceman: Jo Ducky, whatever!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start2", Language.EN, Dialog(
                "Iceman: Jo man, more ice, Duckyboy?",
                null,
                "Thanks, we've had enough!",
                null,
                null
            )
        )

        addDialog(
            "Thanks, we've had enough!", Language.EN, Dialog(
                "Eismann: Jo man, ice in the sunshine!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start3", Language.EN, Dialog(
                "Iceman: Jo man, got the moneys?",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start4", Language.EN, Dialog(
                "Iceman: Jo man, that's all you get!",
                null,
                null,
                null,
                null
            )
        )
    }

    private fun addDroglDialog() {
        addDialog(
            "Start1", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher, ",
                "Droggelbecher?",
                "Droggelbecher? Droggelbecher?",
                "Droggelbecher! Droggelbecher?",
                null
            )
        )

        addDialog(
            "Droggelbecher! Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                "Droggelbecher?",
                null,
                null
            )
        )

        addDialog(
            "Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher! Droggelbecher,",
                "Droggelbecher!",
                "Droggelbecher.",
                null,
                null,
                StateChange.LEFT
            )
        )

        addDialog(
            "Droggelbecher.", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Droggelbecher? Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                "Droggelbecher?",
                "Droggelbecher.?",
                null,
                null
            )
        )

        addDialog(
            "Droggelbecher.?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                "Droggelbecher...",
                null,
                null,
                StateChange.RIGHT
            )
        )

        addDialog(
            "Droggelbecher...", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start2", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher?",
                null,
                "Droggelbecher",
                null,
                null
            )
        )

        addDialog(
            "Droggelbecher", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start3", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher?",
                null,
                null,
                null,
                null
            )
        )

        addDialog(
            "Start4", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                null,
                null,
                null
            )
        )
    }

}
