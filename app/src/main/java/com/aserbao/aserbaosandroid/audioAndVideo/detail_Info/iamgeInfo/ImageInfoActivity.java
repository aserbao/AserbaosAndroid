package com.aserbao.aserbaosandroid.audioAndVideo.detail_Info.iamgeInfo;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageInfoActivity extends AppCompatActivity {

    @BindView(R.id.show_info_tv)
    TextView mShowInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iamge_detail_info_btn)
    public void onViewClicked() {
//        Options options = getImageOptions(R.drawable.emoji_00);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String inputPath = externalStorageDirectory + "/test.jpeg";
        String outputPath = externalStorageDirectory + "/test2.jpeg";
        ExifInterface anInterface = getExif(inputPath);
        String attribute = anInterface.getAttribute(ExifInterface.TAG_MAKE);
        mShowInfoTv.setText(attribute);
    }

    public  Options getImageOptions (int drawable){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),drawable,options);
        return options;
    }

    private static ExifInterface exif = null;

    //设置exif
    public static void setExif(String filepath,String longitude,String latitude,String time) {
        try {
            exif = new ExifInterface(filepath);     //根据图片的路径获取图片的Exif
        } catch (IOException ex) {
            Log.e("Mine", "cannot read exif", ex);
        }
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, longitude);    //把经度写进exif
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, latitude);     //把纬度写进exif
        exif.setAttribute(ExifInterface.TAG_DATETIME, time);              //把时间写进exif
        exif.setAttribute(ExifInterface.TAG_MAKE, longitude);             //把经度写进MAKE 设备的制造商，当然这样也是可以的，大家都是Stirng类型
        exif.setAttribute(ExifInterface.TAG_MODEL, latitude);             //把纬度写进MODEL
        try {
            exif.saveAttributes();         //最后保存起来
        } catch (IOException e) {
            Log.e("Mine", "cannot save exif", e);
        }
    }
    //获取exif
    public static ExifInterface getExif(String filepath){
        try {
            exif = new ExifInterface(filepath);    //想要获取相应的值：exif.getAttribute("对应的key")；比如获取时间：exif.getAttribute(ExifInterface.TAG_DATETIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exif;
    }
}
