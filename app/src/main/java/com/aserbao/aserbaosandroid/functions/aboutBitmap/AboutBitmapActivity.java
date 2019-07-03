package com.aserbao.aserbaosandroid.functions.aboutBitmap;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;
import com.aserbao.aserbaosandroid.functions.aboutBitmap.createLong.CreateLongBitmapActivity;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/7/3 4:13 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.aboutBitmap
 */
public class AboutBitmapActivity extends BaseActivity {
    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("生成长图", CreateLongBitmapActivity.class));
    }
}
