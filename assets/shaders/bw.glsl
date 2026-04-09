#ifdef GL_ES
    #define PRECISION mediump
    precision PRECISION float;
    precision PRECISION int;
#else
    #define PRECISION
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;

// https://stackoverflow.com/questions/3508643/how-do-i-convert-a-photo-from-color-to-black-and-white-using-opengl
void main() {
    vec4 color = texture2D(u_texture, v_texCoords).rgba;

    // color to grey
    float gray = (color.r + color.g + color.b) / 3.0;
    // it is grey now, but don't loose the alpha channel
    vec4 grayscale = vec4(gray, gray, gray, color.a);

    gl_FragColor = vec4(grayscale);
}
