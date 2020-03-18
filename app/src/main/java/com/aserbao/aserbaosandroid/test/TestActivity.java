package com.aserbao.aserbaosandroid.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinatorlayout_and_toolbar_and_collapsing_layout);
        ButterKnife.bind(this);

    }

    private static final String TAG = "TestActivity";


    @Override
    public void onDetachedFromWindow() {
        Log.e(TAG, "onDetachedFromWindow: " );
        super.onDetachedFromWindow();
    }
}
