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
import org.wanne.game.sound.Speech
import org.wanne.game.sound.collections.COW
import org.wanne.game.sound.collections.CowSoundCollection
import org.wanne.game.sound.collections.DR_BEAR
import org.wanne.game.sound.collections.DrBearSoundCollection
import org.wanne.game.sound.collections.MARIO
import org.wanne.game.sound.collections.MarioSoundCollection
import org.wanne.game.sound.collections.TEDDY
import org.wanne.game.sound.collections.TeddySoundCollection
import org.wanne.game.sound.dir

const val PIXMAPS = "pixmaps"

const val SKINS = "skins"

const val TEXTURES = "textures"

const val SPRITES = "sprites"

const val ANIMATIONS = "$SPRITES/animations"

const val DIALOG = "dialog"

const val MUSIC = "music"

const val VIDEOS = "videos"

/**
 * Läd alle Assets im voraus
 */
class AssetsManager {

    private val assetManager: AssetManager = AssetManager()

    lateinit var introVideo: FileHandle
    lateinit var outroVideo: FileHandle

    fun loadUI() {
        assetManager.load("$SKINS/default/uiskin.json", Skin::class.java)

        assetManager.load("$PIXMAPS/Ansehen.png", Pixmap::class.java)
        assetManager.load("$PIXMAPS/Reden.png", Pixmap::class.java)
        assetManager.load("$PIXMAPS/Nehmen.png", Pixmap::class.java)
        assetManager.load("$PIXMAPS/Benutzen.png", Pixmap::class.java)
        assetManager.load("$PIXMAPS/kombinieren.png", Pixmap::class.java)
    }

    fun loadTextures() {
        assetManager.load("$TEXTURES/title3.png", Texture::class.java)
        assetManager.load("$TEXTURES/background.png", Texture::class.java)
        assetManager.load("$TEXTURES/ecke.png", Texture::class.java)
        assetManager.load("$TEXTURES/options.png", Texture::class.java)

        assetManager.load("$TEXTURES/OutsideSingle.png", Texture::class.java)
        assetManager.load("$TEXTURES/Outside.png", Texture::class.java)
        assetManager.load("$TEXTURES/Outside169.png", Texture::class.java)

        assetManager.load("$TEXTURES/puzzle.png", Texture::class.java)
        assetManager.load("$TEXTURES/puzzle169.png", Texture::class.java)

        assetManager.load("$TEXTURES/KinderzimmerSingle.png", Texture::class.java)
        assetManager.load("$TEXTURES/Kinderzimmer.png", Texture::class.java)
        assetManager.load("$TEXTURES/Kinderzimmer169.png", Texture::class.java)
    }

    fun loadSprites() {
        assetManager.load("$SPRITES/buttons.atlas", TextureAtlas::class.java)

        assetManager.load("$SPRITES/items.atlas", TextureAtlas::class.java)
        assetManager.load("$SPRITES/inventory.atlas", TextureAtlas::class.java)

        // Duck
        assetManager.load("$ANIMATIONS/duck/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/duck/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/duck/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/duck/walkRight.atlas", TextureAtlas::class.java)

        // PoolAttendant
        assetManager.load("$ANIMATIONS/poolattendent/scratchLeft.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/poolattendent/scratchRight.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/poolattendent/lookLeft.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/poolattendent/lookRight.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/poolattendent/walkLeft.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/poolattendent/walkRight.atlas", TextureAtlas::class.java)

        assetManager.load("$SPRITES/puzzle.atlas", TextureAtlas::class.java)

        assetManager.load("$ANIMATIONS/iceman.atlas", TextureAtlas::class.java)

        // Menü Stuff
        assetManager.load("$ANIMATIONS/water.atlas", TextureAtlas::class.java)
        assetManager.load("$ANIMATIONS/water_above.atlas", TextureAtlas::class.java)
        assetManager.load("$SPRITES/mainbuttons.atlas", TextureAtlas::class.java)
    }

    fun loadMusic() {
        assetManager.load("$MUSIC/background.mp3", Music::class.java)

        /**
         * https://pixabay.com
         *
         * music_for_audio - Please Calm My Mind
         * Lidérc - No Copyright Lofii Dreamscape CalmChillout_Long Music
         * BFCMUSIC - Upbeat & Optimistic Funky Groove
         */
        assetManager.load("$MUSIC/title_theme.mp3", Music::class.java)
        assetManager.load("$MUSIC/puzzle_theme.mp3", Music::class.java)
        assetManager.load("$MUSIC/funky_groove.mp3", Music::class.java)
    }

    fun loadSounds() {
        val activeDialogs = listOf(Speech.DE_ORIGINAL)

        activeDialogs.forEach {
            loadDialog(DrBearSoundCollection.DIALOG_END, it, DR_BEAR)
            loadDialog(CowSoundCollection.DIALOG_END, it, COW)
            loadDialog(MarioSoundCollection.DIALOG_END, it, MARIO)
            loadDialog(TeddySoundCollection.DIALOG_END, it, TEDDY)
        }
    }

    private fun loadDialog(dialogEnd: Int, speech: Speech, directory: String) {
        for (i in 0..dialogEnd) {
            assetManager.load("$DIALOG/${speech.dir()}/$directory/${i}.mp3", Sound::class.java)
        }
    }

    fun loadVideos() {
        introVideo = Gdx.files.internal("$VIDEOS/intro.webm")
        outroVideo = Gdx.files.internal("$VIDEOS/outro.webm")
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
