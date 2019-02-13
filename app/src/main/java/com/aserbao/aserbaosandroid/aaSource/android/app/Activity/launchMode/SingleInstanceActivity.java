package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

public class SingleInstanceActivity extends BaseLaunchModeActivity {


    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) +"SingleInstanceActivity \n SingleInstance");
    }
}
