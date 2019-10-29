package com.aserbao.aserbaosandroid.aaThird.rxJava3.download;

import android.util.Log;

import com.aserbao.aserbaosandroid.comon.base.extend.BaseAboutProgressActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Supplier;

public class RxJavaDownLoadActivity extends BaseAboutProgressActivity {
    private static final String TAG = "RxJavaDownLoadActivity";
    @Override
    protected void startDownload() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe() called with: emitter = [" + emitter + "] ThreadName =" + Thread.currentThread());
                emitter.onNext(10);
            }
        });
        Observer<Integer> integerObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe() called with: d = [" + d + "] ThreadName =" + Thread.currentThread());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext() called with: integer = [" + integer + "] ThreadName =" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "] ThreadName =" + Thread.currentThread());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called ThreadName =" + Thread.currentThread());
            }
        };
        observable.subscribe(integerObserver);
    }

    @Override
    protected void pauseDownload() {

    }
}
