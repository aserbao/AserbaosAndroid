package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/11
 * email: this is empty email
 */
public class MoveViews extends View {

    public List<Shot> mShotList = new ArrayList<>();
    private Context mContext;
    private int aX;
    private int aY;
    private int anInt;

    public MoveViews(Context context) {
        this(context,null);
    }

    public MoveViews(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoveViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }
    private Paint mPaint;
    private float x,y;
    public float mMaxWidth,mMaxHeight;
    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(false);
        mMaxWidth = DisplayUtil.getScreenWidth(mContext);
        mMaxHeight = DisplayUtil.getScreenHeight(mContext);
        x = mMaxWidth/2;
        y = mMaxHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(x,y,20,mPaint);
    }

    public void start(ImageView view){
        aX = (view.getRight() + view.getLeft()) / 2;
        aY = (view.getBottom() + view.getTop()) / 2;
        anInt = view.getHeight() / 2;
        myHandler.sendEmptyMessage(0);
    }
    public float distance = 5;
    public float mAngle = 83;

    private static final String TAG = "MoveView";
    public void update(){
        double addx = distance * Math.cos(2*PI/360*mAngle);
        x =  x + (float) addx;
        double addY = distance * Math.sin(2*PI/360*mAngle);
        y =  y - (float) addY;
        Log.e(TAG, "update: " + x + " y = " + y + " addx = " + addx + " addy = " + addY);
        if (y > mMaxHeight || y < 0 || x < 0 || x > mMaxWidth){
            stop();
            x = mMaxWidth /2 ;
            y = mMaxHeight;
            Log.e(TAG, "update: 出去了 "  );
        }else{
            myHandler.sendEmptyMessageDelayed(0,10);
        }
        float v = (float) Math.sqrt(Math.pow(x - aX, 2) + Math.pow(y - aY, 2));
        Log.e(TAG, "update: V = " + v );
        if (v < 20 + anInt){
            Toast.makeText(mContext, "到了", Toast.LENGTH_SHORT).show();
        }
        invalidate();
    }

    public void stop(){
        myHandler.removeMessages(0);
        myHandler.removeCallbacksAndMessages(null);
    }

    public MyHandler myHandler = new MyHandler(new WeakReference<MoveViews>(this));
    public class MyHandler extends Handler {
        public WeakReference<MoveViews> mWeakReference;

        public MyHandler(WeakReference<MoveViews> mWeakReference) {
            this.mWeakReference = mWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            MoveViews moveView = mWeakReference.get();
            if (moveView != null) {
                switch (msg.what){
                    case 0:
                        moveView.update();
                        break;
                }
            }
        }
    }

    /**
     * @param bitmap 图片
     * @param initX  初始值X
     * @param initY  初始值Y
     * @param angle  起始角度
     */
   /* public void addShot(Bitmap bitmap, float initX, float initY, float angle){
        mShotList.add(new Shot(mContext,bitmap,initX,initY,angle));
    }*/
}
