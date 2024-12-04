package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class FireFlower(
    posX: Float = 695F,
    posY: Float = 375F,
    private val gameObjectToManipulate: GameObject,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
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
        dialogBoard.prepLookAt(choose("Ganz schön heiß...aua!", "Ahh! It's hot!"))
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
            dialogBoard.prepLookAt(
                choose("Damit bin ich schon fertig.", "I already finished it."),
                choose("War gar nicht so einfach!", "Wasn't easy!")
            )
        }

        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (solved && (gameObjectToManipulate is Mario) && gameObjectToManipulate.didntTalkForTheFirstTime()) {
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
            correctPositionX(650),
            correctPositionY(266)
        ), Player.Companion.Looking.RIGHT
    )

    override fun getToolTipDescription(): String = choose("Feuerblume", "Fireflower")
}
