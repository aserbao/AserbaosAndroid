package com.aserbao.aserbaosandroid.functions.database.mySql.beans;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThingManagerDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mysql.db";
    private static final int VERSION = 1;
    public static final String CREAT_ETABLE_DOG = "CREATE TABLE mysql_thing(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " message TEXT,time LONG)";
    public static final String DROP_TABLE_DOG = "DROP TABLE IF EXISTS mysql_thing";

    public ThingManagerDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**
     * 每次打开数据库最先被执行
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
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
        if(newVersion == 2) {
            db.execSQL(DROP_TABLE_DOG);
            db.execSQL(CREAT_ETABLE_DOG);
        }
    }
}