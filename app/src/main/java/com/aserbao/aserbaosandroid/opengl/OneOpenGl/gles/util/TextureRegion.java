package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.util;

/**
 * Created by bbo on 1/4/16.
 */
public class TextureRegion {
    //--Members--//
    public float u1, v1;                               // Top/Left U,V Coordinates
    public float u2, v2;                               // Bottom/Right U,V Coordinates

    //--Constructor--//
    // D: calculate U,V coordinates from specified texture coordinates
    // A: texWidth, texHeight - the width and height of the texture the region is for
    //    x, y - the top/left (x,y) of the region on the texture (in pixels)
    //    width, height - the width and height of the region on the texture (in pixels)
    public TextureRegion(float textureWidth, float textureHeight, float x, float y, float width, float height)  {
        this.u1 = x / textureWidth;                 // Calculate U1
        this.v1 = y / textureHeight;                // Calculate V1
        this.u2 = this.u1 + width / textureWidth;   // Calculate U2
        this.v2 = this.v1 + height / textureHeight; // Calculate V2
    }
}