package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AShareModuleActivity extends AppCompatActivity {

    @BindView(R.id.a_share_module_circle_iv)
    CircleImageView mAShareModuleCircleIv;
    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;
    @BindView(R.id.a_share_module_btn)
    Button mAShareModuleBtn;
    @BindView(R.id.module_recycler_view)
    RecyclerView mModuleRecyclerView;
    @BindView(R.id.animator_ll)
    FrameLayout mAnimatorLl;

    @BindView(R.id.animator_ll_container)
    LinearLayout  mAnimatorLlContainer;
    @BindView(R.id.module_recycler_view2)
    RecyclerView mModuleRecyclerView2;

    @BindView(R.id.a_share_module_view)
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashare_module);
        ButterKnife.bind(this);
        mShowActivityNameTv.setText("AShareModuleActivity");
        initView();
        initListener();
    }

    private void initListener() {
        mAShareModuleBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOrientation ==LinearLayoutManager.HORIZONTAL ){
                    mOrientation = LinearLayoutManager.VERTICAL;
                }else{
                    mOrientation = LinearLayoutManager.HORIZONTAL;
                }
                mAnimatorLlContainer.setOrientation(mOrientation);
                initView();
                return true;
            }
        });
    }

    private LinearLayoutManager mLinearLayoutManager;
    public AShareModuleAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.HORIZONTAL;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();
    public static int endPosition,startPosition = 0;
    private void initView() {
        mBaseRecyclerBeen = ImageSource.getStaticRecyclerViewData(mBaseRecyclerBeen);
        mCommonAdapter = new AShareModuleAdapter(this, this, mBaseRecyclerBeen, new IBaseRecyclerItemClickListener() {
            @Override
            public void itemClickBack(View view, int position) {
                startPosition = position;
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                int length = lastVisibleItemPosition - firstVisibleItemPosition ;
                Pair<View, String>[] sharedElements = new Pair[length + 1];
                for (int i = 0; i <= length; i++) {
                    int absoultPosition = firstVisibleItemPosition + i;
                    sharedElements[i] =  Pair.create(mLinearLayoutManager.findViewByPosition(absoultPosition),String.valueOf(ImageSource.iamgeUrl[absoultPosition]));
                }
                if(mView.getVisibility() == View.VISIBLE) {
                    mView.setVisibility(View.GONE);
                }else{
                    mView.setVisibility(View.VISIBLE);
                }
                BShareModuleActivity.launch(AShareModuleActivity.this, view,position,sharedElements);
                myHandler.sendEmptyMessageDelayed(0,1000);
            }
        },AShareModuleAdapter.BOTTOM);
        mCommonAdapter.setmOrientation(mOrientation);
        mLinearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        mModuleRecyclerView.setLayoutManager(mLinearLayoutManager);
        mModuleRecyclerView.setAdapter(mCommonAdapter);

        /*AShareModuleAdapter aShareModuleAdapter = new AShareModuleAdapter(this, this, mBaseRecyclerBeen, null, AShareModuleAdapter.TOP);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        aShareModuleAdapter.setmOrientation(mOrientation);
        mModuleRecyclerView2.setLayoutManager(linearLayoutManager);
        mModuleRecyclerView2.setAdapter(aShareModuleAdapter);*/
    }



    @OnClick({R.id.a_share_module_circle_iv, R.id.a_share_module_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_share_module_circle_iv:
                BShareModuleActivity.launch(this, mAShareModuleCircleIv);
                break;
            case R.id.a_share_module_btn:
                BShareModuleActivity.launch(this, mAShareModuleBtn);
//                viewDisappearAnimator(0,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case StaticFinalValues.COME_FROM_B_SHARE_MODULE_ACTIVITY:
//                viewDisappearAnimator(startPosition,endPosition);
                viewDisappearWithRecyclerView(startPosition,endPosition);
                break;
        }
    }


    public void viewDisappearWithRecyclerView(int startPosition,int endPosition){

        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        List<BaseRecyclerBean> mTempBaseRecyclerBean = new ArrayList<>();
        for (int i = 0; i <= lastVisibleItemPosition - firstVisibleItemPosition; i++) {
            BaseRecyclerBean baseRecyclerBean = mBaseRecyclerBeen.get(i);
            mTempBaseRecyclerBean.add(baseRecyclerBean);
        }
        AShareModuleAdapter aShareModuleAdapter = new AShareModuleAdapter(this, this, mTempBaseRecyclerBean, null, AShareModuleAdapter.TOP);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        aShareModuleAdapter.setmOrientation(mOrientation);
        mModuleRecyclerView2.setLayoutManager(linearLayoutManager);
        mModuleRecyclerView2.setAdapter(aShareModuleAdapter);
        mModuleRecyclerView2.setVisibility(View.VISIBLE);

        mModuleRecyclerView2.post(new Runnable() {
            @Override
            public void run() {
                int interval = endPosition - startPosition;
                int absoluteStartPosition = startPosition - firstVisibleItemPosition;
                for (int i = absoluteStartPosition; i <= interval + absoluteStartPosition; i++) {
                    RecyclerView.ViewHolder viewHolderForLayoutPosition2 = mModuleRecyclerView2.findViewHolderForLayoutPosition(i);
                    RecyclerView.ViewHolder viewHolderForLayoutPosition = mModuleRecyclerView.findViewHolderForLayoutPosition(i);
                    if (viewHolderForLayoutPosition2 != null) {
                        viewHolderForLayoutPosition2.itemView.setVisibility(View.VISIBLE);
                        viewHolderForLayoutPosition.itemView.setVisibility(View.INVISIBLE);
                    }
                }

                PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.1f,1.0f);
                PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.1f,1.0f);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mModuleRecyclerView2,  valuesHolder1, valuesHolder2);
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mModuleRecyclerView2.setVisibility(View.GONE);
                        mCommonAdapter.removePositionItem(startPosition,endPosition);
                        mCommonAdapter.notifyDataSetChanged();
                    }
                });
                objectAnimator.setDuration(2000).start();
            }
        });
    }


    public void viewDisappearAnimator(int startPosition,int endPosition){
        if (mLinearLayoutManager == null) return;
        for (int i = startPosition; i <= endPosition; i++) {
            View view = mLinearLayoutManager.findViewByPosition(i);
            mModuleRecyclerView.removeView(view);
            if (view != null) {
                mAnimatorLlContainer.addView(view);
            }
            mCommonAdapter.removePositionItem(i);
        }
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.1f,1.0f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.1f,1.0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mAnimatorLlContainer,  valuesHolder1, valuesHolder2);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorLlContainer.removeAllViews();
//                mCommonAdapter.removePositionItem(startPosition,endPosition);
                mCommonAdapter.notifyDataSetChanged();
            }
        });
        objectAnimator.setDuration(1000).start();

    }

    private static final String TAG = "AShareModuleActivity";
    private MyHandler myHandler = new MyHandler();
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCommonAdapter.notifyDataSetChanged();
            Log.e(TAG, "handleMessage: "  );
        }
    }
}
