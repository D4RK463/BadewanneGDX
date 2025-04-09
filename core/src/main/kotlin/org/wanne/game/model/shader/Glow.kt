package org.wanne.game.model.shader

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.scenes.scene2d.Group

object Glow : Group() {

    private lateinit var vertxShader: String
    private val fragmentShader: String

    val shader: ShaderProgram

    private val fbo: FrameBuffer = FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.width, Gdx.graphics.height, true);

    private var time: Float = 0f

    private val batch: SpriteBatch = SpriteBatch()

    init {
        time = 0F
//        fragmentShader = Gdx.files.internal("shaders/glow.glsl").readString();
        fragmentShader = Gdx.files.internal("shaders/bw.glsl").readString();
        vertxShader = Gdx.files.internal("shaders/vertex.glsl").readString();
//        shader = ShaderProgram(batch.shader.vertexShaderSource, fragmentShader)
        shader = ShaderProgram(vertxShader, fragmentShader)

        if (!shader.isCompiled) {
            println(shader.log)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)
        time += delta
    }

    // ToDo: Refactor
    override fun draw(batch: Batch, parentAlpha: Float) {

        // Normalen Render enden
        batch.end()
        batch.flush()

        // Bild mit Framebuffer zeichnen
        fbo.begin()
        batch.begin()
        super.draw(batch, parentAlpha)
        batch.end()
        batch.flush()
        fbo.end()

        // Shader über das Framebuffer bild legen
        batch.begin()
        batch.shader = shader

        // Werte im Shader setzen
        shader.setUniformf("u_time", time)
        shader.setUniformf("u_resolution", Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        // Texture vom Framebuffer holen und anzeigen
        val texture = fbo.colorBufferTexture
        val region = TextureRegion(texture)

        batch.draw(region, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.shader = null
    }

}
