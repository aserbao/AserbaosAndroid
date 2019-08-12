package com.aserbao.aserbaosandroid.ui.recyclerView.moveToDeleteRecyclerView.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoveToDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> mList = new ArrayList<>();
    private Context mContext;

    public MoveToDeleteAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 100; i++) {
            mList.add(i);
        }
    }

    public static final int CONTETN = 0;
    public static final int TEST_CLICK_SPAN = 1;

    @Override
    public int getItemViewType(int position) {
        return TEST_CLICK_SPAN;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case CONTETN:
                view = LayoutInflater.from(mContext).inflate(R.layout.normal_item, viewGroup, false);
                return new MoveChildViewHolder(view);
            case TEST_CLICK_SPAN:
                view = LayoutInflater.from(mContext).inflate(R.layout.notice_streak_and_rank_item, viewGroup, false);
                return new TestClickSpanViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MoveChildViewHolder) {
            ((MoveChildViewHolder) viewHolder).normalItem.setText(String.valueOf(mList.get(i)));
            ((MoveChildViewHolder) viewHolder).setPosition(i);
        } else if (viewHolder instanceof TestClickSpanViewHolder) {
            ((TestClickSpanViewHolder) viewHolder).setDataSource(i);
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class MoveChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.normal_item)
        TextView normalItem;
        @BindView(R.id.tv_group_del)
        TextView tvGroupDel;

        public MoveChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvGroupDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    Log.e("test", "onClick: delete" + position);
                }
            });
        }


        private int position;

        public void setPosition(int p) {
            position = p;

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(mContext, "我被点击了怎么办？", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.YELLOW);
                }
            };

            String source = "和 智障侠1号 成为好友" + String.valueOf(position);
            SpannableString dynamicDrawableSpan = new SpannableString(source);
//            dynamicDrawableSpan.setSpan(new StyleSpan(Typeface.BOLD),1,8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            dynamicDrawableSpan.setSpan(clickableSpan, 1, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            normalItem.setText(source);
            normalItem.setText(dynamicDrawableSpan);
            normalItem.setMovementMethod(LinkMovementMethod.getInstance());
        }


    }


    public class TestClickSpanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notice_rv_request_location_head_civ)
        CircleImageView mNoticeRvRequestLocationHeadCiv;
        @BindView(R.id.notice_rv_streak_and_rank_item_name_tv)
        TextView mNoticeRvStreakAndRankItemNameTv;
        @BindView(R.id.notice_rv_streak_and_rank_item_time_tv)
        TextView mNoticeRvStreakAndRankItemTimeTv;
        @BindView(R.id.notice_rv_streak_and_rank_item_ll)
        LinearLayout mNoticeRvStreakAndRankItemLl;
        @BindView(R.id.notice_rv_streak_and_rank_item_content_tv)
        TextView mNoticeRvStreakAndRankItemContentTv;
        @BindView(R.id.notice_rv_streak_and_rank_item_content_bold_tv)
        TextView mNoticeRvStreakAndRankItemContentBoldTv;
        @BindView(R.id.notice_rv_streak_and_rank_item_content_three_tv)
        TextView mNoticeRvStreakAndRankItemContentThreeTv;
        @BindView(R.id.notice_rv_streak_and_rank_item_streak_iv)
        ImageView mNoticeRvStreakAndRankItemStreakIv;
        @BindView(R.id.notice_rv_streak_right_tv)
        TextView mNoticeRvStreakRightTv;
        @BindView(R.id.notice_rv_streak_right_civ)
        CircleImageView mNoticeRvStreakRightCiv;

        public TestClickSpanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDataSource(int position) {

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(mContext, "我被点击了怎么办？", Toast.LENGTH_SHORT).show();
                }
            };

            String string = "DynamicDrawableSpan第二个图片可以点击" + String.valueOf(position);
            SpannableStringBuilder dynamicDrawableSpan = new SpannableStringBuilder(string);
            dynamicDrawableSpan.setSpan(new StyleSpan(Typeface.BOLD),1,8,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            dynamicDrawableSpan.setSpan(clickableSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mNoticeRvStreakAndRankItemContentTv.setText(dynamicDrawableSpan);
            mNoticeRvStreakAndRankItemContentTv.setHighlightColor(Color.BLACK);
            mNoticeRvStreakAndRankItemContentTv.setMovementMethod(LinkMovementMethod.getInstance());

            if (position % 2 == 0){
                mNoticeRvStreakAndRankItemContentTv.setText(string);
            }
            mNoticeRvStreakAndRankItemContentTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "被点击了哈哈哈", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
