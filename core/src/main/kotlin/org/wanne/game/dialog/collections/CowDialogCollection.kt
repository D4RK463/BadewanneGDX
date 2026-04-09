package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.CowSoundCollection

class CowDialogCollection(am: AssetsManager) : DialogCollection(am, CowSoundCollection(am)) {

    init {
        addGermanDialog()
        addEnglishDialog()
        addDroglDialog()
    }

    private fun addGermanDialog() {
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
                9
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
                10
            )
        )

        addDialog(
            "Alles klar...", Language.DE, Dialog(
                StateChange.YES
            )
        )
    }

    private fun addEnglishDialog() {
        addDialog(
            "Start1", Language.EN, Dialog(
                "Cow: Guaguardian speeaking!!",
                null,
                "Hi... I'm Duck,",
                "let us out!!!",
                0
            )
        )

        addDialog(
            "Start2", Language.EN, Dialog(
                "Cow: Guaguardian speeaking!!",
                null,
                null,
                "What do I need again?",
                0
            )
        )

        addDialog(
            "Hi... I'm Duck,", Language.EN, Dialog(
                "Cow: I I can't do do that.",
                "I have to abide by the ruuules too.",
                "What rules do you mean?",
                "Why did you kidnap us?",
                1
            )
        )

        addDialog(
            "let us out!!!", Language.EN, Dialog(
                "Cow: I I can't do do that.",
                "I have to abide by the ruuules too.",
                "What rules do you mean?",
                "Why did you kidnap us?",
                1
            )
        )

        addDialog(
            "What rules do you mean?", Language.EN, Dialog(
                "Cow: I I have to detain you you for 15 years.",
                "Can't telll you why!",
                null,
                "Isn't there any other possibility?",
                2
            )
        )

        addDialog(
            "Why did you kidnap us?", Language.EN, Dialog(
                "Cow: I I kidnap everyone who ge-gets sucked",
                "in and dro-drop them into the roooom.",
                null,
                "Excuse me?",
                3
            )
        )

        addDialog(
            "Excuse me?", Language.EN, Dialog(
                "Cow: I I'm getting payed fooor this! Better",
                "thiink about, why yooouu are here.",
                "What can we do, to get out of here?",
                "Who is paying you?",
                4
            )
        )

        addDialog(
            "Who is paying you?", Language.EN, Dialog(
                "Cow: If I I tell you,",
                "I have tooo kill you.",
                null,
                "What can we do, to get out of here?",
                5
            )
        )

        addDialog(
            "What can we do, to get out of here?", Language.EN, Dialog(
                "Cow: Youuu're in luck, I'm in a very good moooood.",
                null,
                null,
                "So?",
                6
            )
        )

        addDialog(
            "Isn't there any other possibility?", Language.EN, Dialog(
                "Kuh: Youuu're in luck, I'm in a very good moooood.",
                null,
                null,
                "So?",
                6
            )
        )

        addDialog(
            "So?", Language.EN, Dialog(
                "Cow: Ge-get me Valium, my",
                "Cowbell and the Milksucker 2000. 'Click'",
                null,
                "Fine, if that's what it takes.",
                7
            )
        )

        addDialog(
            "What do I need again?", Language.EN, Dialog(
                "Cow: Ge-get me Valium, my",
                "Cowbell and the Milksucker 2000. 'Click'",
                null,
                "Fine, if that's what it takes.",
                7
            )
        )

        addDialog(
            "Fine, if that's what it takes.", Language.EN, Dialog(
                StateChange.YES
            )
        )

        addDialog(
            "Start3", Language.EN, Dialog(
                "Cow: What dooooo you want?",
                null,
                null,
                "We've collected all your stuff.",
                9
            )
        )

        addDialog(
            "We've collected all your stuff.", Language.EN, Dialog(
                "Cow: Very niiice. Then I'll open the",
                "dooooor for you straight awaaaay.",
                null,
                "Go on. We want to go out for ice cream!",
                8
            )
        )

        addDialog(
            "Go on. We want to go out for ice cream!", Language.EN, Dialog(
                "Cow: Do-Don't be cheeky nooooow. Don't you",
                "da-dare tell anyone that I'm letting you ooout!",
                null,
                "All right...",
                10
            )
        )

        addDialog(
            "All right...", Language.EN, Dialog(
                StateChange.YES
            )
        )
    }

    private fun addDroglDialog() {
        addDialog(
            "Start1", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!!",
                null,
                "Droggelbecher.",
                null,
                0
            )
        )

        addDialog(
            "Start2", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!!",
                null,
                null,
                "Droggelbecher Droggelbecher?",
                0
            )
        )

        addDialog(
            "Droggelbecher.", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                "Droggelbecher?",
                "Droggelbecher??",
                1
            )
        )

        addDialog(
            "Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                "Droggelbecher???",
                2
            )
        )

        addDialog(
            "Droggelbecher??", Language.DROGL, Dialog(
                "Droggelbecher: Drooooglbecher.",
                null,
                null,
                "Drogl becher?",
                3
            )
        )

        addDialog(
            "Drogl becher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!",
                null,
                "Droggelbecher???",
                null,
                8
            )
        )

        addDialog(
            "Droggelbecher???", Language.DROGL, Dialog(
                "Droggelbecher: Drooooglbecher.",
                null,
                null,
                "Droggelbecher Droggelbecher?",
                6
            )
        )

        addDialog(
            "Droggelbecher Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher. 'Click'",
                null,
                null,
                "Droggelbecher...",
                7
            )
        )

        addDialog(
            "Droggelbecher...", Language.DROGL, Dialog(
                StateChange.YES
            )
        )

        addDialog(
            "Start3", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher?",
                null,
                null,
                "Droggelbecher!!",
                4
            )
        )

        addDialog(
            "Droggelbecher!!", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                "Droggelbecher!!!",
                8
            )
        )

        addDialog(
            "Droggelbecher!!!", Language.DROGL, Dialog(
                StateChange.YES
            )
        )
    }
}
