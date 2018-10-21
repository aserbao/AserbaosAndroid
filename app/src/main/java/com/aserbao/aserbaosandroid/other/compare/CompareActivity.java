package com.aserbao.aserbaosandroid.other.compare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompareActivity extends AppCompatActivity {

    @BindView(R.id.original_tv)
    TextView mOriginalTv;
    @BindView(R.id.final_tv)
    TextView mFinalTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    private List<Message> messageList = new ArrayList<>();
    private void initData() {
        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            ArrayList<Video> videoArrayList = new ArrayList<>();
            Random random = new Random();
            for (int j = 0; j < 1; j++) {
                Video video = new Video();
                if(random.nextInt(10) % 2 == 0){
                    video.setRead(true);
                }else{
                    video.setRead(false);
                }
                videoArrayList.add(video);
            }
            message.setMessageList(videoArrayList);
            message.setId(random.nextInt(100));
            message.setContent("这是第" + i+ "条Message");
            messageList.add(message);
        }
    }

    private void initView() {
        showTv(mOriginalTv);
    }

    public void showTv(TextView textView){
        StringBuffer sb  = new StringBuffer();
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            sb.append(message.toString());
        }
        textView.setText(sb.toString());
    }

    @OnClick({R.id.sort_btn,R.id.sort_second_btn})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.sort_btn:
                Collections.sort(messageList);
                break;
            case R.id.sort_second_btn:
                messageList = sortByRead(messageList);
                break;
        }

        showTv(mFinalTv);
    }

    public List<Message> sortByRead(List<Message> messageList){
        List<Message> resultMessage = new ArrayList<>();
        List<Message> readAllMessage = new ArrayList<>();
        Collections.sort(messageList);
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            List<Video> videoList = message.getMessageList();
            if(videoList.get(videoList.size()-1).isRead) {
                readAllMessage.add(message);
            }else{
                resultMessage.add(message);
            }
            Log.e("test", "sortByRead: "  + i +  " \n结果：" + resultMessage.toString());
        }
        resultMessage.addAll(readAllMessage);
        return resultMessage;
    }
}
