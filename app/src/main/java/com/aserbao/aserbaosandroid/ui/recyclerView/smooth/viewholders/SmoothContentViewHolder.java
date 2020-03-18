package com.aserbao.aserbaosandroid.ui.recyclerView.smooth.viewholders;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.beans.SimpleBean;
import com.aserbao.aserbaosandroid.ui.recyclerView.smooth.interfaces.ISmoothCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/28 11:54 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.smooth.viewholders
 */
public class SmoothContentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.smooth_item_card_view)
    public CardView mSmoothItemCardView;
    @BindView(R.id.smooth_item_tv)
    public TextView mSmoothItemTv;
    private int clickColor;

    public SmoothContentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        clickColor = Color.parseColor("#fff656");
    }

    public void setDataSource(Context context, ISmoothCallBackListener iSmoothCallBackListener, SimpleBean simpleBean){
        mSmoothItemTv.setText(simpleBean.getContent());
    }

    @OnClick(R.id.smooth_item_card_view)
    public void onViewClicked(View view ) {
        mSmoothItemCardView.setCardBackgroundColor(clickColor);
    }
}
