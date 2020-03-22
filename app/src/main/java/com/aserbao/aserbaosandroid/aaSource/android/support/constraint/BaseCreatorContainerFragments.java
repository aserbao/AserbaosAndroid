package com.aserbao.aserbaosandroid.aaSource.android.support.constraint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/20
 * @project: playground-Android
 * @package: com.example.camera.fragments
 */
public class BaseCreatorContainerFragments extends Fragment {

    private ConstraintLayout mConstraintLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.constraintlayout_1, container, false);
        view.findViewById(R.id.btn_group_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViewSize();
            }
        });
        mConstraintLayout = ((ConstraintLayout) view.findViewById(R.id.constraint));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initViewSize();
    }

    int recommendHeight = DisplayUtil.dip2px(200);
    int addH = DisplayUtil.dip2px(20);
    private void initViewSize() {
        recommendHeight += addH;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraintLayout);
        constraintSet.constrainHeight(R.id.view_pager, recommendHeight);
        constraintSet.centerVertically(R.id.view_pager,R.id.constraint);
        constraintSet.applyTo(mConstraintLayout);
    }

    private void initView() {
    }
}
