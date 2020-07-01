package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {
    public static Bitmap loadBitmapFromAssets(Context context, String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
    public static Bitmap loadBitmapFromDisk(Context context, String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public static Bitmap loadBitmapFromRaw(Context context, int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        return bitmap;
    }

    public static void saveBitmap(byte[] buffer, int width, int height) {
        try {
            // 调用image.compressToJpeg（）将YUV格式图像数据data转为jpg格式
            YuvImage image = new YuvImage(buffer, ImageFormat.NV21, width,
                    height, null);
            if (image != null) {
                ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                image.compressToJpeg(new Rect(0, 0, width, height), 80, outstream);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compressToJpeg(new Rect(0,0,width,height),80,stream);
                Bitmap bmp = BitmapFactory.decodeByteArray(stream.toByteArray(),0,stream.size());

                saveBitmap(bmp);

                outstream.flush();
            }
        } catch (Exception ex) {
            Log.e("Sys", "Error:" + ex.getMessage());
        }
    }

    //图片保存
    private static void saveBitmap(Bitmap b){
        String path = Environment.getExternalStorageDirectory()+ "/Omoshiroi/photo/";
        File folder=new File(path);
        if(!folder.exists()&&!folder.mkdirs()){
            Log.i("SaveBitmap", "save pic fail");
            return;
        }
        long dataTake = System.currentTimeMillis();
        final String jpegName=path+ dataTake +".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("SaveBitmap", "save pic success:"+jpegName);

    }

    /**
     * 图片保存
     * 将位图保存到本地文件
     */
    public static void saveBitmap2File(Bitmap bitmap, String fileName) throws Exception {
        if (bitmap!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte[]  bitmapByteCount = baos.toByteArray();

            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(bitmapByteCount);
            fos.flush();
            fos.close();
            baos.close();
        }
    }
}
