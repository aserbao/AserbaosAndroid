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
       /* Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);*/
        overridePendintAnimation(getIntent().getIntExtra(StaticFinalValues.TYPE, 0));
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
        mAnimationTv.setText("B");
        mAnimationPendingTopBtn.setText("slide_top");
        mAnimationPendingBottomBtn.setText("slide_bottom");
        mAnimationPendingLeftBtn.setText("slide_left");
        mAnimationPendingRightBtn.setText("slide_right");

        mAnimationExtraOneBtn.setVisibility(View.VISIBLE);
        mAnimationExtraOneBtn.setText("explode");
        mAnimationExtraTwoBtn.setVisibility(View.VISIBLE);
        mAnimationExtraTwoBtn.setText("fade");
        mAnimationExtraThreeBtn.setVisibility(View.VISIBLE);
        mAnimationExtraThreeBtn.setText("move");
        mAnimationExtraFourBtn.setVisibility(View.VISIBLE);
        mAnimationExtraFourBtn.setText("no_transition");
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

    @OnClick({R.id.animation_pending_top_btn, R.id.animation_pending_left_btn, R.id.animation_pending_right_btn, R.id.animation_pending_bottom_btn,
        R.id.animation_extra_one_btn, R.id.animation_extra_two_btn,R.id.animation_extra_three_btn, R.id.animation_extra_four_btn})
    public void onViewClicked(View view) {
        int type = 0;
        switch (view.getId()) {
            case R.id.animation_pending_left_btn:
                type = StaticFinalValues.LEFT;
                break;
            case R.id.animation_pending_top_btn:
                type = StaticFinalValues.TOP;
                break;
            case R.id.animation_pending_right_btn:
                type = StaticFinalValues.RIGHT;
                break;
            case R.id.animation_pending_bottom_btn:
                type = StaticFinalValues.BOTTOM;
                break;
            case R.id.animation_extra_one_btn:
                type = StaticFinalValues.EXPLORE;
                break;
            case R.id.animation_extra_two_btn:
                type = StaticFinalValues.FADE;
                break;
            case R.id.animation_extra_three_btn:
                type = StaticFinalValues.MOVE;
                break;
            case R.id.animation_extra_four_btn:
                type = StaticFinalValues.NO_TRANSITION;
                break;
        }
        AActivityOptionsActivity.launch(this, type);
    }


}
