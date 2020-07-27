package com.aserbao.aserbaosandroid.ui.canvas.blendmode;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.aserbao.common.pop.PopSelSize;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.data.StaticFinalValues;
import com.example.base.utils.screen.DisplayUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 混合模式 blend
 */
public class BlendModeActivity extends BaseRecyclerViewActivity {
    public final static List<String> mBlendModes = new ArrayList<>();
    static {
        mBlendModes.add("CLEAR");
        mBlendModes.add("SRC");
        mBlendModes.add("DST");
        mBlendModes.add("SRC_OVER");
        mBlendModes.add("DST_OVER");
        mBlendModes.add("SRC_IN");
        mBlendModes.add("DST_IN");
        mBlendModes.add("SRC_OUT");
        mBlendModes.add("DST_OUT");
        mBlendModes.add("SRC_ATOP");
        mBlendModes.add("DST_ATOP");
        mBlendModes.add("XOR");
        mBlendModes.add("DARKEN");
        mBlendModes.add("LIGHTEN");
        mBlendModes.add("MULTIPLY");
        mBlendModes.add("SCREEN");
        mBlendModes.add("ADD");
        mBlendModes.add("OVERLAY");
    }

    private CustomViewForBlendMode mCustomViewForBlendMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mCustomViewForBlendMode = new CustomViewForBlendMode(this);
        addViewToFrameLayout(mCustomViewForBlendMode,true,true, true);
        mCustomViewForBlendMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopSel();
            }
        });
        mCustomViewForBlendMode.setXfermode(PorterDuff.Mode.CLEAR);
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }


    public void showPopSel(){
        new PopSelSize(this).showEditPop(new PopSelSize.IPopSelListener() {
            @Override
            public void selSize(@NotNull String size, int selStatus) {
                PorterDuff.Mode blendMode = getBlendMode(size);
                mCustomViewForBlendMode.setXfermode(blendMode);
            }

        },mBlendModes.get(0),mBlendModes,PopSelSize.TWO_WHEEL_VIEW);
    }

    /**
     * 混合模式换算
     * @param key
     * @return
     */
    public PorterDuff.Mode getBlendMode(String key){
        int resultKey = 0;
        for (int i = 0; i < mBlendModes.size(); i++) {
            String s = mBlendModes.get(i);
            if(s .equals( key)){
                resultKey= i;
                break;
            }
        }
        switch (resultKey) {
            case 0:
                return PorterDuff.Mode.CLEAR;
            case 1:
                return PorterDuff.Mode.SRC;
            case 2:
                return PorterDuff.Mode.DST;
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 4:
                return PorterDuff.Mode.DST_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 6:
                return PorterDuff.Mode.DST_IN;
            case 7:
                return PorterDuff.Mode.SRC_OUT;
            case 8:
                return PorterDuff.Mode.DST_OUT;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 10:
                return PorterDuff.Mode.DST_ATOP;
            case 11:
                return PorterDuff.Mode.XOR;
            case 16:
                return PorterDuff.Mode.DARKEN;
            case 17:
                return PorterDuff.Mode.LIGHTEN;
            case 13:
                return PorterDuff.Mode.MULTIPLY;
            case 14:
                return PorterDuff.Mode.SCREEN;
            case 12:
                return PorterDuff.Mode.ADD;
            case 15:
                return PorterDuff.Mode.OVERLAY;
        }
        return null;
    }


}
