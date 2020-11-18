package com.example.base.utils.widget;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
/**
 * @author: aserbao
 * @date:2020/11/17 3:39 PM
 * @package:
 * @describle: 带圆角点击效果
 */
public class RippleUtil {

    /**
     * 圆角点击效果
     * @param view
     * @param radius 单位 dp
     */
    public static void setRippleNew(View view, float radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setClickable(true);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setColor(Color.parseColor("#000000"));
            drawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, view.getResources().getDisplayMetrics()));

            TypedValue typedValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(android.R.attr.colorControlHighlight, typedValue, true);
            RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(typedValue.data), null, new GradientDrawable());

            rippleDrawable.setDrawableByLayerId(android.R.id.mask, drawable.mutate());

            view.setForeground(rippleDrawable);
        }
    }
}
