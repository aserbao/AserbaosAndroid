package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.explosionViewAnimation.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.explosionViewAnimation.factory.InnerFallingParticleFactory;

import java.util.Random;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class InnerFallingParticle extends Particle{
    static Random random = new Random();
    float radius = InnerFallingParticleFactory.PART_WH;
    float alpha = 1.0f;
    Rect mBound;
    float ox,oy;
    /**
     * @param color 颜色
     * @param x
     * @param y
     */
    public InnerFallingParticle(int color, float x, float y, Rect bound) {
        super(color, x, y);
        mBound = bound;
        ox = x;
        oy = y;
    }


    protected void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha)); //这样透明颜色就不是黑色了
        canvas.drawCircle(cx, cy, radius, paint);
    }

    protected void caculate(float factor){
        if(factor<=0.5){
            if(oy<mBound.exactCenterY()) {
                cy = cy + factor * random.nextInt(mBound.height() / 2);
                if(ox>mBound.exactCenterX()){
                    cx = cx - factor * random.nextInt(mBound.width()/2) * (random.nextFloat());
                }else{
                    cx = cx + factor * random.nextInt(mBound.width()/2) * (random.nextFloat());
                }
            }
        }else{
            cy = cy + factor * random.nextInt(mBound.height() / 2);
            if(ox>mBound.exactCenterX()){
                cx = cx - factor * random.nextInt(mBound.width()/2) * (random.nextFloat());
            }else{
                cx = cx + factor * random.nextInt(mBound.width()/2) * (random.nextFloat());
            }
        }

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
