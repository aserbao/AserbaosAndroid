package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.overridePendingTransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AAnimationActivity extends AppCompatActivity {

    @BindView(R.id.animation_pending_btn)
    Button mAnimationPendingBtn;

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, AAnimationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
        mAnimationPendingBtn.setText("跳转到B");
    }

    @OnClick(R.id.animation_pending_btn)
    public void onViewClicked() {

    }
}
