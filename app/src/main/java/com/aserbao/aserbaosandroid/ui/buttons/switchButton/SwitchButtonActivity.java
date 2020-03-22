package com.aserbao.aserbaosandroid.ui.buttons.switchButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import com.aserbao.aserbaosandroid.AUtils.AUI.popUtil.PopupManager;
import com.aserbao.aserbaosandroid.R;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.base.beans.VHSeekBarBean;
import com.example.base.base.interfaces.IBaseRvItemInSeekBarListener;

import java.util.ArrayList;
import java.util.List;

public class SwitchButtonActivity extends BaseRecyclerViewActivity implements IBaseRvItemInSeekBarListener {

    private SwitchButton switchButton;

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("弹框修改",0));
        View view = LayoutInflater.from(this).inflate(R.layout.switch_button_layout, null);
        addViewToFrameLayout(view,false, false,false);
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
