package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/13 11:39 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode
 */
public abstract class BaseLaunchModeActivity extends AppCompatActivity {
    @BindView(R.id.launch_mode_tv)
    public TextView mLaunchModeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);
        setTextViewContent();
    }

    public abstract void setTextViewContent();

    @OnClick({R.id.standard_btn, R.id.singleInstance_btn, R.id.singletop_btn, R.id.singletask_btn})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.standard_btn:
                intent = new Intent(BaseLaunchModeActivity.this, StandardActivity.class);
                break;
            case R.id.singleInstance_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleInstanceActivity.class);
                break;
            case R.id.singletop_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleTopActivity.class);
                break;
            case R.id.singletask_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleTaskActivity.class);
                break;
        }
        startActivity(intent);
    }
}
