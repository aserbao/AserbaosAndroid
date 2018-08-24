package com.aserbao.aserbaosandroid.ui.editTexts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.CommonAdapter;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseActivity;
import com.aserbao.aserbaosandroid.opengl.ClassBean;
import com.aserbao.aserbaosandroid.ui.colorPicker.ColorPickerActivity;
import com.aserbao.aserbaosandroid.ui.editTexts.softHeightMeasure.SoftHeightMeasureActivity;
import com.aserbao.aserbaosandroid.ui.rv.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditTextsActivity extends BaseActivity {

    @Override
    public int setContentView() {
        return R.layout.activity_open_gl;
    }

    public void initGetData() {
        mClassBeen.add(new ClassBean("计算软键盘的高度", SoftHeightMeasureActivity.class));

    }


}
