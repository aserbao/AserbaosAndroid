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
    }

    private LinearLayoutManager mLinearLayoutManager;
    public BaseRecyclerViewActivityAdapter mCommonAdapter;
    public int mOrientation = LinearLayoutManager.VERTICAL;
    public List<BaseRecyclerBean> mBaseRecyclerBeen = new ArrayList<>();

    private void initView() {
        mBaseRecyclerBeen = ImageSource.getStaticRecyclerViewData(mBaseRecyclerBeen);
        mCommonAdapter = new BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBeen, new IBaseRecyclerItemClickListener() {
            @Override
            public void itemClickBack(View view, int position) {
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                Pair pair1 = Pair.create(mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition),String.valueOf(ImageSource.iamgeUrl[firstVisibleItemPosition]));
                firstVisibleItemPosition++;
                Pair pair2 = Pair.create(mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition),String.valueOf(ImageSource.iamgeUrl[firstVisibleItemPosition]));
                firstVisibleItemPosition++;
                Pair pair3 = Pair.create(mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition),String.valueOf(ImageSource.iamgeUrl[firstVisibleItemPosition]));
                firstVisibleItemPosition++;
                Pair pair4 = Pair.create(mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition),String.valueOf(ImageSource.iamgeUrl[firstVisibleItemPosition]));
                firstVisibleItemPosition++;
                Pair pair5 = Pair.create(mLinearLayoutManager.findViewByPosition(firstVisibleItemPosition),String.valueOf(ImageSource.iamgeUrl[firstVisibleItemPosition]));
                BShareModuleActivity.launch(AShareModuleActivity.this, view,position,pair1,pair2,pair3,pair4,pair5);
            }
        });
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
