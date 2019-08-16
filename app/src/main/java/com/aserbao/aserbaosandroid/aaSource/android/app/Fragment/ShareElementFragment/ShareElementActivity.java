package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;

/**
 * https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+blogspot%2FhsDu+%28Android+Developers+Blog%29
 */
public class ShareElementActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        for (int i = 0; i < ImageSource.iamgeUrl.length; i++) {
            mBaseRecyclerBeen.add(new BaseRecyclerBean(i,ImageSource.iamgeUrl[i]));
        }
    }

    @Override
    public void itemClickBack(View view, int position) {
        Toast.makeText(mContext, "po", Toast.LENGTH_SHORT).show();
        switch (position){
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
