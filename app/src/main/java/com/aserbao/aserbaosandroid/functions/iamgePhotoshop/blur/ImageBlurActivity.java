package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageBlurActivity extends AppCompatActivity {
    private static final String TAG = "ImageBlurActivity";
    @BindView(R.id.blur_iamge_view)
    ImageView mBlurIamgeView;
    @BindView(R.id.start_blur_btn)
    Button mStartBlurBtn;
    @BindView(R.id.blur_scale_tv)
    TextView mBlurScaleTv;
    @BindView(R.id.blur_scale_seek_bar)
    SeekBar mBlurScaleSeekBar;
    @BindView(R.id.blur_radius_tv)
    TextView mBlurRadiusTv;
    @BindView(R.id.blur_radius_seek_bar)
    SeekBar mBlurRadiusSeekBar;


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
//        mBitmap = BitmapFactory.decodeResource(getResources(), ImageSource.getRandomImageId());
        int[] ints = {178,255,255,255};
        mBitmap = Bitmap.createBitmap(ints, 1080, 1920, Bitmap.Config.RGB_565);
        mBlurIamgeView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mBlurIamgeView.getViewTreeObserver().removeOnPreDrawListener(this);
                mBlurIamgeView.buildDrawingCache();
                Bitmap bmp = mBlurIamgeView.getDrawingCache();
                int widthPixels = getResources().getDisplayMetrics().widthPixels;
                int heightPixels = getResources().getDisplayMetrics().heightPixels;
                Bitmap overlay = Bitmap.createBitmap((int) widthPixels,
                        (int) heightPixels, Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(overlay);

                canvas.translate(-0, -heightPixels /2);
                canvas.drawBitmap(bmp, 0, 0, null);
                Bitmap finalBitmap = EasyBlur.with(mContext)
                        .bitmap(overlay) //要模糊的图片
                        .radius(10)//模糊半径
                        .blur();
                mBlurIamgeView.setBackground(new BitmapDrawable(getResources(),finalBitmap));
                return true;
            }
        });



        mBlurIamgeView.setImageResource(R.drawable.starry_sky_1);
    }

    private int mRadius = 10; // 5~25之间
    private int mScale = 8;

    private void initListener() {
        mBlurRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurRadiusTv.setText("radius = " + progress);
                mRadius = progress;
                mBlurIamgeView.setImageBitmap(getBlurBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mBlurScaleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurScaleTv.setText("scale = " + progress);
                mScale = progress;
                mBlurIamgeView.setImageBitmap(getBlurBitmap());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mStartBlurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBlurIamgeView.setImageBitmap(getBlurBitmap());
            }
        });
    }

    public Bitmap getBlurBitmap(){
        Bitmap finalBitmap = EasyBlur.with(this)
                .bitmap(mBitmap) //要模糊的图片
                .radius(mRadius)//模糊半径
                .scale(mScale)//指定模糊前缩小的倍数
                .policy(EasyBlur.BlurPolicy.FAST_BLUR)//使用fastBlur
                .blur();
        return finalBitmap;
    }


}
