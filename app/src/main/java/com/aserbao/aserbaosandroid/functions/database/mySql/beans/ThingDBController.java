package com.aserbao.aserbaosandroid.functions.database.mySql.beans;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;

import java.util.ArrayList;
import java.util.List;

public class ThingDBController {
    public final static String TABLE_NAME = "mysql_thing";
    private final SQLiteDatabase db;

    public ThingDBController(Context context) {
        ThingManagerDBOpenHelper dbHelper = new ThingManagerDBOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // ============ 使用Api实现增删改查
    public boolean insertApi(int id, String message, long time){
        ThingManagerDBModel dbModel = new ThingManagerDBModel();
        if(id >= 0) {
            dbModel.id = id;
        }
        dbModel.message = message;
        dbModel.time = time;
        final boolean success = db.insert(TABLE_NAME, null, dbModel.toContentValues()) != -1;
        dbClose();
        return success;
    }

    public boolean deleteApi(String whereClause,String[] whereArgs){
        boolean sucess = db.delete(TABLE_NAME, whereClause,whereArgs) != -1;
        dbClose();
        return sucess;
    }

    public boolean updateApi(int id,String message,long time){
        ThingManagerDBModel dbModel = new ThingManagerDBModel();
        if(id >= 0) {
            dbModel.id = id;
        }
        dbModel.message = message;
        dbModel.time = time;
        boolean success = db.update(TABLE_NAME, dbModel.toContentValues(), "_id = ?", new String[]{String.valueOf(id)}) != -1;
        dbClose();
        return success;
    }

    /**
     * @param cloums 要查询的字段
     * @param selection 查询条件
     * @param selectionArgs 填充查询条件的值
     * @return
     */
    public List<Thing> queryApi(String[] cloums,String selection,String[]  selectionArgs){
        try {
            Cursor c = db.query(TABLE_NAME, cloums, selection, selectionArgs, null, null, null);
            ArrayList<Thing> arrayList = new ArrayList<>();
            if (!c.moveToLast()) {
                return arrayList;
            }
            do {
                Thing model = new Thing();
                model.setId(c.getInt(c.getColumnIndexOrThrow(ThingManagerDBModel.ID)));
                model.setTime(c.getLong(c.getColumnIndexOrThrow(ThingManagerDBModel.TIME)));
                model.setMessage(c.getString(c.getColumnIndexOrThrow(ThingManagerDBModel.MESSAGE)));
                arrayList.add(model);
            } while (c.moveToPrevious());
            c.close();
            return arrayList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbClose();
        }

        return new ArrayList<>();
    }




    // =============sql语句 增删改查
    public void insertRaw(String message, long time){
        String sql = " insert into " + TABLE_NAME + "(message,time) values(?,?)";
        Object[] args = {message,time};
        db.execSQL(sql,args);
        dbClose();
    }

    public void deleteRaw(String message){
        String sql = "delete from "+TABLE_NAME + " where message = ?";
        Object[] args = {message};
        db.execSQL(sql,args);
        dbClose();
    }

    public void updateRaw(int id,String message,long time){
        String sql = "update "+TABLE_NAME + " set message = ?,time = ? where _id = ?";
        Object[] args = {message,time,id};
        db.execSQL(sql,args);
        dbClose();
    }

    public List<Thing> queryRawById(String  id){
        String sql = "select _id,message,time from "+TABLE_NAME + " where _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        ArrayList<Thing> arrayList = new ArrayList<>();
        if (!cursor.moveToLast()) {
            return arrayList;
        }
        do {
            Thing model = new Thing();
            model.setId(cursor.getInt(cursor.getColumnIndex(ThingManagerDBModel.ID)));
            model.setTime(cursor.getLong(cursor.getColumnIndex(ThingManagerDBModel.TIME)));
            model.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(ThingManagerDBModel.MESSAGE)));
            arrayList.add(model);
        } while (cursor.moveToPrevious());
        cursor.close();
        dbClose();
        return arrayList;
    }

    public List<Thing> queryAll(){
        try {
            final Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            ArrayList<Thing> arrayList = new ArrayList<>();
            if (!c.moveToLast()) {
                return arrayList;
            }
            do {
                Thing model = new Thing();
                model.setId(c.getInt(c.getColumnIndex(ThingManagerDBModel.ID)));
                model.setTime(c.getLong(c.getColumnIndex(ThingManagerDBModel.TIME)));
                model.setMessage(c.getString(c.getColumnIndexOrThrow(ThingManagerDBModel.MESSAGE)));
                arrayList.add(model);
            } while (c.moveToPrevious());
            c.close();
            dbClose();
            return arrayList;
        } catch (Exception e){
            e.printStackTrace();
            dbClose();
        }
        return null;
    }



    public void dbClose(){
        if (db != null) {
            db.close();
        }
    }
}