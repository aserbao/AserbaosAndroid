package com.aserbao.jetpack.architecture.databinding.observable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import com.aserbao.jetpack.R
import com.aserbao.jetpack.databinding.ActivityObservableFileBinding

class ObservableFileActivity : AppCompatActivity() {
    private val observableFieldProfile = ObservableFieldProfile("Ada", 60)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityObservableFileBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_observable_file)
        binding.user = observableFieldProfile
//        setContentView(R.layout.activity_observable_file)
    }
}
