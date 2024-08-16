@file:JvmName("Lwjgl3Launcher")

package org.wanne.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import org.wanne.game.WanneGame

/** Launches the desktop (LWJGL3) application. */
fun main() {
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired()) {
        return
    } else {

        Lwjgl3Application(WanneGame(), Lwjgl3ApplicationConfiguration().apply {
            setTitle("Die Badewannen-Verschwörung GDX")
//        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
            setForegroundFPS(60)
            setResizable(false)

        })
    }


}
