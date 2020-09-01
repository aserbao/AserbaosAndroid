precision mediump float;

uniform sampler2D u_TextureUnit;
uniform vec4 u_Color;
varying vec2 v_TextureCoordinates;

void main()
{
    // Texture is grayscale so take only grayscale value from
    // it when computing color output (otherwise font is always black)
    gl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates).w * u_Color;
}