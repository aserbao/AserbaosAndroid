package com.aserbao.aserbaosandroid.functions.aboutBitmap;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.createBitmap.CreateBitmapActivity;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.createLong.CreateLongBitmapActivity;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.pineColor.PineColorBitmapActivity;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/7/3 4:13 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.aboutBitmap
 */
public class AboutBitmapActivity extends BaseRecyclerViewActivity {
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Bitmap生成", CreateBitmapActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("生成长图", CreateLongBitmapActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("获取图片颜色", PineColorBitmapActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
