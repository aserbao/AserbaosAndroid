package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.simpleExplosion;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by azz on 15/11/19.
 * 爆破粒子
 */
public class Particle {

    public static final int PART_WH = 4; //默认小球宽高

    //原本的值（不可变）
//    float originCX;
//    float originCY;
//    float originRadius;

    //实际的值（可变）
    float cx; //center x of circle
    float cy; //center y of circle
    float radius;

    int color;
    float alpha;

    static Random random = new Random();

    Rect mBound;

    public static Particle generateParticle(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽

        Particle particle = new Particle();
        particle.mBound = bound;
        particle.color = color;
        particle.alpha = 1f;

        particle.radius = PART_WH;
        particle.cx = bound.left + PART_WH * column;
        particle.cy = bound.top + PART_WH * row;

        return particle;
    }

    public void advance(float factor) {
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
