package com.aserbao.camera.utils

import android.graphics.ImageFormat
import android.util.Log
import android.util.Size
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
    /**
     * get the suitable size of capture pic size
     * @param choices Array<Size>
     * @param recommendWidth Int   the width of pic that you want
     * @param recommendHeight Int  the height of pic that you want
     * @param aspectRatioWH Float
     * @return Size
     */
    open fun chooseOptimalPicSize(
        choices: Array<Size>,
        recommendWidth: Int,
        recommendHeight: Int,
        aspectRatioWH: Float
        ):Size{
        // Collect the supported resolutions that are at least as big as the preview Surface
        val bigEnough = ArrayList<Size>()
        // Collect the supported resolutions that are smaller than the preview Surface
        val notBigEnough = ArrayList<Size>()
        for (option in choices) {
            var toInt = (option.width * aspectRatioWH).toInt()
            if(option.width == recommendWidth && option.height == recommendHeight){
                return option
            }
            if (option.height == toInt) {
                if (option.width >= recommendWidth && option.height >= recommendHeight) {
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