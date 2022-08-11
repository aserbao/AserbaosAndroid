package com.example.base.utils.bitmap;

import static android.os.Environment.DIRECTORY_DCIM;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;

import com.example.base.utils.file.AFileUtils;
import com.example.base.utils.log.ALogUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ABitmapUtils {




    /**
     * 保存图片
     * @param picpath String path = Environment.getExternalStorageDirectory() + "/12.png";
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

    /**
    * SDcard是否存在并可写
    */
    public static File saveToLocal(Bitmap bitmap){
        if (null == bitmap) return null;
        String temp = AFileUtils.createtFileName(".png");
        if (!checkSDCard()){
            ALogUtils.e("sdcard not mounted");
            return null;
        }
        File dcimFile =  Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
        File tempFile = new File(dcimFile,temp);
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tempFile = null;
        }catch (IOException e){
            e.printStackTrace();
            tempFile = null;
        }
        return tempFile;
    }

    /**
    * 判断SDCard是否存在,并可写
    */
    public static boolean checkSDCard() {
        String flag = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(flag);
    }

    /**
     * 图片缩放，小图居中显示
     */
    private static Bitmap zoomImg2(Bitmap bm, int destWidth, int destHeight) {
        int srcWidth = bm.getWidth();
        int srcHeight = bm.getHeight();
        float scale_x = destWidth * 1.0f / srcWidth;
        float scale_y = destHeight * 1.0f / srcHeight;

        float scale = Math.min(1.0f , Math.max(scale_x, scale_y));
        float offset_x = (scale * srcWidth - destWidth) / 2.0f;
        float offset_y = (scale * srcHeight - destHeight) / 2.0f;


        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale, 0, 0);
        matrix.postTranslate(-offset_x,-offset_y);
        // 如需要可自行设置 Bitmap.Config.RGB_8888 等等
        Bitmap bmpRet = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmpRet);
        Paint paint = new Paint();
        canvas.drawBitmap(bm, matrix, paint);
        return bmpRet;
    }

    /**
     * @param bitmap  要旋转的图片
     * @param degrees 选择的角度（单位 度）
     * @return 旋转后的Bitmap
     */
    public Bitmap rotate(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap != rotateBitmap) {
//                    bitmap.recycle();
                    return rotateBitmap;
                }
            } catch (OutOfMemoryError ex) {
                ex.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
    * 计算图片的sampleSize
    */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        while ((height / inSampleSize) >= reqHeight || (width / inSampleSize) >= reqWidth) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

    /**
     * 压缩Bitmap的大小
     * Compress Bitmap size
     * @param imagePath     图片文件路径
     * @param requestWidth  压缩到想要的宽度
     * @param requestHeight 压缩到想要的高度
     * @return
     */
    public static Bitmap decodeBitmapFromFile(String imagePath, int requestWidth, int requestHeight) {
        try{
            if (!TextUtils.isEmpty(imagePath)) {
                if (requestWidth <= 0 || requestHeight <= 0) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

                    return rotateImage(bitmap, imagePath);
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;//   {zh} 不加载图片到内存，仅获得图片宽高     {en} Do not load pictures into memory, only get picture width and height
                BitmapFactory.decodeFile(imagePath, options);
                if (options.outHeight == -1 || options.outWidth == -1) {
                    try {
                        ExifInterface exifInterface = new ExifInterface(imagePath);
                        int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//   {zh} 获取图片的高度     {en} Get the height of the picture
                        int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//   {zh} 获取图片的宽度     {en} Get the width of the picture

                        options.outWidth = width;
                        options.outHeight = height;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                options.inSampleSize = calculateInSampleSize(options, requestWidth, requestHeight); //   {zh} 计算获取新的采样率     {en} Calculate to get the new sampling rate
                ALogUtils.d( "inSampleSize: " + options.inSampleSize);
                options.inJustDecodeBounds = false;
                return rotateImage(BitmapFactory.decodeFile(imagePath, options),imagePath);

            } else {
                return null;
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    /**
    * 图片转正
    */
    public static Bitmap rotateImage(Bitmap bitmap,String path) throws IOException {
        if (bitmap == null) return null;
        if (TextUtils.isEmpty(path)) return null;
        int rotate = 0;
        ExifInterface exif;
        exif = new ExifInterface(path);
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotate = 270;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotate = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotate = 90;
                break;
        }
        if (rotate == 0)return bitmap;
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
            bitmap.getHeight(), matrix, true);
    }

    /**
    * 将图片南转byteBuffer
    */
    public static ByteBuffer bitmap2ByteBuffer(final Bitmap bitmap){
        int bytes = bitmap.getByteCount();

        ByteBuffer buffer = ByteBuffer.allocateDirect(bytes);
        bitmap.copyPixelsToBuffer(buffer);
        return buffer;

    }
    /**
    * ByteBuffer 像素转 图片
    */
    public static Bitmap getBitmapFromPixels(ByteBuffer byteBuffer, int width, int height) {

        Bitmap mCameraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        byteBuffer.position(0);
        mCameraBitmap.copyPixelsFromBuffer(byteBuffer);
        byteBuffer.position(0);
        return mCameraBitmap;
    }

    /**
    * YUV ByteBuffer 转 Bitmpa
    */
    public static Bitmap getBitmapFromYuv(ByteBuffer data, int width, int height) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        YuvImage yuvImage = new YuvImage(data.array(), ImageFormat.NV21, width, height, null);
        yuvImage.compressToJpeg(new Rect(0, 0, width, height), 50, out);
        byte[] imageBytes = out.toByteArray();
        Bitmap mCameraBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        return mCameraBitmap;
    }


}
