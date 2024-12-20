package org.wanne.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

/**
 * Läd alle Assets im voraus
 */
class AssetsManager {

    private val assetManager: AssetManager = AssetManager()

    fun loadUI() {
        assetManager.load("ui/default/uiskin.json", Skin::class.java)

        assetManager.load("ui/cursor/Ansehen.png", Pixmap::class.java)
        assetManager.load("ui/cursor/Reden.png", Pixmap::class.java)
        assetManager.load("ui/cursor/Nehmen.png", Pixmap::class.java)
        assetManager.load("ui/cursor/Benutzen.png", Pixmap::class.java)
        assetManager.load("ui/cursor/kombinieren.png", Pixmap::class.java)
    }

    fun loadTextures() {
        assetManager.load("pictures/Menue/title3.png", Texture::class.java)
        assetManager.load("pictures/Menue/background.png", Texture::class.java)
        assetManager.load("pictures/Menue/ecke.png", Texture::class.java)
        assetManager.load("pictures/Menue/options.png", Texture::class.java)

        assetManager.load("pictures/Backgrounds/OutsideSingle.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/Outside.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/Outside169.png", Texture::class.java)

        assetManager.load("pictures/Backgrounds/puzzle.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/puzzleMultiplayer.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/puzzle169.png", Texture::class.java)

        assetManager.load("pictures/Backgrounds/KinderzimmerSingle.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/Kinderzimmer.png", Texture::class.java)
        assetManager.load("pictures/Backgrounds/Kinderzimmer169.png", Texture::class.java)
    }

    fun loadSprites() {
        assetManager.load("pictures/Buttons/buttons.atlas", TextureAtlas::class.java)

        assetManager.load("pictures/Items/items.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Items/inventory.atlas", TextureAtlas::class.java)

        // Duck
        assetManager.load("pictures/Players/Ente/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Ente/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Ente/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Ente/walkRight.atlas", TextureAtlas::class.java)

        // PoolAttendant
        assetManager.load("pictures/Players/Bademeister/scratchLeft.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Bademeister/scratchRight.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Bademeister/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Bademeister/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Bademeister/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Players/Bademeister/walkRight.atlas", TextureAtlas::class.java)

        assetManager.load("pictures/Puzzle/puzzle.atlas", TextureAtlas::class.java)

        assetManager.load("pictures/Items/iceman.atlas", TextureAtlas::class.java)

        // Menü Stuff
        assetManager.load("pictures/Menue/water.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Menue/water_above.atlas", TextureAtlas::class.java)
        assetManager.load("pictures/Menue/mainbuttons.atlas", TextureAtlas::class.java)
    }

    fun loadMusic() {
        assetManager.load("soundsOriginal/Background/Kinderzimmer.mp3", Music::class.java)
        // ToDo: Durch Gema freie Version ersetzen
        assetManager.load("soundsOriginal/Background/jeopardy.mp3", Music::class.java)
    }

    fun loadSounds() {
        // DrBear
        assetManager.load("soundsOriginal/Arztbaer/arztbaerLachen.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Arztbaer/aufschneiden.mp3", Sound::class.java)

        // Mario
        assetManager.load("soundsOriginal/Mario/lassmich.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/feuerverloren.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/bowserweg.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/gefuehlsstein.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/wiemanfeuerwieder.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/klassedanke.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/hierlandenalleleutedie.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/derwaechterhatsieversigelt.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/vielleichtversuchenzuentkommen.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/habseinetelefonnummer.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/abrungtiefeboesekreatur.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/dieselenerntet.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/riesiggroßmitfuerchterlichen.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/ichhabedichgewarnt.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/wirkennenunsnicht.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/warummaskeauf.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/langsamkeinelustmehr.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/fragmadeinenFreund.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/teppischfestgenagelt.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/wuerdedirhelfenaber.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/aberwehe2.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/angstindenaugensehn.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Mario/Mariotransform.mp3", Sound::class.java)

        // Teddy
        assetManager.load("soundsOriginal/Teddy/zitter.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Teddy/ahhhhh.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Teddy/habangst.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Teddy/habangstdassichdenwaechtersehe.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Teddy/achder.mp3", Sound::class.java)

        // Cow
        assetManager.load("soundsOriginal/Kuh/waechterhier.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/daskannichnichregeln.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/15jahre.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/vomAbflussInsZimmer.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/ichwerdedafuerbezahlt.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/mussdichtoeten.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/gutgelaunt.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/besorgsmir.mp3", Sound::class.java)
        assetManager.load("soundsOriginal/Kuh/bisgleich.mp3", Sound::class.java)
    }

    fun update(millis: Int): Boolean {
        return assetManager.update(millis)
    }

    fun progress(): Float {
        return assetManager.progress
    }

    fun <T>get(path: String): T {
        return assetManager.get(path)
    }
}
