package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.image;

/**
 * Created by ICE on 2017/11/6.
 */

public class ImageDrawConstants {
    public static final String Default_vertexShader_filter = "" +
            "attribute vec4 aCamPosition;\n" +
            "attribute vec2 aCamTextureCoord;\n" +
            "varying vec2 vCamTextureCoord;\n" +
            "void main(){\n" +
            "    gl_Position= aCamPosition;\n" +
            "    vCamTextureCoord = aCamTextureCoord;\n" +
            "}";
    public static final String Default_fragmentshader_filter = "" +
            "precision highp float;\n" +
            "varying highp vec2 vCamTextureCoord;\n" +
            "uniform sampler2D uCamTexture;\n" +
            "void main(){\n" +
            "    vec4  color = texture2D(uCamTexture, vCamTextureCoord);\n" +
            "    gl_FragColor = color;\n" +
            "}";

}
