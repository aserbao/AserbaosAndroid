package com.aserbao.aserbaosandroid.designMode.strategy;

import com.aserbao.aserbaosandroid.designMode.strategy.attack.IAttackBehavior;
import com.aserbao.aserbaosandroid.designMode.strategy.speed.ISpeedBehavior;

/**
 * Created by aserbao on 2018 2018/3/6.22:58
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class GreenHeadZombie extends Character {//绿头僵尸
    public GreenHeadZombie(IAttackBehavior iAttackBehavior, ISpeedBehavior iSpeedBehavior) {
        mIAttackBehavior = iAttackBehavior;
        mISpeedBehavior = iSpeedBehavior;
    }
    @Override
    void display() {
        System.err.println("My head is Green");
    }
}

