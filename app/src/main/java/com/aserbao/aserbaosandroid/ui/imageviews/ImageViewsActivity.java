package com.aserbao.aserbaosandroid.ui.imageviews;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.github.siyamed.shapeimageview.HeartImageView;

public class ImageViewsActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("心形ImageView",520));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 520:
                mBaseRecyclerEmptyContainer.removeAllViews();
                HeartImageView heartImageView = new HeartImageView(this);
                int width = DisplayUtil.dp2px(62);
                int height = DisplayUtil.dp2px(58);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width,height,Gravity.CENTER);
                Bitmap bitmap = createBitmap();
                heartImageView.setImageBitmap(bitmap);
                heartImageView.setLayoutParams(layoutParams);
                mBaseRecyclerEmptyContainer.addView(heartImageView);
                break;
        }
    }


    public Bitmap createBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_4444);
        bitmap.eraseColor(Color.parseColor("#ff0000")); // 填充颜色
        return bitmap;
    }
}
