package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.automaitcEditText;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.aspan.BackgroundCoordinator;
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.spandata.CustomSpanData;

import java.util.ArrayList;
import java.util.List;

import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.SpanType.TYPE_ABS_SIZE_SPAN;
import static com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.autolib.span.TypeConfig.UNIT_PX;

/**
 * 文本自动排版处理器
 */
public class AutoProcessor {
    private static final String SYM_CHANGE_LINE = "\n";
    private EditText mHost;
    private String mLastText = "";
    private boolean mResetWidgetSize;
    private List<LineData> mLineDataList;
    private LayoutHelper mLayoutHelper;
    /**
     * 最大文本高度
     */
    private int mMaxTextHeight;

    public AutoProcessor(@NonNull EditText host) {
        mHost = host;
        mLayoutHelper = new LayoutHelper(mHost);
        mLineDataList = new ArrayList<>();
        //设为false，且不设置行间距，这样每行高度的累加值才等于文本总高度，即Layout.getHeight == Layout.getLineCount * TextPaint.getFontMetricsInt
        mHost.setIncludeFontPadding(false);
        //不设置行间距
        mHost.setLineSpacing(0,1);

        //监听文本变化，在文本发生改变但是还没触发重绘前计算好各行文本和字体大小
        mHost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                refresh();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void refresh(){
        if(mHost.getLayout() != null){
            //这里的Layout不能用EditText的Layout，不然无法正确拿到每行文本，需要构建一个辅助Layout，文本的换行都依据该Layout算出
            Layout calculateLayout = getCalculateLayout();
            String text = calculateLayout.getText().toString();
            boolean update = isUpdateText(calculateLayout,text);
            if(update) {
                mLastText = text;
                spliteLineData(calculateLayout,text);
                matchMaxWidthFontSize();
                matchMaxHeightFontSize();
                updateText(text);
                updateTextSize();
                int maxLineWidth = (int) calculateMaxLineWidth();
                if(maxLineWidth > 0){
                    mResetWidgetSize = true;
                    ViewGroup.LayoutParams layoutParams = mHost.getLayoutParams();
                    layoutParams.width = maxLineWidth + mHost.getPaddingLeft() + mHost.getPaddingRight();
                    Log.d("hyh", "AutomaticEditText: refresh: layoutParams.width="+layoutParams.width);
                    mHost.setLayoutParams(layoutParams);
                }
            }
        }


    }


    /**
     * 判断是否需要更新文本
     * @return
     */
    private boolean isUpdateText(Layout layout, String text){
        boolean update = false;
        if(!mLastText.equals(text)) {
            update = true;
        }else{
            int lineCount = layout.getLineCount();
            int size = mLineDataList.size();
            if(lineCount != size){
                update = true;
            }else{
                for (int i = 0; i < lineCount; i++) {
                    int start = layout.getLineStart(i);
                    int end =layout.getLineEnd(i);
                    String rowStr = text.substring(start, end);
                    //去除掉换行符
                    if(rowStr.contains(SYM_CHANGE_LINE)){
                        rowStr = rowStr.replace(SYM_CHANGE_LINE,"");
                    }
                    LineData lineData = mLineDataList.get(i);
                    String lineText = lineData.getLineText();
                    if (!rowStr.equals(lineText)) {
                        //原本的每行文字跟现在的每行文字不相同，说明排版变了，需要重新更新文本
                        update = true;
                        break;
                    }
                }
            }
        }
        return  update;
    }

    /**
     * 按行分割文本
     */
    private void spliteLineData(Layout layout, String text){
        mLineDataList.clear();
        int lineCount = layout.getLineCount();
        Log.d("hyh", "AutomaticEditText: spliteLineData: text="+text+" ,lineCount="+lineCount);
        for (int i = 0; i < lineCount; i++) {
            int start = layout.getLineStart(i);
            int end = layout.getLineEnd(i);
            String rowStr = text.substring(start,end);
            //去除掉换行符
            if(rowStr.contains(SYM_CHANGE_LINE)){
                rowStr = rowStr.replace(SYM_CHANGE_LINE,"");
            }
            CustomSpanData customTextSpanData = new CustomSpanData.Builder(mHost.getContext(),start,end)
                .setSpanType(TYPE_ABS_SIZE_SPAN)
                .setTextSize(UNIT_PX,mLayoutHelper.getFontSize())
                .build();
            LineData lineData = new LineData(rowStr,customTextSpanData);
            Log.d("hyh", "AutomaticEditText: spliteLineData: lineData="+lineData.toString());
            mLineDataList.add(lineData);
        }
    }

    /**
     * 计算匹配最大文本宽度的字体大小
     */
    private void matchMaxWidthFontSize(){
        for(LineData lineData: mLineDataList){
            String lineText = lineData.getLineText();
            if(!TextUtils.isEmpty(lineText)){
                lineData.setFontSizePx(mLayoutHelper.getMatchWidthFontSize(lineText));
            }else{
                lineData.setFontSizePx(mLayoutHelper.getFontSize());
            }
        }
    }

    /**
     * 计算匹配最大文本高度的字体大小
     */
    private void matchMaxHeightFontSize(){
        List<CustomSpanData> customTextSpanDataList = new ArrayList<>();
        for(LineData lineData: mLineDataList){
            CustomSpanData customTextSpanData = lineData.getCustomTextSpanData();
            customTextSpanDataList.add(customTextSpanData);
        }
        mLayoutHelper.claculateMatchHeightFontSize(customTextSpanDataList, mMaxTextHeight);
    }

    private void updateText(String text){
        SpannableString spannableString = new SpannableString(text);
        for(LineData lineData: mLineDataList){
            Log.d("hyh", "AutomaticEditText: updateText: lineText="+lineData.getLineText()+" ,lineFontSize="+lineData.getFontSizePx());
            CustomSpanData customSpanData = lineData.getCustomTextSpanData();
            spannableString.setSpan(customSpanData.onCreateSpan(),customSpanData.getStartIndex(),customSpanData.getEndIndex(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        BackgroundColorSpan backgroundColorSpan=new BackgroundColorSpan(Color.YELLOW);
//        BackgroundCoordinator backgroundColorSpan=new BackgroundCoordinator();
        int end = text.length();
        spannableString.setSpan(backgroundColorSpan, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start = mHost.getSelectionStart();
        mHost.setText(spannableString);
        mHost.setSelection(start);
    }

    private void updateTextSize(){
        float minFontSize = mLayoutHelper.getFontSize();
        for(LineData lineData: mLineDataList){
            CustomSpanData customSpanData = lineData.getCustomTextSpanData();
            float fontSize = customSpanData.getTextSize();
            if(fontSize < minFontSize){
                minFontSize = fontSize;
            }
        }
        mHost.setTextSize(TypedValue.COMPLEX_UNIT_PX,minFontSize);
    }

    private float calculateMaxLineWidth(){
        Paint paint = mLayoutHelper.copyPaint();
        float maxLineWidth = 0;
        for(LineData lineData: mLineDataList){
            paint.setTextSize(lineData.getFontSizePx());
            float width = paint.measureText(lineData.getLineText());
            if(width > maxLineWidth){
                maxLineWidth = width;
            }
        }
        if(maxLineWidth > mLayoutHelper.getLayoutWidth()){
            maxLineWidth = mLayoutHelper.getLayoutWidth();
        }
        Log.d("hyh", "AutomaticEditText: calculateMaxLineWidth: maxLineWidth="+maxLineWidth);
        return maxLineWidth;
    }

    private Layout getCalculateLayout(){
        //注意这里的text是String不是Spannable
        String textString = mHost.getText().toString();
        return mLayoutHelper.buildCalculateLayout(textString);
    }

    protected void handleSizeChanged(int w, int h, int oldw, int oldh) {
        if((w != oldw || h != oldh) && !mResetWidgetSize){
            mMaxTextHeight = h - mHost.getPaddingTop() - mHost.getPaddingBottom();
            int maxTextWidth = w - mHost.getPaddingLeft() - mHost.getPaddingRight();
            mLayoutHelper.setLayoutWidth(maxTextWidth);
            Log.d("hyh", "AutomaticEditText: onSizeChanged: mMaxTextHeight="+ mMaxTextHeight
                + " ,maxTextWidth="+maxTextWidth);
        }
        if(mResetWidgetSize){
            mResetWidgetSize = false;
        }
    }

    public static class LineData{
        //行文本
        private String mLineText;
        private CustomSpanData mCustomSpanData;

        public LineData(String lineStr, CustomSpanData customSpanData) {
            mLineText = lineStr;
            mCustomSpanData = customSpanData;
        }

        public String getLineText() {
            return mLineText;
        }

        public float getFontSizePx(){
            return mCustomSpanData.getTextSize();
        }

        public void setFontSizePx(float textSizePx){
            mCustomSpanData.setTextSize(UNIT_PX,textSizePx);
        }

        public CustomSpanData getCustomTextSpanData() {
            return mCustomSpanData;
        }

        @Override
        public String toString() {
            return "LineData{" +
                "mLineText='" + mLineText + '\'' +
                ", mStartIndex=" + mCustomSpanData.getStartIndex() +
                ", mEndIndex=" + mCustomSpanData.getEndIndex() +
                '}';
        }
    }
}
