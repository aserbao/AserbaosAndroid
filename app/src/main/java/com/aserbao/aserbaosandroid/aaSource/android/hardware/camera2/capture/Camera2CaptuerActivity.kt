package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.capture

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.ImageReader
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.Surface
import android.view.TextureView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.CameraOperaion
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.Facing
import com.aserbao.aserbaosandroid.cameratest.AutoFitTextureView
import com.aserbao.aserbaosandroid.databinding.ActivityCaptureCameraBinding
import com.getremark.base.kotlin_ext.singleClick
import java.util.*

class Camera2CaptuerActivity : AppCompatActivity() {
    companion object{
         private const val TAG = "Camera2CaptuerActivity"
         const val W9H16 = 9f/16f;
         const val W3H4 = 3f/4f;
    }

    /**
     * [TextureView.SurfaceTextureListener] handles several lifecycle events on a
     * [TextureView].
     */
    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {

        override fun onSurfaceTextureAvailable(texture: SurfaceTexture, width: Int, height: Int) {
            openCamera(width, height)
        }

        override fun onSurfaceTextureSizeChanged(texture: SurfaceTexture, width: Int, height: Int) {
            Log.e(TAG, ": onSurfaceTextureSizeChanged width = " + width + " height = "+ height );
        }

        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture) = true

        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) = Unit

    }

    lateinit var mTextureView: AutoFitTextureView
    lateinit var activityCaptureCameraBinding:ActivityCaptureCameraBinding
    private var captureRequest: CaptureRequest? = null
    var mCameraCharacteristics:CameraCharacteristics ?= null
    private var mediaRecorder: MediaRecorder? = null

    /**
     * The [android.util.Size] of video recording.
     */
    private lateinit var videoSize: Size

    private lateinit var previewSize: Size // 最适合的尺寸

    var ratioWH:Float = W9H16 // 宽高比

    open var cameraDevice:CameraDevice ?= null
    var cameraFacing = Facing.FRONT;

    private var imageReader: ImageReader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCaptureCameraBinding = ActivityCaptureCameraBinding.inflate(LayoutInflater.from(this))
        setContentView(activityCaptureCameraBinding.root)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        initView()
        initViewEvent();
    }

    override fun onResume() {
        super.onResume()
        if(mTextureView.isAvailable){
            mTextureView.setAspectRatio(ratioWH)
            openCamera(mTextureView.width,mTextureView.height)
        }else{
            mTextureView.surfaceTextureListener = surfaceTextureListener
        }
    }

    override fun onPause() {
        super.onPause()
        closeCamera()
    }

    fun closeCamera(){
        cameraDevice?.apply {
            this.close()
        }
    }


    private fun initView() {
        mTextureView = activityCaptureCameraBinding.autoTextureView;
    }

    private fun initViewEvent() {
        activityCaptureCameraBinding.apply {
            switchBtn.singleClick {
                if (cameraFacing == Facing.BACK) {
                    cameraFacing = Facing.FRONT
                }else{
                    cameraFacing = Facing.BACK
                }
                closeCamera()
                openCamera(mTextureView.width, mTextureView.height)
            }
            ratioBtn.singleClick {
                var frameLayout = activityCaptureCameraBinding.frameLayout
                var layoutParams = frameLayout.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.dimensionRatio = "h,3:4"
                activityCaptureCameraBinding.frameLayout.layoutParams = layoutParams
                ratioWH = W3H4
                closeCamera()
                openCamera(mTextureView.width,mTextureView.height)
            }
        }
    }

    /**
     * 打开相机权限
     * @param holder
     */
    fun openCamera(width: Int, height: Int) {
        Log.e(TAG, ": openCamera width = "+ width + " height = " + height);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show()
            return
        }
        val mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var usedCameraId: String? = null
        try {
            if (mCameraManager != null) {
                for (cameraId in mCameraManager.getCameraIdList()) {
                    mCameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraId)
                    val facing: Int = mCameraCharacteristics!!.get(CameraCharacteristics.LENS_FACING)
                    if (facing != null && facing === cameraFacing.value) {
                        usedCameraId = cameraId
                        setupCameraCharacteristics(mCameraCharacteristics!!,width,height)
                        break
                    }
                }
            }
            mediaRecorder = MediaRecorder()
            mCameraManager.openCamera(usedCameraId, mStateCall, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun setupCameraCharacteristics(characteristics: CameraCharacteristics,width: Int,height: Int) {
        val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP) ?:
        throw RuntimeException("Cannot get available preview/video sizes")
        videoSize = chooseVideoSize(map.getOutputSizes(MediaRecorder::class.java))
        var outputSizes = map.getOutputSizes(SurfaceTexture::class.java)
//        previewSize = CameraOperaion.chooseOptimalSize(map.getOutputSizes(SurfaceTexture::class.java), width,height,ratioWH)
        previewSize = CameraOperaion.chooseOptimalSize(outputSizes,mTextureView.width,mTextureView.height,1920,1080,ratioWH)!!
        mTextureView.setAspectRatio(ratioWH)
    }

    /**
     * we don't use sizes larger than 1080p,MediaRecorder cannot handle such a high-resolution video
     * @param choices Array<Size>
     * @return Size
     */
    private fun chooseVideoSize(choices: Array<Size>) = choices.firstOrNull {
        it.width == ((it.height / ratioWH).toInt()) && it.width <= 1080 } ?: choices[choices.size - 1]


    var mStateCall = object:CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera;
            startPreview(camera)
        }

        override fun onDisconnected(camera: CameraDevice) {}
        override fun onError(camera: CameraDevice, error: Int) {}

        override fun onClosed(camera: CameraDevice) {
            super.onClosed(camera)
            cameraDevice  = null
        }
    }

    /**
     * [CameraDevice.StateCallback] is called when [CameraDevice] changes its status.
     */
    private fun startPreview(camera: CameraDevice) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            var surfaceTexture = mTextureView.surfaceTexture
            surfaceTexture.setDefaultBufferSize(previewSize.width,previewSize.height)
            Log.e(TAG, ": startPreview previewSize = " + previewSize );
            val surface = Surface(surfaceTexture)
            var cameraCaptureRequest: CaptureRequest.Builder? = null
            cameraCaptureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            cameraCaptureRequest.addTarget(surface)
            try {
                camera.createCaptureSession(Arrays.asList(surface), object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        try {
                            captureRequest = cameraCaptureRequest.build()
                            session.setRepeatingRequest(captureRequest, object : CameraCaptureSession.CaptureCallback() {
                                override fun onCaptureStarted(session: CameraCaptureSession, request: CaptureRequest, timestamp: Long, frameNumber: Long) {
                                    super.onCaptureStarted(session, request, timestamp, frameNumber)
                                }
                            }, null)
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {}
                }, null)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }


}

