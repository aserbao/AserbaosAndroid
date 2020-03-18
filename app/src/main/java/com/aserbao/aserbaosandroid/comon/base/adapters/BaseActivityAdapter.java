package com.aserbao.aserbaosandroid.comon.base.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.AUtils.utils.ConstantUtils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.viewHolder.ClassViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class BaseActivityAdapter extends RecyclerView.Adapter<ClassViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<BaseRecyclerBean> mBaseRecyclerBean = new ArrayList<>();

    public BaseActivityAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen) {
        mContext = context;
        mActivity = activity;
        mBaseRecyclerBean = classBeen;
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_recycler_view_class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        final BaseRecyclerBean classBean = mBaseRecyclerBean.get(position);
        holder.mItemCardView.setBackgroundResource(ConstantUtils.getDrawable());
        holder.mItemTv.setText(classBean.getName());
        holder.mItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, classBean.getClazz());
                if (classBean.getTag() >= 0){
                    intent.setFlags(classBean.getTag());
                }
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mBaseRecyclerBean.size() > 0) {
            ret = mBaseRecyclerBean.size();
        }
        return ret;
    }


}
