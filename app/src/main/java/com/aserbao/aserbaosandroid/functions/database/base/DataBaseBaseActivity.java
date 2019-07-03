package com.aserbao.aserbaosandroid.functions.database.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.base.rv.adapters.DataBaseAdapter;
import com.aserbao.aserbaosandroid.functions.database.base.rv.interfaces.ItemBackListener;
import com.aserbao.aserbaosandroid.functions.database.greenDao.beans.Thing;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/28
 * email: this is empty email
 */
public abstract class DataBaseBaseActivity extends AppCompatActivity {
    public static final int SQL_LITE = 0;
    public static final int GREEN_DAO = 1;

    @BindView(R.id.a_database_submit_et)
    EditText mADataBaseSubmitEt;
    @BindView(R.id.a_green_dao_search_et)
    EditText mADataBaseSearchEt;
    @BindView(R.id.a_data_base_recycler_view)
    RecyclerView mADataBaseRecyclerView;
    public DataBaseAdapter mDataBaseAdapter;

    public List<Thing> things = new ArrayList<>();
    public LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.a_database_which_method_btn)
    public Button mADatabaseWhichMethodBtn;
    @BindView(R.id.a_database_time_show_tv)
    public TextView mADatabaseTimeShowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        initDatabase();
        initView();
    }

    private void initView() {
        mDataBaseAdapter = new DataBaseAdapter(things, this, new ItemBackListener() {
            @Override
            public void onItemClick(Thing s) {
                deleteData(s);
            }

            @Override
            public void onItemLongClick(Thing s) {
                String message = s.getMessage();
                if (message.startsWith("已经")) {
                    CharSequence sequence = message.subSequence(6, message.length());
                    String toString = sequence.toString();
                    s.setMessage(toString);
                } else {
                    s.setMessage("已经被修改了" + s.getMessage());
                }

                s.setTime(System.currentTimeMillis());
                updataData(s);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mADataBaseRecyclerView.setLayoutManager(mLinearLayoutManager);
        mADataBaseRecyclerView.setAdapter(mDataBaseAdapter);
    }


    @OnClick({R.id.a_database_submit_btn, R.id.a_database_search_btn, R.id.a_database_delete_all, R.id.a_database_show_all_data_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_database_submit_btn:
                String content = mADataBaseSubmitEt.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    insertData(null);
                } else {
                    insertData(new Thing(content, System.currentTimeMillis()));
                }
                break;
            case R.id.a_database_search_btn:
                queryData(mADataBaseSearchEt.getText().toString().trim());
                break;
            case R.id.a_database_delete_all:
                deleteAll();
                break;
            case R.id.a_database_show_all_data_tv:
                refreshAdapter(mCuurType);
                break;

        }
    }

    private int mCuurType = GREEN_DAO;

    public void refreshAdapter(int type) {
        mCuurType = type;
        if (mDataBaseAdapter != null) {
            mDataBaseAdapter.refreshData(type);
        }
    }

    public void deleteAll() {
    }

    public void insertData(Thing s) {
        mDataBaseAdapter.addThingData(s);
    }

    ;

    public abstract void updataData(Thing s);

    public abstract void deleteData(Thing s);

    public abstract void queryData(String s);

    public abstract void initDatabase();
}
