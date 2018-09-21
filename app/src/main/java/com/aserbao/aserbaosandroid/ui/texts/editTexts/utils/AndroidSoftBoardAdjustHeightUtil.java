package com.aserbao.aserbaosandroid.ui.texts.editTexts.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

//思路参考于：AndroidBug5497Workaround
public class AndroidSoftBoardAdjustHeightUtil {

    public static void assistActivity(Activity activity) {
        new AndroidSoftBoardAdjustHeightUtil(activity);
    }  
  
    private View mChildOfContent;
    private int                      usableHeightPrevious;  
    private FrameLayout.LayoutParams frameLayoutParams;
  
    private AndroidSoftBoardAdjustHeightUtil(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {  
                possiblyResizeChildOfContent();  
            }  
        });  
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();  
    }  

    private void possiblyResizeChildOfContent() {  
        int usableHeightNow = computeUsableHeight();  
        if (usableHeightNow != usableHeightPrevious) {  
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;  
            //排除其他View引起的变化,专注软键盘变化
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;  //减掉软键盘的高度
            } else {  
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }  
            mChildOfContent.requestLayout();  
            usableHeightPrevious = usableHeightNow;  
        }  
    }  

    private int computeUsableHeight() {  
        Rect r = new Rect();
        //这行代码能够获取到去除标题栏和被软键盘挡住的部分,所剩下的矩形区域  
        mChildOfContent.getWindowVisibleDisplayFrame(r);  
        //r.top : 标题栏的高度  
        //屏幕高度-r.bottom : 软键盘的高度  
        //可用高度(全屏模式) : rect.bottom  
        //可用高度(非全屏模式) : rect.bottom - rect.top  
        return (r.bottom - r.top);// 全屏模式下： return r.bottom  
    }  
  
}  