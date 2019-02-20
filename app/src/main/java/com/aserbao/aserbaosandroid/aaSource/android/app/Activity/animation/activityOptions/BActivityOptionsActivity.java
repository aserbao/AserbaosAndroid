package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BActivityOptionsActivity extends AppCompatActivity {

    @BindView(R.id.animation_pending_top_btn)
    Button mAnimationPendingTopBtn;
    @BindView(R.id.animation_pending_left_btn)
    Button mAnimationPendingLeftBtn;
    @BindView(R.id.animation_pending_right_btn)
    Button mAnimationPendingRightBtn;
    @BindView(R.id.animation_pending_bottom_btn)
    Button mAnimationPendingBottomBtn;
    @BindView(R.id.animation_tv)
    TextView mAnimationTv;
    @BindView(R.id.animation_extra_one_btn)
    Button mAnimationExtraOneBtn;
    @BindView(R.id.animation_extra_two_btn)
    Button mAnimationExtraTwoBtn;
    @BindView(R.id.animation_extra_three_btn)
    Button mAnimationExtraThreeBtn;
    @BindView(R.id.animation_extra_four_btn)
    Button mAnimationExtraFourBtn;

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, BActivityOptionsActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, type);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
    }




}
