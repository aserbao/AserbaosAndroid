package com.aserbao.aserbaosandroid.functions.database.base.rv.viewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/26
 * email: this is empty email
 */
public class TextViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_holder_content_tv)
    public TextView mTextViewHolderContentTv;
    @BindView(R.id.text_view_holder_time_tv)
    public TextView mTextViewHolderTimeTv;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
