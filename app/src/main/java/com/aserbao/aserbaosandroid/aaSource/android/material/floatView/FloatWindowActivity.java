package com.aserbao.aserbaosandroid.aaSource.android.material.floatView;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaSource.android.material.floatView.floatView.FloatView;
import com.aserbao.aserbaosandroid.aaSource.android.material.floatView.launchWindow.SLaunchFloatWindowService;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class FloatWindowActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("添加悬浮窗",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("显示旁边的悬浮窗",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                startFloatWindow();
                break;
            case 1:
                showFloatView();
                break;
        }
    }

    public void startFloatWindow(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(FloatWindowActivity.this)) {
                Intent intent = new Intent(FloatWindowActivity.this, SLaunchFloatWindowService.class);
                Toast.makeText(FloatWindowActivity.this,"已开启Toucher", Toast.LENGTH_SHORT).show();
                startService(intent);
                finish();
            } else {
                //若没有权限，提示获取.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(FloatWindowActivity.this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        } else {
            //SDK在23以下，不用管.
            Intent intent = new Intent(FloatWindowActivity.this, SLaunchFloatWindowService.class);
            startService(intent);
            finish();
        }
    }

    private void showFloatView() {
        FloatView floatView = new FloatView(this);
        addViewToFrameLayout(floatView,false,true, true);
    }
}
