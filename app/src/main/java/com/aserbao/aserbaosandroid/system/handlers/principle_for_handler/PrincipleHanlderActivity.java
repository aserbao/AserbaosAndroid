package com.aserbao.aserbaosandroid.system.handlers.principle_for_handler;

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

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrincipleHanlderActivity extends AppCompatActivity {
    private static final String TAG = "PrincipleHanlderActivit";
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int DELAY_MILLIS = 10;
    public static final int DELAY_MILLIS_TWO = 51;
    public static final int DELAY_MILLIS_THREE = 101;
    @BindView(R.id.handler_fl)
    FrameLayout mHandlerFl;
    //       @BindView(R.id.animation_view)
//       LottieAnimationView mAnimationView;
    private MyHandler myHandler = new MyHandler(new WeakReference<PrincipleHanlderActivity>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principle_hanlder);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.handler_first_btn, R.id.handler_second_btn, R.id.handler_three_btn})
    public void onViewClicked(View view) {
        Message message = new Message();
        message.obj = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.handler_first_btn:
                message.what = NUM_ONE;
                myHandler.sendMessageDelayed(message, DELAY_MILLIS);
                break;
            case R.id.handler_second_btn:
                message.what = NUM_TWO;
                myHandler.sendMessageDelayed(message, DELAY_MILLIS_TWO);
                break;
            case R.id.handler_three_btn:
//                message.what = NUM_THREE;
//                myHandler.sendMessageDelayed(message, DELAY_MILLIS_THREE);
//                LottieComposition composition = LottieComposition.Factory.fromFileSync(this, "lottiefiles/data.json");
                LottieAnimationView mAnimationView = new LottieAnimationView(this);
                LottieComposition composition = LottieComposition.Factory.fromFileSync(this, "lottiefiles/data.json");
                mAnimationView.setComposition(composition);
                mAnimationView.loop(true);
                mAnimationView.playAnimation();
                mHandlerFl.addView(mAnimationView);
                break;
        }

    }

    public void sleep(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
