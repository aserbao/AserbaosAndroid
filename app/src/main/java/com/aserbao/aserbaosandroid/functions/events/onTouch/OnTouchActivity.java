package com.aserbao.aserbaosandroid.functions.events.onTouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnTouchActivity extends AppCompatActivity implements View.OnTouchListener{

    @BindView(R.id.touch_iv)
    ImageView mTouchIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);
        ButterKnife.bind(this);
        mTouchIv.setOnTouchListener(this);
    }

    private static final String TAG = "OnTouchActivity";
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        Log.e(TAG, "onTouch: " + event.getAction() );
        return true;
    }
}
