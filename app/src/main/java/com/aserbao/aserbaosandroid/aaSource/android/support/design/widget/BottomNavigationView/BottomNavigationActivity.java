package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.BottomNavigationView;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomNavigationActivity extends BaseRecyclerViewActivity implements View.OnClickListener{


    private ImageView mBottomIvOne,mBottomIvTwo,mBottomIvThree,mIndicationIv;

    @Override
    public void initGetData() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.aserbao_bottom_navigation_icon, null);
        addViewToFrameLayout(rootView);

        mBottomIvOne = ((ImageView) rootView.findViewById(R.id.bottom_iv_one));
        mBottomIvTwo = ((ImageView) rootView.findViewById(R.id.bottom_iv_two));
        mBottomIvThree = ((ImageView) rootView.findViewById(R.id.bottom_iv_three));
        mIndicationIv = ((ImageView) rootView.findViewById(R.id.indication_iv));
        mBottomIvOne.setOnClickListener(this);
        mBottomIvTwo.setOnClickListener(this);
        mBottomIvThree.setOnClickListener(this);

        rootView.post(new Runnable() {
            @Override
            public void run() {
                startAnimation(mBottomIvOne);
            }
        });
    }

    @Override
    public void itemClickBack(View view, int position) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private static final String TAG = "BottomNavigationActivit";

    public void startAnimation(View view){
        float x = view.getX();
        int width = view.getWidth();
        float x1 = mIndicationIv.getX();
        int width1 = mIndicationIv.getWidth();
        float translationX = x+width/2-width1/2;

        Log.e(TAG, "startAnimation: x = " +  x + " View.width = "+ width + " x1 = " + x1 + " width1 = " +width1 + " translationX = "+ translationX );


        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("translationX", x1, translationX);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mIndicationIv, valuesHolder);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(500).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_iv_one:
                startAnimation(mBottomIvOne);
                break;
            case R.id.bottom_iv_two:
                startAnimation(mBottomIvTwo);
                break;
            case R.id.bottom_iv_three:
                startAnimation(mBottomIvThree);
                break;
        }
    }
}
