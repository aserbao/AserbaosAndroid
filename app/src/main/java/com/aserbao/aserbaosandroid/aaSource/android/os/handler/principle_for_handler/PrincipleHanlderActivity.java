package com.aserbao.aserbaosandroid.aaSource.android.os.handler.principle_for_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrincipleHanlderActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "PrincipleHanlderActivit";
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int DELAY_MILLIS = 10;
    public static final int DELAY_MILLIS_TWO = 51;
    public static final int DELAY_MILLIS_THREE = 101;


    private MyHandler myHandler = new MyHandler(new WeakReference<PrincipleHanlderActivity>(this));


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("清除所有",10));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("0",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("1",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("2",2));
    }


    public void sleep(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        Message message = new Message();
        switch (position){
            case 10:
//                myHandler.removeCallbacks(null);
                myHandler.removeMessages(NUM_ONE);
                break;
            case 0:
                message.obj = System.currentTimeMillis();
                message.what = NUM_ONE;
                myHandler.sendMessageDelayed(message, DELAY_MILLIS);
                break;
            case 1:
                message.obj = System.currentTimeMillis();
                message.what = NUM_TWO;
                myHandler.sendMessageDelayed(message, DELAY_MILLIS_TWO);
                break;
            case 2:
                message.what = NUM_THREE;
                myHandler.sendMessageDelayed(message, DELAY_MILLIS_THREE);
                break;
        }
    }

    public static class MyHandler extends Handler {
        WeakReference<PrincipleHanlderActivity> principleHanlderActivityWeakReference;

        public MyHandler(WeakReference<PrincipleHanlderActivity> principleHanlderActivityWeakReference) {
            this.principleHanlderActivityWeakReference = principleHanlderActivityWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            PrincipleHanlderActivity principleHanlderActivity = principleHanlderActivityWeakReference.get();
            if (principleHanlderActivity != null) {
                long diffTime = System.currentTimeMillis() - (long) msg.obj;
                int what = msg.what;
                Log.e(TAG, "handleMessage: What = " + what + "     \t 时间差为 ： " + diffTime);
                Message message = new Message();
                message.what = what;
                message.obj = System.currentTimeMillis();
                switch (msg.what) {
                    case NUM_ONE:
                        sendMessageDelayed(message, DELAY_MILLIS);
                        principleHanlderActivity.sleep();
                        break;
                    case NUM_TWO:
                        sendMessageDelayed(message, DELAY_MILLIS_TWO);
                        break;
                    case NUM_THREE:
                        sendMessageDelayed(message, DELAY_MILLIS_THREE);
                        break;
                }
            }
        }
    }

}
