package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import androidx.annotation.IntegerRes;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.tools.BitmapUtils;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.tools.GLESTools;


/**
 * Created by ICE on 2017/11/6.
 */

public class ImageTexture {
    private int imageTextureId;
    private int frameBufferTextureId;
    private int frameBuffer;
    private int imageSize[];
    private int outWidth;
    private int outHeight;

    public ImageTexture(int outWidth, int outHeight) {
        imageSize = new int[2];
        this.outWidth = outWidth;
        this.outHeight = outHeight;
    }

    public ImageTexture load(Context context, String filePath, boolean isAssetsFile) {
        if (isAssetsFile) {
            return loadBitmap(BitmapUtils.loadBitmapFromAssets(context, filePath));
        } else {
            return  loadBitmap(BitmapUtils.loadBitmapFromDisk(context, filePath));
        }
    }

    public ImageTexture load(Context context, @IntegerRes int resId) {
        return loadBitmap(BitmapUtils.loadBitmapFromRaw(context, resId));
    }

    public ImageTexture loadBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            imageTextureId = GLESTools.loadTexture(bitmap, GLESTools.NO_TEXTURE);
            imageSize[0] = bitmap.getWidth();
            imageSize[1] = bitmap.getHeight();
            int[] frameBufferArr = new int[1];
            int[] frameBufferTextureArr = new int[1];
            GLESTools.createFrameBuff(frameBufferArr,
                    frameBufferTextureArr,
                    outWidth,
                    outHeight);
            frameBuffer = frameBufferArr[0];
            frameBufferTextureId = frameBufferTextureArr[0];
            bitmap.recycle();
        }
        return this;
    }

    public void setImageTextureId(int imageTextureId) {
        this.imageTextureId = imageTextureId;
    }

    public int getImageTextureId() {
        return imageTextureId;
    }
    public int getTextureId() {
        return frameBufferTextureId;
    }
    public int getFrameBuffer() {
        return frameBuffer;
    }

    public int getImageWidth() {
        return imageSize[0];
    }

    public int getImageHeight() {
        return imageSize[1];
    }

    public float getImageRatio() {
        return 1.0f * imageSize[0] / imageSize[1];
    }

    public void destroy() {
        GLES20.glDeleteTextures(2, new int[]{imageTextureId, frameBufferTextureId}, 0);
        GLES20.glDeleteFramebuffers(1, new int[]{frameBuffer}, 0);
    }
}
