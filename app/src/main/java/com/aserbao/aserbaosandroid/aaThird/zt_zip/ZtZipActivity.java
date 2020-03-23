package com.aserbao.aserbaosandroid.aaThird.zt_zip;

import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.commonData.StaticFinalValues;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

public class ZtZipActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "ZtZipActivity";
//    public static final String storageZipFile = StaticFinalValues.STORAGE_ZIP_FILE + "/123.zip";
    public static final String storageZipFile = "/storage/emulated/0/spot/.zipFilter/NeanderthalAlt-05.01-1.005.073-eead04ef.zip";
    public static final String storageUnZipFile = StaticFinalValues.STORAGE_UNZIP_FILE + "/456";
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("检测zip包中是否存在某个文件",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("将zip包中的某个文件转为Byte[]",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("解压",20));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                boolean exists = ZipUtil.containsEntry(new File(storageZipFile), "frx.js");
                Toast.makeText(mContext, "检测结果为=" + exists, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                /*byte[] bytes = ZipUtil.unpackEntry(new File(storageZipFile), "frx.js");
                Toast.makeText(mContext, "功能不行哈", Toast.LENGTH_SHORT).show();
*/
                String dir_zip_path = Environment.getExternalStorageDirectory().getAbsolutePath()  + "/spot/unzipFilter" + "/" + String.valueOf(1) + "/";
                File file = new File(dir_zip_path);
                delFile(file,false);
                break;
            case 20:
                ZipUtil.unpack(new File(storageZipFile), new File(storageUnZipFile));

                /*String dzp = Environment.getExternalStorageDirectory().getAbsolutePath()  + "/spot/unzipFilter" + "/" + String.valueOf(1) + "/";
                File file1 = new File(dzp);
                if (!file1.exists()) file1.mkdirs();
                String[] list = file1.list();
                for (int i = 0; i < list.length; i++) {
                    String s = list[i];
                    Log.e(TAG, "itemClickBack: " + s );
                }*/
                break;
        }
    }

    /**
     * 删除文件（若为目录，则递归删除子目录和文件）
     *
     * @param file
     * @param delThisPath true代表删除参数指定file，false代表保留参数指定file
     */
    public static void delFile(File file, boolean delThisPath) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                int num = subFiles.length;
                // 删除子目录和文件
                for (int i = 0; i < num; i++) {
                    delFile(subFiles[i], true);
                }
            }
        }
        if (delThisPath) {
            file.delete();
        }
    }
}
