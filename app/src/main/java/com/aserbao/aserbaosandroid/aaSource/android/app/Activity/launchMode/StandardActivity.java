package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aserbao.aserbaosandroid.R;

public class StandardActivity extends BaseLaunchModeActivity {


    @Override
    public void setTextViewContent() {
        mLaunchModeTv.setText(String.valueOf(getTaskId()) + "StandardActivity \n Standard " );
    }

    @Override
    public void onBackPressed() {
        sBackList.add("A");
        super.onBackPressed();
    }
}
