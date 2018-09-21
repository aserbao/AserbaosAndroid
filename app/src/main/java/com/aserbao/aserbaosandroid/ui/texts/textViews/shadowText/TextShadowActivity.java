package com.aserbao.aserbaosandroid.ui.texts.textViews.shadowText;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.LinearColorPicker;
import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextShadowActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.tbi_tv_color)
    TextView mTbiTvColor;
    @BindView(R.id.linear_color_picker)
    LinearColorPicker mLinearColorPicker;
    @BindView(R.id.linear_color_text_color_picker)
    LinearColorPicker mLinearColorTextColorPicker;
    @BindView(R.id.tbi_tv_radius)
    TextView mTbiTvRadius;
    @BindView(R.id.text_bar_item_seek_bar_radius)
    SeekBar mTextBarItemSeekBarRadius;
    @BindView(R.id.tbi_tv_dx)
    TextView mTbiTvDx;
    @BindView(R.id.text_bar_item_seek_bar_dx)
    SeekBar mTextBarItemSeekBarDx;
    @BindView(R.id.tbi_tv_dy)
    TextView mTbiTvDy;
    @BindView(R.id.text_bar_item_seek_bar_dy)
    SeekBar mTextBarItemSeekBarDy;
    @BindView(R.id.tbi_tv_alpha)
    TextView mTbiTvAlpha;
    @BindView(R.id.text_bar_item_seek_bar_alpha)
    SeekBar mTextBarItemSeekBarAlpha;
    @BindView(R.id.tbi_tv_elevation)
    TextView mTbiTvElevation;
    @BindView(R.id.text_bar_item_seek_bar_elevation)
    SeekBar mTextBarItemSeekBarElevation;
    @BindView(R.id.tbi_tv_rotate)
    TextView mTbiTvRotate;
    @BindView(R.id.text_bar_item_seek_bar_rotatex)
    SeekBar mTextBarItemSeekBarRotateX;
    @BindView(R.id.text_bar_item_seek_bar_rotatey)
    SeekBar mTextBarItemSeekBarRotateY;
    @BindView(R.id.tv_diaplay)
    TextView mTvDiaplay;
    @BindView(R.id.tbi_tv_size)
    TextView mTbiTvSize;
    @BindView(R.id.text_bar_item_seek_bar_text_size)
    SeekBar mTextBarItemSeekBarTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_bar_item);
        ButterKnife.bind(this);
        initData();
        mTextBarItemSeekBarRadius.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarDx.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarDy.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarAlpha.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarRotateX.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarRotateY.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarTextSize.setOnSeekBarChangeListener(this);
        mTextBarItemSeekBarElevation.setOnSeekBarChangeListener(this);
        mLinearColorPicker.setOnColorSelectListener(new LinearColorPicker.OnColorSelectListener() {
            @Override
            public void onColorSelect(int color, int progress) {
                mShadowColor = color;
                changeText(mShadowColor, mRadius, mdx, mdy, mAlpha, mEveation, mRotateX, mRotateY);
            }
        });
        mLinearColorTextColorPicker.setOnColorSelectListener(new LinearColorPicker.OnColorSelectListener() {
            @Override
            public void onColorSelect(int color, int progress) {
                mTextColor = color;
                changeText(mShadowColor, mRadius, mdx, mdy, mAlpha, mEveation, mRotateX, mRotateY);
            }
        });
        mTvDiaplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadius = 0;mdx = 0;mdy = 0; mShadowColor = Color.YELLOW; mRotateX = 0; mRotateY = 0; mTextColor = Color.BLACK;mTextSize = 25;
                changeText(mShadowColor, mRadius, mdx, mdy, mAlpha, mEveation, mRotateX, mRotateY);
            }
        });
    }


    public int mRadius = 0, mdx = 0, mdy = 0, mShadowColor = Color.YELLOW, mEveation, mRotateX = 0, mRotateY = 0, mTextColor = Color.BLACK,mTextSize = 25;
    private float mAlpha = 1;
    private List<TextView> mListView = new ArrayList<>();


    private void initData() {
        mListView.add(mTbiTvColor);
        mListView.add(mTbiTvDx);
        mListView.add(mTbiTvDy);
        mListView.add(mTbiTvElevation);
        mListView.add(mTbiTvAlpha);
        mListView.add(mTbiTvSize);
        mListView.add(mTbiTvRadius);
        mListView.add(mTbiTvRotate);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.text_bar_item_seek_bar_radius:
                mRadius = progress;
                mTbiTvRadius.setText("Radius = " + progress);
                break;
            case R.id.text_bar_item_seek_bar_dx:
                mTbiTvDx.setText("Dx = " + mdx);
                mdx = progress;
                break;
            case R.id.text_bar_item_seek_bar_dy:
                mTbiTvDy.setText("Dy = " + progress);
                mdy = progress;
                break;
            case R.id.text_bar_item_seek_bar_alpha:
                float v = (float) progress / (float) 100;
                mAlpha = v;
                mTbiTvAlpha.setText("Alpha = " + v);
                break;
            case R.id.text_bar_item_seek_bar_elevation:
                break;
            case R.id.text_bar_item_seek_bar_rotatex:
                mTbiTvRotate.setText("rotateX = " +mRotateX + "rotateY = " + mRotateY );
                mRotateX = progress;
                break;
            case R.id.text_bar_item_seek_bar_rotatey:
                mTbiTvRotate.setText("rotateX = " +mRotateX + "rotateY = " + mRotateY );
                mRotateY = progress;
                break;
            case R.id.text_bar_item_seek_bar_text_size:
                mTextSize = progress;
                break;
        }
        changeText(mShadowColor, mRadius, mdx, mdy, mAlpha, mEveation, mRotateX, mRotateY);
    }

    public void changeViewText(TextView textView,int value){
        textView.setText(textView.getText() + String.valueOf(value));
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void changeText(int color, int radius, int dx, int dy, float alpha, int evelation, int rotateX, int rotateY) {
        for (TextView textView : mListView) {
            textView.setShadowLayer(radius, dx, dy, color);
            textView.setAlpha(alpha);
            textView.setRotationY(rotateX);
            textView.setRotationY(rotateY);
            textView.setTextSize(mTextSize);
            textView.setTextColor(mTextColor);
        }

        String result = "shadowColor : " + String.valueOf(color) + "\n" +
                "dx : " + String.valueOf(dx) + "\n" +
                "dy : " + String.valueOf(dy) + "\n" +
                "alpha : " + String.valueOf(alpha) + "\n" +
                "radius : " + String.valueOf(radius) + "\n" +
                "rotateX : " + String.valueOf(rotateX) + "\n" +
                "rotateY : " + String.valueOf(rotateY) + "\n" +
                "mTextColor : " + String.valueOf(mTextColor) + "\n" +
                "mTextSize : " + String.valueOf(mTextSize);
        ;
        mTvDiaplay.setText(result);
    }


}
