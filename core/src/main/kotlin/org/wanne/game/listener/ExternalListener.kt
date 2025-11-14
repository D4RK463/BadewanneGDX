package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.game.network.SerializablePointAndClickAction

fun interface ExternalListener {

    fun externalClick(stage: Stage, action: SerializablePointAndClickAction): Boolean

}
