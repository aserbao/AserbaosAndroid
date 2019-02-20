package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BShareModuleActivity extends AppCompatActivity {

    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;

    public static void launch(Activity activity, ImageView imageView) {
        activity.startActivity(new Intent(activity, BShareModuleActivity.class), ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "aserbao_share_name").toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_share_module);
        ButterKnife.bind(this);
        mShowActivityNameTv.setText("BShareModuleActivity");
    }

    @OnClick(R.id.b_share_module_civ)
    public void onViewClicked() {
        onBackPressed();
    }
}
