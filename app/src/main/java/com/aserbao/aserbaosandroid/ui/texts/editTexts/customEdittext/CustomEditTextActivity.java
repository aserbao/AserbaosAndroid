package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomEditTextActivity extends AppCompatActivity {


    @BindView(R.id.test_emoji_edit_text)
    EmojiCustomEditText mTestEmojiEditText;
    @BindView(R.id.emoji_custom_btn)
    Button mEmojiCustomBtn;
    @BindView(R.id.lr_edit_tv)
    LineResizeEditText mLrEditTv;
    @BindView(R.id.frame_layout_second)
    FrameLayout mFrameLayoutSecond;
    @BindView(R.id.frame_layout_first)
    FrameLayout mFrameLayoutFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_edit_text);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static final String TAG = "CustomEditTextActivity";

    /*@OnClick({R.id.emoji_custom_btn, R.id.common_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.emoji_custom_btn:
                String trim1 = mTestEmojiEditText.getText().toString().trim();
                mTestEmojiEditText.setContents("https://www.baidu.com/img/bd_logo1.png?where=super", trim1);

                break;
            case R.id.common_btn:
//                mTestEmojiEditText.selectAll();
//                mTestEmojiEditText.setSelection(0,2);
                String contents = mTestEmojiEditText.getContents();
                Log.e("common_btn", "onViewClicked: " + contents);
                break;
        }
    }*/

    private int  width = DisplayUtil.dip2px(300);
    private int height = DisplayUtil.dip2px(500);


    float scale = 1.0f;
    String result = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20";
    @OnClick({R.id.add_btn, R.id.decrease_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_btn:

                width = (int)(width  *  (1+0.05f));
                this.height = (int)(this.height * (1+0.05f));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, this.height);
                mLrEditTv.setLayoutParams(layoutParams);

                scale =  (float) DisplayUtil.dip2px(500)/(float)height;

                Log.e(TAG, "onViewClicked: " +scale);
                if (mLrEditTv != null) {
//                    scale = scale - 0.005f;
                    mLrEditTv.setMatrix(scale);
                }
                mFrameLayoutSecond.removeAllViews();

                mFrameLayoutSecond.addView(mLrEditTv);
                Log.e(TAG, "onViewClicked: mLrEditTv.getHeight() = " + DisplayUtil.px2dp(this,mLrEditTv.getHeight()) + " mLrEditTv.getWidth() = " +  DisplayUtil.px2dp(this,mLrEditTv.getWidth()) + " mFrameLayoutSecond.getHeight() = "+ DisplayUtil.px2dp(this, mFrameLayoutSecond.getHeight()) );
                break;
            case R.id.decrease_btn:
                Log.e(TAG, "onViewClicked: mLrEditTv.getHeight() = " + DisplayUtil.px2dp(this,mLrEditTv.getHeight()) + " mLrEditTv.getWidth() = " +  DisplayUtil.px2dp(this,mLrEditTv.getWidth()) + " mFrameLayoutSecond.getHeight() = "+ DisplayUtil.px2dp(this, mFrameLayoutSecond.getHeight()) );
                break;
        }
    }
}
