package com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullDragDemoActivity extends AppCompatActivity {

    @BindView(R.id.new_story_et)
    EditText mNewStoryEt;
    @BindView(R.id.photo_selection_iv)
    ImageView mPhotoSelectionIv;
    @BindView(R.id.switch_camera_v)
    ImageView mSwitchCameraV;
    @BindView(R.id.switch_flash_v)
    ImageView mSwitchFlashV;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.placeholder_tv)
    TextView mPlaceholderTv;
    @BindView(R.id.iamge_view)
    ImageView mIamgeView;
    @BindView(R.id.drag_iv)
    ImageView mDragIv;
    @BindView(R.id.move_view_container_rl)
    RelativeLayout mMoveViewContainerRl;
    @BindView(R.id.default_record_iv)
    ImageView mDefaultRecordIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_drag_demo);
        ButterKnife.bind(this);
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIamgeView.setImageResource(ImageSource.getRandomImageId());
    }

    private void initListener() {
        initCheckKeyBoardIsShow(mNewStoryEt);
        mDefaultRecordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mDefaultRecordIv.getX();
                float y = mDefaultRecordIv.getY();
                Rect r = new Rect();
                mDefaultRecordIv.getWindowVisibleDisplayFrame(r);
                Log.e(TAG, "onClick: getX =  " +  x + "  getY = " +  y + " r :" +  r.toString());
            }
        });
        mDefaultRecordIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDefaultRecordIv.setVisibility(View.GONE);
                mDragIv.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mDefaultRecordIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        initDragEvent();
    }

    private static final String TAG = "FullDragDemoActivity";
    private FrameLayout mChildContent;
    private int mMeasureSoftKBHeight;
    private int mLastHeight = 0;

    private void initCheckKeyBoardIsShow(EditText editText) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceholderTv.setVisibility(View.VISIBLE);
            }
        });
        //拿到当前XML文件的根布局
        mChildContent = (FrameLayout) findViewById(android.R.id.content);

        //监听当前View的状态,进行通知回调,即"软键盘弹出""
        View childew = mChildContent.getChildAt(0);
        childew.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                int injectSoftHeight = 0;
                try {
                    Method method = inputMethodManager.getClass().getDeclaredMethod("getInputMethodWindowVisibleHeight", null);
                    method.setAccessible(true);
                    injectSoftHeight = (Integer) method.invoke(inputMethodManager, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                View decorView = getWindow().getDecorView();
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                decorView.getWindowVisibleDisplayFrame(r);
                int rootHeight = decorView.getRootView().getHeight();
                int rH = r.bottom - r.top;
                int measureDVHeight = rootHeight - rH;

                if (injectSoftHeight > 200) {
                    mMeasureSoftKBHeight = injectSoftHeight < measureDVHeight ? injectSoftHeight : measureDVHeight;
                } else if (injectSoftHeight <= 200) {
                    mMeasureSoftKBHeight = measureDVHeight;
                }
                Log.e(TAG, "onGlobalLayout: heightDifference" + measureDVHeight + "mMeasureSoftKBHeight =  " + mMeasureSoftKBHeight);

                if (mLastHeight != mMeasureSoftKBHeight) {
                    if (mMeasureSoftKBHeight > 200) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mPlaceholderTv.getLayoutParams();
                        int result = 0;
                        result = mMeasureSoftKBHeight;
                        layoutParams.height = result;
                        mPlaceholderTv.setLayoutParams(layoutParams);
                        mPlaceholderTv.postInvalidate();
                    } else {
                        mPlaceholderTv.setVisibility(View.GONE);
                    }
                    mLastHeight = mMeasureSoftKBHeight;
                }
            }
        });
    }

    private float mLastX;
    private float mLastY;

    private void initDragEvent() {
        mDragIv.setOnTouchListener(new View.OnTouchListener() {
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
                        int b = mMoveViewContainerRl.getHeight() - t - v.getHeight();
                        int r = mMoveViewContainerRl.getWidth() - l - v.getWidth();
                        if (l < 0) {//处理按钮被移动到上下左右四个边缘时的情况，决定着按钮不会被移动到屏幕外边去
                            l = 0;
                            r = mMoveViewContainerRl.getWidth() - v.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = mMoveViewContainerRl.getHeight() - v.getHeight();
                        }

                        if (r < 0) {
                            r = 0;
                            l = mMoveViewContainerRl.getWidth() - v.getWidth();
                        }
                        if (b < 0) {
                            b = 0;
                            t = mMoveViewContainerRl.getHeight() - v.getHeight();
                        }
                        layoutParams.leftMargin = l;
                        layoutParams.topMargin = t;
                        layoutParams.bottomMargin = b;
                        layoutParams.rightMargin = r;

                        v.setLayoutParams(layoutParams);
                        mLastX = (int) currentX;
                        mLastY = (int) currentY;
                        v.postInvalidate();
                        Log.e(TAG, "onTonTouchouch: " + currentX + "  cuurentY =  " + currentY );
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
