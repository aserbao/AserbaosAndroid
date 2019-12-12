package com.aserbao.aserbaosandroid.functions.aboutBitmap.createBitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class CreateBitmapActivity extends BaseRecyclerViewActivity {


    private ImageView imageView;
    private Button button;
    private FrameLayout frameLayout;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("根据View生成简单的Bitmap",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("生成bitmap的时候移动View的位置",1));

        View view = LayoutInflater.from(mContext).inflate(R.layout.framelayout_image, null);
        frameLayout = ((FrameLayout) view.findViewById(R.id.frame_layout_container));
        imageView = ((ImageView) view.findViewById(R.id.imageView));
        button = ((Button) view.findViewById(R.id.frame_layout_btn));
        addViewToFrameLayout(view, true,false);
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
}
