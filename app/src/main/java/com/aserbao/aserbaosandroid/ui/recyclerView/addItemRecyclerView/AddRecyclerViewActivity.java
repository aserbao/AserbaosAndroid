package com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerView.addItemRecyclerView.adapters.AddAdapters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class AddRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.add_recycler_view)
    RecyclerView mAddRecyclerView;
    @BindView(R.id.add_btn)
    Button mAddBtn;
    private AddAdapters addAdapters;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycler_view);
        ButterKnife.bind(this);
        initData();
        init();
//        initListener();
    }


    private List<String> mResult = new ArrayList<>();
    private int mLastPosition;

    private void initData() {
        for (int i = 0; i < 200; i++) {
            mResult.add("第" + i + " 条数据");
            mLastPosition = i;
        }
    }

    private void init() {
        addAdapters = new AddAdapters(mResult, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        mAddRecyclerView.setLayoutManager(linearLayoutManager);
        mAddRecyclerView.setAdapter(addAdapters);
    }

    private void initListener() {
        mAddRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    case SCROLL_STATE_IDLE:

                        break;
                        case SCROLL_STATE_DRAGGING:
                    break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition > addAdapters.getItemCount()/3 * 2){
                    mLastPosition++;
                    addAdapters.addTopItem("第" + mLastPosition + " 条数据");
                }
                Log.e("test", "onScrolled: " + lastVisibleItemPosition );
            }

        });
    }

    public void changEmptyItemHeight() {
        View view = linearLayoutManager.findViewByPosition(2);
        if (view == null) {
            return;
        }
        int itemHeight = view.getHeight();
        RecyclerView.ViewHolder viewHolder = mAddRecyclerView.findViewHolderForAdapterPosition(0);
        if (viewHolder instanceof AddAdapters.FootViewHolder) {
            ViewGroup.LayoutParams layoutParams = ((AddAdapters.FootViewHolder) viewHolder).mFootItemTv.getLayoutParams();
            int height = ((AddAdapters.FootViewHolder) viewHolder).mFootItemTv.getHeight();
            int height1 = height - itemHeight;
            layoutParams.height = height1;
            layoutParams.width = ((AddAdapters.FootViewHolder) viewHolder).mFootItemTv.getWidth();
            ((AddAdapters.FootViewHolder) viewHolder).mFootItemTv.setLayoutParams(layoutParams);
            Log.e("show ", "changEmptyItemHeight: 原来的：" + height + " 修改后的高度 :" + height1 + " 高度差： " + itemHeight);
        }
    }

    @OnClick({R.id.add_btn, R.id.add_btn_first})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
//                changEmptyItemHeight();
                mLastPosition++;
                addAdapters.addItem("第" + mLastPosition + " 条数据");
                linearLayoutManager.scrollToPosition(0);
                break;
            case R.id.add_btn_first:
                mLastPosition++;
                addAdapters.addTopItem("第" + mLastPosition + " 条数据");
                break;
        }
    }
}
