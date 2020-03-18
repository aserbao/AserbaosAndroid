package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import androidx.appcompat.app.AppCompatActivity;

public class StandardActivity extends BaseLaunchModeActivity {


    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) + "StandardActivity \n Standard " );
        mActivityName = "StandardActivity ==";
    }

    @Override
    public void onBackPressed() {
        sBackList.add("A");
        super.onBackPressed();
    }
}
