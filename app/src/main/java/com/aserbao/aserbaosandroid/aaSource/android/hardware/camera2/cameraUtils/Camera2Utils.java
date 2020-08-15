package com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.cameraUtils;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.view.SurfaceView;
import android.view.TextureView;

import com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.CompareSizesByArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/8/13
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.hardware.camera2.cameraUtils
 */
public class Camera2Utils {
    private static final String TAG = "Camera2Utils";

    /**
     * https://developer.android.google.cn/reference/android/hardware/camera2/CameraCharacteristics.html#COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES
     * 获取CameraCharacteristics的camera特性信息
     * @param characteristics
     */
    public static String setupCameraCharacteristics(CameraCharacteristics characteristics){
		/**获取相机角度**/
        int cOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
		/**是否支持闪光灯**/
        boolean isSupportFlash = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        /**最大放大比例**/
        Float maxZoom = characteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        /**获取成像区域**/
        Rect sensorInforect = characteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        /**支持的人脸检测模式**/
        int[] ints = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
		/**支持的最大检测人脸数量**/
        int maxFaceCount = characteristics.get(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT);
		/**支持的最大帧间间隔**/
        Long maxFrameDuration = characteristics.get(CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION);
		/**相机设置支持的裁剪类型**/
        Integer croppingType = characteristics.get(CameraCharacteristics.SCALER_CROPPING_TYPE);
		/**自动曝光列表**/
        int[] aeAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
		/**相机设备支持的帧率范围**/
        Range<Integer>[] fpsRanges = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
		/**相机设备支持的曝光补偿范围**/
        Range<Integer> compensationRange = characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
		/**相机设备支持的曝光最小补偿级别**/
        Rational compensationStep = characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
		/**相机设备支持的自动聚焦模式**/
        int[] afAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
		/**相机设备支持的颜色效果列表**/
        int[] effects = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS);
		/**相机的场景列表**/
        int[] sceneModes = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES);
		/**相机的稳定模式**/
        int[] videoStabilizationModes = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
		/**相机的支持自动黑白平衡模式**/
        int[] awbAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
		/**相机支持的硬件等级**/
        Integer hardwareLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
		/**相机支持的JPEG格式缩略图的大小列表**/
        Size[] thumbnailSizes = characteristics.get(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES);
		/**相机支持的光圈大小列表**/
        float[] apertures = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES);
		/**相机的焦距列表**/
        float[] focalLengths = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
		/**相机的降噪模式列表**/
        int[] noiseReductionModes = characteristics.get(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES);
		/**相机的支持完全功能列表**/
        int[] ints1 = characteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        String infoVersion = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            /**相机制造商的信息**/
            infoVersion = characteristics.get(CameraCharacteristics.INFO_VERSION);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("相机制造商的信息").append(infoVersion).append("\n")
            .append("获取相机角度").append(cOrientation).append("\n")
            .append("是否支持闪光灯").append(isSupportFlash).append("\n")
            .append("最大放大比例").append(maxZoom).append("\n")
            .append("获取成像区域").append(sensorInforect.toString()).append("\n")
            .append("支持的人脸检测模式").append(Arrays.toString(ints)).append("\n")
            .append("支持的最大检测人脸数量").append(maxFaceCount).append("\n")
            .append("支持的最大帧间间隔").append(maxFrameDuration).append("\n")
            .append("相机设置支持的裁剪类型").append(croppingType).append("\n")
            .append("自动曝光列表").append(Arrays.toString(aeAvailableModes)).append("\n")
            .append("相机设备支持的帧率范围").append(fpsRanges.toString()).append("\n")
            .append("相机设备支持的曝光补偿范围").append(compensationRange.toString()).append("\n")
            .append("相机设备支持的曝光最小补偿级别").append(compensationStep.toString()).append("\n")
            .append("相机设备支持的自动聚焦模式").append(Arrays.toString(afAvailableModes)).append("\n")
            .append("相机设备支持的颜色效果列表").append(Arrays.toString(effects)).append("\n")
            .append("相机的场景列表").append(Arrays.toString(sceneModes)).append("\n")
            .append("相机的稳定模式").append(Arrays.toString(videoStabilizationModes)).append("\n")
            .append("自动黑白平衡模式").append(Arrays.toString(awbAvailableModes)).append("\n")
            .append("相机支持的硬件等级").append(hardwareLevel).append("\n")
            .append("相机支持的JPEG格式缩略图的大小列表").append(Arrays.toString(thumbnailSizes)).append("\n")
            .append("相机支持的光圈大小列表").append(Arrays.toString(apertures)).append("\n")
            .append("相机的焦距列表").append(Arrays.toString(focalLengths)).append("\n")
            .append("相机的降噪模式列表").append(Arrays.toString(noiseReductionModes)).append("\n")
            ;

        Log.d(TAG, "setupCameraCharacteristics"  +sb.toString());
        return sb.toString();
    }


    /**
     * 获取流信息
     * @param characteristics
     * @return
     */
    public static String getCameraStreamInfo(CameraCharacteristics characteristics){
        StreamConfigurationMap streamConfigurationMap = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
		/**输出流列表**/
        Size[] surfaceTextureOutputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
        Size[] imageReaderOutputSizes = streamConfigurationMap.getOutputSizes(ImageReader.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int[] inputFormats = streamConfigurationMap.getInputFormats();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("SurfaceTexture支持的输出列表").append(Arrays.toString(surfaceTextureOutputSizes)).append("\n")
            .append("ImageReader支持的输出列表").append(Arrays.toString(imageReaderOutputSizes)).append("\n")
            ;
        Log.d(TAG, "getCameraStreamInfo: " + sb.toString());
        return sb.toString();
    }


         /**
         * 获取最佳预览尺寸
         * @param outputSizes 相机支持的输出
         * @param recommendWidth 预览容器的宽
         * @param recommendHeight 预览容器的高
         * @param whAspectRatio 预览宽高比 eg:9f/16f
         * @return
         */
        public static Size chooseOptimalPreviewSize(Size[] outputSizes, int recommendWidth, int recommendHeight, float whAspectRatio ){
            List bigEnough = new ArrayList();
            List notBigEnough = new ArrayList();
            for (Size option : outputSizes) {
                int height = (int) (option.getWidth() * whAspectRatio);
                if(option.getWidth() == recommendWidth && option.getHeight() == recommendHeight){
                    return option;
                }
                if(option.getHeight() == height){
                    if(option.getWidth() > recommendWidth && option.getHeight() > recommendHeight){
                        bigEnough.add(option);
                    }else{
                        notBigEnough.add(option);
                    }
                }
            }

            if (bigEnough.size() > 0) {
                return Collections.min(bigEnough, new CompareSizesByArea());
            } else if (notBigEnough.size() > 0) {
                return Collections.max(notBigEnough, new CompareSizesByArea());
            } else {
                Log.e(TAG, "Couldn't find any suitable preview size");
                return new Size(1920,1080);
            }
        }
}
