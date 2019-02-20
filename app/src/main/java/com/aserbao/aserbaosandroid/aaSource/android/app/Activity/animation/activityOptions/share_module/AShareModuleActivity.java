package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.a_share_module_btn)
    Button mAShareModuleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashare_module);
        ButterKnife.bind(this);
        mShowActivityNameTv.setText("AShareModuleActivity");
    }

    @OnClick({R.id.a_share_module_circle_iv, R.id.a_share_module_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_share_module_circle_iv:
                BShareModuleActivity.launch(this, mAShareModuleCircleIv);
                break;
            case R.id.a_share_module_btn:
                BShareModuleActivity.launch(this, mAShareModuleBtn);
                break;
        }
    }
}
