package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2

import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCharacteristics
import android.util.Log
import android.util.Pair
import android.util.Size
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.DisplayUtils
import java.util.*
import kotlin.collections.ArrayList

object CameraOperaion{
     var TAG = this.javaClass.canonicalName
    @JvmStatic
    open fun chooseOptimalSize(
        choices: Array<Size>,
        textureViewWidth: Int,
        textureViewHeight: Int,
        maxWidth: Int,
        maxHeight: Int,
        aspectRatioWH: Float
    ): Size {
        // Collect the supported resolutions that are at least as big as the preview Surface
        val bigEnough = ArrayList<Size>()
        // Collect the supported resolutions that are smaller than the preview Surface
        val notBigEnough = ArrayList<Size>()
        for (option in choices) {
            var toInt = (option.width * aspectRatioWH).toInt()
            if (option.width <= maxWidth && option.height <= maxHeight && option.height == toInt) {
                if (option.width >= textureViewWidth && option.height >= textureViewHeight) {
                    bigEnough.add(option)
                } else {
                    notBigEnough.add(option)
                }
            }
        }
        // Pick the smallest of those big enough. If there is no one big enough, pick the
        // largest of those not big enough.
        if (bigEnough.size > 0) {
            return Collections.min(bigEnough, CompareSizesByArea())
        } else if (notBigEnough.size > 0) {
            return Collections.max(notBigEnough, CompareSizesByArea())
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size")
            return choices[0]
        }
    }
}