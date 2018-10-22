package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.database.greenDao.relation.beans.CreditCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/22
 * email: 1142803753@qq.com
 */
public class CreditCardViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.green_dao_credit_card_name_tv)
    TextView mGreenDaoCreditCardNameTv;
    @BindView(R.id.green_dao_credit_card_id_tv)
    TextView mGreenDaoCreditCardIdTv;
    @BindView(R.id.green_dao_credit_card_which_bank_tv)
    TextView mGreenDaoCreditCardWhichBankTv;

    public CreditCardViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setDataSource(CreditCard mCreditCardList) {
        mGreenDaoCreditCardNameTv.setText("名字：" + mCreditCardList.getUserName() + " 用户ID: " + mCreditCardList.getUserId());
        mGreenDaoCreditCardIdTv.setText("卡Id：" + mCreditCardList.getId());
        mGreenDaoCreditCardWhichBankTv.setText("银行：" + mCreditCardList.getWhichBank() + " 卡号： "+ mCreditCardList.getCardNum());
    }
}
