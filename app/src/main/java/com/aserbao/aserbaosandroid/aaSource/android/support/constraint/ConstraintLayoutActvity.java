package com.aserbao.aserbaosandroid.aaSource.android.support.constraint;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstraintLayoutActvity extends AppCompatActivity {

    @BindView(R.id.button22)
    Button mButton22;
    @BindView(R.id.btn_group_1)
    Button mButton23;
    @BindView(R.id.group)
    Group mGroup;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.share_map_layout_iv)
    ImageView mShareMapLayoutIv;
    @BindView(R.id.share_map_layout_shop_info_fl_container)
    FrameLayout mShareMapLayoutShopInfoFlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout_1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_group_1, R.id.btn_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_group_1:
                addView();
//                mGroup.setVisibility(View.GONE);
                break;
            case R.id.btn_3:
                mGroup.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void addView(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.emoji_00);
        mShareMapLayoutShopInfoFlContainer.addView(imageView);
    }
}
