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

  /*  @Inject
    Student mStudent;
*/
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
                /*DaggerMainComponent.create().inject(this);
//                DaggerActivity_MembersInjector.injectMStudent(this,new Student("123",15));
                String name = mStudent.getName();

                Log.e(TAG, "itemClickBack: " + name);
                Snackbar.make(view,name,Snackbar.LENGTH_SHORT).show();*/
//                DaggerMainComponent.builder().appModules(new AppModules()).build().inject(this);
//                DaggerMainComponent.create().inject(this);
                break;
            case 2:
                ButtonActivity.launch(this);
                break;
        }

    }
}
