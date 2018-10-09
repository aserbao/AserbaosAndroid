package com.aserbao.aserbaosandroid.ui.constantUtilsShow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.date.AppScreenMgr;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstantsUtilsShowActivity extends AppCompatActivity {

    @BindView(R.id.btn_screen_show)
    Button mBtnScreenShow;
    @BindView(R.id.btn_data_format)
    Button mBtnDataFormat;
    @BindView(R.id.show_result)
    TextView mShowResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constants_utils_show);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_screen_show, R.id.btn_data_format})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_screen_show:
                mShowResult.setText(AppScreenMgr.getScreenInfo(this));
                break;
            case R.id.btn_data_format:
                break;
        }
    }
}
