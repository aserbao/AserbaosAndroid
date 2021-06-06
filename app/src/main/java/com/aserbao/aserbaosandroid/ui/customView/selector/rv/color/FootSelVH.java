package com.aserbao.aserbaosandroid.ui.customView.selector.rv.color;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.RvItemBorderSelFootBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.HeadFooterClickerListener;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.color.SelColorAdapter;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description:
 **/
public class FootSelVH extends RecyclerView.ViewHolder {
    private final RvItemBorderSelFootBinding binding;

    public FootSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemBorderSelFootBinding.bind(itemView);
    }

    public void setDataSource(int position, HeadFooterClickerListener itemClickerListener){
        binding.borderSelHeadRoundIV.setImageResource(R.drawable.more_color);
        binding.borderSelHeadRoundIV.setOnClickListener(v -> {
            if (itemClickerListener != null) {
                itemClickerListener.itemClick(false);
            }
        });
    }
}
