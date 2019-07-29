package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService;

import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.FingerprintGestureController;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.utils.MyAccessibilityUtils;

import java.util.List;

import static android.accessibilityservice.GestureDescription.getMaxGestureDuration;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SCROLLED;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/22 5:33 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService
 */
public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = "fingerprintGestures";
    private AccessibilityNodeInfo accessibilityNodeInfo;

    /*private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e(TAG, "handleMessage: " + msg.what );
                    slideVertical(19,0);
                    break;
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        clickPointOnScreen(826,179);
                    }
                    break;
            }
//            Log.e(TAG, "handleMessage: " + msg.what );
        }
    };*/
    private boolean isSend = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence packageName = event.getPackageName();
        int eventType = event.getEventType();
        Log.e(TAG, "onAccessibilityEvent: =======" + event.toString() + "\n"+packageName +" ======== "+ event.getClassName());
        accessibilityNodeInfo = getRootInActiveWindow();
        weChatZan(event);

        /*switch (eventType){
            case TYPE_WINDOW_STATE_CHANGED:
                if (packageName.equals("com.example.aserbao.aserbaosandroid")){
                    slideVertical(19,0);
                }
                break;
        }*/
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (getServiceInfo() != null) {
            getServiceInfo().flags = AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE;
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
        int screenHeight = AserbaoApplication.screenHeight;
        int screenWidth = AserbaoApplication.screenWidth;

        Path path = new Path();
        int start = (screenHeight / 20) * startSlideRatio;
        int stop = (screenHeight / 20) * stopSlideRatio;

        Log.e(TAG,"屏幕：" + (screenHeight - (screenHeight / 10)) + "/" +(screenHeight - (screenHeight - (screenHeight / 10))) + "/" + screenWidth / 2 + "/" + start + " /stop" + stop);

        path.moveTo(screenWidth / 2, start);//如果只是设置moveTo就是点击
        path.lineTo(screenWidth / 2, stop);//如果设置这句就是滑动
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription gestureDescription = builder
            .addStroke(new GestureDescription.
                StrokeDescription(path,
                100,
                50)).build();
        long maxGestureDuration = getMaxGestureDuration();
        Log.e(TAG, "slideVertical: maxGestureDuration = " + maxGestureDuration + " path =  " + path.toString() );

        boolean dispatchGesture = dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                isSend = false;
                Log.e(TAG, "onCompleted: " + gestureDescription.toString());
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                isSend = false;
                Log.e(TAG, "onCancelled: " + gestureDescription.toString());
            }
        }, null);
        Log.e(TAG, "slideVertical: " + dispatchGesture );
    }

    //=====================指纹手势检测
    private FingerprintGestureController gestureController;
    private FingerprintGestureController
        .FingerprintGestureCallback fingerprintGestureCallback;
    private boolean mIsGestureDetectionAvailable;
    public void fingerprintGestures(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e(TAG, "fingerprintGestures: " );
//            mIsGestureDetectionAvailable = gestureController.isGestureDetectionAvailable();
            if (fingerprintGestureCallback != null || !mIsGestureDetectionAvailable) {
                return;
            }


            fingerprintGestureCallback = new FingerprintGestureController.FingerprintGestureCallback() {
                    @Override
                    public void onGestureDetected(int gesture) {
                        switch (gesture) {
                            case FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_DOWN:
                                Log.e(TAG, "onGestureDetected: FINGERPRINT_GESTURE_SWIPE_DOWN" + gesture  );
                                break;
                            case FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_LEFT:
                                Log.e(TAG, "onGestureDetected: FINGERPRINT_GESTURE_SWIPE_LEFT" + gesture );
                                break;
                            case FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_RIGHT:
                                Log.e(TAG, "onGestureDetected: FINGERPRINT_GESTURE_SWIPE_RIGHT"+gesture  );
                                break;
                            case FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_UP:
                                Log.e(TAG, "onGestureDetected: FINGERPRINT_GESTURE_SWIPE_UP" +gesture );
                                break;
                            default:
                                Log.e(TAG, "onGestureDetected: DEFAULT" +gesture );
                                break;
                        }
                    }
                    @Override
                    public void onGestureDetectionAvailabilityChanged(boolean available) {
                        Log.e(TAG, "onGestureDetectionAvailabilityChanged: " + available );
                        mIsGestureDetectionAvailable = available;
                    }
            };

            /*if (fingerprintGestureCallback != null) {
                gestureController.registerFingerprintGestureCallback(fingerprintGestureCallback, mHandler);
            }*/
        }else{
            Log.e(TAG, "fingerprintGestures: false" );
        }
    }

    //======================辅助功能快捷方式
    private AccessibilityButtonController accessibilityButtonController;
    private AccessibilityButtonController
        .AccessibilityButtonCallback accessibilityButtonCallback;
    private boolean mIsAccessibilityButtonAvailable;
    public void userAccessibilityButtonController(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            accessibilityButtonController = getAccessibilityButtonController();
            mIsAccessibilityButtonAvailable =
                accessibilityButtonController.isAccessibilityButtonAvailable();
        }
        if (!mIsAccessibilityButtonAvailable) {
            return;
        }

        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        serviceInfo.flags |= AccessibilityServiceInfo.FLAG_REQUEST_ACCESSIBILITY_BUTTON;
        setServiceInfo(serviceInfo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            accessibilityButtonCallback =new AccessibilityButtonController.AccessibilityButtonCallback() {
                @Override
                public void onClicked(AccessibilityButtonController controller) {
                    Log.d("MY_APP_TAG", "Accessibility button pressed!");

                    // Add custom logic for a service to react to the
                    // accessibility button being pressed.
                }

                @Override
                public void onAvailabilityChanged(
                    AccessibilityButtonController controller, boolean available) {
                    if (controller.equals(accessibilityButtonController)) {
                        mIsAccessibilityButtonAvailable = available;
                    }
                }
            };
        }else{
            Log.e(TAG, "onServiceConnected: " );
        }

        if (accessibilityButtonCallback != null && accessibilityButtonController!= null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                accessibilityButtonController.registerAccessibilityButtonCallback(
                    accessibilityButtonCallback, null);
            }else{
                Log.e(TAG, "onServiceConnected:registerAccessibilityButtonCallback " );
            }
        }
    }


    //=====================微信朋友圈点赞功能
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void  weChatZan(AccessibilityEvent event){
        CharSequence packageName = event.getPackageName();
        CharSequence className = event.getClassName();
        CharSequence contentDescription = event.getContentDescription();
        List<CharSequence> text = event.getText();
        int eventType = event.getEventType();
        if (!TextUtils.isEmpty(packageName) && packageName.equals("com.tencent.mm") ){
            /*if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ) {
                if (className.equals(WeChatFinal.LauncherUI)){
                    MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo, "发现", false);
                }else if(className.equals(WeChatFinal.SNSTIMELINEUI)){
                    if (contentDescription.equals(WeChatFinal.FRIENDCIRCLE)){
                        List<AccessibilityNodeInfo> nodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ee2");
                        Log.e(TAG, "weChatZan: hahh " + nodeInfosByViewId.size());
                        if (nodeInfosByViewId.size() > 0){
                            nodeInfosByViewId.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }
            }else if(eventType == AccessibilityEvent.TYPE_VIEW_CLICKED){
                if(className.equals(WeChatFinal.IMAGEVIEW) && contentDescription.equals("评论")){
                    MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo,"赞",false);
                }else if (String.valueOf(text).equals("[发现]")) {
                    MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo, "朋友圈", false);
                }
            }*/
            accessibilityNodeInfo = getRootInActiveWindow();
            if (accessibilityNodeInfo == null){
                return;
            }
            if (eventType == TYPE_WINDOW_STATE_CHANGED || eventType == TYPE_VIEW_SCROLLED) {
                    List<AccessibilityNodeInfo> child = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/eew");
                    if (child.size() <= 0) {
                        return;
                    }
                    AccessibilityNodeInfo childListView = child.get(0);
                    int childCount = childListView.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        AccessibilityNodeInfo childItem = childListView.getChild(i);
    //                Log.e(TAG, "weChatZan: " + event.getEventType() + " " + className + " contentDescription = "+ contentDescription );
                        List<AccessibilityNodeInfo> nodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ee2");

                        if (i < nodeInfosByViewId.size()){
                            nodeInfosByViewId.get(i).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            MyAccessibilityUtils.sleep(200);
                            MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo,"赞",false);
//                            MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo,"取消",false);
                            MyAccessibilityUtils.sleep(300);
                        }

                        Log.e(TAG, "weChatZan: "+ className + contentDescription + nodeInfosByViewId.size() + "\n === " + i);

                        if (i == childCount - 1){
                            MyAccessibilityUtils.performScroll(childListView);
//                            slideVertical(5,18);
                            Log.e(TAG, "weChatZan:  " + i +  "   ======scroll" );
                        }
                   /* if (TextUtils.isEmpty(className)){
                        return;
                    }
                    if (TextUtils.isEmpty(contentDescription)){
                        return;
                    }

                    if (contentDescription.equals(WeChatFinal.FRIENDCIRCLE)){
                            List<AccessibilityNodeInfo> nodeInfosByViewId = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/ee2");
                            Log.e(TAG, "weChatZan: hahh " + nodeInfosByViewId.size());
                            if (nodeInfosByViewId.size() > 0){
                                nodeInfosByViewId.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                    }*/
                    /*if (contentDescription.equals("评论") && className.equals(WeChatFinal.IMAGEVIEW)){
                        MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo,"赞",false);
                    }
                    if (i == childCount -1){
                        MyAccessibilityUtils.performScroll(childListView);
                        Log.e(TAG, "weChatZan:  " + i +  "   ======scroll" );
                    }
                    MyAccessibilityUtils.sleep();*/
                        }

    //            Log.e(TAG, "weChatZan: " + childListView.toString() + " \n" + childListView.isScrollable() );
                /*int childCount = accessibilityNodeInfo.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
                    int childCount1 = child.getChildCount();
                    for (int j = 0; j < childCount1; j++) {
                        AccessibilityNodeInfo childListView = child.getChild(j);
                        if (childListView.isScrollable()){
                            MyAccessibilityUtils.performScroll(childListView);
                        }
                        Log.e(TAG, "weChatZan: " + childListView.toString() + " \n" + childListView.isScrollable() );
                    }
                }*/
                    Log.e(TAG, "weChatZan: =======" + event.toString() + "\n" + packageName + " ======== " + className + " === " + text);
            }else if (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED){
                if (!TextUtils.isEmpty(contentDescription) && contentDescription.equals("评论") && className.equals(WeChatFinal.IMAGEVIEW)){
                    MyAccessibilityUtils.findNodeByTextAndClick(accessibilityNodeInfo,"赞",false);
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void clickPointOnScreen(int x, int y) {

       /* Path path = new Path();

        path.moveTo(x, y);//如果只是设置moveTo就是点击
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
                isSend = false;
                Log.e(TAG, "onCompleted: " + gestureDescription.toString());
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                isSend = false;
                Log.e(TAG, "onCancelled: " + gestureDescription.toString());
            }
        }, null);
        Log.e(TAG, "slideVertical: " + dispatchGesture );*/



        //                    List<AccessibilityNodeInfo> findListInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText("朋友圈");
//                    List<AccessibilityNodeInfo> findListInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/d7b");
                    List<AccessibilityNodeInfo> findListInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/iq");
        Log.e(TAG, "weChatZan: +++++" + findListInfo.size() );
        if (findListInfo!=null && findListInfo.size() > 0) {
                        for (AccessibilityNodeInfo nodeInfo : findListInfo) {
                            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }

    }
}
