package com.aserbao.aserbaosandroid.designMode.decoratorPattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee.Cappuccio;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee.Cream;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee.Latte;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee.Material;
import com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee.Soul;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DecoratorActivity extends AppCompatActivity {

    @BindView(R.id.result)
    TextView mResult;
    private Material mCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.latte, R.id.cappuccio, R.id.soul, R.id.cream, R.id.summary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.latte:
                mCoffee = new Latte();
                break;
            case R.id.cappuccio:
                mCoffee = new Cappuccio();
                break;
            case R.id.soul:
                mCoffee = new Soul(mCoffee);
                break;
            case R.id.cream:
                mCoffee = new Cream(mCoffee);
                break;
            case R.id.summary:
//                mResult.setText("您点了：" + mCoffee.description() + "\n一共消费了：" + mCoffee.cost());
                mResult.setText("您点了：" + new Soul(new Latte()).description() + "\n一共消费了：" + mCoffee.cost());
                break;
        }
    }
}
