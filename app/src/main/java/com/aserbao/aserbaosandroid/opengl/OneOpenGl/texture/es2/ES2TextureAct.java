package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture.es2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

public class ES2TextureAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.es_texture_layout);
	}
	
	public void showTriangle(View view){
		startOpenGl(0);
	}
	
	public void showSquare(View view){
		startOpenGl(1);
	}

	public void startOpenGl(int flag){
		Intent intent = new Intent();
		intent.putExtra("flag", flag);
		intent.setClass(ES2TextureAct.this, OpenGL2TextureAct.class);
		startActivity(intent);
	}
}
