package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.os.Bundle;

public class LaunchModeActivity extends BaseLaunchModeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) +" \n Initialization LaunchMode");
        mActivityName = "LaunchModeActivity ==";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sJumpList.clear();
        sBackList.clear();
    }
}
