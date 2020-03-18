package com.aserbao.aserbaosandroid.functions.how_create_so.useCmake;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UseCMakeBuildSoActivity extends AppCompatActivity {

    @BindView(R.id.use_cmake_tv)
    TextView mUseCmakeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_cmake_build_so);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mUseCmakeTv.setText(AserbaoUtils.getSimpleInfoFromOne() + " \n" + AserbaoUtils.getSimpleInfoFromTwo());
    }
}
