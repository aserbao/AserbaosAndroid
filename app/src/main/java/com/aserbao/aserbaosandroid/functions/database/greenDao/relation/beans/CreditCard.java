package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:信用卡
 * author aserbao
 * date : On 2018/10/18
 * email: this is empty email
 */
@Entity
public class CreditCard {
    @Id
    Long id;
    Long studentId;
    Long teacherId;
    String userName;//持有者名字
    String cardNum;//卡号
    String whichBank;//哪个银行的
    int cardType;//卡等级，分类 0 ~ 5

    @Generated(hash = 1860989810)
    public CreditCard() {
    }

    @Generated(hash = 224468523)
    public CreditCard(Long id, Long studentId, Long teacherId, String userName,
            String cardNum, String whichBank, int cardType) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.userName = userName;
        this.cardNum = cardNum;
        this.whichBank = whichBank;
        this.cardType = cardType;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCardNum() {
        return this.cardNum;
    }
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    public String getWhichBank() {
        return this.whichBank;
    }
    public void setWhichBank(String whichBank) {
        this.whichBank = whichBank;
    }
    public int getCardType() {
        return this.cardType;
    }
    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

}
