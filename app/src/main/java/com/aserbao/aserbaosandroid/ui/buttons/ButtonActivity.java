package com.aserbao.aserbaosandroid.ui.buttons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaThird.dagger2.simple.Student;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        ButterKnife.bind(this);
    }


    public static void launch(Activity activity){
        Intent intent = new Intent(activity,ButtonActivity.class);
        activity.startActivity(intent);
    }
    @Inject
    Student mStudent;

    private static final String TAG = "ButtonActivity";
    @OnClick({R.id.one_btn, R.id.two_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one_btn:
                Log.e(TAG, "onViewClicked: " + mStudent.getName() );
                break;
            case R.id.two_btn:

                break;
        }
    }
}
