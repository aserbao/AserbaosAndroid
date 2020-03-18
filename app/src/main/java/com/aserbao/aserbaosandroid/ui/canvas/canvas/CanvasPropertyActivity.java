package com.aserbao.aserbaosandroid.ui.canvas.canvas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Canvas 性能优化
 */
public class CanvasPropertyActivity extends AppCompatActivity {

    @BindView(R.id.circle_view_canvas)
    CircleView mCircleViewCanvas;
    @BindView(R.id.canvas_btn)
    Button mCanvasBtn;
    @BindView(R.id.input_num_edit_text)
    EditText mInputNumEditText;
    @BindView(R.id.canvas_result_tv)
    TextView mCanvasResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_property);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.canvas_btn)
    public void onViewClicked() {
        String num = mInputNumEditText.getText().toString();
        mCircleViewCanvas.setCallBackListener(new CircleView.CallBackListener() {
            @Override
            public void back(long time) {
                mCanvasResultTv.setText("一共耗时：" + time/(float)1000 +"s");
            }
        },Integer.parseInt(num));
    }
}
