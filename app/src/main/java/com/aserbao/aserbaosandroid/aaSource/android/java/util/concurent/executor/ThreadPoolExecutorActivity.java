package com.aserbao.aserbaosandroid.aaSource.android.java.util.concurent.executor;

import android.util.Log;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 作用：线程池的使用
 * ThreadPoolExecutor : 普通线程池
 *
 * ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
 * DelayQueue：一个使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue：一个不存储元素的阻塞队列。
 * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
 * 参考blog:https://blog.csdn.net/u010687392/article/details/49850803
 * @author aserbao
 * @date: on 2020/6/27
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.java.util.concurent.executor
 */
public class ThreadPoolExecutorActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "ThreadPoolExecutorActiv";

    public static final int INT_CANCEL = 0;
    public static final int INT_THREAD_POOL = 10;
    public static final int INT_FIXED_THREAD_POOL = 11;
    public static final int INT_CACHED_THREAD_POOL = 12;
    public static final int INT_SINGLE_THREAD_POOL = 13;
    public static final int INT_SCHEDULED_THREAD_POOL = 14;
    public static final int INT_POOL_NEST = 15;

    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,1, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(100));
    //可重用固定线程数，队列无上限: 适用于执行长期的任务，性能好很多
    final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    // 按需创建，适用于有大量需要立即执行的耗时少的任务的情况
    final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    // 单任务执行线程池 一个任务一个任务执行的场景
    final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    //创建Scheduled线程池 周期性执行任务的场景
    final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("取消Executor", INT_CANCEL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("threadPoolExecutor", INT_THREAD_POOL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("FixedThreadPool",INT_FIXED_THREAD_POOL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("cachedThreadPool",INT_CACHED_THREAD_POOL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("singleThreadPool",INT_SINGLE_THREAD_POOL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("scheduledThreadPool",INT_SCHEDULED_THREAD_POOL));
        mBaseRecyclerBean.add(new BaseRecyclerBean("线程池嵌套",INT_POOL_NEST));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case INT_CANCEL:
                threadPoolExecutor.shutdownNow();
                fixedThreadPool.shutdownNow();
                cachedThreadPool.shutdownNow();
                singleThreadPool.shutdownNow();
                scheduledThreadPool.shutdownNow();
                break;
            case INT_THREAD_POOL:
                addMoreThreadToExector(threadPoolExecutor);
                break;
            case INT_FIXED_THREAD_POOL:
                addMoreThreadToExector(fixedThreadPool);
                break;
            case INT_CACHED_THREAD_POOL:
                addMoreThreadToExector(cachedThreadPool);
                break;
            case INT_SINGLE_THREAD_POOL:
                addMoreThreadToExector(singleThreadPool);
                break;
            case INT_SCHEDULED_THREAD_POOL:
                for (int i = 0; i < 20; i++) {
                    final int finali = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: "  + Thread.currentThread().getName() + " finali = " + finali);
                        }
                    };
                    scheduledThreadPool.schedule(runnable,2,TimeUnit.MILLISECONDS);
                }
                break;
            case INT_POOL_NEST:
                handleNestPool();
                break;
        }
    }

    private void addMoreThreadToExector(Executor executor){
        for(int i = 0;i<20;i++){
            final int finali = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Log.d(TAG,"当前线程名称 ： " +Thread.currentThread().getName() + " finali = " + finali);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);

        }
    }


    private void handleNestPool(){
        for (int i = 0; i < 10; i++) {
            final int finali = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: 输入标记 " + finali );
                    new NestPool(finali).start();
                }
            };
            fixedThreadPool.execute(runnable);
        }
    }






}
