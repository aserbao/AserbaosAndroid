package com.example.base.utils.permission;

import android.Manifest;

import java.util.HashMap;

/**
 * 权限数据
 */
public class PermissionData {
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION =
            Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION =
            Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE =
            Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE =
            Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_RECORD_AUDIO_EXPLAIN = " · 通话录音权限";
    public static final String PERMISSION_READ_CONTACTS_EXPLAIN = " · 联系人权限";
    public static final String PERMISSION_READ_PHONE_STATE_EXPLAIN = " · 手机号码权限";
    public static final String PERMISSION_CALL_PHONE_EXPLAIN = " · 拨号权限";
    public static final String PERMISSION_CAMERA_EXPLAIN = " · 照相机权限";
    public static final String PERMISSION_ACCESS_FINE_LOCATION_EXPLAIN = " · GPS定位权限";
    public static final String PERMISSION_ACCESS_COARSE_LOCATION_EXPLAIN = "";
    public static final String PERMISSION_READ_EXTERNAL_STORAGE_EXPLAIN = " · 读取本地文件权限";
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE_EXPLAIN = " · 写入本地文件权限";
    private static HashMap<String, String> dataList = new HashMap<>();

    static {
        dataList.put(PERMISSION_RECORD_AUDIO, PERMISSION_RECORD_AUDIO_EXPLAIN);
        dataList.put(PERMISSION_READ_CONTACTS, PERMISSION_READ_CONTACTS_EXPLAIN);
        dataList.put(PERMISSION_READ_PHONE_STATE, PERMISSION_READ_PHONE_STATE_EXPLAIN);
        dataList.put(PERMISSION_CALL_PHONE, PERMISSION_CALL_PHONE_EXPLAIN);
        dataList.put(PERMISSION_CAMERA, PERMISSION_CAMERA_EXPLAIN);
        dataList.put(PERMISSION_ACCESS_FINE_LOCATION, PERMISSION_ACCESS_FINE_LOCATION_EXPLAIN);
        dataList.put(PERMISSION_ACCESS_COARSE_LOCATION, PERMISSION_ACCESS_COARSE_LOCATION_EXPLAIN);
        dataList.put(PERMISSION_READ_EXTERNAL_STORAGE, PERMISSION_READ_EXTERNAL_STORAGE_EXPLAIN);
        dataList.put(PERMISSION_WRITE_EXTERNAL_STORAGE, PERMISSION_WRITE_EXTERNAL_STORAGE_EXPLAIN);
    }

    public static String get(String key) {
        if (dataList.get(key) == null) {
            return "";
        }
        return dataList.get(key);
    }
}
