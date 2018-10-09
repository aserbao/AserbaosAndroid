package com.aserbao.aserbaosandroid.functions.database.mySql;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.functions.database.base.DataBaseBaseActivity;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;
import com.aserbao.aserbaosandroid.functions.database.mySql.beans.ThingDBController;

import java.util.List;

import static com.aserbao.aserbaosandroid.functions.database.mySql.beans.ThingManagerDBOpenHelper.DB_NAME;

public class MySqlActivity extends DataBaseBaseActivity {
    private static final String TAG = "MySqlActivity";
    private ThingDBController dbController;
    public int use_type = 0;//0表示使用Android Api,1表示使用sql语句

    @Override
    public void initDatabase() {
        ThingDBController thingDBController = new ThingDBController(this);
        List<Thing> things = thingDBController.queryAll();
        if (things != null) {
            this.things = things;
        }
        mADatabaseWhichMethodBtn.setVisibility(View.VISIBLE);
        mADatabaseWhichMethodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(use_type == 0){
                    use_type = 1;
                    mADatabaseWhichMethodBtn.setText("使用SQl语句");
                }else{
                    use_type = 0;
                    mADatabaseWhichMethodBtn.setText("使用Android Api方式");
                }
            }
        });
    }

    @Override
    public void insertData(Thing s) {
            if (s == null) {
                for (int i = 0; i < 1000; i++) {
                    String content = "第" + i + "个数据";
                    dbController = new ThingDBController(this);
                        dbController.insertApi(-1, content, System.currentTimeMillis() - 60 * 1000 * i);
                }
            } else {
                dbController = new ThingDBController(this);
                if(use_type == 0) {
                    dbController.insertApi(-1, s.getMessage(), s.getTime());
                }else{
                    dbController.insertRaw(s.getMessage(), s.getTime());
                }
            }
        refreshAdapter(SQL_LITE);
    }



    @Override
    public void deleteData(Thing s) {
        dbController = new ThingDBController(this);
        if(use_type == 0 ) {
            if (!dbController.deleteApi("_id = ?", new String[]{String.valueOf(s.getId())})) {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            }
        }else{
            dbController.deleteRaw(s.getMessage());
        }
    }

    @Override
    public void updataData(Thing s) {
        dbController = new ThingDBController(this);
        if(use_type == 0 ) {
            dbController.updateApi(s.getId(), s.getMessage(), s.getTime());
        }else{
            dbController.updateRaw(s.getId(),s.getMessage(),s.getTime());
        }
        refreshAdapter(SQL_LITE);
    }

    @Override
    public void queryData(String id) {
        dbController = new ThingDBController(this);
        List<Thing> listThings;
        if(use_type == 0 ) {
            listThings = dbController.queryApi(new String[]{"_id", "time", "message"}, "_id > ?", new String[]{id});
        }else{
            listThings = dbController.queryRawById(id);
        }
            if (listThings != null && listThings.size() == 0) {
                Toast.makeText(this, "没有查到相关内容", Toast.LENGTH_SHORT).show();
            }
        mDataBaseAdapter.addNewThingData(listThings);
    }

    @Override
    public void deleteAll() {
        deleteDatabase(DB_NAME);
        refreshAdapter(SQL_LITE);
    }
}
