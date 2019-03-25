package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import static android.view.accessibility.AccessibilityEvent.TYPE_ANNOUNCEMENT;
import static android.view.accessibility.AccessibilityEvent.TYPE_GESTURE_DETECTION_END;
import static android.view.accessibility.AccessibilityEvent.TYPE_GESTURE_DETECTION_START;
import static android.view.accessibility.AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END;
import static android.view.accessibility.AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START;
import static android.view.accessibility.AccessibilityEvent.TYPE_TOUCH_INTERACTION_END;
import static android.view.accessibility.AccessibilityEvent.TYPE_TOUCH_INTERACTION_START;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_FOCUSED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_HOVER_ENTER;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_HOVER_EXIT;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SCROLLED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SELECTED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOWS_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;

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
        CharSequence packageName = event.getPackageName();
        if (packageName.equals("com.getremark.spot")) {
            AccessibilityNodeInfo accessibilityNodeInfo = this.getRootInActiveWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Log.e(TAG, "onAccessibilityEvent: " + event.toString() );
                if (accessibilityNodeInfo != null) {
//                    List<AccessibilityNodeInfo> nodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.getremark.spot:id/tab_chat");
//                    List<AccessibilityNodeInfo> nodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.getremark.spot:id/note_peek_entry_btn");
                    List<AccessibilityNodeInfo> nodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.getremark.spot:id/tv_my_account");
//                    List<AccessibilityNodeInfo> nodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("聊天");
                    accessibilityNodeInfo.recycle();
                    if (nodeInfosByText != null) {
                        for (AccessibilityNodeInfo item : nodeInfosByText) {
                            Log.e(TAG, "onAccessibilityEvent s: " + item.toString() );
//                            item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            item.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT);
                            item.setEditable(true);
//                            item.setSealed(false);
                            Bundle arguments=new Bundle();
                            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"哈哈");
                            item.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT,arguments);

//                            item.setText("hahha");
                        }
                    }
                }
            }
        }

        int eventType = event.getEventType();
        Log.e(TAG, "onAccessibilityEvent: " + event.toString());
        switch (eventType) {
            case TYPE_VIEW_CLICKED://界面点击
                break;
            case TYPE_VIEW_SELECTED://视图选择
                break;
            case TYPE_VIEW_FOCUSED: // 视图聚焦
                break;
            case TYPE_VIEW_TEXT_CHANGED://界面文字改动
                break;
            case TYPE_WINDOW_STATE_CHANGED: //窗口状态改变，可见的View 窗口发生了变化
                break;
            case TYPE_NOTIFICATION_STATE_CHANGED: // 这个是监听状态栏来的通知的，软键盘弹出
                break;
            case TYPE_VIEW_HOVER_ENTER://界面停留
                break;
            case TYPE_VIEW_HOVER_EXIT: //退出停留状态
                break;
            case TYPE_TOUCH_EXPLORATION_GESTURE_START: //手势开始
                break;
            case TYPE_TOUCH_EXPLORATION_GESTURE_END://手势结束
                break;
            case TYPE_WINDOW_CONTENT_CHANGED://页面有内容发生改变，比如添加或者删除一个view，checkbox选中变成非选中，一个textview的内容变化了,对View进行了动画等等
                break;
            case TYPE_VIEW_SCROLLED: // 视图滚动
                break;
            case TYPE_VIEW_TEXT_SELECTION_CHANGED://视图文字改变
                break;
            case TYPE_ANNOUNCEMENT: //应用程序发出通知
                break;
            case TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY: //
                break;
            case TYPE_GESTURE_DETECTION_START://开始手势检测
                break;
            case TYPE_GESTURE_DETECTION_END: //手势检测结束
                break;
            case TYPE_TOUCH_INTERACTION_START: //用户开始触碰屏幕
                break;
            case TYPE_TOUCH_INTERACTION_END: // 用户手离开屏幕
                break;
            case  TYPE_WINDOWS_CHANGED:// 系统窗口中的时间更改通知
                break;
        }
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
