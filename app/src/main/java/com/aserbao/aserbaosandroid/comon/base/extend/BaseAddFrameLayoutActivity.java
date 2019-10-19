package com.aserbao.aserbaosandroid.comon.base.extend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-18 15:46
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base
 */
public abstract class BaseAddFrameLayoutActivity extends BaseRecyclerViewActivity {

    private LinearLayoutManager linearLayoutManager;
    private BaseRecyclerViewActivityAdapter baseRecyclerViewActivityAdapter;
    private int orientaion = LinearLayoutManager.HORIZONTAL;
    /**
     * @param recyclerView
     * @param viewType  StaticFinalValues.VIEW_HOLDER_CIRCLE_IMAGE_ITEM
     */
    public void initRecyclerView(RecyclerView recyclerView, int viewType, IBaseRecyclerItemClickListener iBaseRecyclerItemClickListener){
        List<BaseRecyclerBean> baseRecyclerBeen = new ArrayList<>();
        for (int i = 0; i < ASourceUtil.iamgeUrl.length; i++) {
            baseRecyclerBeen.add(new BaseRecyclerBean(ASourceUtil.iamgeUrl[i], viewType,i));
        }
        linearLayoutManager = new LinearLayoutManager(this, orientaion, false);
        baseRecyclerViewActivityAdapter = new BaseRecyclerViewActivityAdapter(this, this, baseRecyclerBeen, iBaseRecyclerItemClickListener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(baseRecyclerViewActivityAdapter);
    }
}
