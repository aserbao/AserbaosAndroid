package com.aserbao.aserbaosandroid.ui.texts.textViews;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.databinding.AVoicePlayFrameLayoutBinding;

/**
 * @Created time:2021/6/11 5:34 PM
 * @author: aserbao
 * @description: 文字播报的TextView
 **/
public class VoicePlayFrameLayout extends FrameLayout {
    private static final String TAG = "VoicePlayFrameLayout";
    public static final int MAX_NUM = 300;

    private Context mContext;
    private AVoicePlayFrameLayoutBinding binding;

    public VoicePlayFrameLayout(@NonNull Context context) {
        this(context,null);
    }

    public VoicePlayFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VoicePlayFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public VoicePlayFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
        handleLogic();
    }

    private void initView(){
        binding = AVoicePlayFrameLayoutBinding.inflate(LayoutInflater.from(mContext));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        addView(binding.getRoot(),params);
    };

    private void handleLogic(){
        binding.inputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged() called with: s = [" + s + "], start = [" + start + "], count = [" + count + "], after = [" + after + "]");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged() called with: s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]");
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                int inputContentLength = 0;
                String limitResult = "";
                if(s.length() > MAX_NUM){
                    String substring = new StringBuilder(string).substring(0, MAX_NUM);
                    binding.inputEt.setText(substring);
                    binding.inputEt.setSelection(MAX_NUM);
                    binding.textNumTv.setTextColor(Color.parseColor("#F93920"));
                    limitResult = String.valueOf(MAX_NUM) + "/" + String.valueOf(MAX_NUM);
                    inputContentLength = MAX_NUM;
                }else{
                    inputContentLength = binding.inputEt.getText().length();
                    limitResult = inputContentLength + "/" + String.valueOf(MAX_NUM);
                    binding.textNumTv.setTextColor(Color.parseColor("#1c1f23"));
                }
                binding.textNumTv.setText(limitResult);
                if(inputContentLength > 0){
                    binding.textPlayBtn.setBackgroundResource(R.drawable.solid_3f51b5_radius16_bg);
                }else{
                    binding.textPlayBtn.setBackgroundResource(R.drawable.solid_6b7075_radius16_bg);
                }
                Log.d(TAG, "afterTextChanged: " + s);
            }
        });
    }


    private void refreshTxtView(){
        binding.textNumTv.setText("");
    }

}
