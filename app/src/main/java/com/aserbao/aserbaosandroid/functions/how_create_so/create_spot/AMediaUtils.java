package com.aserbao.aserbaosandroid.functions.how_create_so.create_spot;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * 功能: 多媒体工具类
 * @author aserbao
 * @date : On 2019/1/15 11:14 AM
 * @email: 1142803753@qq.com
 * @project:android-spot
 * @package:com.getremark.spot.aa.common.utils.media
 */
public class AMediaUtils {
    public static void scanFile(Context context, String filePath) {
        if (!checkFile(filePath))
            return;
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(intent);
    }
    public static  boolean checkFile(String filePath){
        return new File(filePath).exists();
    }


    public static int  calcFrequency2(byte[] pcmdata) {
        short[] music = (!isBigEnd()) ? byteArray2ShortArrayLittle( pcmdata,  pcmdata.length / 2) :
                byteArray2ShortArrayBig( pcmdata,  pcmdata.length / 2);


        return calculateRealVolume(music,music.length);
    }

    public static short[] translateShort(byte[] pcmdata){
        short[] music = (!isBigEnd()) ? byteArray2ShortArrayLittle( pcmdata,  pcmdata.length / 2) :
                byteArray2ShortArrayBig( pcmdata,  pcmdata.length / 2);
        return music;
    }

    private static boolean isBigEnd() {
        short i = 0x1;
        boolean bRet = ((i >> 8) == 0x1);
        return bRet;
    }

    private static short[] byteArray2ShortArrayBig(byte[] data, int items) {
        short[] retVal = new short[items];
        for (int i = 0; i < retVal.length; i++)
            retVal[i] = (short) ((data[i * 2 + 1] & 0xff) | (data[i * 2] & 0xff) << 8);

        return retVal;
    }

    private static short[] byteArray2ShortArrayLittle(byte[] data, int items) {
        short[] retVal = new short[items];
        for (int i = 0; i < retVal.length; i++)
            retVal[i] = (short) ((data[i * 2] & 0xff) | (data[i * 2 + 1] & 0xff) << 8);

        return retVal;
    }

    protected static int calculateRealVolume(short[] buffer, int readSize) {
        double sum = 0;
        for (int i = 0; i < readSize; i++) {
            sum += buffer[i] * buffer[i];
        }
        if (readSize > 0) {
            double amplitude = sum / readSize;
            int mVolume = (int) Math.sqrt(amplitude);
            return mVolume;
        }
        return -1;
    }


}
