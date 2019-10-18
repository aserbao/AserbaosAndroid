package com.aserbao.aserbaosandroid.functions.regex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.Regex;

public class RegexActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "RegexActivity";
    public static final String sPngUrl = "http://npic.getremark.com/BulldogHarlamov.png";
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("获取Url中图片名称",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选url中的图片名称",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
                String test = "CartoonOctopus.zip ";
                String[] split = test.split(".zip");
                for (String s : split) {
                    Log.e(TAG, "itemClickBack: " + s );
                }
                break;
            case 1:
//                Pattern pattern = Pattern.compile("com/.*\\.");
                Pattern pattern = Pattern.compile(".*/(.*?)$");
                Matcher matcher = pattern.matcher(sPngUrl);
                int groupCount = matcher.groupCount();
                for (int i = 0; i < groupCount; i++) {
                    String group = matcher.group(i);
                    Log.d(TAG, "itemClickBack: " + group);
                }
                break;
        }
    }
}
