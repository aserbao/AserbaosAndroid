package com.aserbao.aserbaosandroid.ui.buttons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButtonActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
//        mBaseRecyclerBean.add(new BaseRecyclerBean("SwitchButton",))
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
