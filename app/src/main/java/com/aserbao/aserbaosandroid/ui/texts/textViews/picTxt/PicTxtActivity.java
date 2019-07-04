package com.aserbao.aserbaosandroid.ui.texts.textViews.picTxt;

import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.HtmlTextActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.blurMaskFilter_span_outer_tv)
    TextView mBlurMaskFilterSpanOuterTv;
    @BindView(R.id.blurMaskFilter_span_inner_tv)
    TextView mBlurMaskFilterSpanInnerTv;
    @BindView(R.id.blurMaskFilter_span_solid_tv)
    TextView mBlurMaskFilterSpanSolidTv;
    @BindView(R.id.blurMaskFilter_span_normal_tv)
    TextView mBlurMaskFilterSpanNormalTv;
    @BindView(R.id.embossmask_span_tv)
    TextView mEmbossMaskSpanTv;
    @BindView(R.id.underLine_span_tv)
    TextView mUnderLineSpanTv;
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
    @BindView(R.id.styleSpan_span_tv)
    TextView mStyleSpanTv;
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
        mThreePictxtTv.setText(Html.fromHtml("点击我，带你到HtmlTextActivity中去<font color= '#ff0000'>textView通过Html实现图文混排</font> 点击这一段"));
        mThreePictxtTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicTxtActivity.this, HtmlTextActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * spanFlag:
     * SPAN_INCLUSIVE_EXCLUSIVE 前面包括，后面不包括
     * SPAN_INCLUSIVE_INCLUSIVE 前面包括，后面包括
     * SPAN_EXCLUSIVE_EXCLUSIVE 前面不包括，后面不包括
     * SPAN_EXCLUSIVE_INCLUSIVE 前面不包括，后面包括
     */
    private void initPassSpannable() {
        String myString = "aserbao的学习总结，欢迎批评指正！";
        int start = 0;
        int end = myString.length();
        int spanFlag = Spannable.SPAN_INCLUSIVE_INCLUSIVE; // this is what is changing

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(PicTxtActivity.this, "我被点击了怎么办？", Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString dynamicDrawableSpan = new SpannableString("DynamicDrawableSpan第二个图片可以点击");
        DynamicDrawableSpan drawableSpan =
            new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BASELINE) {
                @Override
                public Drawable getDrawable() {
                    Drawable d = getResources().getDrawable(R.drawable.emoji_00);
                    d.setBounds(0, 0, 150, 150);
                    return d;
                }
            };
        DynamicDrawableSpan drawableSpan2 = new DynamicDrawableSpan(
            DynamicDrawableSpan.ALIGN_BOTTOM) {
            @Override
            public Drawable getDrawable() {
                Drawable d = getResources().getDrawable(R.drawable.emoji_01);
                d.setBounds(0, 0, 150, 150);
                return d;
            }
        };
        dynamicDrawableSpan.setSpan(drawableSpan, 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        dynamicDrawableSpan.setSpan(drawableSpan2, 7, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        dynamicDrawableSpan.setSpan(clickableSpan, 7, 8, spanFlag);
        mDynamicDrawableSpanTv.setText(dynamicDrawableSpan);
        mDynamicDrawableSpanTv.setMovementMethod(LinkMovementMethod.getInstance());


        SpannableString spannableString = new SpannableString(myString);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(foregroundSpan, 2, 7, spanFlag);
        mForegroundTv.setText(spannableString);

        spannableString = new SpannableString("BackgroundColorSpan");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.BLUE);
        spannableString.setSpan(backgroundColorSpan, 0, spannableString.length(), spanFlag);
        mBackgroundSpanTv.setText(spannableString);

        SpannableString spannableString1 = new SpannableString("ClickableSpan,点击我");

        spannableString1.setSpan(clickableSpan, 0, spannableString1.length(), spanFlag);
        mClickableSpanTv.setText(spannableString1);
        mClickableSpanTv.setMovementMethod(LinkMovementMethod.getInstance());


        //使用MaskFilterSpan注意需要修改字体大小
        SpannableString spannableMaskString = new SpannableString("BlurMaskFilter.Blur.OUTER");
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(8, BlurMaskFilter.Blur.OUTER));
        spannableMaskString.setSpan(maskFilterSpan, 0, spannableMaskString.length(), Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        mBlurMaskFilterSpanOuterTv.setText(spannableMaskString);

        SpannableString spannableMaskInnerString = new SpannableString("BlurMaskFilter.Blur.INNER");
        MaskFilterSpan maskFilterInnerSpan = new MaskFilterSpan(new BlurMaskFilter(8, BlurMaskFilter.Blur.INNER));
        spannableMaskInnerString.setSpan(maskFilterInnerSpan, 0, spannableMaskInnerString.length(), Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        mBlurMaskFilterSpanInnerTv.setText(spannableMaskInnerString);

        SpannableString spannableMaskSolidString = new SpannableString("BlurMaskFilter.Blur.SOLID");
        MaskFilterSpan maskFilterSolidSpan = new MaskFilterSpan(new BlurMaskFilter(8, BlurMaskFilter.Blur.SOLID));
        spannableMaskSolidString.setSpan(maskFilterSolidSpan, 0, spannableMaskSolidString.length(), Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        mBlurMaskFilterSpanSolidTv.setText(spannableMaskSolidString);

        SpannableString spannableMaskNormalString = new SpannableString("BlurMaskFilter.Blur.NORMAL");
        MaskFilterSpan maskFilterNormalSpan = new MaskFilterSpan(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        spannableMaskNormalString.setSpan(maskFilterNormalSpan, 0, spannableMaskNormalString.length(), Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        mBlurMaskFilterSpanNormalTv.setText(spannableMaskNormalString);


        SpannableString embossMaskFilter = new SpannableString("EmbossMaskFilter");
        embossMaskFilter.setSpan(new EmbossMaskFilter(new float[]{100,100,10},0.5f,10,50f),0,6,spanFlag);
        mEmbossMaskSpanTv.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mEmbossMaskSpanTv.setText(embossMaskFilter);

        SpannableString spannableStrikethroughString = new SpannableString("StrikethroughSpan");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableStrikethroughString.setSpan(strikethroughSpan,0 ,spannableStrikethroughString.length(),spanFlag);
        mStrikethroughSpanTv.setText(spannableStrikethroughString);


      //不能共用SuggestionSpan，否则只有最后一个生效/////////非常重要
        SuggestionSpan ss1 = new SuggestionSpan(this, new String[]{"我是", "aserbao"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss1.setFlags(SuggestionSpan.FLAG_AUTO_CORRECTION);//蓝色 ///////////
        SuggestionSpan ss2 = new SuggestionSpan(this, new String[]{"和大家一块", "学习", "Android 开发"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss2.setFlags(SuggestionSpan.FLAG_EASY_CORRECT);//灰色
        SuggestionSpan ss3 = new SuggestionSpan(this, new String[]{"希望能结识", "更多志同道合的伙伴"},SuggestionSpan.FLAG_AUTO_CORRECTION);
        ss3.setFlags(SuggestionSpan.FLAG_MISSPELLED);//无色
        SpannableString str = new SpannableString("SuggestionSpan 的使用 双击点击替换");
        str.setSpan(ss1, 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
        str.setSpan(ss2, 16, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
        str.setSpan(ss3, 20, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
        EditorInfo editorInfo = new EditorInfo();
        editorInfo.packageName = getPackageName();
        editorInfo.fieldId = mSuggestionSpanEv.getId();
        InputConnection inputConnection = mSuggestionSpanEv.onCreateInputConnection(editorInfo);
        inputConnection.commitText(str, 1);

        SpannableString underLineSapn = new SpannableString("UnderLineSapn");
        underLineSapn.setSpan(new UnderlineSpan(),0,underLineSapn.length(),spanFlag);
        mUnderLineSpanTv.setText(underLineSapn);

        SpannableString absoluteSizeSpan = new SpannableString("AbsoluteSizeSpan");
        absoluteSizeSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.sp2px(this,25)),0,absoluteSizeSpan.length(),spanFlag);
        mAbsoluteSpanTv.setText(absoluteSizeSpan);





        SpannableString imageSpan = new SpannableString("ImageSpan");
        Drawable d = getResources().getDrawable(R.drawable.emoji_02);
        d.setBounds(0, 0, 150, 150);
        imageSpan.setSpan(new ImageSpan(d), 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mIamgeSpanTv.setText(imageSpan);


        SpannableString relativeSizeSpan = new SpannableString("RelativeSizeSpan");
        relativeSizeSpan.setSpan(new RelativeSizeSpan(2),0,absoluteSizeSpan.length(),spanFlag);
        mRelativeSizeSpanTv.setText(relativeSizeSpan);

//        值> 1.0将使文本变宽。,值< 1.0将使文本变窄。
        SpannableString scaleXSpan = new SpannableString("ScaleXSpan");
        scaleXSpan.setSpan(new ScaleXSpan(2.7f),0,scaleXSpan.length(),spanFlag);
        mScaleXSpanTv.setText(scaleXSpan);


        SpannableString styleSpan = new SpannableString("StyleSpan BOLD BOLD_ITALIC ITALIC");
        styleSpan.setSpan(new StyleSpan(Typeface.BOLD),10,14,spanFlag);
        styleSpan.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),15,26,spanFlag);
        styleSpan.setSpan(new StyleSpan(Typeface.ITALIC),27,33,spanFlag);
        mStyleSpanTv.setText(styleSpan);

        SpannableString subscriptSpan = new SpannableString("Subscript34Span");
        subscriptSpan.setSpan(new SubscriptSpan(),9,11,spanFlag);
        mSubscriptSpanTv.setText(subscriptSpan);

        SpannableString superScriptSpan = new SpannableString("Super23ScriptSpan");
        superScriptSpan.setSpan(new SuperscriptSpan(),5,7,spanFlag);
        mSuperscriptSpanTv.setText(superScriptSpan);

        SpannableString textAppearanceSpan = new SpannableString("TextAppearanceSpan");
        textAppearanceSpan.setSpan(new TextAppearanceSpan(this,R.style.TextAppearance_AppCompat_Medium),4,14,spanFlag);
        mTextAppearanceSpanTv.setText(textAppearanceSpan);


        SpannableString typeFaceSpan = new SpannableString("TypeFaceSpan");
        typeFaceSpan.setSpan(new TypefaceSpan("monospace"),4,5,spanFlag);
        mTypefaceSpanTv.setText(typeFaceSpan);

        SpannableString urlSpan = new SpannableString("UrlSpan - aserbao的csdn");
        urlSpan.setSpan(new URLSpan("http://blog.csdn.net/qq_32175491"),10,urlSpan.length(),spanFlag);
        mUrlSpanTv.setText(urlSpan);
        mUrlSpanTv.setMovementMethod(new LinkMovementMethod());

    }
}
