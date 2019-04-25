package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/12
 * email: this is empty email
 */
public class ViewManager implements IShotListener{
    private static final String TAG = "ViewManager";
    public ArrayList<Shot> mShotList = new ArrayList<>();
    private MoveView mMoveView;
    private Context mContext;

    public boolean isStart = false;//是否已开启handler处理？

    private Activity mActivity;
    public static ViewManager viewManager ;
    public static ViewManager getInstance(Activity activity, MoveView mMoveView, Context context){
        if (viewManager == null) {
            viewManager = new ViewManager(activity,mMoveView,context,null);
        }
        return viewManager;
    }
    public IShotListener mIShotListener;
    public ViewManager(Activity activity,MoveView mMoveView, Context mContext,IShotListener iShotListener) {
        mActivity = activity;
        mIShotListener = iShotListener;
        this.mMoveView = mMoveView;
        this.mContext = mContext;
    }

    public void addShot(Bitmap bitmap, float initX, float initY, float angle, int targetX, int targetY, int targetRadius){
        Shot shot = new Shot(mContext, bitmap, initX, initY, angle, targetX, targetY, targetRadius,this,null);
        mShotList.add(shot);
        if (!isStart) {
            myHandler.sendEmptyMessage(0);
            isStart = true;
        }
    }
    public void addShot(Bitmap bitmap, float initX, float initY, float angle, int targetX, int targetY, int targetRadius,List<TargetView> mTargetData){
        Shot shot = new Shot(mContext, bitmap, initX, initY, angle, targetX, targetY, targetRadius,this,mTargetData);
        mShotList.add(shot);
        if (!isStart) {
            myHandler.sendEmptyMessage(0);
            isStart = true;
        }
    }

    @Override
    public void isHit(Shot shot,TargetView targetView) {
        removeShot(shot);
        if (mIShotListener != null) {
            mIShotListener.isHit(shot,targetView);
        }
    }

    @Override
    public void isLoseEfficacy(Shot shot) {
        removeShot(shot);
        if (mIShotListener != null) {
            mIShotListener.isLoseEfficacy(shot);
        }
    }

    private void removeShot(Shot shot) {
        if (mShotList != null) {
            mShotList.remove(shot);
            Log.e(TAG, "removeShot: 移除炮弹 ,还有炮弹" + mShotList.size() + " 个" );
            if (mShotList.size() == 0){
                isStart = false;
            }
            mMoveView.setmShotList(mShotList);
            mMoveView.postInvalidate();
        }
    }

    public void update(){
        mMoveView.setmShotList(mShotList);
        mMoveView.postInvalidate();
        myHandler.sendEmptyMessageDelayed(0,10);
    }

    public MyHandler myHandler = new MyHandler(new WeakReference<ViewManager>(this));
    public class MyHandler extends Handler {
        public WeakReference<ViewManager> mWeakReference;

        public MyHandler(WeakReference<ViewManager> mWeakReference) {
            this.mWeakReference = mWeakReference;
        }
        @Override
        public void handleMessage(Message msg) {
            ViewManager viewManager = mWeakReference.get();
            if (viewManager != null) {
                switch (msg.what){
                    case 0:
                        if (isStart){
                            viewManager.update();
                            Log.e(TAG, "handleMessage: " + "刷新界面" );
                        }else{
                            removeCallbacksAndMessages(0);
                        }
                        break;
                }
            }
        }
    }
}
