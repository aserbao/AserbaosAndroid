package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.phone.AJumpUtils;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.MoveToDeleteActivity;

import java.util.Locale;

public class MyAccessibilityServiceActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("AccessibilityService"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开抖音"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开spot"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开微信"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开Item侧滑删除"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("打开Setting界面"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("测试"));
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
            case 3:
                AJumpUtils.jumpToWeChat(this);
                break;
            case 4:
                Intent intent = new Intent(this, MoveToDeleteActivity.class);
                startActivity(intent);
                break;
            case 5:
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                break;
            case 6:
                break;
        }
    }



    private SpannableStringBuilder wrapTextInLocaleSpan(
        CharSequence originalText, Locale loc) {
        SpannableStringBuilder myLocaleBuilder =
            new SpannableStringBuilder(originalText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            myLocaleBuilder.setSpan(new LocaleSpan(loc), 0,
                originalText.length() - 1, 0);
        }
        return myLocaleBuilder;
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
