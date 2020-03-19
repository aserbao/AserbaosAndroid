package com.aserbao.aserbaosandroid.ui.texts.textViews.customTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 功能:
 * author aserbao
 * date : On 2018/11/14
 * email: this is empty email
 */
public class RequestSoftInput extends EditText {
    public RequestSoftInput(Context context) {
        this(context, null);
    }

    public RequestSoftInput(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RequestSoftInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setTextSize(36);
        paint.setColor(Color.GREEN);
        //只有下面两个方法设置为true才能获取到输入的内容
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        outAttrs.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI;
        outAttrs.inputType = InputType.TYPE_NULL;
        return new MyInputConnection(this, true);
    }

    private String nowString;
    private String inputString;
    private Paint paint;
    private float startX;
    private float startY;

    class MyInputConnection extends BaseInputConnection {
        public MyInputConnection(View targetView, boolean fullEditor) {
            super(targetView, fullEditor);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            //note:获取到输入的字符
            Log.d("tag", "commitText:" + text + "\t" + newCursorPosition);
            if (TextUtils.isEmpty(nowString)) {
                nowString = text.toString();
            } else {
                inputString = text.toString();
                nowString = nowString + inputString;
            }
            postInvalidate();
            return true;
        }

        ////有文本输入，当然也有按键输入，也别注意的是有些输入法输入数字并非用commitText方法传递，而是用按键来代替，比如KeyCode_1是代表1等。note：这里我只做了删除，回车按键的处理，由于会触发动作按下和松开两次，所以在这里只做了按下的处理。
        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            /** 当手指离开的按键的时候 */
            Log.d("tag", "sendKeyEvent:KeyCode=" + event.getKeyCode());
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    //删除按键
                    if (nowString.length() > 0) {
                        nowString = nowString.substring(0, nowString.length() - 1);
                    }
                } else if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    //回车按键
                    nowString = nowString + "\n" + inputString;
                }
            }
            postInvalidate();
            return true;
        }

        //当然删除的时候也会触发
        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            Log.d("tag", "deleteSurroundingText " + "beforeLength=" + beforeLength + " afterLength=" + afterLength);
            return true;
        }

        @Override
        public boolean finishComposingText() {
            //结束组合文本输入的时候，这个方法基本上会出现在切换输入法类型，点击回车（完成、搜索、发送、下一步）点击输入法右上角隐藏按钮会触发。
            Log.d("tag", "finishComposingText");
            return true;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!TextUtils.isEmpty(nowString)) {
            canvas.drawText(nowString, startX, startY, paint);
        }

        canvas.drawLine(startX, startY + 18, startX, startY - 18, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            startX = event.getX();
            startY = event.getY();
            InputMethodManager m = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            Log.e("tag","**************************");
        }
        return super.onTouchEvent(event);
    }

}
