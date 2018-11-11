package com.aserbao.aserbaosandroid.functions.sensors.testSensorData;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.FlowLayout;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestSensorDataActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "TestSensorDataActivity";
    @BindView(R.id.sensor_flow_layout)
    FlowLayout mSensorFlowLayout;
    @BindView(R.id.sensor_type_tv)
    TextView mSensorTypeTv;
    @BindView(R.id.sensor_info_tv)
    TextView mSensorInfoTv;
    @BindView(R.id.sensor_real_time_info_tv)
    TextView mSensorRealTimeInfoTv;

    private int[] sensorType = {
            Sensor.TYPE_ACCELEROMETER,//1 加速度传感器
            Sensor.TYPE_MAGNETIC_FIELD,//2 磁感传感器
            Sensor.TYPE_ORIENTATION,//3 方向传感器
            Sensor.TYPE_GYROSCOPE,//4 陀螺仪传感器
            Sensor.TYPE_LIGHT,//5 光线传感器
            Sensor.TYPE_PRESSURE,//6 压力传感器
            Sensor.TYPE_TEMPERATURE,//7 温度传感器
            Sensor.TYPE_PROXIMITY,//8 距离传感器
            Sensor.TYPE_GRAVITY,//9 重力传感器
            Sensor.TYPE_LINEAR_ACCELERATION//10 线性加速度传感器
    };
    private String[] sensorTypeName = {"加速度传感器", "磁感传感器", "方向传感器", "陀螺仪传感器", "光线传感器", "压力传感器", "温度传感器", "距离传感器", "重力传感器", "线性加速度传感器"};

    private static int RATE_TYPE = SensorManager.SENSOR_DELAY_UI;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sensor_data);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            sb.append(String.valueOf(i)).append("值： ").append(values[i]);
        }
//        double v = Math.pow(values[0], 2) + Math.pow(values[1], 2) + Math.pow(values[2], 2);
        Log.e(TAG, "onSensorChanged:" + sb.toString());
        mSensorRealTimeInfoTv.setText(sb.toString());
    }

    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG, "onAccuracyChanged: 加速度为：===========================  " + accuracy);
    }


    @OnClick({R.id.type_accelerometer, R.id.type_magnetic_field, R.id.type_orientation, R.id.type_gyroscope, R.id.type_light, R.id.type_pressure, R.id.type_temperature, R.id.type_proximity, R.id.type_gravity, R.id.type_linear_acceleration})
    public void onViewClicked(View view) {
        mSensorManager.unregisterListener(this);
        if (mSensorManager == null) {
            return;
        }
        Object tag1 = view.getTag();
        String nm = String.valueOf(tag1);
        int tag = Integer.parseInt(nm);
        mSensor = mSensorManager.getDefaultSensor(sensorType[tag]);
        mSensorManager.registerListener(this, mSensor, RATE_TYPE);
        if (view instanceof Button) {
            String sensorType = sensorTypeName[tag];
            ((Button) view).setText(sensorType);
            mSensorTypeTv.setText(sensorType);
        }
        mSensorInfoTv.setText(getSensorInfo(mSensor));
    }

    public String getSensorInfo(Sensor sensor){
        if (sensor != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("	Sensor Type - " + sensor.getType() + "\r\n");
            sb.append("	Sensor Name - " + sensor.getName() + "\r\n");
            sb.append("	Sensor Version - " + sensor.getVersion() + "\r\n");
            sb.append("	Sensor Vendor - " + sensor.getVendor() + "\r\n");
            sb.append("	Maximum Range - " + sensor.getMaximumRange() + "\r\n");
            sb.append("	Minimum Delay - " + sensor.getMinDelay() + "\r\n");
            sb.append("	Power - " + sensor.getPower() + "\r\n");
            sb.append("	Resolution - " + sensor.getResolution() + "\r\n");
            return sb.toString();
        }else{
            return "当前手机没有这个感应器";
        }

    }
}
