package com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView;

import android.app.Activity;
import android.content.Context;

import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-15 16:43
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.recyclerViewPickView
 */
public class PickerAdapter extends BaseRecyclerViewActivityAdapter {

    public PickerAdapter(Context context, Activity activity, List<BaseRecyclerBean> classBeen, IBaseRecyclerItemClickListener listener) {
        super(context, activity, classBeen, listener);
    }

    private boolean mIsLoop;

    public void setmIsLoop(boolean mIsLoop) {
        this.mIsLoop = mIsLoop;
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (!mIsLoop && mBaseRecyclerBean.size() > 0) {
            ret = mBaseRecyclerBean.size();
        }else{
            ret = Integer.MAX_VALUE;
        }
        return ret;
    }
}
