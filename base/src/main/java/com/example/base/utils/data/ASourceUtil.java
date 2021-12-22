package com.example.base.utils.data;

import android.graphics.Path;
import android.graphics.PorterDuff;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;

import com.example.base.BaseApplication;
import com.example.base.R;
import com.example.base.base.beans.BaseRecyclerBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.internal.util.LinkedArrayList;

/**
 * 主要功能:数据管理
 * author aserbao
 * date : On 2018/9/13
 * email: this is empty email
 */
public class ASourceUtil {
//    public static final int[] imageUrls = {R.drawable.emoji_00,R.drawable.emoji_01,R.drawable.emoji_02,R.drawable.emoji_03,R.drawable.emoji_04,R.drawable.emoji_05,R.drawable.emoji_06};
    public static final int[] imageUrls = {R.drawable.mm_1,R.drawable.mm_2,R.drawable.mm_3,R.drawable.mm_4,R.drawable.mm_5,R.drawable.mm_6,R.drawable.mm_7};
//      public static final int[] imageUrls = {R.drawable.starry_sky_1,R.drawable.starry_sky_2,R.drawable.starry_sky_3,R.drawable.starry_sky_4,R.drawable.starry_sky_5,R.drawable.starry_sky_6,R.drawable.starry_sky_7,R.drawable.starry_sky_8,R.drawable.starry_sky_9,R.drawable.starry_sky_10};




    public static List<BaseRecyclerBean> getStaticRecyclerViewData(List<BaseRecyclerBean> mBaseRecyclerBean, int times) {
        if (mBaseRecyclerBean == null) {
            new Exception("传个null进来开玩笑吧？,赶快给我修改了！墨迹个锤子🔨");
        }
        int[] imageUrl = ASourceUtil.imageUrls;
        for (int i = 0; i < imageUrl.length * times; i++) {
            mBaseRecyclerBean.add(new BaseRecyclerBean(imageUrl[i%imageUrl.length],StaticFinalValues.VIEW_HOLDER_IMAGE_100H));
        }
        return mBaseRecyclerBean;
    }

    public static int getRandomImageId(){
        int length = imageUrls.length;
        int nextInt = new Random().nextInt(length);
        return imageUrls[nextInt];
    }


    public static void getAllInterpoator(List<BaseRecyclerBean> baseRecyclerBeans){
        baseRecyclerBeans.add(new BaseRecyclerBean("AccelerateDecelerateInterpolator",StaticFinalValues.AccelerateDecelerateInterpolator));
        baseRecyclerBeans.add(new BaseRecyclerBean("AccelerateDecelerateInterpolator",StaticFinalValues.AccelerateDecelerateInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("AccelerateInterpolator",StaticFinalValues.AccelerateInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("AnticipateInterpolator",StaticFinalValues.AnticipateInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("AnticipateOvershootInterpolator",StaticFinalValues.AnticipateOvershootInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("BounceInterpolator",StaticFinalValues.BounceInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("CycleInterpolator",StaticFinalValues.CycleInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("DecelerateInterpolator",StaticFinalValues.DecelerateInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("LinearInterpolator",StaticFinalValues.LinearInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("OvershootInterpolator",StaticFinalValues.OvershootInterpolator ));
        baseRecyclerBeans.add(new BaseRecyclerBean("PathInterpolator",StaticFinalValues.PathInterpolator ));
    }

    public static Interpolator getCuurSelectedInterpolator(int selectedPosition){
        Interpolator interpolator = null;
        switch (selectedPosition){
            case StaticFinalValues.AccelerateDecelerateInterpolator:
                interpolator = new AccelerateDecelerateInterpolator();
                break;
            case StaticFinalValues.AccelerateInterpolator:
                interpolator = new AccelerateInterpolator();
                break;
            case StaticFinalValues.AnticipateInterpolator:
                interpolator = new AnticipateInterpolator();
                break;
            case StaticFinalValues.AnticipateOvershootInterpolator:
                interpolator = new AnticipateOvershootInterpolator();
                break;
            case StaticFinalValues.BounceInterpolator:
                interpolator = new BounceInterpolator();
                break;
            case StaticFinalValues.CycleInterpolator:
                interpolator = new CycleInterpolator(2);
                break;
            case StaticFinalValues.DecelerateInterpolator:
                interpolator = new DecelerateInterpolator();
                break;
            case StaticFinalValues.LinearInterpolator:
                interpolator = new LinearInterpolator();
                break;
            case StaticFinalValues.OvershootInterpolator:
                interpolator = new OvershootInterpolator();
                break;
            case StaticFinalValues.PathInterpolator:
                Path path = new Path();
                path.moveTo(0,0);
                path.lineTo(0.3f,1.0f);
                path.lineTo(1,1);
                interpolator = new PathInterpolator(BaseApplication.screenWidth/2,BaseApplication.screenHeight);
                break;
        }
        return interpolator;
    }



    public static int getDrawable(){
        return drawables[new Random().nextInt(drawables.length)];
    }
    public static int[] drawables = {
        R.drawable.mm_1,
        R.drawable.mm_2,
    };
}
