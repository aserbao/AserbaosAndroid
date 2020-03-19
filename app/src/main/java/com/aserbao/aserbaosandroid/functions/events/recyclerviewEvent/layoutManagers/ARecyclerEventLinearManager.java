package com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.layoutManagers;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/12
 * email: this is empty email
 */
public class ARecyclerEventLinearManager extends LinearLayoutManager {
    public ARecyclerEventLinearManager(Context context) {
        super(context);
    }

    public ARecyclerEventLinearManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ARecyclerEventLinearManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private boolean isCanScroll = true;
    public void setCanScrollHorizontally(boolean m){
        isCanScroll = m;
    }
    @Override
    public boolean canScrollHorizontally() {
        return isCanScroll & super.canScrollHorizontally();
    }
}
