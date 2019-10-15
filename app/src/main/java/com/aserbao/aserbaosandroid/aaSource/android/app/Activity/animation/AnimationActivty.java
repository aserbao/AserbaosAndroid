package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.officalTransition.AActivityOptionsActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.transition.TransitionActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition.AAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byTheme.AByThemeAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.sideAnimation.SlideItemAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment.ShareElementActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;

public class AnimationActivty extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("overridePendingTransition", AAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置Theme", AByThemeAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置ActivityOption", AActivityOptionsActivity.class));
        mClassBeen.add(new ClassBean("Fragment共享元素", ShareElementActivity.class));
        mClassBeen.add(new ClassBean("Activity共享元素", AShareModuleActivity.class));
        mClassBeen.add(new ClassBean("下滑退出", RecyclerViewEventActivity.class));
        mClassBeen.add(new ClassBean("Transition的使用", TransitionActivity.class));
        mClassBeen.add(new ClassBean("列表Item滑动带着Fragment滑出", SlideItemAnimationActivity.class));
    }
}
