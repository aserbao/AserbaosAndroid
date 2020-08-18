package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture.es2;

import android.graphics.Bitmap;

public abstract class Model {
	public abstract void draw(float[] mvpMatrix);
	public abstract void setTexture(Bitmap bitmap);
}
