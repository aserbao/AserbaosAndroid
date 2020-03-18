package com.aserbao.aserbaosandroid.ui.recyclerView.animator.swapItemAnimator;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

public class ChangeItemAnimatorActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "ChangeItemAnimatorActiv";

    private ChangeItemAdapter changeItemAdapter;
    private SimpleItemTouchCallBack simpleItemTouchCallBack;

    @Override
    public void initGetData() {
        /*for (int i = 0; i < 10; i++) {
            mBaseRecyclerBean.add(new BaseRecyclerBean(String.valueOf(i)));
        }*/
        ASourceUtil.getStaticRecyclerViewData(mBaseRecyclerBean, 1);
        changeOrientation(StaticFinalValues.LINEAR_LAYOUTMANAGER, LinearLayoutManager.HORIZONTAL);

    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
            if (simpleItemTouchCallBack != null) {
                RecyclerView.ViewHolder viewHolderForAdapterPosition = mOpenglRecyclerView.findViewHolderForAdapterPosition(0);
                int itemCount = changeItemAdapter.getItemCount() - 1;
                RecyclerView.ViewHolder lastHolderForAdapterPosition = mOpenglRecyclerView.findViewHolderForAdapterPosition(itemCount);
                simpleItemTouchCallBack.onMove(mOpenglRecyclerView, viewHolderForAdapterPosition, lastHolderForAdapterPosition);
            }
            Log.d(TAG, "itemClickBack() called with: view = [" + view + "], position = [" + position + "], isLongClick = [" + isLongClick + "]");
    }


     @Override
    public void initViewForLinear() {
        changeItemAdapter = new ChangeItemAdapter(this, this, mBaseRecyclerBean, this);
        if (mMode == StaticFinalValues.GRID_LAYOUTMANAGER) {
            mLinearLayoutManager = new GridLayoutManager(this, 3);
        } else {
            mLinearLayoutManager = new LinearLayoutManager(this, mRvOrientation, false);
        }
        mOpenglRecyclerView.setLayoutManager(mLinearLayoutManager);
        mOpenglRecyclerView.setAdapter(changeItemAdapter);
        mBaseRecyclerViewFl.setBackgroundResource(ASourceUtil.getRandomImageId());
        // 拖拽移动和左滑删除
       simpleItemTouchCallBack = new SimpleItemTouchCallBack(changeItemAdapter);
    // 要实现侧滑删除条目，把 false 改成 true 就可以了
        simpleItemTouchCallBack.setmSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(mOpenglRecyclerView);
    }
}
