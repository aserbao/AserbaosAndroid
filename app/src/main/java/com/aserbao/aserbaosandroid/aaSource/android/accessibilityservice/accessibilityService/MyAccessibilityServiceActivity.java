package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.phone.AJumpUtils;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

public class MyAccessibilityServiceActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("AccessibilityService"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开抖音"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开spot"));
    }

    private static final String TAG = "MyAccessibilityServiceA";

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                if (isAccessibilitySettingsOn(this, MyAccessibilityService.class)) {
                    Log.e(TAG, "itemClickBack: " );
                }else{
                    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                }
                break;
            case 1:
                AJumpUtils.jumpToTikTok(this);
                break;
            case 2:
//              com.getremark.spot/.act.SplashActivity
                AJumpUtils.jumpToPackage(this,"com.getremark.spot","com.getremark.spot.act.SplashActivity");
                break;
        }
    }

    public static boolean isAccessibilitySettingsOn(Context mContext, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        final String service = mContext.getPackageName() + "/" + clazz.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
