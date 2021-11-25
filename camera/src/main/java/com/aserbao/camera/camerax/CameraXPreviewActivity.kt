package com.aserbao.camera.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import androidx.camera.camera2.Camera2Config
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aserbao.camera.databinding.CameraxPreviewBinding
import com.example.base.utils.log.ALogUtils
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.camerax_preview.*
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
/**
 * @author: aserbao
 * @date:2020/12/4 5:42 PM
 * @package:com.aserbao.camera.camerax
 * @describle: CameraX 相机预览
 */
class CameraXPreviewActivity : AppCompatActivity() {
    companion object {
        const val TAG = "CameraXPreviewActivity"
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
    }
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val REQUEST_CODE_PERMISSIONS = 0

    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = CameraxPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewFinder = previewView
        initViewEvent()

        // Request camera permissions
        if (allPermissionsGranted()) {
            viewFinder.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
    }

    private fun initViewEvent() {
        custom_riv.singleClick {
            takePhoto()
        }

        camera_switch_button.singleClick{
            lensFacing = if (lensFacing == CameraSelector.LENS_FACING_FRONT)
                CameraSelector.LENS_FACING_BACK else CameraSelector.LENS_FACING_FRONT
            startCamera()
        }
    }

    // Add this after onCreate

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var viewFinder: PreviewView

    /**
     * 开启相机
     */
    private fun startCamera() {
        // Get screen metrics used to setup camera for full screen resolution
        val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
        val rotation = viewFinder.display.rotation

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            // Preview
            preview = Preview.Builder().apply {
                // We request aspect ratio but no resolution
                setTargetAspectRatio(screenAspectRatio)
                // Set initial target rotation
                setTargetRotation(rotation)
            }.build()
            // Create configuration object for the image capture use case
            imageCapture = ImageCapture.Builder()
                .apply {
                    // We don't set a resolution for image capture; instead, we
                    // select a capture mode which will infer the appropriate
                    // resolution based on aspect ration and requested mode
                    setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    setTargetAspectRatio(screenAspectRatio)
                    setTargetRotation(rotation)
                }.build()


            // Bind the CameraProvider to the LifeCycleOwner
            val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
            // Bind use cases to lifecycle
            // If Android Studio complains about "this" being not a LifecycleOwner
            // try rebuilding the project or updating the appcompat dependency to
            // version 1.1.0 or higher.
//        CameraX.bindToLifecycle(this, preview!!,imageCapture)
            var cameraProvider = cameraProviderFuture.get()
            // Must unbind the use-cases before rebinding them
            cameraProvider.unbindAll()


            try {
                // A variable number of use-cases can be passed here -
                // camera provides access to CameraControl & CameraInfo
                var camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)
                // Attach the viewfinder's surface provider to preview use case
                preview?.setSurfaceProvider(viewFinder.createSurfaceProvider())
            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        },ContextCompat.getMainExecutor(this))
    }


    /**
     * Process result from permission request dialog box, has the request
     * been granted? If yes, start Camera. Otherwise display a toast
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                viewFinder.post { startCamera() }
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    /**
     * Check if all permission specified in the manifest have been granted
     */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    /**
     *  [androidx.camera.core.ImageAnalysisConfig] requires enum value of
     *  [androidx.camera.core.AspectRatio]. Currently it has values of 4:3 & 16:9.
     *
     *  Detecting the most suitable ratio for dimensions provided in @params by counting absolute
     *  of preview ratio to one of the provided values.
     *
     *  @param width - preview width
     *  @param height - preview height
     *  @return suitable aspect ratio
     */
    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }


    /**
     * 拍照
     */
    public fun takePhoto(){
        val file = File(externalMediaDirs.first(),
            "${System.currentTimeMillis()}.jpg")

        // Setup image capture metadata
        val metadata = ImageCapture.Metadata().apply {
            // Mirror image when using the front camera
            isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file)
            .setMetadata(metadata)
            .build()

        imageCapture?.takePicture(outputOptions, executor,
            object :  ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val path = outputFileResults.savedUri ?: Uri.fromFile(file)
                    val msg = "Photo capture succeeded: ${path}"
                    ALogUtils.d("CameraXApp", msg)
                    viewFinder.post {
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    val msg = "Photo capture failed: $exception"
                    ALogUtils.e("CameraXApp", msg +  exception.toString())
                    viewFinder.post {
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}
