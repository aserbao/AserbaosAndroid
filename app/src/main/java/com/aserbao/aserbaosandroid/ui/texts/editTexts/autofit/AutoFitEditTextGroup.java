package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText.AutoEditText;

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

    SeekBar seekBar;
    AutoEditText autoFitEditText;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.auto_fit_edit_text_group, null);
        seekBar= view.findViewById(R.id.autoFitSeekBar);
        autoFitEditText= view.findViewById(R.id.autoFitEditText);
        seekBar.setMax(100);
        seekBar.setProgress(10);
        addView(view);
       /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) autoFitEditText.changeSeekBar(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/
    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }
}
