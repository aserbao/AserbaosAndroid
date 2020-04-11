package com.aserbao.aserbaosandroid.aaThird.jetpack.databinding.viewmodels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.databinding.ActivityDataBindingBinding

class DataBindingByVMActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SimpleViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)
//        setContentView(R.layout.activity_data_binding)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }
}
