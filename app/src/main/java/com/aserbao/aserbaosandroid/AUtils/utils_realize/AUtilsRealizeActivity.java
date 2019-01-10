package com.aserbao.aserbaosandroid.AUtils.utils_realize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

    @OnClick(R.id.btn_get_phone_info)
    public void onViewClicked() {
        mUtilsResultTv.setText(APhoneUtils.getInstance().getPhoneStatus(this));
    }
}
