package com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRecyclerItemClickListener;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.CircularHorizontalBTTMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.CircularHorizontalMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.CircularViewMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.CircularViewRTLMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.DefaultMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.ItemViewMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.PickerRecyclerView;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.RotateXScaleYViewMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.RotateYScaleXViewMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.ScaleXViewMode;
import com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView.pickViewLibrary.ScaleYViewMode;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPickActivity extends BaseRecyclerViewActivity {

    private LinearLayoutManager mLayoutManager;
    private boolean mIsNotLoop;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("循环滑动",100));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("不循环滑动",101));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Default",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CircularViewMode",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("ScaleXViewMode",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("ScaleYViewMode",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("RotateXScaleYViewMode",4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("RotateYScaleXViewMode",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CircularHorizontalMode",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CircularViewRTLMode",8));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("CircularHorizontalBTTMode",9));

    }
    private ItemViewMode mItemViewMode;
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 100:
                mIsNotLoop = false;
                if (mLayoutManager == null)
                    Toast.makeText(mContext, "请先选择一个Mode", Toast.LENGTH_SHORT).show();
                return;
            case 101:
                mIsNotLoop = true;
                if (mLayoutManager == null)
                    Toast.makeText(mContext, "请先选择一个Mode", Toast.LENGTH_SHORT).show();
                return;
            case 0:
                mItemViewMode = new DefaultMode();
                mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
                break;
            case 1:
                mItemViewMode = new CircularViewMode();
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case 2:
                mItemViewMode = new ScaleXViewMode();
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                break;
            case 3:
                mItemViewMode = new ScaleYViewMode();
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case 4:
                mItemViewMode = new RotateXScaleYViewMode();
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case 5:
                mItemViewMode = new RotateYScaleXViewMode();
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                break;
            case 6:
                mItemViewMode = new CircularViewMode();
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case 7:
                mItemViewMode = new CircularHorizontalMode();
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                break;
            case 8:
                mItemViewMode = new CircularViewRTLMode();
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case 9:
                // notice when use horizontal layoutManager and item in recyclerView bottom
                // the item xml file is not useful set marginTop and set alginParentBottom,
                // u can use the first argument to control the item position of the recyclerView
                // and this offset need use some density util to resolve adaptation
                // and the offset is relative recyclerView top abs distance
                mItemViewMode = new CircularHorizontalBTTMode(600, false);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                break;

        }
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.recycler_picker, null);
        addViewToFrameLayoutFullScreen(rootView);
        PickerRecyclerView recyclerView = (PickerRecyclerView) rootView.findViewById(R.id.picker_recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setViewMode(mItemViewMode);
        recyclerView.setNeedCenterForce(true);
        recyclerView.setNeedLoop(!mIsNotLoop);
        recyclerView.setOnCenterItemClickListener(new PickerRecyclerView.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClick(View v) {
                Toast.makeText(mContext, "Center Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        List<BaseRecyclerBean> baseRecyclerBeen = new ArrayList<>();
        for (int i = 0; i < ASourceUtil.imageUrls.length; i++) {
            baseRecyclerBeen.add(new BaseRecyclerBean(ASourceUtil.imageUrls[i], StaticFinalValues.VIEW_HOLDER_CIRCLE_IMAGE_ITEM,i));
        }
        PickerAdapter pickerAdapter = new PickerAdapter(this, this, baseRecyclerBeen, new IBaseRecyclerItemClickListener() {
            @Override
            public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
                Toast.makeText(RecyclerViewPickActivity.this, position+"被点击了,常按？=" + isLongClick, Toast.LENGTH_SHORT).show();
            }
        });
        pickerAdapter.setmIsLoop(!mIsNotLoop);
        recyclerView.setAdapter(pickerAdapter);
    }
}
