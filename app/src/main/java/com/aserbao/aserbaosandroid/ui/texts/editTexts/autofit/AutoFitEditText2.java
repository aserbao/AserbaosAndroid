package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.base.utils.screen.DisplayUtil;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/11/2
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit
 */
public class AutoFitEditText2 extends AppCompatEditText {

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


    public AutoFitEditText2(Context context) {
        super(context);
        initialise();
    }

    public AutoFitEditText2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public AutoFitEditText2(Context context, AttributeSet attrs, int defStyleAttr) {
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

    private void refitTextHeight() {
        int supportBigSize = DEFAULT_MIN_TEXT_SIZE + (int)(cuurPercent * DEFAULT_RANGE);
        while(true) {
            boolean fillHeight = isFillHeight(DisplayUtil.dip2px(cuurTextSize));
            if (cuurTextSize < supportBigSize) {
                if(fillHeight){
                    cuurTextSize--;
                    if(!isFillHeight(DisplayUtil.dip2px(cuurTextSize))){
                        setTextSize(cuurTextSize);
                    }
                }else {
                    while(true) {
                        cuurTextSize++;
                        if(isFillHeight(DisplayUtil.dip2px(cuurTextSize))){
                            break;
                        }
                    }
                }
            } else if(cuurTextSize > supportBigSize){
                cuurTextSize--;
            } else{
                if(fillHeight)  cuurTextSize--;
            }
            fillHeight = isFillHeight(DisplayUtil.dip2px(cuurTextSize));
            Log.e(TAG, "refitTextHeight: cuurTextSize=" + cuurTextSize  + " fillHeight=" + fillHeight + " supportBigSize= "+ supportBigSize);
            if(!fillHeight){
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
            if(size >= supportMaxSize){
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = SCREEN_WIDTH - (int)(WIDTH_RANGE * (percent - maxPercent));
                setLayoutParams(layoutParams);
            }else{
                cuurTextSize = size;
                refitTextHeight();
            }
        }else{ //往上调
//            getLayoutParams().width = SCREEN_WIDTH - (int)(WIDTH_RANGE * percent);
            if(!isFillHeight(DisplayUtil.dip2px(size))){
                cuurTextSize = size;
                refitTextHeight();
                supportMaxSize = size;
                maxPercent = percent;
            }else{
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = SCREEN_WIDTH - (int)(WIDTH_RANGE * (percent - maxPercent));
                setLayoutParams(layoutParams);
                Log.e(TAG, "changeSize: layoutParams.width="+ layoutParams.width + " percent="+ percent );
            }
        }
        lastTextSize = size;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(isStartChange) refitTextHeight();
        Log.d(TAG, "onLayout() called with: changed = [" + changed + "], left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
    }

    /**
     * 删除文字后的布局变动
     */
    private void changeRemoveText() {
        int supportBigSize = DEFAULT_MIN_TEXT_SIZE + (int)(cuurPercent * DEFAULT_RANGE);
        if(cuurTextSize < supportBigSize ){
            for (int i = (int)cuurTextSize; i < supportBigSize + 1; i++) {
                ++cuurTextSize;
                if(isFillHeight(DisplayUtil.dip2px(cuurTextSize))){
                    refitTextHeight();
                    break;
                }
            }
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
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }


    /**
     *
     * @param sizePx
     * @return
     */
    private boolean isFillHeight(int sizePx){
        boolean result = false;
        int height = getHeight();
        int availableHeight = height - this.getPaddingTop() - this.getPaddingBottom();// 获取改TextView的画布可用大小
        int lineCount = getLineCount()+1;
        int lineHeight = getFontHeight(sizePx);
        int textContentH = lineCount * lineHeight + this.getPaddingTop() + this.getPaddingBottom();
        if(availableHeight > 0){
            result = textContentH >= availableHeight;
        }
        Log.e(TAG, "ATest isFillHeight: result=" +result + " textContentH="+ textContentH+ " availableHeight="+ availableHeight );
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
            refitTextHeight();
        }
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private static final String TAG = "AutoFitEditText";
}
