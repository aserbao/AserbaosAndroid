package com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.airbnb.lottie.L;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/27 2:26 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.accessibilityservice.accessibilityService.utils
 */
public class MyAccessibilityUtils {
    private static final String TAG = "MyAccessibilityUtils";
    private static long millis = 100;

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

}
