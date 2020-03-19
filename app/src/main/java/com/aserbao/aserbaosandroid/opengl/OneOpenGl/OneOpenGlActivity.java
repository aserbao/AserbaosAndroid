package com.aserbao.aserbaosandroid.opengl.OneOpenGl;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class OneOpenGlActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneGlSurfaceView glSurfaceView = new OneGlSurfaceView(this);
        setContentView(glSurfaceView);
    }
}
