package com.aserbao.aserbaosandroid.aaSource.android.material.button;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aserbao.aserbaosandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialButtonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_button);
        ButterKnife.bind(this);
    }



    @OnClick({R.id.material_btn, R.id.material_iv, R.id.material_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.material_btn:
                break;
            case R.id.material_iv:
                break;
            case R.id.material_tv:
                break;
        }
        Snackbar.make(view, "点我做什么？", Snackbar.LENGTH_SHORT).show();
    }
}
