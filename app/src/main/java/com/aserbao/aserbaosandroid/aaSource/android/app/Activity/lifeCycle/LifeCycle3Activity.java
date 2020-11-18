package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.lifeCycle;

import android.content.Intent;
import android.os.PersistableBundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.utils.log.ALogUtils;
import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.data.ASourceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: aserbao
 * @date:2020/11/18 9:15 PM
 * @package:
 * @describle: 透明主题的Activity
 */
public class LifeCycle3Activity extends AppCompatActivity {
    private static final String TAG = "LifeCycle3Activity";
    @BindView(R.id.life_cycle_iv)
    ImageView mLifeCycleIv;
    @BindView(R.id.life_cycle_tv)
    TextView mLifeCycleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        ButterKnife.bind(this);
        mLifeCycleIv.setImageResource(ASourceUtil.getRandomImageId());
        mLifeCycleTv.setText(TAG);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "], outPersistentState = [" + outPersistentState + "]");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        ALogUtils.logErrorTime(ALogUtils.INT_NUM_END_TIME);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent() called with: intent = [" + intent + "]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish() called");
    }
    @OnClick({R.id.jump_life_cycle_activity, R.id.jump_life_cycle2_activity, R.id.jump_life_cycle3_activity})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.jump_life_cycle_activity:
                intent = new Intent(this,LifeCycleActivity.class);
                break;
            case R.id.jump_life_cycle2_activity:
                intent = new Intent(this,LifeCycle2Activity.class);
                break;
            case R.id.jump_life_cycle3_activity:
                intent = new Intent(this,LifeCycle3Activity.class);
                break;
        }
        startActivity(intent);
    }
}
