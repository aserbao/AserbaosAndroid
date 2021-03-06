package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.base.utils.screen.DisplayUtil;

/*
 * 作用：自定义仿Instagram的自动换行的输入框
 * @author aserbao
 * @date: on 2020/11/2
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit
 */
public class AutoFitEditText extends AppCompatEditText {

    private static final int STATUS_ADD_TEXT = 0; // 表示添加文字
    private static final int STATUS_REMOVE_TEXT = 1; // 表示删除文字
    private static final int STATUS_CHANGE_LAYOUT_FOLD = 2; // 表示布局折叠
    private static final int STATUS_CHANGE_LAYOUT_UNFOLD = 3; // 表示布局展开

    private static int DEFAULT_MIN_TEXT_SIZE = 12; // 最小的字体大小
    private static int DEFAULT_MAX_TEXT_SIZE = 112;// 验证大部分手机情况下无效值
    private static int DEFAULT_RANGE = DEFAULT_MAX_TEXT_SIZE - DEFAULT_MIN_TEXT_SIZE;// 验证大部分手机情况下无效值
    private static int DEFAULT_TEXT_SIZE = 22;
    private static int MIN_TEXT_WIDTH = DisplayUtil.dip2px(200f);
    private static int WIDTH_RANGE;
    private static int SCREEN_WIDTH;
    int lastTextSize = 0;
    float maxPercent,cuurPercent = 0.1f;
    int supportMaxSize = DEFAULT_TEXT_SIZE;


    // Attributes
    private Paint testPaint;
    private float minTextSize, maxTextSize,cuurTextSize = DEFAULT_TEXT_SIZE;



    public AutoFitEditText(Context context) {
        super(context);
        initialise();
    }

    public AutoFitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public AutoFitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise();
    }


    private void initialise() {
        WIDTH_RANGE = DisplayUtil.getScreenWidth(getContext()) - MIN_TEXT_WIDTH;
        SCREEN_WIDTH = DisplayUtil.getScreenWidth(getContext());
        testPaint = new Paint();
        testPaint.set(this.getPaint()); // 获取模拟的paint
        setTextSize(cuurTextSize);
        maxTextSize = this.getTextSize();// 获取单个字体的像素
        if (maxTextSize <= DEFAULT_MIN_TEXT_SIZE) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE;
        }
        minTextSize = DEFAULT_MIN_TEXT_SIZE;
    }

    /**
     * 处理不同的状态
     * @param status
     * @see STATUS_ADD_TEXT
     */
    private void refitTextHeight(int status) {
        int supportBigSize = DEFAULT_MIN_TEXT_SIZE + (int)(cuurPercent * DEFAULT_RANGE);
        while(true) {
            boolean fillHeight = isFillHeight(DisplayUtil.dip2px(cuurTextSize));
            switch (status){
                case STATUS_ADD_TEXT:
                case STATUS_CHANGE_LAYOUT_FOLD:
                    if(fillHeight){
                        cuurTextSize --;
                        Log.d(TAG, "--- refitTextHeight: fillHeight=" + fillHeight + " cuurTextSize="+ cuurTextSize+ " STATUS_CHANGE_LAYOUT" + status);
                    }
                    break;
                case STATUS_CHANGE_LAYOUT_UNFOLD:
                case STATUS_REMOVE_TEXT:
                    if(cuurTextSize > supportBigSize) cuurTextSize = supportBigSize;
                    if(cuurTextSize < supportBigSize){
                        for (int i = (int)cuurTextSize; i <= supportBigSize ; i++) {
                            if(isFillHeight(DisplayUtil.dip2px(i))){
                                cuurTextSize = i - 1;
                                Log.d(TAG, "++ refitTextHeight: fillHeight=" + fillHeight + " cuurTextSize="+ cuurTextSize+ " STATUS_CHANGE_LAYOUT" + status);
                                break;
                            }
                            if(i == supportBigSize){
                                cuurTextSize = supportBigSize;
                            }
                        }
                    }
                    break;
            }
            if(!isFillHeight(DisplayUtil.dip2px(cuurTextSize))){
                Log.e(TAG, "setTextSize refitTextHeight: cuurTextSize=" + cuurTextSize  + " fillHeight=" + fillHeight + " supportBigSize= "+ supportBigSize);
                setTextSize(cuurTextSize);
                break;
            }
        }
    }


    /**
     * 外部范围控制的变动
     * @param percent
     */
    public void changeSeekBar(float percent){
        cuurPercent = percent;
        int size = DEFAULT_MIN_TEXT_SIZE + (int)(percent * DEFAULT_RANGE);
        if(lastTextSize > size){ // 往下调
            if(size <= supportMaxSize){
                cuurTextSize = size;
                setTextSize(cuurTextSize);
            }
        }else{ //往上调
            if(!isFillHeight(DisplayUtil.dip2px(size))){
                cuurTextSize = size;
                supportMaxSize = size;
                maxPercent = percent;
                setTextSize(cuurTextSize);
            }
        }

        lastTextSize = size;
    }
    int lastLayoutBottom = 0;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            int status = STATUS_CHANGE_LAYOUT_FOLD;
            if(lastLayoutBottom - bottom < 0)  status= STATUS_CHANGE_LAYOUT_UNFOLD;
            refitTextHeight(status);
            lastLayoutBottom = bottom;
        }
    }

    /**
     * 获取单个字体高度
     * @param fontSize
     * @return
     */
    public int getFontHeight(float fontSize)  {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        float measureText = paint.measureText(getText().toString());
        Paint.FontMetrics fm = paint.getFontMetrics();
        int singleSizeHeight = (int) Math.ceil(fm.descent - fm.ascent);
        float lineCount = measureText / getWidth();
        return singleSizeHeight;
    }


    /**
     * @param sizePx
     * @return
     */
    private boolean isFillHeight(int sizePx){
        boolean result = false;
        int height = getHeight();
        int availableHeight = height - this.getPaddingTop() - this.getPaddingBottom();// 获取改TextView的画布可用大小
        int lineCount = getLineCount() + 1;
        int lineHeight = getFontHeight(sizePx);
        int textContentH = lineCount * lineHeight + this.getPaddingTop() + this.getPaddingBottom();
        if(availableHeight > 0){
            result = textContentH >= availableHeight;
        }
        Log.e(TAG, "ATest isFillHeight: result=" +result +"lineCount = "+ lineCount+ " lineHeight=" + lineHeight+ " textContentH="+ textContentH+ " availableHeight="+ availableHeight + " cuurTextSize =" + cuurTextSize);
        return result;
    }

    boolean isStartChange = false;
    @Override
    protected void onTextChanged(CharSequence text, int start, int before,
                                 int after) {
        super.onTextChanged(text, start, before, after);
        if(before > after){//删除文字
//            changeRemoveText();
        }
        if(before != after) {
            isStartChange = true;
            int status = STATUS_ADD_TEXT;
            //删除文字
            if(before > after) status = STATUS_REMOVE_TEXT;
            refitTextHeight(status);
        }
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private static final String TAG = "AutoFitEditText";
}
