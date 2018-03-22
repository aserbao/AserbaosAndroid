package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用SurfaceView预览Camera数据
 */
public class CameraSurfaceViewShowActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    @BindView(R.id.mSurface)
    SurfaceView mSurfaceView;

    public SurfaceHolder mHolder;
    private Camera mCamera;
    private Camera.Parameters mParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_camera);
        ButterKnife.bind(this);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // Open the Camera in preview mode
            mCamera = Camera.open(1);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        /*mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mParameters = mCamera.getParameters();
                    mParameters.setPictureFormat(PixelFormat.JPEG); //图片输出格式
                    mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//预览持续发光
                    mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//持续对焦模式
                    mCamera.setParameters(mParameters);
                    mCamera.startPreview();
                    mCamera.cancelAutoFocus();
                }
            }
        });*/
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        /*mSurfaceView.setScaleX(0.5f);
        mSurfaceView.setScaleY(0.5f);
        mSurfaceView.setRotation(50);
        mSurfaceView.setTranslationX(10);*/
//        mSurfaceView.setAlpha(0.9f); //设置透明度SurfaceView将绘制不出来
        //SurfaceView不支持透明度动画效果
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 90.0f, 0.0F);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX", 0.0f, 90.0f, 0.0F);
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f);
        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f);
//        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.3f, 1.0F);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mSurfaceView, valuesHolder, valuesHolder1, valuesHolder2,valuesHolder3);
        objectAnimator.setDuration(2000).start();
    }
}
