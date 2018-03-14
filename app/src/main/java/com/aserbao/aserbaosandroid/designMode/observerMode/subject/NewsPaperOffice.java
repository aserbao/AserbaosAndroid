package com.aserbao.aserbaosandroid.designMode.observerMode.subject;

import com.aserbao.aserbaosandroid.designMode.observerMode.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aserbao on 2018 2018/3/13.23:01
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class NewsPaperOffice implements ISubject {
    private int time = 1;
    private List<IObserver> mList = new ArrayList<>();

    @Override
    public void registerObserver(IObserver iObserver) {
        if (iObserver != null) {
            mList.add(iObserver);
        }
    }

    @Override
    public void removeObserver(IObserver iObserver) {
        mList.remove(iObserver);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).update("第"+time +"次发的新闻");
        }
        time ++ ;
    }
}
