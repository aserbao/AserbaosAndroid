package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/13 11:39 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode
 */
public abstract class BaseLaunchModeActivity extends AppCompatActivity {
    private static final String TAG = "BaseLaunchModeActivity";
    @BindView(R.id.launch_mode_tv)
    public TextView mLaunchModeTv;
    private ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);
        setTextViewContent();
        mActivityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = mActivityManager.getRunningTasks(10);

        for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
            String shortClassName = runningTask.topActivity.getShortClassName();
            String baseShortClassName = runningTask.baseActivity.getShortClassName();
            Log.e(TAG, "onCreate: " +shortClassName + "  baseShortClassName =  " + baseShortClassName + " num = " + runningTask.numActivities  + " numRuning = " + runningTask.numRunning);
        }
    }

    public abstract void setTextViewContent();

    @OnClick({R.id.clear_all_activity_btn,R.id.standard_btn, R.id.singleInstance_btn, R.id.singletop_btn, R.id.singletask_btn})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.clear_all_activity_btn:
                /*List<ActivityManager.AppTask> appTasks = mActivityManager.getAppTasks();
                for (ActivityManager.AppTask appTask : appTasks) {
//                    appTask.finishAndRemoveTask();
                    ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
                    CharSequence description = taskInfo.description;
                    String topActivityClassName = taskInfo.topActivity.getClassName();
                    int persistentId = taskInfo.persistentId;
                    Log.e(TAG, "description = " + description + "onViewClicked: " +  " topActivityClassName = " + topActivityClassName + " persistentId = " + String.valueOf(persistentId) );
                }*/

                List<Activity> activitiesByApplication = getActivitiesByApplication(AserbaoApplication.instance);
                for (Activity activity : activitiesByApplication) {
                    ComponentName componentName = activity.getComponentName();
                    String packageName = activity.getPackageName();
                    Log.e(TAG, "onViewClicked: " + activity.getLocalClassName()  + " componentName =  " + componentName + " packageName = " + packageName);
                }
                break;
            case R.id.standard_btn:
                intent = new Intent(BaseLaunchModeActivity.this, StandardActivity.class);
                startActivity(intent);
                break;
            case R.id.singleInstance_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
                break;
            case R.id.singletop_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleTopActivity.class);
                startActivity(intent);
                break;
            case R.id.singletask_btn:
                intent = new Intent(BaseLaunchModeActivity.this, SingleTaskActivity.class);
                startActivity(intent);
                break;
        }
    }

    private static List<Activity> getActivitiesByApplication(Application application) {
        List<Activity> list = new ArrayList<>();
        try {
            Class<Application> applicationClass = Application.class;
            Field mLoadedApkField = applicationClass.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(application);
            Class<?> mLoadedApkClass = mLoadedApk.getClass();
            Field mActivityThreadField = mLoadedApkClass.getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            Object mActivityThread = mActivityThreadField.get(mLoadedApk);
            Class<?> mActivityThreadClass = mActivityThread.getClass();
            Field mActivitiesField = mActivityThreadClass.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Object mActivities = mActivitiesField.get(mActivityThread);
            // 注意这里一定写成Map，低版本这里用的是HashMap，高版本用的是ArrayMap
            if (mActivities instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Object, Object> arrayMap = (Map<Object, Object>) mActivities;
                for (Map.Entry<Object, Object> entry : arrayMap.entrySet()) {
                    Object value = entry.getValue();
                    Class<?> activityClientRecordClass = value.getClass();
                    Field activityField = activityClientRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Object o = activityField.get(value);
                    list.add((Activity) o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

}
