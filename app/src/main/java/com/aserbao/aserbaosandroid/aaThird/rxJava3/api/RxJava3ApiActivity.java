package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import org.intellij.lang.annotations.Flow;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Supplier;
import io.reactivex.schedulers.Schedulers;

public class RxJava3ApiActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "RxJava3ApiActivity";
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的create创建",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的just创建",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的from创建",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的timer创建",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的interval创建",4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的range创建",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的intervalRange创建",6));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的iterable创建",7));


        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的defer创建",51));

        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的使用",10));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("在线程中使用Flowable ",11));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
                createFlowable();
                break;
            case 1:
                justFlowable();
                break;
            case 2:
                fromFlowable();
                break;
            case 3:
                timerFlowable();
                break;
            case 4:
                intervalFloawble();
                break;
            case 5:
                rangeFlowable();
                break;
            case 6:
                intervalRangeFlowable();
                break;
            case 7:
                iterableFlowable();
                break;

            case 51:
                deferFlowable();
                break;
            case 10:
                testFlowable();
                break;
            case 11:
                testThreadFlowable();
                break;
        }
    }


    private void createFlowable(){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                    emitter.onNext("learn rxJava3 from aserbao");
            }
        },BackpressureStrategy.ERROR)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void justFlowable(){
        Flowable.just(1,2,3,4,5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e(TAG, "justFlowable accept: " + integer );
            }
        });
    }

    private void fromFlowable(){
        String[] inputStrings = {"from1","from2","from3"};
        Flowable.fromArray(inputStrings)
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    Log.e(TAG, "fromFlowable accept: " + s );
                }
            });
    }

    /**
     * 间隔操作符，固定值返回0
     */
    private void timerFlowable(){
        Flowable.timer(2, TimeUnit.SECONDS)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Throwable {
                    Log.e(TAG, "timerFlowable accept: " + aLong );
                }
            });
    }

    /**
     * 间隔操作符，从0开始。
     */
    private void intervalFloawble(){
        Flowable<Long> interval = Flowable.interval(2, 1, TimeUnit.MILLISECONDS);
        interval.subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Throwable {
                    Log.e(TAG, "intervalFloawble accept: " + aLong );
                }
            });
    }

    private void rangeFlowable(){
        Flowable.range(1,10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e(TAG, "accept: "+integer);
            }
        });
    }

    private void intervalRangeFlowable(){
        Flowable.intervalRange(1,10,2,1,TimeUnit.SECONDS)
            .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.e(TAG, "intervalRangeFlowable accept: " + aLong );
            }
        });
    }

    private void iterableFlowable(){
        List<String> arrayList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            arrayList.add( "第"+ String.valueOf(i) + "个");
        }
        Flowable.fromIterable(arrayList).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e(TAG, "iterableFlowable accept: " + s );
            }
        });
    }

    private void deferFlowable() {
        Flowable.defer(new Supplier<Publisher<?>>() {
            @Override
            public Publisher<?> get() throws Throwable {
                return null;
            }
        });
    }


    private void testFlowable() {
        Flowable.just("Hello world").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.e(TAG, "accept: "+ s + " ThreadName = " + Thread.currentThread());
            }
        });
    }

    public void testThreadFlowable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                testFlowable();
            }
        }).start();
    }
}
