package com.aserbao.aserbaosandroid.designMode.strategy.speed;

/**
 * Created by aserbao on 2018 2018/3/8.23:29
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class NormalSpeed implements ISpeedBehavior{
    @Override
    public void speed() {
        System.err.println("My speed is Normal!");
    }
}
