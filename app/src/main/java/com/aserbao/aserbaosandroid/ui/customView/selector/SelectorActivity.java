package com.aserbao.aserbaosandroid.ui.customView.selector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.CustomColorSelectorLlBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeatureConfigureVM;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeaturePagerAdapter;
import com.example.base.utils.screen.DisplayUtil;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SelectorActivity extends AppCompatActivity {
    private static final String TAG = "SelectorActivity";
    private Context mContext;
    private CustomColorSelectorLlBinding binding;
    private FeatureConfigureVM featureConfigureVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomColorSelectorLlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        featureConfigureVM = new ViewModelProvider(this).get(FeatureConfigureVM.class);
        mContext = this;
//        binding.selFeatureConfigVP.setScrollable(true);
        handleViewEvent();
        initView(this);
        fieldObserver();
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
        ViewPager selFeatureConfigVP = binding.selFeatureConfigVP;
        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) binding.selTitleRG.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged() called with: state = [" + state + "]");
            }
        };
        selFeatureConfigVP.addOnPageChangeListener(listener);
        binding.featureBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                featureConfigureVM.changeFeatureStatus(FeatureConfigureVM.FEATURE_DEFAULT_STATUS);
            }
        });
    }

    /**
     * 属性监听
     */
    private void fieldObserver() {
        featureConfigureVM.featureStatus.observe(this,status -> {
            switch (status){
                case FeatureConfigureVM.FEATURE_DEFAULT_STATUS:
                    binding.featureBackIv.setVisibility(INVISIBLE);
                    binding.selTitleRG.setVisibility(VISIBLE);
                    break;
                case FeatureConfigureVM.FEATURE_MORE_COLOR_STATUS:
                    binding.selTitleRG.setVisibility(INVISIBLE);
                    binding.featureBackIv.setVisibility(VISIBLE);
                    break;
            }
        });
    }
}