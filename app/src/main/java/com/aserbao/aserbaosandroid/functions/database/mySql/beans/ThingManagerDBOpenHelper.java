package com.aserbao.aserbaosandroid.functions.database.mySql.beans;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThingManagerDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mysql.db";
    private static final int VERSION = 1;
    public ThingManagerDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + ThingDBController.TABLE_NAME
                + String.format(
                "("
                        + "%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "%s VARCHAR, "
                        + "%s INTEGER"
                        +")"
                , ThingManagerDBModel.ID
                , ThingManagerDBModel.MESSAGE
                , ThingManagerDBModel.TIME
        )) ;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                upToDbVersion2(db);
            case 2:
                upToDbVersion3(db);
            default:
              break;
        }
    }
    public void upToDbVersion2(SQLiteDatabase db){
        db.execSQL("ALTER TABLE " + ThingDBController.TABLE_NAME + " ADD COLUMN add_user_name text");
    }

    public void upToDbVersion3(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("message", "版本升级后的数据");
        db.update(ThingDBController.TABLE_NAME, values, null, null);
    }


}