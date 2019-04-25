package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import android.util.Log;

import javax.inject.Inject;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/3/21 11:21 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class SendFoodMan {

    @Inject
    public SendFoodMan() {
    }

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
