package com.aserbao.aserbaosandroid.aaThird.dagger2;

import android.view.View;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.LazyMan;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Restaurant;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SendFoodMan;
import com.aserbao.aserbaosandroid.aaThird.dagger2.simple.Student;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.buttons.ButtonActivity;

import javax.inject.Inject;

public class DaggerActivity extends BaseRecyclerViewActivity {

    @Inject
    Restaurant restaurant1;
    @Inject
    SendFoodMan sendFoodMan1;
    @Inject
    LazyMan lazyMan1;



    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("不使用Dagger2的做法"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Dagger2"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("跳"));
    }

    private static final String TAG = "DaggerActivity";
    @Override
    public void itemClickBack(View view , int position) {
        switch (position){
            case 0:
                Restaurant restaurant = new Restaurant();
                restaurant.cooking();
                SendFoodMan sendFoodMan = new SendFoodMan();
                sendFoodMan.getFood();
                sendFoodMan.sendFood();
                sendFoodMan.finishSendFood();
                LazyMan lazyMan = new LazyMan();
                lazyMan.receiveFood();
                break;
            case 1:
                DaggerActivity_MembersInjector.injectRestaurant1(this,new Restaurant());
                DaggerActivity_MembersInjector.injectSendFoodMan1(this,new SendFoodMan());
                DaggerActivity_MembersInjector.injectLazyMan1(this,new LazyMan());
                restaurant1.cooking();
                sendFoodMan1.getFood();
                sendFoodMan1.sendFood();
                sendFoodMan1.finishSendFood();
                lazyMan1.receiveFood();
                break;
            case 2:
                ButtonActivity.launch(this);
                break;
        }

    }
}
