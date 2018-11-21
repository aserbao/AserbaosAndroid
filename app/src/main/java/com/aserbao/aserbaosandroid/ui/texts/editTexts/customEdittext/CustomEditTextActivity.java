package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomEditTextActivity extends AppCompatActivity {


    @BindView(R.id.test_emoji_edit_text)
    EmojiCustomEditText mTestEmojiEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_edit_text);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.emoji_custom_btn,R.id.common_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.emoji_custom_btn:
                String trim1 = mTestEmojiEditText.getText().toString().trim();
                mTestEmojiEditText.setContents("https://www.baidu.com/img/bd_logo1.png?where=super",trim1);
                break;
            case R.id.common_btn:
//                mTestEmojiEditText.selectAll();
//                mTestEmojiEditText.setSelection(0,2);
                String contents = mTestEmojiEditText.getContents();
                Log.e("common_btn", "onViewClicked: " + contents );
                break;
        }
    }

}
