package com.aserbao.aserbaosandroid.aaThird.okdownload;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.AppFileMgr;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static com.aserbao.aserbaosandroid.commonData.StaticFinalValues.INDEX_TAG;
import static com.aserbao.aserbaosandroid.commonData.StaticFinalValues.STORAGE_TEMP_APK_PATH;
import static com.aserbao.aserbaosandroid.commonData.StaticFinalValues.STORAGE_TEMP_FILE;

public class OkDownLoadActivity extends BaseRecyclerViewActivity implements DownloadListener{
    private static final String TAG = "OkDownLoadActivity";
    private DownloadTask downloadTask;
    final String filename = "aserbao_test.apk";
    final String url = "https://cdn.llscdn.com/yy/files/xs8qmxn8-lls-LLS-5.8-800-20171207-111607.apk";
//    final String url = "https://cn.bing.com/th?id=OIP.xq1C2fmnSw5DEoRMC86vJwD6D6&pid=Api&rs=1";
    File parentFile = new File(STORAGE_TEMP_FILE);
    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("其他测试",404));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("检查是否已经下载完成",100));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("删除已下载好的文件",101));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Single Download",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("BunchDownLoad",2));
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 404:
                String absolutePath = Environment.getDownloadCacheDirectory().getAbsolutePath();
                Log.e(TAG, "itemClickBack: " + absolutePath );


                break;
            case 100:
                boolean completed = StatusUtil.isCompleted(url, parentFile.getPath(), null);
                Toast.makeText(mContext, "文件是否下载完成？"+String.valueOf(completed), Toast.LENGTH_SHORT).show();
                break;
            case 101:
                if (parentFile.exists()) {
                    boolean delete = AppFileMgr.deleteFile(parentFile);
                    Toast.makeText(mContext, "删除"+String.valueOf(delete), Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                singleDownload();
                break;
            case 2:
                bunchDownload();
                break;
        }
    }

    long totalLength = 0;
    /**
     * taskStart：下载任务开始
     * connectTrialStart：连接状态开始
     * connectTrialEnd：连接状态结束
     *
     */
    public void singleDownload(){
        String s = generateFileName(url);
        parentFile = new File(STORAGE_TEMP_FILE);
        downloadTask = new DownloadTask.Builder(url, parentFile)
//            .setFilename(s)
            // the minimal interval millisecond for callback progress
            .setMinIntervalMillisCallbackProcess(16)
            // ignore the same task has already completed in the past.
            .setPassIfAlreadyCompleted(true)
            .build();

        Log.e(TAG, "singleDownload: " + s );
        downloadTask.enqueue(new DownloadListener4WithSpeed() {
            @Override
            public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info, boolean fromBreakpoint, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {
                totalLength = info.getTotalLength();
                Log.d(TAG, "infoReady() called with: task = [" + task + "], info = [" + info + "], fromBreakpoint = [" + fromBreakpoint + "], model = [" + model + "]");
            }

            @Override
            public void progressBlock(@NonNull DownloadTask task, int blockIndex, long currentBlockOffset, @NonNull SpeedCalculator blockSpeed) {
                Log.d(TAG, "progressBlock() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], currentBlockOffset = [" + currentBlockOffset + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, @NonNull SpeedCalculator taskSpeed) {
                Log.d(TAG, "progress() called with: task = [" + task + "], currentOffset = [" + currentOffset + "], taskSpeed = [" + taskSpeed.speed() + "]" + " 目前进度为 = "+ (currentOffset*100)/totalLength + "%");
            }

            @Override
            public void blockEnd(@NonNull DownloadTask task, int blockIndex, BlockInfo info, @NonNull SpeedCalculator blockSpeed) {
                Log.d(TAG, "blockEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], info = [" + info + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull SpeedCalculator taskSpeed) {
                Log.d(TAG, "taskEnd() called with: task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "], taskSpeed = [" + taskSpeed + "]" + task.getFile());
                Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.d(TAG, "taskStart() called with: task = [" + task + "]");
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.d(TAG, "connectStart() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], requestHeaderFields = [" + requestHeaderFields + "]");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.d(TAG, "connectEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], responseCode = [" + responseCode + "], responseHeaderFields = [" + responseHeaderFields + "]");
            }
        });
    }


    /**
     * 多个下载
     */
    public void bunchDownload(){

        DownloadContext.Builder builder = new DownloadContext.QueueSet()
            .setParentPathFile(parentFile)
            .setMinIntervalMillisCallbackProcess(300)
            .commit();
        for (int i = 0; i < urlList.length; i++) {
            builder.bind(urlList[i]);
        }
        DownloadContext build = builder.setListener(new DownloadContextListener() {
            @Override
            public void taskEnd(@NonNull DownloadContext context, @NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, int remainCount) {
                Log.d(TAG, "taskEnd() called with: context = [" + context + "], task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "], remainCount = [" + remainCount + "]" + task.getFilename());
                if (cause == EndCause.COMPLETED || cause == EndCause.FILE_BUSY || cause == EndCause.SAME_TASK_BUSY){
                        if (remainCount == 1) {
                            task.addTag(1, "remainCount == 1");
                        }else{
                            task.addTag(1, "remainCount == 0");
                        }
                }else if (cause != EndCause.CANCELED){

                }
            }

            @Override
            public void queueEnd(@NonNull DownloadContext context) {
                Log.d(TAG, "queueEnd() called with: context = [" + context + "]");
                DownloadTask[] tasks = context.getTasks();
                for (DownloadTask task : tasks) {
                    String tag = (String) task.getTag(1);
//                    Log.e(TAG, "queueEnd: " + tag  + task.getFilename() + "   "  + task.getFile().toString());
                }
            }
        }).build();
        build.start(null,true);
    }

    @Override
    public void taskStart(@NonNull DownloadTask task) {
        Log.d(TAG, "taskStart() called with: task = [" + task + "]");
    }


    @Override
    public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {
        Log.d(TAG, "connectTrialStart() called with: task = [" + task + "], requestHeaderFields = [" + requestHeaderFields + "]");
    }

    @Override
    public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
        Log.d(TAG, "connectTrialEnd() called with: task = [" + task + "], responseCode = [" + responseCode + "], responseHeaderFields = [" + responseHeaderFields + "]");
    }

    @Override
    public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {
        Log.d(TAG, "downloadFromBeginning() called with: task = [" + task + "], info = [" + info + "], cause = [" + cause + "]");
    }

    @Override
    public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {
        Log.d(TAG, "downloadFromBreakpoint() called with: task = [" + task + "], info = [" + info + "]");
    }

    @Override
    public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
        Log.d(TAG, "connectStart() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], requestHeaderFields = [" + requestHeaderFields + "]");
    }

    @Override
    public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
        Log.d(TAG, "connectEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], responseCode = [" + responseCode + "], responseHeaderFields = [" + responseHeaderFields + "]");
    }

    @Override
    public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {
        Log.d(TAG, "fetchStart() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], contentLength = [" + contentLength + "]");
    }

    @Override
    public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {
        Log.d(TAG, "fetchProgress() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], increaseBytes = [" + increaseBytes + "]");
    }

    @Override
    public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {
        Log.d(TAG, "fetchEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], contentLength = [" + contentLength + "]");
    }

    @Override
    public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {
        Log.d(TAG, "taskEnd() called with: task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "]");
    }
    //=======url list
    private String[] urlList = {
//        "https://cdn.llscdn.com/yy/files/xs8qmxn8-lls-LLS-5.8-800-20171207-111607.apk",
//        "https://dldir1.qq.com/foxmail/work_weixin/wxwork_android_2.4.5.5571_100001.apk",
        "https://cn.bing.com/images/search?view=detailV2&ccid=%2blpv0iBX&id=495339B25719E51B83001DB2BFD5C2A99454BAF4&thid=OIP.-lpv0iBXzismFDFiiqDQcAHaEo&mediaurl=http%3a%2f%2fimg1.3lian.com%2fimg013%2fv4%2f96%2fd%2f41.jpg&exph=800&expw=1280&q=%e5%9b%be%e7%89%87&simid=608055007415567135&selectedIndex=0",
        "https://cn.bing.com/th?id=OIP.xq1C2fmnSw5DEoRMC86vJwD6D6&pid=Api&rs=1",
    };


    //=========
    public static String generateFileName(final String url) {
        return md5(url);
    }
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
