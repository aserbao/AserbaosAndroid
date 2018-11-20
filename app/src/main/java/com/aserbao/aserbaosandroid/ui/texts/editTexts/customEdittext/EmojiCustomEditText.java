package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/20
 * email: 1142803753@qq.com
 */
public class EmojiCustomEditText extends EditText {
    private BitmapDrawable mEmojiDrawable;

    public EmojiCustomEditText(Context context) {
        super(context);
        init();
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public EmojiCustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
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
                        Log.e(TAG, "onKey: " + trim.length() +" getAction "+ event.getAction() );
                        if (trim.startsWith("@") && isEmoji && trim.length() == 1){
                            setText("");
                            isEmoji = false;
                        }
                    }
                    if(TextUtils.isEmpty(trim)){
                        return true;
                    }
                    if (!TextUtils.isEmpty(trim) && trim.length() <1){
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void setContents(String url, String content){
        /*Glide.with(mContext)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mEmojiDrawable = resource;
                    }
                });*/
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        mEmojiDrawable = new BitmapDrawable(bitmap);
        String inputS = "@ "+ content;
        SpannableString imageSpan = new SpannableString(inputS);
        Drawable d = getResources().getDrawable(R.drawable.emoji_02);
        d.setBounds(0, 0, 50, 50);
        imageSpan.setSpan(new ImageSpan(d), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(imageSpan);
        setSelection(getText().toString().trim().length());
        isEmoji = true;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        isEmoji = false;
    }
}
