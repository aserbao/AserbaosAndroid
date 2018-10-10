package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.adapters.AnimationAdapter;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.animation3DRecyclerView.A3DReyclcerView;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.animation3DRecyclerView.SquareRecyclerView;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.AnimManager;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.GalleryRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewItemAnimationActivity extends AppCompatActivity {

    @BindView(R.id.animation_recycler_view)
    SquareRecyclerView mRecyclerView;
    private AnimationAdapter animationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_animation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        animationAdapter = new AnimationAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(animationAdapter);

    }

    /*public void initGalleryRecycle(){
        mRecyclerView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(14, 40)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.50f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP);            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
    }*/



}
