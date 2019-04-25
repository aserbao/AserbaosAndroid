package com.aserbao.aserbaosandroid.ui.animation.moveAnimation.angleShot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.utils.DisplayUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/11
 * email: this is empty email
 */
public class MoveView extends View {

    public ArrayList<Shot> mShotList = new ArrayList<>();
    private Context mContext;

    public MoveView(Context context) {
        this(context,null);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mShotList != null) {
            for (int i = 0; i < mShotList.size(); i++) {
                mShotList.get(i).draw(canvas);
            }
        }
    }

    public void setmShotList(ArrayList<Shot> shotList){
        mShotList = shotList;
    }

}
