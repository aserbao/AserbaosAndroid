package com.aserbao.aserbaosandroid.ui.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    MatrixView mMatrixView;
    @BindView(R.id.matrix_iv)
    ImageView mMatrixIv;
    @BindView(R.id.matrix_container)
    FrameLayout mMatrixContainer;

    @BindView(R.id.test_matrix_iv)
    ImageView mTestMatrixIv;

    private List l = new ArrayList();
    private int mTranslateX, mTranslateY;

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
                mMatrixView.setScaleX(mScale);
                mMatrixView.setScaleY(mScale);
                mTestMatrixIv.setScaleX(mScale);
                mTestMatrixIv.setScaleY(mScale);
//                mMatrixView.setmScale(mScale);
//                mMatrix.postScale(mScale, mScale);
//                mMatrixIv.setImageBitmap(createBitmap());
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
//                mMatrixView.setmRotateDegress(progress);
                mMatrixView.setRotation(progress);
                mTestMatrixIv.setRotation(progress);
//                mMatrix.postRotate(progress);
//                mMatrixIv.setImageBitmap(createBitmap());
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
//                mMatrixView.setmTranslateX(progress);
                mMatrixView.setTranslationX(progress);
                mTestMatrixIv.setTranslationX(progress);
                mTranslateX = progress;
//                mMatrix.postTranslate(mTranslateX, mTranslateY);
//                mMatrixIv.setImageBitmap(createBitmap());
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
//                mMatrixView.setmTranslateY(progress);
                mMatrixView.setTranslationY(progress);
                mTestMatrixIv.setTranslationY(progress);
                mTranslateY = progress;
//                mMatrix.postTranslate(mTranslateX, mTranslateY);
//                mMatrixIv.setImageBitmap(createBitmap());
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

    public Bitmap createBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
        return bitmap1;
    }

    private static final String TAG = "MatrixActivity";

    @OnClick({R.id.get_matrix_btn, R.id.get_matrix_btn2})
    public void onViewClicked() {

        if (mMatrixView != null) {

        }
    }

    @OnClick({R.id.get_matrix_btn, R.id.get_matrix_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_matrix_btn:
                Matrix matrix = mMatrixView.getMatrix();
                Log.e(TAG, "onViewClicked: " + matrix.toString());
                Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.img_dancebase_other);
//            int width = mMatrixView.getWidth();
//            int height = mMatrixView.getHeight();
                int width = mMatrixContainer.getWidth();
                int height = mMatrixContainer.getHeight();
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
                Log.e(TAG, "onViewClicked: width  = " + width + " height =" + height);
                final Canvas canvas = new Canvas();

                int x = (canvas.getWidth() - bitmap.getWidth()) / 2;
                int y = (canvas.getHeight() - bitmap.getHeight()) / 2;
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                canvas.setMatrix(matrix);
                canvas.drawBitmap(bitmap, x,y, paint);


//                canvas.drawBitmap(bitmapSrc, matrix, new Paint());
                mMatrixIv.setImageBitmap(bitmap);
                break;
            case R.id.get_matrix_btn2:
                Matrix matrix2 = mTestMatrixIv.getMatrix();
                Bitmap bitmapSrc2 = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
//            int width = mMatrixView.getWidth();
//            int height = mMatrixView.getHeight();
                int width2 = mMatrixContainer.getWidth();
                int height2 = mMatrixContainer.getHeight();
                Bitmap bitmap2 = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_4444);
                Log.e(TAG, "onViewClicked: width  = " + width2 + " height =" + height2);
                final Canvas canvas2 = new Canvas(bitmap2);
                canvas2.drawBitmap(bitmapSrc2, matrix2, new Paint());
                mMatrixIv.setImageBitmap(bitmap2);
                break;
        }
    }
}
