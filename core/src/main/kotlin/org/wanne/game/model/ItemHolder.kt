package org.wanne.game.model

import org.wanne.game.WanneGame
import org.wanne.game.model.animation.FireAnimation
import org.wanne.game.model.animation.PowerUpAnimation
import org.wanne.game.model.objects.Bed
import org.wanne.game.model.objects.Box
import org.wanne.game.model.objects.BrucePoster
import org.wanne.game.model.objects.Cowbell
import org.wanne.game.model.objects.DeanPoster
import org.wanne.game.model.objects.Door
import org.wanne.game.model.objects.DrBear
import org.wanne.game.model.objects.Drawer
import org.wanne.game.model.objects.Exit
import org.wanne.game.model.objects.FireFlower
import org.wanne.game.model.objects.Mario
import org.wanne.game.model.objects.MilkSucker
import org.wanne.game.model.objects.Note
import org.wanne.game.model.objects.PA2Poster
import org.wanne.game.model.objects.Pills
import org.wanne.game.model.objects.Rug
import org.wanne.game.model.objects.Safe
import org.wanne.game.model.objects.Scalpel
import org.wanne.game.model.objects.Stethoscope
import org.wanne.game.model.objects.Stickers
import org.wanne.game.model.objects.Straw
import org.wanne.game.model.objects.Teddy
import org.wanne.game.model.objects.Telephone
import org.wanne.game.model.objects.Window

class ItemHolder(game: WanneGame) {

    // Animations
    val fireAnimation = FireAnimation(
        game.choose(Point(82F, 198F), Point(336F, 148F)),
        false,
        game.am
    )
    val powerUpAnimation = PowerUpAnimation(
        game.choose(Point(82F, 345F), Point(336F, 295F)),
        false,
        game.am
    )

    // Objects
    val pills = Pills(game = game)
    val bed = Bed(game = game)
    val roomWindow = Window(game = game)
    val drawer = Drawer(game = game)
    val door = Door(game = game)
    val pa2Poster = PA2Poster(game = game)
    val brucePoster = BrucePoster(game = game)
    val deanPoster = DeanPoster(game = game)
    val rug = Rug(game = game)
    val stickers = Stickers(game = game)
    val straw = Straw(game = game)
    val box = Box(game = game)
    val safe = Safe(game = game)
    val drBear = DrBear(game = game, gameObjectToAppear = pills)
    val milkSucker = MilkSucker(game = game)
    val stethoscope = Stethoscope(game = game, gameObjectToAppear = milkSucker)
    val scalpel = Scalpel(game = game, gameObjectToAppear = pills)
    val note = Note(game = game)
    val mario =
        Mario(
            game = game,
            gameObjectToManipulate = rug,
            gameObjectToAppear = note,
            fireAnimation = fireAnimation,
            powerUpAnimation = powerUpAnimation
        )
    val bell = Cowbell(game = game)
    val teddy = Teddy(game = game, gameObjectToCheck = mario)
    val flower = FireFlower(game = game, gameObjectToManipulate = mario)
    val telephone = Telephone(game = game, winningRequiredGameObjectList = listOf(milkSucker, pills, bell))
    val exit = Exit(game = game)

}
