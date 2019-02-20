package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byTheme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BByThemeAnimationActivity extends AppCompatActivity {
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

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, BByThemeAnimationActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);
        getWindow().setWindowAnimations(R.style.test_animation);
        ButterKnife.bind(this);
        mAnimationTv.setText("B");
        mAnimationPendingTopBtn.setText("跳转到A");
        mAnimationPendingBottomBtn.setVisibility(View.GONE);
        mAnimationPendingLeftBtn.setVisibility(View.GONE);
        mAnimationPendingRightBtn.setVisibility(View.GONE);
    }



    @OnClick({R.id.animation_pending_top_btn, R.id.animation_pending_left_btn, R.id.animation_pending_right_btn, R.id.animation_pending_bottom_btn})
    public void onViewClicked(View view) {
        int type = 0;
        AByThemeAnimationActivity.launch(this, type);
    }
}
