package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.data.ASourceUtil;
import com.example.base.utils.data.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators.FadeInDownAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemARCMAnimationActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("add"));
        mBaseSpinnerRecyclerBeen.add(new BaseRecyclerBean("remove"));
       /* for (int i = 0; i < 10; i++) {
            mBaseRecyclerBean.add(new BaseRecyclerBean(String.valueOf(i)));
        }*/

        mBaseRecyclerBean = ASourceUtil.getStaticRecyclerViewData(mBaseRecyclerBean, 1);
        changeOrientation(StaticFinalValues.LINEAR_LAYOUTMANAGER, LinearLayoutManager.HORIZONTAL);
//        setMode(LinearLayoutManager.HORIZONTAL);
        mOpenglRecyclerView.setItemAnimator(new FadeInDownAnimator());
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (comeFrom){
            case COME_FROM_SPINNER:
                mCommonAdapter.removeRange(0,2);
                break;
            default:
                if (mCommonAdapter != null) {
                    if (isLongClick) {
                        int itemCount = mCommonAdapter.getItemCount();
                        BaseRecyclerBean baseRecyclerBean = new BaseRecyclerBean(String.valueOf(itemCount));
                        mCommonAdapter.add(0,baseRecyclerBean);
                    }else{
                        mCommonAdapter.removeRange(0,2);
                        List<BaseRecyclerBean> mBaseList = new ArrayList<>();
                        int[] imageUrl = ASourceUtil.imageUrls;
                        for (int i = 0; i < 2; i++) {
                            mBaseList.add(new BaseRecyclerBean(imageUrl[new Random().nextInt(imageUrl.length)], StaticFinalValues.VIEW_HOLDER_IMAGE_100H));
                        }
                        mCommonAdapter.addRange(mCommonAdapter.getItemCount(),mBaseList);
//                        mCommonAdapter.remove(position);
                    }
                }

        }
    }
}
