package com.aserbao.aserbaosandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.ConstantUtils;
import com.aserbao.aserbaosandroid.opengl.OpenGlBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 */


public class OpenGlRVAdapter extends RecyclerView.Adapter<OpenGlRVAdapter.OpenGlViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<OpenGlBean> mOpenGlBeans = new ArrayList<>();

    public OpenGlRVAdapter(Context context, Activity activity, List<OpenGlBean> openGlBeans) {
        mContext = context;
        mActivity = activity;
        mOpenGlBeans = openGlBeans;
    }

    @Override
    public OpenGlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.opengl_recycler_item, parent, false);
        return new OpenGlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OpenGlViewHolder holder, int position) {
        final OpenGlBean openGlBean = mOpenGlBeans.get(position);
        holder.mItemCardView.setBackgroundResource(ConstantUtils.getDrawable());
        holder.mItemTv.setText(openGlBean.getName());
        holder.mItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity,openGlBean.getClazz()));
            }
        });
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mOpenGlBeans.size() > 0) {
            ret = mOpenGlBeans.size();
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
