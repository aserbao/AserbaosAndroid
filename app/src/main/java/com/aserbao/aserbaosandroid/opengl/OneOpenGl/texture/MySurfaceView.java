
package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture;
 
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
 
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
 
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.aserbao.aserbaosandroid.R;

/**
 * @author: aserbao
 * @date:2020/8/18 9:27 AM
 * @package:
 * @describle:
 */
public class MySurfaceView extends GLSurfaceView{
 
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		setRenderer(new MyRenderer());
	}
 
	private class MyRenderer implements Renderer {
 
		float vertices[]=new float[] {
        		-1, -1,
            	1, -1,
            	0, 1
        };
		
		float textureCoors[]=new float[] {
        		0, 1,
            	1,1,
            	0.5f,0,
	    };  
		
		FloatBuffer mVertexBuffer, mTexCoordBuffer;
		int mTexId;
		
		public MyRenderer() {
			// TODO Auto-generated constructor stub
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
	        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
	        mVertexBuffer = vbb.asFloatBuffer();//转换为int型缓冲
	        mVertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
	        mVertexBuffer.position(0);//设置缓冲区起始位置
	        
	        ByteBuffer cbb = ByteBuffer.allocateDirect(textureCoors.length*4);
	        cbb.order(ByteOrder.nativeOrder());//设置字节顺序
	        mTexCoordBuffer = cbb.asFloatBuffer();//转换为int型缓冲
	        mTexCoordBuffer.put(textureCoors);//向缓冲区中放入顶点着色数据
	        mTexCoordBuffer.position(0);//设置缓冲区起始位置
	        
	        
		}
 
		private int initTexture(GL10 gl, Bitmap bitmap) {
			// TODO Auto-generated method stub
			int[] textures = new int[1];
			
			gl.glEnable(GL10.GL_TEXTURE_2D);
			gl.glGenTextures(1, textures, 0);    
			int currTextureId = textures[0];
			
			gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_REPEAT);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_REPEAT);
	        
	        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			return currTextureId;
		}
 
		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	        gl.glVertexPointer
	        (
	        		2,				
	        		GL10.GL_FLOAT,	
	        		0, 				
	        		mVertexBuffer
	        );
	        
	        gl.glEnable(GL10.GL_TEXTURE_2D);   
	        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	        gl.glTexCoordPointer
	        (
	        		2, 					//每个顶点两个纹理坐标数据 S、T
	        		GL10.GL_FLOAT, 		//数据类型
	        		0, 					//连续纹理坐标数据之间的间隔
	        		mTexCoordBuffer		//纹理坐标数据
	        );
	        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTexId);   
	        
	        gl.glDrawArrays
	        (
	        		GL10.GL_TRIANGLES, 
	        		0, 
	        		3
	        );
	        gl.glDisable(GL10.GL_TEXTURE_2D);//关闭纹理
		}
 
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height);
        	//设置当前矩阵为投影矩阵
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //设置当前矩阵为单位矩阵
            gl.glLoadIdentity();
		}
 
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
			
			mTexId = initTexture(gl, bitmap);
		}
		
	}
}