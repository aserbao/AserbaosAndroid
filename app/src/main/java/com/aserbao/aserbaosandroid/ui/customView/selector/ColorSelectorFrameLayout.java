package com.aserbao.aserbaosandroid.ui.customView.selector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelBeans;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.SelRVAdapter;

import java.util.ArrayList;

/**
 * @Created time:2021/6/3 6:11 PM
 * @author: aserbao
 * @description: 简单的选择器
 **/
public class ColorSelectorFrameLayout extends FrameLayout {
    private Context mContext;

    public ColorSelectorFrameLayout(Context context) {
        super(context);
        initView(context);
    }

    public ColorSelectorFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorSelectorFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ColorSelectorFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context){
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.custom_color_selector_ll, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.BOTTOM;
        addView(view,params);
        initColorRV(((RecyclerView) view.findViewById(R.id.colorSelRV)));
        initSelFeatureRV(((RecyclerView) view.findViewById(R.id.featureSelRV)));
    }


    SelRVAdapter colorSelRVAdapter;
    SelRVAdapter featureSelRVAdapter;
    private void initColorRV(RecyclerView rv){
        ArrayList<SelBeans> colorBeans = new ArrayList<>();
        for (String s : SEL_COLOR) {
            colorBeans.add(new SelBeans(s));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,5);
        colorSelRVAdapter = new SelRVAdapter(mContext, colorBeans, new SelRVAdapter.ItemClickerListener() {
            @Override
            public void itemClick(SelBeans beans, int position) {

            }
        });
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(colorSelRVAdapter);
    }

    private void initSelFeatureRV(RecyclerView rv){
        ArrayList<SelBeans> colorBeans = new ArrayList<>();
        for (int s : SEL_FEATURE) {
            SelBeans selBeans = new SelBeans(s);
            colorBeans.add(selBeans);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,5);
        featureSelRVAdapter = new SelRVAdapter(mContext, colorBeans, new SelRVAdapter.ItemClickerListener() {
            @Override
            public void itemClick(SelBeans beans, int position) {

            }
        });
//        featureSelRVAdapter.setHasHeader(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(featureSelRVAdapter);
    }



    static String[] SEL_COLOR = {
        "#FFFFFF","#FFFFF0","#FFFFE0","#FFFF00",
        "#FFFAFA","#FFFAF0","#FFFACD","#FFF8DC",
        "#FFF68F","#FFF5EE","#FFF0F5","#FFEFDB",
        "#FFEFD5","#FFEC8B","#FFEBCD","#FFE7BA",
        "#FFE4E1","#FFE4C4","#FFE4B5","#FFE1FF",
        "#FFDEAD","#FFDAB9","#FFD700","#FFD39B",
        "#FFC1C1","#FFC125","#FFC0CB","#FFBBFF",
        "#FFB90F","#FFB6C1","#FFB5C5","#FFAEB9",
        "#FFA54F","#FFA500","#FFA07A","#FF8C69"};
    static int[] SEL_FEATURE = {
       R.drawable.place_holder,
       R.drawable.mm_1,
       R.drawable.mm_2,
       R.drawable.mm_3,
       R.drawable.mm_4,
       R.drawable.mm_5,
       R.drawable.mm_6,
       R.drawable.mm_7,
       R.drawable.emoji_00,
       R.drawable.emoji_01,
       R.drawable.emoji_02,
       R.drawable.emoji_03,
       R.drawable.emoji_04,
       R.drawable.emoji_05,
       R.drawable.emoji_06,
       R.drawable.emoji_07,
       R.drawable.emoji_08
    };

}
