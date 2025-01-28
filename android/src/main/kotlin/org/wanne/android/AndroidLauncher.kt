package org.wanne.android

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import org.wanne.game.WanneGame

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = AndroidApplicationConfiguration()
        config.useImmersiveMode = true
        config.useGL30 = true

        initialize(WanneGame(true), config)
    }
}
