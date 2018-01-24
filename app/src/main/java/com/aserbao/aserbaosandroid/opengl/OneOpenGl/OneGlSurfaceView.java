package com.aserbao.aserbaosandroid.opengl.OneOpenGl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by aserbao on 2018 2018/1/16.23:11
 * Email:1142803753@qq.com
 * weixin: aserbao
 */

public class OneGlSurfaceView extends GLSurfaceView {
    private final OneGlRenderer mRenderer;
    public OneGlSurfaceView(Context context) {
        super(context);
        // 创建一个 OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new OneGlRenderer();

        // 给GLSurfaceView设置渲染器
        setRenderer(mRenderer);

        // 只在绘图数据发生变化时才渲染视图。
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}
