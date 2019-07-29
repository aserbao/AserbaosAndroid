package com.aserbao.aserbaosandroid.ui.animation.moveAnimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.IShotListener;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.MoveView;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.Shot;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.TargetView;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.ViewManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Math.PI;

public class MoveAnimActivity extends AppCompatActivity implements IShotListener{

    @BindView(R.id.move_btn)
    Button mMoveBtn;
    @BindView(R.id.move_view)
    MoveView mMoveView;
    @BindView(R.id.target_fl)
    FrameLayout mTargetFl;
    @BindView(R.id.move_frame_layout)
    FrameLayout mMoveFrameLayout;
    private Bitmap bitmap;
    private ViewManager viewManager;

    private int screenHeight, screenWidth = 0;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_anim);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        viewManager = new ViewManager(this,mMoveView, this,this);

        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);
        mRandom = new Random();
    }
    private List<TargetView> mTargetViewList = new ArrayList<>();
    public void addRandomIV() {
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.emoji_00);
            int x = mRandom.nextInt(screenWidth);
            int y = mRandom.nextInt(screenWidth);
            imageView.setX(x);
            imageView.setY(y);
            float[] s = new float[3];
            s[0] = x + 25;
            s[1] = y + 25;
            s[2] = 25;
            mTargetFl.addView(imageView,50,50);
            mTargetViewList.add( new TargetView(imageView,s));
        }
    }

    @OnClick({R.id.move_btn, R.id.move_add_iv_btn,R.id.move_clear_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.move_btn:
                for (int i = 0; i < 5; i++) {
                    int angle = mRandom.nextInt(135)%(135-50 +1) + 45;
                    int shotX = screenWidth / 2;
                    int shotY = this.screenHeight;
                    float[] floats = calculateLine(shotX, shotY, angle, mTargetViewList);
                    int targetX = (int)floats[0];
                    int targetY = (int)floats[1];
                    int targetRadius = (int)floats[2];
                    viewManager.addShot(bitmap, shotX, shotY, angle, targetX, targetY, targetRadius,mTargetViewList);
                }
                break;
            case R.id.move_add_iv_btn:
                addRandomIV();
                break;
            case R.id.move_clear_btn:
                mTargetFl.removeAllViews();
                mTargetViewList.clear();
                break;
        }
    }

    private static final String TAG = "MoveAnimActivity";

    public float[] calculateLine(int shotX,int shotY,float shotAngle,List<TargetView> targetData){
        float[] resultFloat = new float[3];
        float cuurMaxY = 0;
        for (int i = 0; i < targetData.size(); i++) {
            float[] floats = targetData.get(i).getFloats();
            float x = floats[0];
            float y = floats[1];
            float radius = floats[2];

            float y1 = Math.abs(shotY - y);
            float x1 = Math.abs(shotX - x);
            double atan2 = Math.atan2(y1, x1);
            double cuuViewAngle = atan2 * (180 / Math.PI);
            /*if (x >= screenWidth /2 && shotAngle > 90|| x <= screenWidth /2 && shotAngle < 90){
                Log.e(TAG, "calculateLine: 忽略不计" );
                    continue;
            }*/
            double diffAngle = Math.abs(shotAngle - cuuViewAngle);
            double pow1 = Math.pow(x1, 2);
            double pow2 = Math.pow(y1, 2);
            double sqrt = Math.sqrt(pow1 + pow2);
            double sin = Math.sin((float) (2 * PI / 360 * diffAngle));
            double shortDistance = sqrt * sin;
            Log.e(TAG, "calculateLine: " + shortDistance);
            if (shortDistance <= radius + 30 && cuurMaxY < y){
                cuurMaxY  = y;
                resultFloat = floats;
            }
        }
        return resultFloat;
    }

    @Override
    public void isHit(Shot shot, TargetView targetView) {
        mTargetFl.removeView(targetView.getImageView());
    }

    @Override
    public void isLoseEfficacy(Shot shot) {

    }
}
