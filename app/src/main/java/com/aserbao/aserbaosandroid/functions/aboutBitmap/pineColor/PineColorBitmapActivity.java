package com.aserbao.aserbaosandroid.functions.aboutBitmap.pineColor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;
import com.aserbao.aserbaosandroid.ui.customView.gradient.LinearGradientView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PineColorBitmapActivity extends AppCompatActivity {

    @BindView(R.id.normal_lgradient_view)
    LinearGradientView mNormalLgradientView;
    @BindView(R.id.normal_image_view)
    ImageView mNormalImageView;
    @BindView(R.id.start_color_tv)
    TextView mStartColorTv;
    @BindView(R.id.end_color_tv)
    TextView mEndColorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
    }

    Shader.TileMode[] sTileMode = {Shader.TileMode.CLAMP, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR};
    int mCuurPosition = 0;

    @OnClick({R.id.normal_lgradient_view, R.id.normal_image_view})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.normal_lgradient_view:
                Shader.TileMode tileMode = sTileMode[mCuurPosition % 3];
                mNormalLgradientView.changeTileMode(tileMode);
                Toast.makeText(this, tileMode.name(), Toast.LENGTH_SHORT).show();
                mCuurPosition++;
                break;
            case R.id.normal_image_view:
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                int randomImageId = ImageSource.getRandomImageId();
                mNormalImageView.setImageResource(randomImageId);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), randomImageId, options);
                int startColor = calcBitmapAvgColorByAvgRGB(bitmap, 0.2f, 100, true);
                int endColor = calcBitmapAvgColorByAvgRGB(bitmap, 0.2f, 100, false);
                mStartColorTv.setBackgroundColor(startColor);
                mEndColorTv.setBackgroundColor(endColor);
                mNormalLgradientView.setColor(startColor, endColor);
                break;
        }
    }


    /**
     * @param bitmap     图片
     * @param pinPencent 选图片的多少取颜色，0~1
     * @param avgNum     等分为多少份
     * @param isStart    true表示头部，false表示尾部
     */
    public int calcBitmapAvgColorByAvgRGB(Bitmap bitmap, float pinPencent, int avgNum, boolean isStart) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float calcHeight = height * pinPencent;
        float startHeight = 0;
        if (!isStart) {
            startHeight = height - height * pinPencent;
        }
        int summaryRed = 0, summaryGreen = 0, summaryBlue = 0;
        int cuurPerHeight = (int) (startHeight + calcHeight / 2);
        for (int i = 0; i < avgNum; i++) {
            int cuurPerWidth = width / avgNum * i;
            int pixel = bitmap.getPixel(cuurPerWidth, cuurPerHeight);
            Log.e(TAG, "calcBitmapAvgColorTop: " + pixel );
            summaryRed += Color.red(pixel);
            summaryGreen += Color.green(pixel);
            summaryBlue += Color.blue(pixel);
        }
        int avgRed = summaryRed / avgNum;
        int avgGreen = summaryGreen / avgNum;
        int avgBlue = summaryBlue / avgNum;
        Log.e(TAG, "calcBitmapAvgColorTop: avgRed = " + avgRed + "  avgGreen = " + avgGreen + " avgBlue = " + avgBlue);

        return Color.rgb(avgRed, avgGreen, avgBlue);
    }


    private static final String TAG = "PineColorBitmapActivity";
}
