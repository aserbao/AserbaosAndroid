package com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.adapters.MoveToDeleteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoveToDeleteActivity extends AppCompatActivity {

    @BindView(R.id.move_to_delete_rv)
    RecyclerView mMoveToDeleteRv;
    @BindView(R.id.move_to_btn)
    Button moveToBtn;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_to_delete);
        ButterKnife.bind(this);
        initRecyclerView(mMoveToDeleteRv);
    }

    private void initPop() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_move_to_delete, null);
        PopupWindow popupWindow = new PopupWindow(rootView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(moveToBtn, 0, 0, Gravity.RIGHT);
        initRecyclerView(((RecyclerView) rootView.findViewById(R.id.move_to_delete_rv)));
    }

    private void initRecyclerView(RecyclerView moveToDeleteRv) {
        MoveToDeleteAdapter moveToDeleteAda = new MoveToDeleteAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moveToDeleteRv.setLayoutManager(linearLayoutManager);
        moveToDeleteRv.setAdapter(moveToDeleteAda);
    }


    @OnClick({R.id.move_to_btn, R.id.scroll_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.move_to_btn:
                initPop();
                break;
            case R.id.scroll_btn:
                mMoveToDeleteRv.scrollToPosition(0);
                break;
        }
    }
}
