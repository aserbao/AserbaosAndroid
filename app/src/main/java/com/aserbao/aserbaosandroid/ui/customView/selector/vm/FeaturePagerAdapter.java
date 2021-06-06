package com.aserbao.aserbaosandroid.ui.customView.selector.vm;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.aserbao.aserbaosandroid.ui.customView.selector.FeatureConfigFragment;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;

import java.util.ArrayList;

/**
 * @Created time:2021/6/6 5:42 PM
 * @author: aserbao
 * @description:
 **/
public class FeaturePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<RoleConfig> roleConfigure;

    public FeaturePagerAdapter(@NonNull FragmentManager fm, ArrayList<RoleConfig> roleConfigure) {
        super(fm);
        this.roleConfigure = roleConfigure;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for (RoleConfig roleConfig : roleConfigure) {
            FeatureConfigFragment featureConfigFragment = FeatureConfigFragment.getInstance(position);
            return featureConfigFragment;
        }
        return null;
    }


    @Override
    public int getCount() {
        int ret = 0;
        if (roleConfigure != null) {
            ret = roleConfigure.size();
        }
        return ret;
    }
}
