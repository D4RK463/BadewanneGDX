package org.wanne.game.dialog.collections

import org.wanne.game.AssetsManager
import org.wanne.game.Language
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.DialogCollection
import org.wanne.game.dialog.StateChange
import org.wanne.game.sound.collections.MarioSoundCollection

class MarioDialogCollection(am: AssetsManager) : DialogCollection(am, MarioSoundCollection(am)) {

    init {
        getGermanDialog()
        getEnglishDialog()
        getDroglDialog()
    }

    private fun getGermanDialog() {
        addDialog(
            "Start1", Language.DE, Dialog(
                "Rario: Lass mich, ich bin gerad betrübt.",
                null,
                "Was'n los ?",
                null,
                0
            )
        )

        addDialog(
            "Start2", Language.DE, Dialog(
                "Rario: Weisst du vielleicht wie man ",
                "das Feuer wieder entfachen kann?",
                null,
                null,
                4
            )
        )

        addDialog(
            "Was'n los ?", Language.DE, Dialog(
                "Rario: Die Prinzessin hat mich verlassen,",
                "weil ich mein 'Feuer' verloren hab.",
                "Erzähl mir mehr.",
                "Mir doch egal",
                1
            )
        )

        addDialog(
            "Mir doch egal", Language.DE, Dialog(
                "Rario: Seit dem der fiese Rowser weg ist,",
                "ist die Action aus der Beziehung raus.",
                null,
                "*laber* ...",
                2
            )
        )

        addDialog(
            "Erzähl mir mehr.", Language.DE, Dialog(
                "Rario: Seit dem der fiese Rowser weg ist,",
                "ist die Action aus der Beziehung raus.",
                null,
                "*laber* ...",
                2
            )
        )

        addDialog(
            "*laber* ...", Language.DE, Dialog(
                "Rario: Sie sagt ich bin ein 'Gefühlsstein'.",
                "Dabei mag ich Steine nichtmal :(",
                null,
                "bla, bla, bla...",
                3
            )
        )

        addDialog(
            "bla, bla, bla...", Language.DE, Dialog(
                "Rario: Weisst du vielleicht wie man ",
                "das Feuer wieder entfachen kann?",
                null,
                null,
                4,
                StateChange.YES
            )
        )

        addDialog(
            "Wie sind wir hier her gekommen?", Language.DE, Dialog(
                "Rario: Hier landen alle Leute die ",
                "sich im Ausguss verirren.",
                "Warum ist die Tür verschlossen?",
                "Und was mach ich nun?",
                6
            )
        )

        addDialog(
            "Warum ist die Tür verschlossen?", Language.DE, Dialog(
                "Rario: Der Wächter hat sie versiegelt.",
                null,
                "Wo ist der Wächter?",
                "Wer ist der Wächter?",
                7
            )
        )

        addDialog(
            "Geht net, die Tür ist zu.", Language.DE, Dialog(
                "Rario: Der Wächter hat sie versiegelt.",
                null,
                "Wo ist der Wächter?",
                "Wer ist der Wächter?",
                7
            )
        )

        addDialog(
            "Und was mach ich nun?", Language.DE, Dialog(
                "Rario: Vielleicht solltest du versuchen ",
                "aus dem Raum zu entkommen?",
                null,
                "Geht net, die Tür ist zu.",
                8
            )
        )

        addDialog(
            "Wo ist der Wächter?", Language.DE, Dialog(
                "Rario: Ich hab keine Ahnung wo er ist.",
                "Aber ich hab seine Telefonnummer.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Wo kann ich ihn finden?", Language.DE, Dialog(
                "Rario: Ich hab keine Ahnung wo er ist.",
                "Aber ich hab seine Telefonnummer.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Wo ist nochma der Wächter?", Language.DE, Dialog(
                "Rario: Ich hab keine Ahnung wo er ist.",
                "Aber ich hab seine Telefonnummer.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Wer ist der Wächter?", Language.DE, Dialog(
                "Rario: Das ist eine abgrundtief böse Kreatur.",
                "Es gibt niemand der sie gesehen hat und noch lebt.",
                "Klar doch!",
                "Weiter...",
                10
            )
        )

        addDialog(
            "Wer ist nochma der Wächter?", Language.DE, Dialog(
                "Rario: Das ist eine abgrundtief böse Kreatur.",
                "Es gibt niemand der sie gesehen hat und noch lebt.",
                "Klar doch!",
                "Weiter...",
                10
            )
        )

        addDialog(
            "Weiter...", Language.DE, Dialog(
                "Rario: Es wird gemunkelt, das der Wächter die",
                "Seelen derer erntet die sich im Ausguss verirren.",
                "Übertrieben!",
                "WOW!!!",
                11
            )
        )

        addDialog(
            "WOW!!!", Language.DE, Dialog(
                "Rario: Er soll riesig gross sein mit ",
                "fürchterlichen Klauen und Eiter triefendem Maul.",
                "Wo kann ich ihn finden?",
                "Du laberst doch nur!",
                12
            )
        )

        addDialog(
            "Du laberst doch nur!", Language.DE, Dialog(
                "Rario: Wenn du meinst, aber ich hab dich gewarnt.",
                null,
                null,
                "Wo kann ich ihn finden?",
                13
            )
        )

        addDialog(
            "Übertrieben!", Language.DE, Dialog(
                "Rario: Wenn du meinst, aber ich hab dich gewarnt.",
                null,
                null,
                "Wo kann ich ihn finden?",
                13
            )
        )

        addDialog(
            "Klar doch!", Language.DE, Dialog(
                "Rario: Wenn du meinst, aber ich hab dich gewarnt.",
                null,
                null,
                "Wo kann ich ihn finden?",
                13
            )
        )

        addDialog(
            "Start3", Language.DE, Dialog(
                "Rario: Jawohl! Damit kann ich ",
                "die Prinzessin bestimmt wieder zurückgewinnen.",
                "Wie sind wir hier her gekommen?",
                "Warum ist die Tür verschlossen?",
                5
            )
        )

        addDialog(
            "Start4", Language.DE, Dialog(
                "Ich hab da was nicht mitbekommen.",
                null,
                "Wer ist nochma der Wächter?",
                "Wo ist nochma der Wächter?",
                null,
                StateChange.YES
            )
        )

        addDialog(
            "Start5", Language.DE, Dialog(
                "Rario: Ich kann die Angst in deinen Augen sehen. Sei ",
                "froh das man durchs Telefon nichts riechen kann!",
                null,
                null,
                21
            )
        )

        addDialog(
            "Warum liegt hier eigentlich Stroh?", Language.DE, Dialog(
                "Rario: Warum hast du ne Maske auf?",
                null,
                null,
                null,
                15
            )
        )

        addDialog(
            "Warum lässt sich der Teppich nicht bewegen?", Language.DE, Dialog(
                "Rario: Langsam hab ich keine Lust mehr dir ",
                "zu helfen. Wir sind schon quitt.",
                null,
                "Bitte, bitte...",
                16
            )
        )

        addDialog(
            "Was riecht hier so komisch?", Language.DE, Dialog(
                "Rario: Frag ma deinen dicken Freund da drüben. XD",
                null,
                "Warum liegt hier eigentlich Stroh?",
                "Warum lässt sich der Teppich nicht bewegen?",
                17
            )
        )

        addDialog(
            "Bitte, bitte...", Language.DE, Dialog(
                "Rario: Na gut, es wird erzählt das der Wächter ",
                "den Teppich festgenagelt hat.",
                null,
                "Kannst du mir helfen den Teppich loszuwerden?",
                18
            )
        )

        addDialog(
            "Kannst du mir helfen den Teppich loszuwerden?", Language.DE, Dialog(
                "Rario: Ich würde dir ja helfen aber ich will nicht.",
                null,
                "Du #*%&!!! Ich hasse dich!!",
                "*schnief* Bitte, bitte ich tu auch alles für dich!",
                19
            )
        )

        addDialog(
            "Du #*%&!!! Ich hasse dich!!", Language.DE, Dialog(
                "Rario: Na gut, na gut, aber wehe du erzählst es ",
                "den Anderen. Mehr werde ich nicht helfen!",
                null,
                null,
                20,
                StateChange.YES
            )
        )

        addDialog(
            "*schnief* Bitte, bitte ich tu auch alles für dich!", Language.DE, Dialog(
                "Rario: Na gut, na gut, aber wehe du erzählst es ",
                "den Anderen. Mehr werde ich nicht helfen!",
                null,
                null,
                20,
                StateChange.YES
            )
        )

        addDialog(
            "Start6", Language.DE, Dialog(
                "Rario: Das reicht jetzt! Wir kennen uns nicht!",
                null,
                null,
                null,
                14
            )
        )

        addDialog(
            "Start7", Language.DE, Dialog(
                "Hi Mario!!",
                null,
                "Warum lässt sich der Teppich nicht bewegen?",
                "Was riecht hier so komisch?",
                null
            )
        )
    }

    private fun getEnglishDialog() {
        addDialog(
            "Start1", Language.EN, Dialog(
                "Rario: Leave me alone, I'm sad.",
                null,
                "What's going on?",
                null,
                0
            )
        )

        addDialog(
            "Start2", Language.EN, Dialog(
                "Rario: Do you know how to rekindle the fire?",
                null,
                null,
                null,
                4
            )
        )

        addDialog(
            "What's going on?", Language.EN, Dialog(
                "Rario: The princess left me because",
                "I lost my ‘fire’.",
                "Tell me more.",
                "I don't care.",
                1
            )
        )

        addDialog(
            "I don't care.", Language.EN, Dialog(
                "Rario: Ever since that Rowser left,",
                "the spark has gone out of the relationship.",
                null,
                "*yadda yadda* ...",
                2
            )
        )

        addDialog(
            "Tell me more.", Language.EN, Dialog(
                "Rario: Ever since that Rowser left,",
                "the spark has gone out of the relationship.",
                null,
                "*yadda yadda* ...",
                2
            )
        )

        addDialog(
            "*yadda yadda* ...", Language.EN, Dialog(
                "Rario: She says I am 'stone-hearted'.",
                "But I don't even like stones :(",
                null,
                "bla, bla, bla...",
                3
            )
        )

        addDialog(
            "bla, bla, bla...", Language.EN, Dialog(
                "Rario: Do you know how to rekindle the fire?",
                null,
                null,
                null,
                4,
                StateChange.YES
            )
        )

        addDialog(
            "How did we get here?", Language.EN, Dialog(
                "Rario: This is where all the people",
                " who get lost in the sink end up.",
                "Why is the door locked?",
                "And what do I do now?",
                6
            )
        )

        addDialog(
            "Why is the door locked?", Language.EN, Dialog(
                "Rario: The guardian has sealed it.",
                null,
                "Where is the guardian?",
                "Who is the guardian?",
                7
            )
        )

        addDialog(
            "Can't, the door is locked.", Language.EN, Dialog(
                "Rario: The guardian has sealed it.",
                null,
                "Where is the guardian?",
                "Who is the guardian?",
                7
            )
        )

        addDialog(
            "And what do I do now?", Language.EN, Dialog(
                "Rario: Maybe you should try to escape",
                "from this room?",
                null,
                "Can't, the door is locked.",
                8
            )
        )

        addDialog(
            "Where is the guardian?", Language.EN, Dialog(
                "Rario: I don't know where he is.",
                "But I have his number.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Where can I find him?", Language.EN, Dialog(
                "Rario: I don't know where he is.",
                "But I have his number.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Where is the guardian again?", Language.EN, Dialog(
                "Rario: I don't know where he is.",
                "But I have his number.",
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Who is the guardian?", Language.EN, Dialog(
                "Rario: This is an abysmally evil creature.",
                "There is no one who has seen him and is still alive.",
                "Sure thing!",
                "more...",
                10
            )
        )

        addDialog(
            "Who is the guardian again?", Language.EN, Dialog(
                "Rario: This is an abysmally evil creature.",
                "There is no one who has seen him and is still alive.",
                "Sure thing!",
                "more...",
                10
            )
        )

        addDialog(
            "more...", Language.EN, Dialog(
                "Rario: It is rumoured that the guardian harvests",
                "the souls of those who stray into the sink.",
                "Excessive!",
                "WOW!!!",
                11
            )
        )

        addDialog(
            "WOW!!!", Language.EN, Dialog(
                "Rario: It is said to be huge with",
                "terrible claws and a mouth dripping with pus.",
                "Where can I find him?",
                "You're just babbling!",
                12
            )
        )

        addDialog(
            "You're just babbling!", Language.EN, Dialog(
                "Rario: If you think so, but I warned you.",
                null,
                null,
                "Where can I find him?",
                13
            )
        )

        addDialog(
            "Excessive!", Language.EN, Dialog(
                "Rario: If you think so, but I warned you.",
                null,
                null,
                "Where can I find him?",
                13
            )
        )

        addDialog(
            "Sure thing!", Language.EN, Dialog(
                "Rario: If you think so, but I warned you.",
                null,
                null,
                "Where can I find him?",
                13
            )
        )

        addDialog(
            "Start3", Language.EN, Dialog(
                "Rario: Jawohl!",
                "I'm sure I can win the princess back with this.",
                "How did we get here?",
                "Why is the door locked?",
                5
            )
        )

        addDialog(
            "Start4", Language.EN, Dialog(
                "I missed something. Ahhh..",
                null,
                "Who is the guardian again?",
                "Where is the guardian again?",
                null,
                StateChange.YES
            )
        )

        addDialog(
            "Start5", Language.EN, Dialog(
                "Rario: I can see the fear in your eyes. Just be",
                "glad that you can't smell anything through the phone!",
                null,
                null,
                21
            )
        )

        addDialog(
            "Why is there straw here?", Language.EN, Dialog(
                "Rario: Why are you wearing a mask?",
                null,
                null,
                null,
                15
            )
        )

        addDialog(
            "Why can't the carpet be moved?", Language.EN, Dialog(
                "Rario: I'm getting tired of helping you.",
                "We're even already.",
                null,
                "Pretty please...",
                16
            )
        )

        addDialog(
            "What's that funny smell?", Language.EN, Dialog(
                "Rario: Just ask your fat friend over there. XD",
                null,
                "Why is there straw here?",
                "Why can't the carpet be moved?",
                17
            )
        )

        addDialog(
            "Pretty please...", Language.EN, Dialog(
                "Rario: All right, the story goes that the",
                "guardian nailed the carpet down.",
                null,
                "Can you help me, to get rid of the carpet?",
                18
            )
        )

        addDialog(
            "Can you help me, to get rid of the carpet?", Language.EN, Dialog(
                "Rario: I would help you, but I don't want to.",
                null,
                "You #*%&!!! I hate you!!",
                "*sniff* Please, please I'll do everything for you!",
                19
            )
        )

        addDialog(
            "You #*%&!!! I hate you!!", Language.EN, Dialog(
                "Rario: All right, all right. Don't you dare tell",
                "the others. That's all I'm going to help with!",
                null,
                null,
                20,
                StateChange.YES
            )
        )

        addDialog(
            "*sniff* Please, please I'll do everything for you!", Language.EN, Dialog(
                "Rario: All right, all right. Don't you dare tell",
                "the others. That's all I'm going to help with!",
                null,
                null,
                20,
                StateChange.YES
            )
        )

        addDialog(
            "Start6", Language.EN, Dialog(
                "Rario: That's enough now! I don't know you!",
                null,
                null,
                null,
                14
            )
        )

        addDialog(
            "Start7", Language.EN, Dialog(
                "Hi Mario!!",
                null,
                "Why can't the carpet be moved?",
                "What's that funny smell?",
                null
            )
        )
    }

    private fun getDroglDialog() {
        addDialog(
            "Start1", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                "Droggelbecher?",
                null,
                0
            )
        )

        addDialog(
            "Start2", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher?",
                null,
                null,
                null,
                4
            )
        )

        addDialog(
            "Droggelbecher?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher,",
                "Droggelbecher.",
                "Droggelbecher.",
                null,
                1
            )
        )

        addDialog(
            "Droggelbecher.", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                "*drogl* ...",
                2
            )
        )

        addDialog(
            "*drogl* ...", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher. :(",
                null,
                null,
                "Droggelbecher...",
                3
            )
        )

        addDialog(
            "Droggelbecher...", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher?",
                null,
                null,
                null,
                4,
                StateChange.YES
            )
        )

        addDialog(
            "Droggelbecher??", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher ",
                "Droggelbecher.",
                "Droggelbecher???",
                null,
                6
            )
        )

        addDialog(
            "Droggelbecher???", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                "Droggelbecher!?",
                null,
                7
            )
        )

        addDialog(
            "Droggelbecher!?", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher!!",
                null,
                null,
                null,
                9,
                StateChange.YES
            )
        )

        addDialog(
            "Start3", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher :) ",
                null,
                "Droggelbecher??",
                "Droggelbecher???",
                5
            )
        )

        addDialog(
            "Start4", Language.DROGL, Dialog(
                "Droggelbecher?",
                null,
                "Droggelbecher??",
                "Droggelbecher???",
                null,
                StateChange.YES
            )
        )

        addDialog(
            "Start5", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                null,
                21
            )
        )

        addDialog(
            "Drogl becher??", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher...! ",
                null,
                null,
                "Droggelbecher, Droggelbecher...",
                16
            )
        )

        addDialog(
            "Droggelbecher, Droggelbecher...", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher.",
                null,
                null,
                "Droggelbecher!??",
                18
            )
        )

        addDialog(
            "Droggelbecher!??", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher...",
                null,
                "Droggelbecher!!!!",
                null,
                19
            )
        )

        addDialog(
            "Droggelbecher!!!!", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher,",
                "Droggelbecher!",
                null,
                null,
                20,
                StateChange.YES
            )
        )

        addDialog(
            "Start6", Language.DROGL, Dialog(
                "Droggelbecher: Droggelbecher! Droggelbecher!",
                null,
                null,
                null,
                14
            )
        )

        addDialog(
            "Start7", Language.DROGL, Dialog(
                "Droggelbecher!!",
                null,
                "Drogl becher??",
                null,
                null
            )
        )
    }

}
