package com.aserbao.aserbaosandroid.ui.rv.commonRecyclerView.baseAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseAdapterActivity extends AppCompatActivity {

    @BindView(R.id.base_adapter_rv)
    RecyclerView baseAdapterRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }
}
