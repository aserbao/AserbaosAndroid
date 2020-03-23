package com.example.base.base.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.base.R;
import com.example.base.base.beans.BaseRecyclerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:自定义SpinnerAdapter
 *
 * @author aserbao
 * @date : On 2019-11-06 17:25
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.adapters
 */
public class BaseSpinnerAdapter implements SpinnerAdapter{
    private Context mContext;
    public List<BaseRecyclerBean> mBaseSpinnerRecyclerBeen = new ArrayList<>();

    public BaseSpinnerAdapter(Context mContext,List<BaseRecyclerBean> baseSpinnerRecyclerBeen) {
        this.mContext = mContext;
        mBaseSpinnerRecyclerBeen = baseSpinnerRecyclerBeen;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = getView(position, parent);
        return view;
    }



    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mBaseSpinnerRecyclerBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mBaseSpinnerRecyclerBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position,parent);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    private View getView(int position, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.spinner_text_item, parent, false);
        Object item = getItem(position);
        if (item instanceof BaseRecyclerBean) {
            ((TextView) view.findViewById(R.id.spinner_tv)).setText(((BaseRecyclerBean) item).getName());
            int tag = ((BaseRecyclerBean) item).getTag();
            if (tag >= 0){
                view.setTag(tag);
            }else{
                view.setTag(position);
            }
        }
        return view;
    }
}
