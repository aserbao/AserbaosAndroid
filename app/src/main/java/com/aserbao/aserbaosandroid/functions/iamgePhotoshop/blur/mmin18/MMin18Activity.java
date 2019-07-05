package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.mmin18;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.easyblur.EasyBlur;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MMin18Activity extends AppCompatActivity {

    @BindView(R.id.blur_radius_tv)
    TextView mBlurRadiusTv;
    @BindView(R.id.blur_radius_seek_bar)
    SeekBar mBlurRadiusSeekBar;
    @BindView(R.id.realtime_blur_view)
    RealtimeBlurView mRealtimeBlurView;
    @BindView(R.id.blur_iamge_view)
    ImageView mBlurIamgeView;


    private Context mContext;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_blur);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initListener();
    }

    private void initView() {
        mBlurIamgeView.setImageResource(ImageSource.getRandomImageId());
    }



    private void initListener() {
        mBlurRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRealtimeBlurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, getResources().getDisplayMetrics()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
