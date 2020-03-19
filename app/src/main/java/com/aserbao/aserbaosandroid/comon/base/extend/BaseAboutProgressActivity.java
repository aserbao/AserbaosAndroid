package com.aserbao.aserbaosandroid.comon.base.extend;

import androidx.annotation.CallSuper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-10-19 19:41
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.comon.base.extend
 */
public abstract class BaseAboutProgressActivity extends BaseRecyclerViewActivity implements View.OnClickListener{
    public static final int MAX_PROGRESS = 100;
    protected int curProgress =0;
    protected TextView mShowProgressTv;
    protected ProgressBar mShowProgress;
    protected Button mProgressStartBtn;
    protected Button mProgressStopBtn;
    protected Button mProgressResetBtn;


    @CallSuper
    @Override
    public void initGetData() {
        View rootView = addLayoutToFrameLayout(R.layout.android_os_handler_progress,true);
        mProgressStartBtn = rootView.findViewById(R.id.progress_start_btn);
        mProgressStopBtn = rootView.findViewById(R.id.progress_pause_btn);
        mProgressResetBtn = rootView.findViewById(R.id.progress_reset_btn);
        mShowProgressTv = rootView.findViewById(R.id.show_progress_tv);
        mShowProgress = rootView.findViewById(R.id.show_pb);
        mProgressStartBtn.setOnClickListener(this);
        mProgressStopBtn.setOnClickListener(this);
        mProgressResetBtn.setOnClickListener(this);
    }

    @CallSuper
    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {}

    @CallSuper
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.progress_start_btn:
                if (curProgress == MAX_PROGRESS) curProgress = 0;
                startDownload();
                break;
            case R.id.progress_pause_btn:
                pauseDownload();
                break;
            case R.id.progress_reset_btn:
                curProgress = 0;
                break;
        }
    }

    protected abstract void startDownload();
    protected abstract void pauseDownload();

    public void updateProgress(int progress){
        mShowProgress.setProgress(progress);
        String result = "下载进度: "+progress + "%";
        if (progress == 100){
            result = "下载完成";
        }
        mShowProgressTv.setText(result);
    }
}
