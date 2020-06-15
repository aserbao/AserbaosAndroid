package com.aserbao.aserbaosandroid.ui.customView.seekbar.discreate;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

/**
 * Simple {@link StateDrawable} implementation
 * to draw rectangles
 *
 * @hide
 */
public class TrackRectDrawable extends StateDrawable {
    public TrackRectDrawable(@NonNull ColorStateList tintStateList) {
        super(tintStateList);
    }

    @Override
    void doDraw(Canvas canvas, Paint paint) {
        canvas.drawRect(getBounds(), paint);
    }

}
