package com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.fullScreen;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.date.AppScreenMgr;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.CustomActivity;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全屏状态下的软键盘显示
 */
public class AutoAdjustSoftHeightActivity extends CustomActivity {
    private static final String TAG = "AutoAdjustSoftHeightAct";
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.viewEdit)
    LinearLayout mViewEdit;
    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.result_tv)
    TextView mResultTv;
    @BindView(R.id.auto_rl)
    RelativeLayout mAutoRl;
    @BindView(R.id.text_view)
    TextView mTextView;


    private int mHeight;
    private int mScreeenWidth;
    private int mScreenHeight;
    private int mBtnShowHeight;
    private int mBtnShowWidth;
    private float mLastX;
    private float mLastY;
    private View mContentChild;
    private FrameLayout mChildContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_adjust_soft_height2);
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

        initDragEvent();
        //拿到当前XML文件的根布局
        mChildContent = (FrameLayout) findViewById(android.R.id.content);

        //监听当前View的状态,进行通知回调,即"软键盘弹出""
        View  childew = mChildContent.getChildAt(0);
        childew.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {

                View decorView = getWindow().getDecorView();
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                decorView.getWindowVisibleDisplayFrame(r);
                int heightDifference = decorView.getRootView().getHeight() - (r.bottom - r.top);
                if(mLastHeight != heightDifference) {
                    if(heightDifference > 300) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTextView.getLayoutParams();
                        boolean isHaveNarBar = AppScreenMgr.isNavigationBarShow(AutoAdjustSoftHeightActivity.this);
                        int navigationBarrH = AppScreenMgr.getNavigationBarrH(AutoAdjustSoftHeightActivity.this);
                        if(isHaveNarBar) {
                            layoutParams.height = heightDifference - navigationBarrH;
                        }else{
                            layoutParams.height = heightDifference;
                        }
                        mTextView.setLayoutParams(layoutParams);
                        mTextView.postInvalidate();
                    }else{
                        mTextView.setVisibility(View.GONE);
                    }
                    mLastHeight = heightDifference;
                    Log.e(TAG, "onSoftKeyboardOpened: " + heightDifference + "mLastHeight" + mLastHeight);
                }
            }
        });
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setVisibility(View.VISIBLE);
                Log.e(TAG, "onClick: " );
            }
        });
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch: " + event.getAction());
                return false;
            }
        });

    }

    private int mLastHeight = 0;


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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "onWindowFocusChanged: " + hasFocus);
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
                int l = layoutParams.leftMargin;
                int height = mAutoRl.getHeight();
                int t = mScreenHeight - mHeight - mBtnShowHeight;
                int b = mAutoRl.getHeight() - t - mBtnShow.getHeight();
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
                View mChildOfContent = mAutoRl.getChildAt(0);
                mChildOfContent.getViewTreeObserver()
                        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            public void onGlobalLayout() {
                                Rect r = new Rect();
                                mAutoRl.getWindowVisibleDisplayFrame(r);
                                int screenHeight = mAutoRl.getRootView().getHeight();
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


    private void initDragEvent() {
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

}
