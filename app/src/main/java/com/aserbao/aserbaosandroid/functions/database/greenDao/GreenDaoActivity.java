package com.aserbao.aserbaosandroid.functions.database.greenDao;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import static com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao.Properties.Id;
import static com.aserbao.aserbaosandroid.functions.database.greenDao.db.ThingDao.Properties.Message;

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
            for (int i = 0; i < 100; i++) {
                String content = "第" + i + "个数据";
                String name = "第" + i + "个名字";
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
        QueryBuilder<Thing> thingQueryBuilder = qb.where(ThingDao.Properties.Message.like(message)).orderAsc(ThingDao.Properties.Message);
        List<Thing> thingList = thingQueryBuilder.list(); //查出当前对应message的数据

        //查询Message值为message时，按Name值排序的结果
        qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list1 = qb.where(ThingDao.Properties.Message.eq(message)).orderAsc(ThingDao.Properties.Name).list();

        //嵌套查询： 查询Id大于5小于5，且Message值为message的数据
        qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list2 = qb.where(ThingDao.Properties.Message.eq(message),
                qb.and(ThingDao.Properties.Id.gt(5),
                        ThingDao.Properties.Id.le(50))).list();

        List<Thing> youngJoes = qb.list();
        return list;
    }




}
