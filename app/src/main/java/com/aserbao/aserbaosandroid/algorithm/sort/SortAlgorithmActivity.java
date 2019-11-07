package com.aserbao.aserbaosandroid.algorithm.sort;

import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.log.ALogUtils;
import com.aserbao.aserbaosandroid.algorithm.list.Student;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.util.Arrays;
import java.util.Random;

public class SortAlgorithmActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "SortAlgorithmActivity";
    private int[] source_data ;
    private Random mRandom;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("java中=的意义",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("冒泡排序",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("原始选择排序",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("选择排序升级版",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("直接插入排序",40));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("二分插入排序",41));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("希尔排序",42));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("归并排序",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("快速排序",6));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("快速排序",61));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("计数排序",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("基数排序",8));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("堆排序",9));
        int data_count = 10000;
        mRandom = new Random();
        source_data = new int[data_count];
        for (int i = 0; i < data_count; i++) {
            source_data[i] = mRandom.nextInt(data_count);
        }
    }



    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        ALogUtils.logErrorTime(ALogUtils.INT_NUM_START_TIME);
        switch (position){
            case 0:
                changeInt();
                break;
            case 1:
//                int[] bubble_data = {20,40,50,30,10,5,15,25,45,35};
                bubbleSort(source_data);
                break;
            case 2:
                selectedSort(source_data);
                break;
            case 3:
                selectedSort2(source_data);
                break;
            case 40:
                directInsertionSort(source_data);
//                insertSort();
                break;
            case 41:
                binaryInsertSort(source_data);
                break;
            case 42:
                shellSort(source_data);
                break;
            case 5:
                useMergeSortData(source_data);
                break;
            case 6:
                useFastSortData(source_data);
                break;
            case 61:
//                int[] source_data = {35,15,25,5,10,45,40,20};
                inPlaceSort(source_data,0,source_data.length-1);
                Log.e(TAG, "itemClickBack: "+Arrays.toString(source_data) );
                break;
            case 7:
//                int[] source_data1 = {35,15,25,5,10,45,40,20};
                source_data = countSort(source_data);
                Log.e(TAG, "itemClickBack: countSort = "+Arrays.toString(source_data) );
                break;
            case 8:
//                int[] radix_data = {1571,232,33,786,9987,668,99,6666,3321,7542};
                radixSort(source_data,5);
                Log.e(TAG, "itemClickBack: radixSort = " + Arrays.toString(source_data) );
                break;
            case 9:
//                int[] heap_data = {35,15,25,5,10,45,40,20};
                heapSort(source_data);
                Log.e(TAG, "itemClickBack: heapSort = " + Arrays.toString(source_data) );
                break;
        }
        ALogUtils.logErrorTime(ALogUtils.INT_NUM_END_TIME);
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
    public void bubbleSort(int[] source_data){
        int calcTime = 0;
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
    public void selectedSort(int[] source_data){
        int calcTime = 0;
//        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
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

    public void selectedSort2(int[] source_data){
        int calcTime = 0;
//        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
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
    public void directInsertionSort(int[] source_data){
        int calcTime = 0;
//        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
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
    public void binaryInsertSort(int[] source_data) {
        int calcTime = 0;
//        int[] source_data = {35,45,25,15,5,10,30,50,40,20};
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


    /**
     * 希尔排序
     */
    public void  shellSort(int[] source_data) {
//        int[] source_data = {5, 45, 10, 25, 35, 15};
        //希尔排序
        int d = source_data.length;
        while (true) {
            d = d / 2;
            for (int i = 0; i < d; i++) {
                for (int j = i + d; j < source_data.length; j = j + d) {
                    int temp = source_data[i];
                    int k;
                    for (k = j - d; k >= 0 && source_data[k] > temp; k = k - d) {
                        source_data[k + d] = source_data[k];
                    }
                    source_data[k + d] = temp;
                    Log.e(TAG, "shellSort: " + String.valueOf(d) + " 值 = " + Arrays.toString(source_data));
                }
            }
            if (d == 1) {
                break;
            }
        }
    }
    /**
     * 使用归并排序
     */
    public void useMergeSortData( int[] source_data){
//        int[] source_data = {35,15,25,5,10,45,40,20};
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


    /**
     * 快速排序
     */
    public void useFastSortData(int[] source_data){
//        int[] source_data = {35,15,25,5,10,45,40,20};
        int start = 0,end = source_data.length -1;
        source_data = fastSort(source_data,start,end);
        Log.e(TAG, "useFaseSortData: "  + Arrays.toString(source_data) );
    }

    // 快速排序

    public int[] fastSort(int[] source_data,int start,int end){
        int pivot = source_data[start];
        int i = start;
        int j = end;
        while (i<j) {
            while ((i<j)&&(source_data[j]>pivot)) {
                j--;
            }
            while ((i<j)&&(source_data[i]<pivot)) {
                i++;
            }
            if ((source_data[i]==source_data[j])&&(i<j)) {
                i++;
            } else {
                int temp = source_data[i];
                source_data[i] = source_data[j];
                source_data[j] = temp;
                Log.e(TAG, "fastSort:pivot="+ pivot + " i =" + i + " j = " + j + " 数据为 = " + Arrays.toString(source_data));
            }
        }
        if (i-1>start) {
            source_data = fastSort(source_data, start, i - 1);
        }
        if (j+1<end) {
            source_data = fastSort(source_data, j + 1, end);
        }
        return (source_data);
    }

    public void inPlaceSort(int[] source_data, int head, int tail) {
        if (head >= tail || source_data == null || source_data.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = source_data[head];
        while (i <= j) {
            while (source_data[i] < pivot) {
                ++i;
            }
            while (source_data[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = source_data[i];
                source_data[i] = source_data[j];
                source_data[j] = t;
                ++i;
                --j;
                Log.e(TAG, "inPlaceSort: i = "+ i + " j = " + j  + " source_data =  " + Arrays.toString(source_data));
            } else if (i == j) {
                ++i;
            }
        }
        inPlaceSort(source_data, head, j);
        inPlaceSort(source_data, i, tail);
        Log.e(TAG, "inPlaceSort: " + Arrays.toString(source_data) );
    }


    /**
     * 计数排序
     */
    public int[] countSort(int[] source_data){
        int temp[] = new int[source_data.length];
        int max = source_data[0], min = source_data[0];
        for(int i : source_data){
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for(int i = 0; i < source_data.length; ++i){
            c[source_data[i]-min] += 1;//优化过的地方，减小了数组c的大小
        }
        for(int i = 1; i < c.length; ++i){
            c[i] = c[i] + c[i-1];
        }
        for(int i = source_data.length-1; i >= 0; --i){
            temp[--c[source_data[i]-min]] = source_data[i];//按存取的方式取出c的元素
        }
        return temp;
    }


    /**
     * 基数排序
     */
    public void radixSort(int[] number, int d) //d表示最大的数有多少位
    {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][]temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[]order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while(m <= d)
        {
            for(int i = 0; i < number.length; i++)
            {
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            for(int i = 0; i < 10; i++)
            {
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++)
                    {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }



    /**
     * 堆排序
     */
    public void heapSort(int[] source_data) {
        int len = source_data.length - 1;
        int beginIndex = (source_data.length >> 1) - 1;
        for (int i = beginIndex; i >= 0; i--)
            maxHeapify(source_data,i, len);
        for (int i = len; i > 0; i--) {
            swap(source_data,0, i);
            maxHeapify(source_data,0, i - 1);
        }
    }
    private void maxHeapify(int[] source_data,int index, int len) {
        int li = (index << 1) + 1;
        int ri = li + 1;
        int cMax = li;
        if (li > len) return;
        if (ri <= len && source_data[ri] > source_data[li])
            cMax = ri;
        if (source_data[cMax] > source_data[index]) {
            swap(source_data,cMax, index);
            maxHeapify(source_data, cMax, len);
        }
    }
    private void swap(int[] source_data, int i, int j) {
        int temp = source_data[i];
        source_data[i] = source_data[j];
        source_data[j] = temp;
    }

}


