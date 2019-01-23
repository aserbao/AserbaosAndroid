package com.aserbao.aserbaosandroid.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.canvas.matrix.MatrixView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: 1142803753@qq.com
 */
public class TestActivity extends AppCompatActivity {
    private List l = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
    }

    private static final String TAG = "TestActivity";
    String s = " 哈哈哈 hhhhh aserbao 这是个问题";


    @OnClick(R.id.test_btn)
    public void onViewClicked() {
        char[] split = s.toCharArray();

//        String[] split = s.split("[^x00-xff]");
//        String[] split = s.split("[u4e00-u9fa5]");
        for (int i = 0; i < split.length; i++) {
            Log.e(TAG, "test_btn: " + split[i]);
        }
    }
}
