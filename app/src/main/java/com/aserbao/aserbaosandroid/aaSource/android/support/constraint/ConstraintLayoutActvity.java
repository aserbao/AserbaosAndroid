package com.aserbao.aserbaosandroid.aaSource.android.support.constraint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.viewpager.widget.ViewPager;

import com.example.base.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * question1: 为什么constraintSet.centerVertically在Activity中可以生效，而在fragment中不能生效？
 */
public class ConstraintLayoutActvity extends AppCompatActivity {

    @BindView(R.id.button22)
    Button mButton22;
    @BindView(R.id.btn_group_1)
    Button mButton23;
    @BindView(R.id.group)
    Group mGroup;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.constraint)
    ConstraintLayout mConstraint;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout_1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_group_1, R.id.btn_3,R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_group_1:
                changeCons();
//                mGroup.setVisibility(View.GONE);
                break;
            case R.id.button2:
                changebias();
                break;
            case R.id.btn_3:
                mGroup.setVisibility(View.VISIBLE);
                break;
        }
    }

    int recommendHeight = DisplayUtil.dip2px(150);
    int addH = DisplayUtil.dip2px(20);

/**
     * 动态修改ConstraintLayout布局
     */
    public void changeCons() {
        recommendHeight += addH;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraint);
        constraintSet.constrainHeight(R.id.view_pager,recommendHeight);
        constraintSet.centerVertically(R.id.view_pager, R.id.constraint);
        constraintSet.centerHorizontally(R.id.view_pager, R.id.constraint);
        constraintSet.applyTo(mConstraint);
    }

    private float bias = 0.5f;
    private void changebias(){
//        bias += 0.1f;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mViewPager.getLayoutParams();
        layoutParams.height = recommendHeight;
        layoutParams.topToTop = R.id.constraint;
        layoutParams.bottomToBottom = R.id.constraint;
        mViewPager.setLayoutParams(layoutParams);
    }

}
