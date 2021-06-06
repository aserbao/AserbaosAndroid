package com.aserbao.aserbaosandroid.ui.customView.selector.rv.feature;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.RvItemBorderSelHeadBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.SelColorAdapter;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description:
 **/
public class HeadSelVH extends RecyclerView.ViewHolder {
    private final RvItemBorderSelHeadBinding binding;

    public HeadSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemBorderSelHeadBinding.bind(itemView);
    }

    public void setDataSource(int position, HeadFooterClickerListener itemClickerListener){
        binding.borderSelHeadRoundIV.setImageResource(R.drawable.feature_sel_custom);
        binding.borderSelHeadRoundIV.setOnClickListener(v -> {
            if (itemClickerListener != null) {
                itemClickerListener.itemClick(true);
            }
        });
    }
}
