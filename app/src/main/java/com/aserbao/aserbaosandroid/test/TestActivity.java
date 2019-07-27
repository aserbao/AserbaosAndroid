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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        mTestBtn2.setVisibility(View.INVISIBLE);
    }

    private static final String TAG = "TestActivity";

    @OnClick({R.id.test_btn, R.id.test_btn2})
    public void onViewClicked() {
        Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
    }

    public void addView() {
    }
    public int count = 10 * 10000;
    public int num = 0;
    @OnClick({R.id.test_btn, R.id.test_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_btn:
                mStartTime = System.currentTimeMillis();
                num = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < count; i++) {
                            num++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (count - 1 == num){
                                        Log.e(TAG, "运行"+count + "条数据 一共耗时：" + (System.currentTimeMillis() - mStartTime)/1000 + "s" );
                                    }
                                    Log.e(TAG, "run: " + String.valueOf(num));
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.test_btn2:
                addView();
                break;
        }
    }
}
