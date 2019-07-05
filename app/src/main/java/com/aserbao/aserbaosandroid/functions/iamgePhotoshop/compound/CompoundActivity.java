package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.compound;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur.easyblur.EasyBlur;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompoundActivity extends AppCompatActivity {

    @BindView(R.id.compound_one_iv)
    ImageView mCompoundOneIv;
    @BindView(R.id.compound_two_iv)
    ImageView mCompoundTwoIv;
    @BindView(R.id.compound_btn)
    Button mCompoundBtn;
    @BindView(R.id.compound_result_iv)
    ImageView mCompoundResultIv;
    private Bitmap mInitBitmap;
    private Bitmap mBlurBitmap;
    private Bitmap mSecondBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
        ButterKnife.bind(this);
        mInitBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.starry_sky_1);
        mSecondBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_00);
        mBlurBitmap = EasyBlur.with(this).bitmap(mInitBitmap).radius(25).scale(2).blur();
        mCompoundOneIv.setImageBitmap(mBlurBitmap);
    }

    @OnClick(R.id.compound_btn)
    public void onViewClicked() {
        Bitmap bitmap = compoundImag(mInitBitmap, mSecondBitmap,4);
        mCompoundResultIv.setImageBitmap(bitmap);
    }

    public Bitmap compoundImag(Bitmap bitmap1, Bitmap bitmap2,int scale){
        Bitmap bitmap = EasyBlur.with(this).bitmap(bitmap1).radius(15).scale(4).blur();
        Bitmap bitmap3 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        int width1 = bitmap.getWidth();
        int height1 = bitmap.getHeight();
        int width2 = bitmap2.getWidth()/scale;
        int height2 = bitmap2.getWidth()/scale;
        int left = (width1 - width2)/2;
        int top = (height1 - height2)/2;
        Rect srcRectF = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        Rect dstRectF = new Rect(left, top, left + width2, top+ height2);
        canvas.drawBitmap(bitmap2, srcRectF, dstRectF, null);
//        canvas.drawBitmap(bitmap2, left, top, null);
        Log.e("compoundImag", "compoundImag: width1 = " + width1 + " width2 = " + width2 + " height1 = " + height1 +  " height2  = " + height2 + " legt = " + left + " top = " + top );
        return bitmap3;
    }
}
