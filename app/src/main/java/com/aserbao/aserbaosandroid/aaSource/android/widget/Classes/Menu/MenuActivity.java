package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu.MenuDemo.MenuDemoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class MenuActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Menu菜单", MenuDemoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
