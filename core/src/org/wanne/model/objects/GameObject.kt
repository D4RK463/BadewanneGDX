package org.wanne.model.objects

import org.wanne.model.AbstractObject
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player
import java.util.*

abstract class GameObject (posX: Float, posY: Float): AbstractObject(posX, posY) {

    private val random = Random()

    var stupidAnswers = listOf(
        "Hääh?!?",
        "Was zum Teufel?",
        "Ich kann da nicht bauen!!",
        "Das geht so nicht!",
        "Belästige mich nicht!",
        "Versteh ich nich!!",
        "Wie solln das gehn?",
        "w00t?",
        "Bin doch net blöd!",
        "Hör auf mich zu verwirren!",
        "KLAR...",
        "NATÜRLICH...",
        "Denk doch ma nach!",
        "Funst net!",
        "LANGWEILIG!"
    )

    abstract fun look(dialogBoard: DialogBoard)

    open fun use(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    open fun combine(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    open fun talk(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    open fun take(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    abstract fun getInteractPosition() : Pair<Point,Player.Companion.Looking?>

}