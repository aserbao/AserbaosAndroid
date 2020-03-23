package com.example.base.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.base.database.greendao.db.DaoMaster;
import com.example.base.database.greendao.db.StudentsDao;

import org.greenrobot.greendao.database.Database;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/15
 * email: this is empty email
 */
public class MyDaoMaster extends DaoMaster.OpenHelper {
    private static final String TAG = "MyDaoMaster";
    public MyDaoMaster(Context context, String name) {
        super(context, name);
    }

    public MyDaoMaster(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, StudentsDao.class);
        Log.e(TAG, "onUpgrade: " + oldVersion + " newVersion = " + newVersion);
    }
}
