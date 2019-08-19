package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.adapters.BaseRecyclerViewActivityAdapter;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import java.util.ArrayList;
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
                initView();
                return true;
            }
        });
    }

    private LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.HORIZONTAL;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();

    private void initView() {
        mBaseRecyclerBeen = ImageSource.getStaticRecyclerViewData(mBaseRecyclerBeen);
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, new IBaseRecyclerItemClickListener() {
            @Override
            public void itemClickBack(View view, int position) {
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                int length = lastVisibleItemPosition - firstVisibleItemPosition ;
                Pair<View, String>[] sharedElements = new Pair[length + 1];
                for (int i = 0; i <= length; i++) {
                    int absoultPosition = firstVisibleItemPosition + i;
                    sharedElements[i] =  Pair.create(mLinearLayoutManager.findViewByPosition(absoultPosition),String.valueOf(ImageSource.iamgeUrl[absoultPosition]));
                }
                BShareModuleActivity.launch(AShareModuleActivity.this, view,position,sharedElements);
            }
        });
        mCommonAdapter.setmOrientation(mOrientation);
        mLinearLayoutManager = new LinearLayoutManager(this, mOrientation, false);
        mModuleRecyclerView.setLayoutManager(mLinearLayoutManager);
        mModuleRecyclerView.setAdapter(mCommonAdapter);
    }

    @OnClick({R.id.a_share_module_circle_iv, R.id.a_share_module_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_share_module_circle_iv:
                BShareModuleActivity.launch(this, mAShareModuleCircleIv);
                break;
            case R.id.a_share_module_btn:
                BShareModuleActivity.launch(this, mAShareModuleBtn);
                break;
        }
    }
}
