package com.aserbao.aserbaosandroid.ui.animation.objectAnimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.PathInterpolator;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ObjectAnimatorActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("PathInterpolator",0));
        mBaseRecyclerTv.setText("动画");
        mBaseRecyclerTv.setTextColor(Color.WHITE);
        mBaseRecyclerTv.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                userPathInterPolator();
                break;
        }
    }

    public void userPathInterPolator(){
//        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("translationX", 0.0f, 300.0f);
//        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
//        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX", 0.0f, 90.0f, 0.0F);
        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("translationX", 100f);

//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBaseRecyclerTv, valuesHolder, valuesHolder1, valuesHolder2, valuesHolder3);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBaseRecyclerTv, valuesHolder3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
            ObjectAnimator animator = ObjectAnimator.ofFloat(mBaseRecyclerTv, View.X, View.Y, path);
            animator.setDuration(2000);
            animator.start();
        }
//        objectAnimator.setDuration(2000).start();
    }
}
