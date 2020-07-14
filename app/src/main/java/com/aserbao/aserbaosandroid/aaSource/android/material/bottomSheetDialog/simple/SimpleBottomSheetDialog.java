package com.aserbao.aserbaosandroid.aaSource.android.material.bottomSheetDialog.simple;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.aserbao.aserbaosandroid.R;
import com.example.base.interfaces.IClickBackListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SimpleBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;

    IClickBackListener iClickBackListener;

    public SimpleBottomSheetDialog(IClickBackListener iClickBackListener) {
        this.iClickBackListener = iClickBackListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_bottom_sheet, null);
        dialog.setContentView(view);
        view.findViewById(R.id.one_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickBackListener.clickItem(0,v);
            }
        });
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }


    @Override
    public void onStart()
    {
        super.onStart();
        //默认全屏展开
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void doclick(View v)
    {
        //点击任意布局关闭
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
