package com.aserbao.aserbaosandroid.ui.recyclerView.smooth.interfaces;

import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans.SimpleBean;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/28 12:08 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.smooth.interfaces
 */
public interface ISmoothCallBackListener {
    void itemClick(int mLastPosition,int position, SimpleBean simpleBean);
    void itemClickTwo();
}
