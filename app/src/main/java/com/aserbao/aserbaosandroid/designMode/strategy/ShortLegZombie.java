package com.aserbao.aserbaosandroid.designMode.strategy;

import com.aserbao.aserbaosandroid.designMode.strategy.attack.IAttackBehavior;
import com.aserbao.aserbaosandroid.designMode.strategy.speed.ISpeedBehavior;

/**
 * Created by aserbao on 2018 2018/3/6.22:58
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class ShortLegZombie extends Character {//短腿僵尸


    public ShortLegZombie(IAttackBehavior iAttackBehavior, ISpeedBehavior iSpeedBehavior) {
        mIAttackBehavior = iAttackBehavior;
        mISpeedBehavior = iSpeedBehavior;
    }

    @Override
    void display() {
        System.err.println("I'm ShortLegZombie");
    }

}

