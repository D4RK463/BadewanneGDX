package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import org.wanne.game.AssetsManager
import org.wanne.model.AbstractObject
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player
import java.util.*

abstract class GameObject(
    posX: Float,
    posY: Float,
    am: AssetsManager
) : AbstractObject(posX, posY, am) {
    val inventoryAtlas: TextureAtlas = am.get("pictures/Items/inventory.atlas")

    private val random = Random()

    private var stupidAnswers =
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

    abstract fun look(dialogBoard: DialogBoard)

    open fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
        action.reset()
    }

    open fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
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
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
        action.reset()
    }

    open fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
        action.reset()
    }

    override fun dispose() {
        itemAtlas.dispose()
    }

    abstract fun getInteractPosition(): Pair<Point, Player.Companion.Looking?>

    abstract fun getToolTipDescription(): String

    /**
     * Fügt dieses Object der Stage hinzu und erzeugt einen ToolTip dafür
     */
    fun addToStage(
        stage: Stage,
        skin: Skin,
    ) {
        this.addListener(TextTooltip(getToolTipDescription(), skin))
        stage.addActor(this)
    }
}
