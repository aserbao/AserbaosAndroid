package com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.PopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/30 2:26 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.adapters
 */
public class PopSimpleDemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PopBean> mPopBean = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public PopSimpleDemoAdapter(Context mContext, List<PopBean> mPopBean,ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.mPopBean = mPopBean;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_button_item, viewGroup, false);
        return new SimpleButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SimpleButtonViewHolder){
            if (mPopBean.size() > i) {
                String content = mPopBean.get(i).getContent();
                if (!TextUtils.isEmpty(content)) {
                    ((SimpleButtonViewHolder) viewHolder).mRecyclerViewButtonBtn.setText(content);
                    ((SimpleButtonViewHolder) viewHolder).mRecyclerViewButtonBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemClickListener.itemClick(i);
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mPopBean.size() > 0){
            ret = ret + mPopBean.size();
        }
        return ret;
    }

    public static class SimpleButtonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_view_button_btn)
        public Button mRecyclerViewButtonBtn;
        public SimpleButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface ItemClickListener{
        void itemClick(int position);
    }
}
