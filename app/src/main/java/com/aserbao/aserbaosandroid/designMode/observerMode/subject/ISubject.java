package com.aserbao.aserbaosandroid.designMode.observerMode.subject;

import com.aserbao.aserbaosandroid.designMode.observerMode.observer.IObserver;

/**
 * Created by aserbao on 2018 2018/3/13.22:59
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public interface ISubject {
    void registerObserver(IObserver iObserver);
    void removeObserver(IObserver iObserver);
    void notifyObserver();
}
