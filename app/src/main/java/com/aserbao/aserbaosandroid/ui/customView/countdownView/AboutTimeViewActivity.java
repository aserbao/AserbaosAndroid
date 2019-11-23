package com.aserbao.aserbaosandroid.ui.customView.countdownView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.date.ADateMgr;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时间相关的UI,倒计时，计时器
 */
public class AboutTimeViewActivity extends AppCompatActivity {
    //2018-03-28 11:09:16
    @BindView(R.id.custom_tainer)
    FrameLayout mCustomTainer;
    @BindView(R.id.custom_count_down_view)
    CountDownView mCountDownView;
    @BindView(R.id.countDownVG)
    ACountDownVG mCountDownVG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    public void btn_count_down_btn(View view) {
        mCountDownView.setTimes("2018-03-29 14:15:30");
    }

    @OnClick({R.id.start_btn, R.id.stop_btn,R.id.testViewBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                mCountDownView.setTimes(ADateMgr.getNowDayOffset(-1));
                mCountDownVG.startCountdownTime();
                break;
            case R.id.stop_btn:
                mCountDownVG.stopCountDounTime();
                break;
            case R.id.testViewBtn:
                if (mCountDownVG.getVisibility() == View.VISIBLE){
                    mCountDownVG.setVisibility(View.INVISIBLE);
                }else{
                    mCountDownVG.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
