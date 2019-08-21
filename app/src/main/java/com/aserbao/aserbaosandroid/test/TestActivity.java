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
        String result = "thisisan你到底要干啥";
        int length = result.toCharArray().length;
        Toast.makeText(this,String.valueOf(length), Toast.LENGTH_SHORT).show();
    }


}
