package com.example.base.base.interfaces;

import android.view.View;

import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.beans.SelBean;

public interface ISelItemListener {
    void selItemClick(View view, int position, boolean isLongClick, SelBean selBean);
}
