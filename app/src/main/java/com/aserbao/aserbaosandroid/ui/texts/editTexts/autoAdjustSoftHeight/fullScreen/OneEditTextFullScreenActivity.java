package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.data.ASourceUtil;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.utils.AndroidSoftBoardAdjustHeightUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全屏模式下的Activity的软键盘的显示技巧
 */
public class OneEditTextFullScreenActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.btn_show)
    Button mBtnShow;
    @BindView(R.id.auto_rl)
    RelativeLayout mAutoRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_edit_text_full_screen);
        ButterKnife.bind(this);
        AndroidSoftBoardAdjustHeightUtil.assistActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mImageView.setImageResource(ASourceUtil.getRandomImageId());
    }


    private float mLastX;
    private float mLastY;

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

    @OnClick({R.id.btn_do_something, R.id.btn_do_second})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_do_something:
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mAutoRl.getLayoutParams();
                layoutParams.height = mAutoRl.getHeight() / 2 ;
                mAutoRl.requestLayout();
                break;
            case R.id.btn_do_second:
                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mAutoRl.getLayoutParams();
                layoutParams1.height = getWindow().getDecorView().getHeight() ;
                mAutoRl.requestLayout();
                break;
        }
    }
}
