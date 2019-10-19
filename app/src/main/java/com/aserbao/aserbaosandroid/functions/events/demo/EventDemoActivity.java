package com.aserbao.aserbaosandroid.functions.events.demo;

import android.view.LayoutInflater;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-18 15:21
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.events.demo
 */
public class EventDemoActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Button和RecyclerView叠加的事件分发处理"));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {

    }
}
