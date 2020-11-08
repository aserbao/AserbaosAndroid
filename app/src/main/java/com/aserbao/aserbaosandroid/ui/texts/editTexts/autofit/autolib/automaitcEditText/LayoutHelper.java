package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText;

import android.graphics.Paint;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.spandata.CustomSpanData;

import java.util.List;

/**
 */
public class LayoutHelper {
    private TextView mHost;
    private float mFontSize;//px
    private int mLayoutWidth;
    private static final String sSeizeText = "111";
    private float mMinWidth;
    private TextSizeAdjustHelper mTextSizeAdjustHelper;

    public LayoutHelper(TextView host) {
        mHost = host;
        mFontSize = host.getTextSize();
        initMinWidth();
        mTextSizeAdjustHelper = new TextSizeAdjustHelper(host);
    }

    private void initMinWidth(){
        Paint paint = copyPaint();
        mMinWidth = paint.measureText(sSeizeText);
    }

    public void setLayoutWidth(int layoutWidth) {
        mLayoutWidth = layoutWidth;
    }

    public int getLayoutWidth() {
        return mLayoutWidth;
    }

    public float getFontSize() {
        return mFontSize;
    }

    /**
     * 这里构建一个Layout，用于辅助计算，不是TextView关联的Layout
     * @param text
     * @return
     */
    protected Layout buildCalculateLayout(CharSequence text){
        TextPaint paint = copyPaint();
        return new DynamicLayout(text,paint, mLayoutWidth,mHost.getLayout().getAlignment(),mHost.getLayout().getSpacingMultiplier(),mHost.getLayout().getSpacingAdd(),mHost.getIncludeFontPadding());
    }

    protected TextPaint copyPaint(){
        TextPaint paint = new TextPaint(mHost.getPaint());
        paint.setTextSize(mFontSize);
        return paint;
    }

    protected float getMatchWidthFontSize(String lineText){
        /*Paint paint = copyPaint();
        float textWidth = paint.measureText(lineText);
        if(textWidth < mMinWidth){
            textWidth = mMinWidth;
            lineText = sSeizeText;
        }
        //按照宽比缩放字体
        float textSize = mLayoutWidth / textWidth * mFontSize;
        paint.setTextSize(textSize);
        //缩放字体后还得检查行宽度是否大于最大文本宽度，如果大于则还需要调小字体
        textSize = mTextSizeAdjustHelper.calculateMatchWidthSize(paint,lineText, mLayoutWidth);*/
        return mFontSize;
    }


    protected float getMatchWidthFontSizeOld(String lineText){
        Paint paint = copyPaint();
        float textWidth = paint.measureText(lineText);
        if(textWidth < mMinWidth){
            textWidth = mMinWidth;
            lineText = sSeizeText;
        }
        //按照宽比缩放字体
        float textSize = mLayoutWidth / textWidth * mFontSize;
        paint.setTextSize(textSize);
        //缩放字体后还得检查行宽度是否大于最大文本宽度，如果大于则还需要调小字体
        textSize = mTextSizeAdjustHelper.calculateMatchWidthSize(paint,lineText, mLayoutWidth);
        return textSize;
    }

    protected void claculateMatchHeightFontSize(List<CustomSpanData> customTextSpanDataList, int height){
        mTextSizeAdjustHelper.calculateMatchHeightSize(customTextSpanDataList,height);
    }

    /**
     * 设置字体
     * @param mFontSize
     */
    public void setFontSize(float mFontSize) {
        this.mFontSize = mFontSize;
    }


}
