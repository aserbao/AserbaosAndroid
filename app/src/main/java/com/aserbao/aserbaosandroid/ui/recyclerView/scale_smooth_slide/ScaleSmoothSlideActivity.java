package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.adapter.SmoothAdapter;
import com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.library.CardScaleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScaleSmoothSlideActivity extends AppCompatActivity {

    @BindView(R.id.scale_smooth_slide_recycler_view)
    RecyclerView mScaleSmoothSlideRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_smooth_slide);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        SmoothAdapter smoothAdapter = new SmoothAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mScaleSmoothSlideRecyclerView.setLayoutManager(linearLayoutManager);
        mScaleSmoothSlideRecyclerView.setAdapter(smoothAdapter);
        CardScaleHelper mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(200);
        mCardScaleHelper.attachToRecyclerView(mScaleSmoothSlideRecyclerView);

    }
}
