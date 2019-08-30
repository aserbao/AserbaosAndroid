package com.aserbao.aserbaosandroid.ui.animation.baseAnimation.tweenAnimator;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aa.BaserRecyclerViewManager;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;

public class TweenAnimatorActivity extends BaseRecyclerViewActivity {

    private ImageView imageView,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选择插值器",101));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Translate",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Scale",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Rotate",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Alpha",3));

        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.LEFT;
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.emoji_00);
        imageView.setLayoutParams(layoutParams);
        mBaseRecyclerEmptyContainer.addView(imageView);



        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(DisplayUtil.dp2px(100),DisplayUtil.dp2px(100));
        layoutParams2.gravity = Gravity.CENTER;
        imageView2 = new ImageView(this);
        imageView2.setImageResource(ASourceUtil.getRandomImageId());
        imageView2.setLayoutParams(layoutParams2);
        mBaseRecyclerEmptyContainer.addView(imageView2);



        mCanvasPointView.setVisibility(View.VISIBLE);
        mBaseRecyclerTv.setText("TweenAnimation");
    }


    Interpolator interpolator = new AccelerateInterpolator();
    @Override
    public void itemClickBack(View view, int position) {
        Animation animation = imageView.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        switch (position){
            case 101:
                BaserRecyclerViewManager aPopupManager = new BaserRecyclerViewManager(this, this);
                ASourceUtil.getAllInterpoator(aPopupManager.mBaseRecyclerBeen);
                aPopupManager.showBottomRecyclerViewPop(new IBaseRecyclerItemClickListener() {
                    @Override
                    public void itemClickBack(View view, int position) {
                        interpolator = ASourceUtil.getCuurSelectedInterpolator(position);
                        if (aPopupManager != null) {
                            aPopupManager.dismiss();
                        }
                    }
                });
                break;
            case 0:
                useTranslateAnimation();
                break;
            case 1:
                useScaleAnimation();
                break;
            case 2:
                useRotateAnimation();
                break;
            case 3:
                userAlphaAnimation();
                break;
        }
    }


    public void useTranslateAnimation(){
        float x = mBaseRecyclerTv.getX();
        float y = mBaseRecyclerTv.getY();
        Point point = new Point((int)x,(int)y);
        mCanvasPointView.addPoint(point);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0,0,200);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setDuration(500);
        mBaseRecyclerTv.startAnimation(translateAnimation);
    }


    public void useScaleAnimation(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(scaleAnimation);
    }

    public void useRotateAnimation(){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, 0, 0);
        rotateAnimation.setInterpolator(interpolator);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        imageView2.startAnimation(rotateAnimation);
    }


    public void userAlphaAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.8f);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(500);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(alphaAnimation);
    }


}
