package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class FireFlower(
    posX: Float = 695F,
    posY: Float = 375F,
    am: AssetsManager,
    private val gameObjectToManipulate: GameObject,
    private val game: WanneGame,
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    var solved = false

    override fun getSprite(time: Float): Sprite =
        if (solved) {
            if (isInInventory) {
                addPositionToSprite(inventoryAtlas.createSprite("FeuerblumeInv"))
            } else {
                addPositionToSprite(itemAtlas.createSprite("Feuerblume"))
            }
        } else {
            if (isInInventory) {
                addPositionToSprite(inventoryAtlas.createSprite("FeuerblumeKaputtInv"))
            } else {
                addPositionToSprite(itemAtlas.createSprite("FeuerblumeKaputt"))
            }
        }

    override fun getName(): String = "FireFlower"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Ganz schön heiß...aua!")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        // Wenn das Puzzle noch nicht gelöst wurde
        if (!game.puzzleSolved) {
            // Bildschirm ändern
            game.screen = game.puzzleScreen
        } else {
            dialogBoard.prepLookAt("Damit bin ich schon fertig.", "War gar nicht so einfach!")
        }

        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (solved && (gameObjectToManipulate is Mario) && !gameObjectToManipulate.talkForTheFirstTime) {
            doCombine(dialogBoard, action, this, "Mario")
        } else {
            super.combine(dialogBoard, action)
        }
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.removeGameObject(this)
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            650,
            266
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Feuerblume"
}
