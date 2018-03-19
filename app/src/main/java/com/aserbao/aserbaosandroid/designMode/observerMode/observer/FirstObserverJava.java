package com.aserbao.aserbaosandroid.designMode.observerMode.observer;

import com.aserbao.aserbaosandroid.designMode.observerMode.subject.NewsPaperOfficeJava;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by aserbao on 2018 2018/3/13.23:04
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class FirstObserverJava implements Observer {//JAVA观察者实现
    private String message;
    Observable mObservable;

    public String getMessage() {
        return message;
    }

    @Override
    public void update(Observable o, Object arg) {
        mObservable = o;
        if(mObservable instanceof NewsPaperOfficeJava){
            message = "FirstObserver 收到了" + ((NewsPaperOfficeJava) mObservable).getMessage();
        }
    }
}
