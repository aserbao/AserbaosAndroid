package com.aserbao.aserbaosandroid.aaThird.jetpack.viewbind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue
import com.aserbao.aserbaosandroid.aaThird.jetpack.viewmodel.SharedViewModels
import com.aserbao.aserbaosandroid.databinding.FragmentLeftBinding
import kotlinx.android.synthetic.main.fragment_left.*

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/3/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaThird.jetpack.viewbind
 */
open class ViewBindBaseFragment : Fragment(){
    public lateinit var model: SharedViewModels
    public lateinit var binding: FragmentLeftBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLeftBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(activity!!).get(SharedViewModels::class.java)
        model.getCurrentName().observe(this, Observer {
            binding.topTv.setText("左边数据："+ it)
        })
        model.getCurrentRightName().observe(this, Observer {
            binding.bottomTv.setText("右边数据："+ it)
        })
    }
}