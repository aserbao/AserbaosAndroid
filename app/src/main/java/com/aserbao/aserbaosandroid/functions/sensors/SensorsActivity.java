package com.aserbao.aserbaosandroid.functions.sensors;

import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.functions.sensors.testSensorData.TestSensorDataActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

public class SensorsActivity extends BaseActivity {



    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("测试传感器数据",TestSensorDataActivity.class));
        mClassBeen.add(new ClassBean("摇一摇",SharkActivity.class));
    }
}
