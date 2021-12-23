package com.example.base.utils.file;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MOVIES;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.example.base.utils.log.ALogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AFileUtils {
    /**
     * 递归拷贝Asset目录中的文件到rootDir中
     * Recursively copy the files in the Asset directory to rootDir
     * @param assets
     * @param path
     * @param rootDir
     * @throws IOException
     */
    public static void copyAssets(AssetManager assets, String path, String rootDir) throws IOException {
        if (isAssetsDir(assets, path)) {
            File dir = new File(rootDir + File.separator + path);
            if (!dir.exists() && !dir.mkdirs()) {
                throw new IllegalStateException("mkdir failed");
            }
            for (String s : assets.list(path)) {
                copyAssets(assets, path + "/" + s, rootDir);
            }
        } else {
            InputStream input = assets.open(path);
            File dest = new File(rootDir, path);
            copyToFileOrThrow(input, dest);
        }

    }

    public static void copyAssets(AssetManager assets, String dir) throws IOException {
        String[] paths = assets.list("");
        for (String s : paths) {
            copyAssets(assets, s, dir);
        }
    }

    public static boolean isAssetsDir(AssetManager assets, String path) {
        try {

            String[] files = assets.list(path);
            return files != null && files.length > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void copyToFileOrThrow(InputStream inputStream, File destFile)
        throws IOException {
        if (destFile.exists()) {
            return;

        }
        File file = destFile.getParentFile();
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(destFile);
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            out.flush();
            try {
                out.getFD().sync();
            } catch (IOException e) {
            }
            out.close();
        }
    }


    /** {zh}
     * 解压压缩包
     * 解压后删除zip文件
     * unzip the package and delete thd zip file
     * @return
     */
    /** {en}
     * Unzip package
     *  Delete zip file after decompression
     * unzip the package and delete thd zip file
     * @return
     */

    public static boolean unzipAssetFile(Context context, String zipFilePath, File dstDir) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(context.getAssets().open(zipFilePath));
            return unzipFile(zipInputStream, dstDir);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean unzipFile(String filePath, File dstDir) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File(filePath)));
            boolean ret = unzipFile(zipInputStream, dstDir);
            ALogUtils.d("unzipFile ret =" + ret);
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }
        ALogUtils.d("unzipFile ret =" + false);

        return false;
    }

    public static boolean unzipFile(ZipInputStream zipInputStream, File dstDir) {

        try {
            if (dstDir.exists()) {
                dstDir.delete();
            }
            dstDir.mkdirs();
            if (null == zipInputStream) {
                return false;
            }
            ZipEntry entry;
            String name;
            do {
                entry = zipInputStream.getNextEntry();
                if (null != entry) {
                    name = entry.getName();
                    if (entry.isDirectory()) {
                        name = name.substring(0, name.length() - 1);
                        File folder = new File(dstDir, name);
                        folder.mkdirs();

                    } else {
                        //   {zh} 否则创建文件,并输出文件的内容     {en} Otherwise create the file and output the contents of the file
                        File file = new File(dstDir, name);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
                        int len;
                        byte[] buffer = new byte[1024];
                        while ((len = zipInputStream.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                            out.flush();
                        }
                        out.close();

                    }
                }

            } while (null != entry);

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
        return true;

    }

    public static boolean clearDir(File dir) {
        if (!dir.exists()) {
            return true;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                clearDir(file);
                file.delete();
            } else {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        return true;
    }

    public static String generateVideoFile() {
        String directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).getAbsolutePath();
        String name = createtFileName(".mp4");
        return directory + "/" + name;
    }

    public static String generateCacheFile(String fileName) {
        String directory = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath();
        return directory + File.separator + fileName;
    }

    public static synchronized String createtFileName(String suffix) {
        java.util.Date dt = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName= fmt.format(dt);
        fileName = fileName + suffix; //extension, you can change it.
        return fileName;
    }
}
