package com.aserbao.camera

import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraMetadata
import android.util.AttributeSet
import android.view.TextureView

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/10/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.camera
 */
class Camera2View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : TextureView(context, attrs, defStyleAttr, defStyleRes){

    public var mRatioWH = 9f / 16f
    var cameraId = CameraMetadata.LENS_FACING_FRONT

    lateinit var mCameraControl: CameraCaptureControl

    /**
     * [TextureView.SurfaceTextureListener] handles several lifecycle events on a
     * [TextureView].
     */
    private val textureListener = object : TextureView.SurfaceTextureListener {

        override fun onSurfaceTextureAvailable(texture: SurfaceTexture, width: Int, height: Int) {
            mCameraControl.openCamera(cameraId, width, height)
        }

        override fun onSurfaceTextureSizeChanged(texture: SurfaceTexture, width: Int, height: Int) {
        }

        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture) = true

        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) = Unit

    }

    init {
        mCameraControl = CameraCaptureControl(context,this)
        surfaceTextureListener = textureListener
    }

    /**
     * 设置预览比例
     * @param ratioWH Float
     */
    fun setAspectRatio(ratioWH: Float) {
        mRatioWH = ratioWH
        requestLayout()
    }

    fun onResume(){
        if(isAvailable){
            setAspectRatio(mRatioWH)
            mCameraControl.openCamera(cameraId,width,height)
        }else{
            surfaceTextureListener = textureListener
        }
    }

    fun onPause(){
        mCameraControl.closeCamera()
    }

    fun switchCamera(){
        cameraId = if(cameraId == CameraMetadata.LENS_FACING_FRONT) CameraMetadata.LENS_FACING_BACK else CameraMetadata.LENS_FACING_FRONT
        mCameraControl.closeCamera()
        mCameraControl.openCamera(cameraId,width,height)
    }

    /**
     * 拍照
     * @param picName String ：test
     */
    fun capturePic(picName:String, ihandle :IHandleCameraListener){
        mCameraControl.capturePic(picName,ihandle)
    }

    /**
     * 开始录制
     * @param picVideo String
     * @param ihandle IHandleCameraListener
     */
    fun startRecord(picVideo:String,ihandle: IHandleCameraListener){
//        mCameraControl.startRecordingVideo(picVideo,ihandle)
    }

    /**
     * 停止录制
     */
    fun stopRecord(){
//        mCameraControl.stopRecordingVideo()
    }



    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        if (width < height * mRatioWH) {
            setMeasuredDimension(width, (width * mRatioWH).toInt())
        } else {
            setMeasuredDimension((height * mRatioWH).toInt(), height)
        }
    }

}