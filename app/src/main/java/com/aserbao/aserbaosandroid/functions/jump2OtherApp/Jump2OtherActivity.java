package com.aserbao.aserbaosandroid.functions.jump2OtherApp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.util.Collections;
import java.util.List;

public class Jump2OtherActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("抖音"));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                /*Intent intent1 = new Intent();
                ComponentName componentName = new ComponentName("com.ss.android.ugc.aweme","com.ss.android.ugc.aweme.main.MainActivity");
                intent1.setComponent(componentName);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);*/
                jump2Other("");
                break;
        }
    }

    private static final String TAG = "Jump2OtherActivity";
    private void jump2Other(String packageName) {
        //同AndroidManifest中主入口Activity一样
        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        //得到一个PackageManager的对象
        PackageManager packageManager =getApplicationContext().getPackageManager();
        //获取到主入口的Activity集合
        List<ResolveInfo> mlist = packageManager.queryIntentActivities(intent,0);

        Collections.sort(mlist,new ResolveInfo.DisplayNameComparator(packageManager));

        for (ResolveInfo res :mlist){
            String pkg = res.activityInfo.packageName;
            String cls = res.activityInfo.name;
            Log.e(TAG, "jump2Other: activityInfo.packageName = " + pkg + " activityInfo.name = " + cls );
            if (pkg.contains("com.tencent.mm")){
                ComponentName componentName = new ComponentName(pkg,cls);
                Intent intent1 = new Intent();
                intent1.setComponent(componentName);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        }
    }
}
