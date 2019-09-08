package com.aserbao.aserbaosandroid.ui.imageviews;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.ui.imageviews.custom.ShadowRoundImageView;
import com.github.siyamed.shapeimageview.HeartImageView;

public class ImageViewsActivity extends BaseRecyclerViewActivity {


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("心形ImageView",520));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("带阴影的ImageView",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        mBaseRecyclerEmptyContainer.removeAllViews();
        int width = DisplayUtil.dp2px(62);
        int height = DisplayUtil.dp2px(58);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width,height,Gravity.CENTER);
        mBaseRecyclerEmptyContainer.setBackgroundColor(Color.TRANSPARENT);
        switch (position){
            case 520:
                HeartImageView heartImageView = new HeartImageView(this);
                Bitmap bitmap = createBitmap();
                heartImageView.setImageBitmap(bitmap);
                heartImageView.setLayoutParams(layoutParams);
                mBaseRecyclerEmptyContainer.addView(heartImageView);
                break;
            case 1:
                ShadowRoundImageView shadowRoundImageView = new ShadowRoundImageView(this);
                shadowRoundImageView.setImageResource(R.drawable.mm_1);
                shadowRoundImageView.setLayoutParams(layoutParams);
                mBaseRecyclerEmptyContainer.setBackgroundColor(Color.WHITE);
                mBaseRecyclerEmptyContainer.addView(shadowRoundImageView);
                break;
        }
    }


    public Bitmap createBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_4444);
        bitmap.eraseColor(Color.parseColor("#ff0000")); // 填充颜色
        return bitmap;
    }
}
