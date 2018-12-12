package com.aserbao.aserbaosandroid.ui.animation.moveAnimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.MoveView;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.ViewManager;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoveAnimActivity extends AppCompatActivity {

    @BindView(R.id.move_btn)
    Button mMoveBtn;
    @BindView(R.id.move_view)
    MoveView mMoveView;
    @BindView(R.id.move_anim_iv)
    ImageView mMoveAnimIv;
    private Bitmap bitmap;
    private ViewManager viewManager;

    private int screenHeight,screenWidth = 0;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_anim);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        viewManager = new ViewManager(mMoveView, this);

        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);
        mRandom = new Random();
    }


    @OnClick(R.id.move_btn)
    public void onViewClicked() {
        int angle = mRandom.nextInt(40) + 70;
        int targetx = (screenWidth/2 - 100 + mRandom.nextInt(200));
        viewManager.addShot(bitmap,screenWidth/2, screenHeight,angle,targetx,screenHeight / (new Random().nextInt(5)+1),25);
//        mMoveView.addShot(bitmap, screenWidth/2, screenHeight,90);
    }
}
