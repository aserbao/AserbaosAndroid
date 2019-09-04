package com.aserbao.aserbaosandroid.other.valuePass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/**
 * 引用传递
 */
public class ValuePassActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("测试下对象传递",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("测试下静态对象传递",2));
        mBaseRecyclerTv.setGravity(Gravity.CENTER);
        valuesBean = new ValuesBean(0, 0, "我没被修改");
    }

    public ValuesBean valuesBean;
    public ValuesBean mValuesBean2;

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 1:
                ValuesBean mValuesBean = valuesBean;
                mValuesBean.name ="修改name";

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("代码：\n ValuesBean valuesBean = new ValuesBean(0, 0, \"初始对象\");\n" +
                    "                ValuesBean mValuesBean = valuesBean;\n" +
                    "                mValuesBean.name =\"修改name\";");
                stringBuffer.append("修改后两个对象的属性值分别为:\n");
                stringBuffer.append("初始对象name="+ valuesBean.name);
                stringBuffer.append("\n修改对象name="+ mValuesBean.name);
                mBaseRecyclerTv.setText(stringBuffer.toString());
                break;
            case 2:
                mValuesBean2 = valuesBean;
                valuesBean.name = "我已经被修改了";
                mBaseRecyclerTv.setText("valuesBean.Name = " + valuesBean.name + "mValuesBean2 = "+ mValuesBean2.name);
                break;
        }
    }

    class ValuesBean {
        public int x;
        public int y;
        public String name;

        public ValuesBean(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ValuesBean{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
        }
    }
}
