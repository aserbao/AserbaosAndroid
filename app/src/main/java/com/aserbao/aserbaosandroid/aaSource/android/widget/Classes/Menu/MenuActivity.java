package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu.MenuDemo.MenuDemoActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class MenuActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Menu菜单", MenuDemoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
