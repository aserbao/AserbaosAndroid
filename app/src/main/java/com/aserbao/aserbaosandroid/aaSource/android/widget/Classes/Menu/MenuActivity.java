package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.Menu.MenuDemo.MenuDemoActivity;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

public class MenuActivity extends BaseActivity {


    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Menu菜单", MenuDemoActivity.class));
    }
}
