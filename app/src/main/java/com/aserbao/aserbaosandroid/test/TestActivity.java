package com.aserbao.aserbaosandroid.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.bezier.likeAnimation.BezierCustomLike;
import com.aserbao.aserbaosandroid.ui.customView.customImageView.PointImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
public class TestActivity extends AppCompatActivity {
    @BindView(R.id.test_btn)
    Button mTestBtn;
    @BindView(R.id.test_btn2)
    Button mTestBtn2;
    private List l = new ArrayList();
    private long mStartTime;

    @BindView(R.id.test_point_iv)
    PointImageView mTestPointIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        mTestPointIv.setPointMode(PointImageView.NUMBER_POINT);
        mTestPointIv.setMessageNum(10);
        mTestPointIv.setHaveMesage(true);
    }

    private static final String TAG = "TestActivity";

    @OnClick({R.id.test_btn, R.id.test_btn2})
    public void onViewClicked() {
        mTestPointIv.setPointMode(PointImageView.NUMBER_POINT);
        mTestPointIv.setMessageNum(100);
        mTestPointIv.setHaveMesage(true);
    }


}
