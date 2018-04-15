package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.image;

import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.filter.hardvideofilter.BaseHardVideoFilter;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.tools.GLESTools;

import java.nio.FloatBuffer;

/**
 * Created by ICE on 2017/11/6.
 */

public class BaseDrawImageFilter extends BaseHardVideoFilter {
    protected int glDefaultProgram;
    protected int glDefaultTextureLoc;
    protected int glDefaultCamPostionLoc;
    protected int glDefaultCamTextureCoordLoc;

    public BaseDrawImageFilter() {
    }

    @Override
    public void onInit(int videoWidth, int videoHeight) {
        super.onInit(videoWidth, videoHeight);
        glDefaultProgram = GLESTools.createProgram(
                ImageDrawConstants.Default_vertexShader_filter,
                ImageDrawConstants.Default_fragmentshader_filter);
        GLES20.glUseProgram(glDefaultProgram);
        glDefaultTextureLoc = GLES20.glGetUniformLocation(glDefaultProgram, "uCamTexture");
        glDefaultCamPostionLoc = GLES20.glGetAttribLocation(glDefaultProgram, "aCamPosition");
        glDefaultCamTextureCoordLoc = GLES20.glGetAttribLocation(glDefaultProgram, "aCamTextureCoord");
    }


    @Override
    public void onDraw(int cameraTexture, int targetFrameBuffer, FloatBuffer shapeBuffer, FloatBuffer textrueBuffer) {
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, targetFrameBuffer);
        GLES20.glUseProgram(glDefaultProgram);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, cameraTexture);
        GLES20.glUniform1i(glDefaultTextureLoc, 0);
        GLES20.glEnableVertexAttribArray(glDefaultCamPostionLoc);
        GLES20.glEnableVertexAttribArray(glDefaultCamTextureCoordLoc);
        shapeBuffer.position(0);
        GLES20.glVertexAttribPointer(glDefaultCamPostionLoc, 2,
                GLES20.GL_FLOAT, false,
                2 * 4, shapeBuffer);
        textrueBuffer.position(0);
        GLES20.glVertexAttribPointer(glDefaultCamTextureCoordLoc, 2,
                GLES20.GL_FLOAT, false,
                2 * 4, textrueBuffer);
        onPreDraw();
        GLES20.glViewport(0, 0, outVideoWidth, outVideoHeight);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawIndecesBuffer.limit(), GLES20.GL_UNSIGNED_SHORT, drawIndecesBuffer);
        GLES20.glFinish();
        onAfterDraw();
        GLES20.glDisableVertexAttribArray(glDefaultCamPostionLoc);
        GLES20.glDisableVertexAttribArray(glDefaultCamTextureCoordLoc);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);
        GLES20.glUseProgram(0);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
    }

    protected void onPreDraw() {

    }

    protected void onAfterDraw() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GLES20.glDeleteProgram(glDefaultProgram);
    }
}
