package org.wanne.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import ktx.preferences.set
import org.wanne.game.sound.Language

class Config {

    private var prefs: Preferences = Gdx.app.getPreferences("prefs")

    var ipAddress: String = prefs.getString("ipAddress", "IP Adresse eingeben")

    var clientPort: Int = prefs.getInteger("clientPort", 32421)

    var serverPort: Int = prefs.getInteger("serverPort", 32421)

    var soundVolume: Float = prefs.getFloat("soundVolume", 1F)

    var musicVolume: Float = prefs.getFloat("musicVolume", 0.2F)

    var extrasUnlocked: Boolean = prefs.getBoolean("extrasUnlocked", false)

    var language: String = Language.DE_ORIGINAL.language

    fun saveSettings() {
        prefs["ipAddress"] = ipAddress
        prefs["clientPort"] = clientPort
        prefs["serverPort"] = serverPort
        prefs["soundVolume"] = soundVolume
        prefs["musicVolume"] = musicVolume
        prefs["extrasUnlocked"] = extrasUnlocked
        prefs["language"] = language

        prefs.flush()
    }

}
