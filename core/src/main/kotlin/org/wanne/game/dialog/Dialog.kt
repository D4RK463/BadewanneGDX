package org.wanne.game.dialog

class Dialog( val talkToSentence: String,
              val talkToSentence2: String?,
              val answer1: String?,
              val answer2: String?,
              val soundIndex: Int?,
              val stateChange: Boolean = false
    ) {
    override fun toString(): String {
        return "Dialog(talkToSentence='$talkToSentence', talkToSentence2=$talkToSentence2, answer1=$answer1, answer2=$answer2, soundIndex=$soundIndex, stateChange=$stateChange)"
    }
}
