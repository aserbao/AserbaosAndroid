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
    private boolean mIsPause = false;

    @Override
    protected void startDownload() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //模拟从后台获取数据
                while (curProgress <= 100 && !mIsPause) {
                    Thread.sleep(15);
                    emitter.onNext(curProgress);
                    curProgress++;
                }
            }
        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidScheduler.mainThread())
          .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Throwable {
                  updateProgress(integer);
              }
          });
        mIsPause = false;
//        integerObservable.distinct();
        /*Observable.intervalRange(0,101,15,15, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Throwable {
                    updateProgress(aLong.intValue());
                }
            });*/
    }

    @Override
    protected void pauseDownload() {
        mIsPause = true;
    }
}
