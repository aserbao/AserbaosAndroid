package com.aserbao.aserbaosandroid.algorithm.list;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/5/7 8:17 PM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.algorithm.list
 */
public class StudentAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public List<Student> mStudent = new ArrayList<>();
    public StudentAdapters(Context mContext) {
        this.mContext = mContext;
    }

    public void SimpleViewGroup(Student student){
        mStudent.add(student);
        Collections.sort(mStudent, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int i = o1.getScore() - o2.getScore();
                if (i == 0){
                    i = o1.getAge() - o2.getAge();
                }
                return i;
            }
        });
        notifyDataSetChanged();
    }

    public void addStudentList(List<Student> studentList){
        mStudent.clear();
        mStudent = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.two_tv_item, viewGroup, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (mStudent.size() > i){
            Student student = mStudent.get(i);
            ((StudentViewHolder) viewHolder).setDataSource(student);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mStudent.size() > 0 ) {
            ret = ret + mStudent.size();
        }
        return ret;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name_tv)
        TextView mItemNameTv;
        @BindView(R.id.item_content_tv)
        TextView mItemContentTv;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setDataSource(Student student){
            mItemNameTv.setText(student.getUser_name());
            mItemContentTv.setText(String.valueOf(student.getScore()));
        }
    }
}
