package com.aserbao.common.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.common.R;

/**
* @Created time:2021/6/7 11:50 AM
* @author: aserbao
* @description: 仅带标记的fragment，用来检查Tablayout 和 ViewPager的效果
**/
public class EmptyFragment extends Fragment {
    public static final String CONTENT = "content";
    private View mView;
    private String mTag = "";

    public static EmptyFragment getInstance(String content) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT,content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTag = arguments.getString(CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_empty, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) mView.findViewById(R.id.emptyTV)).setText(mTag);
    }


}