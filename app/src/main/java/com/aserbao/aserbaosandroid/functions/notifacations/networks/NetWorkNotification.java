package com.aserbao.aserbaosandroid.functions.notifacations.networks;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class NetWorkNotification extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("开启网络监听"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("取消网络监听"));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                register();
                break;
            case 1:
                unregister();
                break;
        }
    }
    NetWorkStateReceiver netWorkStateReceiver;
    //在onResume()方法注册
    @Override
    protected void onResume() {

        super.onResume();
    }

    public void register(){
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
    }

    public void unregister(){
        unregisterReceiver(netWorkStateReceiver);
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }

}
