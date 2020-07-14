package com.aserbao.aserbaosandroid.aaSource.android.java.lang.clone;

import android.util.Log;
import android.view.View;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 作用：clone
 * @author aserbao
 * @date: on 2020/7/7
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aaSource.android.java.lang
 */
public class CloneTestActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过对象实现Serializable 进行clone",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过Cloneable 接口来实现对象clone",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                cloneTest();
            break;
            case 1:
                cloneTest2();
                break;
        }
    }


    public void cloneTest() {
        try {
            CloneBean aserbao = new CloneBean("aserbao", 18);
            CloneBean cloneBean = clone(aserbao);
            cloneBean.name = "imerbao";
            cloneBean.age = 16;
            Log.e("CloneTest", "main: " + aserbao.toString() + " cloneBean = " + cloneBean.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cloneTest2() {
        try {
            CloneBean2 aserbao = new CloneBean2("aserbao", 18);
            CloneBean2 cloneBean = aserbao.clone(aserbao);
            cloneBean.name = "imerbao";
            cloneBean.age = 16;
            Log.e("CloneTest", "main: " + aserbao.toString() + " cloneBean = " + cloneBean.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> T clone(T obj)throws Exception{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
    }
}
