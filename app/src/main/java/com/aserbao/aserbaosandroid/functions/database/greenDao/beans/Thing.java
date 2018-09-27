package com.aserbao.aserbaosandroid.functions.database.greenDao.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/26
 * email: 1142803753@qq.com
 */
@Entity
public class Thing {
    long id;
    String message;
    long time;

    @Generated(hash = 922201001)
    public Thing(long id, String message, long time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

    @Generated(hash = 1981866127)
    public Thing() {
    }

    public Thing(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
