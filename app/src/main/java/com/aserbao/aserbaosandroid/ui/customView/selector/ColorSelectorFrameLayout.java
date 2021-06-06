package com.aserbao.aserbaosandroid.ui.customView.selector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.CustomColorSelectorLlBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeatureConfigureVM;
import com.aserbao.aserbaosandroid.ui.customView.selector.vm.FeaturePagerAdapter;
import com.example.base.utils.screen.DisplayUtil;

import java.util.ArrayList;

/**
 * @Created time:2021/6/3 6:11 PM
 * @author: aserbao
 * @description: 简单的选择器
 **/
public class ColorSelectorFrameLayout extends FrameLayout {
    private Context mContext;
    private CustomColorSelectorLlBinding binding;
    private FeatureConfigureVM featureConfigureVM;
    public ColorSelectorFrameLayout(Context context) {
        super(context);
        initFragment(context);
    }

    public ColorSelectorFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFragment(context);
    }

    public ColorSelectorFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFragment(context);
    }

    public ColorSelectorFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initFragment(context);
    }


    private void initFragment(Context context){
        mContext = context;
        binding = CustomColorSelectorLlBinding.inflate(LayoutInflater.from(mContext));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.BOTTOM;
        addView(binding.getRoot(),params);
        AppCompatActivity fragmentAct = (AppCompatActivity) context;
        featureConfigureVM = new ViewModelProvider(fragmentAct).get(FeatureConfigureVM.class);
        initFragment(fragmentAct);
    }


    private void initFragment(FragmentActivity activity) {
        ArrayList<RoleConfig> roleConfigure = featureConfigureVM.roleConfigure;
        for (int i = 0; i < roleConfigure.size(); i++) {
            RoleConfig roleConfig = featureConfigureVM.getRoleConfig(i);
            RadioButton radioButton = (RadioButton)LayoutInflater.from(activity).inflate(R.layout.dynamic_role_radio_button, null);
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
        FeaturePagerAdapter featurePagerAdapter = new FeaturePagerAdapter(activity.getSupportFragmentManager(), featureConfigureVM.roleConfigure);
        binding.selFeatureConfigVP.setAdapter(featurePagerAdapter);
    }









}
