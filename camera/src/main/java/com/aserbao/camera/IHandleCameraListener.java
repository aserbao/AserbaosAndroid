package com.aserbao.camera;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/10/19
 * @project: AserbaosAndroid
 * @package: com.aserbao.camera
 */
public interface IHandleCameraListener {
    void capturePic(String picPath);
    void videoComplete(String videoPath);
    void error(Exception e);
}
