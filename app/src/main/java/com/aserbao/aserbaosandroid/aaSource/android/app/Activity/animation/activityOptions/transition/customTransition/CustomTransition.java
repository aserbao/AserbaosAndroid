package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.transition.customTransition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.aserbao.aserbaosandroid.AserbaoApplication;

public class CustomTransition extends Transition {

    // Define a key for storing a property value in
    // TransitionValues.values with the syntax
    // package_name:transition_class:property_name to avoid collisions
    private static final String PROPNAME_BACKGROUND =
        "com.example.android.customtransition:CustomTransition:background";

    private static final String TAG = "CustomTransition";
    @Override
    public void captureStartValues(TransitionValues values) {
        // Call the convenience method captureValues
        captureValues(values);
        Log.e(TAG, "captureStartValues: " + values.toString() );
    }

    @Override
    public void captureEndValues(TransitionValues values) {
        Log.e(TAG, "captureEndValues: " + values.values );
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot,
                                   TransitionValues startValues,
                                   TransitionValues endValues) {

            Path path = new Path();
            path.moveTo(0,0);
            int screenWidth = AserbaoApplication.screenWidth/2;
            int screenHeight = AserbaoApplication.screenHeight/2;
            path.lineTo(screenWidth /2, screenHeight);
            path.lineTo(screenWidth,screenHeight/2);
    //                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
            ObjectAnimator animator = ObjectAnimator.ofFloat(sceneRoot, View.X, View.Y, path);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(2000);
            return animator;
    }

    // For the view in transitionValues.view, get the values you
    // want and put them in transitionValues.values
    private void captureValues(TransitionValues transitionValues) {
        // Get a reference to the view
        View view = transitionValues.view;
        // Store its background property in the values map
        transitionValues.values.put(PROPNAME_BACKGROUND, view.getBackground());
    }
}