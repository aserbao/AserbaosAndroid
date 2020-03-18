package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

import com.aserbao.aserbaosandroid.AUtils.AUI.progress.ACustomRecordProgress;
import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;

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
    ACustomRecordProgress mDragIv;
    @BindView(R.id.move_view_container_rl)
    RelativeLayout mMoveViewContainerRl;
    @BindView(R.id.include_rl)
    RelativeLayout mIncludeRl;
    @BindView(R.id.default_record_iv)
    View mDefaultRecordIv;
    @BindView(R.id.bottom_input_ll)
    LinearLayout mBottomInputLl;


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
        mIamgeView.setImageResource(ASourceUtil.getRandomImageId());
    }

    private void initListener() {
        initCheckKeyBoardIsShow(mNewStoryEt);
        mDefaultRecordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mDefaultRecordIv.getX();
                float y = mDefaultRecordIv.getY();
                float includeRlY = mIncludeRl.getY();
                Rect r = new Rect();
                mDefaultRecordIv.getWindowVisibleDisplayFrame(r);
                Log.e(TAG, "onClick: WindowVisibleDisplayFrame : " + r.toString());
                mDefaultRecordIv.getGlobalVisibleRect(r);
                Log.e(TAG, "onClick: getGlobalVisibleRect : " + r.toString());
                mDefaultRecordIv.getLocalVisibleRect(r);
                Log.e(TAG, "onClick: getLocalVisibleRect : " + r.toString());
                int[] location = new int[2];
                mDefaultRecordIv.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
                Log.e(TAG, " \nonClick:   原录制按钮窗口内的位置：x = " + location[0] + " Y = " + location[1]);
                mDefaultRecordIv.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                Log.e(TAG, "onClick:   原录制按钮屏幕中的位置：x = " + location[0] + " Y = " + location[1]);


                int[] location1 = new int[2];
                mDragIv.getLocationOnScreen(location1);
                Log.e(TAG, "onClick:   结果录制按钮屏幕中的位置：x = " + location1[0] + " Y = " + location1[1]);


                int chaX = location[0] - location1[0];
                int chaY = location[1] - location1[1];

                Log.e(TAG, "onClick: chaX = " + chaX + " ChaY = " + chaY);
                PropertyValuesHolder mBottomPaint = PropertyValuesHolder.ofFloat("X", 0.0f, chaX);
                PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("Y", 0.0f, chaY);
                ObjectAnimator.ofPropertyValuesHolder(mDragIv, mBottomPaint, translationY).setDuration(0).start();
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


        initDragEvent();
    }

    private static final String TAG = "FullDragDemoActivity";
    private FrameLayout mChildContent;
    private int mMeasureSoftKBHeight;
    private int mLastHeight = 0;

    private void initCheckKeyBoardIsShow(final EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setCursorVisible(true);
                mPlaceholderTv.setVisibility(View.VISIBLE);
                return false;
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
                    Method method = inputMethodManager.getClass().getDeclaredMethod("getInputMethodWindowVisibleHeight", ((Class<?>) null));
                    method.setAccessible(true);
                    injectSoftHeight = (Integer) method.invoke(inputMethodManager, ((Class<?>) null));
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
                        setRecordBtnMargain(mMeasureSoftKBHeight);
                    } else {
                        setRecordBtnMargain(0);
                        mNewStoryEt.setCursorVisible(false);
                        mPlaceholderTv.setVisibility(View.GONE);
                    }
                    mLastHeight = mMeasureSoftKBHeight;
                }
            }
        });
    }

    private void setRecordBtnMargain(int increaseHeight) {
        RelativeLayout.LayoutParams dragIvLayoutParams = (RelativeLayout.LayoutParams) mDragIv.getLayoutParams();
        dragIvLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM | RelativeLayout.CENTER_HORIZONTAL);
        int oneIncludeBottomMargin = DisplayUtil.dp2px(FullDragDemoActivity.this, 36);
        int bottomMargin = oneIncludeBottomMargin + increaseHeight;

        dragIvLayoutParams.bottomMargin = bottomMargin;
        dragIvLayoutParams.topMargin = bottomMargin - mDragIv.getHeight();
        mDragIv.setLayoutParams(dragIvLayoutParams);
    }


    private void initDragEvent() {
        mDragIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //拖动事件处理
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                    case MotionEvent.ACTION_MOVE:
                        mDragIv.startRecording();
                        closeKeybord(mNewStoryEt,FullDragDemoActivity.this);
                        mBottomInputLl.setVisibility(View.GONE);
                        int currentX = (int) event.getRawX();
                        int currentY = (int) event.getRawY();
                        int halfHeight = mDragIv.getHeight() / 2;
                        int halfWidth = mDragIv.getWidth() / 2;
                        v.layout(currentX - halfWidth, currentY - halfHeight, currentX + halfWidth, currentY + halfHeight);
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        mDragIv.stopRecording();
                        mBottomInputLl.setVisibility(View.VISIBLE);
                        mMoveViewContainerRl.requestLayout();
                        Log.e(TAG, "onTouch: ActionUP");
                        break;
                }
                return true;
            }
        });
    }

    public  void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
