package com.example.base.database.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020-01-29
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.functions.database.greenDao.beans
 */
@Entity
public class SuperThing extends TestSuper{
    @Id(autoincrement = true)
    long id;
    String content;

    @Generated(hash = 931673794)
    public SuperThing(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Generated(hash = 422869794)
    public SuperThing() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
