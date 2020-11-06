package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.aspan;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.style.LineBackgroundSpan;
import android.util.Log;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/11/6
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.aspan
 */
public class BackgroundCoordinator implements LineBackgroundSpan {
    @Override
    public void drawBackground(
        Canvas canvas,
        Paint paint,
        int left,
        int right,
        int top,
        int baseline,
        int bottom,
        CharSequence text,
        int start,
        int end,
        int currentLine) {
        Spanned spanned = (Spanned) text;
        for (BackgroundSpan span : spanned.getSpans(start, end, BackgroundSpan.class)) {
            span.draw(canvas, spanned);
        }
    }
}

class BackgroundSpan {
    public void draw(Canvas canvas, Spanned spanned) {
        Log.d("AtA", "draw() called with: canvas = [" + canvas + "], spanned = [" + spanned.toString() + "]" );
        // Custom background rendering...
    }
}
