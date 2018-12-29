package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.interfaces;

import com.aserbao.aserbaosandroid.functions.hobbies_and_interests.douyinUrl.beans.Video;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/29
 * email: 1142803753@qq.com
 */
public interface IAnalyzeTaskCallBack {
    void onAnalzed(Video video);
    void onCanceled();
    void onAnalyzeError(Exception e);
}
