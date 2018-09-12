package com.aserbao.aserbaosandroid.ui.editTexts.utils;
/* 
 * @本类描述   网上收集的,解决"全屏模式"下,adjustResize失效方案    
 *             
 * @内容说明   解决: 
 *             1.非全屏模式下使用adjustPan无效问题 
 *             2.全屏模式下使用adjustPan和adjustResize无效问题 
 *  
 * @补充内容   有兴趣的同学可以学下里面的思路,自己写一个解决方案(*^__^*) 嘻嘻……) 
 * 
 * ---------------------------------      
 * @更新时间    
 * @新增内容    
 * 
 */

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class AndroidBug5497Workaround {
  
    // For more information, see https://code.google.com/p/android/issues/detail?id=5497  
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.  
  
    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);  
    }  
  
    private View mChildOfContent;
    private int                      usableHeightPrevious;  
    private FrameLayout.LayoutParams frameLayoutParams;
  
    private AndroidBug5497Workaround(Activity activity) {  
  
        //拿到当前XML文件的根布局  
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);  
  
        //监听当前View的状态,进行通知回调,即"软键盘弹出""  
        mChildOfContent = content.getChildAt(0);  
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {  
                possiblyResizeChildOfContent();  
            }  
        });  
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();  
    }  
  
  /** 
     * 重新设置高度 
     * <p> 
     * 把界面高度设置为可用高度 
     */  
    private void possiblyResizeChildOfContent() {  
        int usableHeightNow = computeUsableHeight();  
        if (usableHeightNow != usableHeightPrevious) {  
            // int usableHeightSansKeyboard = activity.getWindowManager().getDefaultDisplay().getHeight();//获取屏幕尺寸，不包括虚拟功能高度 用这个可以完美解决  
            //findViewById(android.R.id.content).getMeasuredHeight() 也可以解决虚拟按键问题  
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();  
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;  
            //排除其他View引起的变化,专注软键盘变化  
            if (heightDifference > (usableHeightSansKeyboard / 4)) {  
                // keyboard probably just became visible  
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;  
            } else {  
                // keyboard probably just became hidden  
                frameLayoutParams.height = usableHeightSansKeyboard;  
            }  
            mChildOfContent.requestLayout();  
            usableHeightPrevious = usableHeightNow;  
        }  
    }  
  
    /** 
     * 软键盘弹出后,可以显示内容的高度 
     * 
     * @return 
     */  
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