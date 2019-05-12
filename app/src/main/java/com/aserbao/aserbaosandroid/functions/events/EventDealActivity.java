package com.aserbao.aserbaosandroid.functions.events;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.events.onTouch.OnTouchActivity;
import com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest.DoubleRecyclerNestActivity;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.ScrollEventActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class EventDealActivity extends BaseActivity {

    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("onTouch事件处理", OnTouchActivity.class));
        mClassBeen.add(new ClassBean("滚动冲突处理", ScrollEventActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView滚动冲突处理", RecyclerViewEventActivity.class));
        mClassBeen.add(new ClassBean("RecyclerView嵌套冲突处理", DoubleRecyclerNestActivity.class));
    }

}
