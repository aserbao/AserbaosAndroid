package com.aserbao.aserbaosandroid.functions.database.greenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoActivity";

    @BindView(R.id.a_green_dao_submit_et)
    EditText mAGreenDaoSubmitEt;
    @BindView(R.id.a_green_dao_search_et)
    EditText mAGreenDaoSearchEt;
    @BindView(R.id.a_green_dao_recycler_view)
    RecyclerView mAGreenDaoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {

    }


    @OnClick({R.id.a_green_dao_submit_btn, R.id.a_green_dao_search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_green_dao_submit_btn:
                long time = new Date().getTime();
                long l = System.currentTimeMillis();
                Log.e(TAG, "onViewClicked:  time " + time  + " \n System  = " + l );
                break;
            case R.id.a_green_dao_search_btn:
                break;
        }
    }
}
