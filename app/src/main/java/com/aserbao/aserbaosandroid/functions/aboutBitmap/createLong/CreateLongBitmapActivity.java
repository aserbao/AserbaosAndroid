package com.aserbao.aserbaosandroid.functions.aboutBitmap.createLong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.AUtils.utils.MainLooper;
import com.aserbao.aserbaosandroid.AUtils.utils.date.AppScreenMgr;
import com.aserbao.aserbaosandroid.AUtils.utils.random.RandomValue;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.ImageSource;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateLongBitmapActivity extends AppCompatActivity {

    public static final int INT_ADD_NUM = 100;
    @BindView(R.id.about_bitmap_nested_scroll_view)
    ScrollView mAboutBitmapNestedScrollView;
    @BindView(R.id.about_bitmap_ll)
    LinearLayout mAboutBitmapll;
    @BindView(R.id.about_bitmap_tv)
    TextView mAboutBitmapTv;


    String path = Environment.getExternalStorageDirectory() + "/12.png";
    private long mStartTime;
    private int itemNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_bitmap);
        ButterKnife.bind(this);
    }


    public static Bitmap getListViewBitmap(RecyclerView listView, String picpath) {
        int h = 0;
        Bitmap bitmap;
        // 获取listView实际高度

        for (int i = 0; i < listView.getChildCount(); i++) {
            h += listView.getChildAt(i).getHeight();
        }
        listView.getHeight();
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h,
            Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        listView.draw(canvas);
        saveBitmap(picpath, bitmap);
        return bitmap;
    }

    private static void saveBitmap(String picpath, Bitmap bitmap) {
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(picpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片
     * @param bm
     * @throws IOException
     */
    public  void saveFile(String picpath,Bitmap bm ) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picpath));
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        bos.flush();
        bos.close();
       /* //把图片保存后声明这个广播事件通知系统相册有新图片到来
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        sendBroadcast(intent);*/
    }

    private static final String TAG = "CreateLongBitmapActivity";

    public static Bitmap getScrollViewBitmap(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        Log.e(TAG, "getScrollViewBitmap: " + h);
        // 创建相应大小的bitmap

        bitmap = Bitmap.createBitmap(AppScreenMgr.getScreenWidth(scrollView.getContext()), h,
            Bitmap.Config.ARGB_4444);
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#f2f7fa"));
        scrollView.draw(canvas);
        return bitmap;
    }

    @OnClick({R.id.create_long_bitmap_btn, R.id.add_item_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_long_bitmap_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mStartTime = System.currentTimeMillis();
                        Bitmap scrollViewBitmap = getScrollViewBitmap(mAboutBitmapNestedScrollView);
                        Log.e(TAG, "onViewClicked: 绘制完成时间" + (System.currentTimeMillis() - mStartTime)/1000 + "s" );
//                        saveBitmap(path, scrollViewBitmap);
                        try {
                            saveFile(path, scrollViewBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String msg =  (System.currentTimeMillis() - mStartTime) / 1000 + "s";
                        Log.e(TAG, "onViewClicked: " + msg);
                        MainLooper.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAboutBitmapTv.setText(mAboutBitmapTv.getText() + " \n 耗时： " + msg );
                            }
                        });
                    }
                }).start();
                break;
            case R.id.add_item_btn:

                for (int i = itemNum; i < itemNum + INT_ADD_NUM; i++) {
                    View view1 = LayoutInflater.from(this).inflate(R.layout.about_bitmap_long_bitmap_item, null);
                    ImageView imageView = (ImageView) view1.findViewById(R.id.about_bitmap_iv);
                    TextView titleTextView = (TextView) view1.findViewById(R.id.about_bitmap_title_tv);
                    TextView contentTextView = (TextView) view1.findViewById(R.id.about_bitmap_content_tv);
                    String chineseName = RandomValue.getChineseName();
                    titleTextView.setText(chineseName + " 编号：" + String.valueOf(i)) ;
                    contentTextView.setText(RandomValue.getTel());
                    imageView.setImageResource(R.drawable.emoji_00);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    mAboutBitmapll.addView(view1);
                }
                itemNum += INT_ADD_NUM;
                mAboutBitmapTv.setText("一共有" + String.valueOf(itemNum) + "个item");
                break;
        }
    }
}
