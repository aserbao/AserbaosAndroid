package com.aserbao.aserbaosandroid.opengl.OneOpenGl.shapes;

import android.opengl.GLES20;

import com.aserbao.aserbaosandroid.opengl.OneOpenGl.OneGlRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by aserbao on 2018 2018/1/18.22:28
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class TriangleColor {
    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private final int mProgram;
    private FloatBuffer vertexBuffer;
    private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    /*static float triangleCoords[] = {   // in counterclockwise order:
            0.0f,  0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 255, 0, 0, 1.0f };*/

    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {
            1.0f, 0f, 0f, 1.0f ,
    };
    public TriangleColor() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());
        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = OneGlRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = OneGlRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // 创建空的OpenGL ES程序
        mProgram = GLES20.glCreateProgram();

        // 添加顶点着色器来编程
        GLES20.glAttachShader(mProgram, vertexShader);

        // 添加片段着色器来编程
        GLES20.glAttachShader(mProgram, fragmentShader);

        // 创建OpenGL ES程序可执行文件
        GLES20.glLinkProgram(mProgram);
    }

    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw(float[] mvpMatrix){
        // 将程序添加到OpenGL ES环境
        GLES20.glUseProgram(mProgram);

        // 获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // 准备三角形坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // 获取片段着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // 设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // 得到形状的变换矩阵的句柄
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // 将投影和视图转换传递给着色器
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        // 画三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // 禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
