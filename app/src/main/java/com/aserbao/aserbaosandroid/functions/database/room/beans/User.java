package com.aserbao.aserbaosandroid.functions.database.room.beans;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "school_name")
    public String schoolName;

    public User(int uid, String name, String schoolName) {
        this.uid = uid;
        this.name = name;
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "User{" +
            "uid=" + uid +
            ", name='" + name + '\'' +
            ", schoolName='" + schoolName + '\'' +
            '}';
    }
}
    