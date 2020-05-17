package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.capture

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.*
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.Facing
import com.aserbao.aserbaosandroid.databinding.ActivityCaptureCameraBinding
import com.getremark.base.kotlin_ext.singleClick
import java.util.*

class Camera2CaptuerActivity : AppCompatActivity(), SurfaceHolder.Callback {
    lateinit var mSurfaceView: SurfaceView
    lateinit var mHolder: SurfaceHolder
    lateinit var activityCaptureCameraBinding:ActivityCaptureCameraBinding
    private var captureRequest: CaptureRequest? = null
    var mCameraCharacteristics:CameraCharacteristics ?= null

    open var cameraDevice:CameraDevice ?= null

    var cameraFacing = Facing.FRONT;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCaptureCameraBinding = ActivityCaptureCameraBinding.inflate(LayoutInflater.from(this))
        setContentView(activityCaptureCameraBinding.root)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        initView()
        initViewEvent();
    }

    private fun initView() {
        mSurfaceView = activityCaptureCameraBinding.mSurface;
        mHolder = mSurfaceView.holder
        mHolder.addCallback(this)
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
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
                openCamera()
            }
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

    override fun surfaceCreated(holder: SurfaceHolder) {
        mHolder = holder
        openCamera()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {}
    /**
     * 打开相机权限
     * @param holder
     */
    fun openCamera() {
        val mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        var usedCameraId: String? = null
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请提供相机权限", Toast.LENGTH_SHORT).show()
                return
            }
            if (mCameraManager != null) {
                for (cameraId in mCameraManager.getCameraIdList()) {
                    mCameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraId)
                    val facing: Int = mCameraCharacteristics!!.get(CameraCharacteristics.LENS_FACING)
                    if (facing != null && facing === cameraFacing.value) {
                        usedCameraId = cameraId
//                        setupCameraCharacteristics(mCameraCharacteristics)
                        break
                    }
                }
            }
            mCameraManager.openCamera(usedCameraId, mStateCall, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
    companion object {
        private const val TAG = "Camera2SurfaceViewActiv"
    }

    var mStateCall = object:CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val surface = mHolder.surface
                val mSurfaces: MutableList<Surface> = ArrayList()
                mSurfaces.add(surface)
                try {
                    camera.createCaptureSession(mSurfaces, object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            var cameraCaptureRequest: CaptureRequest.Builder? = null
                            try {
                                cameraCaptureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                                cameraCaptureRequest.addTarget(mHolder.surface)
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

        override fun onDisconnected(camera: CameraDevice) {}
        override fun onError(camera: CameraDevice, error: Int) {}

        override fun onClosed(camera: CameraDevice) {
            super.onClosed(camera)
            cameraDevice  = null
        }
    }


}

