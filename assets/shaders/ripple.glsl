#ifdef GL_ES
    #define PRECISION mediump
    precision PRECISION float;
    precision PRECISION int;
#else
    #define PRECISION
#endif

uniform sampler2D u_texture;
uniform float u_time;
uniform vec2 u_resolution;

varying vec2 v_texCoords;

void main()
{
    // pixel position normalised to [-1, 1]
    vec2 cPos = -1.0 + 2.0 * v_texCoords.xy / u_resolution.xy;

    // distance of current pixel from center
    float cLength = length(cPos);

    vec2 uv = v_texCoords.xy/u_resolution.xy+(cPos/cLength)*cos(cLength*12.0-u_time*4.0) * 0.03;

    vec3 col = texture2D(u_texture, uv).xyz;

    gl_FragColor = vec4(col,1.0);
}
