
package com.aserbao.aserbaosandroid.ui.rv;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.MoveToDeleteActivity;

public class RecyclerViewActivity extends BaseActivity {




    @Override
    public int setContentView() {
        return R.layout.base_activity;
    }

    public void initGetData() {
        mClassBeen.add(new ClassBean("Item侧滑删除", MoveToDeleteActivity.class));
        mClassBeen.add(new ClassBean("打造公用的Adapter", MoveToDeleteActivity.class));

    }

}
