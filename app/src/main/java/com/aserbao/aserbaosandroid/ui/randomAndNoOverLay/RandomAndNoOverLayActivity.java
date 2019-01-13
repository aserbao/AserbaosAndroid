package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids.HexagonalGridView;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids_offical.Hex;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RandomAndNoOverLayActivity extends AppCompatActivity {

    @BindView(R.id.random_and_no_over_lay)
    RandomAndNoOverLay mRandomAndNoOverLay;
    @BindView(R.id.hexagonal_grid_view)
    HexagonalGridView mHexagonalGridView;
    @BindView(R.id.input_num_edit_text)
    EditText mInputNumEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_and_no_over_lay);
        ButterKnife.bind(this);
    }

    public void btn_click(View view) {
    }

    private static final String TAG = "RandomAndNoOverLayActiv";


    @OnClick({R.id.no_overlap_btn, R.id.hex_grid_offic_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no_overlap_btn:
                int ballNum = Integer.parseInt(mInputNumEditText.getText().toString().trim());
                if (ballNum < 7){
                    ballNum = 200;
                }
                mHexagonalGridView.start(ballNum);
                break;
            case R.id.hex_grid_offic_btn:
                Hex hex = new Hex(0, 0, 0);
                List<Hex> hexes = cubeSpiral(hex, 3);
//                List<Hex> hexes = cubeRing(hex, 3);
                Log.e(TAG, "onViewClicked: " + hexes.size());
                break;
        }
    }


    public List<Hex> cubeSpiral(Hex hex, int radius) {
        List<Hex> result = new ArrayList<>();
        result.add(hex);
        for (int i = 0; i <= radius; i++) {
            result.addAll(cubeRing(hex, i));
        }
        return result;
    }

    public List<Hex> cubeRing(Hex hex, int radius) {
        List<Hex> result = new ArrayList<>();
        hex = hex.add(hex.direction(4).scale(radius));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < radius; j++) {
                result.add(hex);
                hex = hex.neighbor(i);
            }
        }
        return result;
    }
}
