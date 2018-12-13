package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.FallingParticleFactory;

import java.util.Random;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class FallingParticle extends Particle{
    static Random random = new Random();
    float radius = FallingParticleFactory.PART_WH;
    float alpha = 1.0f;
    Rect mBound;
    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public FallingParticle(int color, float x, float y,Rect bound) {
        super(color, x, y);
        mBound = bound;
    }


    protected void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    protected void caculate(float factor){
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
