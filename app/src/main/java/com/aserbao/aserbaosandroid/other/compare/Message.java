package com.aserbao.aserbaosandroid.other.compare;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/19
 * email: this is empty email
 */
public class Message implements Comparable<Message>{
    private static final String TAG = "Message";
    List<Video> messageList;
    int id;
    String content;

    public List<Video> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Video> messageList) {
        this.messageList = messageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" id = " + id  + " \n");
        for (int i = 0; i < messageList.size(); i++) {
            Video video = messageList.get(i);
            sb.append(" videoPath = " + video.getVideoPath() + "  isRead = " + video.isRead + " \n");
        }
        sb.append("content = " +  content + " \n");
        sb.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        return sb.toString();
    }


    /**
     * @param o
     * @return 小于零正序，大于零反序
     */
    @Override
    public int compareTo(@NonNull Message o) {
        int i = this.id - o.id;
        Log.e(TAG, "compareTo: " + i );
        return i;
    }

}
