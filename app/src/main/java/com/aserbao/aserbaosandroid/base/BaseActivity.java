package com.aserbao.aserbaosandroid.base;

import android.Manifest;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.AUtils.utils.PermissionUtils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.adapters.BaseActivityAdapter;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*基类*/
public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.opengl_recycler_view)
    public RecyclerView mOpenglRecyclerView;

    public BaseActivityAdapter mCommonAdapter;

    public List<ClassBean> mClassBeen = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        ButterKnife.bind(this);
        initGetData();
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new  String[]{Manifest.permission.CHANGE_CONFIGURATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},0);
        }
    }



    public abstract  void initGetData();


    public void initView() {
        mCommonAdapter = new BaseActivityAdapter(this,this, mClassBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
    }

}
