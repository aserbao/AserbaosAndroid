package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AShareModuleActivity extends AppCompatActivity {

    @BindView(R.id.a_share_module_circle_iv)
    CircleImageView mAShareModuleCircleIv;
    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashare_module);
        ButterKnife.bind(this);
        mShowActivityNameTv.setText("AShareModuleActivity");
    }

    @OnClick(R.id.a_share_module_circle_iv)
    public void onViewClicked() {
        BShareModuleActivity.launch(this, mAShareModuleCircleIv);
    }
}
