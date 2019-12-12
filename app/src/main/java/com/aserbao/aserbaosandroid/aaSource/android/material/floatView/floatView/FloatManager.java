package com.aserbao.aserbaosandroid.aaSource.android.material.floatView.floatView;

import android.app.Activity;
import android.widget.FrameLayout;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-12-10 17:33
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.material.floatView.floatView
 */
public class FloatManager {

    private static volatile FloatManager mInstance;

    public static FloatManager getmInstance() {
        return mInstance;
    }

    public FloatManager attach(Activity activity){
        return this;
    }


    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
