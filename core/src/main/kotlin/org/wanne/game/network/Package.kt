package org.wanne.game.network

import org.wanne.game.VideoMode
import java.io.Serializable

class Package(
    var intent: Intent = Intent.HELLO,
    val clickData: SerializablePointAndClickAction,
    val selectedVideoMode: VideoMode
): Serializable {

}
