package com.aserbao.aserbaosandroid.functions.aboutBitmap.createBitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.base.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;

public class CreateBitmapActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "CreateBitmapActivity";

    private ImageView imageView;
    private Button button;
    private FrameLayout frameLayout;

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("根据View生成简单的Bitmap",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("生成bitmap的时候移动View的位置",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试View不可见的时候创建图片",2));

        View view = LayoutInflater.from(mContext).inflate(R.layout.framelayout_image, null);
        frameLayout = ((FrameLayout) view.findViewById(R.id.frame_layout_container));
        /*imageView = ((ImageView) view.findViewById(R.id.imageView));
        button = ((Button) view.findViewById(R.id.frame_layout_btn));
        addViewToFrameLayout(view, true,false, false);*/
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                Bitmap bitmap = getViewBitmap(mBaseRecyclerViewFl);
                imageView.setImageBitmap(bitmap);
                break;
            case 1:
                ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    int dip2px = DisplayUtil.dip2px(120);
                    ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(dip2px,dip2px,0,0);
                }
                Bitmap bitmap1 = getViewBitmap(frameLayout);
                imageView.setImageBitmap(bitmap1);
                break;
            case 2:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.test_water, null);
                ConstraintLayout cardView = view1.findViewById(R.id.water_cons);
//                ImageView imageView = view1.findViewById(R.id.card_image);
//                imageView.setImageResource(R.drawable.emoji_00);
//                view1.buildDrawingCache(false);
                int dp2px100 = DisplayUtil.dp2px(200);
                int screenHeight = DisplayUtil.getScreenHeight(this);
                int screenWidth = DisplayUtil.getScreenWidth(this);
                Bitmap bitmapView1 = createBitmap3(cardView,screenWidth/2,screenHeight/2);
                Log.e(TAG, "itemClickBack: " + bitmapView1);

                break;
        }
    }

    /**
     * 通过View的缓存生成Bitmap
     * @param view
     * @return
     */
    public static Bitmap getViewBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        // 获取指定View的截图
        Bitmap bitmap = view.getDrawingCache();
        Bitmap bm = Bitmap.createBitmap(bitmap);

        view.setDrawingCacheEnabled(false);

        return bm;
    }


    /**
     * 通过canvas来绘制bitmap
     * @param v
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap createViewBitmap(View v, int targetWidth, int targetHeight, Matrix matrix) {
        // 先把显示的View画出图片
        Bitmap bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        float scaleWidth = (float) targetWidth / v.getWidth();
        float scaleHeight = (float) targetHeight / v.getHeight();
        canvas.scale(scaleWidth, scaleHeight);
        canvas.setMatrix(matrix);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 根据xml生成图片
     * @param v
     * @param width
     * @param height
     * @return
     */
    //如果出现图片只截取了上面的一部分   那么你就需要计算控件自适应的高度了
    public static Bitmap createBitmap3(ViewGroup v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

}
