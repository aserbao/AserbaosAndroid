package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CollapsingToolbarLayout;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollapsingToolbarLayoutActivity extends BaseRecyclerViewActivity {


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_coordinator_layout);
    }*/

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("简单的CoordinatorLayout的使用",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CoordinatorLayout+AppBarLayout的使用",2));

    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                View view1 = LayoutInflater.from(this).inflate(R.layout.simple_coordinator_layout, null);
                addViewToFrameLayoutFullScreen(view1);
                break;
            case 2:
                View simpleView = LayoutInflater.from(this).inflate(R.layout.coordinatorlayout_and_toolbar_layout, null);
                addViewToFrameLayoutFullScreen(simpleView);
                break;

        }
    }
}
