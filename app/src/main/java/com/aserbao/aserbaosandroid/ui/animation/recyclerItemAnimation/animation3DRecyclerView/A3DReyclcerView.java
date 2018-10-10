package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.animation3DRecyclerView;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.AnimManager;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.GalleryItemDecoration;
import com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.galleryrecycler.GalleryRecyclerView;

/**
 * 功能:
 * author aserbao
 * date : On 2018/10/9
 * email: 1142803753@qq.com
 */
public class A3DReyclcerView extends RecyclerView {
    private static final String TAG = "A3DReyclcerView";
    private A3DReyclcerView mA3DRecyclerView;
    private boolean mHasWindowFocus = false;
    private boolean mIsNeedScrollOneItemEachTime = true;//是否每次只能滑动一个

    public A3DReyclcerView(@NonNull Context context) {
        this(context,null);
    }

    public A3DReyclcerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public A3DReyclcerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mA3DRecyclerView = this;
        attachToRecyclerHelper();
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        this.mHasWindowFocus = hasWindowFocus;

        if(getAdapter().getItemCount() <= 0){
            return;
        }
        initScrollListener();
    }

    private void initScrollListener() {
        AScrollerListener mAScrollerListener = new AScrollerListener();
        mA3DRecyclerView.addOnScrollListener(mAScrollerListener);
    }

    private void attachToRecyclerHelper() {
        if(mIsNeedScrollOneItemEachTime) {
            new PagerSnapHelper().attachToRecyclerView(mA3DRecyclerView);
        }else {
            new LinearSnapHelper().attachToRecyclerView(mA3DRecyclerView);
        }
    }



    public class AScrollerListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(!mA3DRecyclerView.getHasWindowFocus()){
                return;
            }
            if(mA3DRecyclerView.getOrientation() == LinearLayoutManager.HORIZONTAL){
                onHoritiontalScroll(mA3DRecyclerView,dx);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }

    private int mPosition = 0;

    // 使偏移量为左边距 + 左边Item的可视部分宽度
    private int mConsumeX = 0;
    private int mConsumeY = 0;
    // 滑动方向
    private int slideDirct = SLIDE_RIGHT;
    private static final int SLIDE_LEFT = 1;    // 左滑
    private static final int SLIDE_RIGHT = 2;   // 右滑
    private static final int
            SLIDE_TOP = 3;     // 上滑
    private static final int SLIDE_BOTTOM = 4;  // 下滑
    private int mLastHorizontalScrollOffset;


    private final Camera mCamera = new Camera();
    /**
     * 水平滑动
     *
     * @param recyclerView
     * @param dx
     */
    private void onHoritiontalScroll(final RecyclerView recyclerView, final int dx) {

        mConsumeX += dx;

        if (dx > 0) {
            // 右滑
            slideDirct = SLIDE_RIGHT;
        } else {
            // 左滑
            slideDirct = SLIDE_LEFT;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemWidth的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager linearLayoutManager = mA3DRecyclerView.getLinearLayoutManager();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                float shouldConsumeX = linearLayoutManager.findViewByPosition(firstVisibleItemPosition).getWidth();

                int computeHorizontalScrollRange = mA3DRecyclerView.computeHorizontalScrollRange();
                int computeHorizontalScrollExtent = mA3DRecyclerView.computeHorizontalScrollExtent();
                int computeHorizontalScrollOffset = mA3DRecyclerView.computeHorizontalScrollOffset();

//                float percent = (float) (mConsumeX - computeHorizontalScrollOffset) / shouldConsumeX;
                float percent = (float) (computeHorizontalScrollOffset - firstVisibleItemPosition * shouldConsumeX) / shouldConsumeX;
                /*Log.d(TAG, "mConsumeX=" + mConsumeX + "; dx=" + dx + "  percent = " + percent + " \ncomputeHorizontalScrollRange = "
                        + computeHorizontalScrollRange + " computeHorizontalScrollExtent = " + computeHorizontalScrollExtent +  " computeHorizontalScrollOffset = " + computeHorizontalScrollOffset +
                        " cuurposition = "  + firstVisibleItemPosition);*/

//                View leftView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition - 1);
                View currentView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition );
                View rightView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition + 1);

                /*if (currentView != null) {
                    currentView.setRotationY(360 * percent);
                }
                if (rightView != null) {
                    rightView.setRotationY(360 * (1 - percent));
                }*/

                final Camera camera = mCamera;
                camera.save();
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    float z = -(10 * percent);
                    camera.setLocation(0, 0, -8);

                    Log.e(TAG, "run:  z = " + z);
                }*/
                float z = (1000 * percent);

                camera.translate(0, z, 0);    // camera - 沿y轴正方向平移100像素

                Matrix matrix = new Matrix();
                camera.getMatrix(matrix);
                matrix.postTranslate(0,z);    // matrix - 沿y轴正方向平移100像素
                Log.e(TAG, "run:  z = " + z);
                camera.restore();
            }
        });
    }



    /**
     * 获取位置
     *
     * @param mConsumeX      实际消耗距离
     * @param shouldConsumeX 理论消耗距离
     * @return
     */
    private int getPosition(int mConsumeX, int shouldConsumeX) {
        float offset = (float) mConsumeX / (float) shouldConsumeX;
        int position = Math.round(offset);        // 四舍五入获取位置
        mPosition = position;
        return position;
    }

    public int getPosition() {
        return mPosition;
    }


    public boolean getHasWindowFocus(){
        return mHasWindowFocus;
    }

    public int getOrientation(){
        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
            } else {
                return ((LinearLayoutManager) getLayoutManager()).getOrientation();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }
    public LinearLayoutManager getLinearLayoutManager() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            if (getLayoutManager() instanceof GridLayoutManager) {
                throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");

            } else {
                return (LinearLayoutManager) getLayoutManager();
            }
        } else {
            throw new RuntimeException("请设置LayoutManager为LinearLayoutManager");
        }
    }
}
