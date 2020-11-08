package com.aserbao.aserbaosandroid.aAaTestSingleCode;

import java.util.LinkedList;
import java.util.Scanner;

/*
 * 作用：
 * @author aserbao
 * @date: on 2020/11/8
 * @project: AserbaosAndroid
 * @package: com.aserbao.aserbaosandroid.aAaTestSingleCode
 */
class ForTest {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        LinkedList linkedList = new LinkedList();
        initList(linkedList);
        int[] numTime = new int[linkedList.size()];
    }
    static int roomNum = 1;

    private static void initList(LinkedList linkedList){
        Scanner in = new Scanner(System.in);
        System.out.println("roomNum, Please Enter roomNum:");
        roomNum = in.nextInt();
        for(int i = 0; i <= roomNum;i++ ){
            linkedList.add(in.nextInt());
        }
    }

}
