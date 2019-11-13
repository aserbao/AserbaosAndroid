package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Supplier;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-12 20:22
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.rxJava3.api
 */
public class RxJavaBean {
    public String values = "init_value";
    public boolean mUseDef = false;
    public void setValues(String values) {
        this.values = values;
    }

    public Observable<String> valueObservable() {
        if(mUseDef){
            return Observable.defer(new Supplier<ObservableSource<? extends String>>() {
                @Override
                public ObservableSource<? extends String> get() throws Throwable {
                    return Observable.just(values);
                }
            });
        }else{
            return Observable.just(values);
        }
    }
}
