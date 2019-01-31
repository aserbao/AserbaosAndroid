package com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.adapters.PopSimpleDemoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupWindowSimpleDemo extends AppCompatActivity {

    @BindView(R.id.pop_simple_container)
    FrameLayout mPopSimpleContainer;
    private PopupWindow mPopupWindow;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_demo);
        ButterKnife.bind(this);
        mContext = this;
//        init();
        createPop();
    }



    @NonNull
    private View getView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_simple_demo, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pop_simple_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        ArrayList<PopBean> list = new ArrayList<>();
        for (int i = 0; i < sValues.length; i++) {
            list.add(new PopBean(sValues[i]));
        }
        PopSimpleDemoAdapter popSimpleDemoAdapter = new PopSimpleDemoAdapter(mContext, list, new PopSimpleDemoAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position) {
                switch (position) {
                    case 0:
                        mPopupWindow.dismiss();
                        mPopupWindow.showAtLocation(mPopSimpleContainer, Gravity.LEFT, 0, 0);
                        break;
                    case 1:
                        mPopupWindow.dismiss();
                        mPopupWindow.showAtLocation(mPopSimpleContainer, Gravity.TOP, 0, 0);
                        break;
                    case 2:
                        mPopupWindow.dismiss();
                        mPopupWindow.showAtLocation(mPopSimpleContainer, Gravity.CENTER, 0, 0);
                        break;
                    case 3:
                        mPopupWindow.dismiss();
                        mPopupWindow.showAtLocation(mPopSimpleContainer, Gravity.RIGHT, 0, 0);
                        break;
                    case 4:
                        mPopupWindow.dismiss();
                        mPopupWindow.showAtLocation(mPopSimpleContainer, Gravity.BOTTOM, 0, 0);
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(popSimpleDemoAdapter);
        return view;
    }

    public void createPop() {
        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(getView());
        mPopSimpleContainer.post(new Runnable() {
            @Override
            public void run() {
                mPopupWindow.showAtLocation(mPopSimpleContainer,Gravity.CENTER,0,0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private String[] sValues = {"LEFT", "TOP", "CENTER","RIGHT", "BOTTOM"};

    @OnClick({R.id.show_left, R.id.show_top, R.id.show_right, R.id.show_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_left:
                mPopupWindow.showAtLocation(mPopSimpleContainer,Gravity.CENTER,0,0);
                break;
            case R.id.show_top:
                break;
            case R.id.show_right:
                break;
            case R.id.show_bottom:
                break;
        }
    }
}
