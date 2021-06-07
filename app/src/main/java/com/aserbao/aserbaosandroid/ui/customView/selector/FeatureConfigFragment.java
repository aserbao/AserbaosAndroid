package com.aserbao.aserbaosandroid.ui.customView.selector;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.RvItemColorSelectorLlBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelColorBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelFeatureBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.SelColorAdapter;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature.SelFeatureAdapter;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeatureConfigureVM;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.SELCOLORBEANS;

/**
* @Created time:2021/6/6 5:14 PM
* @author: aserbao
* @description:
**/
public class FeatureConfigFragment extends Fragment {
    public static final String POSITION = "position";
    private  RvItemColorSelectorLlBinding binding;
    private Context mContext;
    private RoleConfig mRoleConfigure;
    private FeatureConfigureVM featureConfigureVM;
    private int mPosition;
    /**
     * 数据源
     */
    private  ArrayList<SelColorBean> selColorBeans = new ArrayList<>();

    public static FeatureConfigFragment getInstance(int position) {
        FeatureConfigFragment fragment = new FeatureConfigFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt(POSITION);
        }
        mContext = getContext();
        featureConfigureVM = new ViewModelProvider(getActivity()).get(FeatureConfigureVM.class);
        mRoleConfigure = featureConfigureVM.getRoleConfig(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = RvItemColorSelectorLlBinding.inflate(LayoutInflater.from(getContext()));
        View view = inflater.inflate(R.layout.custom_color_selector_ll, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selColorBeans.addAll(SELCOLORBEANS);

        initColorRV(binding.rvItemColorSelRV,selColorBeans);
        initSelFeatureRV(binding.rvItemFeatureSelRV,mRoleConfigure.getFeatureBeans());
        fieldObserver();
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
                mRoleConfigure.setSelColor(beans.color);
            }
        });
        colorSelRVAdapter.setMaxShowLength(6);
        colorSelRVAdapter.setHasFooter(true);
        colorSelRVAdapter.setmHeadFooterClickerListener(new HeadFooterClickerListener() {
            @Override
            public void itemClick(boolean isHead) {
                initMoreColorRv(rv,colorBeans);
                featureConfigureVM.changeFeatureStatus(FeatureConfigureVM.FEATURE_MORE_COLOR_STATUS);
            }
        });
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
                mRoleConfigure.setSelColor(beans.color);
            }
        });
        colorSelRVAdapter.setHasFooter(false);
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
                mRoleConfigure.setSelSource(beans.path);
            }
        });
        featureSelRVAdapter.setHasHeader(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(featureSelRVAdapter);
    }

    /**
     * 属性观察
     */
    private void fieldObserver(){
        featureConfigureVM.featureStatus.observe(this,status -> {
            switch (status){
                case FeatureConfigureVM.FEATURE_DEFAULT_STATUS:
                    initColorRV(binding.rvItemColorSelRV,selColorBeans);
                    break;
            }
        });
    }
}