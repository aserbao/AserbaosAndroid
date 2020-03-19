package com.aserbao.aserbaosandroid.functions.database.room;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.room.Room;

import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.functions.database.room.beans.StudentRoom;
import com.aserbao.aserbaosandroid.functions.database.room.daos.StudentRoomDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomActivity extends BaseRecyclerViewActivity {


    private StudentRoomDao mStudentRoomDao;
    private ExecutorService mExecutorService;

    @Override
    public void initGetData() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mBaseRecyclerBean.add(new BaseRecyclerBean("增",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("删",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("改",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("查",4));
        mStudentRoomDao = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "database-name").build().studentRoomDao();
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                mExecutorService.execute(() -> insertStudent());
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                mExecutorService.execute(() -> queryStudent());
                break;
        }
    }


    public void insertStudent(){
        Random mRandom = new Random();
        List<StudentRoom> studentRooms = new ArrayList<>();
        for (int i = 0; i <  5; i++) {
            StudentRoom student = new StudentRoom();
            student.setStudentNo((int)System.currentTimeMillis());
            int age = mRandom.nextInt(10) + 10;
            student.setAge(age);
            student.setTelPhone(RandomValue.getTel());
            String chineseName = RandomValue.getChineseName();
            student.setName(chineseName);
            if (i % 2 == 0) {
                student.setSex("男");
            } else {
                student.setAddress(RandomValue.getRoad());
                student.setSex("女");
            }
            student.setGrade(String.valueOf(age % 10) + "年纪");
            student.setSchoolName(RandomValue.getSchoolName());
//            mStudentRoomDao.insertAll(student);
            studentRooms.add(student);
        }
        mStudentRoomDao.insertAll(studentRooms);
    }

    private static final String TAG = "RoomActivity";
    public void queryStudent(){
        List<StudentRoom> all = mStudentRoomDao.getAll();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < all.size(); i++) {
            StudentRoom studentRoom = all.get(i);
            stringBuffer.append("第").append(String.valueOf(i)).append("个").append(studentRoom.toString()).append("\n");
        }
        Log.e(TAG, "queryStudent: " + stringBuffer.toString() );
    }
}
