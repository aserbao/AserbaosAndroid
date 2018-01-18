package com.aserbao.aserbaosandroid.opengl.OneOpenGl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by aserbao on 2018 2018/1/16.23:11
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class OneGlSurfaceView extends GLSurfaceView {
    private final OneGlRenderer mRenderer;
    public OneGlSurfaceView(Context context) {
        super(context);
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new OneGlRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}
