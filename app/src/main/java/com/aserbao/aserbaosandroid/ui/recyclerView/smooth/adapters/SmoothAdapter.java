package com.aserbao.aserbaosandroid.ui.recyclerView.smooth.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans.SimpleBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.interfaces.ISmoothCallBackListener;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.viewholders.SmoothFootViewHolder;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.viewholders.SmoothHeadViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/28 11:51 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.smooth.adapters
 */
public class SmoothAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    public List<SimpleBean> simpleBeansList = new ArrayList<>();
    private ISmoothCallBackListener mISmoothCallBackListener;

    /**
     * @param mContext
     * @param initPosition 默认位置在数据列表中的位置
     * @param simpleBeansList
     * @param iSmoothCallBackListener
     */
    public SmoothAdapter(Context mContext, int initPosition, List<SimpleBean> simpleBeansList, ISmoothCallBackListener iSmoothCallBackListener) {
        mClickPosition= mLastClickPosition= initPosition;
        this.mContext = mContext;
        this.simpleBeansList = simpleBeansList;
        mISmoothCallBackListener = iSmoothCallBackListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return StaticFinalValues.HEAD;
        } else if (position == simpleBeansList.size() + 1) {
            return StaticFinalValues.FOOT;
        } else {
            return StaticFinalValues.CONTENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        switch (viewType) {
            case StaticFinalValues.HEAD:
                view = LayoutInflater.from(mContext).inflate(R.layout.smooth_head_item, viewGroup, false);
                return new SmoothHeadViewHolder(view);
            case StaticFinalValues.FOOT:
                view = LayoutInflater.from(mContext).inflate(R.layout.smooth_foot_item, viewGroup, false);
                return new SmoothFootViewHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.smooth_content_item, viewGroup, false);
                return new SmoothContentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SmoothContentViewHolder) {
            int cuurPosition = i - 1;
            if (simpleBeansList.size() > cuurPosition) {
                SimpleBean simpleBean = simpleBeansList.get(cuurPosition);
                ((SmoothContentViewHolder) viewHolder).setDataSource(mContext, cuurPosition,mISmoothCallBackListener, simpleBean);
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 2;
        if (simpleBeansList.size() > 0) {
            ret = ret + simpleBeansList.size();
        }
        return ret;
    }
    private View mLastView;
    private int mClickPosition = 0;
    private int mLastClickPosition = 0;
    public class SmoothContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.smooth_item_tv)
        public TextView mSmoothItemTv;
        private int cuurPosition;
        private ISmoothCallBackListener mISmoothCallBackListener;
        private SimpleBean mSimpleBean;
        public SmoothContentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setDataSource(Context context,int position, ISmoothCallBackListener iSmoothCallBackListener, SimpleBean simpleBean){
            mISmoothCallBackListener = iSmoothCallBackListener;
            if (mClickPosition == position){
                mSmoothItemTv.setBackgroundResource(R.drawable.smooth_item_view_bg);
            }else{
                mSmoothItemTv.setBackgroundResource(R.drawable.smooth_item_view_white_bg);
            }
            mSimpleBean = simpleBean;
            cuurPosition = position;
            mSmoothItemTv.setText(simpleBean.getContent());

        }

        @OnClick(R.id.smooth_item_card_view)
        public void onViewClicked(View view ) {
            Object tag = mSmoothItemTv.getTag();
            if (tag != null && (int) tag == 1){
                mISmoothCallBackListener.itemClickTwo();
                mSmoothItemTv.setTag(0);
            }else {
                if (mLastView != null) {
                    mLastView.setBackgroundResource(R.drawable.smooth_item_view_white_bg);
                    mLastView.setTag(0);
                }
                mISmoothCallBackListener.itemClick(mLastClickPosition,cuurPosition, mSimpleBean);
                mSmoothItemTv.setBackgroundResource(R.drawable.smooth_item_view_bg);
                mSmoothItemTv.setTag(1);
                mLastView = mSmoothItemTv;
                mClickPosition = cuurPosition;
                mLastClickPosition = cuurPosition;
            }
        }
    }

}
