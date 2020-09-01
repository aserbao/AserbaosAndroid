package com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

public class VertexArray {
    public static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer mFloatBuffer;

    public VertexArray(float[] vertexData) {
        mFloatBuffer = ByteBuffer
                .allocateDirect(vertexData.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertexData);
    }

    public void setVertexAttribPointer(int dataOffset, int attributeLocation, int componentCount, int stride) {
        mFloatBuffer.position(dataOffset);
        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT, false, stride, mFloatBuffer);
        glEnableVertexAttribArray(attributeLocation);
        mFloatBuffer.position(0);
    }
}
