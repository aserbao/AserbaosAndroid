package com.aserbao.aserbaosandroid.comon.base.extend;

import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-19 19:41
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.extend
 */
public abstract class BaseAboutProgressActivity extends BaseRecyclerViewActivity {
    protected TextView mShowProgress;
    protected ProgressBar mShowPb;
    protected Button mProgressStartBtn;
    protected Button mProgressStopBtn;
    protected Button mProgressResetBtn;

    @CallSuper
    @Override
    public void initGetData() {
        View rootView = addLayoutToFrameLayout(R.layout.android_os_handler_progress);
        mProgressStartBtn = rootView.findViewById(R.id.progress_start_btn);
        mProgressStopBtn = rootView.findViewById(R.id.progress_stop_btn);
        mProgressResetBtn = rootView.findViewById(R.id.progress_reset_btn);
        mShowProgress = rootView.findViewById(R.id.show_progress);
        mShowPb = rootView.findViewById(R.id.show_pb);
    }
}
