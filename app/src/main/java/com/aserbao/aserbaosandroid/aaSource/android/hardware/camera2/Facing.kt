package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2

import android.hardware.camera2.CameraMetadata

/**
 * @author: aserbao
 * @date:2020/8/12 10:54 AM
 * @package:
 * @describle: Camera facing.
 */
enum class Facing(val value: Int) {
    /**
     * No facing. Camera will stop working.
     */
    NONE(-1),
    FRONT(CameraMetadata.LENS_FACING_FRONT),
    BACK(CameraMetadata.LENS_FACING_BACK);
}