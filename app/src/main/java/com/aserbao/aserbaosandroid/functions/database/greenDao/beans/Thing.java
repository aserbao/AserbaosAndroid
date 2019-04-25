package com.aserbao.aserbaosandroid.functions.database.greenDao.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/26
 * email: this is empty email
 */
@Entity
public class Thing {
    Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Property(nameInDb="message")
    @Index(unique = true)
    String message;
    String name;

    @Transient
    long time;

    @Generated(hash = 1981866127)
    public Thing() {
    }

    public Thing(String message, long time) {
        this.message = message;
        this.time = time;
    }

    @Generated(hash = 363139733)
    @Keep
    public Thing(Long id, String message,long time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }

    @Generated(hash = 196461523)
    public Thing(Long id, String message, String name) {
        this.id = id;
        this.message = message;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
