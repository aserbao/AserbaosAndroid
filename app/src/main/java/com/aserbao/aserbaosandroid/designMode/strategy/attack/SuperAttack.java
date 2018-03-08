package com.aserbao.aserbaosandroid.designMode.strategy.attack;

/**
 * Created by aserbao on 2018 2018/3/8.23:27
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class SuperAttack implements IAttackBehavior {
    @Override
    public void attack() {
        System.err.println("I use super attack");
    }
}
