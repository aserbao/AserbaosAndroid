package com.aserbao.aserbaosandroid.commonData;

import com.aserbao.aserbaosandroid.R;

import java.util.Random;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/13
 * email: 1142803753@qq.com
 */
public class ImageSource {
    public static int getRandomImageId(){
//        int[] iamgeUrl = {R.drawable.mm_1,R.drawable.mm_2,R.drawable.mm_3,R.drawable.mm_4,R.drawable.mm_5,R.drawable.mm_6,R.drawable.mm_7};
        int[] iamgeUrl = {R.drawable.emoji_00,R.drawable.emoji_01,R.drawable.emoji_02,R.drawable.emoji_03,R.drawable.emoji_04,R.drawable.emoji_05,R.drawable.emoji_06};
        int length = iamgeUrl.length;
        int nextInt = new Random().nextInt(length);
        return iamgeUrl[nextInt];
    }
}
