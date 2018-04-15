package com.aserbao.aserbaosandroid.ui.recyclerview.aEffect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EffectRVActivity extends AppCompatActivity {

    @BindView(R.id.effect_rv)
    RecyclerView mEffectRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect_rv);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 0;
            }
        });
        mEffectRv.setLayoutManager(gridLayoutManager);
    }
}
