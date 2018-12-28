package com.aserbao.aserbaosandroid.ui.recyclerView.scale_smooth_slide.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.GalleryRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/25
 * email: 1142803753@qq.com
 */
public abstract class ScaleBaseActivity extends AppCompatActivity {
    @BindView(R.id.scale_smooth_slide_recycler_view)
    public RecyclerView mScaleSmoothSlideRecyclerView;
    @BindView(R.id.scale_smooth_slide_gallery_view)
    public GalleryRecyclerView mScaleSmoothSlideGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_smooth_slide);
        ButterKnife.bind(this);
        init();
    }

    public abstract void init();
}
