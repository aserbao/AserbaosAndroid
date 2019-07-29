package com.aserbao.aserbaosandroid.ui.recyclerView.smooth;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.adapters.SmoothAdapter;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans.SimpleBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.interfaces.ISmoothCallBackListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class SmoothActivitiy extends AppCompatActivity {

    @BindView(R.id.smooth_recycler_view)
    RecyclerView mSmoothRecyclerView;
    @BindView(R.id.smooth_card_view)
    CardView mSmoothCardView;
    @BindView(R.id.smooth_tv)
    TextView mSmoothTv;
    @BindView(R.id.smooth_rl)
    RelativeLayout mSmoothRl;
    private int mSmoothHeadHeight;
    private boolean isClick = false;
    private LinearLayoutManager mLinearLayoutManager;
    private SmoothAdapter mSmoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_actvitiy);
        ButterKnife.bind(this);
        mSmoothHeadHeight = (int)getResources().getDimension(R.dimen.smooth_head_height);
        init();
    }

    private int mClickMoveTotal =0;
    private int mTotal = 0;

    private void init() {
        List<SimpleBean> simpleBeanList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SimpleBean simpleBean = new SimpleBean(ImageSource.getRandomImageId(), String.valueOf(i));
            simpleBeanList.add(simpleBean);
        }
        int initPosition = 0;
        //                mSmoothCardView.setVisibility(View.GONE);
        mSmoothAdapter = new SmoothAdapter(this, initPosition, simpleBeanList, new ISmoothCallBackListener() {
            @Override
            public void itemClick(int mLastPosition, int position, SimpleBean simpleBean) {
                mSmoothTv.setText(simpleBean.getContent());
                mSmoothCardView.setVisibility(View.VISIBLE);
                scrollToPosition(mLastPosition,position);
                isClick = true;
                ObjectAnimator.ofFloat(mSmoothCardView, mSmoothCardView.Y, 0, mSmoothHeadHeight).setDuration(0).start();
            }

            @Override
            public void itemClickTwo() {
//                mSmoothCardView.setVisibility(View.GONE);
            }
        });
        mTotal = (int) mSmoothHeadHeight;
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSmoothRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSmoothRecyclerView.setAdapter(mSmoothAdapter);
        mSmoothRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case SCROLL_STATE_IDLE:
                        isClick = false;
                        break;
                    case SCROLL_STATE_DRAGGING:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isClick) {
                    mTotal = mSmoothHeadHeight;
                }else{
                    mTotal -= dy;
                }
                changeCardViewPosition(mTotal);
            }
        });
        mLinearLayoutManager.scrollToPositionWithOffset(initPosition,0);
    }


    public void changeCardViewPosition(int movePosition) {
        int scrollOffset = mSmoothRecyclerView.computeVerticalScrollOffset();
        int computeVerticalScrollExtent = mSmoothRecyclerView.computeVerticalScrollExtent();
        int scrollRange = mSmoothRecyclerView.computeVerticalScrollRange();

        Log.e(TAG, "scrollToPosition:scrollOffset =  " + scrollOffset + " computeVerticalScrollExtent = " + computeVerticalScrollExtent + " scrollRange =  " + scrollRange );

        ObjectAnimator.ofFloat(mSmoothCardView, mSmoothCardView.Y, 0, movePosition).setDuration(0).start();
    }

    private static final String TAG = "SmoothActivitiy";
    public void scrollToPosition(int mLastPosition, int i){
        int[] ints = new int[2];
        int childCount = mSmoothRecyclerView.getChildCount();
        int childCount1 = mLinearLayoutManager.getChildCount();
        int itemCount = mLinearLayoutManager.getItemCount();
        int itemCount1 = mSmoothAdapter.getItemCount();
        Log.e(TAG, "scrollToPosition: " + childCount );
        View view = mSmoothRecyclerView.getChildAt(i + 1);
        if (view != null) {
            float y = view.getY();
//            view.getLocationInWindow(ints);
            view.getLocationOnScreen(ints);
            int anIntY = ints[1];
            int chaHeight = anIntY -  mSmoothHeadHeight;
            Log.e(TAG, "scrollToPosition:i = " + i + " Y = " + ints[1] + " y = " + y + " chaHeight = " + chaHeight +  " mSmoothHeadHeight  =  " + mSmoothHeadHeight);
//            mSmoothRecyclerView.smoothScrollBy(0, chaHeight,new AccelerateDecelerateInterpolator());
        }
    }

}
