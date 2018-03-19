package com.aserbao.aserbaosandroid.designMode.observerMode.observer;

/**
 * Created by aserbao on 2018 2018/3/13.23:04
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class SecondObserver implements IObserver {//观察者实现
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public void update(String s) {
        message = "SecondObserver 收到了" + s;
    }
}
