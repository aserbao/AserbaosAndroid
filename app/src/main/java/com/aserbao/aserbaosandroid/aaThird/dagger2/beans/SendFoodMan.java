package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/21 11:21 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class SendFoodMan {
    int no;//编号

    public void getFood(){
        Log.e("dagger", "I had get the Food" );
    }

    public void sendFood(){
        Log.e("dagger", "I sending the food for you now ");
    }

    public void finishSendFood(){
        Log.e("dagger", "I've already send it where put your desk");
    }

}
