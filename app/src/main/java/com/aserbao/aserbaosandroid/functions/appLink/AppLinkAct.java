package com.aserbao.aserbaosandroid.functions.appLink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

/**
 * @author: aserbao
 * @date:2020/11/27 8:19 PM
 * @package:
 * @describle: 调试app link 和 deep link
 */
public class AppLinkAct extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        BaseRecyclerBean baseRecyclerBean = new BaseRecyclerBean("当前调试的界面", 0);
        mBaseRecyclerBean.add(baseRecyclerBean);
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


}