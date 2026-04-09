package org.wanne.game.model.shader

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import org.wanne.game.model.objects.GameObject

/*
https://github.com/raeleus/shadertoy-sample-project/wiki
https://github.com/crashinvaders/gdx-vfx
https://gamefromscratch.com/libgdx-tutorial-part-12-using-glsl-shaders-and-creating-a-mesh/
 */
abstract class Shader {
    val defaultVertxShader: String = Gdx.files.internal("shaders/vertex.glsl").readString()

    abstract fun draw(batch: Batch, gameObject: GameObject, stateTime: Float)
}
