package com.aserbao.thirdlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.aserbao.thirdlibs.tablayout.TabLayoutAct;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
/**
* @Created time:2021/6/7 3:18 PM
* @author: aserbao
* @description: 第三方库
**/
public class ThirdLibActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("TabLayout", TabLayoutAct.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }

}