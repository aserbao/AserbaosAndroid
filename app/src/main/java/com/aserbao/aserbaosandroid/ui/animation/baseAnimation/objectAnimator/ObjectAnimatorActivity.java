package com.aserbao.aserbaosandroid.ui.animation.baseAnimation.objectAnimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ObjectAnimatorActivity extends BaseRecyclerViewActivity {

    private ObjectAnimator objectAnimator;


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("PathInterpolator",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("取消",1));
        mBaseRecyclerTv.setText("动画");
        mBaseRecyclerTv.setTextColor(Color.WHITE);
        mBaseRecyclerTv.setBackgroundColor(Color.BLACK);

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
                userPathInterPolator();
                break;
            case 1:
                cancelAnimator();
                break;
        }
    }

    public void userPathInterPolator(){
//      PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("translationX", 0.0f, 300.0f);
      PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f);
      PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f);
//      PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX", 0.0f, 90.0f, 0.0F);
//        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("translationX", 100f);

//      ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBaseRecyclerTv, valuesHolder, valuesHolder1, valuesHolder2, valuesHolder3);
        mBaseRecyclerTv.setPivotX(0);
        mBaseRecyclerTv.setPivotY(0);
        mBaseRecyclerTv.invalidate();

        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBaseRecyclerTv, valuesHolder2,valuesHolder1);
        objectAnimator.setDuration(300);
        objectAnimator.setRepeatCount(100*10000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    public void cancelAnimator(){
        objectAnimator.cancel();
    }
}
