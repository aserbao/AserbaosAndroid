package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

import static com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car.TAG;

/**
 * 功能: 活塞
 *
 * @author aserbao
 * @date : On 2019/5/29 5:15 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Block {
    @Inject
    public Block() {
        Log.e(TAG, "Block:的构造方法 " );
    }
}
