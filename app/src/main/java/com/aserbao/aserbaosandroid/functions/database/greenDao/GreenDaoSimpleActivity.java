package com.aserbao.aserbaosandroid.functions.database.greenDao;

import android.text.TextUtils;

import com.aserbao.aserbaosandroid.AUtils.utils.log.ALogUtils;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity;
import com.example.base.database.beans.Thing;
import com.example.base.database.greendao.db.DaoSession;
import com.example.base.database.greendao.db.ThingDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoSimpleActivity extends DataBaseBaseActivity {
    private static final String TAG = "GreenDaoSimpleActivity";
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
            ALogUtils.logErrorTime(ALogUtils.INT_NUM_START_TIME);
            List<Thing> thingArrayList = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                String content = "第" + i + "个数据";
                String name = "第" + i + "个名字";
                Thing thing = new Thing(content, System.currentTimeMillis() - 60 * 1000 * i);
                thingArrayList.add(thing);
//                daoSession.insertOrReplace(thing);
            }
            insertListInTx(thingArrayList);
            ALogUtils.logErrorTime(ALogUtils.INT_NUM_END_TIME);
        }else{
            daoSession.insertOrReplace(s);
        }
        refreshAdapter(GREEN_DAO);

    }

    public void add(){
        for (int i = 0; i < 10; i++) {
/*

            Student student = new Student();
            student.setAge((int) (Math.random() * 100 + 10));
            student.setName("第几" +  i + " 个");
            long thingInfoId = (long) (Math.random() * 1000);
            student.setThingInfoId(thingInfoId);
            daoSession.getUserDao().insert(student);

            Thing thing = new Thing();
            thing.setId(thingInfoId);
            thing.setMessage("第" + i+ "个 message " );
            thing.setTime(System.currentTimeMillis());
            thing.setName("第" + i+ "个 Name ");

            daoSession.getThingDao().insert(thing);
            */

        }

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
        /*daoSession.delete(null);
        ThingDao thingDao1 = daoSession.getThingDao();
        Log.e(TAG, "deleteAll: " + thingDao1.loadAll().size() );*/
        refreshAdapter(GREEN_DAO);
    }

    @Override
    public void queryData(String s) {
        if (TextUtils.isEmpty(s)){
            updateListInTx();
        }else {
            List<Thing> things = daoSession.queryRaw(Thing.class, " where id = ?", s);
            mDataBaseAdapter.addNewThingData(things);
        }
//        mDataBaseAdapter.addNewThingData(queryListByOther());
//        mDataBaseAdapter.addNewThingData(queryListByMoreTime());
//        mDataBaseAdapter.addNewThingData(queryListBySqL());
//        deleteItem();
//        mDataBaseAdapter.notifyDataSetChanged();
    }

    public List queryAll(){
        List<Thing> things = daoSession.loadAll(Thing.class);
        return things;
    }
    public List quetyList(String message){

        daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        QueryBuilder<Thing> qb = daoSession.queryBuilder(Thing.class);
        // 查出所有的数据
        List<Thing> list = qb.list();

        //查出当前对应message的数据
        QueryBuilder<Thing> thingQueryBuilder = qb.where(ThingDao.Properties.Message.like(message)).orderAsc(ThingDao.Properties.Message);
        List<Thing> thingList = thingQueryBuilder.list();

        //查询Message值为message时，按Name值排序的结果
        qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list1 = qb.where(ThingDao.Properties.Message.eq(message)).orderAsc(ThingDao.Properties.Name).list();

        //嵌套查询： 查询Id大于5小于5，且Message值为message的数据
        qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list2 = qb.where(ThingDao.Properties.Message.eq(message),
                qb.and(ThingDao.Properties.Id.gt(5),
                        ThingDao.Properties.Id.le(50))).list();


        return list;
    }

    public List queryList(){
        DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        QueryBuilder<Thing> qb = daoSession.queryBuilder(Thing.class);
        //嵌套查询： 查询Id大于5小于5，且Message值为message的数据
        qb = daoSession.queryBuilder(Thing.class);
        List<Thing> list2 = qb.where(ThingDao.Properties.Message.eq("一"),
                qb.and(ThingDao.Properties.Id.gt(5),
                        ThingDao.Properties.Id.le(50))).list();
        return  list2;
    }

    public List queryListByOther(){
        DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        QueryBuilder<Thing> qb = daoSession.queryBuilder(Thing.class);

        //搜索条件为Id值大于1，即结果为[2,3,4,5,6,7,8,9,10,11];
        // offset(2)表示往后偏移2个，结果为[4,5,6,7,8,9,10,11,12,13];
        List<Thing> list = qb.where(ThingDao.Properties.Id.gt(1)).limit(10).offset(2).list();
        return list;
    }

    public List queryListByMoreTime(){
        DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        QueryBuilder<Thing> qb = daoSession.queryBuilder(Thing.class);

        //搜索条件为Id值大于1，即结果为[2,3,4,5,6,7,8,9,10,11];
        // offset(2)表示往后偏移2个，结果为[4,5,6,7,8,9,10,11,12,13];
        Query<Thing> query = qb.where(ThingDao.Properties.Id.gt(1)).limit(10).offset(2).build();
        List<Thing> list = query.list();

        //通过SetParameter来修改上面的查询条件，比如我们将上面条件修改取10条Id值大于5，往后偏移两位的数据，方法如下！
        query.setParameter(0,5);
        List<Thing> list1 = query.list();
        return list1;
    }

    public List queryListBySqL(){
        DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        Query<Thing> query = daoSession.queryBuilder(Thing.class).where(
                new WhereCondition.StringCondition("_ID IN " +
                        "(SELECT _ID FROM THING WHERE _ID > 5)")
        ).build();
        List<Thing> list = query.list();
        return list;
    }

    public boolean deleteItem(){
        DaoSession daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
        QueryBuilder<Thing> where = daoSession.queryBuilder(Thing.class).where(ThingDao.Properties.Id.gt(5));
        DeleteQuery<Thing> deleteQuery = where.buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
        return false;
    }


    /**
     * 批量更新
     */
    public void updateListInTx(){
        List<Thing> things = daoSession.getThingDao().loadAll();
        for (Thing thing : things) {
            thing.setMessage(thing.getMessage() + " GoodBye 你了");
        }
        daoSession.getThingDao().updateInTx(things);
        refreshAdapter(GREEN_DAO);
    }

    public void insertListInTx(List<Thing> thingList){
        daoSession.getThingDao().insertInTx(thingList);
        refreshAdapter(GREEN_DAO);
    }

    public void deleteListInTx(List<Thing> thingList){
        daoSession.getThingDao().deleteInTx(thingList);
        refreshAdapter(GREEN_DAO);
    }





}
