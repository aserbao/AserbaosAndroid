package com.aserbao.camera;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/10/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.camera
 */
interface ICamera {
    void openCamera(int cameraId, int width, int height);
    /**
     * 拍照
     * @param picPath test
     * @param ihandle
     */
    void capturePic(String picPath, IHandleCameraListener ihandle);
}
