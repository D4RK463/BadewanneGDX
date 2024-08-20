package org.wanne.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.wanne.game.sound.SoundManager
import org.wanne.screens.CowPhoneScreen
import org.wanne.screens.MenuScreen
import org.wanne.screens.OutsideScreen
import org.wanne.screens.PuzzleScreen
import org.wanne.screens.RoomScreen

class WanneGame : Game() {
    val roomScreen: RoomScreen by lazy {
        RoomScreen(this)
    }
    val puzzleScreen: PuzzleScreen by lazy {
        PuzzleScreen(this)
    }
    val cowPhoneScreen: CowPhoneScreen by lazy {
        CowPhoneScreen(this)
    }
    val outsideScreen: OutsideScreen by lazy {
        OutsideScreen(this)
    }
    val menuScreen: MenuScreen by lazy {
        MenuScreen(this)
    }

    lateinit var batch: SpriteBatch

    val soundManager = SoundManager()

    var isSingleplayer = true

    var puzzleSolved = false

    var talkedToCow = false

    var possessWinningObjects = false

    var cowIsBusy = false

    private var initialLoadingDone = false
    val am = AssetsManager()

    override fun create() {

        // Assets initialisieren
        am.loadMenuScreen()

        batch = SpriteBatch()
    }

    override fun render() {
        /*
        https://www.gamedevelopment.blog/asset-manager-libgdx-tutorial/

        if (assMan.manager.update()) { // Load some, will return true if done loading
            currentLoad+= 1;
            switch(currentLoad){
            case FONT:	assMan.loadFonts();
                loadingLabel.setText("Loading Fonts");
                break;
            case PARTY:	assMan.loadParticleEffects();
                loadingLabel.setText("Loading Particle Effects");
                break;
            case SOUND:	assMan.loadSounds();
                loadingLabel.setText("Loading Sounds");
                break;
            case MUSIC:	assMan.loadMusic();
                loadingLabel.setText("Loading Music");
                break;
            case 5:	assMan.loadMusic();
                loadingLabel.setText("Loading Fonts");
                break;
            }
            if (currentLoad >5){
                loadingLabel.setText("");
                percent = 1; // set bar to full
                parent.changeScreen(BlockBreaker.MENU); //changes screen

            }
        }else{
            percent = Interpolation.linear.apply(percent, assMan.manager.getProgress(), 0.05f);
        }

         */


        if (am.update(17)) {
            println("Loading complete")

            if (!initialLoadingDone) {
                setScreen(menuScreen)
                initialLoadingDone = true
            }
        }

        println(am.progress())

        super.render() // important!
    }

    override fun dispose() {
        batch.dispose()
    }

    fun createUIButton(
        texture: Sprite,
        texturePressed: Sprite,
        x: Float,
        y: Float,
    ): ImageButton {
        val style = ImageButtonStyle()
        style.imageUp = TextureRegionDrawable(texture)
        style.imageDown = TextureRegionDrawable(texturePressed)
        val button = ImageButton(style)
        button.x = x
        button.y = y

        return button
    }
}
