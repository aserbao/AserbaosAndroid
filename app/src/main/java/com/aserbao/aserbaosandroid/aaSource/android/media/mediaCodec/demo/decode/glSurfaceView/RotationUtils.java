package com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-12-19 20:14
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.media.mediaCodec.demo.decode.glSurfaceView
 */
public class RotationUtils {
    public static final int ROT_0 = 0;
    public static final int ROT_90 = 90;
    public static final int ROT_180 = 180;
    public static final int ROT_270 = 270;
    public static float[] setRotation(int rotation) {
        float[] coord = null;
        switch (rotation) {
            case ROT_0:
                coord = new float[]{
                    0.0f, 0.0f,
                    0.0f, 1.0f,
                    1.0f, 0.0f,
                    1.0f, 1.0f,
                };
                break;
            case ROT_90:
                coord = new float[]{
                    0.0f, 1.0f,
                    1.0f, 1.0f,
                    0.0f, 0.0f,
                    1.0f, 0.0f
                };
                break;
            case ROT_180:
                coord = new float[]{
                    1.0f, 1.0f,
                    1.0f, 0.0f,
                    0.0f, 1.0f,
                    0.0f, 0.0f,
                };
                break;
            case ROT_270:
                coord = new float[]{
                    1.0f, 0.0f,
                    0.0f, 0.0f,
                    1.0f, 1.0f,
                    0.0f, 1.0f
                };
                break;
        }
        return coord;
    }
}
