package com.aserbao.aserbaosandroid.aaSource.android.material.floatView;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.material.floatView.launchWindow.SLaunchFloatWindowService;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

public class FloatWindowActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("添加悬浮窗"));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                startFloatWindow();
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
}
