package com.aserbao.aserbaosandroid.functions.jumpSystemSetting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.jumpSystemSetting.utils.JumpCuurApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JumpSystemSettingActivity extends AppCompatActivity {

    @BindView(R.id.jump_vivo)
    Button jumpVivo;
    @BindView(R.id.jump_oppo)
    Button jumpOppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_system_setting);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.jump_vivo, R.id.jump_oppo,R.id.jump_app_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jump_vivo:
                jumpSystemSetting(this);
                break;
            case R.id.jump_oppo:

                break;
            case R.id.jump_app_activity:
                JumpCuurApp.showInstalledAppDetails(this,getPackageName());
                break;
        }
    }

    public void jumpSystemSetting(Activity activity){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", activity.getPackageName());
            intent.putExtra("app_uid", activity.getApplicationInfo().uid);
            startActivity(intent);
        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            startActivity(intent);
        }
    }
    /*跳到设置界面，所有手机通用*/
    public void jumpSetting(){
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}
