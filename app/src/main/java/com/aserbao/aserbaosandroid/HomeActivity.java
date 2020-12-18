package com.aserbao.aserbaosandroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.aserbao.aserbaosandroid.AUtils.AUI.popUtil.PopupManager;
import com.aserbao.aserbaosandroid.AUtils.utils_realize.AUtilsRealizeActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.lifeCycle.LifeCycleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.capture.Camera2CaptuerActivity;
import com.aserbao.aserbaosandroid.aaSource.android.os.handler.principle_for_handler.PrincipleHanlderActivity;
import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.progressBar.ProgressBarActivity;
import com.aserbao.aserbaosandroid.aaSource.java.util.concurrent.FutureAct;
import com.aserbao.aserbaosandroid.aaThird.rxJava3.api.RxJava3ApiActivity;
import com.aserbao.aserbaosandroid.algorithm.list.AboutListSortActivty;
import com.aserbao.aserbaosandroid.audioAndVideo.AudioAndVideoActivity;
import com.aserbao.aserbaosandroid.aaSource.android.AndroidActivity;
import com.aserbao.aserbaosandroid.aaThird.ThirdActivity;
import com.aserbao.aserbaosandroid.algorithm.AlgorithmActivity;
//import com.aserbao.aserbaosandroid.functions.ffmpeg.RxFFmpegAct;
import com.aserbao.aserbaosandroid.kotlin.base.KotlinBaseUseAct;
import com.aserbao.aserbaosandroid.kotlin.coroutine.CoroutineActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.RecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext.CustomEditTextAct;
import com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ACustomToastActivity;
import com.aserbao.camera.camerax.CameraXPreviewActivity;
import com.aserbao.common.ui.act.CommonUIActivity;
import com.aserbao.jetpack.architecture.navigation.NavigationAct;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.designMode.DesignModeActivity;
import com.aserbao.aserbaosandroid.functions.FunctionsActivity;
import com.aserbao.aserbaosandroid.functions.ffmpeg.FFmpegActivity;
import com.aserbao.aserbaosandroid.opengl.OpenGlActivity;
import com.aserbao.aserbaosandroid.other.OthersActivity;
import com.aserbao.aserbaosandroid.test.TestActivity;
import com.aserbao.aserbaosandroid.ui.UIActivity;

import java.lang.reflect.Method;

/**
 * 1. 如果版本号大于 10的话，直接exit.
 * 2. 如果是release 版本，保存当前时间，若大于当前时间+20天，设一个随机时间后，直接exit.
 * 3. 如果是debug版本，一个月之后，直接exit.
 */
public class HomeActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Android基础类", AndroidActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("第三方库的使用", ThirdActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试", TestActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("OpenGl", OpenGlActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("策略模式", DesignModeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("多媒体", AudioAndVideoActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("View", UIActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("功能", FunctionsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("常用数据", AUtilsRealizeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("其他", OthersActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ffmpeg", FFmpegActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("算法", AlgorithmActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Common", CommonUIActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面", NavigationAct.class));
//        mBaseRecyclerBean.add(new BaseRecyclerBean("当前调试的界面",0));


        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "测试", Toast.LENGTH_SHORT).show();
            }
        });
        Button button1 = button;
        addViewToFrameLayout(button1,false,false,false);
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
//        ARouter.getInstance().build( RouterConfig.JUMP_TO_CAMERA_ACTIVITY2).navigation();
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, 0 );
       /* System.exit(0);
        Runtime.getRuntime().exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());*/
        boolean apkInDebug = isApkInDebug(this);
    }


    /**
     * 当前是否在正式环境中
     * @param context
     * @return
     */
      public static boolean isApkInDebug(Context context) {
        try {
                ApplicationInfo info = context.getApplicationInfo();
                  return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
              } catch (Exception e) {
                   return false;
               }
      }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }
}
