package com.aserbao.aserbaosandroid.functions.aboutBitmap.pineColor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.commonData.ASourceUtil;
import com.aserbao.aserbaosandroid.ui.customView.gradient.LinearGradientView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PineColorBitmapActivity extends AppCompatActivity {

    public static final int HEIGHT = 1080;
    public static final int WIDTH = 720;
    @BindView(R.id.pine_color_lgradient_view)
    LinearGradientView mNormalLgradientView;
    @BindView(R.id.pine_color_image_view)
    ImageView mNormalImageView;
    @BindView(R.id.start_color_tv)
    TextView mStartColorTv;
    @BindView(R.id.end_color_tv)
    TextView mEndColorTv;
    @BindView(R.id.pine_color_fl)
    FrameLayout mPineColorFl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
    }

    Shader.TileMode[] sTileMode = {Shader.TileMode.CLAMP, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR};
    int mCuurPosition = 0;

    @OnClick({R.id.pine_color_lgradient_view, R.id.pine_color_image_view, R.id.start_color_tv, R.id.end_color_tv})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.pine_color_lgradient_view:
                Shader.TileMode tileMode = sTileMode[mCuurPosition % 3];
                mNormalLgradientView.changeTileMode(tileMode);
                Toast.makeText(this, tileMode.name(), Toast.LENGTH_SHORT).show();
                mCuurPosition++;
                break;
            case R.id.pine_color_image_view:
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                int randomImageId = ASourceUtil.getRandomImageId();
                mNormalImageView.setImageResource(randomImageId);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), randomImageId, options);
                int startColor = calcBitmapAvgColorByAvgRGB(bitmap, 0.2f, 100, true);
                int endColor = calcBitmapAvgColorByAvgRGB(bitmap, 0.2f, 100, false);
                mStartColorTv.setBackgroundColor(startColor);
                mEndColorTv.setBackgroundColor(endColor);
                mNormalLgradientView.setColor(startColor, endColor);
                break;
            case R.id.start_color_tv:
                ViewGroup.LayoutParams layoutParams = mPineColorFl.getLayoutParams();
                layoutParams.height = HEIGHT;
                layoutParams.width = WIDTH;
                mPineColorFl.setLayoutParams(layoutParams);
                Bitmap bitmap1 = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(bitmap1);
                mPineColorFl.draw(canvas);

                mNormalImageView.setImageBitmap(bitmap1);

                try {
                    saveFile(path, bitmap1);
                    Toast.makeText(this, "保存成功，路径为" + path, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.end_color_tv:
                break;
        }
    }

    String path = Environment.getExternalStorageDirectory() + "/12.png";

    public void saveFile(String picpath, Bitmap bm) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picpath));
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        bos.flush();
        bos.close();
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
            Log.e(TAG, "calcBitmapAvgColorTop: " + pixel);
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
