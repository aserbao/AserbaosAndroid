package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs;

import android.content.Context;


import com.aserbao.aserbaosandroid.R;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by bbo on 1/3/16.
 */
public class TextShaderProgram extends ShaderProgram {
    // Uniform locations
    private final int uMatrixLocation;
    private final int uTextureUnitLocation;
    private final int uColorLocation;

    // Attribute locations
    private final int aPositionLocation;
    private final int aTextureCoordinatesLocation;

    public TextShaderProgram(Context context) {
        super(context, R.raw.text_vertex_shader, R.raw.text_fragment_shader);

        // Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);
        uTextureUnitLocation = glGetUniformLocation(mProgram, U_TEXTURE_UNIT);
        uColorLocation = glGetUniformLocation(mProgram, U_COLOR);

        // Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
        aTextureCoordinatesLocation = glGetAttribLocation(mProgram, A_TEXTURE_COORDINATES);
    }

    public void setUniforms(float[] matrix, int textureId, float r, float g, float b, float a) {
        // Pass the matrix into the shader program.
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        // Set the text color
        glUniform4f(uColorLocation, r, g, b, a);

        // Set the text texture (the texture containing textures of each character).
        // Set the active texture unit to texture unit 0.
        glActiveTexture(GL_TEXTURE0);

        // Bind the texture to this unit.
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Tell the texture uniform sampler to use this texture in the shader by
        // telling it to read from texture unit 0.
        glUniform1i(uTextureUnitLocation, 0);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getTextureCoordinatesAttributeLocation() {
        return aTextureCoordinatesLocation;
    }
}
