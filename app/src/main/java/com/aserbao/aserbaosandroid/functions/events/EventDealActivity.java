package com.aserbao.aserbaosandroid.functions.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.ScrollEventActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class EventDealActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("滚动冲突处理", ScrollEventActivity.class));
    }
}
