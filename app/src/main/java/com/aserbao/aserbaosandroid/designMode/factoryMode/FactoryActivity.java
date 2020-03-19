package com.aserbao.aserbaosandroid.designMode.factoryMode;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FactoryActivity extends AppCompatActivity {

    @BindView(R.id.factory_tv)
    TextView mFactoryTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sichuang, R.id.btn_chongqing, R.id.btn_hunan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sichuang:
                break;
            case R.id.btn_chongqing:
                break;
            case R.id.btn_hunan:
                break;
        }
    }
}
