package com.aserbao.aserbaosandroid.functions.database.greenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao;
import com.aserbao.aserbaosandroid.functions.database.base.rv.adapters.DataBaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends DataBaseBaseActivity {
    private static final String TAG = "GreenDaoActivity";


    @Override
    public void insertData(Thing s) {
        for (int i = 0; i < 1000; i++) {
            String content = "第" + i + "个数据";
            Thing thing = new Thing(content, System.currentTimeMillis() - 60 * 1000 * i);
            ((AserbaoApplication) getApplication()).getDaoSession().getThingDao().insert(thing);
        }
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void updataData(Thing s) {

    }

    @Override
    public void deleteData(Thing s) {
        ((AserbaoApplication) getApplication()).getDaoSession().getThingDao().deleteAll();
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void queryData(String s) {

    }

    @Override
    public void initDatabase() {
        ThingDao thingDao = ((AserbaoApplication) getApplication()).getDaoSession().getThingDao();
        List<Thing> things = thingDao.loadAll();
    }



}
