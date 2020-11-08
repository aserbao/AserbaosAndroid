package com.aserbao.aserbaosandroid.ui.colorPicker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.LinearColorPicker;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.ColorPicker;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.OpacityBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.SVBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.SaturationBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.ValueBar;
import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorPickerActivity extends AppCompatActivity {
    private static final String TAG = "ColorPickerActivity";
    @BindView(R.id.linear_color_picker)
    LinearColorPicker linearColorPicker;
    @BindView(R.id.color_iv)
    ImageView colorIv;
    @BindView(R.id.color_card_view)
    CardView colorCardView;
    @BindView(R.id.holo_color_picker)
    ColorPicker holoColorPicker;
    @BindView(R.id.opacity_bar)
    OpacityBar opacityBar;
    @BindView(R.id.saturation_bar)
    SaturationBar saturationBar;
    @BindView(R.id.value_bar)
    ValueBar valueBar;
    @BindView(R.id.sv_bar)
    SVBar svBar;
    @BindView(R.id.holo_color_ll)
    LinearLayout holoColorLl;
    @BindView(R.id.first_btn)
    Button firstBtn;
    @BindView(R.id.second_btn)
    Button secondBtn;
    @BindView(R.id.three_btn)
    Button threeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        ButterKnife.bind(this);
        initFirstBtn();
        initSecondBtn();

    }





    private void initSecondBtn() {
        holoColorPicker.addOpacityBar(opacityBar);
        holoColorPicker.addSaturationBar(saturationBar);
        holoColorPicker.addValueBar(valueBar);
        holoColorPicker.addSVBar(svBar);
        holoColorPicker.setOnColorSelectedListener(new ColorPicker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                setSelColor(color);
                Log.e(TAG, "onColorSelect: " + "color  =  " + String.valueOf(color));
            }
        });
        opacityBar.setOnOpacityChangedListener(new OpacityBar.OnOpacityChangedListener() {
            @Override
            public void onOpacityChanged(int opacity) {
                setSelColor(opacityBar.getColor());
            }
        });
        saturationBar.setOnSaturationChangedListener(new SaturationBar.OnSaturationChangedListener() {
            @Override
            public void onSaturationChanged(int saturation) {
                setSelColor(saturationBar.getColor());
            }
        });

        valueBar.setOnValueChangedListener(new ValueBar.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                setSelColor(valueBar.getColor());
            }
        });


    }

    private void initFirstBtn() {
        linearColorPicker.setOnColorSelectListener(new LinearColorPicker.OnColorSelectListener() {
            @Override
            public void onColorSelect(int color, int progress) {
                setSelColor(color);
                Log.e(TAG, "onColorSelect: " + progress + "color  =  " + String.valueOf(color));
            }
        });
    }

    public void setSelColor(int color) {
//        firstBtn.setBackgroundColor(color);
//        secondBtn.setBackgroundColor(color);
//        threeBtn.setBackgroundColor(color);
        colorIv.setBackgroundColor(color);
    }

    @OnClick({R.id.first_btn, R.id.second_btn, R.id.three_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_btn:
                colorCardView.setVisibility(View.VISIBLE);
                holoColorLl.setVisibility(View.GONE);
                break;
            case R.id.second_btn:
                holoColorLl.setVisibility(View.VISIBLE);
                colorCardView.setVisibility(View.GONE);
                break;
            case R.id.three_btn:
                holoColorLl.setVisibility(View.GONE);
                colorCardView.setVisibility(View.GONE);
                valueBar.setVisibility(View.VISIBLE);
                svBar.setVisibility(View.VISIBLE);
                break;
        }
    }
}
