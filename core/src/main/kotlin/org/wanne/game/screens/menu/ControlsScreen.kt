package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.TEXTURES
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.screens.util.UiButtonBuilder

class ControlsScreen(game: WanneGame): AbstractMenuScreen(game) {

    var background: Texture = game.am["$TEXTURES/tutorial_classic.png"]

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        stage.addActor(Image(background))
        buildMenu()
    }

    fun buildMenu() {
        val controlsSprite = game.choose("steuerung", "controls", false)
        val controlTitle = Image(mainButtonAtlas.createSprite(controlsSprite))
        controlTitle.x = 30f
        controlTitle.y = 620f

        val inventoryString = game.choose("Inventar", "Inventory", false)
        val inventoryPair = createLabelWithShadow(inventoryString, 950f, 40f)
        val inventory = inventoryPair.first
        val inventoryShadow = inventoryPair.second

        val actionbarString = game.choose("Aktionsleiste", "Actionbar", false)
        val actionbarPair = createLabelWithShadow(actionbarString, 500f, 40f)
        val actionbar = actionbarPair.first
        val actionbarShadow = actionbarPair.second

        val changePlayerString = game.choose("Spieler wechseln", "Change player", false)
        val changePlayerPair = createLabelWithShadow(changePlayerString, 560f, 655f)
        val changePlayer = changePlayerPair.first
        val changePlayerShadow = changePlayerPair.second

        val backSprite = game.choose("zuruck", "back", false)
        val backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(30F, 30F))
            .build()
        backButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.mainMenuScreen
                    game.config.saveSettings()
                    dispose()
                }
            },
        )

        stage.addActor(inventoryShadow)
        stage.addActor(actionbarShadow)
        stage.addActor(changePlayerShadow)
        stage.addActor(inventory)
        stage.addActor(actionbar)
        stage.addActor(changePlayer)
        stage.addActor(backButton)
        stage.addActor(controlTitle)
    }

    override fun dispose() {
        stage.dispose()
    }

}
