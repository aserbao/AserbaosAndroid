package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture.es2;

import static android.opengl.GLES20.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDisable;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glTexParameterf;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Square extends Model{
	private FloatBuffer vertexBuffer;
	private ShortBuffer drawListBuffer;
	FloatBuffer textureCoordBuffer;
	private int[] textureId;
	private Bitmap texture;
	int mProgram;
	int mPositionHandle;
	int aTextureCoord;
	int vertexCount = 4;// 顶点数
	int vertexStride = COORDS_PER_VERTEX * 4;// 跨度
	int mMVPMatrixHandle;
	Shader shader;
	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;
	/** 正方形是由三角形拼接起来的（2个三角形）,为了简便只定义两个三角形的4个顶点，因为有2个是公共的 **/
	static float squareCoords[] = {
			-0.5f, 0.5f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.5f, -0.5f, 0.0f, // bottom right
			0.5f, 0.5f, 0.0f }; // top right

	/** 因为使用了公共的坐标，所以要定义4个顶点（2个三角形）的绘制顺序 **/
	private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

	public Square() {
		shader = new Shader();
		shader.setProgram(vertexShaderCode, fragmentShaderCode);
		initDataBuffer();
	}

	/**
	 * 初始化坐标
	 */
	private void initDataBuffer() {
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 4 bytes per float)
				squareCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(squareCoords);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);

		textureCoordBuffer = ByteBuffer.allocateDirect(2 * 4 * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();

		textureCoordBuffer.put(new float[] { 0, 0, 0, 1, 1, 1, 1, 0 });
		textureCoordBuffer.position(0);

		/**
		 * 获取着色器中的变量句柄
		 */
		aTextureCoord = shader.getHandle("aTextureCoord");
		mPositionHandle = shader.getHandle("vPosition");
		mMVPMatrixHandle = shader.getHandle("uMVPMatrix");
	}

	/**
	 * 加载纹理
	 * 
	 * @param bitmap
	 */
	public void setTexture(Bitmap bitmap) {
		this.texture = bitmap;
		textureId = new int[1];
		glGenTextures(1, textureId, 0);// 生成纹理ID
		glBindTexture(GL_TEXTURE_2D, textureId[0]);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		if (texture != null) {
			glEnable(GL_TEXTURE_2D);
			GLUtils.texImage2D(GL_TEXTURE_2D, 0, texture, 0);
			glDisable(GL_TEXTURE_2D);
			this.texture.recycle();
			this.texture = null;
		}
	}

	public void draw(float[] mvpMatrix) {
		// 将program加入OpenGL ES环境中
		shader.useProgram();

		/** 绑定之前获取的纹理ID **/
		glBindTexture(GL_TEXTURE_2D, textureId[0]);

		// 设置纹理贴图
		glVertexAttribPointer(aTextureCoord, 2, GL_FLOAT, false, 0,
				textureCoordBuffer);
		glEnableVertexAttribArray(aTextureCoord);

		// 启用一个指向三角形的顶点数组的handle
		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);

		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

		/** 设置投影变换矩阵 **/
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

		// 画正方形
		GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, drawOrder.length,
				GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

		// 禁用顶点数组
		GLES20.glDisableVertexAttribArray(mPositionHandle);
		// 清空texture
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	/**
	 * 顶点着色器
	 */
	private final String vertexShaderCode = "uniform mat4 uMVPMatrix;   \n"
			+ "attribute vec4 vPosition; \n"
			+ "attribute vec2 aTextureCoord; \n"
			+ "varying vec2 vTextureCoord; \n" + "void main() {  \n"
			+ "  gl_Position = uMVPMatrix * vPosition; \n"
			+ "  vTextureCoord = aTextureCoord;" + "}";

	/** 片着色器 **/
	private final String fragmentShaderCode =
            "precision mediump float;" +
            "varying vec2 vTextureCoord;" +
            "uniform sampler2D sTexture;" + "void main() {" +
            "  gl_FragColor = texture2D(sTexture, vTextureCoord);" + "}";
}
