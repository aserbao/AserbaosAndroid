package com.aserbao.aserbaosandroid.designMode.strategy;

import com.aserbao.aserbaosandroid.designMode.strategy.attack.IAttackBehavior;
import com.aserbao.aserbaosandroid.designMode.strategy.speed.ISpeedBehavior;

/**
 * Created by aserbao on 2018 2018/3/4.22:47
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public abstract class Character {//抽象类

    public IAttackBehavior mIAttackBehavior;
    public ISpeedBehavior mISpeedBehavior;

    void move(){
        System.err.println("move");
    }
    void attack(){
        mIAttackBehavior.attack();
    }
    void speed(){
        mISpeedBehavior.speed();
    }

    public void setIAttackBehavior(IAttackBehavior IAttackBehavior) {
        mIAttackBehavior = IAttackBehavior;
    }

    public void setISpeedBehavior(ISpeedBehavior ISpeedBehavior) {
        mISpeedBehavior = ISpeedBehavior;
    }

    abstract void display();
}
