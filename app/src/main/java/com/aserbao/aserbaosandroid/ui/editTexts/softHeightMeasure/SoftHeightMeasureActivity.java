package com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SoftHeightMeasureActivity extends AppCompatActivity {

    @BindView(R.id.soft_et)
    EditText softEt;
    @BindView(R.id.show_soft_height_tv)
    TextView showSoftHeightTv;
    @BindView(R.id.soft_height_rl)
    RelativeLayout softHeightRl;
    private int mHeightDifference;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_height_measure);
        ButterKnife.bind(this);
        initListener();

    }

    private void initPop(int offy) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.test_pop, null);
        mPopupWindow = new PopupWindow(rootView.getRootView(), WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(rootView.getRootView(), Gravity.AXIS_Y_SHIFT, 0, -offy);
    }

    private void initListener() {
        showSoftHeightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop(mHeightDifference);
            }
        });
        View mChildOfContent = softHeightRl.getChildAt(0);
        mChildOfContent.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        softHeightRl.getWindowVisibleDisplayFrame(r);
                        int screenHeight = softHeightRl.getRootView().getHeight();
                        int screenWidth = softHeightRl.getRootView().getWidth();
                        mHeightDifference = screenHeight - (r.bottom);
                        int widthDifference = screenWidth - (r.right);
                        showSoftHeightTv.setText("检测布局屏幕显示的Top:" + r.top + "\n" +
                                "检测布局屏幕显示的Bottom:" + r.bottom + "\n" +
                                "检测布局屏幕显示的Left:" + r.left + "\n" +
                                "检测布局屏幕显示的Right:" + r.right + "\n" +
                                "检测布局屏幕getHeight:" + screenHeight + "\n" +
                                "检测布局屏幕getWidth:" + screenWidth + "\n" +
                                "检测布局屏幕显示高度差为:" + mHeightDifference + "\n" +
                                "检测布局屏幕显示宽度差为:" + +widthDifference);
                    }
                });
    }

    @OnClick({R.id.bt_adjustPan, R.id.bt_adjustResize, R.id.bt_adjustUnspecified,R.id.btn_adjustNothing})
    public void onViewClicked(View view) {
        int inputMode = 0;
        switch (view.getId()) {
            case R.id.bt_adjustPan:
                 inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
                break;
            case R.id.bt_adjustResize:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
                break;
            case R.id.bt_adjustUnspecified:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED;
                break;
            case R.id.btn_adjustNothing:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
                break;
        }
        getWindow().setSoftInputMode(inputMode);
    }
}
