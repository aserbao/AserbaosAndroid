package com.aserbao.camera;

import android.graphics.SurfaceTexture;
import android.view.TextureView;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/10/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.camera
 */
interface ICamera {
    void openCamera(int cameraId, int width, int height);

}
