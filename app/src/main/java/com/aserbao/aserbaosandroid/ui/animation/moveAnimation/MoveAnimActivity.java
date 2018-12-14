package com.aserbao.aserbaosandroid.ui.animation.moveAnimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.MoveView;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.ViewManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Math.PI;

public class MoveAnimActivity extends AppCompatActivity {

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
        viewManager = new ViewManager(this,mMoveView, this);

        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);
        mRandom = new Random();
    }
    private List<float[]> mTargetData = new ArrayList<>();
    private List<ImageView> mImageViewList = new ArrayList<>();
    public void addRandomIV() {
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.emoji_00);
            int x = mRandom.nextInt(screenWidth);
            int y = mRandom.nextInt(screenWidth);
            imageView.setX(x);
            imageView.setY(y);
          /*  int x = screenWidth / 100 * (i + 1);
            int y = 200;
            imageView.setX(x);
            imageView.setY(200);*/
            mTargetFl.addView(imageView, 50,50);
            mImageViewList.add(imageView);
            float[] s = new float[3];
            s[0] = x + 25;
            s[1] = y + 25;
            s[2] = 25;
            mTargetData.add(s);
        }
    }

    public void getImageViewData(){
        for (int i = 0; i < mImageViewList.size(); i++) {
            ImageView imageView = mImageViewList.get(i);
            float x = imageView.getX();
            float y = imageView.getY();
            float[] s = new float[3];
            s[0] = x + imageView.getWidth() / 2;
            s[1] = y + imageView.getHeight() / 2;
            s[2] = imageView.getHeight() /2;
            mTargetData.add(s);
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
                    float[] floats = calculateLine(shotX, shotY, angle, mTargetData);
                    int targetX = (int)floats[0];
                    int targetY = (int)floats[1];
                    int targetRadius = (int)floats[2];
                    viewManager.addShot(bitmap, shotX, shotY, angle, targetX, targetY, targetRadius,mTargetData);
                }
                break;
            case R.id.move_add_iv_btn:
                addRandomIV();
                break;
            case R.id.move_clear_btn:
                mTargetFl.removeAllViews();
                mImageViewList.clear();
                mTargetData.clear();
                break;
        }
    }

    private static final String TAG = "MoveAnimActivity";

    public float[] calculateLine(int shotX,int shotY,float shotAngle,List<float[]> targetData){
        float[] resultFloat = new float[3];
        float cuurMaxY = 0;
        for (int i = 0; i < targetData.size(); i++) {
            float[] floats = targetData.get(i);
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

    public void fromShot(int shootX,int shootY, float angle){

        float mAngle = 0;
        int halfScreenWidth = AserbaoApplication.screenWidth / 2;
        int halfScreenHeight = AserbaoApplication.screenHeight / 2;
        float receiveX = halfScreenWidth;
        float receiveY = halfScreenHeight * 2;
        float x,y = 0;
        float shotX = shootX;
        float shotY = shootY;
        if(shotX > 0 && shotX < AserbaoApplication.screenWidth && shotY > 0 && shotY < AserbaoApplication.screenHeight){
            x = shotX;
            y = shotY;
        }else {
            if (angle == 90) {
                x = receiveX;
                y = 0;
            } else if (angle == 0) {
                x = AserbaoApplication.screenWidth;
                y = receiveY;
            } else if (angle == 180) {
                x = 0;
                y = receiveY;
            } else if (angle == 270) {
                x = receiveX;
                y = AserbaoApplication.screenHeight;
            } else if (receiveY / halfScreenWidth == Math.tan(mAngle)) {
                if (angle < 90 || angle > 270 && angle < 360) {
                    x = AserbaoApplication.screenWidth;
                } else {
                    x = 0;
                }
                if (angle > 180) {
                    y = AserbaoApplication.screenHeight;
                } else {
                    y = 0;
                }
            } else if (receiveY / halfScreenWidth < Math.tan(mAngle)) {
                if (angle < 180) {
                    if (angle > 90) {
                        mAngle = 180 - angle;
                    }
                    mAngle = (float) (2 * PI / 360 * mAngle);
                    y = (float) (halfScreenWidth * Math.tan(mAngle));
                } else if (angle > 180 && angle < 360) {
                    if (angle < 270) {
                        mAngle = angle - 180;
                    } else {
                        mAngle = 360 - angle;
                    }
                    mAngle = (float) (2 * PI / 360 * mAngle);
                    y = receiveY + (float) (halfScreenWidth * Math.tan(mAngle));
                }
                if (angle < 90 || angle > 270 && angle < 360) {
                    x = AserbaoApplication.screenWidth;
                } else {
                    x = 0;
                }
            } else {
                if (angle < 90 && angle > 270 && angle < 360) {
                    if (angle > 270) {
                        mAngle = 360 - angle;
                    }
                    mAngle = (float) (2 * PI / 360 * mAngle);
                    x = halfScreenWidth + (float) (halfScreenHeight / Math.tan(mAngle));
                } else {
                    if (angle < 180) {
                        mAngle = 180 - angle;
                    } else {
                        mAngle = angle - 180;
                    }
                    mAngle = (float) (2 * PI / 360 * mAngle);
                    x = (float) (halfScreenHeight / Math.tan(mAngle));
                }

                if (angle < 180) {
                    y = 0;
                } else {
                    y = AserbaoApplication.screenHeight;
                }
            }
        }

        float[] floats = new float[3];
        floats[0] = receiveX;
        floats[1] = receiveY;
        floats[2] = 70;



//        ViewManager.getInstance(this, mMoveView, this).addShot(mCurrentBulletBitmap, x, y, angle, markerBeans);
    }
}
