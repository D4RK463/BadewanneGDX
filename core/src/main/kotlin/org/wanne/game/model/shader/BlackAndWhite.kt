package org.wanne.game.model.shader

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import org.wanne.game.model.objects.GameObject

object BlackAndWhite : Shader() {
    private val fragmentShader: String = Gdx.files.internal("shaders/bw.glsl").readString()

    private val shader: ShaderProgram

    init {;
        shader = ShaderProgram(defaultVertxShader, fragmentShader)

        if (!shader.isCompiled) {
            println(shader.log)
        }
    }

    override fun draw(batch: Batch, gameObject: GameObject, stateTime: Float) {

        // Normalen Render enden
        batch.end()
        batch.flush()

        // Object mit Shader zeichnen
        batch.begin()
        batch.shader = shader

        shader.bind()
        gameObject.draw(batch, 1F, Gdx.graphics.deltaTime)
        batch.end()
        batch.flush()

        // Shader wieder entfernen und alles geht normal weiter
        batch.shader = null
        batch.begin()
    }

}
