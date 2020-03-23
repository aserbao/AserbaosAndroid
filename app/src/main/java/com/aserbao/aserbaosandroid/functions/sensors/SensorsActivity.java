package com.aserbao.aserbaosandroid.functions.sensors;

import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.sensors.testSensorData.TestSensorDataActivity;
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.beans.BaseRecyclerBean;

public class SensorsActivity extends BaseRecyclerViewActivity {



    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试传感器数据",TestSensorDataActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("摇一摇",SharkActivity.class));
    }
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}
}
