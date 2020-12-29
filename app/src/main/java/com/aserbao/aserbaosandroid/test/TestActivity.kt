package com.aserbao.aserbaosandroid.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.aserbao.aserbaosandroid.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.utils.data.StaticFinalValues
import kotlinx.android.synthetic.main.test_layout.*
import java.util.*

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
class TestActivity : BaseRecyclerViewActivity() {



    override fun onDetachedFromWindow() {
        Log.e(TAG, "onDetachedFromWindow: ")
        super.onDetachedFromWindow()
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {

    }

    override fun initGetData() {
        for (index in 1..100){
            mBaseRecyclerBean.add(BaseRecyclerBean(StaticFinalValues.VIEW_TEST,index.toString()))
        }
    }

    companion object {
        private const val TAG = "TestActivity"
    }
}