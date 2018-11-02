package com.aserbao.aserbaosandroid.functions.how_create_so.useNdkBuild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;


public class UseNdkBuildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_ndk_build);
        ((TextView) findViewById(R.id.use_ndk_build_tv)).setText(CallUtils.callSimpleInfo() + " \n" + CallUtils.callSimpleInfo2());
    }
}
