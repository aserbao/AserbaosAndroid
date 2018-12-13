package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.explosionAnimation2.factory.VerticalAscentFactory;

import java.util.Random;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class VerticalAscentParticle extends Particle{
    static Random random = new Random();
    float radius = VerticalAscentFactory.PART_WH;
    float alpha;
    Rect mBound;
    float ox,oy;
    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public VerticalAscentParticle(int color, float x, float y, Rect bound) {
        super(color, x, y);
        ox = x;
        oy = y;
        mBound = bound;
    }


    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void caculate(float factor) {
        if(ox>mBound.exactCenterX()){
            cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat());
        }else{
            cx = cx - factor * random.nextInt(mBound.width()) * (random.nextFloat());
        }
        if(factor<=0.5){
            cy = cy - factor * random.nextInt(mBound.height() / 2);
        }else{
            cy = cy + factor * random.nextInt(mBound.height() / 2);
        }

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
