package com.aserbao.aserbaosandroid.ui.animation.explosionAnimation.viewExplosion.simpleViewExplosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

import java.util.ArrayList;

public class SimpleExplosionField extends View {
    private static final String TAG = "ExplosionField";
    private static final Canvas mCanvas = new Canvas();
    private ArrayList<ExplosionAnimator> explosionAnimators;
    private View.OnClickListener onClickListener;

    public SimpleExplosionField(Context context) {
        super(context);
        init();
    }

    public SimpleExplosionField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        explosionAnimators = new ArrayList<ExplosionAnimator>();

        attach2Activity((Activity) getContext());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator animator : explosionAnimators) {
            animator.draw(canvas);
        }
    }

    /**
     * 爆破
     * @param view 使得该view爆破
     */
    public void explode(final View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect); //得到view相对于整个屏幕的坐标
        rect.offset(0, -DisplayUtil.dp2px(25)); //去掉状态栏高度

        final ExplosionAnimator animator = new ExplosionAnimator(this, createBitmapFromView(view), rect);
        explosionAnimators.add(animator);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.animate().alpha(0f).setDuration(150).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.animate().alpha(1f).setDuration(150).start();

                //动画结束时从动画集中移除
                explosionAnimators.remove(animation);
                animation = null;
            }
        });
        animator.start();
    }

    private Bitmap createBitmapFromView(View view) {
        /*
         * 为什么屏蔽以下代码段？
         * 如果ImageView直接得到位图，那么当它设置背景（backgroud)时，不会读取到背景颜色
         */
//        if (view instanceof ImageView) {
//            Drawable drawable = ((ImageView)view).getDrawable();
//            if (drawable != null && drawable instanceof BitmapDrawable) {
//                return ((BitmapDrawable) drawable).getBitmap();
//            }
//        }

        //view.clearFocus(); //不同焦点状态显示的可能不同——（azz:不同就不同有什么关系？）

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        if (bitmap != null) {
            synchronized (mCanvas) {
                mCanvas.setBitmap(bitmap);
                view.draw(mCanvas);
                mCanvas.setBitmap(null); //清除引用
            }
        }
        return bitmap;
    }

    /**
     * 给Activity加上全屏覆盖的ExplosionField
     */
    private void attach2Activity(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }


    /**
     * 希望谁有破碎效果，就给谁加Listener
     * @param view 可以是ViewGroup
     */
    public void addListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0 ; i < count; i++) {
                addListener(viewGroup.getChildAt(i));
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }


    private OnClickListener getOnClickListener() {
        if (null == onClickListener) {

            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleExplosionField.this.explode(v);

//                view.setOnClickListener(null); // 用过一次就不需要了
                }
            };
        }

        return onClickListener;
    }
}
