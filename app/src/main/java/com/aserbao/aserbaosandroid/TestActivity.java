package com.aserbao.aserbaosandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/17
 * email: 1142803753@qq.com
 */
public class TestActivity extends AppCompatActivity {
    private List l = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            if(i > 5) {
                l.add(i);
            }else{
                l.add(null);
            }
        }
         findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Object o = l.get(6);
                 Object o1 = l.get(2);
                 Toast.makeText(TestActivity.this, "第一个值：" + o + " 第二个值： " + o1, Toast.LENGTH_SHORT).show();
             }
         });
    }

}
