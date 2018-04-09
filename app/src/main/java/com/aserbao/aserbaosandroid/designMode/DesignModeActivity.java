package com.aserbao.aserbaosandroid.designMode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.DecoratorActivity;
import com.aserbao.aserbaosandroid.designMode.factoryMode.FactoryActivity;
import com.aserbao.aserbaosandroid.designMode.observerMode.ObserverActivity;
import com.aserbao.aserbaosandroid.designMode.strategyMode.StrategyActivity;

public class DesignModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_mode);
    }

    public void btn_stategy(View view) {
        startActivity(new Intent(this, StrategyActivity.class));
    }

    public void btn_observer(View view) {
        startActivity(new Intent(this, ObserverActivity.class));
    }

    public void btn_decorator(View view) {
        startActivity(new Intent(this, DecoratorActivity.class));
    }

    public void btn_factory(View view) {
        startActivity(new Intent(this, FactoryActivity.class));
    }
}
