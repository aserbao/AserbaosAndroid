package com.aserbao.aserbaosandroid.functions.cutout;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.List;

public class CutOutActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "CutOutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int flag = getIntent().getIntExtra(StaticFinalValues.FLAG, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT);
        getWindow().addFlags(flag);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("显示DisplayCutout的个数",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置主题为\n LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT",100));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置主题为\n LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES",101));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置主题为\n LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER",102));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置SystemUiVisibility为\n SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置SystemUiVisibility为\n SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("设置SystemUiVisibility为\n SYSTEM_UI_FLAG_LAYOUT_STABLE",3));

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
               /* DisplayCutout displayCutout = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    displayCutout = (DisplayCutout) WindowInsets.getDisplayCutout();
                }
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                int safeInsetBottom = displayCutout.getSafeInsetBottom();
                int safeInsetLeft = displayCutout.getSafeInsetLeft();
                int safeInsetRight = displayCutout.getSafeInsetRight();
                int safeInsetTop = displayCutout.getSafeInsetTop();
                String msg = "itemClickBack: \n boundingRects.size= " + boundingRects.size() + "\n safeInsetBottom = " + safeInsetBottom +
                    "\n getSafeInsetLeft = " + safeInsetLeft + "\n getSafeInsetRight = " + safeInsetRight + "\n getSafeInsetTop =" + safeInsetTop;
                mBaseRecyclerTv.setText("别懒，打断点，看日志" + msg);
                Log.e(TAG, msg);*/

                View decorView = getWindow().getDecorView();
                WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
                if (rootWindowInsets != null) {
                    DisplayCutout cutout = rootWindowInsets.getDisplayCutout();
                    List<Rect> boundingRects = cutout.getBoundingRects();
                    String msg = "";
                    if (boundingRects != null && boundingRects.size() > 0) {
                        for (Rect rect : boundingRects) {
                            msg = "left-" + rect.left;
                        }
                    }
                    int safeInsetBottom = cutout.getSafeInsetBottom();
                    int safeInsetLeft = cutout.getSafeInsetLeft();
                    int safeInsetRight = cutout.getSafeInsetRight();
                    int safeInsetTop = cutout.getSafeInsetTop();
                    String result = "itemClickBack: \n boundingRects.size= " + boundingRects.size() + "\n safeInsetBottom = " + safeInsetBottom +
                        "\n getSafeInsetLeft = " + safeInsetLeft + "\n getSafeInsetRight = " + safeInsetRight + "\n getSafeInsetTop =" + safeInsetTop;
                    mBaseRecyclerTv.setText("别懒，打断点，看日志" + result + " \n " + msg);
                    Log.e(TAG, result);
                }
                break;
            case 100:
            case 101:
            case 102:
                /*Intent intent = new Intent(this,CutOutActivity.class);
                intent.putExtra(StaticFinalValues.FLAG,position -100);
                startActivity(intent);*/
                getIntent().putExtra(StaticFinalValues.FLAG,position -100);
                recreate();
                break;
            case 1:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                break;
            case 2:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                break;
            case 3:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                break;
        }
    }
}
