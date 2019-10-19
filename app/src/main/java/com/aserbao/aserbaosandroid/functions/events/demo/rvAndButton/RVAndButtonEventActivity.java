package com.aserbao.aserbaosandroid.functions.events.demo.rvAndButton;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.AUI.progress.ACustomRecordProgress;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseAddFrameLayoutActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.PickerRecyclerView;

import org.jcodec.common.model.Frame;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Button覆盖在RecyclerView上面，当在Button上执行点击常按操作时，事件交给Button处理，当Button滑动的时候，事件交给RecyclerView处理，当Button常按再滑动时，事件交给Button自己处理。
 */
public class RVAndButtonEventActivity extends BaseAddFrameLayoutActivity {

    private static final String TAG = "RVAndButtonEventActivit";
    ACustomRecordProgress mEventRvCrProgress;
    PickerRecyclerView mEventRvPickerRv;
    FrameLayout mEventRvCrFrameLayout;
    ADispatchTouchEventFragment mEventRvDispatchTeFrameLayout;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("添加布局显示",0));

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        switch (position){
            case 0:
                View addRootView = LayoutInflater.from(mContext).inflate(R.layout.function_event_rv_activity, null);
                addViewToFrameLayoutFullScreen(addRootView);
                mEventRvCrProgress = ((ACustomRecordProgress) addRootView.findViewById(R.id.event_rv_cr_progress));
                mEventRvPickerRv = ((PickerRecyclerView) addRootView.findViewById(R.id.event_rv_picker_rv));
                mEventRvCrFrameLayout = ((FrameLayout) addRootView.findViewById(R.id.event_rv_cr_frame_layout));
                mEventRvDispatchTeFrameLayout = ((ADispatchTouchEventFragment) addRootView.findViewById(R.id.event_rv_dispatch_te_fragment));
                mEventRvPickerRv.setPadding(AserbaoApplication.screenWidth / 2, 0, AserbaoApplication.screenWidth / 2, 0);
                mEventRvPickerRv.setNeedLoop(false);
//                mEventRvPickerRv.setNeedCenterForce(true);
                initRecyclerView(mEventRvPickerRv,StaticFinalValues.VIEW_HOLDER_CIRCLE_IMAGE_ITEM,null);
                init();
                break;
        }
    }

    private float mLastX;
    private float mLastY;
    private int cuurTouchStatus = 0; // 0表示默认，1表示点击，2表示常按，3表示拖拽
    private long mActionDownStartTime = 0;
    public void  init(){
        mEventRvCrProgress.setmEventRvDispatchTeFrameLayout(mEventRvDispatchTeFrameLayout);
        mEventRvDispatchTeFrameLayout.setmIScrollListener(new ADispatchTouchEventFragment.IScrollListener() {
            @Override
            public void scrollListener(float scrollX) {
                Log.e(TAG, "scrollListener: " + scrollX );
            }

            @Override
            public void moveListener(float moveX, float moveY) {
                Log.d(TAG, "moveListener() called with: moveX = [" + moveX + "], moveY = [" + moveY + "]");
            }

            @Override
            public void clickListener() {
                Toast.makeText(mContext, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
