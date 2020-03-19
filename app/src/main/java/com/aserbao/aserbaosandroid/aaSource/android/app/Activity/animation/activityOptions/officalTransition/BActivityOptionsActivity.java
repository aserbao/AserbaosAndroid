package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.officalTransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import butterknife.ButterKnife;

public class BActivityOptionsActivity extends AppCompatActivity {


    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, BActivityOptionsActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, type);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);//特别注意：此方法必须在setContentView之前调用，否则会报错
//        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.custom_explode);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
    }




}
