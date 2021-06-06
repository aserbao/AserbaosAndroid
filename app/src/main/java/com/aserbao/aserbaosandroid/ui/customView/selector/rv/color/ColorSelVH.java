package com.aserbao.aserbaosandroid.ui.customView.selector.rv.color;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.databinding.RvItemBorderSelRoundBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelColorBean;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.SelColorAdapter;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description: 颜色布局
 **/
public class ColorSelVH extends RecyclerView.ViewHolder {
    private final RvItemBorderSelRoundBinding binding;

    public ColorSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemBorderSelRoundBinding.bind(itemView);
    }

    public void setDataSource(SelColorBean beans, int position, SelColorAdapter.ItemClickerListener itemClickerListener){
        if(beans == null) return;
        if(!TextUtils.isEmpty(beans.color)) {
            binding.borderSelRoundIV.setCenterColor(Color.parseColor(beans.color));
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
