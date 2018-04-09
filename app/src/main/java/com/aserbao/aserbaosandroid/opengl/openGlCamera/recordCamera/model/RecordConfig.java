package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.model;


import android.hardware.Camera;

public class RecordConfig {
    private Size targetVideoSize;
    private int videoBufferQueueNum;
    private int bitRate;
    private int renderingMode;
    private int defaultCamera;
    private int frontCameraDirectionMode;
    private int backCameraDirectionMode;
    private int videoFPS;
    private int videoGOP;
    private boolean printDetailMsg;


    private RecordConfig() {
    }

    public static RecordConfig obtain() {
        RecordConfig res = new RecordConfig();
        res.setRenderingMode(MediaConfig.Rending_Model_OpenGLES);
        res.setTargetVideoSize(new Size(640, 480));
        res.setVideoFPS(25);
        res.setVideoGOP(1);
        res.setVideoBufferQueueNum(5);
        res.setBitRate(2000000);
        res.setPrintDetailMsg(false);
        res.setDefaultCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
        res.setBackCameraDirectionMode(MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0);
        res.setFrontCameraDirectionMode(MediaConfig.DirectionMode.FLAG_DIRECTION_ROATATION_0);
        return res;
    }


    /**
     * set the default camera to start stream
     */
    public void setDefaultCamera(int defaultCamera) {
        this.defaultCamera = defaultCamera;
    }

    /**
     * set front camera rotation & flip
     * @param frontCameraDirectionMode {@link MediaConfig.DirectionMode}
     */
    public void setFrontCameraDirectionMode(int frontCameraDirectionMode) {
        this.frontCameraDirectionMode = frontCameraDirectionMode;
    }
    /**
     * set front camera rotation & flip
     * @param backCameraDirectionMode {@link MediaConfig.DirectionMode}
     */
    public void setBackCameraDirectionMode(int backCameraDirectionMode) {
        this.backCameraDirectionMode = backCameraDirectionMode;
    }

    /**
     * set  renderingMode when using soft mode<br/>
     * no use for hard mode
     * @param renderingMode {@link MediaConfig#Rending_Model_OpenGLES}
     */
    public void setRenderingMode(int renderingMode) {
        this.renderingMode = renderingMode;
    }

    /**
     * no use for now
     * @param printDetailMsg
     */
    public void setPrintDetailMsg(boolean printDetailMsg) {
        this.printDetailMsg = printDetailMsg;
    }

    /**
     * set the target video size.<br/>
     * real video size may different from it.Depend on device.
     * @param videoSize
     */
    public void setTargetVideoSize(Size videoSize) {
        targetVideoSize = videoSize;
    }

    /**
     * set video buffer number for soft mode.<br/>
     * num larger:video Smoother,more memory.
     * @param num
     */
    public void setVideoBufferQueueNum(int num) {
        videoBufferQueueNum = num;
    }

    /**
     * set video bitrate
     * @param bitRate
     */
    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public int getVideoFPS() {
        return videoFPS;
    }

    public void setVideoFPS(int videoFPS) {
        this.videoFPS = videoFPS;
    }

    public int getVideoGOP(){
        return videoGOP;
    }

    public void setVideoGOP(int videoGOP){
        this.videoGOP = videoGOP;
    }

    public int getVideoBufferQueueNum() {
        return videoBufferQueueNum;
    }

    public int getBitRate() {
        return bitRate;
    }

    public Size getTargetVideoSize() {
        return targetVideoSize;
    }

    public int getDefaultCamera() {
        return defaultCamera;
    }

    public int getBackCameraDirectionMode() {
        return backCameraDirectionMode;
    }

    public int getFrontCameraDirectionMode() {
        return frontCameraDirectionMode;
    }

    public int getRenderingMode() {
        return renderingMode;
    }

    public boolean isPrintDetailMsg() {
        return printDetailMsg;
    }

    private boolean square = false;
    public void setSquare(boolean enable) {
        this.square = enable;
    }
    public boolean isSquare() {
        return this.square;
    }

    public boolean isSaveVideoEnable() {
        return true;
    }

    private String saveVideoPath = null;
    public void setSaveVideoPath(String path) {
        this.saveVideoPath = path;
    }
    public String getSaveVideoPath() {
        return this.saveVideoPath;
    }

}
