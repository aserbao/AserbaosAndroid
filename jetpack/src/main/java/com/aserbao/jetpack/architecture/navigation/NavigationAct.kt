package com.aserbao.jetpack.architecture.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.aserbao.jetpack.R
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

/**
 * @author: aserbao
 * @date:2020/12/16 9:39 AM
 * @package:com.aserbao.jetpack.architecture.navigation
 * @describle: Jetpack navigation
 */
class NavigationAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_act)
    }
}