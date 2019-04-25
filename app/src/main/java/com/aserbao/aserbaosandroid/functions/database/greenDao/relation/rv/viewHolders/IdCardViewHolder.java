package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.IdCard;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/18
 * email: this is empty email
 */
public class IdCardViewHolder extends ViewHolder {
    @BindView(R.id.green_dao_id_card_name_tv)
    TextView mGreenDaoIdCardNameTv;
    @BindView(R.id.green_dao_id_card_no_tv)
    TextView mGreenDaoIdCardNoTv;

    public IdCardViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(IdCard idCard){
        if (idCard != null) {
            mGreenDaoIdCardNameTv.setText("持有者名字：" + idCard.getUserName());
            mGreenDaoIdCardNoTv.setText("卡号 ：" + idCard.getIdNo() );
        }
    }
}
