package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids.HexagonalGridView;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids_offical.Hex;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RandomAndNoOverLayActivity extends AppCompatActivity {

    @BindView(R.id.random_and_no_over_lay)
    RandomAndNoOverLay mRandomAndNoOverLay;
    @BindView(R.id.hexagonal_grid_view)
    HexagonalGridView mHexagonalGridView;
    @BindView(R.id.input_num_edit_text)
    EditText mInputNumEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_and_no_over_lay);
        ButterKnife.bind(this);
    }

    public void btn_click(View view) {
    }

    private static final String TAG = "RandomAndNoOverLayActiv";


    @OnClick({R.id.no_overlap_btn, R.id.screen_random_no_overlay_btn})
    public void onViewClicked(View view) {
        String s = mInputNumEditText.getText().toString();
        int ballNum = 10;
        if (!TextUtils.isEmpty(s)){
            ballNum = Integer.parseInt(s.trim());
        }
        closeKeybord(mInputNumEditText,RandomAndNoOverLayActivity.this);
        switch (view.getId()) {
            case R.id.no_overlap_btn:
                mHexagonalGridView.start(ballNum,false);
                break;
            case R.id.screen_random_no_overlay_btn:
                mHexagonalGridView.start(ballNum,true);
                break;
        }
    }

    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
