package com.aserbao.aserbaosandroid.functions.database.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.aserbao.aserbaosandroid.functions.database.room.beans.StudentRoom;
import com.aserbao.aserbaosandroid.functions.database.room.beans.User;

import java.util.List;


@Dao
public interface StudentRoomDao {
    @Query("SELECT * FROM student")
    List<StudentRoom> getAll();

    @Insert
    void insertAll(StudentRoom... studentRooms);

    @Insert
    void insertAll(List<StudentRoom> studentRooms);

    @Delete
    void delete(StudentRoom studentRoom);
    }
    