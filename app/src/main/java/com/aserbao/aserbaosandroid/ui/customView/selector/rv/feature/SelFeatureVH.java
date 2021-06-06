package com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.databinding.RvItemBorderSelRoundBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelFeatureBean;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description:
 **/
public class SelFeatureVH extends RecyclerView.ViewHolder {
    private final RvItemBorderSelRoundBinding binding;

    public SelFeatureVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemBorderSelRoundBinding.bind(itemView);
    }

    public void setDataSource(SelFeatureBean beans, int position, SelFeatureAdapter.ItemClickerListener itemClickerListener){
        if(beans == null) return;
        if(beans.thumbnail > 0){
            binding.borderSelRoundIV.setImageResource(beans.thumbnail);
            binding.borderSelRoundIV.setScaleType(ImageView.ScaleType.CENTER_CROP);
            binding.borderSelRoundIV.setCurrMode(2);
        }
        binding.borderSelRoundIV.isHasBorder = true;
        binding.borderSelRoundIV.isChecked = beans.isSel;
        binding.borderSelRoundIV.postInvalidate();
        binding.borderSelRoundIV.setOnClickListener(v -> {
            if(beans.isSel) return;
            beans.isSel = true;
            if (itemClickerListener != null) {
                itemClickerListener.itemClick(beans,position);
            }
        });
    }
}
