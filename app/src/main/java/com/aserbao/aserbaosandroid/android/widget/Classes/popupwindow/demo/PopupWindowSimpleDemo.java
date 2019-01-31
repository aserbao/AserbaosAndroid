package com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.adapters.PopSimpleDemoAdapter;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.PopupWindow.INPUT_METHOD_FROM_FOCUSABLE;

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

    public void xmlAttr(){
        mPopupWindow.setOverlapAnchor(true);                                                // 是否遮盖附着View？默认是false。
        mPopupWindow.setAnimationStyle(R.anim.quit_fullscreen);                             // 设置动画
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));                  // 部分机型点击返回键PopupWindow不消失，可以通过设置此方法解决
        mPopupWindow.setElevation(4.0f);                                                    // 设置elevation
        mPopupWindow.setEnterTransition(new Slide());                                       // 设置显示动画 系统动画有三种Explode,Slide,Fade
        mPopupWindow.setExitTransition(new Slide());                                        // 设置退出动画 系统动画有三种Explode,Slide,Fade
        mPopupWindow.setSoftInputMode(INPUT_METHOD_FROM_FOCUSABLE);                         // Popup和输入法之间的设置联系
        mPopupWindow.dismiss();                                                             // 消失
        mPopupWindow.setClippingEnabled(true);                                              // 是否允许父布局对Pop进行裁剪，默认为true
        mPopupWindow.setFocusable(true);                                                    // 是否能聚焦
        mPopupWindow.setOutsideTouchable(true);                                             // 是否点击外部可以消失
        mPopupWindow.isShowing();                                                           // 判断Popup是否显示？
        mPopupWindow.setTouchable(false);                                                   // 是否有touch事件 默认为true
        mPopupWindow.setOnDismissListener(null);                                            // 消失监听
        mPopupWindow.setWindowLayoutType();
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
      /*  mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(getView());*/


        mPopupWindow = new PopupWindow(getView(),ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(false);     //点外部是否消失
        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setSlideEdge(Gravity.TOP);
        mPopupWindow.setEnterTransition(slide);
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
