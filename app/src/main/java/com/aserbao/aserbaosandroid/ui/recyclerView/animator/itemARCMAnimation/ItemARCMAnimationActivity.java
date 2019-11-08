package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation;

import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators.FadeInDownAnimator;

public class ItemARCMAnimationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("add"));
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("remove"));
        for (int i = 0; i < 10; i++) {
            mBaseRecyclerBeen.add(new BaseRecyclerBean(String.valueOf(i)));
        }
        mOpenglRecyclerView.setItemAnimator(new FadeInDownAnimator());
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (comeFrom){
            case COME_FROM_SPINNER:

                break;
            default:
                if (mCommonAdapter != null) {
                    if (isLongClick) {
                        int itemCount = mCommonAdapter.getItemCount();
                        BaseRecyclerBean baseRecyclerBean = new BaseRecyclerBean(String.valueOf(itemCount));
                        mCommonAdapter.add(0,baseRecyclerBean);
                    }else{
                        mCommonAdapter.removeRange(0,5);
//                        mCommonAdapter.remove(position);
                    }
                }

        }
    }
}
