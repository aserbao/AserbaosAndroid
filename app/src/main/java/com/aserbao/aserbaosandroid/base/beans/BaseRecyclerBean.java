package com.aserbao.aserbaosandroid.base.beans;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/19 4:51 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base.beans
 */
public class BaseRecyclerBean {
    String name;
    int tag;

    public BaseRecyclerBean(String name, int tag) {
        this.name = name;
        this.tag = tag;
    }

    public BaseRecyclerBean(String name) {
        this.name = name;
    }

    public int getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
