package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CoordinatorLayout;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.AUI.popUtil.PopupManager;
import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

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
        View addView;
        switch (position){
            case 0:
                addView = LayoutInflater.from(this).inflate(R.layout.coordinator_and_floating_action_button_layout, null);
                View coorfinator_anchor_fl = addView.findViewById(R.id.coordinator_anchor_fl);
                addView.findViewById(R.id.coordinator_fabtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(coorfinator_anchor_fl, "显示", Snackbar.LENGTH_SHORT).show();
                    }
                });
                addViewToFrameLayoutFullScreen(addView);
                break;
            case 1:
                addView = LayoutInflater.from(this).inflate(R.layout.coordinator_and_appbar_layout, null);
                ImageView appbarScrollIv = (ImageView) addView.findViewById(R.id.appbar_scroll_iv);
                AppBarLayout appBarLayout = (AppBarLayout) addView.findViewById(R.id.app_layout);
                if (isLongClick){
                    List<BaseRecyclerBean> mPopBaseRecyclerBeen = new ArrayList<>();
                    mPopBaseRecyclerBeen.add(new BaseRecyclerBean("scroll",0));
                    mPopBaseRecyclerBeen.add(new BaseRecyclerBean("enterAlways",1));
                    mPopBaseRecyclerBeen.add(new BaseRecyclerBean("enterAlwaysCollapsed,需要和enterAlways一起使用",2));
                    mPopBaseRecyclerBeen.add(new BaseRecyclerBean("exitUntilCollapsed",3));
                    mPopBaseRecyclerBeen.add(new BaseRecyclerBean("scroll|exitUntilCollapsed",4));
                    PopupManager popupManager = new PopupManager(this);
                    popupManager.showSelectedPop(this, mPopBaseRecyclerBeen, new IBaseRecyclerItemClickListener() {
                        @Override
                        public void itemClickBack(View view, int position, boolean isLongClick) {
                            popupManager.dismiss();
                            AppBarLayout.LayoutParams appbarScrollIvLayoutParams = (AppBarLayout.LayoutParams) appbarScrollIv.getLayoutParams();
                            switch (position){
                                case 0:
                                    appbarScrollIvLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                                    break;
                                case 1:
                                    appbarScrollIvLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                                    break;
                                case 2:
                                    appbarScrollIvLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                                    break;
                                case 3:
                                    appbarScrollIvLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                                    break;
                                case 4:
                                    appbarScrollIvLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                                    appBarLayout.setMinimumHeight(DisplayUtil.dip2px(100));
                                    break;
                            }
                            appbarScrollIv.setLayoutParams(appbarScrollIvLayoutParams);
                            addViewToFrameLayoutFullScreen(addView);
                        }
                    });
                }else {
                    addViewToFrameLayoutFullScreen(addView);
                }
                break;
            case 2:
                View simpleView = LayoutInflater.from(this).inflate(R.layout.coordinatorlayout_and_toolbar_and_collapsing_layout, null);
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) simpleView.findViewById(R.id.collapsingToolbarLayout);
                Toolbar tb = (Toolbar) simpleView.findViewById(R.id.toolbar);
                AppBarLayout simpleAppBarLayout = (AppBarLayout) simpleView.findViewById(R.id.app_bar_layout);

                setSupportActionBar(tb);//设置toolbar
                collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.LEFT);//设置收缩后标题的位置
                collapsingToolbarLayout.setExpandedTitleGravity(Gravity.LEFT);////设置展开后标题的位置
                collapsingToolbarLayout.setTitle("Hello");//设置标题的名字
                collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT);//设置收缩后标题的颜色

                /*Class<? extends CollapsingToolbarLayout> aClass = collapsingToolbarLayout.getClass();
                CollapsingToolbarLayout collapsingToolbarLayout1 = aClass.newInstance();*/


                simpleAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        Log.e(TAG, "onOffsetChanged: " + i );
                    }
                });

                addViewToFrameLayout(simpleView);
                break;
            case 10:
                View view1 = LayoutInflater.from(this).inflate(R.layout.simple_coordinator_layout, null);
                addViewToFrameLayoutFullScreen(view1);
                break;
        }
    }

    private static final String TAG = "CoordinatorLayoutActivi";


}
