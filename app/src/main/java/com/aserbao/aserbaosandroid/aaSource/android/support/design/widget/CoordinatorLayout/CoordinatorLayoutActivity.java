package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CoordinatorLayout;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.AUI.popUtil.PopupManager;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CoordinatorLayoutActivity extends BaseRecyclerViewActivity {


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_coordinator_layout);
    }*/

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用CoordinatorLayout动态调整FloatingActionButton的位置",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CoordinatorLayout嵌套AppBarLayout",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CoordinatorLayout+AppBarLayout的使用",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("简单的CoordinatorLayout的使用",10));

    }

    @Override
    public void itemClickBack(View v, int position, boolean isLongClick) {
        View view;
        switch (position){
            case 0:
                view = LayoutInflater.from(this).inflate(R.layout.coordinator_and_floating_action_button_layout, null);
                View coorfinator_anchor_fl = view.findViewById(R.id.coordinator_anchor_fl);
                view.findViewById(R.id.coordinator_fabtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(coorfinator_anchor_fl, "显示", Snackbar.LENGTH_SHORT).show();
                    }
                });
                addViewToFrameLayoutFullScreen(view);
                break;
            case 1:
                view = LayoutInflater.from(this).inflate(R.layout.coordinator_and_appbar_layout, null);
                ImageView appbarScrollIv = (ImageView) view.findViewById(R.id.appbar_scroll_iv);

                if (isLongClick){
                    new PopupManager(this).
                }
                AppBarLayout.LayoutParams appbarScrollIvLayoutParams = (AppBarLayout.LayoutParams) appbarScrollIv.getLayoutParams();
                appbarScrollIvLayoutParams.setScrollFlags();


                addViewToFrameLayoutFullScreen(view);
                break;
            case 2:
                View simpleView = LayoutInflater.from(this).inflate(R.layout.coordinatorlayout_and_toolbar_and_collapsing_layout, null);
                addViewToFrameLayoutFullScreen(simpleView);
                break;
            case 10:
                View view1 = LayoutInflater.from(this).inflate(R.layout.simple_coordinator_layout, null);
                addViewToFrameLayoutFullScreen(view1);
                break;
        }
    }
}
