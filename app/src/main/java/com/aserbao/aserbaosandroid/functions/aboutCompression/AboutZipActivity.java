package com.aserbao.aserbaosandroid.functions.aboutCompression;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;
//import com.leo618.zip.IZipCallback;
//import com.leo618.zip.ZipManager;

import java.io.File;
import java.util.ArrayList;

/**
 *
 */
public class AboutZipActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "AboutZipActivity";



    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("zip压缩",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("解压zip文件",1));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick) {
        /*File file = new File(StaticFinalValues.STORAGE_UNZIP_FILE);
        File zipFile = new File(StaticFinalValues.STORAGE_ZIP_FILE);
        if (!file.exists()) file.mkdir();
        if (!zipFile.exists()) zipFile.mkdir();
        switch (position){
            case 0:
                ArrayList<File> files = new ArrayList<>();
                files.add(new File(StaticFinalValues.STORAGE_UNZIP_FILE+"/123/cfg.toml")) ;
                ZipManager.zip(files, StaticFinalValues.STORAGE_ZIP_FILE + String.valueOf(System.currentTimeMillis())+".zip", new IZipCallback() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, "onStart() called");
                    }

                    @Override
                    public void onProgress(int percentDone) {
                        Log.d(TAG, "onProgress() called with: percentDone = [" + percentDone + "]");
                    }

                    @Override
                    public void onFinish(boolean success) {
                        Toast.makeText(mContext, "zip=" + success, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFinish() called with: success = [" + success + "]");
                    }
                });
                break;
            case 1:
                String targetZipFilePath = StaticFinalValues.STORAGE_ZIP_FILE + "/123.zip";
                String dir_zip_path = StaticFinalValues.STORAGE_UNZIP_FILE + "/";
                ZipManager.unzip(targetZipFilePath, dir_zip_path, new IZipCallback() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, "onStart() called");
                    }

                    @Override
                    public void onProgress(int percentDone) {
                        Log.d(TAG, "onProgress() called with: percentDone = [" + percentDone + "]");
                    }

                    @Override
                    public void onFinish(boolean success) {
                        Toast.makeText(mContext, "unzip = "+ success, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFinish() called with: success = [" + success + "]");
                    }
                });
                break;
        }*/
    }
}
