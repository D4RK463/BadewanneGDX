package org.wanne.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

/**
 * Läd alle Assets im voraus
 */
class AssetsManager {

    private val assetManager: AssetManager = AssetManager()

    lateinit var introVideo: FileHandle
    lateinit var outroVideo: FileHandle

    fun loadUI() {
        assetManager.load("skins/default/uiskin.json", Skin::class.java)

        assetManager.load("pixmaps/Ansehen.png", Pixmap::class.java)
        assetManager.load("pixmaps/Reden.png", Pixmap::class.java)
        assetManager.load("pixmaps/Nehmen.png", Pixmap::class.java)
        assetManager.load("pixmaps/Benutzen.png", Pixmap::class.java)
        assetManager.load("pixmaps/kombinieren.png", Pixmap::class.java)
    }

    fun loadTextures() {
        assetManager.load("textures/title3.png", Texture::class.java)
        assetManager.load("textures/background.png", Texture::class.java)
        assetManager.load("textures/ecke.png", Texture::class.java)
        assetManager.load("textures/options.png", Texture::class.java)

        assetManager.load("textures/OutsideSingle.png", Texture::class.java)
        assetManager.load("textures/Outside.png", Texture::class.java)
        assetManager.load("textures/Outside169.png", Texture::class.java)

        assetManager.load("textures/puzzle.png", Texture::class.java)
        assetManager.load("textures/puzzle169.png", Texture::class.java)

        assetManager.load("textures/KinderzimmerSingle.png", Texture::class.java)
        assetManager.load("textures/Kinderzimmer.png", Texture::class.java)
        assetManager.load("textures/Kinderzimmer169.png", Texture::class.java)
    }

    fun loadSprites() {
        assetManager.load("sprites/buttons.atlas", TextureAtlas::class.java)

        assetManager.load("sprites/items.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/inventory.atlas", TextureAtlas::class.java)

        // Duck
        assetManager.load("sprites/animations/duck/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/duck/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/duck/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/duck/walkRight.atlas", TextureAtlas::class.java)

        // PoolAttendant
        assetManager.load("sprites/animations/poolattendent/scratchLeft.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/poolattendent/scratchRight.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/poolattendent/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/poolattendent/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/poolattendent/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/poolattendent/walkRight.atlas", TextureAtlas::class.java)

        assetManager.load("sprites/puzzle.atlas", TextureAtlas::class.java)

        assetManager.load("sprites/animations/iceman.atlas", TextureAtlas::class.java)

        // Menü Stuff
        assetManager.load("sprites/animations/water.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/animations/water_above.atlas", TextureAtlas::class.java)
        assetManager.load("sprites/mainbuttons.atlas", TextureAtlas::class.java)
    }

    fun loadMusic() {
        assetManager.load("music/background.mp3", Music::class.java)

        /**
         * https://pixabay.com
         *
         * music_for_audio - Please Calm My Mind
         * Lidérc - No Copyright Lofii Dreamscape CalmChillout_Long Music
         * BFCMUSIC - Upbeat & Optimistic Funky Groove
         */
        assetManager.load("music/title_theme.mp3", Music::class.java)
        assetManager.load("music/puzzle_theme.mp3", Music::class.java)
        assetManager.load("music/funky_groove.mp3", Music::class.java)
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

    fun loadVideos() {
        introVideo = Gdx.files.internal("video/intro.webm")
        outroVideo = Gdx.files.internal("video/outro.webm")
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
