package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/12
 * email: 1142803753@qq.com
 */
public interface IShotListener {
    void isHit(Shot shot,TargetView targetView);
    void isLoseEfficacy(Shot shot);
}
