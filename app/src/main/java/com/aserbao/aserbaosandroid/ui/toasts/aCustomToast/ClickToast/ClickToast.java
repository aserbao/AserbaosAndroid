package com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ClickToast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class ClickToast{

    private View mIv;

    private static Toast mToast;
    private static TextView btn;
    private View mView;


    public View show(final Context context, int duration,int layoutId){
        if(mToast == null){
            LayoutInflater inflater = (LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //自定义布局
            mView = inflater.inflate(layoutId, null);
            mToast = Toast.makeText(context.getApplicationContext(), "", duration);
            //这里可以指定显示位置
            //            mToast.setGravity(Gravity.BOTTOM, 0, 0);

            mToast.setView(mView);
        }
        try {
            Object mTN ;
            mTN = getField(mToast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    //显示与隐藏动画
//                    params.windowAnimations = R.style.ClickToast;
                    //Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                    //设置viewgroup宽高
                    params.width = WindowManager.LayoutParams.MATCH_PARENT; //设置Toast宽度为屏幕宽度
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mToast.show();
        return mView;
    }


    /**
     * 反射字段
     * @param object 要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }
}
