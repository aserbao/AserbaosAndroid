package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    }

    public abstract void setTextViewContent();

}
