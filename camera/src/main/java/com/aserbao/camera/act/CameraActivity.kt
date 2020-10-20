package com.aserbao.camera.act

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.arouter.RouterConfig
import com.example.base.base.BaseRecyclerViewActivity
import com.example.base.base.beans.BaseRecyclerBean
import com.aserbao.camera.camerax.CameraXPreviewActivity

@Route(path = RouterConfig.JUMP_TO_CAMERA_ACTIVITY)
class CameraActivity : BaseRecyclerViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
        when(position){
            0-> {
                var instance = ARouter.getInstance()
                var build = instance.build(RouterConfig.JUMP_TO_CAMERA_ACTIVITY2)
                build.navigation()
            };
        }
    }

    override fun initGetData() {
        mBaseRecyclerBean.add(BaseRecyclerBean("入口",0))
        mBaseRecyclerBean.add(BaseRecyclerBean("CameraX的预览", CameraXPreviewActivity::class.java))
    }
}
