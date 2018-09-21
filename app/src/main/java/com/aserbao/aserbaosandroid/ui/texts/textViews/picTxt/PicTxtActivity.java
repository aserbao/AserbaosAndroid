package com.aserbao.aserbaosandroid.ui.texts.textViews.picTxt;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SuggestionSpan;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.BlurMaskFilter.Blur.OUTER;

public class PicTxtActivity extends AppCompatActivity {

    @BindView(R.id.one_pictxt_tv)
    TextView mOnePictxtTv;
    @BindView(R.id.three_pictxt_tv)
    TextView mThreePictxtTv;
    @BindView(R.id.foregroundColor_tv)
    TextView mForegroundTv;
    @BindView(R.id.backgroundColor_span_tv)
    TextView mBackgroundSpanTv;
    @BindView(R.id.clickable_span_tv)
    TextView mClickableSpanTv;
    @BindView(R.id.maskFilter_span_tv)
    TextView mMaskFilterSpanTv;
    @BindView(R.id.rasterizer_span_tv)
    TextView mRasterizerSpanTv;
    @BindView(R.id.strikethrough_span_tv)
    TextView mStrikethroughSpanTv;
    @BindView(R.id.suggestion_span_et)
    EditText mSuggestionSpanEv;
    @BindView(R.id.absolute_span_tv)
    TextView mAbsoluteSpanTv;
    @BindView(R.id.dynamicDrawable_span_tv)
    TextView mDynamicDrawableSpanTv;
    @BindView(R.id.iamge_span_tv)
    TextView mIamgeSpanTv;
    @BindView(R.id.relativeSize_span_tv)
    TextView mRelativeSizeSpanTv;
    @BindView(R.id.scaleX_span_tv)
    TextView mScaleXSpanTv;
    @BindView(R.id.subscript_span_tv)
    TextView mSubscriptSpanTv;
    @BindView(R.id.superscript_span_tv)
    TextView mSuperscriptSpanTv;
    @BindView(R.id.textAppearance_span_tv)
    TextView mTextAppearanceSpanTv;
    @BindView(R.id.typeface_span_tv)
    TextView mTypefaceSpanTv;
    @BindView(R.id.url_span_tv)
    TextView mUrlSpanTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_txt);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initPassSpannable();
    }

    /**
     * spanFlag:
     * SPAN_INCLUSIVE_EXCLUSIVE 前面包括，后面不包括
     * SPAN_INCLUSIVE_INCLUSIVE 前面包括，后面包括
     * SPAN_EXCLUSIVE_EXCLUSIVE 前面不包括，后面不包括
     * SPAN_EXCLUSIVE_INCLUSIVE 前面不包括，后面包括
     */
    private void initPassSpannable() {
        String myString = "aserbao的学习总结，欢迎批评指正,戳这里！";
        int start = 0;
        int end = myString.length();
        int spanFlag = Spannable.SPAN_INCLUSIVE_INCLUSIVE; // this is what is changing

        SpannableString spannableString = new SpannableString(myString);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(foregroundSpan, 2, 7, spanFlag);
        mForegroundTv.setText(spannableString);

        spannableString = new SpannableString(myString);
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.BLUE);
        spannableString.setSpan(backgroundColorSpan, 0, myString.length(), spanFlag);
        mBackgroundSpanTv.setText(spannableString);

        spannableString = new SpannableString(myString);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(PicTxtActivity.this, "我被点击了怎么办？", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan,end - 4 ,myString.length(),spanFlag);
        mClickableSpanTv.setText(spannableString);
        mClickableSpanTv.setMovementMethod(LinkMovementMethod.getInstance());



        spannableString = new SpannableString(myString);
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(5, OUTER));
        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(new float[]{1,1,3}, 1.5f, 8, 3);
        spannableString.setSpan(maskFilterSpan,start + 2 ,end/2,spanFlag);
        spannableString.setSpan(embossMaskFilter,end/2,end - 2,spanFlag);
        mMaskFilterSpanTv.setText(spannableString);


        spannableString = new SpannableString(myString);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan,start/2 ,end/2,spanFlag);
        mStrikethroughSpanTv.setText(spannableString);


        /*spannableString = new SpannableString(myString);
        new SuggestionSpan(this,)*/

        //不能共用Span，否则只有最后一个生效/////////非常重要
        SuggestionSpan ss1 = new SuggestionSpan(this, new String[]{"123", "456", "789"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss1.setFlags(SuggestionSpan.FLAG_AUTO_CORRECTION);//蓝色 ///////////
        SuggestionSpan ss2 = new SuggestionSpan(this, new String[]{"hello", "hi", "bye"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss2.setFlags(SuggestionSpan.FLAG_EASY_CORRECT);//灰色
        SuggestionSpan ss3 = new SuggestionSpan(this, new String[]{"你好", "再见", "谢谢"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss3.setFlags(SuggestionSpan.FLAG_MISSPELLED);//无色

        SpannableString str = new SpannableString("我们是一家人 对不对 是不是");

        str.setSpan(ss1, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);//只有最后一个生效
        str.setSpan(ss2, 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
        str.setSpan(ss3, 11, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);

        EditorInfo editorInfo = new EditorInfo();
        editorInfo.packageName = getPackageName();
        editorInfo.fieldId = mSuggestionSpanEv.getId();
        InputConnection inputConnection = mSuggestionSpanEv.onCreateInputConnection(editorInfo);
        inputConnection.commitText(str, 1);

        mClickableSpanTv.setText(spannableString);
        spannableString = new SpannableString(myString);
        mClickableSpanTv.setText(spannableString);
        spannableString = new SpannableString(myString);
        mClickableSpanTv.setText(spannableString);
    }
}
