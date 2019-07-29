package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.aserbao.aserbaosandroid.AserbaoApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/27 2:26 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.utils
 */
public class MyAccessibilityUtils {
    private static final String TAG = "MyAccessibilityUtils";
    private static long millis = 1000;
    public static void sleep() {
        sleep(millis);
    }

    public static void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean checkNodeText(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        return !TextUtils.isEmpty(nodeInfo.getText().toString());
    }

    public static boolean checkNodeDes(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        return !TextUtils.isEmpty(nodeInfo.getContentDescription());
    }

    public static boolean performClick(AccessibilityNodeInfo clickNode) {
        while (clickNode != null && !clickNode.isClickable()) {
            clickNode = clickNode.getParent();
        }
        if (clickNode != null) {
            boolean result = false;
            try {
                //wait some times
                result = clickNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        Log.e(TAG, "clickNode is null");
        return false;
    }

    public static AccessibilityNodeInfo findNodeByText(AccessibilityNodeInfo root, String text) {
        if (root == null || TextUtils.isEmpty(text)) {
            return null;
        }
        List<AccessibilityNodeInfo> nodeList = root.findAccessibilityNodeInfosByText(text);

        if (nodeList == null || nodeList.isEmpty()) {
            return null;
        }
        AccessibilityNodeInfo clickNode = null;
        for (AccessibilityNodeInfo nodeInfo : nodeList) {
            boolean eqText = nodeInfo.getText() != null && nodeInfo.getText().toString().equals(text);
            boolean eqDesc = nodeInfo.getContentDescription() != null && nodeInfo.getContentDescription().toString().equals(text);
            if (eqText || eqDesc) {
                clickNode = nodeInfo;
                break;
            }
        }
        return clickNode;
    }

    public static AccessibilityNodeInfo findNodeContainsText(AccessibilityNodeInfo root, String text) {
        if (root == null || TextUtils.isEmpty(text)) {
            return null;
        }
        List<AccessibilityNodeInfo> nodeList = root.findAccessibilityNodeInfosByText(text);

        if (nodeList == null || nodeList.isEmpty()) {
            return null;
        }
        AccessibilityNodeInfo clickNode = null;
        for (AccessibilityNodeInfo nodeInfo : nodeList) {
            boolean eqText = nodeInfo.getText() != null && nodeInfo.getText().toString().contains(text);
            boolean eqDesc = nodeInfo.getContentDescription() != null && nodeInfo.getContentDescription().toString().contains(text);
            if (eqText || eqDesc) {
                clickNode = nodeInfo;
                break;
            }
        }
        return clickNode;
    }
    public static boolean findNodeByTextAndClick(AccessibilityNodeInfo root, String text) {
        return findNodeByTextAndClick(root, text, false);
    }

    public static boolean findNodeByTextAndClick(AccessibilityNodeInfo root, String text, boolean isNewPage) {
        if (root == null)
            return false;
        List<AccessibilityNodeInfo> txtNodeInfoList = root.findAccessibilityNodeInfosByText(text);

        if (txtNodeInfoList == null || txtNodeInfoList.isEmpty()) {
            Log.e(TAG, "没有找到" + text + "按钮");
            return false;
        }
        AccessibilityNodeInfo clickNode = null;
        for (AccessibilityNodeInfo nodeInfo : txtNodeInfoList) {
            if (nodeInfo.getText() != null && text.equals(nodeInfo.getText().toString())) {
                clickNode = nodeInfo;
                Log.i(TAG, "text= " + nodeInfo.getText());
            }
        }

        while (clickNode != null && !clickNode.isClickable()) {
            clickNode = clickNode.getParent();
        }
        if (clickNode != null) {
            boolean result = false;
            try {
                //wait some times
                if (isNewPage) {
                    root.recycle();
                }
                result = clickNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        Log.e(TAG, "clickNode is null");
        return false;
    }
    public static boolean findNodeContainsTextAndClick(AccessibilityNodeInfo root, String text) {
        if (root == null || TextUtils.isEmpty(text)) {
            return false;
        }
        List<AccessibilityNodeInfo> nodeList = root.findAccessibilityNodeInfosByText(text);

        if (nodeList == null || nodeList.isEmpty()) {
            return false;
        }
        AccessibilityNodeInfo clickNode = null;
        for (AccessibilityNodeInfo nodeInfo : nodeList) {
            boolean eqText = nodeInfo.getText() != null && nodeInfo.getText().toString().contains(text);
            boolean eqDesc = nodeInfo.getContentDescription() != null && nodeInfo.getContentDescription().toString().contains(text);
            if (eqText || eqDesc) {
                clickNode = nodeInfo;
                break;
            }
        }
        Log.i(TAG, "点击："+text+"！");
        return performClick(clickNode);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean findNodeByIdTextAndClick(AccessibilityNodeInfo root, String id, String text, boolean isNewPage) {
        if (root == null) {
            return false;
        }
        List<AccessibilityNodeInfo> idNodeInfoList = root.findAccessibilityNodeInfosByViewId(id);
        if (idNodeInfoList == null || idNodeInfoList.isEmpty()) {
            return false;
        }

        AccessibilityNodeInfo clickNode = null;
        for (int i = 0; i < idNodeInfoList.size(); i++) {
            AccessibilityNodeInfo nodeInfo = idNodeInfoList.get(i);
            if (nodeInfo == null) {
                continue;
            }
            //根据text过滤
            if (!TextUtils.isEmpty(nodeInfo.getText())
                && !TextUtils.isEmpty(text)) {
                if (text.equals(nodeInfo.getText())) {
                    clickNode = nodeInfo;
                    break;
                }
            }
        }
        if (clickNode == null) {
            return false;
        }
        while (clickNode != null && !clickNode.isClickable()) {
            clickNode = clickNode.getParent();
        }
        if (clickNode != null) {
            boolean result = false;
            try {
                //wait some times
                if (isNewPage) {
                    root.recycle();
                }
                result = clickNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        Log.e(TAG, "clickNode is null");
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean findNodeByIdAndClick(AccessibilityNodeInfo root, String id) {
        if (root == null || TextUtils.isEmpty(id)) {
            return false;
        }
        List<AccessibilityNodeInfo> nodeList = root.findAccessibilityNodeInfosByViewId(id);

        if (nodeList == null || nodeList.isEmpty()) {
            return false;
        }
        return performClick(nodeList.get(0));
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static boolean findNodeByIdClassAndClick(AccessibilityNodeInfo root, String id, String className, boolean isNewPage) {
        if (root == null) {
            return false;
        }
        List<AccessibilityNodeInfo> idNodeInfoList = root.findAccessibilityNodeInfosByViewId(id);
        if (idNodeInfoList == null || idNodeInfoList.isEmpty()) {
            return false;
        }

        AccessibilityNodeInfo clickNode = null;
        for (int i = 0; i < idNodeInfoList.size(); i++) {
            AccessibilityNodeInfo nodeInfo = idNodeInfoList.get(i);
            if (nodeInfo == null) {
                continue;
            }
            //根据className过滤
            if (!TextUtils.isEmpty(className)) {
                if (className.equals(nodeInfo.getClassName())) {
                    clickNode = nodeInfo;
                    break;
                }
            }
        }
        if (clickNode == null) {
            return false;
        }
        while (clickNode != null && !clickNode.isClickable()) {
            clickNode = clickNode.getParent();
        }
        if (clickNode != null) {
            boolean result = false;
            try {
                if (isNewPage) {
                    root.recycle();
                }
                //wait some times
                result = clickNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        Log.e(TAG, "clickNode is null");
        return false;
    }


    public static boolean performScroll(AccessibilityNodeInfo scrollerNode) {
        while (scrollerNode != null && !scrollerNode.isScrollable()) {
            scrollerNode = scrollerNode.getParent();
        }
        if (scrollerNode != null) {
            boolean result = false;
            try {
                result = scrollerNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                //wait some times
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        Log.e(TAG, "scrollerNode is null");
        return false;
    }

    public static boolean performScrollBack(AccessibilityNodeInfo scrollerNode) {
        while (scrollerNode != null && !scrollerNode.isScrollable()) {
            scrollerNode = scrollerNode.getParent();
        }
        if (scrollerNode != null) {
            boolean result = scrollerNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
            sleep();
            return result;
        }
        return false;
    }

    /**
     *  执行粘贴操作（注意：执行之后，会 sleep 0.1s）
     * @param ct
     * @param node
     * @param text
     * @return
     */
    public static boolean performPaste(Context ct, AccessibilityNodeInfo node, String text) {
        if (node == null || TextUtils.isEmpty(text)) {
            return false;
        }
        boolean result;
        if (Build.VERSION.SDK_INT >= 21) {
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
            result = node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
            sleep();
            return result;
        } else {
            ClipboardManager cm = (ClipboardManager) ct.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("text", text);
            cm.setPrimaryClip(mClipData);
            result = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
            sleep();
            return result;
        }
    }

    public static boolean hasNode(AccessibilityNodeInfo root, String text) {
        if (root == null || TextUtils.isEmpty(text)) {
            return false;
        }
        List<AccessibilityNodeInfo> nodeList = root.findAccessibilityNodeInfosByText(text);

        if (nodeList == null || nodeList.isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * @param root
     * @param id
     * @param className
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static List<AccessibilityNodeInfo> findNodeByIdAndClassNameList(AccessibilityNodeInfo root, String id, String className) {
        if (root == null) {
            return null;
        }
        List<AccessibilityNodeInfo> resultList = new ArrayList<>();
        List<AccessibilityNodeInfo> idNodeInfoList = root.findAccessibilityNodeInfosByViewId(id);
        if (idNodeInfoList == null || idNodeInfoList.isEmpty()) {
            return null;
        }
        Log.e("@@@", "idNodeinfolist size = " + idNodeInfoList.size());
        for (int i = 0; i < idNodeInfoList.size(); i++) {
            AccessibilityNodeInfo nodeInfo = idNodeInfoList.get(i);
            if (nodeInfo == null) {
                continue;
            }
            //根据className过滤
            if (!TextUtils.isEmpty(className)) {
                if (className.equals(nodeInfo.getClassName())) {
                    resultList.add(nodeInfo);
                }
            }
        }
        return resultList;
    }

    public static AccessibilityNodeInfo findNodeByClass(AccessibilityNodeInfo root, String className) {
        if (TextUtils.isEmpty(className) || root == null) {
            return null;
        }
        int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo rootChild = root.getChild(i);
            if (rootChild != null) {
                if (className.equals(rootChild.getClassName().toString().trim())) {
                    return rootChild;
                }
            }
        }
        return null;
    }

    public static List<AccessibilityNodeInfo> findNodeByClassList(AccessibilityNodeInfo root, String className) {
        List<AccessibilityNodeInfo> list = new ArrayList<>();
        if (TextUtils.isEmpty(className) || root == null) {
            return Collections.EMPTY_LIST;
        }
        int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo rootChild = root.getChild(i);
            if (rootChild != null) {
                if (className.equals(rootChild.getClassName().toString().trim())) {
                    list.add(rootChild);
                }
            }
        }
        return list;
    }

    public static List<AccessibilityNodeInfo> traverseNodeByClassList(AccessibilityNodeInfo root, String className) {
        List<AccessibilityNodeInfo> list = new ArrayList<>();
        if (TextUtils.isEmpty(className) || root == null) {
            return Collections.EMPTY_LIST;
        }
        List<AccessibilityNodeInfo> list2 = new ArrayList<>();
        traverseNodeClassToList(root, list2);
        for (AccessibilityNodeInfo nodeInfo : list2) {
            if (nodeInfo.getClassName() != null && className.equals(nodeInfo.getClassName().toString())) {
                list.add(nodeInfo);
            }
        }
        return list;
    }

    public static void traverseNodeClassToList(AccessibilityNodeInfo node, List<AccessibilityNodeInfo> list) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                list.add(child);
                if (child.getChildCount() > 0) {
                    traverseNodeClassToList(child, list);
                }
            }
        }
    }

    public static AccessibilityNodeInfo findNodeByClassNameAndTime(AccessibilityNodeInfo node, String className, int findClassTimes){
        List<AccessibilityNodeInfo> list = new ArrayList<>();
        traverseNodeClassToList(node, list);
        for (AccessibilityNodeInfo nodeInfo : list) {
            if (nodeInfo.getClassName() != null && className.equals(nodeInfo.getClassName().toString())) {
                findClassTimes--;
                if (findClassTimes < 0) {
                    findClassTimes = 0;
                }
                if (findClassTimes == 0)
                    return nodeInfo;
            }
        }
        return null;
    }


    /**
     * @param node
     * @param startSlideRatio   0~20
     * @param stopSlideRatio    0~20
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void slideVertical(AccessibilityService node,int startSlideRatio, int stopSlideRatio) {
        int screenHeight = AserbaoApplication.screenHeight;
        int screenWidth = AserbaoApplication.screenWidth;
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

        boolean dispatchGesture = node.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
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
