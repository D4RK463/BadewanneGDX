package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.CowSoundCollection

class CowDialogCollection(am: AssetsManager) : DialogCollection(am, CowSoundCollection(am)) {

    init {

        // Deutsch
        addDialog(
            "Start1", Language.DE, Dialog(
                "Kuh: Wäwächter hier!!",
                null,
                "Hi...Ente hier, lass",
                "uns verdammt nochma hier raus!!!",
                0
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Kuh: Wäwächter hier!!",
                null,
                null,
                "Was brauch ich noch ma?",
                0
            )
        )

        addDialog(
            "Hi...Ente hier, lass", Language.DE, Dialog(
                "Kuh: Dadas kann ich nicht,",
                "auauch ich muss mich an didie Regeln halten.",
                "Von welchen Regeln sprichst du?",
                "Warum hast du uns entführt?",
                1
            )
        )

        addDialog(
            "uns verdammt nochma hier raus!!!", Language.DE, Dialog(
                "Kuh: Dadas kann ich nicht,",
                "auauch ich muss mich an didie Regeln halten.",
                "Von welchen Regeln sprichst du?",
                "Warum hast du uns entführt?",
                1
            )
        )

        addDialog(
            "Von welchen Regeln sprichst du?", Language.DE, Dialog(
                "Kuh: Ichch soll euch 15 Jajahre gefangen haltenn.",
                "Warum dadarf ich nicht sasagen!",
                null,
                "Gibt es nicht irgendeine andere Möglichkeit?",
                2
            )
        )

        addDialog(
            "Warum hast du uns entführt?", Language.DE, Dialog(
                "Kuh: Ichch hole jejeden der im Abfluss",
                "landet und brringe ihn ins Zzimmer.",
                null,
                "Wie bitte?",
                3
            )
        )

        addDialog(
            "Wie bitte?", Language.DE, Dialog(
                "Kuh: Ichch werde dafühür bezahhlt! Frage",
                "dich lieber warum dudu im Abfluss gelandet bist.",
                "Was können wir tun um hier rauszukommen?",
                "Wer bezahlt dich?",
                4
            )
        )

        addDialog(
            "Wer bezahlt dich?", Language.DE, Dialog(
                "Kuh: Wewen ich dir das ssage",
                "mümüsste ich dich tötöten.",
                null,
                "Was können wir tun um hier rauszukommen?",
                5
            )
        )

        addDialog(
            "Was können wir tun um hier rauszukommen?", Language.DE, Dialog(
                "Kuh: Dudu hast Glück, ich bibin sehr gut gelaunt.",
                null,
                null,
                "Und?",
                6
            )
        )

        addDialog(
            "Gibt es nicht irgendeine andere Möglichkeit?", Language.DE, Dialog(
                "Kuh: Dudu hast Glück, ich bibin sehr gut gelaunt.",
                null,
                null,
                "Und?",
                6
            )
        )

        addDialog(
            "Und?", Language.DE, Dialog(
                "Kuh: Bebesorge mir Valium, memeine",
                "Kuhglocke und den Milchabsauger 2000. 'Klick'",
                null,
                "Na schön, wenn's sein muss.",
                7
            )
        )

        addDialog(
            "Was brauch ich noch ma?", Language.DE, Dialog(
                "Kuh: Bebesorge mir Valium, memeine",
                "Kuhglocke und den Milchabsauger 2000. 'Klick'",
                null,
                "Na schön, wenn's sein muss.",
                7
            )
        )

        addDialog(
            "Na schön, wenn's sein muss.", Language.DE, Dialog(
                StateChange.YES
            )
        )

        addDialog(
            "Start3", Language.DE, Dialog(
                "Kuh: Wawas willst duu schon wieder?",
                null,
                null,
                "Wir haben dein ganzes Zeug gesammelt.",
                null
            )
        )

        addDialog(
            "Wir haben dein ganzes Zeug gesammelt.", Language.DE, Dialog(
                "Kuh: Sehr guuuut. Daann mach ich euch",
                "gleich die Tüür auf.",
                null,
                "Mach hin. Wir wolln Eis essen gehn!",
                8
            )
        )

        addDialog(
            "Mach hin. Wir wolln Eis essen gehn!", Language.DE, Dialog(
                "Kuh: Ey jetzzt nicht frech werdenn.",
                "Wehe ihr saagt jemand daas ich euch raus lasse!",
                null,
                "Alles klar...",
                null
            )
        )

        addDialog(
            "Alles klar...", Language.DE, Dialog(
                StateChange.YES
            )
        )

    }

}
