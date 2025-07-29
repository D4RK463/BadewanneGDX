package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.SPRITES
import org.wanne.game.TEXTURES
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.screens.util.UiButtonBuilder

class ControlsScreen(game: WanneGame): AbstractMenuScreen(game) {

    private val buttonAtlas: TextureAtlas = game.am["$SPRITES/buttons.atlas"]

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        val background: Texture = if(game.classicMode())
            game.am["$TEXTURES/tutorial_classic.png"]
        else
            game.am["$TEXTURES/tutorial_wide.png"]
        stage.addActor(Image(background))
        buildMenu()
    }

    fun buildMenu() {
        val controlsSprite = game.choose("steuerung", "controls", false)
        val controlTitle = Image(mainButtonAtlas.createSprite(controlsSprite))
        val controlPoint = game.choose(Point(30f, 630f), Point(950f, 630f))
        controlTitle.x = controlPoint.x
        controlTitle.y = controlPoint.y

        val inventoryString = game.choose("Inventar", "Inventory", false)
        val inventoryPoint = game.choose(Point(950f, 126f), Point(30f, 440f))
        val inventoryPair = createLabelWithShadow(inventoryString, inventoryPoint.x, inventoryPoint.y, 1F, Color.ORANGE)
        val inventory = inventoryPair.first
        val inventoryShadow = inventoryPair.second

        val actionbarString = game.choose("Aktionsleiste", "Actionbar", false)
        val actionbarPoint = game.choose(Point(520f, 160f), Point(50f, 580f))
        val actionbarPair = createLabelWithShadow(actionbarString, actionbarPoint.x, actionbarPoint.y, 1F, Color.ORANGE)
        val actionbar = actionbarPair.first
        val actionbarShadow = actionbarPair.second

        val changePlayerString = game.choose("Figur wechseln", "Change player", false)
        val changePlayerPoint = game.choose(Point(560f, 655f), Point(60f, 660f))
        val changePlayerPair = createLabelWithShadow(changePlayerString, changePlayerPoint.x, changePlayerPoint.y, 1F, Color.ORANGE)
        val changePlayer = changePlayerPair.first
        val changePlayerShadow = changePlayerPair.second

        val action = if (game.android) { "tab" } else { game.choose("klick", "click", false) }
        val control1String = game.choose("${action} an einen Ort um", "${action} on a location to", false)
        val control1Point = game.choose(Point(42f, 575f), Point(600f, 575f))
        val control1Pair = createLabelWithShadow(control1String, control1Point.x, control1Point.y)
        val control1 = control1Pair.first
        val control1Shadow = control1Pair.second

        val control2String = game.choose("die Figur zu bewegen.", "move the player.", false)
        val control2Point = game.choose(Point(42f, 535f), Point(600f, 535f))
        val control2Pair = createLabelWithShadow(control2String, control2Point.x, control2Point.y)
        val control2 = control2Pair.first
        val control2Shadow = control2Pair.second

        val control3String = game.choose("Jede Figur hat unterschiedliche", "Each player has different", false)
        val control3Point = game.choose(Point(42f, 485f), Point(600f, 485f))
        val control3Pair = createLabelWithShadow(control3String, control3Point.x, control3Point.y)
        val control3 = control3Pair.first
        val control3Shadow = control3Pair.second

        val control4String = game.choose("Aktionen in seiner Aktionsleiste.", "actions in their actionbar.", false)
        val control4Point = game.choose(Point(42f, 445f), Point(600f, 445f))
        val control4Pair = createLabelWithShadow(control4String, control4Point.x, control4Point.y)
        val control4 = control4Pair.first
        val control4Shadow = control4Pair.second

        val lookString = game.choose("Ansehen", "Look at", false)
        val lookP = game.choose(Point(107f, 385f), Point(940f, 385f))
        val lookPair = createLabelWithShadow(lookString, lookP.x, lookP.y)
        val lookLabel = lookPair.first
        val lookShadow = lookPair.second

        val speakString = game.choose("Reden", "Talk to", false)
        val speakP = game.choose(Point(107f, 335f), Point(940f, 335f))
        val speakPair = createLabelWithShadow(speakString, speakP.x, speakP.y)
        val speakLabel = speakPair.first
        val speakShadow = speakPair.second

        val takeString = game.choose("Nehmen", "Take", false)
        val takeP = game.choose(Point(107f, 285f), Point(940f, 285f))
        val takePair = createLabelWithShadow(takeString, takeP.x, takeP.y)
        val takeLabel = takePair.first
        val takeShadow = takePair.second

        val useString = game.choose("Benutzen", "Use", false)
        val useP = game.choose(Point(107f, 235f), Point(940f, 235f))
        val usePair = createLabelWithShadow(useString, useP.x, useP.y)
        val useLabel = usePair.first
        val useShadow = usePair.second

        val combineString = game.choose("Kombinieren", "Combine", false)
        val combineP = game.choose(Point(107f, 185f), Point(940f, 185f))
        val combinePair = createLabelWithShadow(combineString, combineP.x, combineP.y)
        val combineLabel = combinePair.first
        val combineShadow = combinePair.second

        val look = Image(buttonAtlas.createSprite("Ansehen"))
        val lookPoint = game.choose(Point(42f, 385f), Point(1200f, 385f))
        look.x = lookPoint.x
        look.y = lookPoint.y
        val speak = Image(buttonAtlas.createSprite("Reden"))
        val speakPoint = game.choose(Point(42f, 335f), Point(1200f, 335f))
        speak.x = speakPoint.x
        speak.y = speakPoint.y
        val take = Image(buttonAtlas.createSprite("Nehmen"))
        val takePoint = game.choose(Point(42f, 285f), Point(1200f, 285f))
        take.x = takePoint.x
        take.y = takePoint.y
        val use = Image(buttonAtlas.createSprite("Benutzen"))
        val usePoint = game.choose(Point(42f, 235f), Point(1200f, 235f))
        use.x = usePoint.x
        use.y = usePoint.y
        val combine = Image(buttonAtlas.createSprite("kombinieren"))
        val combinePoint = game.choose(Point(42f, 185f), Point(1200f, 185f))
        combine.x = combinePoint.x
        combine.y = combinePoint.y

        val showItemsString = if(game.android)
            game.choose("Berühre den Screen mit 2 Fingern", "Touch the screen with 2 fingers", false)
        else
            game.choose("Drücke die Leertaste", "Press the spacebar", false)
        val showItems1Point = game.choose(Point(42f, 135f), Point(600f, 135f))
        val showItems1Pair = createLabelWithShadow(showItemsString, showItems1Point.x, showItems1Point.y)
        val showItems1Label = showItems1Pair.first
        val showItems1Shadow = showItems1Pair.second

        val showItems2String = game.choose("um benutzbare Dinge anzuzeigen.", "to highlight usable things.", false)
        val showItems2Point = game.choose(Point(42f, 90f), Point(600f, 90f))
        val showItems2Pair = createLabelWithShadow(showItems2String, showItems2Point.x, showItems2Point.y)
        val showItems2Label = showItems2Pair.first
        val showItems2Shadow = showItems2Pair.second

        val backSprite = game.choose("zuruck", "back", false)
        val backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(30F, 10F))
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
        stage.addActor(control1Shadow)
        stage.addActor(control2Shadow)
        stage.addActor(control3Shadow)
        stage.addActor(control4Shadow)
        stage.addActor(lookShadow)
        stage.addActor(speakShadow)
        stage.addActor(takeShadow)
        stage.addActor(useShadow)
        stage.addActor(combineShadow)
        stage.addActor(showItems2Shadow)
        stage.addActor(showItems1Shadow)
        stage.addActor(inventory)
        stage.addActor(actionbar)
        stage.addActor(changePlayer)
        stage.addActor(control1)
        stage.addActor(control2)
        stage.addActor(control3)
        stage.addActor(control4)
        stage.addActor(lookLabel)
        stage.addActor(speakLabel)
        stage.addActor(takeLabel)
        stage.addActor(useLabel)
        stage.addActor(combineLabel)
        stage.addActor(showItems2Label)
        stage.addActor(showItems1Label)
        stage.addActor(look)
        stage.addActor(speak)
        stage.addActor(take)
        stage.addActor(use)
        stage.addActor(combine)
        stage.addActor(backButton)
        stage.addActor(controlTitle)
    }

    override fun dispose() {
        stage.dispose()
    }

}
