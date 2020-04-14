package com.example.base.base.extend;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener;
import com.example.base.utils.data.ASourceUtil;

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
        for (int i = 0; i < ASourceUtil.imageUrls.length; i++) {
            baseRecyclerBeen.add(new BaseRecyclerBean(ASourceUtil.imageUrls[i], viewType,i));
        }
        linearLayoutManager = new LinearLayoutManager(this, orientaion, false);
        baseRecyclerViewActivityAdapter = new BaseRecyclerViewActivityAdapter(this, this, baseRecyclerBeen, iBaseRecyclerItemClickListener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(baseRecyclerViewActivityAdapter);
    }
}
