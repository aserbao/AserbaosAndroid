package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aserbao.aserbaosandroid.R;

/*
 * 作用：仿Instagram的文本编辑
 * @author aserbao
 * @date: on 2020/11/4
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit
 */
public class AutoFitEditTextGroup extends FrameLayout {
    public AutoFitEditTextGroup(@NonNull Context context) {
        super(context);
        initView();
    }

    public AutoFitEditTextGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoFitEditTextGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public AutoFitEditTextGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.auto_fit_edit_text_group, null);
        addView(view);
    }
    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }
}
