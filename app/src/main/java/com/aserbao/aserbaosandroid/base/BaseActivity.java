package com.aserbao.aserbaosandroid.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.CommonAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*基类*/
public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.opengl_recycler_view)
    public RecyclerView mOpenglRecyclerView;

    public CommonAdapter mCommonAdapter;

    public List<ClassBean> mClassBeen = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);

        initGetData();
        initView();
    }

    public abstract  int setContentView();
    public abstract  void initGetData();


    public void initView() {
        mCommonAdapter = new CommonAdapter(this,this, mClassBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
    }

}
