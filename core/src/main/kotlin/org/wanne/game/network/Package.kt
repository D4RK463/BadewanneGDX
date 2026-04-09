package org.wanne.game.network

import org.wanne.game.VideoMode
import org.wanne.game.network.model.SerializableAction
import java.io.Serializable

class Package(
    var intent: Intent = Intent.HELLO,
    val clickData: SerializableAction? = null,
    val selectedVideoMode: VideoMode
): Serializable {

}
