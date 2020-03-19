package com.aserbao.aserbaosandroid.functions.database.greenDao.relation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.CreditCard;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.IdCard;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Student;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.StudentAndTeacherBean;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Teacher;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.adapters.RelationAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.aserbao.aserbaosandroid.AserbaoApplication.DB_NAME;

public class GreenDaoRelationActivity extends AppCompatActivity {

    @BindView(R.id.to_one_one_reycler_view)
    RecyclerView mToOneOneReyclerView;
    @BindView(R.id.to_one_two_reycler_view)
    RecyclerView mToOneTwoReyclerView;
    private RelationAdapter mRelationAdapter;
    private Random mRandom =  new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_to_one);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initView();
    }
    private void initView() {
        mRelationAdapter = new RelationAdapter(this,this,mToOneOneReyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mToOneOneReyclerView.setLayoutManager(linearLayoutManager);
        mToOneOneReyclerView.setAdapter(mRelationAdapter);
    }

    @OnClick({R.id.to_one_add_data_btn, R.id.to_one_add_teacher_btn,R.id.show_student_btn,R.id.show_credit_card_btn,R.id.show_id_card_btn,R.id.show_teacher_btn,R.id.show_all_data_btn,R.id.delete_all_data_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.to_one_add_data_btn:
                DaoSession daoSession = null;
                List<Student> students = null;
                int size = 0;
                daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
                students = daoSession.loadAll(Student.class);
                size = students.size();
                List<Teacher> teacherList = daoSession.loadAll(Teacher.class);
                if(teacherList.size() > 0) {
                    for (int i = size; i < size + 5; i++) {
                        Student student = new Student();
                        student.setStudentNo(i);
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
                        daoSession.insert(student);

                        addOtherData(mRandom, daoSession, chineseName, student.getId(),true);

                        Collections.shuffle(teacherList);
                        for (int j = 0; j < mRandom.nextInt(8) + 1; j++) {
                            if(j < teacherList.size()){
                                Teacher teacher = teacherList.get(j);
                                StudentAndTeacherBean teacherBean = new StudentAndTeacherBean(student.getId(), teacher.getId());
                                daoSession.insert(teacherBean);
                            }
                        }
                    }

                    mRelationAdapter.refreshAllData(RelationAdapter.STUDENT);
                }else{
                    Toast.makeText(this, "请先添加老师数据", Toast.LENGTH_SHORT).show();
                }
//                }
                break;
            case R.id.to_one_add_teacher_btn:
                daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
                List<Teacher> teachers = daoSession.loadAll(Teacher.class);
                    for (int i =teachers .size(); i < teachers.size() + 4; i++) {
                        Teacher teacher = new Teacher();
                        teacher.setTeacherNo(i);
                        int age = mRandom.nextInt(20) + 18;
                        teacher.setAge(age);
                        String chineseName = RandomValue.getChineseName();
                        teacher.setName(chineseName);
                        teacher.setSchoolName(RandomValue.getSchoolName());
                        if (i % 2 == 0) {
                            teacher.setSex("男");
                        } else {
                            teacher.setSex("女");
                        }
                        teacher.setTelPhone(RandomValue.getTel());
                        teacher.setSubject(RandomValue.getRandomSubject());
                        daoSession.insert(teacher);
                        addOtherData(mRandom, daoSession, chineseName, teacher.getId(),false);
                    }
                mRelationAdapter.refreshAllData(RelationAdapter.TEACHER);
                break;

            case R.id.show_student_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.STUDENT);
                break;
            case R.id.show_credit_card_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.CREDITCARD);
                break;
            case R.id.show_id_card_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.IDCARD);
                break;
            case R.id.show_teacher_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.TEACHER);
                break;
            case R.id.show_all_data_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.ALLDATA);
                break;
            case R.id.delete_all_data_btn:
                boolean b = deleteDatabase(DB_NAME);
                if(b){
                    mRelationAdapter.refreshAllData(RelationAdapter.ALLDATA);
                }else{
                    Toast.makeText(this, "删除表失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public void addOtherData(Random random, DaoSession daoSession,String userName,Long id,boolean isStudent){
        IdCard idCard = new IdCard();
        idCard.setUserName(userName);
        idCard.setIdNo(RandomValue.getRandomID());
        daoSession.insert(idCard);
        for (int j = 0; j < random.nextInt(5) + 1 ; j++) {
            CreditCard creditCard = new CreditCard();
            if (isStudent) {
                creditCard.setStudentId(String.valueOf(id) + "_string");
            }else{
                creditCard.setTeacherId(id);
            }
            creditCard.setUserName(userName);
            creditCard.setCardNum(String.valueOf(random.nextInt(899999999) + 100000000) + String.valueOf(random.nextInt(899999999) + 100000000));
            creditCard.setWhichBank(RandomValue.getBankName());
            creditCard.setCardType(random.nextInt(10));
            daoSession.insert(creditCard);
        }
    }
}
