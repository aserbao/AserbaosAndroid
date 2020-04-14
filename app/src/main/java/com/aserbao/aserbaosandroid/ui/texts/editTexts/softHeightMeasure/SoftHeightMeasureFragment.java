package com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.aserbao.aserbaosandroid.AUtils.AUI.layout.FlowLayout;
import com.aserbao.aserbaosandroid.R;
import com.example.base.utils.data.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 非全屏状态下的软键盘显示
 */
public class SoftHeightMeasureFragment extends Fragment {

    @BindView(R.id.soft_mid_et)
    EditText siftMidEt;
    @BindView(R.id.soft_et)
    EditText softEt;
    @BindView(R.id.show_soft_height_tv)
    TextView showSoftHeightTv;
    /*@BindView(R.id.soft_height_rl)
    RelativeLayout mSoftHeightRl;*/
/*
    @BindView(R.id.image_view_bg)
    ImageView mImageViewBg;
*/
    @BindView(R.id.soft_height_fl)
    FlowLayout mSoftHeightFl;
    @BindView(R.id.soft_height_rl)
    ConstraintLayout mSoftHeightRl;
    private int mHeightDifference;
    private PopupWindow mPopupWindow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_soft_height_measure, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mImageViewBg.setImageResource(ASourceUtil.getRandomImageId());
    }


    private void initListener() {
        View mChildOfContent = mSoftHeightRl.getChildAt(0);
        mChildOfContent.getViewTreeObserver()
            .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    Rect r = new Rect();
                    mSoftHeightRl.getWindowVisibleDisplayFrame(r);
                    int screenHeight = mSoftHeightRl.getRootView().getHeight();
                    int screenWidth = mSoftHeightRl.getRootView().getWidth();
                    mHeightDifference = screenHeight - (r.bottom);
                    int widthDifference = screenWidth - (r.right);
                    if (mHeightDifference > 200) {
                        //软键盘显示
                        mSoftHeightFl.setVisibility(View.GONE);
                    } else {
                        //软键盘隐藏
                        mSoftHeightFl.setVisibility(View.VISIBLE);
                    }
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

    @OnClick({R.id.show_soft_height_tv, R.id.bt_adjustPan, R.id.bt_adjustResize, R.id.bt_adjustUnspecified, R.id.btn_adjustNothing,
        R.id.btn_stateHidden, R.id.btn_stateAlwaysHidden, R.id.btn_stateVisible, R.id.btn_stateAlwaysVisible})
    public void onViewClicked(View view) {
        int inputMode = 0;
        switch (view.getId()) {
            case R.id.show_soft_height_tv:
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                break;
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
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
                break;
            case R.id.btn_stateHidden:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
                startActivityOwn(inputMode);
                break;
            case R.id.btn_stateAlwaysHidden:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;
                startActivityOwn(inputMode);
                break;
            case R.id.btn_stateVisible:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
                startActivityOwn(inputMode);
                break;
            case R.id.btn_stateAlwaysVisible:
                inputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
                startActivityOwn(inputMode);
                break;
        }
        getActivity().getWindow().setSoftInputMode(inputMode);
    }

    public void startActivityOwn(int input) {
        Intent intent = new Intent(getActivity(), SoftHeightMeasureActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, input);
        startActivity(intent);
    }

}
