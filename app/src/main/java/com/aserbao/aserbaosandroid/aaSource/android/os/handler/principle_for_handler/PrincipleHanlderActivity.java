package com.aserbao.aserbaosandroid.aaSource.android.os.handler.principle_for_handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.lang.ref.WeakReference;

public class PrincipleHanlderActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "PrincipleHanlderActivit";
    public static final int WHAT_NUM_ONE = 1;
    public static final int WHAT_NUM_TWO = 2;
    public static final int WHAT_NUM_THREE = 3;
    public static final int WHAT_HANDLE_SLEEP = 4;

    public static final int ONE_DELAY_MILLIS = 10;
    public static final int TWO_DELAY_MILLIS = 51;
    public static final int THREE_DELAY_MILLIS = 101;
    public static final int FOUR_DELAY_MILLIS = 100;


    private MyHandler myHandler = new MyHandler(new WeakReference<PrincipleHanlderActivity>(this));


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试", 1024));
        mBaseRecyclerBean.add(new BaseRecyclerBean("清除所有",10));
        mBaseRecyclerBean.add(new BaseRecyclerBean("延迟"+ONE_DELAY_MILLIS+"ms",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("延迟"+TWO_DELAY_MILLIS+"ms",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("延迟"+THREE_DELAY_MILLIS+"ms",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("SLEEP", WHAT_HANDLE_SLEEP));
    }


    public void sleep(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        Message message = new Message();
        Log.d(TAG, "itemClickBack() called with: view = [" + view + "], position = [" + position + "], isLongClick = [" + isLongClick + "], comeFrom = [" + comeFrom + "]");
        switch (position){
            case 1024:
                if(isLongClick){
                    myHandler.removeMessages(1000);
                }else{
                    myHandler.sendEmptyMessageDelayed(1000,3 * 1000);
                }
                break;
            case 10:
//                myHandler.removeMessages(WHAT_NUM_ONE);
//                myHandler.removeCallbacks(null);
                myHandler.removeCallbacksAndMessages(null);
                break;
            case 0:
                if(isLongClick){
                    myHandler.removeMessages(ONE_DELAY_MILLIS);
                }else {
                    message.obj = System.currentTimeMillis();
                    message.what = WHAT_NUM_ONE;
                    myHandler.sendMessageDelayed(message, ONE_DELAY_MILLIS);
                }
                break;
            case 1:
                message.obj = System.currentTimeMillis();
                message.what = WHAT_NUM_TWO;
                myHandler.sendMessageDelayed(message, TWO_DELAY_MILLIS);
                break;
            case 2:
                message.obj = System.currentTimeMillis();
                message.what = WHAT_NUM_THREE;
                myHandler.sendMessageDelayed(message, THREE_DELAY_MILLIS);
                break;
            case WHAT_HANDLE_SLEEP:
                message.obj = System.currentTimeMillis();
                message.what = WHAT_HANDLE_SLEEP;
                myHandler.sendMessageDelayed(message, THREE_DELAY_MILLIS);
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
                long diffTime = 0;
                if(msg.obj != null) {
                    diffTime = System.currentTimeMillis() - (long) msg.obj;
                }
                int what = msg.what;
                Message message = new Message();
                message.what = what;
                message.obj = System.currentTimeMillis();
                switch (msg.what) {
                    case WHAT_NUM_ONE:
                        Log.e(TAG, "handleMessage: What = " + what + "     \t 时间差为 ： " + diffTime + " cuurThread.name="+ Thread.currentThread().getName());
                        sendMessageDelayed(message, ONE_DELAY_MILLIS);
                        principleHanlderActivity.sleep();
                        break;
                    case WHAT_NUM_TWO:
                        Log.e(TAG, "handleMessage: What = " + what + "     \t 时间差为 ： " + diffTime + " cuurThread.name="+ Thread.currentThread().getName());
                        sendMessageDelayed(message, TWO_DELAY_MILLIS);
                        break;
                    case WHAT_NUM_THREE:
                        Log.e(TAG, "handleMessage: What = " + what + "     \t 时间差为 ： " + diffTime + " cuurThread.name="+ Thread.currentThread().getName());
                        sendMessageDelayed(message, THREE_DELAY_MILLIS);
                        break;
                    case WHAT_HANDLE_SLEEP:
                        try {
                            Thread.sleep(100);
                            sendMessageDelayed(message, FOUR_DELAY_MILLIS);
                            Log.e(TAG, "handleMessage: What = " + what + "     \t 时间差为 ： " + diffTime + " cuurThread.name="+ Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.e(TAG, "handleMessage: error message=" + e.toString() );
                        }
                        break;
                    default:
                        Log.d(TAG, "handleMessage() called with: msg = [" + msg + "]");
                        break;
                }
            }
        }
    }

}
