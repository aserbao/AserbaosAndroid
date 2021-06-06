package com.aserbao.aserbaosandroid.ui.customView.selector;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.databinding.RvItemColorSelectorLlBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelColorBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelFeatureBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.SelColorAdapter;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature.SelFeatureAdapter;

import java.util.ArrayList;

import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.SELCOLORBEANS;

/**
 * @Created time:2021/6/3 6:11 PM
 * @author: aserbao
 * @description: 简单的选择器
 **/
public class FeatureConfigSelVH extends RecyclerView.ViewHolder {
    private final RvItemColorSelectorLlBinding binding;
    private Context mContext;
    private RoleConfig mRoleConfigure;
    private View mView;

    public FeatureConfigSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemColorSelectorLlBinding.bind(itemView);
    }
    
    
    public void setDataSource(Context context,RoleConfig roleConfig,int position){
        if (roleConfig == null) return;
        mContext = context;
        mRoleConfigure = roleConfig;
        ArrayList<SelColorBean> selColorBeans = new ArrayList<>();
        selColorBeans.addAll(SELCOLORBEANS);
        
        initColorRV(binding.rvItemColorSelRV,selColorBeans);
        initSelFeatureRV(binding.rvItemFeatureSelRV,mRoleConfigure.getFeatureBeans());
    }
    
    
    SelColorAdapter colorSelRVAdapter;
    SelFeatureAdapter featureSelRVAdapter;

    /**
     * 显示单行颜色
     * @param rv
     * @param colorBeans
     */
    private void initColorRV(RecyclerView rv, ArrayList<SelColorBean> colorBeans){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        colorSelRVAdapter = new SelColorAdapter(mContext, colorBeans, new SelColorAdapter.ItemClickerListener() {
            @Override
            public void itemClick(SelColorBean beans, int position) {

            }
        });
        colorSelRVAdapter.setHasFooter(true);
        colorSelRVAdapter.setmHeadFooterClickerListener(new HeadFooterClickerListener() {
            @Override
            public void itemClick(boolean isHead) {
                initMoreColorRv(rv,colorBeans);
            }
        });
//        rv.setLayoutManager(gridLayoutManager);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(colorSelRVAdapter);
    }

    /**
     * 显示更多颜色
     * @param rv
     * @param colorBeans
     */
    private void initMoreColorRv(RecyclerView rv,ArrayList<SelColorBean> colorBeans){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,5);
        colorSelRVAdapter = new SelColorAdapter(mContext, colorBeans, new SelColorAdapter.ItemClickerListener() {
            @Override
            public void itemClick(SelColorBean beans, int position) {

            }
        });
        colorSelRVAdapter.setHasFooter(true);
        colorSelRVAdapter.setmHeadFooterClickerListener(new HeadFooterClickerListener() {
            @Override
            public void itemClick(boolean isHead) {
                initColorRV(rv,colorBeans);
            }
        });
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(colorSelRVAdapter);
    }

    /**
     * 选中的特征
     * @param rv
     * @param featureBeans
     */
    private void initSelFeatureRV(RecyclerView rv, ArrayList<SelFeatureBean> featureBeans){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,5);
        featureSelRVAdapter = new SelFeatureAdapter(mContext, featureBeans, new SelFeatureAdapter.ItemClickerListener() {
            @Override
            public void itemClick(SelFeatureBean beans, int position) {

            }
        });
        featureSelRVAdapter.setHasHeader(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(featureSelRVAdapter);
    }



    

}
