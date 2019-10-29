package com.aserbao.aserbaosandroid.aaThird.rxJava3;

import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.RxJavaDownLoadActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;


import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.Subscriber;
import rx.Subscription;

public class RxJavaActivity extends BaseRecyclerViewActivity {

    private static final String TAG = "RxJavaActivity";


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("RxJava实现一个模拟下载功能", RxJavaDownLoadActivity.class,10));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Hello World",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable的使用讲解",100));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Observable的使用讲解",101));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("RxJava使用三步骤",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 100:
                testFlowable();
                break;
            case 101:
                testObservable();
                break;
            case 0:
                testJust();
                break;
            case 1:
                /*Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello");
                        subscriber.onNext("Hi");
                        subscriber.onNext("Aloha");
                        subscriber.onCompleted();
                    }
                });*/

                Observable observable = new Observable() {
                    @Override
                    protected void subscribeActual(Observer observer) {

                    }
                };
                observable.subscribe(mObserver);
//                observable.subscribe(mSubscriber);

                break;
        }
    }


    public void testJust() {
//        Flowable.just("Hello world").subscribe((Consumer<? super String>) System.out::println);
        //不支持lamdba
        /*Flowable.just("Hello world")
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println(s);
                }
            });*/
    }

    public void testFlowable(){
       /* Disposable d = Flowable.just("Hello world!")
            .delay(1, TimeUnit.SECONDS);*/
            /*.subscribeWith(new DisposableSubscriber<String>() {
                @Override public void onStart() {
                    Log.d(TAG, "onStart() called" + Thread.currentThread());
                    request(2);
                }
                @Override public void onNext(String t) {
                    Log.d(TAG, "onNext() called with: t = [" + t + "]"+ Thread.currentThread());
//                    request(1);
                    cancel();
                }
                @Override public void onError(Throwable t) {
                    Log.d(TAG, "onError() called with: t = [" + t + "]"+ Thread.currentThread());
                    t.printStackTrace();
                }
                @Override public void onComplete() {
                    Log.d(TAG, "onComplete() called"+ Thread.currentThread());
                }
            });*/

        /*try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用dispose()方法可以取消当前序列
        d.dispose();*/
    }

    public void testObservable(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe() called with: emitter = [" + emitter + "] ThreadName =" + Thread.currentThread());
                emitter.onNext(10);
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
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

        Log.e(TAG, "testObservable: 开始建立订阅关系"  );
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(observer);

        /*Observable.just(1,2,3,4,5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe() called with: d = [" + d + "] ThreadName =" + Thread.currentThread());
                }

                @Override
                public void onNext(Integer integer) {
                    Log.d(TAG, "onNext() called with: integer = [" + integer + "]ThreadName =" + Thread.currentThread());
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError() called with: e = [" + e + "]ThreadName =" + Thread.currentThread());
                }

                @Override
                public void onComplete() {
                    Log.d(TAG, "onComplete() calledThreadName =" + Thread.currentThread());
                }
            });*/

    }

    Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext() called with: s = [" + s + "]");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError() called with: e = [" + e + "]");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete() called");
        }
    };



}
