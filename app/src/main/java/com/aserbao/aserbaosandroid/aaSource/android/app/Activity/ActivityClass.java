package com.aserbao.aserbaosandroid.aaSource.android.app.Activity;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.AnimationActivty;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.launchMode.LaunchModeActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.lifeCycle.LifeCycleActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class ActivityClass extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity的启动模式", LaunchModeActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity的跳转动画", AnimationActivty.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity的生命周期", LifeCycleActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("BasicActivity",BasicActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("BottomNavigationActivity",BottomNavigationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("FragmentViewModelActivity",FragmentViewModelActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("FullscreenActivity",FullscreenActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("LoginActivity",LoginActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Master/Detail flow",PersonListActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("NavigationDrawerActivity",NavigationDrawerActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("ScrollingActivity",ScrollingActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("SettingActivity",SettingsActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("TabbedActivity",TabbedActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
