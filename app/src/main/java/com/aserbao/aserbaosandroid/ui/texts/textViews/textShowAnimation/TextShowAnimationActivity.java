package com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.animTextView.FlyTxtView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextShowAnimationActivity extends AppCompatActivity {
    @BindView(R.id.anim_fly_txt_view)
    FlyTxtView mAnimFlyTxtView;

    String text = "学习项目，仅供参考，文字效果显示，文字多一点，看中什么效果直接用！";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_show_animation);
        ButterKnife.bind(this);

    }

    private void init() {
        initFlyTxtView();
    }

    public void initFlyTxtView() {
        mAnimFlyTxtView.setTextSize(20);
        mAnimFlyTxtView.setTextColor(Color.BLACK);
        mAnimFlyTxtView.setTexts(text);
        mAnimFlyTxtView.startAnimation();
    }

    @OnClick(R.id.anim_start_btn)
    public void onViewClicked() {
        init();
    }
}
