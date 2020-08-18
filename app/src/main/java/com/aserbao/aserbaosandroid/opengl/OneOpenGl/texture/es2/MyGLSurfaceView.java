package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture.es2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import com.aserbao.aserbaosandroid.R;

public class MyGLSurfaceView extends GLSurfaceView implements Renderer{
	
	Model model;
	int flag = -1;
	//投影矩阵
    private float[] mProjectionMatrix = new float[16];
    
    //视图矩阵
    private float[] mViewMatrix = new float[16];
    
    //模型视图投影矩阵
    private float[] mMVPMatrix = new float[16];
	
	public MyGLSurfaceView(Context context,int flag){
        super(context);
        setEGLContextClientVersion(2);
        this.flag = flag;
        // Set the Renderer for drawing on the GLSurfaceView
        this.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSPARENT);
        setRenderer(this);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

	/**
	 * 调用一次，初始化opengles 环境
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		// 设置背景颜色
        GLES20.glClearColor(0.97f, 0.58f, 0.0f, 1.0f);
        
        if(flag==0){
        		model = new Triangle();
        		model.setTexture(BitmapFactory.decodeResource(getResources(), R.drawable.mm_1));
        }
        if(flag==1){
        		model = new Square();
        		model.setTexture(BitmapFactory.decodeResource(getResources(), R.drawable.mm_2));
        }
        
	}
	float ratio;
	/**
	 * 当glView改变时调用，比如屏幕方向彼变化
	 */
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//设置绘制区域
		GLES20.glViewport(0, 0, width, height);
		
		 ratio = (float) width / height;
		 Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
       
	}

	/**
	 * 每次重绘调用
	 */
	@Override
	public void onDrawFrame(GL10 gl) {
	    GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        model.draw(mMVPMatrix);
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0,3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
	}
	
	
	public static int loadShader(int shaderType, String source) {
	        int shader = GLES20.glCreateShader(shaderType);
	        if (shader != 0) {
	            GLES20.glShaderSource(shader, source);
	            GLES20.glCompileShader(shader);
	            int[] compiled = new int[1];
	            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
	            if (compiled[0] == 0) {
	                GLES20.glDeleteShader(shader);
	                shader = 0;
	            }
	        }
	        return shader;
	    }
}