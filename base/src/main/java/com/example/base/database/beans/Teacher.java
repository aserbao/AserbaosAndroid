package com.example.base.database.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;
/*import com.example.base.database.greendao.db.DaoSession;
import com.example.base.database.greendao.db.StudentDao;
import com.example.base.database.greendao.db.CreditCardDao;
import com.example.base.database.greendao.db.IdCardDao;
import com.example.base.database.greendao.db.TeacherDao;*/
import com.example.base.database.greendao.db.DaoSession;
import com.example.base.database.greendao.db.StudentDao;
import com.example.base.database.greendao.db.CreditCardDao;
import com.example.base.database.greendao.db.IdCardDao;
import com.example.base.database.greendao.db.TeacherDao;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: this is empty email
 */
@Entity
public class Teacher {
    @Id(autoincrement = true)
    Long id;

    @Unique
    int teacherNo;//职工号

    int age; //年龄
    String sex; //性别

    String telPhone;
    String name;//姓名
    String schoolName;//学校名字

    String subject;//科目

    @ToOne(joinProperty = "name")
    IdCard student;

    @ToMany(referencedJoinProperty = "id")
    List<CreditCard> creditCardsList;

    @ToMany
    @JoinEntity(entity = StudentAndTeacherBean.class,sourceProperty = "teacherId",targetProperty = "studentId")
    List<Student> studentList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 648119699)
    private transient TeacherDao myDao;

    @Generated(hash = 184718316)
    public Teacher(Long id, int teacherNo, int age, String sex, String telPhone, String name, String schoolName,
            String subject) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.age = age;
        this.sex = sex;
        this.telPhone = telPhone;
        this.name = name;
        this.schoolName = schoolName;
        this.subject = subject;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeacherNo() {
        return this.teacherNo;
    }

    public void setTeacherNo(int teacherNo) {
        this.teacherNo = teacherNo;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelPhone() {
        return this.telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Generated(hash = 635690445)
    private transient String student__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 137743110)
    public IdCard getStudent() {
        String __key = this.name;
        if (student__resolvedKey == null || student__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdCardDao targetDao = daoSession.getIdCardDao();
            IdCard studentNew = targetDao.load(__key);
            synchronized (this) {
                student = studentNew;
                student__resolvedKey = __key;
            }
        }
        return student;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1847551028)
    public void setStudent(IdCard student) {
        synchronized (this) {
            this.student = student;
            name = student == null ? null : student.getUserName();
            student__resolvedKey = name;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 892088247)
    public List<CreditCard> getCreditCardsList() {
        if (creditCardsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CreditCardDao targetDao = daoSession.getCreditCardDao();
            List<CreditCard> creditCardsListNew = targetDao._queryTeacher_CreditCardsList(id);
            synchronized (this) {
                if (creditCardsList == null) {
                    creditCardsList = creditCardsListNew;
                }
            }
        }
        return creditCardsList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 441911208)
    public synchronized void resetCreditCardsList() {
        creditCardsList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 927304389)
    public List<Student> getStudentList() {
        if (studentList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentListNew = targetDao._queryTeacher_StudentList(id);
            synchronized (this) {
                if (studentList == null) {
                    studentList = studentListNew;
                }
            }
        }
        return studentList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1628625923)
    public synchronized void resetStudentList() {
        studentList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1349174479)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherDao() : null;
    }



}
