package com.aserbao.aserbaosandroid.aaThird.rxJava3.download;

import android.util.Log;

import com.aserbao.aserbaosandroid.comon.base.extend.BaseAboutProgressActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Supplier;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDownLoadActivity extends BaseAboutProgressActivity {
    private static final String TAG = "RxJavaDownLoadActivity";
    @Override
    protected void startDownload() {
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe() called with: emitter = [" + emitter + "] ThreadName =" + Thread.currentThread().getName());
                int i = 0;
                while(i <= 100){
                    Thread.sleep(100);
                    emitter.onNext(i);
                    i++;
                }
            }
        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidScheduler.mainThread())
          .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Integer integer) {
                updateProgress(integer);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });*/

        Observable.intervalRange(0,100,15,15, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Throwable {
                    updateProgress(aLong.intValue());
                }
            });
    }

    @Override
    protected void pauseDownload() {

    }
}
