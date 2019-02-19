package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.byOverridePendingTransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AAnimationActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.base_recycler_tv)
    TextView mBaseRecyclerTv;

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, AAnimationActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_animation);
        ButterKnife.bind(this);
        mBaseRecyclerTv.setText("A");
        overridePendintAnimation(getIntent().getIntExtra(StaticFinalValues.TYPE, 0));
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("从上出现转到B"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("从下出现转到B"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("从左出现转到B"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("从右出现转到B"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("淡入淡出"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("放大缩小"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("转动淡出效果"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("左上角淡出效果"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("压缩变小效果"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("交错效果"));
    }

    @Override
    public void itemClickBack(int position){
        AAnimationActivity.launch(this, position);
    }

    private void overridePendintAnimation(int type) {
        switch (type) {
            case 0:
                overridePendingTransition(R.anim.activity_left_to_screen_anim, R.anim.activity_screen_to_right_anim);
                break;
            case 1:
                overridePendingTransition(R.anim.activity_top_to_screen_anim, R.anim.activity_screen_to_bottom_anim);
                break;
            case 2:
                overridePendingTransition(R.anim.activity_right_to_screen_anim, R.anim.activity_screen_to_left_anim);
                break;
            case 3:
                overridePendingTransition(R.anim.activity_bottom_to_screen_anim, R.anim.activity_screen_to_top_anim);
                break;
            case 4:
                overridePendingTransition(R.anim.activity_alpha0_to_alpha1_anim, R.anim.activity_alpha1_to_alpha0_anim);
                break;
            case 5:
                overridePendingTransition(R.anim.activity_scale0_to_scale1_anim, R.anim.activity_scale1_to_scale0_anim);
                break;
            case 6:
                overridePendingTransition(R.anim.activity_scale021_and_rotate_anim, R.anim.activity_scale120_and_rotate_anim);
                break;
            case 7:
                overridePendingTransition(R.anim.activity_scale021_and_translate_anim, R.anim.activity_scale120_and_translate_anim);
                break;
        }
    }
}
