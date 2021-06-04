package com.aserbao.aserbaosandroid.ui.customView.selector.rv;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.databinding.RvItemBorderSelRoundBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelBeans;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description:
 **/
public class HeadSelVH extends RecyclerView.ViewHolder {
    private final RvItemBorderSelRoundBinding binding;

    public HeadSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemBorderSelRoundBinding.bind(itemView);
    }

    public void setDataSource(SelBeans beans, int position, SelRVAdapter.ItemClickerListener itemClickerListener){
        if(beans == null) return;
        if(beans.resId > 0){
            binding.borderSelRoundIV.setCurrMode(0);
            binding.borderSelRoundIV.setImageResource(beans.resId);
            binding.borderSelRoundIV.setBorderInterval(0);

//            binding.borderSelRoundIV.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        binding.borderSelRoundIV.setOnClickListener(v -> {
            beans.isSel = true;
            if (itemClickerListener != null) {
                itemClickerListener.itemClick(beans,position);
            }
        });
    }
}
