package com.aserbao.aserbaosandroid.ui.textViews.shadow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.LinearColorPicker;
import com.aserbao.aserbaosandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextShadowAdapter extends RecyclerView.Adapter<TextShadowAdapter.TextShadowViewHolder> {


    private Context mContext;

    public TextShadowAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TextShadowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_bar_item, viewGroup, false);
        return new TextShadowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextShadowViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public static class TextShadowViewHolder extends RecyclerView.ViewHolder implements SeekBar.OnSeekBarChangeListener {
        @BindView(R.id.tbi_tv_color)
        TextView mTbiTvColor;
        @BindView(R.id.linear_color_picker)
        LinearColorPicker mLinearColorPicker;
        @BindView(R.id.linear_color_text_color_picker)
        LinearColorPicker mLinearColorTextColorPicker;
        @BindView(R.id.tbi_tv_radius)
        TextView mTbiTvRadius;
        @BindView(R.id.text_bar_item_seek_bar_radius)
        SeekBar mTextBarItemSeekBarRadius;
        @BindView(R.id.tbi_tv_dx)
        TextView mTbiTvDx;
        @BindView(R.id.text_bar_item_seek_bar_dx)
        SeekBar mTextBarItemSeekBarDx;
        @BindView(R.id.tbi_tv_dy)
        TextView mTbiTvDy;
        @BindView(R.id.text_bar_item_seek_bar_dy)
        SeekBar mTextBarItemSeekBarDy;
        @BindView(R.id.tbi_tv_alpha)
        TextView mTbiTvAlpha;
        @BindView(R.id.text_bar_item_seek_bar_alpha)
        SeekBar mTextBarItemSeekBarAlpha;
        @BindView(R.id.tbi_tv_elevation)
        TextView mTbiTvElevation;
        @BindView(R.id.text_bar_item_seek_bar_elevation)
        SeekBar mTextBarItemSeekBarElevation;
        @BindView(R.id.tbi_tv_rotate)
        TextView mTbiTvRotate;
        @BindView(R.id.text_bar_item_seek_bar_rotatex)
        SeekBar mTextBarItemSeekBarRotateX;
        @BindView(R.id.text_bar_item_seek_bar_rotatey)
        SeekBar mTextBarItemSeekBarRotateY;

        @BindView(R.id.tv_diaplay)
        TextView mTvDiaplay;

        public int mRadius, mdx, mdy, mShadowColor,mEveation,mRotateX,mRotateY,mTextColor;
        private float mAlpha = 1.0f;
        private List<TextView> mListView = new ArrayList<>();


        public TextShadowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initData();
            mTextBarItemSeekBarRadius.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarDx.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarDy.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarAlpha.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarRotateX.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarRotateY.setOnSeekBarChangeListener(this);
            mTextBarItemSeekBarElevation.setOnSeekBarChangeListener(this);
            mLinearColorPicker.setOnColorSelectListener(new LinearColorPicker.OnColorSelectListener() {
                @Override
                public void onColorSelect(int color, int progress) {
                    mShadowColor = color;

                }
            });
            mLinearColorTextColorPicker.setOnColorSelectListener(new LinearColorPicker.OnColorSelectListener() {
                @Override
                public void onColorSelect(int color, int progress) {
                    mTextColor = color;
                }
            });
        }

        private void initData() {
            mListView.add(mTbiTvColor);
            mListView.add(mTbiTvDx);
            mListView.add(mTbiTvDy);
            mListView.add(mTbiTvElevation);
            mListView.add(mTbiTvAlpha);
            mListView.add(mTbiTvRotate);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.text_bar_item_seek_bar_radius:
                    mRadius = progress;
                    break;
                case R.id.text_bar_item_seek_bar_dx:
                    mdx = progress;
                    break;
                case R.id.text_bar_item_seek_bar_dy:
                    mdy = progress;
                    break;
                case R.id.text_bar_item_seek_bar_alpha:
                    mAlpha = progress;
                    break;
                case R.id.text_bar_item_seek_bar_elevation:
                    break;
                case R.id.text_bar_item_seek_bar_rotatex:
                    mRotateX = progress;
                    break;
                case R.id.text_bar_item_seek_bar_rotatey:
                    mRotateY = progress;
                    break;
            }
            changeText(mShadowColor,mRadius,mdx,mdy,mAlpha,mEveation,mRotateX,mRotateY);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        public void changeText(int color, int radius, int dx, int dy, float alpha,int evelation,int rotateX,int rotateY) {
            for (TextView textView : mListView) {
                textView.setShadowLayer(radius,dx,dy,color);
                textView.setAlpha(alpha);
                textView.setRotationY(rotateX);
                textView.setRotationY(rotateY);
                textView.setTextColor(mTextColor);
            }

            String result = "shadowColor : " + String.valueOf(color) + "\n" +
                    "dx : " + String.valueOf(dx) + "\n" +
                    "dy : " + String.valueOf(dy) + "\n" +
                    "alpha : " + String.valueOf(alpha) + "\n" +
                    "radius : " + String.valueOf(radius) + "\n" +
                    "rotateX : " + String.valueOf(rotateX) + "\n" +
                    "rotateY : " + String.valueOf(rotateY);
                    ;
            mTvDiaplay.setText(result);
        }


    }
}
