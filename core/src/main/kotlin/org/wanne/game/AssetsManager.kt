package org.wanne.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class AssetsManager {

    val assetManager: AssetManager = AssetManager()

    fun loadMenuScreen() {
        assetManager.load("ui/uiskin.json", Skin::class.java)

        assetManager.load("pictures/Buttons/buttons.atlas", TextureAtlas::class.java)

        assetManager.load("pictures/Menue/header.png", Texture::class.java)
        assetManager.load("pictures/Menue/ente.png", Texture::class.java)
        assetManager.load("pictures/Menue/bademeister.png", Texture::class.java)

    }

    fun loadTextures() {
        assetManager.load("pictures/Buttons/buttons.atlas", TextureAtlas::class.java)

        assetManager.load("pictures/Menue/header.png", Texture::class.java)
        assetManager.load("pictures/Menue/ente.png", Texture::class.java)
        assetManager.load("pictures/Menue/bademeister.png", Texture::class.java)
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
