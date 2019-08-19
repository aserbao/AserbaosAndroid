package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
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
//        mBaseRecyclerBeen = ImageSource.getStaticRecyclerViewData(mBaseRecyclerBeen);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        /*ViewGroup.LayoutParams layoutParams = mBaseRecyclerEmptyContainer.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams){
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0,0,0,0);
        }
        mBaseRecyclerEmptyContainer.setLayoutParams(layoutParams);*/

        mBaseRecyclerBeen.add(new BaseRecyclerBean("横向的",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("纵向的",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("网格的",3));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 1:
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_recycler_empty_container, new GridFragment(), GridFragment.class.getSimpleName())
                    .commit();
                break;
            case 2:
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_recycler_empty_container, new ShareElementFragment(), ShareElementFragment.class.getSimpleName())
                    .commit();
                break;
            case 3:
                break;
        }
    }

    public void initView() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ImageSource.getRandomImageId());
    }

}
