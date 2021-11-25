package com.example.base.utils.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.base.utils.log.ALogUtils;

/**
 * @Created time:2021/10/22 5:40 下午
 * @author: aserbao
 * @description: 关于视图的一些工具
 **/
public class AViewUtils {

    public static long lastClickTime = 0;

    /**
     * 视图快速点击判断
     * @return
     */
    public static boolean isFastClick(){
        long timeMillis = System.currentTimeMillis();
        boolean result = true;
        if (timeMillis - lastClickTime > 100){
            result =  false;
        }
        lastClickTime = timeMillis;
        return result;
    }


    static final int animationDuration = 250;
    /**
     * 带动画效果的显示和消失
     * @param view
     * @param visibility
     */
    public static void setAnimationVisibility(View view, int visibility){
        if(view == null || view.getVisibility() == visibility) return;
        switch (visibility){
            case View.INVISIBLE:
            case View.GONE:
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0);
                alphaAnimation.setDuration(animationDuration);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (view != null) {
                            view.setVisibility(visibility);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                view.startAnimation(alphaAnimation);
                break;
            case View.VISIBLE:
                AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f,1.0f);
                alphaAnimation1.setDuration(animationDuration);
                alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if (view != null) {
                            view.setVisibility(visibility);
                        }
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {}

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                view.startAnimation(alphaAnimation1);
                break;
            default:
                ALogUtils.d("CommonObsImpl", "setVisibility: 使用出错，请检查");
        }
    }


    /**
     * 底部往上显示
     * @param view
     * @param visibility
     */
    public static void bottomToCenterVisibility(View view, int visibility){
        if(view == null || view.getVisibility() == visibility) return;
        float height = view.getHeight();
        float startValue = 0,endValue = height;
        if(visibility == View.VISIBLE){
            view.setVisibility(View.VISIBLE);
            startValue = height;
            endValue = 0;
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat( view, "translationY",  startValue, endValue);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(visibility);
                view.requestLayout();
            }
        });
        animator.setDuration(animationDuration);
        animator.start();
    }

}
