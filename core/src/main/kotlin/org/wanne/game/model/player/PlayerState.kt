package org.wanne.game.model.player

import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction

data class PlayerState(
    var moveToPoint: Point? = null,
    var needToMove: Boolean = false,
    var currentAction: PointAndClickAction = PointAndClickAction.createDefaultAction(),
    var lookingAtTheEnd: Player.Companion.Looking? = null,
    var doTheAction: () -> Unit = {},
    var player: Player
)
