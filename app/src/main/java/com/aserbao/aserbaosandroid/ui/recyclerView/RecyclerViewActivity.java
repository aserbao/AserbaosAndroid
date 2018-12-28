
package com.aserbao.aserbaosandroid.ui.recyclerView;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView.AddRecyclerViewActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.MoveToDeleteActivity;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.ScaleSmoothSlideActivity;

public class RecyclerViewActivity extends BaseActivity {


    public void initGetData() {
        mClassBeen.add(new ClassBean("Item侧滑删除", MoveToDeleteActivity.class));
        mClassBeen.add(new ClassBean("打造公用的Adapter", MoveToDeleteActivity.class));
        mClassBeen.add(new ClassBean("左右滑动的RecyclerView", ScaleSmoothSlideActivity.class));
        mClassBeen.add(new ClassBean("空白的RecyclerView", AddRecyclerViewActivity.class));
    }

}
