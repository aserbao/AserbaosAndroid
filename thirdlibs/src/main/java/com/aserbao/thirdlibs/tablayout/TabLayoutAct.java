package com.aserbao.thirdlibs.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aserbao.common.ui.fragments.BaseViewPagerAct;
import com.aserbao.thirdlibs.databinding.TabLayoutSmartLayoutBinding;

public class TabLayoutAct extends BaseViewPagerAct {
    private TabLayoutSmartLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TabLayoutSmartLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        String[] mtitle = {"脸型","发型","眉毛","眼睛","鼻子","嘴巴"};
        setViewPager(binding.viewPager,mtitle);
        binding.oneSmartTL.setViewPager(binding.viewPager);
    }
}