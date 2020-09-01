package com.aserbao.aserbaosandroid.opengl.OneOpenGl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.aserbao.aserbaosandroid.opengl.OneOpenGl.gles.SummaryRenderer;

/**
 * Created by aserbao on 2018 2018/1/16.23:11
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class TwoGlSurfaceView extends GLSurfaceView {
    private final SummaryRenderer mRenderer;
    public TwoGlSurfaceView(Context context) {
        super(context);
        // 创建一个 OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new SummaryRenderer(context);

        // 给GLSurfaceView设置渲染器
        setRenderer(mRenderer);

        // 只在绘图数据发生变化时才渲染视图。
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

}
