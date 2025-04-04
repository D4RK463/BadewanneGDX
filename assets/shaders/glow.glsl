#ifdef GL_ES
    #define PRECISION mediump
    precision PRECISION float;
    precision PRECISION int;
#else
    #define PRECISION
#endif

varying vec2       v_texCoords;
uniform vec3       u_resolution;           // viewport resolution (in pixels)
uniform float      u_time;                 // shader playback time (in seconds)
uniform sampler2D  u_texture;

const float radius = 16.0;
const vec3 glowColor = vec3(0.9, 0.2, 0.0);
const float sweepRate = 25.0;
const float pi = 3.14159265358979323846;
const float timeFactor = 3.0;
const float halfTimeFactor = timeFactor * 0.5;

float coefficient()
{
    float v = mod(u_time, timeFactor);
    if(v > halfTimeFactor)
    v = timeFactor - v;
    return v;
}

void main ()
{
    vec2 uv = v_texCoords;
    vec4 texel = texture2D(u_texture, uv);
    vec4 finalColor = vec4(0.0);
    float density = 0.0;

    if(texel.a >= 1.0)
    finalColor = vec4(texel.rgb, 1.0);
    else
    {
        for(float i = 0.0; i < 360.0; i += sweepRate)
        {
            float xi = radius * cos(pi * i / 180.0) / u_resolution.x;
            float yi = radius * sin(pi * i / 180.0) / u_resolution.y;

            density += texture2D(u_texture, vec2(uv.x + xi, uv.y + yi)).a;
            density += texture2D(u_texture, vec2(uv.x - xi, uv.y + yi)).a;
            density += texture2D(u_texture, vec2(uv.x - xi, uv.y - yi)).a;
            density += texture2D(u_texture, vec2(uv.x + xi, uv.y - yi)).a;
        }
        finalColor = vec4(glowColor * density / radius * coefficient(), 1.0);
        finalColor += vec4(texel.rgb * texel.a, texel.a);
    }
    gl_FragColor = finalColor;
}
