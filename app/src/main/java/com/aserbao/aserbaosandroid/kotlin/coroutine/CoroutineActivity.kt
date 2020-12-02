package com.aserbao.aserbaosandroid.kotlin.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.utils.log.ALogUtils
import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * kotlin 协程的使用
 * @property TAG String
 * @property SIMPLE_EXAMPLE Int
 * @property RUNBLOCKING Int
 * @property JOB Int
 * @property SCOPE_BUILD Int
 */
class CoroutineActivity : BaseRecyclerViewActivity() {

    private val SIMPLE_EXAMPLE = 0
    private val RUNBLOCKING = 1
    private val JOB = 2
    private val SCOPE_BUILD = 3
    private val SAME_REQUEST = 4

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("basic of coroutine", SIMPLE_EXAMPLE))
        mBaseRecyclerBean.add(BaseRecyclerBean("RunBlicking", RUNBLOCKING))
        mBaseRecyclerBean.add(BaseRecyclerBean("job", JOB))
        mBaseRecyclerBean.add(BaseRecyclerBean("scope build", SCOPE_BUILD))
        mBaseRecyclerBean.add(BaseRecyclerBean("等多个请求同时请求的返回结果", SAME_REQUEST))
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            SIMPLE_EXAMPLE -> simpleExample()
            RUNBLOCKING -> runBlockingExample()
            JOB -> jobExample()
            SCOPE_BUILD -> {
//                scopeBuildExample()
                test()
            }
            SAME_REQUEST->{
                concurrenceRequest()
            }
        }

    }

    private fun scopeBuildExample() {
        runBlocking {
            launch {
                delay(886)
                 ALogUtils.d(TAG,"runBlocking task is running")
            }

            coroutineScope {
                launch {
                    delay(1314)
                     ALogUtils.d(TAG,"coroutine scope task is running")
                }

                delay(520)
                ALogUtils.d(TAG,"coroutine scope is finish")
            }
             ALogUtils.d(TAG,"coroutine scope is over")
        }
    }

    private fun jobExample() {
        runBlocking{
            var job = GlobalScope.launch {
                delay(520)
                ALogUtils.d(TAG,"World!")
            }
            ALogUtils.d(TAG,"Hello,")
            job.join()
            ALogUtils.d(TAG,"job ok")
        }
    }

    private fun runBlockingExample() {
        runBlocking{
            GlobalScope.launch {
                delay(520)
                ALogUtils.d(TAG,"World!")
            }
            ALogUtils.d(TAG,"Hello,")
            delay(1314)
            ALogUtils.d(TAG,"runBlocking ok")
        }
    }

    fun simpleExample(){
        GlobalScope.launch {
            delay(520)
            ALogUtils.d(TAG,"World! ")
        }
        ALogUtils.d(TAG,"Hello,")
        Thread.sleep(123)
//        runBlocking { delay(1314) }
        ALogUtils.d(TAG,"GlobalScope ok")
    }





    fun test(){
        runBlocking{

            val job = async {
                Thread(Runnable {
                    var i = 0;
                    while(i < 10){
                        i++;
                        Log.e(TAG, ": test $i " );
                        Thread.sleep(100)
                    }
                }).start()
                "result1"
            }

            Log.e(TAG, ": test 2");
        }
        Log.e(TAG, ": test 完成了" );
    }


    /**
     * 多个请求同时返回
     */
    fun concurrenceRequest(){
        GlobalScope.launch {
            var addResult = withContext(Dispatchers.IO){
                val one = async { one() }
                val two = async { two() }
                one.await() + two.await()
            }
            Log.d(TAG, "addResult() called = "+ addResult);
        }
    }

    suspend fun one():String{
        delay(1000)
        return "one Result"
    }

    suspend fun two():String{
        delay(3000)
        return "two Result"
    }
}
