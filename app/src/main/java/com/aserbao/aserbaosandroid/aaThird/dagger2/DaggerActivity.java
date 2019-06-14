package com.aserbao.aserbaosandroid.aaThird.dagger2;

import android.view.View;

import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Block;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Car;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Cylinder;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Engine;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Rim;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.SparkPlug;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Tire;
import com.aserbao.aserbaosandroid.aaThird.dagger2.beans.Wheel;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.DaggerCarComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.DaggerDepenComponent;
import com.aserbao.aserbaosandroid.aaThird.dagger2.di.components.DepenComponent;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.buttons.ButtonActivity;

import javax.inject.Inject;
import javax.inject.Named;

public class DaggerActivity extends BaseRecyclerViewActivity {



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
                normal();
                break;
            case 1:
                useDagger2();
                break;
            case 2:
                ButtonActivity.launch(this);
                break;
        }
    }

    public void normal(){
        Block block = new Block();
        Cylinder cylinder = new Cylinder();
        SparkPlug sparkPlug = new SparkPlug();
        Tire tire = new Tire();
        Rim rim = new Rim();
        Engine engine = new Engine(block,cylinder,sparkPlug);
        Wheel wheel = new Wheel(rim,tire);
        Car car = new Car(wheel,engine);
        car.drive();
    }

    @Named("CarB")
    @Inject
    public Car mCar;
    public void useDagger2(){
//        DaggerCarComponent.create().inject(this);
        DepenComponent depenComponent = DaggerDepenComponent.create();
        DaggerCarComponent.builder().depenComponent(depenComponent).build().inject(this);
        mCar.drive();
    }
}
