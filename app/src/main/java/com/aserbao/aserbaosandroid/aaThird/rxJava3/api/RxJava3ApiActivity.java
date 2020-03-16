package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Notification;
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
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
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
    public static final int USE_CREATING_INTERVAL_RANGE = 20;
    public static final int USE_CREATING_REPEAT = 21;
    public static final int USE_CREATING_TIMER = 22;

    public static final int USE_TRANSFORMING_BUFFER = 30;
    public static final int USE_TRANSFORMING_WINDOW = 31;
    public static final int USE_TRANSFORMING_TOLIST = 32;
    public static final int USE_TRANSFORMING_MAP = 33;
    public static final int USE_TRANSFORMING_FLATMAP = 34;
    public static final int USE_TRANSFORMING_CONCATMAP = 35;
    public static final int USE_TRANSFORMING_SWITCHMAP = 36;
    public static final int USE_TRANSFORMING_GROUP_BY = 37;
    public static final int USE_TRANSFORMING_SCAN = 38;
    public static final int USE_TRANSFORMING_CAST = 39;
    public static final int USE_RETRY_CAST = 40;


    public static final int USE_FILTERING_DEBOUNCE = 50;
    public static final int USE_FILTERING_THROTTLEWITHTIMEOUT = 51;
    public static final int USE_FILTERING_THROTTLE_FIRST = 52;
    public static final int USE_FILTERING_THROTTLE_LAST = 53;
    public static final int USE_FILTERING_SAMPLE = 54;
    public static final int USE_FILTERING_DISTINCT = 55;
    public static final int USE_FILTERING_ELEMENT_AT = 56;
    public static final int USE_FILTERING_FILTER = 57;
    public static final int USE_FILTERING_FIRST = 58;
    public static final int USE_FILTERING_LAST = 59;
    public static final int USE_FILTERING_IGNORE_ELEMENTS = 60;
    public static final int USE_FILTERING_SKIP = 61;
    public static final int USE_FILTERING_SKIP_LAST = 62;
    public static final int USE_FILTERING_TAKE = 63;
    public static final int USE_FILTERING_TAKE_LAST = 64;
    public static final int USE_FILTERING_OF_TYPE = 65;
    public static final int USE_FILTERING_TIMEOUT = 66;

    public static final int USE_COMBINING_START_WITH = 80;
    public static final int USE_COMBINING_CONCAT = 89;
    public static final int USE_COMBINING_COMBINELATEST = 81;
    public static final int USE_COMBINING_JOIN = 82;
    public static final int USE_COMBINING_MERGE = 83;
    public static final int USE_COMBINING_ZIP = 84;
    public static final int USE_COMBINING_SWITCH_MAP = 85;
    public static final int USE_COMBINING_REDUCE = 86;
    public static final int USE_COMBINING_COUNT = 87;
    public static final int USE_COMBINING_COLLECT = 88;

    public static final int USE_UTILITY_DELAY = 100;
    public static final int USE_UTILITY_DO_ON_NEXT= 101;
    public static final int USE_UTILITY_MATERIALIZE= 102;
    public static final int USE_UTILITY_OBSERVEON = 103;
    public static final int USE_UTILITY_SUBSCRIBEON = 104;
    public static final int USE_UTILITY_SERIALIZE = 105;
    public static final int USE_UTILITY_TIMEINTERVAL = 106;
    public static final int USE_UTILITY_TIMESTAMP = 107;
    public static final int USE_UTILITY_USING = 108;

    public static final int USE_CONDITIONAL_ALL = 120;
    public static final int USE_CONDITIONAL_AMB_WITH = 121;
    public static final int USE_CONDITIONAL_CONTAINS = 122;
    public static final int USE_CONDITIONAL_DEFAULTISEMPTY = 123;
    public static final int USE_CONDITIONAL_SEQUENCEEQUAL = 124;
    public static final int USE_CONDITIONAL_SKIPUNTIL = 125;
    public static final int USE_CONDITIONAL_SKIPWHILE = 126;
    public static final int USE_CONDITIONAL_TAKEUNTIL = 127;
    public static final int USE_CONDITIONAL_TAKEWHILE = 128;

    public static final int USE_MATHEMATICAL_REDUCE = 140;

    public static final int USE_BACKPRESSURESTRATEGY_MISSING = 200;
    public static final int USE_BACKPRESSURESTRATEGY_ERROR = 201;
    public static final int USE_BACKPRESSURESTRATEGY_BUFFER = 202;
    public static final int USE_BACKPRESSURESTRATEGY_DROP = 203;
    public static final int USE_BACKPRESSURESTRATEGY_LASTEST = 204;

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"Rxjava中的被观察者"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Flowable 的使用",USE_FLOWABLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Observable的使用",USE_OBSERVABLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Single的使用",USE_SINGLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Completable的使用",USE_COMPLETABLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Maybe的使用",USE_MAYBE));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"创建操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Flowable 的create创建",USE_CREATING_CREATE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Flowable 的defer方法",USE_CREATING_DEFER));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用defer创建被观察者 注意常按和点击的输出",USE_CREATING_DEFER_SAMPLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用empty创建被观察者例子",USE_CREATING_EMPTY));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用never创建被观察者例子",USE_CREATING_NEVER));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用fromArray", USE_CREATING_FROM_ARRAY));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用fromIterable", USE_CREATING_FROM_ITERABLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用just", USE_CREATING_JUST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用interval",USE_CREATING_INTERVAL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用range",USE_CREATING_RANGE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用intervalRange",USE_CREATING_INTERVAL_RANGE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用repeat",USE_CREATING_REPEAT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用timer",USE_CREATING_TIMER));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"转换操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用buffer", USE_TRANSFORMING_BUFFER));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用window", USE_TRANSFORMING_WINDOW));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用toList", USE_TRANSFORMING_TOLIST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用map", USE_TRANSFORMING_MAP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用flatmap", USE_TRANSFORMING_FLATMAP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用concatmap", USE_TRANSFORMING_CONCATMAP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用switchmap", USE_TRANSFORMING_SWITCHMAP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用groupby", USE_TRANSFORMING_GROUP_BY));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用scan", USE_TRANSFORMING_SCAN));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用cast", USE_TRANSFORMING_CAST));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"过滤操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用debounce", USE_FILTERING_DEBOUNCE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用throttlewithtimeout", USE_FILTERING_THROTTLEWITHTIMEOUT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用throttlefirst", USE_FILTERING_THROTTLE_FIRST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用throttlelast", USE_FILTERING_THROTTLE_LAST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用sample", USE_FILTERING_SAMPLE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用distinct", USE_FILTERING_DISTINCT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用elementAt", USE_FILTERING_ELEMENT_AT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用filter", USE_FILTERING_FILTER));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用first", USE_FILTERING_FIRST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用last", USE_FILTERING_LAST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用ingoreElements", USE_FILTERING_IGNORE_ELEMENTS));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用skip", USE_FILTERING_SKIP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用skipLast", USE_FILTERING_SKIP_LAST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用take", USE_FILTERING_TAKE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用takeLast", USE_FILTERING_TAKE_LAST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用ofType", USE_FILTERING_OF_TYPE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用timeout", USE_FILTERING_TIMEOUT));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"组合操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用startWith", USE_COMBINING_START_WITH));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用combineLatest", USE_COMBINING_COMBINELATEST));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用join", USE_COMBINING_JOIN));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用merge", USE_COMBINING_MERGE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用zip", USE_COMBINING_ZIP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用switchMap", USE_COMBINING_SWITCH_MAP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用reduce", USE_COMBINING_REDUCE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用count", USE_COMBINING_COUNT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用collect", USE_COMBINING_COLLECT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用concat", USE_COMBINING_CONCAT));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"辅助操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用count", USE_UTILITY_DELAY));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用doOnNext", USE_UTILITY_DO_ON_NEXT));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用Materialize", USE_UTILITY_MATERIALIZE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用ObserveOn", USE_UTILITY_OBSERVEON));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用SubscribeOn", USE_UTILITY_SUBSCRIBEON));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用Serialize", USE_UTILITY_SERIALIZE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用TimeInterval", USE_UTILITY_TIMEINTERVAL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用TimeStamp", USE_UTILITY_TIMESTAMP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用using", USE_UTILITY_USING));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"条件操作符的使用"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用all", USE_CONDITIONAL_ALL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用amb", USE_CONDITIONAL_AMB_WITH));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用contains", USE_CONDITIONAL_CONTAINS));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用defaultIsEmpty", USE_CONDITIONAL_DEFAULTISEMPTY));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用sequenceEqual", USE_CONDITIONAL_SEQUENCEEQUAL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用skipUtil", USE_CONDITIONAL_SKIPUNTIL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用skipWhile", USE_CONDITIONAL_SKIPWHILE));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用takeUtil", USE_CONDITIONAL_TAKEUNTIL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用takeWhile", USE_CONDITIONAL_TAKEWHILE));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"数学运算符和聚合运算符"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用reduce", USE_MATHEMATICAL_REDUCE));
        mBaseRecyclerBean.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"Rxjava中的背压策略"));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用backpressureStrategy.MISSING", USE_BACKPRESSURESTRATEGY_MISSING));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用backpressureStrategy.ERROR", USE_BACKPRESSURESTRATEGY_ERROR));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用backpressureStrategy.BUFFER", USE_BACKPRESSURESTRATEGY_BUFFER));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用backpressureStrategy.DROP", USE_BACKPRESSURESTRATEGY_DROP));
        mBaseRecyclerBean.add(new BaseRecyclerBean("使用backpressureStrategy.LATEST", USE_BACKPRESSURESTRATEGY_LASTEST));


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
            case USE_CREATING_DEFER_SAMPLE:
                useDeferSample(isLongClick);
                break;
            case USE_TRANSFORMING_BUFFER:
            case USE_TRANSFORMING_WINDOW:
            case USE_TRANSFORMING_MAP:
            case USE_TRANSFORMING_FLATMAP:
            case USE_TRANSFORMING_CONCATMAP:
            case USE_TRANSFORMING_SWITCHMAP:
            case USE_TRANSFORMING_GROUP_BY:
            case USE_TRANSFORMING_SCAN:
            case USE_TRANSFORMING_CAST:
            case USE_TRANSFORMING_TOLIST:
            case USE_RETRY_CAST:
                useTransforming(position);
                break;
            case USE_FILTERING_DEBOUNCE:
            case USE_FILTERING_THROTTLEWITHTIMEOUT:
            case USE_FILTERING_THROTTLE_FIRST:
            case USE_FILTERING_THROTTLE_LAST:
            case USE_FILTERING_SAMPLE:
            case USE_FILTERING_DISTINCT:
            case USE_FILTERING_ELEMENT_AT:
            case USE_FILTERING_FILTER:
            case USE_FILTERING_FIRST:
            case USE_FILTERING_LAST:
            case USE_FILTERING_IGNORE_ELEMENTS:
            case USE_FILTERING_SKIP:
            case USE_FILTERING_SKIP_LAST:
            case USE_FILTERING_TAKE:
            case USE_FILTERING_TAKE_LAST:
            case USE_FILTERING_OF_TYPE:
            case USE_FILTERING_TIMEOUT:
                useFiltering(position,isLongClick);
                break;
            case USE_COMBINING_START_WITH:
            case USE_COMBINING_COMBINELATEST:
            case USE_COMBINING_JOIN:
            case USE_COMBINING_MERGE:
            case USE_COMBINING_ZIP:
            case USE_COMBINING_SWITCH_MAP:
            case USE_COMBINING_REDUCE:
            case USE_COMBINING_COUNT:
            case USE_COMBINING_COLLECT:
            case USE_COMBINING_CONCAT:
                useCombining(position,isLongClick);
                break;
            case USE_UTILITY_DELAY:
            case USE_UTILITY_DO_ON_NEXT:
            case USE_UTILITY_MATERIALIZE:
            case USE_UTILITY_OBSERVEON:
            case USE_UTILITY_SUBSCRIBEON:
            case USE_UTILITY_SERIALIZE:
            case USE_UTILITY_TIMEINTERVAL:
            case USE_UTILITY_TIMESTAMP:
            case USE_UTILITY_USING:
                useUtility(position,isLongClick);
                break;
            case USE_CONDITIONAL_ALL:
            case USE_CONDITIONAL_AMB_WITH:
            case USE_CONDITIONAL_CONTAINS:
            case USE_CONDITIONAL_DEFAULTISEMPTY:
            case USE_CONDITIONAL_SEQUENCEEQUAL:
            case USE_CONDITIONAL_SKIPUNTIL:
            case USE_CONDITIONAL_SKIPWHILE:
            case USE_CONDITIONAL_TAKEUNTIL:
            case USE_CONDITIONAL_TAKEWHILE:
                useConditional(position,isLongClick);
                break;
            case USE_MATHEMATICAL_REDUCE:
                useMathematical(position,isLongClick);
                break;
            case USE_BACKPRESSURESTRATEGY_MISSING:
            case USE_BACKPRESSURESTRATEGY_ERROR:
            case USE_BACKPRESSURESTRATEGY_BUFFER :
            case USE_BACKPRESSURESTRATEGY_DROP:
            case USE_BACKPRESSURESTRATEGY_LASTEST:
                useBackPressures(position,isLongClick);
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

    /**
     * 被观察者类型
     * @param type
     */
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
                Observable<String> hello_world = Observable.just("Hello world");
                Consumer<String> consumer = new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: " + s + " ThreadName = " + Thread.currentThread());
                    }
                };
                hello_world
                    .subscribe(consumer);
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
            case USE_TRANSFORMING_BUFFER:
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
            case USE_TRANSFORMING_WINDOW:
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
            case USE_TRANSFORMING_TOLIST:
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
            case USE_TRANSFORMING_MAP:
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
            case USE_TRANSFORMING_FLATMAP:
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
            case USE_TRANSFORMING_CONCATMAP:
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
            case USE_TRANSFORMING_SWITCHMAP:
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
            case USE_TRANSFORMING_GROUP_BY:
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
            case USE_TRANSFORMING_SCAN:
//                Observable.just(1,2,3,4,5)
                Observable.range(1,100)
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
            case USE_TRANSFORMING_CAST:
                Observable.just(1,2,3,4,5)
                    .cast(Number.class)
                    .subscribe(result -> Log.e(TAG, "useTransforming: " + result ));
                break;
            case USE_RETRY_CAST:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                    }
                }).retryWhen(new RetryWithDelay(5,1000))
                    .subscribe(string -> Log.e(TAG, "useTransforming: " + string ));

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
            case USE_FILTERING_THROTTLEWITHTIMEOUT:
            case USE_FILTERING_DEBOUNCE:
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
            case USE_FILTERING_THROTTLE_FIRST:
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
            case USE_FILTERING_THROTTLE_LAST:
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
            case USE_FILTERING_SAMPLE:
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

            case USE_FILTERING_DISTINCT:
                Observable.just(1,1,2,2,3,4,5,5,2)
                    .distinct()
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result));
                break;
            case USE_FILTERING_ELEMENT_AT:
                Observable.range(1,10)
                    .elementAt(3)
                    .subscribe(result -> Log.e(TAG, "useFiltering: " + result ));
                break;
            case USE_FILTERING_FILTER:
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
            case USE_FILTERING_FIRST:
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
            case USE_FILTERING_LAST:
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
            case USE_FILTERING_IGNORE_ELEMENTS:
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
            case USE_FILTERING_SKIP:
                Observable.range(1,5)
                    .skip(2)
                    .subscribe(result -> Log.e(TAG, "skip useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .skip(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "skip Time useFiltering: " + result ));
                break;
            case USE_FILTERING_SKIP_LAST:
                Observable.range(1,5)
                    .skipLast(2)
                    .subscribe(result -> Log.e(TAG, "skipLast useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .skipLast(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "skipLast Time useFiltering: " + result ));

                break;
            case USE_FILTERING_TAKE:
                Observable.range(1,5)
                    .take(2)
                    .subscribe(result -> Log.e(TAG, "take useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .take(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "take Time useFiltering: " + result ));
                break;
            case USE_FILTERING_TAKE_LAST:
                Observable.range(1,5)
                    .takeLast(2)
                    .subscribe(result -> Log.e(TAG, "takeLast useFiltering: " + result ));

                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS)
                    .takeLast(3,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "takeLast Time useFiltering: " + result ));
                break;
            case USE_FILTERING_OF_TYPE:
                Observable.just(false,"a",1,2,"b",4,"c",true)
                    .ofType(String.class)
                    .subscribe(result -> Log.e(TAG, "ofType useFiltering: " + result));
                break;
            case USE_FILTERING_TIMEOUT:
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
            case USE_COMBINING_START_WITH:
                Observable<Integer> range12 = Observable.range(1, 2);
                Observable<Integer> range68 = Observable.range(6, 2);
                range12
                    .startWith(range68)
                    .startWithItem(100)
                    .subscribe(result -> Log.e(TAG, "startWith: useCombining" + result ));
                break;
            case USE_COMBINING_CONCAT:
                Observable.intervalRange(1, 3,0,1,TimeUnit.SECONDS)
                    .concatWith(Observable.intervalRange(101, 2,0,1,TimeUnit.SECONDS))
                    .subscribe(result -> Log.e(TAG, "concatWith useCombining: " + result ));
                break;
            case USE_COMBINING_MERGE:
                //mergeWith可以接受2~9个Observable。若某个Observable出错，则会停止数据发射。
                Observable.intervalRange(1, 3,0,1,TimeUnit.SECONDS)
                    .mergeWith(Observable.intervalRange(101, 2,0,1,TimeUnit.SECONDS))
                    .subscribe(result -> Log.e(TAG, "mergeWith useCombining: " +result));
                break;
            case USE_COMBINING_ZIP:
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
            case USE_COMBINING_COMBINELATEST:
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
            case USE_COMBINING_JOIN:
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
            case USE_COMBINING_SWITCH_MAP:
                Observable.range(1,5)
                    .switchMap(new Function<Integer, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Integer integer) throws Throwable {
                            return Observable.just("switchMap" + integer);
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "switchMap useCombining: " + result ));
                break;
            case USE_COMBINING_REDUCE:
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
            case USE_COMBINING_COUNT:
                Observable.range(1,5)
                    .count()
                    .subscribe(result -> Log.e(TAG, "count useCombining:一共发送了" + result + "次数据"));
                break;
            case USE_COMBINING_COLLECT:
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

    /**
     * 辅助操作符
     * @param position
     * @param isLongClick
     */
    private void useUtility(int position,boolean isLongClick){
        switch (position){
            case USE_UTILITY_DELAY:
                Observable.just(1,2,3,4)
                    .delay(5,TimeUnit.SECONDS)
                    .subscribe(result -> Log.e(TAG, "delay useUtility: " + result ));
                break;
            case USE_UTILITY_DO_ON_NEXT:
                Observable.intervalRange(1,5,1,1,TimeUnit.SECONDS)
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, "doOnNext accept: " + aLong );
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "USE_UTILITY_DO_ON_NEXT useUtility: " + result ));
                break;
            case USE_UTILITY_MATERIALIZE:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(10);
                        emitter.onComplete();
                    }
                })
                    .materialize()
                    .subscribe(new Observer<Notification<Integer>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "materialize onSubscribe: " + d );
                        }

                        @Override
                        public void onNext(Notification<Integer> integerNotification) {
                            Integer value = integerNotification.getValue();
                            Log.e(TAG, "materialize onNext: " + value );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "materialize onError: " + e.toString() );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "materialize onComplete: "  );
                        }
                    });
                break;
            case USE_UTILITY_OBSERVEON:
                Observable.just(1,2,3)
                    .observeOn(Schedulers.newThread())          //子线程
//                    .observeOn(AndroidScheduler.mainThread())   //主线程
                    .subscribe(result -> Log.e(TAG, "observeOn useUtility: threadName =" + Thread.currentThread().getName()+" 数据为 =" +  result));
                break;
            case USE_UTILITY_SUBSCRIBEON:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(100);
                        emitter.onComplete();
                        Log.e(TAG, "subscribeOn: threadName =" + Thread.currentThread().getName());
                    }
                })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidScheduler.mainThread())
                    .subscribe(result -> Log.e(TAG, "subscribeOn useUtility: threadName =" + Thread.currentThread().getName()+ "数据为 =" + result));
                break;
            case USE_UTILITY_SERIALIZE:
                Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        emitter.onNext(1L);
                        emitter.onNext(2L);
                        emitter.onComplete();
                        emitter.onNext(3L);
                    }
                })
                    .serialize()
                    .subscribe(result -> Log.e(TAG, "serialize useCombining: " + result ));
                break;
            case USE_UTILITY_TIMEINTERVAL:
                Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        emitter.onNext(1L);
                        Thread.sleep(300);
                        emitter.onNext(2L);
                        Thread.sleep(1000);
                        emitter.onNext(3L);
                        Thread.sleep(800);
                        emitter.onNext(4L);
                    }
                })
                    .timeInterval()
                    .subscribe(result -> Log.e(TAG, "timeInterval useUtility: " + result ));
                break;
            case USE_UTILITY_TIMESTAMP:
                Observable.intervalRange(1,3,2,1,TimeUnit.SECONDS)
                    .timestamp()
                    .subscribe(result -> Log.e(TAG, "timestamp useUtility: " + result ));
                break;
            case USE_UTILITY_USING:
                Observable<Long> longObservable = Observable.intervalRange(1, 3, 2, 1, TimeUnit.SECONDS);

                DisposableObserver<String> observer = new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                };
                if (!isLongClick) {
                    Log.e(TAG, "useUtility: " + isLongClick );
                    longObservable
                        .using(new Supplier<String>() {
                            @Override
                            public String get() throws Throwable {
                                return "supplier";
                            }
                        }, new Function<String, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(String s) throws Throwable {
                                return Observable.just(s);
                            }
                        }, new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                Log.e(TAG, "accept: " + s);
                            }
                        })
                        .subscribe(observer);
                }else{
                    observer.dispose();
                }
                break;
        }
    }

    /**
     * 条件操作符
     * @param position
     * @param isLongClick
     */
    private void useConditional(int position,boolean isLongClick){
        switch (position){
            case USE_CONDITIONAL_ALL:
                if (isLongClick) {
                    Observable.just(0, 2, 1, 4)
                        .all(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Throwable {
                                boolean result = false;
                                if (integer < 5) result = true;
                                return result;
                            }
                        })
                        .subscribe(result -> Log.e(TAG, "1 all useConditional: " + result));
                }else {
                    Observable.just(4, 0, 7, 3)
                        .all(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Throwable {
                                boolean result = false;
                                if (integer < 5) result = true;
                                return result;
                            }
                        })
                        .subscribe(result -> Log.e(TAG, "2 all useConditional: " + result));
                }
                break;
            case USE_CONDITIONAL_AMB_WITH:
                Observable<Long> intervalRange1 = Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS);
                Observable<Long> intervalRange2 = Observable.intervalRange(10, 5, 1, 1, TimeUnit.SECONDS);
                Observable<Long> intervalRange3 = Observable.intervalRange(100, 5, 3, 1, TimeUnit.SECONDS);

                intervalRange1
                    .ambWith(intervalRange2)
                    .ambWith(intervalRange3)
                    .subscribe(result -> Log.e(TAG, "ambWith useConditional: " + result ));
                break;
            case USE_CONDITIONAL_CONTAINS:
                Observable.just(2,30,22,5,60,1)
                    .contains(22)
                    .subscribe(result -> Log.e(TAG, "contains useConditional: "+ result ));
                break;
            case USE_CONDITIONAL_DEFAULTISEMPTY:
                Observable.empty()
                    .defaultIfEmpty(5)
                    .subscribe(result -> Log.e(TAG, "defaultIfEmpty1 useConditional: "+ result));

                Observable.just(1)
                    .defaultIfEmpty(5)
                    .subscribe(result -> Log.e(TAG, "defaultIfEmpty2 useConditional: "+ result));
                break;
            case USE_CONDITIONAL_SEQUENCEEQUAL:
                Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4);
                Observable<Integer> integerObservable1 = Observable.just(1,3,2, 4);
                Observable<Long> longObservable = Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS);
                Observable<Long> longObservable1 = Observable.intervalRange(1, 4, 2, 2, TimeUnit.SECONDS);

                Observable.sequenceEqual(integerObservable, integerObservable1)
                    .subscribe(result -> Log.e(TAG, "1 useConditional: " + result ));

                Observable.sequenceEqual(longObservable, longObservable1)
                    .subscribe(result -> Log.e(TAG, "2 useConditional: " + result ));

                break;
            case USE_CONDITIONAL_SKIPUNTIL:
                Observable<Long> observable = Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS);
                Observable<Long> observable1 = Observable.intervalRange(10, 2, 2, 2, TimeUnit.SECONDS);
                observable
                    .skipUntil(observable1)
                    .subscribe(result -> Log.e(TAG, "skipUntil useConditional: "+ result ));
                break;
            case USE_CONDITIONAL_SKIPWHILE:
                Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS)
                    .skipWhile(new Predicate<Long>() {
                        @Override
                        public boolean test(Long aLong) throws Throwable {
                            boolean result = false;
                            if (aLong != 8) result = true;
                            Log.e(TAG, "test: " + result);
                            return result;//当返回false时就会输出后面的数据。
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "skipWhile useConditional: "+ result ));
                break;
            case USE_CONDITIONAL_TAKEUNTIL:
                Observable<Long> takeObservable = Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS);
                Observable<Long> takeObservable1 = Observable.intervalRange(10, 2, 2, 2, TimeUnit.SECONDS);
                takeObservable
                    .takeUntil(takeObservable1)
                    .subscribe(result -> Log.e(TAG, "takeUntil useConditional: "+ result ));
                break;
            case USE_CONDITIONAL_TAKEWHILE:
                Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS)
                    .takeWhile(new Predicate<Long>() {
                        @Override
                        public boolean test(Long aLong) throws Throwable {
                            boolean result = false;
                            if (aLong != 8) result = true;
                            Log.e(TAG, "test: " + result);
                            return result;//当返回false时就抛弃后面的数据。
                        }
                    })
                    .subscribe(result -> Log.e(TAG, "skipWhile useConditional: "+ result ));
                break;
        }
    }


    /**
     * 数学运算符
     * @param position
     * @param isLongClick
     */
    private void useMathematical(int position,boolean isLongClick){
        switch (position){
            case USE_MATHEMATICAL_REDUCE:
                Observable.range(1,100)
                    .reduce(new BiFunction<Integer, Integer, Integer>() {
                        @Override
                        public Integer apply(Integer integer, Integer integer2) throws Throwable {
                            Log.e(TAG, "apply: integer = " +integer + " integer2 = "+ integer2);
                            return integer + integer2;
                        }
                    })
                .subscribe(result -> Log.e(TAG, "reduce useMathematical: " + result ));
                break;
        }
    }

    /**
     * 测背压
     * @param position
     * @param isLongClick
     */
    private void useBackPressures(int position,boolean isLongClick){
        BackpressureStrategy type = BackpressureStrategy.MISSING;
        switch (position){
            case USE_BACKPRESSURESTRATEGY_MISSING:
                type = BackpressureStrategy.MISSING; //抛出异常 MissingBackpressureException，并提示 缓存区满了。
                break;
            case USE_BACKPRESSURESTRATEGY_ERROR:
                type = BackpressureStrategy.ERROR;
                break;
            case USE_BACKPRESSURESTRATEGY_BUFFER :
                type = BackpressureStrategy.BUFFER;
                 break;
            case USE_BACKPRESSURESTRATEGY_DROP:
                type = BackpressureStrategy.DROP;
                break;
            case USE_BACKPRESSURESTRATEGY_LASTEST:
                type = BackpressureStrategy.LATEST;
                break;
        }
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                int count = Flowable.bufferSize() * 2;
                switch (position){
                    case USE_BACKPRESSURESTRATEGY_MISSING:
                    case USE_BACKPRESSURESTRATEGY_ERROR:
                        for (int i = 0; i < count; i++){
                            emitter.onNext(i);
                            Thread.sleep(1);
                        }
                        break;
                    case USE_BACKPRESSURESTRATEGY_BUFFER :
                        count = Integer.MAX_VALUE;
                        for (int i = 0; i < count; i++){
                            emitter.onNext(i);
                        }
                        break;
                    case USE_BACKPRESSURESTRATEGY_DROP:
                        for (int i = 0; i < count; i++){
                            emitter.onNext(i);
                        }
                        break;
                    case USE_BACKPRESSURESTRATEGY_LASTEST:
                        for (int i = 0; i < count; i++){
                            emitter.onNext(i);
                        }
                        break;
                }
                emitter.onComplete();
            }
        },type)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new MySubscriber());
    }


    class MySubscriber implements Subscriber<Integer> {
        Subscription mSubscription;
        @Override
        public void onSubscribe(Subscription s) {
            mSubscription = s;
            s.request(Integer.MAX_VALUE);//s.request(Integer.MAX_VALUE);
            Log.e(TAG, "onSubscribe: " +s );
        }

        @Override
        public void onNext(Integer integer) {
            Log.e(TAG, "onNext: " +integer );
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mSubscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            Log.e(TAG, "onError: " +t.toString() );
        }

        @Override
        public void onComplete() {
            Log.e(TAG, "onComplete: " );
        }
    }
}
