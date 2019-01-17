package com.aserbao.aserbaosandroid.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: 1142803753@qq.com
 */
public class TestActivity extends AppCompatActivity {
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
    private List l = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        mScaleSb.setMax(100);
        mScaleSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTestView.setmScale(progress/(float)100);
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
