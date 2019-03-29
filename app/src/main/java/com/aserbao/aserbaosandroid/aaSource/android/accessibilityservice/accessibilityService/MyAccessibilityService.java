package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.airbnb.lottie.L;
import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

import java.util.List;

import static android.media.AudioManager.ADJUST_RAISE;
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
    private AccessibilityNodeInfo accessibilityNodeInfo;



    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e(TAG, "handleMessage: " + msg.what );
                    slideVertical(19,0);
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private AudioManager audioManager =
        (AudioManager) getSystemService(AUDIO_SERVICE);

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence packageName = event.getPackageName();
        int eventType = event.getEventType();
        AccessibilityNodeInfo interactedNodeInfo =
            event.getSource();
        if (interactedNodeInfo.getText().equals("Increase volume")) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY,
                ADJUST_RAISE, 0);
        }

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
                mHandler.sendEmptyMessageDelayed(0,1500);
//                if (packageName.equals("com.getremark.spot")) {
                if (packageName.equals("com.aserbao.aserbaosandroid")) {
                    accessibilityNodeInfo = this.getRootInActiveWindow();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        if (accessibilityNodeInfo != null) {
                            List<AccessibilityNodeInfo> nodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.example.aserbao.aserbaosandroid:id/move_to_delete_rv");
                            if (nodeInfosByViewId != null) {
//                                nodeInfosByViewId.get(0).performAction(GESTURE_SWIPE_UP);
                                Log.e(TAG, "onAccessibilityEvent: GESTURE_SWIPE_UP_AND_DOWN " + event.toString() );
                            }
//                            accessibilityNodeInfo.performAction(GESTURE_SWIPE_DOWN);
//                            performSwipeRight(accessibilityNodeInfo);

                        }
                    }
                }
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


    @Override
    protected boolean onGesture(int gestureId) {
        Log.e(TAG, "onGesture: " +gestureId);
        return super.onGesture(gestureId);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.e(TAG, "onKeyEvent: " +event.getAction() );
        return super.onKeyEvent(event);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void slideVertical(int startSlideRatio, int stopSlideRatio) {
        int screenHeight = DisplayUtil.getScreenHeight(getApplicationContext());
        int screenWidth = DisplayUtil.getScreenWidth(getApplicationContext());
        Log.e("","屏幕：" + (screenHeight - (screenHeight / 10)) + "/" +
            (screenHeight - (screenHeight - (screenHeight / 10))) + "/" + screenWidth / 2);

        Path path = new Path();
        int start = (screenHeight / 20) * startSlideRatio;
        int stop = (screenHeight / 20) * stopSlideRatio;
        path.moveTo(screenWidth / 2, start);//如果只是设置moveTo就是点击
        path.lineTo(screenWidth / 2, stop);//如果设置这句就是滑动
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder
            .addStroke(new GestureDescription.
                StrokeDescription(path,
                200,
                1000)).build();

        boolean dispatchGesture = dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.e(TAG, "onCompleted: " + gestureDescription.toString());
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.e(TAG, "onCancelled: " + gestureDescription.toString());
            }
        }, null);
        Log.e(TAG, "slideVertical: " + dispatchGesture );
    }

}
