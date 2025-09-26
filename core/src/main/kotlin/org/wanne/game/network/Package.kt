package org.wanne.game.network

import java.io.Serializable

class Package(
    var intent: Intent = Intent.HELLO
): Serializable {

}
