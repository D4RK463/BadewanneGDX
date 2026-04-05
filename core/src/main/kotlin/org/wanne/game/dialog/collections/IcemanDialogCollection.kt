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
                0
            )
        )

        addDialog(
            "Hell und funkelt! Und bei dir?", Language.DE, Dialog(
                "Eismann: Jo man nice und shiny!",
                null,
                "Nice, kannst'e Eis entbehren?",
                null,
                1
            )
        )

        addDialog(
            "Nice, kannst'e Eis entbehren?", Language.DE, Dialog(
                "Eismann: Jo sure man! Take soviel,",
                "wie ihr wollt, Bros, No need anyway!",
                "Mega gut, Danke.",
                null,
                2,
                StateChange.LEFT
            )
        )

        addDialog(
            "Mega gut, Danke.", Language.DE, Dialog(
                "Eismann: Jo dude, Eis in the sunshine!",
                null,
                null,
                null,
                3
            )
        )

        addDialog(
            "Wie steht die Sonne? Echt jetz?", Language.DE, Dialog(
                "Eismann: Jo man, chill!",
                "Was wollt ihr, hee?",
                "Ich hätte gerne 1 riesen Eis für meinen Freund hier.",
                null,
                4
            )
        )

        addDialog(
            "Ich hätte gerne 1 riesen Eis für meinen Freund hier.", Language.DE, Dialog(
                "Eismann: Jo man, klar!",
                "Got the money?",
                "Ich schau mal in meiner Hose nach.",
                null,
                5,
                StateChange.RIGHT
            )
        )

        addDialog(
            "Ich schau mal in meiner Hose nach.", Language.DE, Dialog(
                "Eismann: Jo Ducky, whatever!",
                null,
                null,
                null,
                6
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Eismann: Mehr Eis, Duckboy?",
                null,
                "Danke, wir haben genug!",
                null,
                7
            )
        )

        addDialog(
            "Danke, wir haben genug!", Language.DE, Dialog(
                "Eismann: Jo dude, Eis in the sunshine!",
                null,
                null,
                null,
                3
            )
        )

        addDialog(
            "Start3", Language.DE, Dialog(
                "Eismann: Jo man, got the moneys?",
                null,
                null,
                null,
                8
            )
        )

        addDialog(
            "Start4", Language.DE, Dialog(
                "Eismann: Jo dude, mehr gibs nicht!",
                null,
                null,
                null,
                9
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
                0
            )
        )

        addDialog(
            "Bright and sparkling! And yours?", Language.EN, Dialog(
                "Iceman: Jo man nice and shiny!",
                null,
                "Nice, can you spare some ice cream?",
                null,
                1
            )
        )

        addDialog(
            "Nice, can you spare some ice cream?", Language.EN, Dialog(
                "Iceman: Jo sure man! Take as much,",
                "as you like, Bros, No need anyway!",
                "Awesome, thank you.",
                null,
                2,
                StateChange.LEFT
            )
        )

        addDialog(
            "Awesome, thank you.", Language.EN, Dialog(
                "Iceman: Jo dude, ice in the sunshine!",
                null,
                null,
                null,
                3
            )
        )

        addDialog(
            "How is the sun hanging? Seriously?", Language.EN, Dialog(
                "Iceman: Jo man, chill!",
                "What do you want, hee?",
                "I would like 1 large ice cream for my friend here.",
                null,
                4
            )
        )

        addDialog(
            "I would like 1 large ice cream for my friend here.", Language.EN, Dialog(
                "Iceman: Jo man, sure!",
                "Got the money?",
                "Let me check my wallet.",
                null,
                5,
                StateChange.RIGHT
            )
        )

        addDialog(
            "Let me check my wallet.", Language.EN, Dialog(
                "Iceman: Jo Ducky, whatever!",
                null,
                null,
                null,
                6
            )
        )

        addDialog(
            "Start2", Language.EN, Dialog(
                "Iceman: More ice, Duckboy?",
                null,
                "Thanks, we've had enough!",
                null,
                7
            )
        )

        addDialog(
            "Thanks, we've had enough!", Language.EN, Dialog(
                "Eismann: Jo dude, ice in the sunshine!",
                null,
                null,
                null,
                3
            )
        )

        addDialog(
            "Start3", Language.EN, Dialog(
                "Iceman: Jo man, got the moneys?",
                null,
                null,
                null,
                8
            )
        )

        addDialog(
            "Start4", Language.EN, Dialog(
                "Iceman: Jo dude, that's all you get!",
                null,
                null,
                null,
                9
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
