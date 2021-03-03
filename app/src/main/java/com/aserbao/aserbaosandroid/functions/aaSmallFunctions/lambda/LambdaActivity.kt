package com.aserbao.aserbaosandroid.functions.aaSmallFunctions.lambda

import android.util.Log
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import java.util.*

/**
 * Lambda 表达式
 */
class LambdaActivity : BaseRecyclerViewActivity() {
    companion object{
        val LAMBDA_TYPE_ONE = 0
    }
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            LAMBDA_TYPE_ONE ->{
                lambdaComparator()
            }
        }
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("comparator",LAMBDA_TYPE_ONE))
    }


    fun lambdaComparator(){
        var arrayOf = arrayOf(10, 5, 8, 2, 0, 7)
        Arrays.sort(arrayOf){i1,i2->i1.compareTo(i2)}
        Log.e(TAG, ": lambdaComparator"+ Arrays.toString(arrayOf) );
    }

    fun testAutoIntercept(){
        
    }
}