package com.aserbao.aserbaosandroid.functions.database.greenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao;
import com.aserbao.aserbaosandroid.functions.database.greenDao.rv.adapters.GreenDaoAdapter;

import java.util.Date;
import java.util.List;

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
    private GreenDaoAdapter daoAdapter;

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
        ThingDao thingDao = ((AserbaoApplication) getApplication()).getDaoSession().getThingDao();
        List<Thing> things = thingDao.loadAll();

        daoAdapter = new GreenDaoAdapter(things,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAGreenDaoRecyclerView.setLayoutManager(linearLayoutManager);
        mAGreenDaoRecyclerView.setAdapter(daoAdapter);
    }


    @OnClick({R.id.a_green_dao_submit_btn, R.id.a_green_dao_search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_green_dao_submit_btn:
                for (int i = 0; i < 1000; i++) {
                    String content = "第" + i + "个数据";
                    Thing thing = new Thing(content, System.currentTimeMillis() - 60 * 1000 * i);
                    ((AserbaoApplication) getApplication()).getDaoSession().getThingDao().insert(thing);
                }
                daoAdapter.refreshData();
                break;
            case R.id.a_green_dao_search_btn:
                ((AserbaoApplication) getApplication()).getDaoSession().getThingDao().deleteAll();
                daoAdapter.refreshData();
                break;
        }
    }
}
