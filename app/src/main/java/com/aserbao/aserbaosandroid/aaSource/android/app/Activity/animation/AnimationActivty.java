package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.AActivityOptionsActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition.AAnimationActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byTheme.AByThemeAnimationActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class AnimationActivty extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("overridePendingTransition", AAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置Theme", AByThemeAnimationActivity.class));
        mClassBeen.add(new ClassBean("通过设置ActivityOption", AActivityOptionsActivity.class));
    }
}
