package com.aserbao.common.ui.act

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.aserbao.common.ui.photo.CropImageViewActivity
import com.example.base.arouter.RouterConfig
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean

@Route(path = RouterConfig.JUMP_TO_COMMON_ACTIVITY)
class CommonUIActivity : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0 -> {
                var instance = ARouter.getInstance()
                var build = instance.build(RouterConfig.JUMP_TO_CAMERA_ACTIVITY)
                build.navigation()
            }
        }
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("入口2",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("photo",CropImageViewActivity::class.java))
    }

}
