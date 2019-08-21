package com.aserbao.aserbaosandroid.ui.toasts.aCustomToast;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ClickToast.ClickToast;

public class ACustomToastActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("简单的Toast"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带点击的Toast"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("自定义Toast"));
    }


    /*@OnClick({R.id.iamge_btn, R.id.second_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iamge_btn:
                View rootView = LayoutInflater.from(this).inflate(R.layout.toast_item_1, null);
                final ImageView mImageView = (ImageView) rootView.findViewById(R.id.toast_item_iv);
                rootView.findViewById(R.id.toast_item_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageView.setVisibility(View.GONE);
                    }
                });
                ClickToast toast = new ClickToast();
                *//*toast.setView(rootView);
                toast.setDuration(Toast.LENGTH_LONG);*//*
//                toast.show(toast);
                View toastv = toast.show(mContext, Toast.LENGTH_LONG, R.layout.toast_item_1);


                *//*ImageView imageView = new ImageView(this);
                Glide.with(this).load(StaticFinalValues.IMAGE_URL).into(imageView);
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();*//*

                break;

        }
    }*/


    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                Toast.makeText(mContext, "This is What?", 10 * 1000).show();
                break;
            case 1:
                // TODO: 2019/4/25 点击多次就蹦了
                ClickToast clickToast = new ClickToast();
                final View toastClickView = clickToast.show(mContext, Toast.LENGTH_LONG, R.layout.toast_item_1);
                toastClickView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "动动我试试", Toast.LENGTH_SHORT).show();
                        toastClickView.findViewById(R.id.toast_item_iv).setVisibility(View.GONE);
                    }
                });
                break;
            case 2:
                View rootView = LayoutInflater.from(this).inflate(R.layout.toast_item_1, null);
                Toast toast2 = new Toast(getApplicationContext());
                toast2.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
                toast2.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
                toast2.setView(rootView); //添加视图文件
                toast2.show();
                break;
        }
    }
}
