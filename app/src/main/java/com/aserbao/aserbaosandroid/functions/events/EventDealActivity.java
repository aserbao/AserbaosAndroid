package com.aserbao.aserbaosandroid.functions.events;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.events.onTouch.OnTouchActivity;
import com.aserbao.aserbaosandroid.functions.events.onTouch.double_recycler_nest.DoubleRecyclerNestActivity;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.RecyclerViewEventActivity;
import com.aserbao.aserbaosandroid.functions.events.scrollEvent.ScrollEventActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class EventDealActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("onTouch事件处理", OnTouchActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("滚动冲突处理", ScrollEventActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView滚动冲突处理", RecyclerViewEventActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("RecyclerView嵌套冲突处理", DoubleRecyclerNestActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
