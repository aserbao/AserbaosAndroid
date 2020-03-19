package com.aserbao.aserbaosandroid.designMode.strategyMode;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.designMode.strategyMode.attack.IAttackBehavior;
import com.aserbao.aserbaosandroid.designMode.strategyMode.attack.OrdinaryAttack;
import com.aserbao.aserbaosandroid.designMode.strategyMode.attack.ReinforceAttack;
import com.aserbao.aserbaosandroid.designMode.strategyMode.attack.SuperAttack;
import com.aserbao.aserbaosandroid.designMode.strategyMode.speed.FastSpeed;
import com.aserbao.aserbaosandroid.designMode.strategyMode.speed.ISpeedBehavior;
import com.aserbao.aserbaosandroid.designMode.strategyMode.speed.NormalSpeed;
import com.aserbao.aserbaosandroid.designMode.strategyMode.speed.SlowSpeed;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 策略模式
 */
public class StrategyActivity extends AppCompatActivity {


    @BindView(R.id.zombie_tv)
    TextView mZombieTv;
    @BindView(R.id.speed_tv)
    TextView mSpeedTv;
    @BindView(R.id.attack_tv)
    TextView mAttackTv;

    private Character mCharacter;
    private IAttackBehavior mIAttackBehavior;
    private ISpeedBehavior mISpeedBehavior;
    private int mRandomZombieNum = 0;
    private int mRandomSpeedNum = 0;
    private int mRandomAttackNum = 0;
    private Random mRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        ButterKnife.bind(this);
        //默认设置
        mIAttackBehavior = new OrdinaryAttack();
        mISpeedBehavior = new NormalSpeed();
        mCharacter = new RedHeadZombie(mIAttackBehavior,mISpeedBehavior);
        mRandom = new Random();
    }

    @OnClick({R.id.btn_change_zombie, R.id.btn_change_speed, R.id.btn_change_attack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_zombie:
                mRandomZombieNum = mRandom.nextInt(4);
                switch (mRandomZombieNum){
                    case 0:
                        mCharacter = new RedHeadZombie(mIAttackBehavior,mISpeedBehavior);
                        mZombieTv.setText("红头僵尸");
                        break;
                    case 1:
                        mCharacter = new GreenHeadZombie(mIAttackBehavior,mISpeedBehavior);
                        mZombieTv.setText("绿头僵尸");
                        break;
                    case 2:
                        mCharacter = new ShortLegZombie(mIAttackBehavior,mISpeedBehavior);
                        mZombieTv.setText("短腿僵尸");
                        break;
                    case 3:
                        mCharacter = new NoAttackZombie(mIAttackBehavior,mISpeedBehavior);
                        mZombieTv.setText("无攻击力僵尸");
                        break;
                }
                mCharacter.attack();
                mCharacter.speed();
                break;
            case R.id.btn_change_speed:
                mRandomSpeedNum = mRandom.nextInt(3);
                switch (mRandomSpeedNum){
                    case 0:
                        mISpeedBehavior = new NormalSpeed();
                        mSpeedTv.setText("My speed is normal");
                        break;
                    case 1:
                        mISpeedBehavior = new SlowSpeed();
                        mSpeedTv.setText("My speed is slow");
                        break;
                    case 2:
                        mISpeedBehavior = new FastSpeed();
                        mSpeedTv.setText("My speed is fast");
                        break;
                }
                mCharacter.setISpeedBehavior(mISpeedBehavior);
                mCharacter.speed();
                break;
            case R.id.btn_change_attack:
                mRandomAttackNum = mRandom.nextInt(3);
                switch (mRandomAttackNum){
                    case 0:
                        mIAttackBehavior = new OrdinaryAttack();
                        mAttackTv.setText("I use ordinary rtdinary attack");
                        break;
                    case 1:
                        mIAttackBehavior = new ReinforceAttack();
                        mAttackTv.setText("I use ordinary reinforce attack");
                        break;
                    case 2:
                        mIAttackBehavior = new SuperAttack();
                        mAttackTv.setText("I use ordinary super attack");
                        break;
                    case 3:
                        break;
                }
                mCharacter.setIAttackBehavior(mIAttackBehavior);
                mCharacter.attack();
                break;
        }
    }
}
