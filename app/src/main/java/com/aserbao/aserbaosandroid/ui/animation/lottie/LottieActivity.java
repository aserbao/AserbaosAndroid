package com.aserbao.aserbaosandroid.ui.animation.lottie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LottieActivity extends AppCompatActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationView.post(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        });
    }

    public void startAnimation() {
        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
//        animationView.setAnimation("hello-world.json");//在assets目录下的动画json文件名。
        animationView.loop(true);//设置动画循环播放
//        animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
        animationView.playAnimation();//播放动画
    }
}
