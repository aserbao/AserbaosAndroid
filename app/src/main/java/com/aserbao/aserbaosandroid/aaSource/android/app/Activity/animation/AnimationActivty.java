package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.officalTransition.AActivityOptionsActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition.AAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byTheme.AByThemeAnimationActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class AnimationActivty extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("overridePendingTransition", AAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置Theme", AByThemeAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置ActivityOption", AActivityOptionsActivity.class));
        mClassBeen.add(new ClassBean("通过共享元素", AShareModuleActivity.class));
    }
}
