package com.aserbao.aserbaosandroid.ui.animation.moveAnimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot.MoveView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_anim);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
    }


    @OnClick(R.id.move_btn)
    public void onViewClicked() {
        mMoveView.start(mMoveAnimIv);
//        mMoveView.addShot(bitmap, screenWidth/2, screenHeight,90);
    }
}
