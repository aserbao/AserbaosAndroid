package com.aserbao.aserbaosandroid.ui.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
public class MatrixActivity extends AppCompatActivity {
    @BindView(R.id.rotate_sb)
    SeekBar mRotateSb;
    @BindView(R.id.translateY_sb)
    SeekBar mTranslateYSb;
    @BindView(R.id.translateX_sb)
    SeekBar mTranslateSb;
    @BindView(R.id.scale_sb)
    SeekBar mScaleSb;
    @BindView(R.id.test_view)
    MatrixView mTestView;
    @BindView(R.id.matrix_iv)
    ImageView mMatrixIv;

    private List l = new ArrayList();
    private int mTranslateX,mTranslateY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_matrix);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        mScaleSb.setMax(100);
        mScaleSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float mScale = progress / (float) 100;
                mTestView.setmScale(mScale);
                mMatrix.postScale(mScale,mScale);
                mMatrixIv.setImageBitmap(createBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mRotateSb.setMax(360);
        mRotateSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTestView.setmRotateDegress(progress);
                mMatrix.postRotate(progress);
                mMatrixIv.setImageBitmap(createBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mTranslateSb.setMax(AserbaoApplication.screenWidth);
        mTranslateSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTestView.setmTranslateX(progress);
                mTranslateX = progress;
                mMatrix.postTranslate(mTranslateX,mTranslateY);
                mMatrixIv.setImageBitmap(createBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mTranslateYSb.setMax(AserbaoApplication.screenHeight);
        mTranslateYSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTestView.setmTranslateY(progress);
                mTranslateY = progress;
                mMatrix.postTranslate(mTranslateX,mTranslateY);
                mMatrixIv.setImageBitmap(createBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    Matrix mMatrix = new Matrix();
    public Bitmap createBitmap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
        return bitmap1;
    }



}
