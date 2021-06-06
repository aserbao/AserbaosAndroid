package com.aserbao.aserbaosandroid.ui.customView.selector.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.title.RadioButtonSelVH;
import com.aserbao.aserbaosandroid.ui.customView.selector.rv.title.SelTitleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Created by aserbao on 2018/1/25.
 * 顶部导航栏
 */
public class SelRVContainerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    protected List<RoleConfig> mRoleConfig = new ArrayList<>();
    private ItemClickerListener mItemClickerListener;


    public SelRVContainerAdapter(Context mContext, List<RoleConfig> mColorBeans, ItemClickerListener itemClickerListener) {
        this.mContext = mContext;
        this.mItemClickerListener = itemClickerListener;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_sel_radio_button, parent, false);
        return new RadioButtonSelVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mRoleConfig.size() && holder instanceof RadioButtonSelVH) {
            RoleConfig roleConfig = mRoleConfig.get(position);
            ((RadioButtonSelVH) holder).setDataSource(roleConfig, position, new SelTitleAdapter.ItemClickerListener() {
                @Override
                public void itemClick(RoleConfig beans, int position) {
                    for (RoleConfig config : mRoleConfig) {
                        if(config != beans){
                            config.setSel(false);
                        }
                    }
                    notifyDataSetChanged();
                    mItemClickerListener.itemClick(beans,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mRoleConfig.size() > 0) {
            ret = mRoleConfig.size();
        }
        return ret;
    }


    public interface ItemClickerListener{
        void itemClick(RoleConfig beans, int position);
    }

}
