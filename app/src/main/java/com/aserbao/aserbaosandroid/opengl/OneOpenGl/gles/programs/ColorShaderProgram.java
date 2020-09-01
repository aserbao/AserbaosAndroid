package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.programs;

import android.content.Context;


import com.aserbao.aserbaosandroid.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

public class ColorShaderProgram extends ShaderProgram {
    // Uniform locations
    private final int uMatrixLocation;
    private final int uColorLocation;

    // Attribute locations
    private final int aPositionLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

        // Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);
        uColorLocation = glGetUniformLocation(mProgram, U_COLOR);

        // Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
    }

    public void setUniforms(float[] matrix, float r, float g, float b, float a) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glUniform4f(uColorLocation, r, g, b, a);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
}