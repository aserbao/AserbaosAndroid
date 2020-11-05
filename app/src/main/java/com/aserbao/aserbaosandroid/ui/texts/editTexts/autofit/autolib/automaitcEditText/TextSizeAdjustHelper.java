package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText;

import android.graphics.Paint;
import android.util.Log;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.spandata.CustomSpanData;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.UNIT_PX;


/**
 */
public class TextSizeAdjustHelper {
    /**
     * 高度允许的误差值
     */
    private static final int MIN_HEIGHT_GAP_DP = 5;

    private static final float RATE_SCALE_ERROR_VALUE = 0.001f;

    private TextView mHost;
    private int mHeightGap;
    private List<CustomSpanData> mCustomTextSpanDataList;
    private List<Float> mOriFontSizePxList;

    public TextSizeAdjustHelper(TextView host) {
        mHost = host;
        mHeightGap = SizeUtils.dp2px(host.getContext(),MIN_HEIGHT_GAP_DP);
    }

    public void calculateMatchHeightSize(List<CustomSpanData> customTextSpanDataList, int maxHeight){
        if(customTextSpanDataList == null){
            Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSize: error: customTextSpanDataList is null");
            return;
        }
        mCustomTextSpanDataList = customTextSpanDataList;
        if(mOriFontSizePxList == null){
            mOriFontSizePxList = new ArrayList<>();
        }
        for(CustomSpanData customTextSpanData: customTextSpanDataList){
            mOriFontSizePxList.add(customTextSpanData.getTextSize());
        }

        int textHeight = getTextHeight();
        Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSize: textHeight="+textHeight+" ,maxHeight="+maxHeight);
        if(textHeight > maxHeight){
            //文本高度大于最大高度则需要等比例缩小各行字体大小，直到文本总高度小于最大高度
            int minHeight =maxHeight - mHeightGap;
            Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSize: minHeight="+minHeight+" ,maxHeight="+maxHeight);
            calculateMatchHeightSizeByRate(0,1,minHeight,maxHeight);
        }
        reset();
    }

    /**
     * 二分法查找合适的字体大小，字体大小按比例调整
     * @return
     */
    private void calculateMatchHeightSizeByRate(float lowRate,float highRate,int minHeight,int maxHeight){
        Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSizeByRate: lowRate="+lowRate+" ,highRate="+highRate);
        if(highRate - lowRate <= RATE_SCALE_ERROR_VALUE){
            Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSizeByRate: rate return");
            return;
        }
        float middleRate= (lowRate+highRate)/2;
        scaleFontSizeByRate(middleRate);
        int height = getTextHeight();
        Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSizeByRate: height="+height);
        if(height > maxHeight){
            //缩小字体后文字高度大于最大值，需要继续缩小字体
            highRate = middleRate;
            calculateMatchHeightSizeByRate(lowRate,highRate,minHeight,maxHeight);
        } else if(height < minHeight){
            //缩小字体后文字高度小于最小值，需要放大字体
            lowRate = middleRate;
            calculateMatchHeightSizeByRate(lowRate,highRate,minHeight,maxHeight);
        }else{
            Log.d("hyh", "TextSizeAdjustHelper: calculateMatchHeightSizeByRate: height return");
        }
    }

    private int getTextHeight(){
        int totalHeight = 0;
        for(CustomSpanData customSpanData: mCustomTextSpanDataList){
            int lineHeight = getSingleLineHeight(customSpanData.getTextSize());
            totalHeight += lineHeight;
        }
        return totalHeight;
    }

    private int getSingleLineHeight(float fontSize){
        Paint paint = new Paint(mHost.getPaint());
        paint.setTextSize(fontSize);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.bottom - fontMetrics.top);
    }

    private void scaleFontSizeByRate(float rate){
        Log.d("hyh", "TextSizeAdjustHelper: scaleFontSizeByRate: rate="+rate);
        for(int i=0;i<mOriFontSizePxList.size();i++){
            float fontSize = mOriFontSizePxList.get(i) * rate;
            mCustomTextSpanDataList.get(i).setTextSize(UNIT_PX,fontSize);
        }
    }

    private void reset(){
        mOriFontSizePxList.clear();
        mCustomTextSpanDataList.clear();
        mCustomTextSpanDataList = null;
    }

    public float calculateMatchWidthSize(Paint paint, String text, int maxWidth){
        float textSize = paint.getTextSize();
        float width = paint.measureText(text);
        Log.d("hyh", "TextSizeAdjustHelper: calculateMatchWidthSize: width="+width+" ,maxWidth="+maxWidth);
        if(maxWidth >= width && maxWidth - width <= text.length()){
            Log.d("hyh", "TextSizeAdjustHelper: calculateMatchWidthSize: return 1 textSize="+textSize);
            return textSize;
        }

        if(width > maxWidth){
            textSize = getNarrowFitTextSize(paint,text,maxWidth,1);
        }else{
            textSize = getZoomFitTextSize(paint,text,maxWidth,1);
        }
        Log.d("hyh", "TextSizeAdjustHelper: calculateMatchWidthSize: return 2 textSize="+textSize);
        return textSize;
    }

    private float getNarrowFitTextSize(Paint paint, String text, int maxWidth, float rate){
        float textSize = paint.getTextSize();
        textSize -= 1 * rate;
        paint.setTextSize(textSize);
        float width = paint.measureText(text);
        Log.d("hyh", "TextSizeAdjustHelper: getNarrowFitTextSize: width="+width);
        if(maxWidth >= width && maxWidth - width <= text.length()){
            return textSize;
        }
        //结束条件
        if(width < maxWidth){
            return getZoomFitTextSize(paint,text,maxWidth,rate);
        }else{
            return getNarrowFitTextSize(paint,text,maxWidth,rate);
        }
    }

    private float getZoomFitTextSize(Paint paint, String text, int maxWidth, float rate){
        float textSize = paint.getTextSize();
        textSize += 1 * rate;
        paint.setTextSize(textSize);
        float width = paint.measureText(text);
        Log.d("hyh", "TextSizeAdjustHelper: getZoomFitTextSize: width="+width);
        if(maxWidth >= width && maxWidth - width <= text.length()){
            return textSize;
        }
        //结束条件
        if(width < maxWidth){
            return getZoomFitTextSize(paint,text,maxWidth,rate);
        }else{
            return getNarrowFitTextSize(paint,text,maxWidth,rate);
        }
    }
}
