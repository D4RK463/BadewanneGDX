package org.wanne.game.sound.collections

import org.wanne.game.AssetsManager
import org.wanne.game.sound.Speech
import org.wanne.game.sound.SoundCollection

class MarioSoundCollection(am: AssetsManager) : SoundCollection(am) {
    init {

        // Deutsch Alt
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/lassmich.mp3")) // 0
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/feuerverloren.mp3")) // 1
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/bowserweg.mp3")) // 2
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/gefuehlsstein.mp3")) // 3
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/wiemanfeuerwieder.mp3")) // 4
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/klassedanke.mp3")) // 5
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/hierlandenalleleutedie.mp3")) // 6
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/derwaechterhatsieversigelt.mp3")) // 7
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/vielleichtversuchenzuentkommen.mp3")) // 8
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/habseinetelefonnummer.mp3")) // 9
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/abrungtiefeboesekreatur.mp3")) // 10
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/dieselenerntet.mp3")) // 11
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/riesiggroßmitfuerchterlichen.mp3")) // 12
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/ichhabedichgewarnt.mp3")) // 13
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/wirkennenunsnicht.mp3")) // 14
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/warummaskeauf.mp3")) // 15
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/langsamkeinelustmehr.mp3")) // 16
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/fragmadeinenFreund.mp3")) // 17
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/teppischfestgenagelt.mp3")) // 18
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/wuerdedirhelfenaber.mp3")) // 19
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/aberwehe2.mp3")) // 20
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/angstindenaugensehn.mp3")) // 21
        addSound(Speech.DE_ORIGINAL, am.get("soundsOriginal/Mario/Mariotransform.mp3")) // 22
    }
}
