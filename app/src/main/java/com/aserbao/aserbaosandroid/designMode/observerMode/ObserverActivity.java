package com.aserbao.aserbaosandroid.designMode.observerMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.designMode.observerMode.observer.FirstObserver;
import com.aserbao.aserbaosandroid.designMode.observerMode.observer.SecondObserver;
import com.aserbao.aserbaosandroid.designMode.observerMode.subject.NewsPaperOffice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObserverActivity extends AppCompatActivity {

    @BindView(R.id.btn_send_broadcast)
    Button mBtnSendBroadcast;
    @BindView(R.id.first_observer_tv)
    TextView mFirstObserverTv;
    @BindView(R.id.first_observer_btn)
    Button mFirstObserverBtn;
    @BindView(R.id.second_observer_tv)
    TextView mSecondObserverTv;
    @BindView(R.id.sencond_observer_btn)
    Button mSencondObserverBtn;

    private NewsPaperOffice mNewsPaperOffice;
    private FirstObserver mFirstObserver;
    private SecondObserver mSecondObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obaser);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_send_broadcast, R.id.first_observer_btn, R.id.sencond_observer_btn})
    public void onViewClicked(View view) {
        if (mNewsPaperOffice == null) {
            mNewsPaperOffice = new NewsPaperOffice();
        }
        switch (view.getId()) {
            case R.id.btn_send_broadcast:
                mNewsPaperOffice.notifyObserver();
                mFirstObserverTv.setText(mFirstObserver.getMessage());
                mSecondObserverTv.setText(mSecondObserver.getMessage());
                break;
            case R.id.first_observer_btn:
                if (mFirstObserver == null) {
                    mFirstObserver = new FirstObserver();
                }
                if(mFirstObserverBtn.getText().equals("订阅报纸")){
                    mFirstObserverBtn.setText("取消订阅");
                    mNewsPaperOffice.registerObserver(mFirstObserver);
                }else{
                    mFirstObserverBtn.setText("订阅报纸");
                    mNewsPaperOffice.removeObserver(mFirstObserver);
                }
                break;
            case R.id.sencond_observer_btn:
                if (mSecondObserver == null) {
                    mSecondObserver = new SecondObserver();
                }
                if(mFirstObserverBtn.getText().equals("订阅报纸")){
                    mSencondObserverBtn.setText("取消订阅");
                    mNewsPaperOffice.registerObserver(mSecondObserver);
                }else{
                    mSencondObserverBtn.setText("订阅报纸");
                    mNewsPaperOffice.removeObserver(mSecondObserver);
                }
                break;
        }
    }
}
