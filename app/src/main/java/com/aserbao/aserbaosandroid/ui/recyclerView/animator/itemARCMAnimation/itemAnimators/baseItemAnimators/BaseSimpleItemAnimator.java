package com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators.baseItemAnimators;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-11-07 10:53
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.recyclerView.animator.itemARCMAnimation.itemAnimators
 */
public abstract class BaseSimpleItemAnimator extends SimpleItemAnimator {

    private static final boolean DEBUG = false;
    private static TimeInterpolator sDefaultInterpolator;
    private ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList();
    private ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList();
    private ArrayList<MoveInfo> mPendingMoves = new ArrayList();
    private ArrayList<ChangeInfo> mPendingChanges = new ArrayList();
    ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList();
    ArrayList<ArrayList<MoveInfo>> mMovesList = new ArrayList();
    ArrayList<ArrayList<ChangeInfo>> mChangesList = new ArrayList();
    ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList();
    ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList();
    ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList();
    ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList();


    public void runPendingAnimations() {
        boolean removalsPending = !mPendingRemovals.isEmpty();
        boolean movesPending = !mPendingMoves.isEmpty();
        boolean changesPending = !mPendingChanges.isEmpty();
        boolean additionsPending = !mPendingAdditions.isEmpty();
        if (removalsPending || movesPending || additionsPending || changesPending) {
            Iterator var5 = mPendingRemovals.iterator();

            while(var5.hasNext()) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)var5.next();
                animateRemoveImpl(holder);
            }

            mPendingRemovals.clear();
            Runnable adder;
            if (movesPending) {
                ArrayList moves = new ArrayList();
                moves.addAll(mPendingMoves);
                mMovesList.add(moves);
                mPendingMoves.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator var1 = moves.iterator();

                        while(var1.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo)var1.next();
                            animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }

                        moves.clear();
                        mMovesList.remove(moves);
                    }
                };
                if (removalsPending) {
                    View view = ((MoveInfo)moves.get(0)).holder.itemView;
                    ViewCompat.postOnAnimationDelayed(view, adder, getRemoveDuration());
                } else {
                    adder.run();
                }
            }

            if (changesPending) {
                ArrayList  changes = new ArrayList();
                changes.addAll(mPendingChanges);
                mChangesList.add(changes);
                mPendingChanges.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator var1 = changes.iterator();

                        while(var1.hasNext()) {
                            ChangeInfo change = (ChangeInfo)var1.next();
                            animateChangeImpl(change);
                        }

                        changes.clear();
                        mChangesList.remove(changes);
                    }
                };
                if (removalsPending) {
                    RecyclerView.ViewHolder holder = ((ChangeInfo)changes.get(0)).oldHolder;
                    ViewCompat.postOnAnimationDelayed(holder.itemView, adder, getRemoveDuration());
                } else {
                    adder.run();
                }
            }

            if (additionsPending) {
                ArrayList additions = new ArrayList();
                additions.addAll(mPendingAdditions);
                mAdditionsList.add(additions);
                mPendingAdditions.clear();
                adder = new Runnable() {
                    public void run() {
                        Iterator var1 = additions.iterator();

                        while(var1.hasNext()) {
                            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)var1.next();
                            animateAddImpl(holder);
                        }

                        additions.clear();
                        mAdditionsList.remove(additions);
                    }
                };
                if (!removalsPending && !movesPending && !changesPending) {
                    adder.run();
                } else {
                    long removeDuration = removalsPending ? getRemoveDuration() : 0L;
                    long moveDuration = movesPending ? getMoveDuration() : 0L;
                    long changeDuration = changesPending ? getChangeDuration() : 0L;
                    long totalDelay = removeDuration + Math.max(moveDuration, changeDuration);
                    View view = ((RecyclerView.ViewHolder)additions.get(0)).itemView;
                    ViewCompat.postOnAnimationDelayed(view, adder, totalDelay);
                }
            }

        }
    }

    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        mPendingRemovals.add(holder);
        return true;
    }

    private void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        animateRemoveImpls(holder);
        mRemoveAnimations.add(holder);
    }

    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        holder.itemView.setAlpha(0.0F);
        preAnimateAdd(holder);
        mPendingAdditions.add(holder);
        return true;
    }



    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        View view = holder.itemView;
        fromX += (int)holder.itemView.getTranslationX();
        fromY += (int)holder.itemView.getTranslationY();
        resetAnimation(holder);
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;
        if (deltaX == 0 && deltaY == 0) {
            dispatchMoveFinished(holder);
            return false;
        } else {
            if (deltaX != 0) {
                view.setTranslationX((float)(-deltaX));
            }

            if (deltaY != 0) {
                view.setTranslationY((float)(-deltaY));
            }

            mPendingMoves.add(new MoveInfo(holder, fromX, fromY, toX, toY));
            return true;
        }
    }
    void animateMoveImpl(final RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        final View view = holder.itemView;
        final int deltaX = toX - fromX;
        final int deltaY = toY - fromY;
        if (deltaX != 0) {
            view.animate().translationX(0.0F);
        }

        if (deltaY != 0) {
            view.animate().translationY(0.0F);
        }

        final ViewPropertyAnimator animation = view.animate();
        this.mMoveAnimations.add(holder);
        animation.setDuration(this.getMoveDuration()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                dispatchMoveStarting(holder);
            }

            public void onAnimationCancel(Animator animator) {
                if (deltaX != 0) {
                    view.setTranslationX(0.0F);
                }

                if (deltaY != 0) {
                    view.setTranslationY(0.0F);
                }

            }

            public void onAnimationEnd(Animator animator) {
                animation.setListener((Animator.AnimatorListener)null);
                dispatchMoveFinished(holder);
                mMoveAnimations.remove(holder);
                dispatchFinishedWhenDone();
            }
        }).start();
    }




    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        if (oldHolder == newHolder) {
            return animateMove(oldHolder, fromX, fromY, toX, toY);
        } else {
            float prevTranslationX = oldHolder.itemView.getTranslationX();
            float prevTranslationY = oldHolder.itemView.getTranslationY();
            float prevAlpha = oldHolder.itemView.getAlpha();
            resetAnimation(oldHolder);
            int deltaX = (int)((float)(toX - fromX) - prevTranslationX);
            int deltaY = (int)((float)(toY - fromY) - prevTranslationY);
            oldHolder.itemView.setTranslationX(prevTranslationX);
            oldHolder.itemView.setTranslationY(prevTranslationY);
            oldHolder.itemView.setAlpha(prevAlpha);
            if (newHolder != null) {
                resetAnimation(newHolder);
                newHolder.itemView.setTranslationX((float)(-deltaX));
                newHolder.itemView.setTranslationY((float)(-deltaY));
                newHolder.itemView.setAlpha(0.0F);
            }

            mPendingChanges.add(new ChangeInfo(oldHolder, newHolder, fromX, fromY, toX, toY));
            return true;
        }
    }

    void animateChangeImpl(final ChangeInfo changeInfo) {
        RecyclerView.ViewHolder holder = changeInfo.oldHolder;
        final View view = holder == null ? null : holder.itemView;
        RecyclerView.ViewHolder newHolder = changeInfo.newHolder;
        final View newView = newHolder != null ? newHolder.itemView : null;
        if (view != null) {
            ViewPropertyAnimator oldViewAnimation = view.animate().setDuration(getChangeDuration());
            mChangeAnimations.add(changeInfo.oldHolder);
            oldViewAnimation.translationX((float)(changeInfo.toX - changeInfo.fromX));
            oldViewAnimation.translationY((float)(changeInfo.toY - changeInfo.fromY));
            oldViewAnimation.alpha(0.0F).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    dispatchChangeStarting(changeInfo.oldHolder, true);
                }

                public void onAnimationEnd(Animator animator) {
                    oldViewAnimation.setListener((Animator.AnimatorListener)null);
                    view.setAlpha(1.0F);
                    view.setTranslationX(0.0F);
                    view.setTranslationY(0.0F);
                    dispatchChangeFinished(changeInfo.oldHolder, true);
                    mChangeAnimations.remove(changeInfo.oldHolder);
                    dispatchFinishedWhenDone();
                }
            }).start();
        }

        if (newView != null) {
            ViewPropertyAnimator newViewAnimation = newView.animate();
            mChangeAnimations.add(changeInfo.newHolder);
            newViewAnimation.translationX(0.0F).translationY(0.0F).setDuration(getChangeDuration()).alpha(1.0F).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    dispatchChangeStarting(changeInfo.newHolder, false);
                }

                public void onAnimationEnd(Animator animator) {
                    newViewAnimation.setListener((Animator.AnimatorListener)null);
                    newView.setAlpha(1.0F);
                    newView.setTranslationX(0.0F);
                    newView.setTranslationY(0.0F);
                    dispatchChangeFinished(changeInfo.newHolder, false);
                    mChangeAnimations.remove(changeInfo.newHolder);
                    dispatchFinishedWhenDone();
                }
            }).start();
        }

    }

    private void endChangeAnimation(List<ChangeInfo> infoList, RecyclerView.ViewHolder item) {
        for(int i = infoList.size() - 1; i >= 0; --i) {
            ChangeInfo changeInfo = (ChangeInfo)infoList.get(i);
            if (endChangeAnimationIfNecessary(changeInfo, item) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                infoList.remove(changeInfo);
            }
        }

    }

    private void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
        if (changeInfo.oldHolder != null) {
            endChangeAnimationIfNecessary(changeInfo, changeInfo.oldHolder);
        }

        if (changeInfo.newHolder != null) {
            endChangeAnimationIfNecessary(changeInfo, changeInfo.newHolder);
        }

    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder item) {
        boolean oldItem = false;
        if (changeInfo.newHolder == item) {
            changeInfo.newHolder = null;
        } else {
            if (changeInfo.oldHolder != item) {
                return false;
            }

            changeInfo.oldHolder = null;
            oldItem = true;
        }

        item.itemView.setAlpha(1.0F);
        item.itemView.setTranslationX(0.0F);
        item.itemView.setTranslationY(0.0F);
        dispatchChangeFinished(item, oldItem);
        return true;
    }

    public void endAnimation(RecyclerView.ViewHolder item) {
        View view = item.itemView;
        view.animate().cancel();

        int i;
        for(i = mPendingMoves.size() - 1; i >= 0; --i) {
            MoveInfo moveInfo = (MoveInfo)mPendingMoves.get(i);
            if (moveInfo.holder == item) {
                view.setTranslationY(0.0F);
                view.setTranslationX(0.0F);
                dispatchMoveFinished(item);
                mPendingMoves.remove(i);
            }
        }

        endChangeAnimation(mPendingChanges, item);
        if (mPendingRemovals.remove(item)) {
            view.setAlpha(1.0F);
            dispatchRemoveFinished(item);
        }

        if (mPendingAdditions.remove(item)) {
            view.setAlpha(1.0F);
            dispatchAddFinished(item);
        }

        ArrayList moves;
        for(i = mChangesList.size() - 1; i >= 0; --i) {
            moves = (ArrayList)mChangesList.get(i);
            endChangeAnimation(moves, item);
            if (moves.isEmpty()) {
                mChangesList.remove(i);
            }
        }

        for(i = mMovesList.size() - 1; i >= 0; --i) {
            moves = (ArrayList)mMovesList.get(i);

            for(int j = moves.size() - 1; j >= 0; --j) {
                MoveInfo moveInfo = (MoveInfo)moves.get(j);
                if (moveInfo.holder == item) {
                    view.setTranslationY(0.0F);
                    view.setTranslationX(0.0F);
                    dispatchMoveFinished(item);
                    moves.remove(j);
                    if (moves.isEmpty()) {
                        mMovesList.remove(i);
                    }
                    break;
                }
            }
        }

        for(i = mAdditionsList.size() - 1; i >= 0; --i) {
            moves = (ArrayList)mAdditionsList.get(i);
            if (moves.remove(item)) {
                view.setAlpha(1.0F);
                dispatchAddFinished(item);
                if (moves.isEmpty()) {
                    mAdditionsList.remove(i);
                }
            }
        }

        if (mRemoveAnimations.remove(item)) {
        }

        if (mAddAnimations.remove(item)) {
        }

        if (mChangeAnimations.remove(item)) {
        }

        if (mMoveAnimations.remove(item)) {
        }

        dispatchFinishedWhenDone();
    }

    private void resetAnimation(RecyclerView.ViewHolder holder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = (new ValueAnimator()).getInterpolator();
        }

        holder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(holder);
    }

    public boolean isRunning() {
        return !mPendingAdditions.isEmpty() || !mPendingChanges.isEmpty() || !mPendingMoves.isEmpty() || !mPendingRemovals.isEmpty() || !mMoveAnimations.isEmpty() || !mRemoveAnimations.isEmpty() || !mAddAnimations.isEmpty() || !mChangeAnimations.isEmpty() || !mMovesList.isEmpty() || !mAdditionsList.isEmpty() || !mChangesList.isEmpty();
    }

    void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }

    }

    public void endAnimations() {
        int count = mPendingMoves.size();

        int listCount;
        for(listCount = count - 1; listCount >= 0; --listCount) {
            MoveInfo item = (MoveInfo)mPendingMoves.get(listCount);
            View view = item.holder.itemView;
            view.setTranslationY(0.0F);
            view.setTranslationX(0.0F);
            dispatchMoveFinished(item.holder);
            mPendingMoves.remove(listCount);
        }

        count = mPendingRemovals.size();

        RecyclerView.ViewHolder item;
        for(listCount = count - 1; listCount >= 0; --listCount) {
            item = (RecyclerView.ViewHolder)mPendingRemovals.get(listCount);
            dispatchRemoveFinished(item);
            mPendingRemovals.remove(listCount);
        }

        count = mPendingAdditions.size();

        for(listCount = count - 1; listCount >= 0; --listCount) {
            item = (RecyclerView.ViewHolder)mPendingAdditions.get(listCount);
            item.itemView.setAlpha(1.0F);
            dispatchAddFinished(item);
            mPendingAdditions.remove(listCount);
        }

        count = mPendingChanges.size();

        for(listCount = count - 1; listCount >= 0; --listCount) {
            endChangeAnimationIfNecessary((ChangeInfo)mPendingChanges.get(listCount));
        }

        mPendingChanges.clear();
        if (isRunning()) {
            listCount = mMovesList.size();

            int j;
            int i;
            ArrayList changes;
            for(i = listCount - 1; i >= 0; --i) {
                changes = (ArrayList)mMovesList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    MoveInfo moveInfo = (MoveInfo)changes.get(j);
                    RecyclerView.ViewHolder item0 = moveInfo.holder;
                    View view = item0.itemView;
                    view.setTranslationY(0.0F);
                    view.setTranslationX(0.0F);
                    dispatchMoveFinished(moveInfo.holder);
                    changes.remove(j);
                    if (changes.isEmpty()) {
                        mMovesList.remove(changes);
                    }
                }
            }

            listCount = mAdditionsList.size();

            for(i = listCount - 1; i >= 0; --i) {
                changes = (ArrayList)mAdditionsList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    RecyclerView.ViewHolder item1 = (RecyclerView.ViewHolder)changes.get(j);
                    View view = item1.itemView;
                    view.setAlpha(1.0F);
                    dispatchAddFinished(item1);
                    changes.remove(j);
                    if (changes.isEmpty()) {
                        mAdditionsList.remove(changes);
                    }
                }
            }

            listCount = mChangesList.size();

            for(i = listCount - 1; i >= 0; --i) {
                changes = (ArrayList)mChangesList.get(i);
                count = changes.size();

                for(j = count - 1; j >= 0; --j) {
                    endChangeAnimationIfNecessary((ChangeInfo)changes.get(j));
                    if (changes.isEmpty()) {
                        mChangesList.remove(changes);
                    }
                }
            }

            cancelAll(mRemoveAnimations);
            cancelAll(mMoveAnimations);
            cancelAll(mAddAnimations);
            cancelAll(mChangeAnimations);
            dispatchAnimationsFinished();
        }
    }

    void cancelAll(List<RecyclerView.ViewHolder> viewHolders) {
        for(int i = viewHolders.size() - 1; i >= 0; --i) {
            ((RecyclerView.ViewHolder)viewHolders.get(i)).itemView.animate().cancel();
        }

    }

    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        return !payloads.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }

    private static class ChangeInfo {
        public RecyclerView.ViewHolder oldHolder;
        public RecyclerView.ViewHolder newHolder;
        public int fromX;
        public int fromY;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder) {
            oldHolder = oldHolder;
            newHolder = newHolder;
        }

        ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
            this(oldHolder, newHolder);
            fromX = fromX;
            fromY = fromY;
            toX = toX;
            toY = toY;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + oldHolder + ", newHolder=" + newHolder + ", fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
        }
    }

    private static class MoveInfo {
        public RecyclerView.ViewHolder holder;
        public int fromX;
        public int fromY;
        public int toX;
        public int toY;

        MoveInfo(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
            this.holder = holder;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }
    }

    //----------aserbao-------
    protected abstract void preAnimateAdd(RecyclerView.ViewHolder holder);
    protected  abstract void animateRemoveImpls(final RecyclerView.ViewHolder holder);
    protected  abstract void animateAddImpl(final RecyclerView.ViewHolder holder);
    protected long getRemoveDelay(final RecyclerView.ViewHolder holder) {
        return Math.abs(holder.getOldPosition() * getRemoveDuration() / 4);
    }
    protected long getAddDelay(final RecyclerView.ViewHolder holder) {
        return Math.abs(holder.getAdapterPosition() * getAddDuration() / 4);
    }

    protected class DefaultRemoveListener implements ViewPropertyAnimatorListener {

        RecyclerView.ViewHolder mViewHolder;

        public DefaultRemoveListener(final RecyclerView.ViewHolder holder) {
            mViewHolder = holder;
        }

        @Override public void onAnimationStart(View view) {
            dispatchRemoveStarting(mViewHolder);
        }

        @Override public void onAnimationCancel(View view) {
            viewReset(view);
        }
        @Override public void onAnimationEnd(View view) {
            viewReset(view);
            dispatchRemoveFinished(mViewHolder);
            mRemoveAnimations.remove(mViewHolder);
            dispatchFinishedWhenDone();
        }
    }

    protected class DefaultAddListener implements ViewPropertyAnimatorListener {

        RecyclerView.ViewHolder mViewHolder;

        public DefaultAddListener(final RecyclerView.ViewHolder holder) {
            mViewHolder = holder;
        }

        @Override public void onAnimationStart(View view) {
            dispatchAddStarting(mViewHolder);
        }

        @Override public void onAnimationCancel(View view) {
            viewReset(view);
        }
        @Override public void onAnimationEnd(View view) {
            viewReset(view);
            dispatchAddFinished(mViewHolder);
            mRemoveAnimations.remove(mViewHolder);
            dispatchFinishedWhenDone();
        }
    }

    private void viewReset(View view) {
        ViewCompat.setAlpha(view, 1);
        ViewCompat.setScaleY(view, 1);
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setTranslationY(view, 0);
        ViewCompat.setTranslationX(view, 0);
        ViewCompat.setRotation(view, 0);
        ViewCompat.setRotationY(view, 0);
        ViewCompat.setRotationX(view, 0);
        ViewCompat.setPivotY(view, view.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(view, view.getMeasuredWidth() / 2);
        ViewCompat.animate(view).setInterpolator(null).setStartDelay(0);
    }


}
