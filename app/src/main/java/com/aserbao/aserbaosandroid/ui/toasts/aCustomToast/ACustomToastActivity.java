package com.aserbao.aserbaosandroid.ui.toasts.aCustomToast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;
import com.aserbao.aserbaosandroid.ui.toasts.aCustomToast.ClickToast.ClickToast;
import com.bumptech.glide.Glide;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ACustomToastActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acustom_toast);
        ButterKnife.bind(this);
        mContext = this;
    }


    @OnClick({R.id.iamge_btn, R.id.second_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iamge_btn:
                View rootView = LayoutInflater.from(this).inflate(R.layout.toast_item_1, null);
                final ImageView mImageView = (ImageView) rootView.findViewById(R.id.toast_item_iv);
                rootView.findViewById(R.id.toast_item_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageView.setVisibility(View.GONE);
                    }
                });
                ClickToast toast = new ClickToast();
                /*toast.setView(rootView);
                toast.setDuration(Toast.LENGTH_LONG);*/
//                toast.show(toast);
                View toastv = toast.show(mContext, Toast.LENGTH_LONG, R.layout.toast_item_1);


                /*ImageView imageView = new ImageView(this);
                Glide.with(this).load(StaticFinalValues.IMAGE_URL).into(imageView);
                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();*/

                break;
            case R.id.second_btn:

                Toast.makeText(mContext, "This is What?", 10 * 1000).show();
                break;
        }
    }


}
