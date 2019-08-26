package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.commonData.ImageSource;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BShareModuleActivity extends AppCompatActivity {


    @BindView(R.id.b_share_module_fl)
    FrameLayout mBShareModuleFl;
    @BindView(R.id.b_share_module_civ)
    CircleImageView mBShareModuleCiv;
    @BindView(R.id.show_activity_name_tv)
    TextView mShowActivityNameTv;
    public static final int IMAGE_VIEW = 0;
    public static final int BUTTON = 1;
    public static final int RECYCLER_ITEM = 2;
    private int inputType;
    private int position;

    public static void launch(Activity activity, View view) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        if (view instanceof Button) {
            intent.putExtra(StaticFinalValues.TYPE, BUTTON);
        }
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, "aserbao_share_name").toBundle());
    }

    public static void launch(Activity activity, View view,int position, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, RECYCLER_ITEM);
        intent.putExtra(StaticFinalValues.POSITION,position);
        activity.startActivityForResult(intent, StaticFinalValues.COME_FROM_A_SHARE_MODULE_ACTIVITY,ActivityOptions.makeSceneTransitionAnimation(activity, sharedElements).toBundle());
    }




    private static final String TAG = "BShareModuleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        inputType = intent.getIntExtra(StaticFinalValues.TYPE, 0);
        position = intent.getIntExtra(StaticFinalValues.POSITION, 0);
        setContentView(R.layout.activity_b_share_module);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate: " + inputType);
        setImageRes();
        setTransitionName(COMING);
        mShowActivityNameTv.setText("BShareModuleActivity");
        setEnterSharedElementCallback(new SharedElementCallback() {
        });
    }

    private void setImageRes() {
        if (position <ImageSource.iamgeUrl.length ) {
            int resid = ImageSource.iamgeUrl[position];
            mBShareModuleFl.setBackgroundResource(resid);
            mBShareModuleCiv.setImageResource(resid);
        }else{
            Toast.makeText(this, "到顶了", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.b_share_module_civ)
    public void onViewClicked() {
        switch (inputType){
            case IMAGE_VIEW:
            case BUTTON:
                onBackPressed();
                break;
            case RECYCLER_ITEM:
                position++;
                setImageRes();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setTransitionName(BACK);
        AShareModuleActivity.endPosition = position;
        setResult(StaticFinalValues.COME_FROM_B_SHARE_MODULE_ACTIVITY);
        super.onBackPressed();
    }

    public static final int COMING = 0;
    public static final int BACK = 1;
    private void setTransitionName(int comeFrom) {
        switch (inputType){
            case IMAGE_VIEW:
                mBShareModuleCiv.setTransitionName("aserbao_share_name");
                break;
            case BUTTON:
                mBShareModuleFl.setTransitionName("aserbao_share_name");
                break;
            case RECYCLER_ITEM:
                mBShareModuleFl.setTransitionName(String.valueOf(ImageSource.iamgeUrl[position]));
//                mBShareModuleCiv.setTransitionName(String.valueOf(ImageSource.iamgeUrl[position]));
                
                break;
        }
    }
}
