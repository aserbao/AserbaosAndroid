package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * 功能:
 * 用法：
 * setContents();输入带表情的输入
 * getContexts();获取除表情之外的内容
 * author aserbao
 * date : On 2018/11/20
 * email: 1142803753@qq.com
 */
public class EmojiCustomEditText extends EditText {
    private Context mContext;
    private Drawable mEmojiDrawable;

    public EmojiCustomEditText(Context context) {
        super(context);
        init(context);
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    public boolean onPreDraw() {
        boolean b = super.onPreDraw();
        int selectionStart = getSelectionStart();
        if (isEmoji && (selectionStart == 1 || selectionStart == 2)){
           setSelection(3);
        }
        return b;
    }

    private void init(Context context) {
        mContext = context;
        initListener();
    }

    private boolean isEmoji = false;
    private static final String TAG = "EmojiCustomEditText";
    private void initListener() {
        setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_DEL){
                    String trim = getText().toString().trim();
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
                        if (trim.startsWith("@") && isEmoji){
                            if (getSelectionStart() == 3){
                                setSelection(0,3);
                                isEmoji = false;
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    public void setContents(String url, final String content){
        Glide.with(mContext)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mEmojiDrawable = resource;
                        if (mEmojiDrawable != null) {
                            String inputS = "";
                            if (isEmoji) {
                                String s = getText().toString().trim();
                                inputS = "@  " + s.substring(3).trim();;
                            }else{
                                inputS = "@  " + content;
                            }
                            SpannableString imageSpan = new SpannableString(inputS);
                            mEmojiDrawable.setBounds(0, 0, 50, 50);
                            imageSpan.setSpan(new ImageSpan(mEmojiDrawable), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                            setText(imageSpan);
                            setSelection(getText().toString().trim().length());
                            isEmoji = true;
                        }
                    }
                });
    }

    public String getContents(){
        String result = "";
        if (isEmoji){
            String s = getText().toString().trim();
            result = s.substring(3).trim();
        }else{
            result = getText().toString().trim();
        }
        return result;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        isEmoji = false;
    }
}
