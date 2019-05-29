package com.aserbao.aserbaosandroid.aaThird.dagger2.beans;

import javax.inject.Inject;

/**
 * 功能: 车轮
 *
 * @author aserbao
 * @date : On 2019/5/29 5:11 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.beans
 */
public class Wheel {
    @Inject
    Rim rim;
    @Inject
    Tire tire;

    @Inject
    public Wheel(Rim rim, Tire tire) {
        this.rim = rim;
        this.tire = tire;
    }
}
