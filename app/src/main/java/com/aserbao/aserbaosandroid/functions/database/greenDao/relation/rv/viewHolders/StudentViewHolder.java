package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.example.base.database.beans.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/18
 * email: this is empty email
 */
public class StudentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.green_dao_student_name_tv)
    public TextView mGreenDaoStudentNameTv;
    @BindView(R.id.green_dao_student_id_tv)
    TextView mGreenDaoStudentIdTv;
    @BindView(R.id.green_dao_student_no_tv)
    TextView mGreenDaoStudentNoTv;
    @BindView(R.id.green_dao_student_age_tv)
    TextView mGreenDaoStudentAgeTv;
    @BindView(R.id.green_dao_student_sex_tv)
    TextView mGreenDaoStudentSexTv;
    @BindView(R.id.green_dao_student_tel_tv)
    TextView mGreenDaoStudentTelTv;
    @BindView(R.id.green_dao_student_school_name_tv)
    TextView mGreenDaoStudentSchoolNameTv;
    @BindView(R.id.green_dao_student_grade_tv)
    TextView mGreenDaoStudentGradeTv;
    @BindView(R.id.green_dao_student_address_tv)
    TextView mGreenDaoStudentAddressTv;
    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setDataSource(Student student){
        if (student != null) {
            mGreenDaoStudentNameTv.setText("名字： " + student.getName());
            mGreenDaoStudentIdTv.setText("ID： " + student.getId());
            mGreenDaoStudentNoTv.setText("学号：" + student.getStudentNo());
            mGreenDaoStudentAgeTv.setText("年龄：" + student.getAge());
            mGreenDaoStudentSexTv.setText("性别：" + student.getSex());
            mGreenDaoStudentTelTv.setText("手机号："+ student.getTelPhone());
            mGreenDaoStudentSchoolNameTv.setText("学校名字：" + student.getSchoolName());
            mGreenDaoStudentGradeTv.setText("年纪： "+ student.getGrade());
            mGreenDaoStudentAddressTv.setText("家庭住址：" + student.getAddress());
        }
    }

    @OnClick({R.id.green_dao_student_name_tv, R.id.green_dao_student_school_name_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.green_dao_student_name_tv:
                break;
            case R.id.green_dao_student_school_name_tv:
                break;
        }
    }
}
