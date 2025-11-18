package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.game.network.model.ActionWrapper

fun interface ExternalListener {

    fun externalClick(stage: Stage, action: ActionWrapper): Boolean

}
