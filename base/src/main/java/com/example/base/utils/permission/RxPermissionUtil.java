package com.example.base.utils.permission;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by aiya on 17/12/18.
 */

public class RxPermissionUtil {
    private static final String TAG = "RxPermissionUtil";
    private static RxPermissions sRxPermissions;
    private static RxPermissionUtil sRxPermissionUtil = new RxPermissionUtil();
    private StringBuilder mBuilder = new StringBuilder();
    private PermissionCallBack mPermissionCallBack;
    private AlertDialog mDialog;

    private RxPermissionUtil() {

    }

    public static RxPermissionUtil getInstance() {
        return sRxPermissionUtil;
    }

    /**
     * 反射调用
     */
    private static int checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Object object = context.getSystemService(context.APP_OPS_SERVICE);
            Class c = object.getClass();
            try {
                Class[] args = new Class[3];
                args[0] = int.class;
                args[1] = int.class;
                args[2] = String.class;
                Method method = c.getDeclaredMethod("checkOp", args);
                return (Integer) method.invoke(object, op, Binder.getCallingUid(), context.getPackageName());
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * 申请单个照相机权限
     * 低于6.0版本的，国产机自带的权限系统
     */
    public void requestCameraPermissionByLollipop(AppCompatActivity context, String... permissions) {
        sRxPermissions = new RxPermissions(context);
        mBuilder.setLength(0);
        sRxPermissions.request(permissions).subscribe(granted -> {
            if (granted) {
                int checkResult = checkOp(context, 26);
                if (checkResult != AppOpsManager.MODE_IGNORED) {
                    mPermissionCallBack.result("");
                    return;
                }
            }
            mBuilder.append("在设置-应用-丸子视频-权限管理中开启\n");
            mBuilder.append(PermissionData.get(permissions[0]));
            mBuilder.append("\n");
            mBuilder.append("以正常使用此功能");
            mPermissionCallBack.result(mBuilder.toString());
            openSettingDialog(context, mBuilder.toString());
        });
    }

    /**
     * 申请单个录音权限
     * 低于6.0版本的，国产机自带的权限系统
     */
    public void requestAudioByLollipop(FragmentActivity context, String... permissions) {
        sRxPermissions = new RxPermissions(context);
        mBuilder.setLength(0);
        sRxPermissions.request(permissions).subscribe(granted -> {
            int checkResult = checkOp(context, 27);
            if (checkResult != AppOpsManager.MODE_IGNORED && granted) {
                mPermissionCallBack.result("");
            } else {
                mBuilder.append("在设置-应用-丸子视频-权限管理中开启\n");
                mBuilder.append(PermissionData.get(permissions[0]));
                mBuilder.append("\n");
                mBuilder.append("以正常使用此功能");
                openSettingDialog(context, mBuilder.toString());
            }
        });
    }

    /**
     * 申请单个录音权限
     * 低于6.0版本的，国产机自带的权限系统
     */
    public void requestAudioByLollipop(AppCompatActivity context, String... permissions) {
        sRxPermissions = new RxPermissions(context);
        mBuilder.setLength(0);
        sRxPermissions.request(permissions).subscribe(granted -> {
            int checkResult = checkOp(context, 27);
            if (checkResult != AppOpsManager.MODE_IGNORED && granted) {
                mPermissionCallBack.result("");
            } else {
                mBuilder.append("在设置-应用-丸子视频-权限管理中开启\n");
                mBuilder.append(PermissionData.get(permissions[0]));
                mBuilder.append("\n");
                mBuilder.append("以正常使用此功能");
                openSettingDialog(context, mBuilder.toString());
            }
        });
    }

    public void requestCameraAndAudioByLollipop(AppCompatActivity context, String... permissions) {
        sRxPermissions = new RxPermissions(context);
        mBuilder.setLength(0);
        sRxPermissions.request(permissions).subscribe(granted -> {
            //26是照相机权限
            int checkResult1 = checkOp(context, 26);
            if (checkResult1 == AppOpsManager.MODE_IGNORED) {
                mBuilder.append("在设置-应用-丸子视频-权限管理中开启\n");
                mBuilder.append(PermissionData.get(permissions[0]));
                mBuilder.append("\n");
            }
            //27是录音权限
            int checkResult2 = checkOp(context, 27);
            if (checkResult2 == AppOpsManager.MODE_IGNORED) {
                if (mBuilder.length() == 0) {
                    mBuilder.append("在设置-应用-丸子视频-权限管理中开启\n");
                }
                mBuilder.append(PermissionData.get(permissions[1]));
                mBuilder.append("\n");
                mBuilder.append("以正常使用此功能");
                openSettingDialog(context, mBuilder.toString());
                mPermissionCallBack.result("");
            } else {
                if (mBuilder.length() != 0) {
                    mBuilder.append("以正常使用此功能");
                    openSettingDialog(context, mBuilder.toString());
                }
                mPermissionCallBack.result("");
            }
        });
    }

    public void openSettingDialog(Context context, String message) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = new AlertDialog.Builder(context).setMessage(message)
                .setTitle("权限申请")
                .setPositiveButton("确定", (dialog, which) -> navigateToAppDetailSettings(context))
                .setNegativeButton("取消", null)
                .create();
        mDialog.show();
    }

    public void openSettingDialog(Context context, String message,
                                  DialogInterface.OnClickListener onPositiveClickListener,
                                  DialogInterface.OnClickListener onNegativeClickListener) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = new AlertDialog.Builder(context).setMessage(message)
                .setTitle("权限申请")
                .setPositiveButton("确定", onPositiveClickListener)
                .setNegativeButton("取消", onNegativeClickListener)
                .create();
        mDialog.show();
    }

    public void setPermissionResultListener(PermissionCallBack callBack) {
        this.mPermissionCallBack = callBack;
    }

    public void navigateToAppDetailSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }
}
