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
import com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao;
import com.aserbao.aserbaosandroid.functions.database.base.rv.adapters.DataBaseAdapter;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends DataBaseBaseActivity {
    private static final String TAG = "GreenDaoActivity";
    private DaoSession daoSession;


    @Override
    public void initDatabase() {
        daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        ThingDao thingDao = daoSession.getThingDao();
        things = thingDao.loadAll();
    }

    @Override
    public void insertData(Thing s) {
        if(s == null ) {
            for (int i = 0; i < 1000; i++) {
                String content = "第" + i + "个数据";
                Thing thing = new Thing(content, System.currentTimeMillis() - 60 * 1000 * i);
                daoSession.insertOrReplace(thing);
            }
        }else{
            daoSession.insertOrReplace(s);
        }
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void updataData(Thing s) {
        daoSession.update(s);
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void deleteData(Thing s) {
        daoSession.delete(s);
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void deleteAll() {
        daoSession.deleteAll(Thing.class);
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void queryData(String s) {
        List<Thing> things = daoSession.queryRaw(Thing.class, " where id = ?", s);
        mDataBaseAdapter.addNewThingData(things);
    }

    public List queryAll(){
        List<Thing> things = daoSession.loadAll(Thing.class);
        return things;
    }
    public List quetyList(String message){
        QueryBuilder<Thing> qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list = qb.list(); // 查出所有的数据
        QueryBuilder<Thing> thingQueryBuilder = qb.where(ThingDao.Properties.Message.gt(message)).orderAsc(ThingDao.Properties.Message);
        List<Thing> thingList = thingQueryBuilder.list(); //查出当前对应message的数据
        return list;
    }




}
