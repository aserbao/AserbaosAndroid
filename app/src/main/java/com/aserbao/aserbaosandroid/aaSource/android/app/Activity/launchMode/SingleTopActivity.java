package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

public class SingleTopActivity extends BaseLaunchModeActivity {


    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) +"SingleTopActivity \n SingleTop");
    }
}
