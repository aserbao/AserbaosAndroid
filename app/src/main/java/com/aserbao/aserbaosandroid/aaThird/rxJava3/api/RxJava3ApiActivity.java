package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJava3ApiActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "RxJava3ApiActivity";
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的create创建",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的使用",10));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("在线程中使用Flowable ",11));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
                createFlowable();
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
        },BackpressureStrategy.MISSING)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidScheduler.mainThread())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
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
