package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/21 11:26 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class LazyMan {
    String name;

    public void receiveFood(){
        Log.e("dagger", "I've receive the food, thank you ");
    }
}
