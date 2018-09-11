package com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.date.AppScreenMgr;
import com.aserbao.aserbaosandroid.R;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoAdjustSoftHeightActivity extends AppCompatActivity {
    private static final String TAG = "AutoAdjustSoftHeightAct";
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.viewEdit)
    RelativeLayout mViewEdit;
    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.result_tv)
    TextView mResultTv;
    @BindView(R.id.auto_rl)
    RelativeLayout mAutoRl;


    private int mHeight;
    private int mScreeenWidth;
    private int mScreenHeight;
    private int mBtnShowHeight;
    private int mBtnShowWidth;
    private float mLastX;
    private float mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_adjust_soft_height1);
        ButterKnife.bind(this);
        mBtnShow.post(new Runnable() {
            @Override
            public void run() {
                initGetData();
            }
        });
        initListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        /*mBtnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastX = event.getRawX();
                        mLastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x = ((int) event.getRawX());
                        int y = ((int) event.getRawY());
                        float chaXm = x - mLastX;
                        float chaYm = y - mLastY;
                        int halfWidth = (int) (mBtnShowWidth / 2);
                        int halfHeight = mBtnShowHeight / 2;
                        mBtnShow.layout(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
                        Log.e(TAG, "onTouch: chaX = " + chaXm + " ChaY = " + chaYm);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        float chaXu = event.getX() - mLastX;
                        float chaYu = event.getY() - mLastY;
                        Log.e(TAG, "onTouch: chaX = " + chaXu + " ChaY = " + chaYu);
                        break;
                }
                return false;
            }
        });*/

        mBtnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //拖动事件处理

                boolean mIsClick = false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //v.setBackgroundResource(R.mipmap.btn_style_one_press);
                        mIsClick = false;//当按下的时候设置isclick为false，具体原因看后边的讲解
                        mLastX = (int) event.getRawX();
                        mLastY = (int) event.getRawY();//按钮初始的横纵坐标

                        break;
                    case MotionEvent.ACTION_MOVE:
                        mIsClick = true;//当按钮被移动的时候设置isclick为true
                        float currentX = event.getRawX();
                        float currentY = event.getRawY();
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        int dx = (int) (currentX - mLastX);
                        int dy = (int) (currentY - mLastY);//按钮被移动的距离
                        int l = layoutParams.leftMargin + dx;
                        int t = layoutParams.topMargin + dy;
                        int b = mAutoRl.getHeight() - t - v.getHeight();
                        int r = mAutoRl.getWidth() - l - v.getWidth();
                        if (l < 0) {//处理按钮被移动到上下左右四个边缘时的情况，决定着按钮不会被移动到屏幕外边去
                            l = 0;
                            r = mAutoRl.getWidth() - v.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = mAutoRl.getHeight() - v.getHeight();
                        }

                        if (r < 0) {
                            r = 0;
                            l = mAutoRl.getWidth() - v.getWidth();
                        }
                        if (b < 0) {
                            b = 0;
                            t = mAutoRl.getHeight() - v.getHeight();
                        }
                        layoutParams.leftMargin = l;
                        layoutParams.topMargin = t;
                        layoutParams.bottomMargin = b;
                        layoutParams.rightMargin = r;

                        v.setLayoutParams(layoutParams);
                        mLastX = (int) currentX;
                        mLastY = (int) currentY;
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                return mIsClick;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initGetData() {
        View decorView = getWindow().getDecorView();
        mScreeenWidth = decorView.getWidth();
        mScreenHeight = AppScreenMgr.getScreenHeight(this);
        mBtnShowHeight = mBtnShow.getHeight();
        mBtnShowWidth = mBtnShow.getWidth();
    }

    @OnClick({R.id.btn_do_something, R.id.btn_do_second, R.id.btn_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_do_something:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                try {
                    Method method = inputMethodManager.getClass().getDeclaredMethod("getInputMethodWindowVisibleHeight", null);
                    method.setAccessible(true);
                    mHeight = (Integer) method.invoke(inputMethodManager, null);
                    Log.e(TAG, "initView: " + mHeight);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int top = mScreenHeight - mHeight - mBtnShowHeight;
                int bottom = mScreenHeight - mHeight;
//                mBtnShow.layout(mScreeenWidth - mBtnShow.getWidth(), top, mScreeenWidth, bottom);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mBtnShow.getLayoutParams();
                int l = layoutParams.leftMargin ;
                int height = mAutoRl.getHeight();
                int t = mScreenHeight - mHeight - mBtnShowHeight;
                int b = mAutoRl.getHeight() - t - mBtnShow.getHeight();
//                int b = 0;
                int r = mAutoRl.getWidth() - l - mBtnShow.getWidth();

                layoutParams.leftMargin = l;
                layoutParams.topMargin = t;
                layoutParams.bottomMargin = b;
                layoutParams.rightMargin = r;

                mBtnShow.setLayoutParams(layoutParams);
                mBtnShow.postInvalidate();

                Log.e(TAG, "initView: top:  " + top + " bottom = " + bottom + " mBtnShowHeight : " + mBtnShowHeight);
                break;
            case R.id.btn_do_second:
                View mChildOfContent = mViewEdit.getChildAt(0);
                mChildOfContent.getViewTreeObserver()
                        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            public void onGlobalLayout() {
                                Rect r = new Rect();
                                mViewEdit.getWindowVisibleDisplayFrame(r);
                                int screenHeight = mViewEdit.getRootView().getHeight();
                                int heightDifference = screenHeight - (r.bottom);

                                mBtnShow.layout(mScreeenWidth - mBtnShow.getWidth(), heightDifference + mBtnShowHeight, mScreeenWidth, heightDifference);
                                Log.e(TAG, "onGlobalLayout: " + heightDifference);
                            }
                        });
                break;
            case R.id.btn_show:
                mResultTv.setText(AppScreenMgr.getScreenInfo(this));
                break;
        }
    }


}
