package com.aserbao.aserbaosandroid.ui.buttons.switchButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.AUtils.AUI.popUtil.PopupManager;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.base.beans.VHSeekBarBean;
import com.aserbao.aserbaosandroid.comon.base.interfaces.IBaseRvItemInSeekBarListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SwitchButtonActivity extends BaseRecyclerViewActivity implements IBaseRvItemInSeekBarListener {

    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_button_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("弹框修改",0));
        View view = addLayoutToFrameLayout(R.layout.switch_button_layout, false);
        switchButton = ((SwitchButton) view.findViewById(R.id.switch_button));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                showPopup();
                break;
        }
    }

    public void showPopup(){
        List<BaseRecyclerBean> mPopBRBList = new ArrayList<>();
        mPopBRBList.add(new BaseRecyclerBean(new VHSeekBarBean("margain:",0,50,0,this)));
        new PopupManager(this).showSelectedPop(this,mPopBRBList);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser, int tag) {
        switch (tag){
            case 0:

                switchButton.setThumbMargin(progress,progress,progress,progress);
                break;
        }
    }
}
