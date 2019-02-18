package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BAnimationActivity extends AppCompatActivity {
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

    public static void launch(Activity activity, int type){
        Intent intent = new Intent(activity, BAnimationActivity.class);
        intent.putExtra(StaticFinalValues.TYPE,type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
        mAnimationTv.setText("B");
        mAnimationPendingTopBtn.setText("从上出现转到A");
        mAnimationPendingBottomBtn.setText("从下出现转到A");
        mAnimationPendingLeftBtn.setText("从左出现转到A");
        mAnimationPendingRightBtn.setText("从右出现转到A");
        overridePendintAnimation(getIntent().getIntExtra(StaticFinalValues.TYPE,0));
    }

    private void overridePendintAnimation(int type) {
        switch (type){
            case StaticFinalValues.LEFT:
                overridePendingTransition(R.anim.activity_left_to_screen_anim,R.anim.activity_screen_to_right_anim);
                break;
            case StaticFinalValues.TOP:
                overridePendingTransition(R.anim.activity_top_to_screen_anim,R.anim.activity_screen_to_bottom_anim);
                break;
            case StaticFinalValues.RIGHT:
                overridePendingTransition(R.anim.activity_right_to_screen_anim,R.anim.activity_screen_to_left_anim);
                break;
            case StaticFinalValues.BOTTOM:
                overridePendingTransition(R.anim.activity_bottom_to_screen_anim,R.anim.activity_screen_to_top_anim);
                break;
        }
    }
    @OnClick({R.id.animation_pending_top_btn, R.id.animation_pending_left_btn, R.id.animation_pending_right_btn, R.id.animation_pending_bottom_btn})
    public void onViewClicked(View view) {
        int type = 0;
        switch (view.getId()) {
            case R.id.animation_pending_left_btn:
                type = 0;
                break;
            case R.id.animation_pending_top_btn:
                type = 1;
                break;
            case R.id.animation_pending_right_btn:
                type = 2;
                break;
            case R.id.animation_pending_bottom_btn:
                type = 3;
                break;
        }
        AAnimationActivity.launch(this,type);
    }
}
