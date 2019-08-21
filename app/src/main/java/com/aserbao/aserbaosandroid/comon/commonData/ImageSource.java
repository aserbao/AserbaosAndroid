package com.aserbao.aserbaosandroid.comon.commonData;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/13
 * email: this is empty email
 */
public class ImageSource {
//    public static final int[] iamgeUrl = {R.drawable.emoji_00,R.drawable.emoji_01,R.drawable.emoji_02,R.drawable.emoji_03,R.drawable.emoji_04,R.drawable.emoji_05,R.drawable.emoji_06};
//    public static final int[] iamgeUrl = {R.drawable.mm_1,R.drawable.mm_2,R.drawable.mm_3,R.drawable.mm_4,R.drawable.mm_5,R.drawable.mm_6,R.drawable.mm_7};
      public static final int[] iamgeUrl = {R.drawable.starry_sky_1,R.drawable.starry_sky_2,R.drawable.starry_sky_3,R.drawable.starry_sky_4,R.drawable.starry_sky_5,R.drawable.starry_sky_6,R.drawable.starry_sky_7,R.drawable.starry_sky_8,R.drawable.starry_sky_9,R.drawable.starry_sky_10};

    public static List<BaseRecyclerBean> getStaticRecyclerViewData(List<BaseRecyclerBean> mBaseRecyclerBeen) {
        if (mBaseRecyclerBeen == null) {
            new Exception("传个null进来开玩笑吧？,赶快给我修改了！墨迹个锤子🔨");
        }
        for (int i = 0; i < ImageSource.iamgeUrl.length; i++) {
            mBaseRecyclerBeen.add(new BaseRecyclerBean(ImageSource.iamgeUrl[i],StaticFinalValues.VIEW_HOLDER_IMAGE_100H));
        }
        return mBaseRecyclerBeen;
    }

    public static int getRandomImageId(){
        int length = iamgeUrl.length;
        int nextInt = new Random().nextInt(length);
        return iamgeUrl[nextInt];
    }
}
