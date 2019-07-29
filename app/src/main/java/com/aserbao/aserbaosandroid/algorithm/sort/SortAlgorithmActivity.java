package com.aserbao.aserbaosandroid.algorithm.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.algorithm.list.Student;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

import java.util.Arrays;

public class SortAlgorithmActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "SortAlgorithmActivity";


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("java中=的意义",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("冒泡排序",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("原始选择排序",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选择排序升级版",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("直接插入排序",40));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("二分插入排序",41));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("归并排序",5));
    }



    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                changeInt();
                break;
            case 1:
                bubbleSort();
                break;
            case 2:
                selectedSort();
                break;
            case 3:
                selectedSort2();
                break;
            case 40:
                directInsertionSort();
//                insertSort();
                break;
            case 41:
                binaryInsertSort();
                break;
            case 5:
                useMergeSortData();
                break;
        }
    }


    public void changeInt(){
        int min = 15;
        int max = min;
        min = 20;
        Log.e(TAG, "changeInt: min = " + min + " max = " + max  );

        Student student = new Student("123", 15, 90);
        Student student1 = student;

        student.setAge(50);
        Log.e(TAG, "changeInt: " + student.toString() + " \n" + student1.toString() );
    }


    /**
     * 冒泡排序
     */
    public void bubbleSort(){
        int calcTime = 0;
        int[] source_data = {20,40,50,30,10,5,15,25,45,35};
        for (int i = 1; i < source_data.length; i++) {
            for (int j = 0; j < source_data.length - i; j++) {
                if (source_data[j] > source_data[j+1]){
                    int temp = source_data[j+1];
                    source_data[j+1] = source_data[j];
                    source_data[j] = temp;
                }
                calcTime++;
                Log.e(TAG, "bubbleSort:第 "+ calcTime + " 次计算 第 "+ i +" 次循环，第"+ (j+1)+"次数据交换后的数据为：" + Arrays.toString(source_data));
            }
        }
        System.out.println(Arrays.toString(source_data));
    }

    /**
     * 选择排序
     */
    public void selectedSort(){
        int calcTime = 0;
        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
        int minIndex = 0;
        for (int i = 0; i < source_data.length; i++) {
            for (int j = i+1 ; j < source_data.length; j++) {
                if (source_data[minIndex] > source_data[j]) {
                    minIndex = j;
                }
                if (j == source_data.length -1){
                    int temp = source_data[i];
                    source_data[i] = source_data[minIndex];
                    source_data[minIndex] = temp;
                }
                calcTime++;
                Log.e(TAG, "selectedSort:第 "+calcTime +"次计算 第 "+(i+1) +" 次循环，第"+ (j-i)+"次数据交换后的数据为：" + Arrays.toString(source_data));
            }
        }
        System.out.println(Arrays.toString(source_data));
    }

    public void selectedSort2(){
        int calcTime = 0;
        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
        int count = source_data.length;
        for (int i = 0; i < count /2 ; i++) {
            int minIndex = i, maxIndex = count - i -1;
            int cuurCycleLastIndex = count - i -1;
            for (int j = i + 1; j <= cuurCycleLastIndex; j++) {
                calcTime ++;
                if (source_data[minIndex] > source_data[j]) {
                    minIndex = j;
                }
                if (source_data[maxIndex] < source_data[j]){
                    maxIndex = j;
                }
                if (j == cuurCycleLastIndex){
                    int temp = source_data[i];
                    source_data[i] = source_data[minIndex];
                    source_data[minIndex] = temp;

                    temp = source_data[cuurCycleLastIndex];
                    source_data[cuurCycleLastIndex] = source_data[maxIndex];
                    source_data[maxIndex] = temp;
                   /* Log.e(TAG, "selectedSort2: i = "  + i + "j= " +j +"cuurCycleLastIndex = " + cuurCycleLastIndex  + " minIndex = " + minIndex + " maxIndex = "+ maxIndex
                        +" \n " +Arrays.toString(source_data) + " calcTime = " + calcTime);*/
                }
                Log.e(TAG, "selectedSort2:第 "+calcTime +"次计算 第 "+(i+1) +" 次循环，第"+ (j -i)+"次数据交换后的数据为：" + Arrays.toString(source_data) + "其中 minIndex = " + minIndex + " maxIndex = "+ maxIndex );
            }
        }
    }

    /**
     * 直接插入排序
     */
    public void directInsertionSort(){
        int calcTime = 0;
        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
        int count = source_data.length;
        int i,j,k;
        for (i = 1; i < count; i++) {
            for (j = i - 1; j >= 0; j--) {
                calcTime ++;
                if (source_data[j] < source_data[i]){
                    break;
                }
            }
            if (j != i - 1){
                int temp = source_data[i];
                for (k = i - 1; k > j; k--) {
                    calcTime ++;
                    source_data[k + 1] = source_data[k];
                }
                source_data[k + 1] = temp;
            }
            Log.e(TAG, "directInsertionSort: 插入第" +i+"个数后，数据为"+ Arrays.toString(source_data) + " 进行了"+ calcTime +"次计算");
        }
    }

    /**
     * 二分插入排序
     */
    public static void binaryInsertSort() {
        int calcTime = 0;
        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
        for (int i = 1; i < source_data.length; i++) {
            int temp = source_data[i];
            int low = 0, high = i - 1, mid = -1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                calcTime ++;
                if (source_data[mid] > temp) {
                    high = mid - 1;
                } else { // 元素相同时，也插入在后面的位置
                    low = mid + 1;
                }
            }
            for(int j = i - 1; j >= low; j--) {
                calcTime ++;
                source_data[j + 1] = source_data[j];
            }
            source_data[low] = temp;
            Log.e(TAG, "binaryInsertSort: 插入第" +i+"个数后，其中 low =" + low + " mid = " + mid + " high = " + high + "数据为"+ Arrays.toString(source_data) + " 一共进行了"+ calcTime +"次计算" );
        }
    }



    public void useMergeSortData(){
        int[] source_data = {35,15,25,5,10,45,40,20};
        source_data = mergeSort(source_data,0,source_data.length -1);
        Log.e(TAG, "useMergeSortData: " + Arrays.toString(source_data) );
    }

    public int[] mergeSort(int[] nums, int low, int high) {
        if (low == high)
            return new int[] { nums[low] };

        int mid = low + (high - low) / 2;
        int[] leftArr = mergeSort(nums, low, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, high); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];

        Log.e(TAG, "mergeSort: leftArr = " + Arrays.toString(leftArr)  + " rightArr = "+Arrays.toString(rightArr) +" newNum = "+ Arrays.toString(newNum));
        return newNum;
    }


}
