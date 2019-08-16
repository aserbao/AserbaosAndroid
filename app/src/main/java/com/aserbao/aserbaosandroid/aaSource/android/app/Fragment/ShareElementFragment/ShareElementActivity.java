package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.comon.commonView.ImageViewFragment;

/**
 * https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+blogspot%2FhsDu+%28Android+Developers+Blog%29
 */
public class ShareElementActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBeen = ImageSource.getStaticRecyclerViewData(mBaseRecyclerBeen);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void itemClickBack(View view, int position) {
        jumpToFragment(view,position);
    }
    Fragment fragment;
    public void jumpToFragment(View view ,int position){

        ((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);

        ImageViewFragment fragment = new ImageViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(StaticFinalValues.POSITION,position);
        fragment.setArguments(bundle);
        getFragmentManager()
            .beginTransaction()
            .setReorderingAllowed(true) // Optimize for shared element transition
            .addSharedElement(view, view.getTransitionName())
            .replace(R.id.base_recycler_empty_container, fragment, ImageViewFragment.class
                .getSimpleName())
            .addToBackStack(null)
            .commit();
    }

    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ImageSource.getRandomImageId());
    }

}
