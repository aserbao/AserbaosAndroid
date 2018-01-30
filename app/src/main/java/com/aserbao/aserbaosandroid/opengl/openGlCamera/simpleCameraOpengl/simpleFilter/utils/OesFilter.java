package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter.utils;

import android.content.res.Resources;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleFilter.filters.AFilter;

/**
 * Description: 加载默认的滤镜的filter
 */
public class OesFilter extends AFilter {

    public OesFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("Shader/oes_base_vertex.sh", "Shader/oes_base_fragment.sh");
    }

    @Override
    protected void onBindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0+getTextureType());
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,getTextureId());
        GLES20.glUniform1i(mHTexture,getTextureType());
    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }

}
