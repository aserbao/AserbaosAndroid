package com.aserbao.aserbaosandroid.ui.animation.viewAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.screen.DisplayUtil;

/**
 * @Created time:2021/6/8 4:11 PM
 * @author: aserbao
 * @description:
 **/
public class SwitchAnimationView extends LinearLayout{
    public static final int ANIMATION_DURATION  = 500;

    private ImageView mImageView;
    private int minWidth = DisplayUtil.dip2px(40);
    private int maxWidth = DisplayUtil.dip2px(100);
    private int centerIVWidth = DisplayUtil.dip2px(24);
    private int minLeftMargin = DisplayUtil.dip2px(4);
    private TextView mTextView;


    private ValueAnimator animatorBack;

    public SwitchAnimationView(Context context) {
        super(context);
        initView(context);
    }

    public SwitchAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SwitchAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SwitchAnimationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context){
        mImageView = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(centerIVWidth, centerIVWidth);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        mImageView.setLayoutParams(layoutParams);
        mImageView.setImageResource(R.drawable.emoji_00);
        layoutParams.leftMargin = (minWidth - centerIVWidth)/2;
        addView(mImageView,layoutParams);

        mTextView = new TextView(context);
        mTextView.setText("半身驱动");
        mTextView.setTextSize(12);
        mTextView.setSingleLine(true);
        mTextView.setTextColor(Color.BLACK);
        LayoutParams textViewLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textViewLP.leftMargin = minLeftMargin;
        textViewLP.rightMargin = minLeftMargin;
        textViewLP.gravity = Gravity.CENTER_VERTICAL;
        addView(mTextView,textViewLP);

        setBackgroundResource(R.drawable.solid_6b7075_radius16_bg);

        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animBack();
            }
        });
    }

    //背景伸展动画
    public void animBack() {
        animatorBack = ValueAnimator.ofInt(minWidth, maxWidth);
        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                otherAnimation(animation,true);
                ViewGroup.LayoutParams params = getLayoutParams();
                params.width = animatedValue;
                setLayoutParams(params);
            }
        });
        animatorBack.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setBackUp();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mImageView.setClickable(false);
            }
        });
        animatorBack.setDuration(ANIMATION_DURATION);
        animatorBack.start();
    }

    //背景收回动画
    public void setBackUp() {
        animatorBack = ValueAnimator.ofInt(maxWidth, minWidth);
        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = getLayoutParams();
                params.width = animatedValue;
                setLayoutParams(params);
                otherAnimation(animation,false);

            }
        });
        animatorBack.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mImageView.setClickable(true);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
                mImageView.setClickable(true);
            }
        });
        animatorBack.setDuration(ANIMATION_DURATION);
        animatorBack.start();
    }


    private void otherAnimation(ValueAnimator animation, boolean isOpen){

        long currentPlayTime = animation.getCurrentPlayTime();
        long totalDuration = animation.getDuration();
        float progress = (float)currentPlayTime / (float)totalDuration;
        float alpha = 1.0f,rotation = 0;

        if(isOpen){
            rotation = currentPlayTime;
            alpha = progress;
        }else{
            rotation = ANIMATION_DURATION - currentPlayTime;
            alpha = (float) rotation / (float)totalDuration;
        }
        mImageView.setRotation(rotation);

        mTextView.setAlpha(alpha);

    }

}
