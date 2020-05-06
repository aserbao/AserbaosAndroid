package com.aserbao.aserbaosandroid.aaThird.okdownload;

import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.others.AppFileMgr;
import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener3;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static com.example.base.utils.data.StaticFinalValues.STORAGE_TEMP_FILE;

public class OkDownLoadActivity extends BaseRecyclerViewActivity{
    private static final String TAG = "OkDownLoadActivity";
    private DownloadTask downloadTask;
    final String filename = "aserbao_test.apk";
//    final String url = "https://cdn.llscdn.com/yy/files/xs8qmxn8-lls-LLS-5.8-800-20171207-111607.apk";
//    final String url = "http://npic.getremark.com/68d111bddc1a71def3ed6033ebcbb79c-5888a2b749d40ddb441af68db5a7f354";
//    final String url = "http://npic.getremark.com/68d111bddc1a71def3ed6033ebcbb79c-3db263493a128bf6da1b1d12d06cb194";
    final String url = "https://s3.cn-north-1.amazonaws.com.cn/dev.android.package/ss-4_1_8.apk";
//    final String url = "https://cn.bing.com/th?id=OIP.xq1C2fmnSw5DEoRMC86vJwD6D6&pid=Api&rs=1";
    File parentFile = new File(STORAGE_TEMP_FILE);

    //=======url list
    private String[] urlList = {
//        "http://npic.getremark.com/68d111bddc1a71def3ed6033ebcbb79c-5888a2b749d40ddb441af68db5a7f354",
//        "http://npic.getremark.com/68d111bddc1a71def3ed6033ebcbb79c-3db263493a128bf6da1b1d12d06cb194",
        "http://npic.getremark.com/0246eeaf9a9e3225373c66a059839fe5-3af72f6473790252e474c891c0d2676c",
        "http://npic.getremark.com/2c16849fdbf2ccc229cea92f5f3b7f8d-ddc395724563289c8d4e104b3be164a1",
//        "https://dldir1.qq.com/foxmail/work_weixin/wxwork_android_2.4.5.5571_100001.apk"
    };
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("其他测试",404));
        mBaseRecyclerBean.add(new BaseRecyclerBean("检查是否已经下载完成",100));
        mBaseRecyclerBean.add(new BaseRecyclerBean("删除已下载好的文件",101));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Single Download",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("BunchDownLoad",2));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
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
        downloadTask.enqueue(new DownloadListener3() {
            @Override
            protected void started(@NonNull DownloadTask task) {
                Log.d(TAG, "started() called with: task = [" + task + "]");
            }

            @Override
            protected void completed(@NonNull DownloadTask task) {
                Log.d(TAG, "completed() called with: task = [" + task + "]");
            }

            @Override
            protected void canceled(@NonNull DownloadTask task) {

            }

            @Override
            protected void error(@NonNull DownloadTask task, @NonNull Exception e) {

            }

            @Override
            protected void warn(@NonNull DownloadTask task) {

            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {
                Log.d(TAG, "retry() called with: task = [" + task + "], cause = [" + cause + "]");
            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {
                Log.d(TAG, "connected() called with: task = [" + task + "], blockCount = [" + blockCount + "], currentOffset = [" + currentOffset + "], totalLength = [" + totalLength + "]");
            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                Log.d(TAG, "progress() called with: task = [" + task + "], currentOffset = [" + currentOffset + "], totalLength = [" + totalLength + "]");
            }
        });
        /*downloadTask.enqueue(new DownloadListener4WithSpeed() {
            @Override
            public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info, boolean fromBreakpoint, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {
                totalLength = info.getTotalLength();
                Log.e(TAG, "infoReady() called with: task = [" + task + "], info = [" + info + "], fromBreakpoint = [" + fromBreakpoint + "], model = [" + model + "]");
            }

            @Override
            public void progressBlock(@NonNull DownloadTask task, int blockIndex, long currentBlockOffset, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "progressBlock() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], currentBlockOffset = [" + currentBlockOffset + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, @NonNull SpeedCalculator taskSpeed) {
                Log.e(TAG, "progress() called with: task = [" + task + "], currentOffset = [" + currentOffset + "], taskSpeed = [" + taskSpeed.speed() + "]" + " 目前进度为 = "+ (currentOffset*100)/totalLength + "%");
            }

            @Override
            public void blockEnd(@NonNull DownloadTask task, int blockIndex, BlockInfo info, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "blockEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], info = [" + info + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull SpeedCalculator taskSpeed) {
                Log.e(TAG, "taskEnd() called with: task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "], taskSpeed = [" + taskSpeed + "]" + task.getFile().toString() + "  ===" + task.getParentFile().toString());
                Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.e(TAG, "taskStart() called with: task = [" + task + "]");
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.e(TAG, "connectStart() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], requestHeaderFields = [" + requestHeaderFields + "]");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.e(TAG, "connectEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], responseCode = [" + responseCode + "], responseHeaderFields = [" + responseHeaderFields + "]");
            }
        });*/
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
                Log.e(TAG, "taskEnd() called with: context = [" + context + "], task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "], remainCount = [" + remainCount + "]" + task.getFilename());
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
                Log.e(TAG, "queueEnd() called with: context = [" + context + "]");
                DownloadTask[] tasks = context.getTasks();
                for (DownloadTask task : tasks) {
                    String tag = (String) task.getTag(1);
//                    Log.e(TAG, "queueEnd: " + tag  + task.getFilename() + "   "  + task.getFile().toString());
                }
            }
        }).build();
        build.start(new DownloadListener4WithSpeed() {
            @Override
            public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info, boolean fromBreakpoint, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {
                totalLength = info.getTotalLength();
                Log.e(TAG, "infoReady() called with: task = [" + task + "], info = [" + info + "], fromBreakpoint = [" + fromBreakpoint + "], model = [" + model + "]");
            }

            @Override
            public void progressBlock(@NonNull DownloadTask task, int blockIndex, long currentBlockOffset, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "progressBlock() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], currentBlockOffset = [" + currentBlockOffset + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, @NonNull SpeedCalculator taskSpeed) {
                Log.e(TAG, "progress() called with: task = [" + task + "], currentOffset = [" + currentOffset + "], taskSpeed = [" + taskSpeed.speed() + "]" + " 目前进度为 = "+ (currentOffset*100)/totalLength + "%");
            }

            @Override
            public void blockEnd(@NonNull DownloadTask task, int blockIndex, BlockInfo info, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "blockEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], info = [" + info + "], blockSpeed = [" + blockSpeed + "]");
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull SpeedCalculator taskSpeed) {
                Log.e(TAG, "taskEnd() called with: task = [" + task + "], cause = [" + cause + "], realCause = [" + realCause + "], taskSpeed = [" + taskSpeed + "]");
                Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.e(TAG, "taskStart() called with: task = [" + task + "]");
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.e(TAG, "connectStart() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], requestHeaderFields = [" + requestHeaderFields + "]");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.e(TAG, "connectEnd() called with: task = [" + task + "], blockIndex = [" + blockIndex + "], responseCode = [" + responseCode + "], responseHeaderFields = [" + responseHeaderFields + "]");
            }
        }, true);
    }







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
