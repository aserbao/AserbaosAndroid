
package com.aserbao.aserbaosandroid.ui.rv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.CommonAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;
import com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.MoveToDeleteActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecyclerViewActivity extends BaseActivity {

    private List<ClassBean> mClassBeen = new ArrayList<>();
    @BindView(R.id.opengl_recycler_view)
    RecyclerView mOpenglRecyclerView;
    public CommonAdapter mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGetData();
        initView();
    }

    @Override
    public int setContentView() {
        return R.layout.activity_open_gl;
    }

    public void initGetData() {
        mClassBeen.add(new ClassBean("Item侧滑删除", MoveToDeleteActivity.class));

    }

    public void initView() {
        mCommonAdapter = new CommonAdapter(this,this, mClassBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
    }
}
