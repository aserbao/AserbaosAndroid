package com.aserbao.aserbaosandroid.ui.animation.baseAnimation.tweenAnimator;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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

    View mView;
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Translate",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Scale",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Rotate",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Alpha",3));


        mView = LayoutInflater.from(mContext).inflate(R.layout.animator_layout, null);
        addViewToFrameLayout(mView);
    }



    @Override
    public void itemClickBack(View view, int position) {
        Animation animation = imageView.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        switch (position){
            case 101:
                /*BaserRecyclerViewManager aPopupManager = new BaserRecyclerViewManager(this, this);
                ASourceUtil.getAllInterpoator(aPopupManager.mBaseRecyclerBeen);
                aPopupManager.showBottomRecyclerViewPop(new IBaseRecyclerItemClickListener() {
                    @Override
                    public void itemClickBack(View view, int position) {
                        interpolator = ASourceUtil.getCuurSelectedInterpolator(position);
                        if (aPopupManager != null) {
                            aPopupManager.dismiss();
                        }
                    }
                });*/
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
        mView.startAnimation(translateAnimation);
    }


    public void useScaleAnimation(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        mView.startAnimation(scaleAnimation);
    }

    public void useRotateAnimation(){
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, 0, 0);
        rotateAnimation.setInterpolator(interpolator);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        mView.startAnimation(rotateAnimation);
    }


    public void userAlphaAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.8f);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(500);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        mView.startAnimation(alphaAnimation);
    }

    Interpolator interpolator = new AccelerateInterpolator();
    int dutation = 300;
    int repeatCount = -1;
    int repeatMode = Animation.REVERSE;
    boolean fillAfter = false; //默认为false,如果为True,动画结束前保持动画结束的transformation不变
    boolean fillBefore = true; //默认为true,若为false。


    public void configureAnimation(Animation animation){
        animation.setInterpolator(interpolator);
        animation.setDuration(dutation);
        animation.setRepeatCount(repeatCount);
        animation.setRepeatMode(repeatMode);
        animation.setFillAfter(fillAfter);
        animation.setFillBefore(fillBefore);
    }


}
