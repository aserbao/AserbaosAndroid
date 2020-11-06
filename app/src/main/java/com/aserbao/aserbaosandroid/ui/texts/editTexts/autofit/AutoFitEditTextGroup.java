package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.seekbar.rangeSeekBar.VerticalRangeSeekBar;
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

    VerticalRangeSeekBar seekBar;
    AutoEditText autoFitEditText;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.auto_fit_edit_text_group, null);
        seekBar= view.findViewById(R.id.autoFitSeekBar);
        autoFitEditText= view.findViewById(R.id.autoFitEditText);
        autoFitEditText.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
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

    private void changeSpan(String content){
        SpannableString spannableString=new SpannableString(content);
        BackgroundColorSpan backgroundColorSpan=new BackgroundColorSpan(Color.YELLOW);
        int start = 0;
        int end = content.length();
        spannableString.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ColorSpan colorSpan = new ColorSpan(Color.RED,Color.BLUE);
//        spannableString.setSpan(spannableString,start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        autoFitEditText.setText(spannableString);
    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }
}
