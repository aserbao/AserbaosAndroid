package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BShareModuleActivity extends AppCompatActivity {


    @BindView(R.id.b_share_module_fl)
    FrameLayout mBShareModuleFl;
    @BindView(R.id.b_share_module_civ)
    CircleImageView mBShareModuleCiv;
    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;

    public static void launch(Activity activity, View view) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        if (view instanceof Button) {
            intent.putExtra(StaticFinalValues.TYPE, 1);
        }
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, "aserbao_share_name").toBundle());
    }

    private static final String TAG = "BShareModuleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int intExtra = getIntent().getIntExtra(StaticFinalValues.TYPE, 0);
        setContentView(R.layout.activity_b_share_module);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate: " +intExtra );
        if (intExtra == 0) {
            mBShareModuleCiv.setTransitionName("aserbao_share_name");
        }
        mBShareModuleFl.setTransitionName("aserbao_share_name");
        mShowActivityNameTv.setText("BShareModuleActivity");
    }

    @OnClick(R.id.b_share_module_civ)
    public void onViewClicked() {
        onBackPressed();
    }
}
