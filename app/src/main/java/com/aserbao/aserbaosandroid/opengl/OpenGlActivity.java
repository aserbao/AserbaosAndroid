package com.aserbao.aserbaosandroid.opengl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenGlActivity extends AppCompatActivity {

    @BindView(R.id.opengl_recycler_view)
    RecyclerView mOpenglRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }
}
