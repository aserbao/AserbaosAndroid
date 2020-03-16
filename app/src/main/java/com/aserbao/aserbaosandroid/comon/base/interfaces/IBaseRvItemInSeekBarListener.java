package com.aserbao.aserbaosandroid.comon.base.interfaces;

import android.widget.SeekBar;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2020-01-16 16:54
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.interfaces
 */
public interface IBaseRvItemInSeekBarListener extends IBaseListener {

   void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser,int tag);
}
