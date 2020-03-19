package com.aserbao.aserbaosandroid.functions.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aserbao.aserbaosandroid.functions.database.room.beans.StudentRoom;
import com.aserbao.aserbaosandroid.functions.database.room.beans.User;
import com.aserbao.aserbaosandroid.functions.database.room.daos.StudentRoomDao;
import com.aserbao.aserbaosandroid.functions.database.room.daos.UserDao;

@Database(entities = {User.class, StudentRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract UserDao userDao();
        public abstract StudentRoomDao studentRoomDao();
}
    