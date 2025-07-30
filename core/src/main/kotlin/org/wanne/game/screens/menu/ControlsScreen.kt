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

        val background: Texture = game.am["$TEXTURES/tutorial.png"]
        stage.addActor(Image(background))
        buildMenu()
    }

    fun buildMenu() {
        val controlsSprite = game.choose("steuerung", "controls", false)
        val controlTitle = Image(mainButtonAtlas.createSprite(controlsSprite))
        controlTitle.x = 30f
        controlTitle.y = 630f

        val classicPair = createLabelWithShadow("Classic Mode", 900f, 655f, 1F, Color.ORANGE)
        val classic = classicPair.first
        val classicShadow = classicPair.second

        val modernPair = createLabelWithShadow("Modern Mode", 900f, 250f, 1F, Color.ORANGE)
        val modern = modernPair.first
        val modernShadow = modernPair.second

        val action = if (game.android) { "tab" } else { game.choose("klick", "click", false) }
        val control1String = game.choose("${action} an einen Ort um", "${action} on a location to", false)
        val control1Pair = createLabelWithShadow(control1String, 42f, 575f)
        val control1 = control1Pair.first
        val control1Shadow = control1Pair.second

        val control2String = game.choose("die Figur zu bewegen.", "move the player.", false)
        val control2Pair = createLabelWithShadow(control2String, 42f, 535f)
        val control2 = control2Pair.first
        val control2Shadow = control2Pair.second

        val control3String = game.choose("Jede Figur hat unterschiedliche", "Each player has different", false)
        val control3Pair = createLabelWithShadow(control3String, 42f, 485f)
        val control3 = control3Pair.first
        val control3Shadow = control3Pair.second

        val control4String = game.choose("Aktionen in seiner Aktionsleiste.", "actions in their actionbar.", false)
        val control4Pair = createLabelWithShadow(control4String, 42f, 445f)
        val control4 = control4Pair.first
        val control4Shadow = control4Pair.second

        val lookString = game.choose("Ansehen", "Look at", false)
        val lookPair = createLabelWithShadow(lookString, 107f, 385f)
        val lookLabel = lookPair.first
        val lookShadow = lookPair.second

        val speakString = game.choose("Reden", "Talk to", false)
        val speakPair = createLabelWithShadow(speakString, 107f, 335f)
        val speakLabel = speakPair.first
        val speakShadow = speakPair.second

        val takeString = game.choose("Nehmen", "Take", false)
        val takePair = createLabelWithShadow(takeString, 107f, 285f)
        val takeLabel = takePair.first
        val takeShadow = takePair.second

        val useString = game.choose("Benutzen", "Use", false)
        val usePair = createLabelWithShadow(useString, 107f, 235f)
        val useLabel = usePair.first
        val useShadow = usePair.second

        val combineString = game.choose("Kombinieren", "Combine", false)
        val combinePair = createLabelWithShadow(combineString, 107f, 185f)
        val combineLabel = combinePair.first
        val combineShadow = combinePair.second

        val changeToDuckString = game.choose("Ente wählen", "Choose duck", false)
        val changeToDuckPair = createLabelWithShadow(changeToDuckString, 372f, 385f)
        val changeToDuckLabel = changeToDuckPair.first
        val changeToDuckShadow = changeToDuckPair.second

        val changeToPAString = game.choose("Bademeister wählen", "Choose pool attendant", false)
        val changeToPAPair = createLabelWithShadow(changeToPAString, 372f, 335f)
        val changeToPALabel = changeToPAPair.first
        val changeToPAShadow = changeToPAPair.second

        val look = Image(buttonAtlas.createSprite("Ansehen"))
        look.x = 42f
        look.y = 385f
        val speak = Image(buttonAtlas.createSprite("Reden"))
        speak.x = 42f
        speak.y = 335f
        val take = Image(buttonAtlas.createSprite("Nehmen"))
        take.x = 42f
        take.y = 285f
        val use = Image(buttonAtlas.createSprite("Benutzen"))
        use.x = 42f
        use.y = 235f
        val combine = Image(buttonAtlas.createSprite("kombinieren"))
        combine.x = 42f
        combine.y = 185f
        val poolAttendant = Image(buttonAtlas.createSprite("Bademeister"))
        poolAttendant.x = 310f
        poolAttendant.y = 382f
        val duck = Image(buttonAtlas.createSprite("Ente"))
        duck.x = 310f
        duck.y = 330f

        val showItemsString = if(game.android)
            game.choose("Berühre den Screen mit 2 Fingern", "Touch the screen with 2 fingers", false)
        else
            game.choose("Drücke die Leertaste", "Press the spacebar", false)
        val showItems1Pair = createLabelWithShadow(showItemsString, 42f, 135f)
        val showItems1Label = showItems1Pair.first
        val showItems1Shadow = showItems1Pair.second

        val showItems2String = game.choose("um benutzbare Dinge anzuzeigen.", "to highlight usable things.", false)
        val showItems2Pair = createLabelWithShadow(showItems2String, 42f, 90f)
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
        stage.addActor(changeToPAShadow)
        stage.addActor(changeToDuckShadow)
        stage.addActor(classicShadow)
        stage.addActor(modernShadow)
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
        stage.addActor(changeToPALabel)
        stage.addActor(changeToDuckLabel)
        stage.addActor(classic)
        stage.addActor(modern)
        stage.addActor(look)
        stage.addActor(speak)
        stage.addActor(take)
        stage.addActor(use)
        stage.addActor(combine)
        stage.addActor(poolAttendant)
        stage.addActor(duck)
        stage.addActor(backButton)
        stage.addActor(controlTitle)
    }

    override fun dispose() {
        stage.dispose()
    }

}
