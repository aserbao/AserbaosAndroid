package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView.ShowDecodeGlSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecodeVideoShowGlSurfaceViewActivity extends AppCompatActivity  {

    @BindView(R.id.gl_surface_view)
    ShowDecodeGlSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gl_surface_view_layout);
        ButterKnife.bind(this);


    }
}
