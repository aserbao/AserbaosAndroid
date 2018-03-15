package com.aserbao.aserbaosandroid.designMode.observerMode.subject;

import java.util.Observable;

/**
 * Created by aserbao on 2018 2018/3/15.8:11
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class NewsPaperOfficeJava extends Observable {
    private String message;
    private int time = 1;

    public void sendMessage(){
        message = "第"+time +"次发的新闻";
        setChanged();
        notifyObservers();
        time++;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
