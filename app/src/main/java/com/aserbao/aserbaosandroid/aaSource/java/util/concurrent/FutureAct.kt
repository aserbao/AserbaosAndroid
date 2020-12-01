package com.aserbao.aserbaosandroid.aaSource.java.util.concurrent

import android.util.Log
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

/**
 * @author: aserbao
 * @date:2020/12/1 10:27 AM
 * @package:com.aserbao.aserbaosandroid.aaSource.java.util.concurrent
 * @describle: Future的使用
 */
class FutureAct : BaseRecyclerViewActivity() {

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("Future demo",0))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0->simpleFuture()
        }
    }

    /**
     * 需要并行处理，且需要返回执行结果。
     */
    fun simpleFuture(){
        val start = System.currentTimeMillis()
        var futureTask1 = FutureTask<String>(object : Callable<String> {
            override fun call(): String {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return "第一个视频下载成功"
            }
        })

        var futureTask2 = FutureTask<String>(object : Callable<String> {
            override fun call(): String {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return "第二个视频下载成功"
            }
        })
        Thread(futureTask1).start()
        Thread(futureTask2).start()

        Log.d(TAG, "simpleFuture() called" + futureTask1.get());
        Log.d(TAG, "simpleFuture() called" + futureTask2.get());
        Log.d(TAG, "simpleFuture() called 耗时："+ (System.currentTimeMillis() - start));


    }

}