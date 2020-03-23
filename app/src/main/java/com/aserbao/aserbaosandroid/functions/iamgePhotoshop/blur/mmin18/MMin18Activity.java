package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.mmin18;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.example.base.commonData.ASourceUtil;

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
        mBlurIamgeView.setImageResource(ASourceUtil.getRandomImageId());
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
