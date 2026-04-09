package org.wanne.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import ktx.preferences.set
import org.wanne.game.sound.Speech

class Config(android: Boolean) {

    private var prefs: Preferences = Gdx.app.getPreferences("prefs")

    var ipAddress: String = prefs.getString("ipAddress", "IP Adresse eingeben")

    var clientPort: Int = prefs.getInteger("clientPort", 32421)

    var serverPort: Int = prefs.getInteger("serverPort", 32421)

    var soundVolume: Float = prefs.getFloat("soundVolume", 1F)

    var musicVolume: Float = prefs.getFloat("musicVolume", 0.2F)

    var extrasUnlocked: Boolean = prefs.getBoolean("extrasUnlocked", false)

    var drooglUnlocked: Boolean = prefs.getBoolean("drooglUnlocked", false)
    var orgUnlocked: Boolean = prefs.getBoolean("orglUnlocked", false)
    var numberOfPlaythroughs: Int = prefs.getInteger("numberOfPlaythroughs", 0)

    var speech: String = Speech.DE_NEU.speech

    var mode: String = if (android) {VideoMode.MODERN.toString()} else {VideoMode.CLASSIC.toString()}

    fun saveSettings() {
        prefs["ipAddress"] = ipAddress
        prefs["clientPort"] = clientPort
        prefs["serverPort"] = serverPort
        prefs["soundVolume"] = soundVolume
        prefs["musicVolume"] = musicVolume
        prefs["extrasUnlocked"] = extrasUnlocked
        prefs["drooglUnlocked"] = drooglUnlocked
        prefs["orglUnlocked"] = orgUnlocked
        prefs["numberOfPlaythroughs"] = numberOfPlaythroughs
        prefs["language"] = speech
        prefs["mode"] = mode

        prefs.flush()
    }

    fun getResolutionX() : Int {
        return when(mode) {
            VideoMode.CLASSIC.toString() -> 1024
            VideoMode.MODERN.toString() -> 1280
            else -> 1024
        }
    }

    fun getResolutionY() : Int {
        return when(mode) {
            VideoMode.CLASSIC.toString() -> 768
            VideoMode.MODERN.toString() -> 720
            else -> 768
        }
    }

}
