package com.aserbao.aserbaosandroid.ui.texts.textViews.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.customTextView.EditTestMultiLine;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleTextViewActivity extends AppCompatActivity {

    @BindView(R.id.multi_line_edit_text)
    EditTestMultiLine mMultiLineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text_view);
        ButterKnife.bind(this);
        mMultiLineEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputMethod(mMultiLineEditText);
            }
        });
    }

    private void showInputMethod(EditText editTextMessage) {
        /*editTextMessage.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextMessage, 0);
*/
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//这里给它设置了弹出的时间，
        imm.toggleSoftInput(100, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
