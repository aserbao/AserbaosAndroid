package com.aserbao.aserbaosandroid.functions.jumpSystemSetting;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.utils.JumpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JumpSystemSettingActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_system_setting);
        ButterKnife.bind(this);
        initRecylerView();
    }

    private void initRecylerView() {

    }

    @OnClick({R.id.jump_notification, R.id.jump_setting, R.id.jump_app_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jump_notification:
                JumpUtils.jumpSystemNotification(this);
                break;
            case R.id.jump_setting:
                JumpUtils.jumpSetting(this);
                break;
            case R.id.jump_app_activity:
                JumpUtils.showInstalledAppDetails(this, getPackageName());
                break;
        }
    }


}
