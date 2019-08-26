package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.transition;

import android.graphics.Path;
import android.os.Bundle;
import android.support.transition.ChangeBounds;
import android.support.transition.Explode;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.Slide;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionActivity extends AppCompatActivity {

    @BindView(R.id.transition_fl_container)
    FrameLayout mTransitionFlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);
    }

    int selected_position = 1;

    @OnClick({R.id.add_target_btn,R.id.transition_change_bound_btn, R.id.transition_slide_btn, R.id.transition_fade_btn, R.id.transition_explore_btn})
    public void onViewClicked(View view) {
        int start_layout = R.layout.start_layout1;
        switch (selected_position % 3) {
            case 0:
                start_layout = R.layout.start_layout1;
                break;
            case 1:
                start_layout = R.layout.start_layout2;
                break;
            case 2:
                start_layout = R.layout.start_layout3;
                break;
        }
        selected_position++;
        Scene sceneForLayout = Scene.getSceneForLayout(mTransitionFlContainer, start_layout, this);
        switch (view.getId()) {
            case R.id.transition_change_bound_btn:
                ChangeBounds transition = new ChangeBounds();
                transition.setDuration(1000);
                Path path = new Path();
                path.lineTo(0.25f, 0.25f);
                path.moveTo(0.25f, 0.5f);
                path.lineTo(1f, 1f);
                transition.setInterpolator(new PathInterpolator(path));
                TransitionManager.go(sceneForLayout, transition);
                break;
            case R.id.transition_slide_btn:
                TransitionManager.go(sceneForLayout, new Slide());
                break;
            case R.id.transition_fade_btn:
                TransitionManager.go(sceneForLayout, new Fade());
                break;
            case R.id.transition_explore_btn:
                TransitionManager.go(sceneForLayout, new Explode());
                break;
            case R.id.add_target_btn:
                ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.addTarget(R.id.image1);
                changeBounds.setDuration(1000);
                TransitionManager.go(sceneForLayout, changeBounds);
                break;
        }
    }
}
