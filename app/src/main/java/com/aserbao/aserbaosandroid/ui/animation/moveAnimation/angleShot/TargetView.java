package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.widget.ImageView;

public class TargetView {
    private ImageView mImageView;
    private float[] mFloats;

    public TargetView(ImageView imageView, float[] floats) {
        mImageView = imageView;
        mFloats = floats;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }

    public float[] getFloats() {
        return mFloats;
    }

    public void setFloats(float[] floats) {
        mFloats = floats;
    }
}
