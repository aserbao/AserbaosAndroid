package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.functions.Supplier;
import io.reactivex.internal.util.SorterFunction;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues.LOGO_FROM_ASERBAO;

public class RxJava3ApiActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "RxJava3ApiActivity";

    public static final int USE_FLOWABLE = 0;
    public static final int USE_OBSERVABLE = 1;
    public static final int USE_SINGLE = 2;
    public static final int USE_COMPLETABLE = 3;
    public static final int USE_MAYBE = 4;
    public static final int USE_CREATING_CREATE  = 10;
    public static final int USE_CREATING_DEFER = 11;
    public static final int USE_CREATING_DEFER_SAMPLE = 12;
    public static final int USE_CREATING_EMPTY = 13;
    public static final int USE_CREATING_NEVER = 14;
    public static final int USE_CREATING_FROM_ARRAY = 15;
    public static final int USE_CREATING_FROM_ITERABLE = 16;
    public static final int USE_CREATING_JUST = 17;
    public static final int USE_CREATING_INTERVAL = 18;
    public static final int USE_CREATING_RANGE = 19;
    public static final int USE_CREATING_INTERVAL_RANGE = 120;
    public static final int USE_CREATING_REPEAT = 21;
    public static final int USE_CREATING_TIMER = 22;

    public static final int USE_CREATING_BUFFER = 30;
    public static final int USE_CREATING_WINDOW = 31;
    public static final int USE_CREATING_TOLIST= 32;
    public static final int USE_CREATING_MAP= 33;
    public static final int USE_CREATING_FLATMAP= 34;
    public static final int USE_CREATING_CONCATMAP= 35;
    public static final int USE_CREATING_SWITCHMAP= 36;
    public static final int USE_CREATING_GROUP_BY= 37;
    public static final int USE_CREATING_SCAN= 38;
    public static final int USE_CREATING_CAST= 39;


    public static final int USE_CREATING_DEBOUNCE= 50;
    public static final int USE_CREATING_THROTTLEWITHTIMEOUT= 51;
    public static final int USE_CREATING_THROTTLE_FIRST= 52;
    public static final int USE_CREATING_THROTTLE_LAST= 53;
    public static final int USE_CREATING_SAMPLE= 54;
    public static final int USE_CREATING_DISTINCT= 55;
    public static final int USE_CREATING_ELEMENT_AT= 56;
    public static final int USE_CREATING_FILTER= 57;
    public static final int USE_CREATING_FIRST= 58;
    public static final int USE_CREATING_LAST= 59;
    public static final int USE_CREATING_IGNORE_ELEMENTS= 60;
    public static final int USE_CREATING_SKIP= 61;
    public static final int USE_CREATING_SKIP_LAST = 62;
    public static final int USE_CREATING_TAKE= 63;
    public static final int USE_CREATING_TAKE_LAST = 64;
    public static final int USE_CREATING_OF_TYPE = 65;
    public static final int USE_CREATING_TIMEOUT = 66;

    public static final int USE_CREATING_START_WITH = 80;
    public static final int USE_CREATING_CONCAT = 89;
    public static final int USE_CREATING_COMBINELATEST = 81;
    public static final int USE_CREATING_JOIN = 82;
    public static final int USE_CREATING_MERGE = 83;
    public static final int USE_CREATING_ZIP = 84;
    public static final int USE_CREATING_SWITCH_MAP = 85;
    public static final int USE_CREATING_REDUCE = 86;
    public static final int USE_CREATING_COUNT = 87;
    public static final int USE_CREATING_COLLECT = 88;



    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"Rxjava中的被观察者"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的使用",USE_FLOWABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Observable的使用",USE_OBSERVABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Single的使用",USE_SINGLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Completable的使用",USE_COMPLETABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Maybe的使用",USE_MAYBE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"创建操作符的使用"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的create创建",USE_CREATING_CREATE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的defer方法",USE_CREATING_DEFER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用defer创建被观察者 注意常按和点击的输出",USE_CREATING_DEFER_SAMPLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用empty创建被观察者例子",USE_CREATING_EMPTY));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用never创建被观察者例子",USE_CREATING_NEVER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用fromArray", USE_CREATING_FROM_ARRAY));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用fromIterable", USE_CREATING_FROM_ITERABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用just", USE_CREATING_JUST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用interval",USE_CREATING_INTERVAL));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用range",USE_CREATING_RANGE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用intervalRange",USE_CREATING_INTERVAL_RANGE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用repeat",USE_CREATING_REPEAT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用timer",USE_CREATING_TIMER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"转换操作符的使用"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用buffer",USE_CREATING_BUFFER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用window",USE_CREATING_WINDOW));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用toList",USE_CREATING_TOLIST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用map",USE_CREATING_MAP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用flatmap",USE_CREATING_FLATMAP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用concatmap",USE_CREATING_CONCATMAP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用switchmap",USE_CREATING_SWITCHMAP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用groupby",USE_CREATING_GROUP_BY));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用scan",USE_CREATING_SCAN));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用cast",USE_CREATING_CAST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"过滤操作符的使用"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用debounce",USE_CREATING_DEBOUNCE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用throttlewithtimeout",USE_CREATING_THROTTLEWITHTIMEOUT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用throttlefirst",USE_CREATING_THROTTLE_FIRST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用throttlelast",USE_CREATING_THROTTLE_LAST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用sample",USE_CREATING_SAMPLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用distinct",USE_CREATING_DISTINCT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用elementAt",USE_CREATING_ELEMENT_AT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用filter",USE_CREATING_FILTER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用first",USE_CREATING_FIRST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用last",USE_CREATING_LAST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用ingoreElements",USE_CREATING_IGNORE_ELEMENTS));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用skip",USE_CREATING_SKIP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用skipLast", USE_CREATING_SKIP_LAST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用take",USE_CREATING_TAKE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用takeLast", USE_CREATING_TAKE_LAST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用ofType", USE_CREATING_OF_TYPE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用timeout", USE_CREATING_TIMEOUT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"组合操作符的使用"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用startWith", USE_CREATING_START_WITH));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用combineLatest", USE_CREATING_COMBINELATEST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用join", USE_CREATING_JOIN));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用merge", USE_CREATING_MERGE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用zip", USE_CREATING_ZIP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用switchMap", USE_CREATING_SWITCH_MAP));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用reduce", USE_CREATING_REDUCE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用count", USE_CREATING_COUNT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用collect", USE_CREATING_COLLECT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用concat", USE_CREATING_CONCAT));

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case USE_FLOWABLE:
            case USE_OBSERVABLE:
            case USE_SINGLE:
            case USE_COMPLETABLE:
            case USE_MAYBE:
                useObservable(position);
                break;
            case USE_CREATING_CREATE:
            case USE_CREATING_DEFER:
            case USE_CREATING_EMPTY:
            case USE_CREATING_NEVER:
            case USE_CREATING_FROM_ARRAY:
            case USE_CREATING_FROM_ITERABLE:
            case USE_CREATING_JUST:
            case USE_CREATING_INTERVAL:
            case USE_CREATING_RANGE:
            case USE_CREATING_INTERVAL_RANGE:
            case USE_CREATING_REPEAT:
            case USE_CREATING_TIMER:
                createObservable(position);
                break;
            case USE_CREATING_BUFFER:
            case USE_CREATING_WINDOW:
            case USE_CREATING_MAP:
            case USE_CREATING_FLATMAP:
            case USE_CREATING_CONCATMAP:
            case USE_CREATING_SWITCHMAP:
            case USE_CREATING_GROUP_BY:
            case USE_CREATING_SCAN:
            case USE_CREATING_CAST:
            case USE_CREATING_TOLIST:
                useTransforming(position);
                break;
            case USE_CREATING_DEBOUNCE:
            case USE_CREATING_THROTTLEWITHTIMEOUT:
            case USE_CREATING_THROTTLE_FIRST:
            case USE_CREATING_THROTTLE_LAST:
            case USE_CREATING_SAMPLE:
            case USE_CREATING_DISTINCT:
            case USE_CREATING_ELEMENT_AT:
            case USE_CREATING_FILTER:
            case USE_CREATING_FIRST:
            case USE_CREATING_LAST:
            case USE_CREATING_IGNORE_ELEMENTS:
            case USE_CREATING_SKIP:
            case USE_CREATING_SKIP_LAST:
            case USE_CREATING_TAKE:
            case USE_CREATING_TAKE_LAST:
            case USE_CREATING_OF_TYPE:
            case USE_CREATING_TIMEOUT:
                useFiltering(position,isLongClick);
                break;
            case USE_CREATING_START_WITH:
            case USE_CREATING_COMBINELATEST:
            case USE_CREATING_JOIN:
            case USE_CREATING_MERGE:
            case USE_CREATING_ZIP:
            case USE_CREATING_SWITCH_MAP:
            case USE_CREATING_REDUCE:
            case USE_CREATING_COUNT:
            case USE_CREATING_COLLECT:
            case USE_CREATING_CONCAT:
                useCombining(position,isLongClick);
                break;
            case USE_CREATING_DEFER_SAMPLE:
                useDeferSample(isLongClick);
                break;



        }
    }

    /**
     * 测试defer的实例
     * @param isUseDefer 是否使用defer
     */
    private void useDeferSample(boolean isUseDefer) {
        RxJavaBean rxJavaBean = new RxJavaBean();
        rxJavaBean.mUseDef = isUseDefer;
        Observable<String> observable = rxJavaBean.valueObservable();
        rxJavaBean.setValues("change_value");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e(TAG, "accept: " +s );
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void useObservable(int type) {
        switch (type){
            case USE_FLOWABLE:
                Flowable.just("Hello world").subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: "+ s + " ThreadName = " + Thread.currentThread());
                    }
                });
                break;
            case USE_OBSERVABLE:
                Observable.just("Hello world").subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: "+ s + " ThreadName = " + Thread.currentThread());
                    }
                });
                break;
            case USE_SINGLE:
                Single.create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
//                emitter.onSuccess(10);
//                emitter.onSuccess(100);//只能提交一个，多个无效。
                        emitter.onError(new Exception("error info"));//只能提交一个，多个无效。
                    }
                }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidScheduler.mainThread())
                    .subscribe(new SingleObserver<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Integer integer) {
                            Log.e(TAG, "onSuccess: " + integer);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.toString());
                        }
                    });
                break;
            case USE_COMPLETABLE:
                Observable<Integer> integerObservable = Completable.create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(CompletableEmitter emitter) throws Exception {
                        emitter.onComplete();
//                emitter.onError(new Exception("error info"));
                    }
                }).andThen(Observable.range(1, 10));
                integerObservable
                    .delay(1,TimeUnit.SECONDS)
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: " + d );
                        }

                        @Override
                        public void onNext(Integer integer) {
                            Log.e(TAG, "onNext: " + integer );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " +e );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: " );
                        }
                    });
                break;
            case USE_MAYBE:
                Maybe.create(new MaybeOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
                /*for (int i = 0; i < 100; i++) {
                    emitter.onSuccess(i);
                }*/
                        emitter.onComplete();
//                emitter.onError(new Exception("error info"));
                    }
                }).subscribeWith(new DisposableMaybeObserver<Integer>() {
                    @Override
                    public void onStart() {
                        Log.e(TAG, "onStart: " );
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(TAG, "onSuccess: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
                break;
        }
    }


    /**
     * 创建被观察者的方法
     * @param type
     */
    private void createObservable(int type){
        switch (type){
            case USE_CREATING_CREATE:
                Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Log.e(TAG, "get: 初始化 Observable");
                        emitter.onNext(LOGO_FROM_ASERBAO);
                        emitter.onComplete();
//                        emitter.onNext(LOGO_FROM_ASERBAO);
                    }
                });
                Log.e(TAG, "开始订阅第一个观察者 ");
                Disposable disposable = observable
                    .delay(2,TimeUnit.SECONDS)
                    .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第一个观察者 收到的结果为：" + s);
                    }
                });
                disposable.dispose();
                Log.e(TAG, "开始订阅第二个观察者 ");
                observable
                    .delay(3,TimeUnit.SECONDS)
                    .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第二个观察者 收到的结果为：" + s );
                    }
                });
                break;
            case USE_CREATING_DEFER:
                Observable<String> defer = Observable.defer(new Supplier<ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> get() throws Throwable {
                        Log.e(TAG, "get: 初始化 Observable");
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                Log.e(TAG, "subscribe: 发个消息" );
                                emitter.onNext(LOGO_FROM_ASERBAO);
                            }
                        });
                    }
                });
                Log.e(TAG, "开始订阅第一个观察者 ");
                Disposable disposable1 = defer
                    .delay(2,TimeUnit.SECONDS)
                    .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第一个观察者 收到的结果为：" + s);
                    }
                });
                disposable1.dispose();
                Log.e(TAG, "开始订阅第二个观察者 ");
                defer
                    .delay(3,TimeUnit.SECONDS)
                    .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第二个观察者 收到的结果为：" + s );
                    }
                });
                break;
            case USE_CREATING_EMPTY:
                //会发送一个null数据
                Observable<Integer> empty = Observable.empty();
                empty.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "empty onSubscribe: " );
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "empty onNext: " );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "empty onError: " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "empty onComplete: " );
                    }
                });
                break;
            case USE_CREATING_NEVER:
                //不会发送任何消息，
                Observable<Integer> never = Observable.never();
                never.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "never onSubscribe: " );
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "never onNext: " );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "never onError: " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "never onComplete: " );
                    }
                });
                break;

            case USE_CREATING_FROM_ARRAY:
                List<String> arrayList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    String value = String.valueOf(i);
                    arrayList.add(value);
                }
                Observable.fromArray(arrayList)
                    .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Throwable {
                        for (String string : strings) {
                            Log.e(TAG, "accept: " + string);
                        }
                    }
                });

                String[] array = {"a","b","c"};
                Observable.fromArray(array)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(String s) {
                            Log.e(TAG, "onNext: " +s );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: " );
                        }
                    });

                break;

            case USE_CREATING_FROM_ITERABLE:
                List<String> list = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    list.add( "第"+ String.valueOf(i) + "个");
                }
                Flowable.fromIterable(list).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "iterableFlowable accept: " + s );
                    }
                });
                break;
            case USE_CREATING_JUST:
                // 创建被观察者，并发送just里面的每个参数。
                Observable.just(1,2,3,4).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, "accept: " + integer );
                    }
                });
                break;
            case USE_CREATING_INTERVAL:
                Observable.interval(3, 1, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Log.e(TAG, "intervalFloawble accept: " + aLong );
                    }
                });
                break;
            case USE_CREATING_RANGE:
                Observable.range(100,10).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, "accept: "+integer);
                    }
                });
                break;
            case USE_CREATING_INTERVAL_RANGE:
                Observable.intervalRange(100,10,3,1,TimeUnit.SECONDS)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, "intervalRangeFlowable accept: " + aLong );
                        }
                    });
                break;
            case USE_CREATING_REPEAT:
                //create创建的Observable对象设置repeat次数无效
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                    }
                }).repeat(repeatTime)
                    .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, repeatTime + "create accept: " + integer );
                    }
                });

                Observable.intervalRange(100,10,3,1,TimeUnit.SECONDS)
                    .repeat(repeatTime)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, repeatTime + "intervalRangeFlowable accept: " + aLong );
                        }
                    });
                //just创建的Observable对象有repeat次数限制
                Observable.just(1)
                    .repeat(repeatTime)
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {
                            Log.e(TAG,  repeatTime + "just accept: " + integer );
                        }
                    });
                repeatTime ++;
                break;
            case USE_CREATING_TIMER:
                Observable
                    .timer(5, TimeUnit.SECONDS)
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "timer onSubscribe: " +d );
                        }

                        @Override
                        public void onNext(Long aLong) {
                            Log.e(TAG, "timer onNext: " + aLong );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "timer onError: " + e );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "timer onComplete: " );
                        }
                    });
                break;
        }

    }
    int repeatTime = 1;


    /**
     * 使用转换操作符
     * @param type
     */
    private void useTransforming(int type){
        switch (type) {
            case USE_CREATING_BUFFER:
                Disposable subscribe = Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS)
                    .buffer(3)
                    .subscribe(new Consumer<List<Long>>() {
                        @Override
                        public void accept(List<Long> longs) throws Throwable {
                            String string = Arrays.toString(longs.toArray());
                            Log.e(TAG, "accept: " + string);
                        }
                    });
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscribe.dispose();
                break;
            case USE_CREATING_WINDOW:
                Disposable subscribe1 = Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS)
                    .window(3)
                    .subscribe(new Consumer<Observable<Long>>() {
                        @Override
                        public void accept(Observable<Long> longObservable) throws Throwable {
                            longObservable.subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Throwable {
                                    Log.e(TAG, "window accept: " + aLong);
                                }
                            });
                        }
                    });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscribe1.dispose();
                break;
            case USE_CREATING_TOLIST:
                Disposable toList = Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS)
                    .toList()
                    .subscribe(new Consumer<List<Long>>() {
                        @Override
                        public void accept(List<Long> integers) throws Throwable {
                            String s = Arrays.toString(integers.toArray());
                            Log.e(TAG, "accept: " + s );
                        }
                    });
                /*try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toList.dispose();*/
                break;
            case USE_CREATING_MAP:
                Observable.just(0)
                    .map(new Function<Integer, String>() {
                        @Override
                        public String apply(Integer integer) throws Throwable {
                            return "map after " + integer;
                        }
                    })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
                            Log.e(TAG, "accept: "+ s );
                        }
                    });
                break;
            case USE_CREATING_FLATMAP:
                Observable.just(1,100,1000)
                    .flatMap((new Function<Integer, ObservableSource<Long>>() {
                        @Override
                        public ObservableSource<Long> apply(Integer integer) throws Throwable {
//                            return Observable.just(100+integer,String.valueOf(integer));
                            Observable<Long> longObservable = Observable.intervalRange(integer, 2, 1,1, TimeUnit.SECONDS);
                            return longObservable;
                        }
                    }))
                    .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Throwable {
                        Log.e(TAG, "accept: " + s );
                    }
                });
                break;
            case USE_CREATING_CONCATMAP:
                Observable.just(1,100,1000)
                    .concatMap((new Function<Integer, ObservableSource<Long>>() {
                        @Override
                        public ObservableSource<Long> apply(Integer integer) throws Throwable {
//                            return Observable.just(100+integer,String.valueOf(integer));
                            Observable<Long> longObservable = Observable.intervalRange(integer, 2, 1,1, TimeUnit.SECONDS);
                            return longObservable;
                        }
                    }))
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long s) throws Throwable {
                            Log.e(TAG, "concatMap accept: " + s );
                        }
                    });
                break;
            case USE_CREATING_SWITCHMAP:
                // 当使用switchMap进行装换时，有且只能有一个被转换的Observable存在，当有一个新的Observable存在时，前面所有的Observable都会被终止。
                Observable.just(1,100,1000)
                    .switchMap((new Function<Integer, ObservableSource<Long>>() {
                        @Override
                        public ObservableSource<Long> apply(Integer integer) throws Throwable {
                            Observable<Long> longObservable = Observable.intervalRange(integer, 2, 1,1, TimeUnit.SECONDS);
                            return longObservable;
                        }
                    }))
                    .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Throwable {
                        Log.e(TAG, "accept: " + s );
                    }
                });

                break;
            case USE_CREATING_GROUP_BY:
                Observable.just(1,-1,100,-100,1000,-1000)
                    .groupBy(new Function<Integer, String>() {
                        @Override
                        public String apply(Integer integer) throws Throwable {
                            String result = "负数";
                            if (integer > 0) result = "正数";
                            return result;
                        }
                    })
                    .subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                        @Override
                        public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Throwable {
                            stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Throwable {
                                    String key = stringIntegerGroupedObservable.getKey();
                                    Log.e(TAG, key + ":" + String.valueOf(integer));
                                }
                            });
                        }
                    });
                break;
            case USE_CREATING_SCAN:
                Observable.just(1,2,3,4,5)
                    .scan(new BiFunction<Integer, Integer, Integer>() {
                        @Override
                        public Integer apply(Integer integer, Integer integer2) throws Throwable {
                            int item = integer + integer2;
                            return item;
                        }
                    }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, "accept: " + integer );
                    }
                });
                break;
            case USE_CREATING_CAST:
                Observable.just(1,2,3,4,5)
                    .cast(Number.class)
                    .subscribe(result -> Log.e(TAG, "useTransforming: " + result ));
                break;

        }
    }


    /**
     * 过滤操作符
     * @param position
     * @param isLongClick
     */
    private void useFiltering(int position, boolean isLongClick) {
        switch (position){
            case USE_CREATING_THROTTLEWITHTIMEOUT:
            case USE_CREATING_DEBOUNCE:
                Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        emitter.onNext(1L);
                        Thread.sleep(100);
                        emitter.onNext(2L);
                        Thread.sleep(201);
                        emitter.onNext(3L);
                        Thread.sleep(150);
                        emitter.onNext(4L);
                        Thread.sleep(200);
                        emitter.onNext(5L);
                        Thread.sleep(100);
                        emitter.onNext(6L);
                        emitter.onComplete();
                    }
                })
//                    .throttleWithTimeout(200,TimeUnit.MILLISECONDS)
                    .debounce(200,TimeUnit.MILLISECONDS)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, "accept: " + aLong );
                        }
                    });
                break;
            case USE_CREATING_THROTTLE_FIRST:
                Observable.create(new ObservableOnSubscribe<Long>() {
                @Override
                public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                    emitter.onNext(1L);
                    Thread.sleep(300);
                    emitter.onNext(2L);
                    Thread.sleep(700);
                    emitter.onNext(3L);
                    Thread.sleep(500);
                    emitter.onNext(4L);
                    Thread.sleep(800);
                    emitter.onNext(5L);
                    Thread.sleep(200);
                    emitter.onNext(6L);
                    emitter.onComplete();
                }
            })
                    .throttleFirst(1,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result ));
                break;
            case USE_CREATING_THROTTLE_LAST:
                Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        emitter.onNext(1L);
                        Thread.sleep(300);
                        emitter.onNext(2L);
                        Thread.sleep(700);
                        emitter.onNext(3L);
                        Thread.sleep(500);
                        emitter.onNext(4L);
                        Thread.sleep(800);
                        emitter.onNext(5L);
                        Thread.sleep(200);
                        emitter.onNext(6L);
                        emitter.onComplete();
                    }
                })
                    .throttleLast(1,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result));
                break;
            case USE_CREATING_SAMPLE:
                Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        emitter.onNext(1L);
                        Thread.sleep(300);
                        emitter.onNext(2L);
                        Thread.sleep(700);
                        emitter.onNext(3L);
                        Thread.sleep(500);
                        emitter.onNext(4L);
                        Thread.sleep(800);
                        emitter.onNext(5L);
                        Thread.sleep(200);
                        emitter.onNext(6L);
                        emitter.onComplete();
                    }
                })
                    .sample(1,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "sample useFiltering: " + result));
                break;

            case USE_CREATING_DISTINCT:
                Observable.just(1,1,2,2,3,4,5,5,2)
                    .distinct()
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result));
                break;
            case USE_CREATING_ELEMENT_AT:
                Observable.range(1,10)
                    .elementAt(3)
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result ));
                break;
            case USE_CREATING_FILTER:
                Observable.range(1,10)
                    .filter(new Predicate<Integer>() {
                        @Override
                        public boolean test(Integer integer) throws Throwable {
                            if (integer > 5){// 大于5的数据才发出。
                                return true;
                            }
                            return false;
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result));
                break;
            case USE_CREATING_FIRST:
                if(isLongClick){
                    Observable.empty()
                        .first(5)
                        .subscribe(resutl -> Log.e(TAG, "useFiltering: " + resutl));
                }else {
                    Observable.just(1, 2)
                        .first(5)
                        .subscribe(resutl -> Log.e(TAG, "useFiltering: " + resutl));
                }
                break;
            case USE_CREATING_LAST:
                if(isLongClick){
                    Observable.empty()
                        .last(5)
                        .subscribe(resutl -> Log.e(TAG, "useFiltering: " + resutl));
                }else {
                    Observable.just(1, 2)
                        .last(5)
                        .subscribe(resutl -> Log.e(TAG, "useFiltering: " + resutl));
                }
                break;
            case USE_CREATING_IGNORE_ELEMENTS:
                Observable.range(1,10)
                    .ignoreElements()
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: " );
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                break;
            case USE_CREATING_SKIP:
                Observable.range(1,5)
                    .skip(2)
                    .subscribe(result -> Log.e(TAG, "skip useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .skip(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "skip Time useFiltering: " + result ));
                break;
            case USE_CREATING_SKIP_LAST:
                Observable.range(1,5)
                    .skipLast(2)
                    .subscribe(result -> Log.e(TAG, "skipLast useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .skipLast(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "skipLast Time useFiltering: " + result ));

                break;
            case USE_CREATING_TAKE:
                Observable.range(1,5)
                    .take(2)
                    .subscribe(result -> Log.e(TAG, "take useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .take(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "take Time useFiltering: " + result ));
                break;
            case USE_CREATING_TAKE_LAST:
                Observable.range(1,5)
                    .takeLast(2)
                    .subscribe(result -> Log.e(TAG, "takeLast useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .takeLast(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "takeLast Time useFiltering: " + result ));
                break;
            case USE_CREATING_OF_TYPE:
                Observable.just(false,"a",1,2,"b",4,"c",true)
                    .ofType(String.class)
                    .subscribe(result -> Log.e(TAG, "ofType useFiltering: " + result));
                break;
            case USE_CREATING_TIMEOUT:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        Thread.sleep(800);
                        emitter.onNext(2);
                        Thread.sleep(1000);
                        emitter.onNext(3);
                        Thread.sleep(1200);
                        emitter.onNext(4);
                        emitter.onComplete();
                    }
                })
                    .timeout(1,TimeUnit.SECONDS,Observable.just(100))
                    .subscribe(result -> Log.e(TAG, "timeout useFiltering: " + result ));

                break;
        }
    }


    /**
     * 组合操作符
     * @param posiiton
     * @param isLongClick
     */
    private void useCombining(int posiiton,boolean isLongClick){


        switch (posiiton){
            case USE_CREATING_START_WITH:
                Observable<Integer> range12 = Observable.range(1, 2);
                Observable<Integer> range68 = Observable.range(6, 2);
                range12
                    .startWith(range68)
                    .startWithItem(100)
                    .subscribe(result -> Log.e(TAG, "startWith: useCombining" + result ));
                break;
            case USE_CREATING_CONCAT:
                Observable.intervalRange(1, 3,0,1,TimeUnit.SECONDS)
                    .concatWith(Observable.intervalRange(101, 2,0,1,TimeUnit.SECONDS))
                    .subscribe(result -> Log.e(TAG, "concatWith useCombining: " + result ));
                break;
            case USE_CREATING_MERGE:
                //mergeWith可以接受2~9个Observable。若某个Observable出错，则会停止数据发射。
                Observable.intervalRange(1, 3,0,1,TimeUnit.SECONDS)
                    .mergeWith(Observable.intervalRange(101, 2,0,1,TimeUnit.SECONDS))
                    .subscribe(result -> Log.e(TAG, "mergeWith useCombining: " +result));
                break;
            case USE_CREATING_ZIP:
                //zipWith如果观察者数量不同，则已少的为标准。
                Observable.intervalRange(1, 3,0,1,TimeUnit.SECONDS)
                    .zipWith(Observable.intervalRange(101, 2, 0, 1, TimeUnit.SECONDS), new BiFunction<Long, Long, Long>() {
                        @Override
                        public Long apply(Long aLong, Long aLong2) throws Throwable {
                            return aLong + aLong2;
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "zipWith useCombining: " +result));
                break;
            case USE_CREATING_COMBINELATEST:
                Observable<Integer> rangeCom = Observable.range(1, 2);
                Observable<String>  stringABC= Observable.just("a","b","c");
                rangeCom.combineLatest(rangeCom, stringABC, new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String coms) throws Throwable {
                        return String.valueOf(integer) + coms;
                    }
                })
                    .subscribe(result -> Log.e(TAG, "1 combineLatest useCombining: " + result ));

                Observable.combineLatest(Observable.just("a","b","c"),
                    Observable.intervalRange(101, 2,0,1,TimeUnit.SECONDS),
                    new BiFunction<String, Long, String>() {
                            @Override
                            public String apply(String string, Long integer2) throws Throwable {
                                return string + String.valueOf(integer2);
                            }
                        })
                    .subscribe(result -> Log.e(TAG, "2 combineLatest useCombining: " + result ));

                break;
            case USE_CREATING_JOIN:
                /*Observable<Long> intervalRange = Observable.intervalRange(1, 10,2,1,TimeUnit.SECONDS);

                Observable<String> just = Observable.just("one", "two", "three", "four");
                Observable<Integer> range = Observable.range(1, 5);
                    range
                    .join(just, s -> {
                            //使Observable延迟3000毫秒执行
                            return Observable.timer(2, TimeUnit.SECONDS);
                        }, integer -> {
                            //使Observable延迟2000毫秒执行
                            return Observable.timer(2, TimeUnit.SECONDS);
                        },
                        //结合上面发射的数据
                        (s, integer) -> s+integer)
                    .subscribe(o -> Log.e(TAG, "join useCombining: "  + o));*/


                Observable.intervalRange(1,5,0,1,TimeUnit.SECONDS)
                        .join(Observable.intervalRange(101,3,0,1,TimeUnit.SECONDS)
                            ,integer -> {
                                return Observable.timer(5, TimeUnit.SECONDS);
                            },
                            string -> {
                                return Observable.timer(2, TimeUnit.SECONDS);
                            },
                            (integer,string) -> "LeftO = "+integer + "RightO="+ string)
                .subscribe(result -> Log.e(TAG, "join useCombining: " + result ));



                break;
            case USE_CREATING_SWITCH_MAP:
                Observable.range(1,5)
                    .switchMap(new Function<Integer, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Integer integer) throws Throwable {
                            return Observable.just("switchMap" + integer);
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "switchMap useCombining: " + result ));
                break;
            case USE_CREATING_REDUCE:
                Observable.range(1,5)
                    .reduce(new BiFunction<Integer, Integer, Integer>() {
                        @Override
                        public Integer apply(Integer integer, Integer integer2) throws Throwable {
                            Log.e(TAG, "apply: 执行过程  第一个数= " + integer + " 第二个数= "+ integer2  );
                            return integer + integer2;
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "reduce useCombining: " + result));
                break;
            case USE_CREATING_COUNT:
                Observable.range(1,5)
                    .count()
                    .subscribe(result -> Log.e(TAG, "count useCombining:一共发送了" + result + "次数据"));
                break;
            case USE_CREATING_COLLECT:
                Observable.range(1,5)
                    .collect(new Supplier<ArrayList<Integer>>() {
                        @Override
                        public ArrayList<Integer> get() throws Throwable {
                            return new ArrayList<>();
                        }
                    }, new BiConsumer<ArrayList<Integer>, Integer>() {
                        @Override
                        public void accept(ArrayList<Integer> integers, Integer integer) throws Throwable {
                            integers.add(integer);
                        }
                    })
                    .subscribe(reslut -> Log.e(TAG, "collect useCombining: " +reslut ));
                break;

        }
    }
}
