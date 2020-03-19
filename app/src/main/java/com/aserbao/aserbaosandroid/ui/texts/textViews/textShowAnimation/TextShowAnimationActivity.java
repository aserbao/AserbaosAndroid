package com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.animTextView.FlyTxtView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.animTextView.ShadeAnimTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.HTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.evaporate.EvaporateTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.fade.FadeTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.fall.FallTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.line.LineTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.rainbow.RainbowTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.scale.ScaleTextView;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.typer.TyperTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextShowAnimationActivity extends AppCompatActivity {
    String mText = "";
    int mTextColor = Color.GRAY;
    int mTextSize = 16;


    @BindView(R.id.anim_fly_txt_view)
    FlyTxtView mAnimFlyTxtView;
    @BindView(R.id.anim_shade_txt_view)
    ShadeAnimTextView mAnimShadeTxtView;
    @BindView(R.id.fade_text_view)
    FadeTextView mFadeTextView;
    @BindView(R.id.evapotate_text_view)
    EvaporateTextView mEvapotateTextView;
    @BindView(R.id.evaporate_txt_view_2)
    EvaporateTextView mEvapotateTextView2;
    @BindView(R.id.fall_text_view)
    FallTextView mFallTextView;
    @BindView(R.id.line_text_view)
    LineTextView mLineTextView;
    @BindView(R.id.rainbow_text_view)
    RainbowTextView mRainbowTextView;
    @BindView(R.id.scale_text_view)
    ScaleTextView mScaleTextView;
    @BindView(R.id.typer_text_view)
    TyperTextView mTyperTextView;
    @BindView(R.id.text_show_seek_bar)
    AppCompatSeekBar mTextShowSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_show_animation);
        ButterKnife.bind(this);
        mText = getString(R.string.show_text);
    }

    private void init() {
        initTxtShow();
        initListener();
    }

    private void initTxtShow() {
        initFlyTxtView();
        initShadeTextView();
        mFadeTextView.animateText(mText);
        mEvapotateTextView.animateText(mText);
        mEvapotateTextView.animateText(getString(R.string.show_text2));
        mLineTextView.animateText(mText);
        mRainbowTextView.animateText(mText);
//        setStyle(mScaleTextView);
        setStyle(mTyperTextView);
    }

    private void initListener(){
        mTextShowSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mFadeTextView.setProgress(progress/100f);
                mEvapotateTextView.setProgress(progress/100f);
                mFallTextView.setProgress(progress/100f);
                mLineTextView.setProgress(progress/100f);
                mRainbowTextView.setProgress(progress/100f);
                mScaleTextView.setProgress(progress/100f);
                mTyperTextView.setProgress(progress/100f);
                mEvapotateTextView2.setProgress(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initFlyTxtView() {
        mAnimFlyTxtView.setTextSize(mTextSize);
        mAnimFlyTxtView.setTextColor(mTextColor);
        mAnimFlyTxtView.setTexts(mText);
        mAnimFlyTxtView.startAnimation();
    }

    public void initShadeTextView() {
        mAnimShadeTxtView.setAnimating(true);
        mAnimShadeTxtView.setText(mText);
    }


    public void setStyle( HTextView textView) {
//        textView.setTextColor(mTextColor);
//        textView.setTextSize(mTextSize);
        textView.animateText(mText);
    }

    @OnClick(R.id.anim_start_btn)
    public void onViewClicked() {
        init();
    }


}
