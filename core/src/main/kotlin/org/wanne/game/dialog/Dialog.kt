package org.wanne.game.dialog

class Dialog(
    val talkToSentence: String,
    val talkToSentence2: String?,
    val answer1: String?,
    val answer2: String?,
    val soundIndex: Int?,
    val stateChange: StateChange = StateChange.NO
) {
    constructor(stateChange: StateChange) : this("", null, null, null, null, stateChange)

    override fun toString(): String {
        return "Dialog(talkToSentence='$talkToSentence', talkToSentence2=$talkToSentence2, answer1=$answer1, answer2=$answer2, soundIndex=$soundIndex, stateChange=$stateChange)"
    }

    fun isFilled(): Boolean {
        return talkToSentence != ""
    }

    fun isStateChanged(): Boolean {
        return stateChange != StateChange.NO
    }
}

enum class StateChange {

    /**
     * Allgemeiner Statuswechsel
     */
    YES,

    /**
     * Statuswechsel linker Weg
     */
    LEFT,

    /**
     * Statuswechsel rechter Weg
     */
    RIGHT,

    /**
     * Kein Statuswechsel
     */
    NO
}
