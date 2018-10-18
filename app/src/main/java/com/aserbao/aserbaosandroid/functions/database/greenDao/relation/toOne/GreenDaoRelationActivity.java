package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.toOne;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.IdCard;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Student;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.adapters.RelationAdapter;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoRelationActivity extends AppCompatActivity {

    @BindView(R.id.to_one_one_reycler_view)
    RecyclerView mToOneOneReyclerView;
    @BindView(R.id.to_one_two_reycler_view)
    RecyclerView mToOneTwoReyclerView;
    private RelationAdapter mRelationAdapter;

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
        mRelationAdapter = new RelationAdapter(this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mToOneOneReyclerView.setLayoutManager(linearLayoutManager);
        mToOneOneReyclerView.setAdapter(mRelationAdapter);
    }

    @OnClick({R.id.to_one_add_btn, R.id.to_one_query_btn,R.id.show_student_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.to_one_add_btn:
                DaoSession daoSession = null;
                List<Student> students = null;
                int size = 0;
                daoSession = ((AserbaoApplication) getApplication()).getDaoSession();
                    students = daoSession.loadAll(Student.class);
                    size = students.size();
                    for (int i = size; i < size + 5; i++) {
                        Student student = new Student();
                        student.setStudentNo(i);
                        int age = new Random().nextInt(10) + 10;
                        student.setAge(age);
                        student.setTelPhone(RandomValue.getTel());
                        String chineseName = RandomValue.getChineseName();
                        student.setName(chineseName);
                        if(i % 2 == 0){
                            student.setSex("男");
                        }else{
                            student.setSex("女");
                        }
                        student.setAddress(RandomValue.getRoad());
                        student.setGrade(String.valueOf(age % 10) + "年纪");
                        student.setSchoolName(RandomValue.getChineseName());
                        daoSession.insert(student);
                        IdCard idCard = new IdCard();
                        idCard.setUserName(chineseName);
                        idCard.setIdNo(RandomValue.getRandomID());
                        daoSession.insert(idCard);
                    }
                    mRelationAdapter.refreshAllData(RelationAdapter.STUDENT);
//                }
                break;
            case R.id.to_one_query_btn:
                break;

            case R.id.show_student_btn:
                mRelationAdapter.refreshAllData(RelationAdapter.STUDENT);
                break;
        }
    }


}
