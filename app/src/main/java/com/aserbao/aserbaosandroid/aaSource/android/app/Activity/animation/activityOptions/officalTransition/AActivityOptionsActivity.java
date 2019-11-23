package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.officalTransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

public class AActivityOptionsActivity extends BaseRecyclerViewActivity {


    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, AActivityOptionsActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, type);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("slide_left"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("slide_top"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("slide_right"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("slide_bottom"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("explode"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("fade"));
        mBaseRecyclerTv.setText("A");
    }

    private void overridePendintAnimation(int type) {
        Transition transition = null;
        switch (type) {
            case StaticFinalValues.LEFT:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);
                break;
            case StaticFinalValues.TOP:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top);
                break;
            case StaticFinalValues.RIGHT:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
                break;
            case StaticFinalValues.BOTTOM:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
                break;
            case StaticFinalValues.EXPLORE:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
                break;
            case StaticFinalValues.FADE:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
                break;
            case StaticFinalValues.MOVE:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
                break;
            case StaticFinalValues.NO_TRANSITION:
                transition = TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition);
                break;
        }
        getWindow().setEnterTransition(transition);
    }
    @Override
    public void setTranslations(){
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        overridePendintAnimation(getIntent().getIntExtra(StaticFinalValues.TYPE, 0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        if (position == 4) {
            BActivityOptionsActivity.launch(this, position);
        }else{
            AActivityOptionsActivity.launch(this, position);
        }
    }
}
