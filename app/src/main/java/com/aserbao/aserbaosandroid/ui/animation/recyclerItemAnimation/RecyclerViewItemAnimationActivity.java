package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.adapters.AnimationAdapter;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.linearManagers.ALinearManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewItemAnimationActivity extends AppCompatActivity {

    @BindView(R.id.animation_recycler_view)
    RecyclerView mRecyclerView;
    private AnimationAdapter animationAdapter;
    private ALinearManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_animation);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        animationAdapter = new AnimationAdapter(this);
        linearLayoutManager = new ALinearManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(animationAdapter);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
    }

    public void btn_next(View view) {
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        mRecyclerView.smoothScrollToPosition(firstVisibleItemPosition + 1);
    }

    /*public void initGalleryRecycle(){
        mRecyclerView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(14, 40)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.50f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP);            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
    }*/



}
