package com.aserbao.aserbaosandroid.functions.sensors;

import android.app.Activity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.sensors.testSensorData.TestSensorDataActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;

public class SensorsActivity extends BaseActivity {



    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("测试传感器数据",TestSensorDataActivity.class));
        mClassBeen.add(new ClassBean("摇一摇",SharkActivity.class));
    }
}
