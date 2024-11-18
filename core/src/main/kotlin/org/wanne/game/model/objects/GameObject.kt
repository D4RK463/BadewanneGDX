package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import org.wanne.game.Language
import org.wanne.game.WanneGame
import org.wanne.game.model.AbstractObject
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player
import java.util.*

abstract class GameObject(
    posX: Float,
    posY: Float,
    game: WanneGame
) : AbstractObject(posX, posY, game) {
    val inventoryAtlas: TextureAtlas = am.get("pictures/Items/inventory.atlas")

    private val random = Random()

    private val stupidAnswersDE =
        listOf(
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
            "LANGWEILIG!",
        )

    private val stupidAnswersEN =
        listOf(
            "Huh?!?",
            "What the hell?",
            "I can't build there!",
            "That's not possible!",
            "Don't bother me!",
            "I don't understand!!!",
            "How is that supposed to work?",
            "w00t?",
            "I'm not stupid!",
            "Stop confusing me!",
            "CLEAR...",
            "OF COURSE...",
            "Think about it!",
            "Doesn't work!",
            "BORING!",
        )

    private lateinit var tooltip: TextTooltip

    abstract fun look(dialogBoard: DialogBoard)

    open fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(getAStupidAnswer())
        action.reset()
    }

    open fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(getAStupidAnswer())
        action.reset()
    }

    fun doCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
        gameObject: GameObject,
        checkGameObjectClassName: String,
    ) {
        if (action.setCombineObject(gameObject)) {
            val combineObject = action.getCombineObjectByType(checkGameObjectClassName)

            if (combineObject != null) {
                combineObject.afterCombine(dialogBoard, action)
                gameObject.afterCombine(dialogBoard, action)
            } else {
                action.reset()
            }
        }
    }

    /**
     * Diese Funktion wird ausgelöst, nachdem 2 GameObjects miteinander kombiniert wurden
     */
    open fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
    }

    open fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(getAStupidAnswer())
        action.reset()
    }

    open fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (isInInventory) {
            dialogBoard.prepLookAt("Das hab ich doch schon!")
        } else {
            dialogBoard.prepLookAt(getAStupidAnswer())
        }

        action.reset()
    }

    private fun getAStupidAnswer(): String {
        return when(game.currentLang().language) {
            Language.DE -> stupidAnswersDE[random.nextInt(stupidAnswersDE.size - 1)]
            Language.EN -> stupidAnswersEN[random.nextInt(stupidAnswersEN.size - 1)]
            Language.DROGL -> randomizeDroglbecher()
        }
    }

    override fun dispose() {
        itemAtlas.dispose()
    }

    abstract fun getInteractPosition(): Pair<Point, Player.Companion.Looking?>

    abstract fun getToolTipDescription(): String

    /**
     * Fügt dieses Object der Stage hinzu und erzeugt einen ToolTip in der passenden Sprache dafür
     */
    fun addToStage(
        stage: Stage,
        skin: Skin,
    ) {
        // alten Tooltip löschen, wichtig für den Sprachwechsel
        try {
            this.removeListener(tooltip)
        } catch (_: Exception) {
        }

        tooltip = TextTooltip(getToolTipDescription(), skin)
        this.addListener(tooltip)
        stage.addActor(this)
    }

    /**
     * Entscheidet welches der richtige String ist, anhand der aktuell
     * eingestellten Sprache
     */
    fun choose(german: String, english: String): String {
        return when(game.currentLang().language) {
            Language.DE -> german
            Language.EN -> english
            Language.DROGL -> randomizeDroglbecher()
        }
    }

    private fun randomizeDroglbecher(): String {
        val sign: String = when(random.nextInt(5)) {
            0 -> ""
            1 -> "!"
            2 -> "?"
            3 -> "!?"
            4 -> "!!"
            else -> ""
        }
        return "Droglbecher$sign"
    }
}
