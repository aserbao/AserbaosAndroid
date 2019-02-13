package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

public class SingleTaskActivity extends BaseLaunchModeActivity {


    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) +"SingleTaskActivity \n SingleTask");
    }
}
