package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @OnClick({R.id.emoji_custom_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.emoji_custom_btn:
                String trim1 = mTestEmojiEditText.getText().toString().trim();
                mTestEmojiEditText.setContents(" ",trim1);
                break;
        }
    }

}
