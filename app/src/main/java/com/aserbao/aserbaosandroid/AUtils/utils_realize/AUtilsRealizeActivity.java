package com.aserbao.aserbaosandroid.AUtils.utils_realize;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.date.AppSysMgr;
import com.aserbao.aserbaosandroid.AUtils.utils.network.ANetworkUtils;
import com.aserbao.aserbaosandroid.AUtils.utils.phone.APhoneMediaUtils;
import com.aserbao.aserbaosandroid.AUtils.utils.phone.APhoneUtils;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AUtilsRealizeActivity extends AppCompatActivity {

    @BindView(R.id.utils_result_tv)
    TextView mUtilsResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autils_realize);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_get_phone_info, R.id.btn_get_phone_info2,R.id.btn_get_phone_media_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_phone_info:
                StringBuffer stringBuffer = new StringBuffer();
                String phoneMeesager = APhoneUtils.getInstance().getPhoneMeesager(this);
                String appSysMgrInfo = AppSysMgr.getAppSysMgrInfo(this);
                stringBuffer.append(phoneMeesager).append(appSysMgrInfo);

                BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
                String deviceName = myDevice.getName();

                mUtilsResultTv.setText(deviceName + stringBuffer.toString());
                break;
            case R.id.btn_get_phone_info2:
                int networkState = ANetworkUtils.getNetworkState(this);
                String valueOf = String.valueOf(networkState);
                mUtilsResultTv.setText(valueOf);
                break;
            case R.id.btn_get_phone_media_info:
                String phoneMediaInfo = APhoneMediaUtils.getPhoneMediaInfo(this);
                mUtilsResultTv.setText(phoneMediaInfo);
                break;
        }
    }
}
