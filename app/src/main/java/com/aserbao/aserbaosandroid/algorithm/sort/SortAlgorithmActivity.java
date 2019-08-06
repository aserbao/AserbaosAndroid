package com.aserbao.aserbaosandroid.algorithm.sort;

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
        mBaseRecyclerBeen.add(new BaseRecyclerBean("希尔排序",42));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("归并排序",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("快速排序",6));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("快速排序",61));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("计数排序",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("基数排序",8));
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
            case 42:
                shellSort();
                break;
            case 5:
                useMergeSortData();
                break;
            case 6:
                useFastSortData();
                break;
            case 61:
                int[] source_data = {35,15,25,5,10,45,40,20};
                inPlaceSort(source_data,0,source_data.length-1);
                Log.e(TAG, "itemClickBack: "+Arrays.toString(source_data) );
                break;
            case 7:
                int[] source_data1 = {35,15,25,5,10,45,40,20};
                source_data1 = countSort(source_data1);
                Log.e(TAG, "itemClickBack: countSort = "+Arrays.toString(source_data1) );
                break;
            case 8:
                int[] radix_data = {1571,232,33,786,9987,668,99,6666,3321,7542};
                radixSort(radix_data,4);
                Log.e(TAG, "itemClickBack: radixSort = " + Arrays.toString(radix_data) );
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
    public void binaryInsertSort() {
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


    /**
     * 希尔排序
     */
    public void  shellSort() {
        int[] source_data = {5, 45, 10, 25, 35, 15};
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


    /**
     * 快速排序
     */
    public void useFastSortData(){
        int[] source_data = {35,15,25,5,10,45,40,20};
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
    public int[] heapSort(int[] array) {
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 整个堆排序最关键的地方
     * @param array 待组堆
     * @param i 起始结点
     * @param length 堆的长度
     */
    public void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;  //这一步决定了下一个步骤执行到的是左子树还是右子树
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a 元素的下标
     * @param b 元素的下标
     */
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}


