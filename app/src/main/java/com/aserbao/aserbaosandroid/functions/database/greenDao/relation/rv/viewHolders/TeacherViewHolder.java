package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.example.base.database.beans.Teacher;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/18
 * email: this is empty email
 */
public class TeacherViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.green_dao_teacher_name_tv)
    public TextView mGreenDaoTeacherNameTv;
    @BindView(R.id.green_dao_teacher_id_tv)
    TextView mGreenDaoTeacherIdTv;
    @BindView(R.id.green_dao_teacher_no_tv)
    TextView mGreenDaoTeacherNoTv;
    @BindView(R.id.green_dao_teacher_age_tv)
    TextView mGreenDaoTeacherAgeTv;
    @BindView(R.id.green_dao_teacher_sex_tv)
    TextView mGreenDaoTeacherSexTv;
    @BindView(R.id.green_dao_teacher_tel_tv)
    TextView mGreenDaoTeacherTelTv;
    @BindView(R.id.green_dao_teacher_school_name_tv)
    TextView mGreenDaoTeacherSchoolNameTv;
    @BindView(R.id.green_dao_teacher_subject_tv)
    TextView mGreenDaoTeacherSubjectTv;
    public TeacherViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setDataSource(Teacher teacher){
        if (teacher != null) {
            mGreenDaoTeacherNameTv.setText("名字： " + teacher.getName());
            mGreenDaoTeacherIdTv.setText("ID： " + teacher.getId());
            mGreenDaoTeacherNoTv.setText("学号：" + teacher.getTeacherNo());
            mGreenDaoTeacherAgeTv.setText("年龄：" + teacher.getAge());
            mGreenDaoTeacherSexTv.setText("性别：" + teacher.getSex());
            mGreenDaoTeacherTelTv.setText("手机号："+ teacher.getTelPhone());
            mGreenDaoTeacherSchoolNameTv.setText("学校名字：" + teacher.getSchoolName());
            mGreenDaoTeacherSubjectTv.setText("科目： "+ teacher.getSubject());
        }
    }


}
