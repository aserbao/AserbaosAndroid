package com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.adapters.MoveToDeleteAda;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveToDeleteActivity extends AppCompatActivity {

    @BindView(R.id.move_to_delete_rv)
    RecyclerView mMoveToDeleteRv;
    @BindView(R.id.move_to_btn)
    Button moveToBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_to_delete);
        ButterKnife.bind(this);
        initRecyclerView(mMoveToDeleteRv);
        moveToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop();
            }
        });

    }

    private void initPop() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_move_to_delete, null);
        PopupWindow popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(moveToBtn,0,0, Gravity.RIGHT);
        initRecyclerView(((RecyclerView) rootView.findViewById(R.id.move_to_delete_rv)));
    }

    private void initRecyclerView(RecyclerView moveToDeleteRv) {
        MoveToDeleteAda moveToDeleteAda = new MoveToDeleteAda(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moveToDeleteRv.setLayoutManager(linearLayoutManager);
        moveToDeleteRv.setAdapter(moveToDeleteAda);
    }


}
