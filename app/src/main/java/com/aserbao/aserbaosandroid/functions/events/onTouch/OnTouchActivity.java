package com.aserbao.aserbaosandroid.functions.events.onTouch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
/**
* @Created time:2021/7/26 6:15 PM
* @author: aserbao
* @description:事件分发分析
**/
public class OnTouchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);
        ButterKnife.bind(this);
    }

    private static final String TAG = "OnTouchActivity";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\t被调用了");
        boolean b = super.dispatchTouchEvent(ev);
        Log.e(TAG, "\tdispatchTouchEvent   : \t" + ev.getAction() + "\treturn = "+b );
        return b;
//        return true;
//        return false;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\t被调用了");
        boolean b = super.onTouchEvent(event);
        Log.e(TAG, "\tonTouchEvent         : \t" + event.getAction() +"\treturn = "+b );
        return b;
    }

    @OnClick({ R.id.touch_cbtn,R.id.touch_custom_view_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touch_custom_view_group:
                Toast.makeText(this, "viewGroup被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.touch_cbtn:
                Toast.makeText(this, "按钮被点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
