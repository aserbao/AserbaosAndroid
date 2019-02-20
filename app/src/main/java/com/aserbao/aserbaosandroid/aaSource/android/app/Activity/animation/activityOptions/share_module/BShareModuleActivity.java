package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BShareModuleActivity extends AppCompatActivity {

    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;

    public static void launch(Activity activity, View imageView) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        if (imageView instanceof Button){
            intent.putExtra(StaticFinalValues.TYPE,1);
        }
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, imageView, "aserbao_share_name").toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int intExtra = getIntent().getIntExtra(StaticFinalValues.TYPE, 0);
        if (intExtra == 0){
            setContentView(R.layout.activity_b_share_module);
        }else{
            setContentView(R.layout.activity_b_share_module2);
        }
        ButterKnife.bind(this);
        mShowActivityNameTv.setText("BShareModuleActivity");
    }

    @OnClick(R.id.b_share_module_civ)
    public void onViewClicked() {
        onBackPressed();
    }
}
