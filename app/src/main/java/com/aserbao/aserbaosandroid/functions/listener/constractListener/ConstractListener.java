package com.aserbao.aserbaosandroid.functions.listener.constractListener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ConstractListener extends BaseRecyclerViewActivity {

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, ConstractListener.class);
        activity.startActivity(intent);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("开启联系人变动监听"));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                startService(new Intent(this,ContactIntentService.class));
                break;
        }
    }
}
