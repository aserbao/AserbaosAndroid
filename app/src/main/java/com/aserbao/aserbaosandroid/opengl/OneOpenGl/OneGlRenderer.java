package com.aserbao.aserbaosandroid.opengl.OneOpenGl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.aserbao.aserbaosandroid.opengl.OneOpenGl.shapes.Square;
import com.aserbao.aserbaosandroid.opengl.OneOpenGl.shapes.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by aserbao on 2018 2018/1/16.23:15
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class OneGlRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;
    private Square   mSquare;
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
