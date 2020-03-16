package com.aserbao.aserbaosandroid.functions.screen;

import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020-02-28
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.functions.screen
 */
public class ScreenActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("获取View旋转后在屏幕中的位置",0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:

                break;

        }
    }
}
