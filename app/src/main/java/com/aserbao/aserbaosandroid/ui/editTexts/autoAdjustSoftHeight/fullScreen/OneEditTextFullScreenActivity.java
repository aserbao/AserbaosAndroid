package com.aserbao.aserbaosandroid.ui.editTexts.autoAdjustSoftHeight.fullScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.editTexts.utils.AndroidBug5497Workaround;

import butterknife.ButterKnife;

/**
 * 全屏模式下的Activity的软键盘的显示技巧
 */
public class OneEditTextFullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_edit_text_full_screen);
        ButterKnife.bind(this);
        AndroidBug5497Workaround.assistActivity(this);
    }
}
