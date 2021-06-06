package com.aserbao.aserbaosandroid.ui.customView.selector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.CustomColorSelectorLlBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.SelRVBottomContainerAdapter;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.title.SelTitleAdapter;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeatureConfigureVM;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeaturePagerAdapter;
import com.example.base.utils.screen.DisplayUtil;

import java.util.ArrayList;

public class SelectorActivity extends AppCompatActivity {

    private Context mContext;
    private SelTitleAdapter selTitleAdapter;
    private SelRVBottomContainerAdapter selRVBottomContainerAdapter;
    private CustomColorSelectorLlBinding binding;
    private FeatureConfigureVM featureConfigureVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomColorSelectorLlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        featureConfigureVM = new ViewModelProvider(this).get(FeatureConfigureVM.class);
        mContext = this;
        binding.selFeatureConfigVP.setScrollable(false);
       initView(this);
       handleViewEvent();
    }



    private void initView(SelectorActivity selectorActivity) {
        ArrayList<RoleConfig> roleConfigure = featureConfigureVM.roleConfigure;
        for (int i = 0; i < roleConfigure.size(); i++) {
            RoleConfig roleConfig = featureConfigureVM.getRoleConfig(i);
            RadioButton radioButton = (RadioButton)LayoutInflater.from(this).inflate(R.layout.dynamic_role_radio_button, null);
            radioButton.setText(roleConfig.getName());
            radioButton.setClickable(true);
            radioButton.setTag(i);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.selFeatureConfigVP.setCurrentItem(((int) v.getTag()));
                }
            });
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = DisplayUtil.dip2px(14f);
            params.rightMargin = DisplayUtil.dip2px(14f);
            binding.selTitleRG.addView(radioButton,params);
        }
        FeaturePagerAdapter featurePagerAdapter = new FeaturePagerAdapter(getSupportFragmentManager(), featureConfigureVM.roleConfigure);
        binding.selFeatureConfigVP.setAdapter(featurePagerAdapter);
    }

    private void handleViewEvent() {
//        binding.selTitleRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            }
//        });
    }


}