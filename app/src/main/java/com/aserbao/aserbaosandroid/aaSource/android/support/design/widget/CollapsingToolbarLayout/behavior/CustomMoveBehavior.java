package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CollapsingToolbarLayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CustomMoveBehavior extends CoordinatorLayout.Behavior<TextView> {

    private int width;

    public CustomMoveBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //告知监听的dependency是Button
        return dependency instanceof Button;
    }

    @Override
    //当 dependency(Button)变化的时候，可以对child(TextView)进行操作
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setX(dependency.getX()+200);
        child.setY(dependency.getY()+200);
        child.setText(dependency.getX()+","+dependency.getY());

        return true;
    }
}
