package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.objects;


import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.data.VertexArray;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs.TextureShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;


public class Table {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * VertexArray.BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
        // Order of coordinates: X, Y, Z, S, T
        // Triangle Fan
        // Top
         0f,      0f,  0, 0.5f, 0.5f,
        -1f, -0.125f,  0,   0f,   1f,
         1f, -0.125f,  0,   1f,   1f,
         1f,  0.125f,  0,   1f,   0f,
        -1f,  0.125f,  0,   0f,   0f,
        -1f, -0.125f,  0,   0f,   1f,
        // Bottom
         0f,      0f, -0.03f, 0.5f, 0.5f,
        -1f,  0.125f, -0.03f,   0f,   0f,
         1f,  0.125f, -0.03f,   1f,   0f,
         1f, -0.125f, -0.03f,   1f,   1f,
        -1f, -0.125f, -0.03f,   0f,   1f,
        -1f,  0.125f, -0.03f,   0f,   0f,
    };

    private final VertexArray mVertexArray;

    public Table() {
        mVertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram textureProgram) {
        mVertexArray.setVertexAttribPointer(
                0,
                textureProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);

        mVertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, 12);
    }
}