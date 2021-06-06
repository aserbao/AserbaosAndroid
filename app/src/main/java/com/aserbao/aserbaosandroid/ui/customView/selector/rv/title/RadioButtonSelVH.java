package com.aserbao.aserbaosandroid.ui.customView.selector.rv.title;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.databinding.RvItemSelRadioButtonBinding;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.SelRVContainerAdapter;

/**
 * @Created time:2021/6/4 4:32 PM
 * @author: aserbao
 * @description:
 **/
public class RadioButtonSelVH extends RecyclerView.ViewHolder {
    private final RvItemSelRadioButtonBinding binding;

    public RadioButtonSelVH(@NonNull View itemView) {
        super(itemView);
        binding = RvItemSelRadioButtonBinding.bind(itemView);
    }

    public void setDataSource(RoleConfig beans, int position, SelTitleAdapter.ItemClickerListener itemClickerListener){
        if(beans == null) return;
        String name = beans.getName();
        binding.radioBtn.setText(name);
        binding.radioBtn.setChecked(beans.isSel());
        binding.radioBtn.setOnClickListener(v -> {
            binding.radioBtn.setChecked(true);
            beans.setSel(true);
            if (itemClickerListener != null) {
                itemClickerListener.itemClick(beans,position);
            }
        });
    }
}
