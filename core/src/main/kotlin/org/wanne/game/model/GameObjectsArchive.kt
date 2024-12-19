package org.wanne.game.model

import org.wanne.game.WanneGame
import org.wanne.game.model.objects.Bed
import org.wanne.game.model.objects.Box
import org.wanne.game.model.objects.BrucePoster
import org.wanne.game.model.objects.Cow
import org.wanne.game.model.objects.Cowbell
import org.wanne.game.model.objects.DeanPoster
import org.wanne.game.model.objects.Door
import org.wanne.game.model.objects.DrBear
import org.wanne.game.model.objects.Drawer
import org.wanne.game.model.objects.Exit
import org.wanne.game.model.objects.FireFlower
import org.wanne.game.model.objects.GoldBag
import org.wanne.game.model.objects.Graffiti
import org.wanne.game.model.objects.HonkSign
import org.wanne.game.model.objects.Ice
import org.wanne.game.model.objects.IceMenuLeft
import org.wanne.game.model.objects.IceMenuRight
import org.wanne.game.model.objects.Iceman
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
import org.wanne.game.model.objects.Street
import org.wanne.game.model.objects.Teddy
import org.wanne.game.model.objects.Telephone
import org.wanne.game.model.objects.Window

class GameObjectsArchive(game: WanneGame) {

    // Room
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
            gameObjectToAppear = note
        )
    val bell = Cowbell(game = game)
    val teddy = Teddy(game = game, gameObjectToCheck = mario)
    val flower = FireFlower(game = game, gameObjectToManipulate = mario)
    val telephone = Telephone(game = game, winningRequiredGameObjectList = listOf(milkSucker, pills, bell))
    val exit = Exit(game = game)

    // Cow Dialog
    val cow = Cow(game = game)

    // Outside
    val ice = Ice(game = game)
    val iceMenuLeft = IceMenuLeft(game = game)
    val iceMenuRight = IceMenuRight(game = game)
    val honkSign = HonkSign(game = game)
    val street = Street(game = game)
    val graffiti = Graffiti(game = game)
    val iceman = Iceman(game = game, gameObjectToAppear = ice)
    val goldBag = GoldBag(game = game)

}
