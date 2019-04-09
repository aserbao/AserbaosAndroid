package com.aserbao.aserbaosandroid.base.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.ConstantUtils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.beans.ClassBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class BaseActivityAdapter extends RecyclerView.Adapter<BaseActivityAdapter.OpenGlViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<ClassBean> mClassBeen = new ArrayList<>();

    public BaseActivityAdapter(Context context, Activity activity, List<ClassBean> classBeen) {
        mContext = context;
        mActivity = activity;
        mClassBeen = classBeen;
    }

    @Override
    public OpenGlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_activity_item, parent, false);
        return new OpenGlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OpenGlViewHolder holder, int position) {
        final ClassBean classBean = mClassBeen.get(position);
        holder.mItemCardView.setBackgroundResource(ConstantUtils.getDrawable());
        holder.mItemTv.setText(classBean.getName());
        holder.mItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, classBean.getClazz());
                if (classBean.getFlag() >= 0){
                    intent.setFlags(classBean.getFlag());
                }
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mClassBeen.size() > 0) {
            ret = mClassBeen.size();
        }
        return ret;
    }

    class OpenGlViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        TextView mItemTv;
        @BindView(R.id.item_card_view)
        CardView mItemCardView;
        public OpenGlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
