package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.IdCardDao;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.CreditCard;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.IdCard;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Student;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.Teacher;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders.IdCardViewHolder;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders.StudentViewHolder;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/18
 * email: 1142803753@qq.com
 */
public class RelationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int STUDENT = 0;
    public static final int TEACHER = 1;
    public static final int IDCARD = 2;
    public static final int CREDITCARD = 3;



    private int cuurType = STUDENT;

    private Context mContext;
    private Activity activity;
    private DaoSession daoSession;

    List<Student> mStudentList = new ArrayList<>();
    List<Teacher> mTeacherList = new ArrayList<>();
    List<IdCard> mIdCardList = new ArrayList<>();
    List<CreditCard> mCreditCardList = new ArrayList<>();

    public RelationAdapter(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        daoSession = ((AserbaoApplication) activity.getApplication()).getDaoSession();
    }
    public void refreshAllData(int type){
        cuurType = type;

        switch (type){
            case STUDENT:
                mStudentList =  daoSession.loadAll(Student.class);
                break;
            case TEACHER:
                mTeacherList = daoSession.loadAll(Teacher.class);
                break;
            case IDCARD:
                mIdCardList = daoSession.loadAll(IdCard.class);
                break;
            case CREDITCARD:
                mCreditCardList = daoSession.loadAll(CreditCard.class);
                break;
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return cuurType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        switch (cuurType) {
            case STUDENT:
                view = inflater.inflate(R.layout.green_dao_student_item, viewGroup, false);
                return new StudentViewHolder(view);
            case IDCARD:
                view = inflater.inflate(R.layout.green_dao_id_card_item, viewGroup, false);
                return new IdCardViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof StudentViewHolder){
            if(i < mStudentList.size()) {
                Student student = mStudentList.get(i);
                ((StudentViewHolder) viewHolder).setDataSource(student);
                ((StudentViewHolder) viewHolder).mGreenDaoStudentNameTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = ((StudentViewHolder) viewHolder).mGreenDaoStudentNameTv.getText().toString().trim();
                        String[] split = name.split(" ");
                        String userName = split[1];
                        mIdCardList = daoSession.queryBuilder(IdCard.class).where(IdCardDao.Properties.UserName.like(userName)).list();
                        cuurType = IDCARD;
                        notifyDataSetChanged();
                    }
                });
            }
        }else if(viewHolder instanceof IdCardViewHolder){
            if (i < mIdCardList.size()){
                IdCard idCard = mIdCardList.get(i);
                ((IdCardViewHolder) viewHolder).setDataSource(idCard);
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        switch (cuurType){
            case STUDENT:
                if (mStudentList != null) {
                    ret = ret + mStudentList.size();
                }
                break;
            case TEACHER:
                if (mTeacherList != null) {
                    ret = ret + mTeacherList.size();
                }
                break;
            case IDCARD:
                if (mIdCardList != null) {
                    ret = ret + mIdCardList.size();
                }
                break;
            case CREDITCARD:
                if (mCreditCardList != null) {
                    ret = ret + mCreditCardList.size();
                }
                break;
        }
        return ret;
    }


}
