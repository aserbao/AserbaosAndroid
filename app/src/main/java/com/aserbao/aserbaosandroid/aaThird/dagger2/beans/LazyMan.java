package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/21 11:26 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class LazyMan {

    @Inject
    public LazyMan() {
    }

    public void receiveFood(){
        Log.e("dagger", "I've receive the food, thank you ");
    }
}
