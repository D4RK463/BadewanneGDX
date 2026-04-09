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
import org.wanne.game.model.objects.GameObject
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

class GameObjectsArchive() {

    // Room
    lateinit var pills: Pills
    lateinit var bed: Bed
    lateinit var roomWindow: Window
    lateinit var drawer: Drawer
    lateinit var door: Door
    lateinit var pa2Poster: PA2Poster
    lateinit var brucePoster: BrucePoster
    lateinit var deanPoster: DeanPoster
    lateinit var rug: Rug
    lateinit var stickers: Stickers
    lateinit var straw: Straw
    lateinit var box: Box
    lateinit var safe: Safe
    lateinit var drBear: DrBear
    lateinit var milkSucker: MilkSucker
    lateinit var stethoscope: Stethoscope
    lateinit var scalpel: Scalpel
    lateinit var note: Note
    lateinit var mario: Mario
    lateinit var bell: Cowbell
    lateinit var teddy: Teddy
    lateinit var flower: FireFlower
    lateinit var telephone: Telephone
    lateinit var exit: Exit

    // Cow Dialog
    lateinit var cow: Cow

    // Outside
    lateinit var ice: Ice
    lateinit var iceMenuLeft: IceMenuLeft
    lateinit var iceMenuRight: IceMenuRight
    lateinit var honkSign: HonkSign
    lateinit var street: Street
    lateinit var graffiti: Graffiti
    lateinit var iceman: Iceman
    lateinit var goldBag: GoldBag

    fun asList(): List<GameObject> {
        return listOf(
            pills,
            bed,
            roomWindow,
            drawer,
            door,
            pa2Poster,
            brucePoster,
            deanPoster,
            rug,
            stickers,
            straw,
            box,
            safe,
            drBear,
            milkSucker,
            stethoscope,
            scalpel,
            note,
            mario,
            bell,
            teddy,
            flower,
            telephone,
            exit,
            cow,
            ice,
            iceMenuLeft,
            iceMenuRight,
            honkSign,
            street,
            graffiti,
            iceman,
            goldBag
        )
    }

    fun resetItems(game: WanneGame) {
        pills = Pills(game = game)
        bed = Bed(game = game)
        roomWindow = Window(game = game)
        drawer = Drawer(game = game)
        door = Door(game = game)
        pa2Poster = PA2Poster(game = game)
        brucePoster = BrucePoster(game = game)
        deanPoster = DeanPoster(game = game)
        rug = Rug(game = game)
        stickers = Stickers(game = game)
        straw = Straw(game = game)
        box = Box(game = game)
        safe = Safe(game = game)
        drBear = DrBear(game = game, gameObjectToAppear = pills)
        milkSucker = MilkSucker(game = game)
        stethoscope = Stethoscope(game = game, gameObjectToAppear = milkSucker)
        scalpel = Scalpel(game = game, gameObjectToAppear = pills)
        note = Note(game = game)
        mario =
            Mario(
                game = game,
                gameObjectToManipulate = rug,
                gameObjectToAppear = note
            )
        bell = Cowbell(game = game)
        teddy = Teddy(game = game, gameObjectToCheck = mario)
        flower = FireFlower(game = game, gameObjectToManipulate = mario)
        telephone = Telephone(game = game, winningRequiredGameObjectList = listOf(milkSucker, pills, bell))
        exit = Exit(game = game)

        // Cow Dialog
        cow = Cow(game = game)

        // Outside
        ice = Ice(game = game)
        iceMenuLeft = IceMenuLeft(game = game)
        iceMenuRight = IceMenuRight(game = game)
        honkSign = HonkSign(game = game)
        street = Street(game = game)
        graffiti = Graffiti(game = game)
        iceman = Iceman(game = game, gameObjectToAppear = ice)
        goldBag = GoldBag(game = game)
    }
}
