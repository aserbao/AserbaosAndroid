package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/22 5:33 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService
 */
public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = "MyAccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e(TAG, "onAccessibilityEvent: " + event.toString() );
    }



    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }


    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt: ");
    }
}
