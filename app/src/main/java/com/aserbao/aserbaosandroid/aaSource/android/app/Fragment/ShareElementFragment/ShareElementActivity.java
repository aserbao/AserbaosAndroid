package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment;

import androidx.recyclerview.widget.GridLayoutManager;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.data.ASourceUtil;

/**
 * https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+blogspot%2FhsDu+%28Android+Developers+Blog%29
 */
public class ShareElementActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
//        mBaseRecyclerBean = ASourceUtil.getStaticRecyclerViewData(mBaseRecyclerBean);
        mBaseRecyclerEmptyContainer.setVisibility(View.VISIBLE);
        /*ViewGroup.LayoutParams layoutParams = mBaseRecyclerEmptyContainer.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams){
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0,0,0,0);
        }
        mBaseRecyclerEmptyContainer.setLayoutParams(layoutParams);*/

        mBaseRecyclerBean.add(new BaseRecyclerBean("横向的",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("纵向的",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("网格的",3));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
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

    public void initViewForLinear() {
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBean, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mOpenglRecyclerView.setLayoutManager(gridLayoutManager);
        mOpenglRecyclerView.setAdapter(mCommonAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());
    }

}
