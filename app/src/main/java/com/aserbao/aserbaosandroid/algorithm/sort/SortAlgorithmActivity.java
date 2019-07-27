package com.aserbao.aserbaosandroid.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

import java.util.Arrays;

public class SortAlgorithmActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "SortAlgorithmActivity";


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("冒泡排序"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选择排序"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("冒泡排序"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("冒泡排序"));
    }



    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                bubbleSort();
                break;
            case 1:
                selectedSort();
                break;
        }
    }


    /**
     * 冒泡排序
     */
    public void bubbleSort(){
        int[] source_data = {20,40,50,30,10,5,15,25,45,35};
        for (int i = 1; i < source_data.length; i++) {
            for (int j = 0; j < source_data.length - i; j++) {
                if (source_data[j] > source_data[j+1]){
                    int temp = source_data[j+1];
                    source_data[j+1] = source_data[j];
                    source_data[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(source_data));
    }

    /**
     * 选择排序
     */
    public void selectedSort(){
        int[] source_data = {20,40,50,30,10,5,15,25,45,35};
        int index = 0;
        for (int i = 0; i < source_data.length; i++) {
            for (int j = i ; j < source_data.length; j++) {
                if (source_data[index] > source_data[j]) {
                    index = j;
                }
                if (j == source_data.length -1){
                    int temp = source_data[i];
                    source_data[i] = source_data[index];
                    source_data[index] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(source_data));
    }
}
