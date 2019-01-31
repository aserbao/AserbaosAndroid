package com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.android.widget.Classes.popupwindow.demo.adapters.PopSimpleDemoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.PopupWindow.INPUT_METHOD_FROM_FOCUSABLE;

public class PopupWindowSimpleDemo extends AppCompatActivity {

    @BindView(R.id.pop_simple_container)
    FrameLayout mPopSimpleContainer;
    @BindView(R.id.pop_center_btn)
    Button mPopCenterBtn;
    private PopupWindow mPopupWindow;
    private Context mContext;
    private boolean isAttachView = false;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_demo);
        ButterKnife.bind(this);
        mContext = this;
        createPop();
    }

    public void xmlAttr() {
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));                  // 部分机型点击返回键PopupWindow不消失，可以通过设置此方法解决
        mPopupWindow.setAnimationStyle(R.anim.quit_fullscreen);                             // 设置动画
        mPopupWindow.setElevation(4.0f);                                                    // 设置elevation
        mPopupWindow.setOverlapAnchor(true);                                                // 是否遮盖附着View？默认是false。
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
                        showPop(Gravity.LEFT);
                        break;
                    case 1:
                        showPop(Gravity.TOP);
                        break;
                    case 2:
                        showPop(Gravity.CENTER);
                        break;
                    case 3:
                        showPop(Gravity.RIGHT);
                        break;
                    case 4:
                        showPop(Gravity.BOTTOM);
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(popSimpleDemoAdapter);
        return view;
    }

    public void showPop(int gravity){
        mPopupWindow.dismiss();
        if (isAttachView){
            int x =0 ,y = 0;
            int[] ints = new int[2];
            mPopCenterBtn.getLocationOnScreen(ints);
            switch (gravity){
                case Gravity.LEFT:
                    x = ints[0] - view.getMeasuredWidth() ;
                    y = ints[1] - view.getMeasuredHeight()/2;
                    break;
                case Gravity.TOP:
                    x = ints[0] + mPopCenterBtn.getWidth()/2 - view.getMeasuredWidth()/2;
                    y = ints[1] - view.getMeasuredHeight() - mPopCenterBtn.getHeight()/2;
                    break;
                case Gravity.CENTER:
                    x = ints[0] + mPopCenterBtn.getWidth()/2 - view.getMeasuredWidth()/2;
                    y = ints[1] - view.getMeasuredHeight()/2;
                    break;
                case Gravity.RIGHT:
                    x = ints[0] + mPopCenterBtn.getWidth() ;
                    y = ints[1] - view.getMeasuredHeight()/2;
                    break;
                case Gravity.BOTTOM:
                    x = ints[0] + mPopCenterBtn.getWidth()/2 - view.getMeasuredWidth()/2;
                    y = ints[1] + mPopCenterBtn.getHeight()/2;
                    break;
            }
            mPopupWindow.showAtLocation(mPopCenterBtn,Gravity.NO_GRAVITY,x,y);
        }else {
            mPopupWindow.showAtLocation(mPopSimpleContainer,gravity, 0, 0);
        }
    }

    public void createPop() {
        view = getView();
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(false);     //点外部是否消失
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.TOP);
        mPopupWindow.setEnterTransition(slide);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private String[] sValues = {"LEFT", "TOP", "CENTER", "RIGHT", "BOTTOM"};

    @OnClick({R.id.pop_simple_btn, R.id.pop_center_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pop_simple_btn:
                isAttachView = false;
                showPop(Gravity.CENTER);
                break;
            case R.id.pop_center_btn:
                isAttachView = true;
                showPop(Gravity.TOP);
                break;
        }
    }
}
