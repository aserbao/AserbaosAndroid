package com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.base.base.BaseRecyclerViewActivity
/**
 * @author: aserbao
 * @date:2020/8/18 9:29 AM
 * @package:com.aserbao.aserbaosandroid.opengl.OneOpenGl.texture
 * @describle: OpenGl纹理
 */
class OpenGlTextureAct : BaseRecyclerViewActivity() {
    override fun itemClickBack(view: View?, position: Int, isLongClick: Boolean, comeFrom: Int) {
    }

    override fun initGetData() {

        addViewToFrameLayout( MySurfaceView(this),true,true,true)
    }


}