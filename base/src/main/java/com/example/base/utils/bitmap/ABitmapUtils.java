package com.example.base.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
