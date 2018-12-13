package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.BooleanFactory;
import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.ExplodeParticleFactory;

/**
 * @author crazychen
 * @version 1.0
 * @date 2015/12/2
 */
public class ExplodeParticle extends Particle{
    public float radius = BooleanFactory.PART_WH;
    public float alpha;
    public int color;
    public float cx;
    public float cy;
    public float baseCx;
    public float baseCy;
    public float baseRadius;
    public float top;
    public float bottom;
    public float mag;
    public float neg;
    public float life;
    public float overflow;
    
    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public ExplodeParticle(int color, float x, float y) {
        super(color, x, y);
    }


    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void caculate(float factor) {
        float f = 0f;
        float normalization = factor / ExplodeParticleFactory.END_VALUE;
        if (normalization < life || normalization > 1f - overflow) {
            alpha = 0f;
            return;
        }
        normalization = (normalization - life) / (1f - life - overflow);
        float f2 = normalization * ExplodeParticleFactory.END_VALUE;
        if (normalization >= 0.7f) {
            f = (normalization - 0.7f) / 0.3f;
        }
        alpha = 1f - f;
        f = bottom * f2;
        cx = baseCx + f;
        cy = (float) (baseCy - this.neg * Math.pow(f, 2.0)) - f * mag;
        radius = ExplodeParticleFactory.V + (baseRadius - ExplodeParticleFactory.V) * f2;
    }
}