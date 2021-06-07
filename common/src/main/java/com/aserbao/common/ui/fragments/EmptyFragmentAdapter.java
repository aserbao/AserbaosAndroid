package com.aserbao.common.ui.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
     * @Created time:2021/6/7 2:22 PM 
     * @author: aserbao
     * @description:
     **/
public  class EmptyFragmentAdapter extends FragmentPagerAdapter {
    public String[] titles = {"one","two","three"};

    public EmptyFragmentAdapter(@NonNull FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        EmptyFragment emptyFragment = EmptyFragment.getInstance(titles[position]);
        return emptyFragment;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (titles != null) {
            ret = titles.length;
        }
        return ret;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = titles[position];
        return title;
    }
}