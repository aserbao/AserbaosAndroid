package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.PathMotion;
import android.transition.TransitionSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.example.base.commonData.ASourceUtil;
import com.example.base.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.functions.events.MoveDragActivity;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BShareModuleActivity extends MoveDragActivity {


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

    public static void launch(Activity activity, View view, int whichLinearInterpolator) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        if (view instanceof Button) {
            intent.putExtra(StaticFinalValues.TYPE, BUTTON);
        }
        intent.putExtra(StaticFinalValues.WHICH_INTERPOLATOR, whichLinearInterpolator);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, "aserbao_share_name").toBundle());
    }

    public static void launch( int whichLinearInterpolator,Activity activity, View view,int position, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(activity, BShareModuleActivity.class);
        intent.putExtra(StaticFinalValues.TYPE, RECYCLER_ITEM);
        intent.putExtra(StaticFinalValues.POSITION,position);
        intent.putExtra(StaticFinalValues.WHICH_INTERPOLATOR, whichLinearInterpolator);
        activity.startActivityForResult(intent, StaticFinalValues.COME_FROM_A_SHARE_MODULE_ACTIVITY,ActivityOptions.makeSceneTransitionAnimation(activity, sharedElements).toBundle());
    }

    private static final String TAG = "BShareModuleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra(StaticFinalValues.WHICH_INTERPOLATOR, -1);
        if (intExtra >= 0) {
            setTransiton(intExtra);
        }
        super.onCreate(savedInstanceState);
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

    @Override
    public void onViewClick(MotionEvent event) {

    }

    @Override
    public void onViewLongClick(MotionEvent event) {

    }

    @Override
    public View getContentView() {
        return mBShareModuleFl;
    }

    private void setImageRes() {
        if (position < ASourceUtil.imageUrls.length ) {
            int resid = ASourceUtil.imageUrls[position];
            mBShareModuleFl.setBackgroundResource(resid);
            mBShareModuleCiv.setImageResource(resid);
        }else{
            Toast.makeText(this, "到顶了", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTransiton(int interpolator) {
        Log.e(TAG, "launch: " + interpolator);
        TransitionSet transition = new TransitionSet();
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(1000);
        changeBounds.setInterpolator(ASourceUtil.getCuurSelectedInterpolator(interpolator));
        switch (interpolator){
            case StaticFinalValues.PathMotion:
                changeBounds.setPathMotion(new PathMotion() {
                    @Override
                    public Path getPath(float startX, float startY, float endX, float endY) {
                        Log.e(TAG, "getPath() called with: startX = [" + startX + "], startY = [" + startY + "], endX = [" + endX + "], endY = [" + endY + "]");
                        Path p = new Path();
                        p.moveTo(startX, startY);
                      /*  p.lineTo(endX, startY);
                        p.lineTo(startX, endY);
                        p.lineTo(endX, endY);*/

//                        p.quadTo(endX + (startX - endX)/2, endY + DisplayUtil.dp2px(200), endX, endY);
//                        p.quadTo(startX, 2 * endY, endX, endY);
                        p.quadTo(AserbaoApplication.screenWidth/2, AserbaoApplication.screenHeight, endX, endY);

                        return p;
                    }
                });
                changeBounds.setDuration(1000);
                break;
            case StaticFinalValues.AcrMotion:
                ArcMotion arcMotion = new ArcMotion();
                arcMotion.setMinimumHorizontalAngle(50f);
                arcMotion.setMinimumVerticalAngle(50f);
                changeBounds.setPathMotion(arcMotion);
                break;
        }
        transition.addTransition(changeBounds);
        getWindow().setSharedElementEnterTransition(transition);
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
                mBShareModuleFl.setTransitionName(String.valueOf(ASourceUtil.imageUrls[position]));
//                mBShareModuleCiv.setTransitionName(String.valueOf(ASourceUtil.imageUrls[position]));
                
                break;
        }
    }
}
