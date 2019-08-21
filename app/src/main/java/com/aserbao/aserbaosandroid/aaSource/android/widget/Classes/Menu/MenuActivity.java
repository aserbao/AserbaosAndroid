package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu.MenuDemo.MenuDemoActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class MenuActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Menu菜单", MenuDemoActivity.class));
    }
}
