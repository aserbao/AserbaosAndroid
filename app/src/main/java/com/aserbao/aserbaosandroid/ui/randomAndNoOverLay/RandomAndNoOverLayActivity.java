package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_and_no_over_lay);
        ButterKnife.bind(this);
    }

    public void btn_click(View view) {
//        mRandomAndNoOverLay.start();
        mHexagonalGridView.start();
    }

    private static final String TAG = "RandomAndNoOverLayActiv";
    @OnClick(R.id.hex_grid_offic_btn)
    public void onViewClicked() {
        Hex hex = new Hex(0, 0, 0);
        cubeSpiral(hex,1);
    }
    public void cubeSpiral(Hex hex,int radius){
        List<Hex> result = new ArrayList<>();
        result.add(hex);
        for (int i = 1; i <= radius; i++) {
            result = cubeRing(result,hex,radius);
        }
        for (int i = 0; i < result.size(); i++) {
            Hex hex1 = result.get(i);
            Log.e(TAG, "cubeSpiral: q = "+ hex1.q  + " r = "+ hex1.r  + " s = " + hex1.s);
        }
    }
    public List<Hex> cubeRing(List<Hex> result ,Hex hex,int radius){
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
