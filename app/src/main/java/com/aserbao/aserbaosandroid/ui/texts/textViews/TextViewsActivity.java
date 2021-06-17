package com.aserbao.aserbaosandroid.ui.texts.textViews;

import android.view.View;

import com.aserbao.aserbaosandroid.ui.texts.textViews.custom.CustomTextViewActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.font.TextFontActivity;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText.HtmlTextActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.picTxt.PicTxtActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.shadowText.TextShadowActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.simple.SimpleTextViewActivity;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.TextShowAnimationActivity;

public class TextViewsActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("textView的简单使用", SimpleTextViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("textView的阴影效果", TextShadowActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("图文混排", PicTxtActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("TextView显示Html", HtmlTextActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("TextVie文字显示动画", TextShowAnimationActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("竖排的TextView", CustomTextViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("字体设置", TextFontActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("语音播放TextView",0));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                VoicePlayFrameLayout voicePlayFrameLayout = new VoicePlayFrameLayout(this);
                addViewToFrameLayout(voicePlayFrameLayout,true,false,true);
                break;
        }
    }
}
