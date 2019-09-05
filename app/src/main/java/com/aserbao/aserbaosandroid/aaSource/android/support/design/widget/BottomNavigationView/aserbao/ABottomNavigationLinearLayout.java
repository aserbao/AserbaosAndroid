package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.BottomNavigationView.aserbao;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-09-04 15:11
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.BottomNavigationView.aserbao
 */
public class ABottomNavigationLinearLayout extends LinearLayout {

    private Context mContext;
    private int item_layout;
    private boolean needShowText;

    public ABottomNavigationLinearLayout(Context context) {
        this(context,null);
    }

    public ABottomNavigationLinearLayout(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ABottomNavigationLinearLayout(Context context,  AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ABottomNavigationLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }
    
    List<BottomItemBean> mBottomItemBean = new ArrayList<>();
    public void setBottomItemBean(List<BottomItemBean> bottomItemBean){
        mBottomItemBean = bottomItemBean;
        removeAllViews();
        notificationDataChange();
    }

    public void notificationDataChange(){
        if (mBottomItemBean != null) {
            for (int i = 0; i < mBottomItemBean.size(); i++) {
                View item_view = LayoutInflater.from(mContext).inflate(item_layout, this);
                if (!needShowText) {
                    item_view.findViewById(R.id.a_bottom_item_tv).setVisibility(GONE);
                }
                item_view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int clickPosition = (int) v.getTag();
                        setCuurTab(clickPosition);
                    }
                });
                item_view.setTag(i);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                addView(item_view, i,layoutParams);
            }
        }
    }


    public void setCuurTab(int position){
        if (getChildCount() > position && mBottomItemBean != null){
            for (int i = 0; i < mBottomItemBean.size(); i++) {
                View item_view = getChildAt(i);
                BottomItemBean bottomItemBean = mBottomItemBean.get(i);
                ImageView aBottomItemIv = (ImageView) item_view.findViewById(R.id.a_bottom_item_iv);
                TextView aBottomItemTv = (TextView) item_view.findViewById(R.id.a_bottom_item_tv);
                if (i == position){
                    aBottomItemIv.setImageResource(bottomItemBean.getSelectedIcon());
                    aBottomItemTv.setText(bottomItemBean.getTextContent());

                }else{
                    aBottomItemIv.setImageResource(bottomItemBean.getUnSelectedIcon());
                    aBottomItemTv.setText(bottomItemBean.getTextContent());
                }
            }
        }
    }


    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ABottomNavigationLinearLayout);
        item_layout = ta.getResourceId(R.styleable.ABottomNavigationLinearLayout_item_layout, R.layout.a_default_bottom_item_layout);
        needShowText = ta.getBoolean(R.styleable.ABottomNavigationLinearLayout_need_text, true);
        ta.recycle();
    }



   /* public void startAnimation(View view){
        float x = view.getX();
        int width = view.getWidth();
        float x1 = mIndicationIv.getX();
        int width1 = mIndicationIv.getWidth();
        float translationX = x+width/2-width1/2;

        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("translationX", x1, translationX);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mIndicationIv, valuesHolder);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(500).start();
    }*/




}
