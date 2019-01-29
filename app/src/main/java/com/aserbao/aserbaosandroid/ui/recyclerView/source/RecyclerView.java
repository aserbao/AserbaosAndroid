//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.aserbao.aserbaosandroid.ui.recyclerView.source;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.recyclerview.R.dimen;
import android.support.v7.recyclerview.R.styleable;
import android.support.v7.widget.AdapterHelper.UpdateOp;
import android.support.v7.widget.ChildHelper.Callback;
import android.support.v7.widget.GapWorker.LayoutPrefetchRegistryImpl;
import android.support.v7.widget.ViewInfoStore.ProcessCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
    static final String TAG = "RecyclerView";
    static final boolean DEBUG = false;
    static final boolean VERBOSE_TRACING = false;
    private static final int[] NESTED_SCROLLING_ATTRS = new int[]{16843830};
    private static final int[] CLIP_TO_PADDING_ATTR = new int[]{16842987};
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    static final boolean POST_UPDATES_ON_ANIMATION;
    static final boolean ALLOW_THREAD_GAP_WORK;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    static final boolean DISPATCH_TEMP_DETACH = false;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    static final int DEFAULT_ORIENTATION = 1;
    public static final int NO_POSITION = -1;
    public static final long NO_ID = -1L;
    public static final int INVALID_TYPE = -1;
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final int MAX_SCROLL_DURATION = 2000;
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private final RecyclerView.RecyclerViewDataObserver mObserver;
    final RecyclerView.Recycler mRecycler;
    private RecyclerView.SavedState mPendingSavedState;
    AdapterHelper mAdapterHelper;
    ChildHelper mChildHelper;
    final ViewInfoStore mViewInfoStore;
    boolean mClipToPadding;
    final Runnable mUpdateChildViewsRunnable;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    RecyclerView.Adapter mAdapter;
    @VisibleForTesting
    RecyclerView.LayoutManager mLayout;
    RecyclerView.RecyclerListener mRecyclerListener;
    final ArrayList<RecyclerView.ItemDecoration> mItemDecorations;
    private final ArrayList<RecyclerView.OnItemTouchListener> mOnItemTouchListeners;
    private RecyclerView.OnItemTouchListener mActiveOnItemTouchListener;
    boolean mIsAttached;
    boolean mHasFixedSize;
    boolean mEnableFastScroller;
    @VisibleForTesting
    boolean mFirstLayoutComplete;
    private int mInterceptRequestLayoutDepth;
    boolean mLayoutWasDefered;
    boolean mLayoutFrozen;
    private boolean mIgnoreMotionEventTillDown;
    private int mEatenAccessibilityChangeFlags;
    boolean mAdapterUpdateDuringMeasure;
    private final AccessibilityManager mAccessibilityManager;
    private List<RecyclerView.OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mLayoutOrScrollCounter;
    private int mDispatchScrollCounter;
    @NonNull
    private RecyclerView.EdgeEffectFactory mEdgeEffectFactory;
    private EdgeEffect mLeftGlow;
    private EdgeEffect mTopGlow;
    private EdgeEffect mRightGlow;
    private EdgeEffect mBottomGlow;
    RecyclerView.ItemAnimator mItemAnimator;
    private static final int INVALID_POINTER = -1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final long FOREVER_NS = 9223372036854775807L;
    private int mScrollState;
    private int mScrollPointerId;
    private VelocityTracker mVelocityTracker;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mLastTouchX;
    private int mLastTouchY;
    private int mTouchSlop;
    private RecyclerView.OnFlingListener mOnFlingListener;
    private final int mMinFlingVelocity;
    private final int mMaxFlingVelocity;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private boolean mPreserveFocusAfterLayout;
    final RecyclerView.ViewFlinger mViewFlinger;
    GapWorker mGapWorker;
    LayoutPrefetchRegistryImpl mPrefetchRegistry;
    final RecyclerView.State mState;
    private RecyclerView.OnScrollListener mScrollListener;
    private List<RecyclerView.OnScrollListener> mScrollListeners;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private RecyclerView.ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    boolean mPostedAnimatorRunner;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private final int[] mMinMaxLayoutPositions;
    private NestedScrollingChildHelper mScrollingChildHelper;
    private final int[] mScrollOffset;
    final int[] mScrollConsumed;
    private final int[] mNestedOffsets;
    final int[] mScrollStepConsumed;
    @VisibleForTesting
    final List<RecyclerView.ViewHolder> mPendingAccessibilityImportanceChange;
    private Runnable mItemAnimatorRunner;
    static final Interpolator sQuinticInterpolator;
    private final ProcessCallback mViewInfoProcessCallback;

    public RecyclerView(@NonNull Context context) {
        this(context, (AttributeSet)null);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mObserver = new RecyclerView.RecyclerViewDataObserver();
        this.mRecycler = new RecyclerView.Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            public void run() {
                if (RecyclerView.this.mFirstLayoutComplete && !RecyclerView.this.isLayoutRequested()) {
                    if (!RecyclerView.this.mIsAttached) {
                        RecyclerView.this.requestLayout();
                    } else if (RecyclerView.this.mLayoutFrozen) {
                        RecyclerView.this.mLayoutWasDefered = true;
                    } else {
                        RecyclerView.this.consumePendingUpdateOperations();
                    }
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new RecyclerView.EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = 1.4E-45F;
        this.mScaledVerticalScrollFactor = 1.4E-45F;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new RecyclerView.ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new LayoutPrefetchRegistryImpl() : null;
        this.mState = new RecyclerView.State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new RecyclerView.ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mScrollStepConsumed = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() {
            public void run() {
                if (RecyclerView.this.mItemAnimator != null) {
                    RecyclerView.this.mItemAnimator.runPendingAnimations();
                }

                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ProcessCallback() {
            public void processDisappeared(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo info, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo postInfo) {
                RecyclerView.this.mRecycler.unscrapView(viewHolder);
                RecyclerView.this.animateDisappearance(viewHolder, info, postInfo);
            }

            public void processAppeared(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo preInfo, RecyclerView.ItemAnimator.ItemHolderInfo info) {
                RecyclerView.this.animateAppearance(viewHolder, preInfo, info);
            }

            public void processPersistent(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postInfo) {
                viewHolder.setIsRecyclable(false);
                if (RecyclerView.this.mDataSetHasChangedAfterLayout) {
                    if (RecyclerView.this.mItemAnimator.animateChange(viewHolder, viewHolder, preInfo, postInfo)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                } else if (RecyclerView.this.mItemAnimator.animatePersistence(viewHolder, preInfo, postInfo)) {
                    RecyclerView.this.postAnimationRunner();
                }

            }

            public void unused(RecyclerView.ViewHolder viewHolder) {
                RecyclerView.this.mLayout.removeAndRecycleView(viewHolder.itemView, RecyclerView.this.mRecycler);
            }
        };
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, CLIP_TO_PADDING_ATTR, defStyle, 0);
            this.mClipToPadding = a.getBoolean(0, true);
            a.recycle();
        } else {
            this.mClipToPadding = true;
        }

        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        ViewConfiguration vc = ViewConfiguration.get(context);
        this.mTouchSlop = vc.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(vc, context);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(vc, context);
        this.mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
        this.setWillNotDraw(this.getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        this.initAutofill();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }

        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        boolean nestedScrollingEnabled = true;
        if (attrs != null) {
            int defStyleRes = 0;
            TypedArray a = context.obtainStyledAttributes(attrs, styleable.RecyclerView, defStyle, defStyleRes);
            String layoutManagerName = a.getString(styleable.RecyclerView_layoutManager);
            int descendantFocusability = a.getInt(styleable.RecyclerView_android_descendantFocusability, -1);
            if (descendantFocusability == -1) {
                this.setDescendantFocusability(262144);
            }

            this.mEnableFastScroller = a.getBoolean(styleable.RecyclerView_fastScrollEnabled, false);
            if (this.mEnableFastScroller) {
                StateListDrawable verticalThumbDrawable = (StateListDrawable)a.getDrawable(styleable.RecyclerView_fastScrollVerticalThumbDrawable);
                Drawable verticalTrackDrawable = a.getDrawable(styleable.RecyclerView_fastScrollVerticalTrackDrawable);
                StateListDrawable horizontalThumbDrawable = (StateListDrawable)a.getDrawable(styleable.RecyclerView_fastScrollHorizontalThumbDrawable);
                Drawable horizontalTrackDrawable = a.getDrawable(styleable.RecyclerView_fastScrollHorizontalTrackDrawable);
                this.initFastScroller(verticalThumbDrawable, verticalTrackDrawable, horizontalThumbDrawable, horizontalTrackDrawable);
            }

            a.recycle();
            this.createLayoutManager(context, layoutManagerName, attrs, defStyle, defStyleRes);
            if (VERSION.SDK_INT >= 21) {
                a = context.obtainStyledAttributes(attrs, NESTED_SCROLLING_ATTRS, defStyle, defStyleRes);
                nestedScrollingEnabled = a.getBoolean(0, true);
                a.recycle();
            }
        } else {
            this.setDescendantFocusability(262144);
        }

        this.setNestedScrollingEnabled(nestedScrollingEnabled);
    }

    String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + this.getContext();
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            ViewCompat.setImportantForAutofill(this, 8);
        }

    }

    @Nullable
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void setAccessibilityDelegateCompat(@Nullable RecyclerViewAccessibilityDelegate accessibilityDelegate) {
        this.mAccessibilityDelegate = accessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, this.mAccessibilityDelegate);
    }

    private void createLayoutManager(Context context, String className, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (className != null) {
            className = className.trim();
            if (!className.isEmpty()) {
                className = this.getFullClassName(context, className);

                try {
                    ClassLoader classLoader;
                    if (this.isInEditMode()) {
                        classLoader = this.getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }

                    Class<? extends RecyclerView.LayoutManager> layoutManagerClass = classLoader.loadClass(className).asSubclass(RecyclerView.LayoutManager.class);
                    Object[] constructorArgs = null;

                    Constructor constructor;
                    try {
                        constructor = layoutManagerClass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        constructorArgs = new Object[]{context, attrs, defStyleAttr, defStyleRes};
                    } catch (NoSuchMethodException var13) {
                        try {
                            constructor = layoutManagerClass.getConstructor();
                        } catch (NoSuchMethodException var12) {
                            var12.initCause(var13);
                            throw new IllegalStateException(attrs.getPositionDescription() + ": Error creating LayoutManager " + className, var12);
                        }
                    }

                    constructor.setAccessible(true);
                    this.setLayoutManager((RecyclerView.LayoutManager)constructor.newInstance(constructorArgs));
                } catch (ClassNotFoundException var14) {
                    throw new IllegalStateException(attrs.getPositionDescription() + ": Unable to find LayoutManager " + className, var14);
                } catch (InvocationTargetException var15) {
                    throw new IllegalStateException(attrs.getPositionDescription() + ": Could not instantiate the LayoutManager: " + className, var15);
                } catch (InstantiationException var16) {
                    throw new IllegalStateException(attrs.getPositionDescription() + ": Could not instantiate the LayoutManager: " + className, var16);
                } catch (IllegalAccessException var17) {
                    throw new IllegalStateException(attrs.getPositionDescription() + ": Cannot access non-public constructor " + className, var17);
                } catch (ClassCastException var18) {
                    throw new IllegalStateException(attrs.getPositionDescription() + ": Class is not a LayoutManager " + className, var18);
                }
            }
        }

    }

    private String getFullClassName(Context context, String className) {
        if (className.charAt(0) == '.') {
            return context.getPackageName() + className;
        } else {
            return className.contains(".") ? className : RecyclerView.class.getPackage().getName() + '.' + className;
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new Callback() {
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            public void addView(View child, int index) {
                RecyclerView.this.addView(child, index);
                RecyclerView.this.dispatchChildAttached(child);
            }

            public int indexOfChild(View view) {
                return RecyclerView.this.indexOfChild(view);
            }

            public void removeViewAt(int index) {
                View child = RecyclerView.this.getChildAt(index);
                if (child != null) {
                    RecyclerView.this.dispatchChildDetached(child);
                    child.clearAnimation();
                }

                RecyclerView.this.removeViewAt(index);
            }

            public View getChildAt(int offset) {
                return RecyclerView.this.getChildAt(offset);
            }

            public void removeAllViews() {
                int count = this.getChildCount();

                for(int i = 0; i < count; ++i) {
                    View child = this.getChildAt(i);
                    RecyclerView.this.dispatchChildDetached(child);
                    child.clearAnimation();
                }

                RecyclerView.this.removeAllViews();
            }

            public RecyclerView.ViewHolder getChildViewHolder(View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public void attachViewToParent(View child, int index, android.view.ViewGroup.LayoutParams layoutParams) {
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    if (!vh.isTmpDetached() && !vh.shouldIgnore()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + vh + RecyclerView.this.exceptionLabel());
                    }

                    vh.clearTmpDetachFlag();
                }

                RecyclerView.this.attachViewToParent(child, index, layoutParams);
            }

            public void detachViewFromParent(int offset) {
                View view = this.getChildAt(offset);
                if (view != null) {
                    RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(view);
                    if (vh != null) {
                        if (vh.isTmpDetached() && !vh.shouldIgnore()) {
                            throw new IllegalArgumentException("called detach on an already detached child " + vh + RecyclerView.this.exceptionLabel());
                        }

                        vh.addFlags(256);
                    }
                }

                RecyclerView.this.detachViewFromParent(offset);
            }

            public void onEnteredHiddenState(View child) {
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    vh.onEnteredHiddenState(RecyclerView.this);
                }

            }

            public void onLeftHiddenState(View child) {
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null) {
                    vh.onLeftHiddenState(RecyclerView.this);
                }

            }
        });
    }

    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new android.support.v7.widget.AdapterHelper.Callback() {
            public RecyclerView.ViewHolder findViewHolder(int position) {
                RecyclerView.ViewHolder vh = RecyclerView.this.findViewHolderForPosition(position, true);
                if (vh == null) {
                    return null;
                } else {
                    return RecyclerView.this.mChildHelper.isHidden(vh.itemView) ? null : vh;
                }
            }

            public void offsetPositionsForRemovingInvisible(int start, int count) {
                RecyclerView.this.offsetPositionRecordsForRemove(start, count, true);
                RecyclerView.this.mItemsAddedOrRemoved = true;
                RecyclerView.this.mState.mDeletedInvisibleItemCountSincePreviousLayout += count;
            }

            public void offsetPositionsForRemovingLaidOutOrNewView(int positionStart, int itemCount) {
                RecyclerView.this.offsetPositionRecordsForRemove(positionStart, itemCount, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            public void markViewHoldersUpdated(int positionStart, int itemCount, Object payload) {
                RecyclerView.this.viewRangeUpdate(positionStart, itemCount, payload);
                RecyclerView.this.mItemsChanged = true;
            }

            public void onDispatchFirstPass(UpdateOp op) {
                this.dispatchUpdate(op);
            }

            void dispatchUpdate(UpdateOp op) {
                switch(op.cmd) {
                case 1:
                    RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, op.positionStart, op.itemCount);
                    break;
                case 2:
                    RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, op.positionStart, op.itemCount);
                case 3:
                case 5:
                case 6:
                case 7:
                default:
                    break;
                case 4:
                    RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, op.positionStart, op.itemCount, op.payload);
                    break;
                case 8:
                    RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, op.positionStart, op.itemCount, 1);
                }

            }

            public void onDispatchSecondPass(UpdateOp op) {
                this.dispatchUpdate(op);
            }

            public void offsetPositionsForAdd(int positionStart, int itemCount) {
                RecyclerView.this.offsetPositionRecordsForInsert(positionStart, itemCount);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }

            public void offsetPositionsForMove(int from, int to) {
                RecyclerView.this.offsetPositionRecordsForMove(from, to);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
        });
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        this.mHasFixedSize = hasFixedSize;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    public void setClipToPadding(boolean clipToPadding) {
        if (clipToPadding != this.mClipToPadding) {
            this.invalidateGlows();
        }

        this.mClipToPadding = clipToPadding;
        super.setClipToPadding(clipToPadding);
        if (this.mFirstLayoutComplete) {
            this.requestLayout();
        }

    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public void setScrollingTouchSlop(int slopConstant) {
        ViewConfiguration vc = ViewConfiguration.get(this.getContext());
        switch(slopConstant) {
        case 1:
            this.mTouchSlop = vc.getScaledPagingTouchSlop();
            break;
        default:
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + slopConstant + "; using default value");
        case 0:
            this.mTouchSlop = vc.getScaledTouchSlop();
        }

    }

    public void swapAdapter(@Nullable RecyclerView.Adapter adapter, boolean removeAndRecycleExistingViews) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, true, removeAndRecycleExistingViews);
        this.processDataSetCompletelyChanged(true);
        this.requestLayout();
    }

    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, false, true);
        this.processDataSetCompletelyChanged(false);
        this.requestLayout();
    }

    void removeAndRecycleViews() {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }

        if (this.mLayout != null) {
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }

        this.mRecycler.clear();
    }

    private void setAdapterInternal(@Nullable RecyclerView.Adapter adapter, boolean compatibleWithPrevious, boolean removeAndRecycleViews) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }

        if (!compatibleWithPrevious || removeAndRecycleViews) {
            this.removeAndRecycleViews();
        }

        this.mAdapterHelper.reset();
        RecyclerView.Adapter oldAdapter = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }

        if (this.mLayout != null) {
            this.mLayout.onAdapterChanged(oldAdapter, this.mAdapter);
        }

        this.mRecycler.onAdapterChanged(oldAdapter, this.mAdapter, compatibleWithPrevious);
        this.mState.mStructureChanged = true;
    }

    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return this.mAdapter;
    }

    public void setRecyclerListener(@Nullable RecyclerView.RecyclerListener listener) {
        this.mRecyclerListener = listener;
    }

    public int getBaseline() {
        return this.mLayout != null ? this.mLayout.getBaseline() : super.getBaseline();
    }

    public void addOnChildAttachStateChangeListener(@NonNull RecyclerView.OnChildAttachStateChangeListener listener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }

        this.mOnChildAttachStateListeners.add(listener);
    }

    public void removeOnChildAttachStateChangeListener(@NonNull RecyclerView.OnChildAttachStateChangeListener listener) {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.remove(listener);
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }

    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layout) {
        if (layout != this.mLayout) {
            this.stopScroll();
            if (this.mLayout != null) {
                if (this.mItemAnimator != null) {
                    this.mItemAnimator.endAnimations();
                }

                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }

                this.mLayout.setRecyclerView((RecyclerView)null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }

            this.mChildHelper.removeAllViewsUnfiltered();
            this.mLayout = layout;
            if (layout != null) {
                if (layout.mRecyclerView != null) {
                    throw new IllegalArgumentException("LayoutManager " + layout + " is already attached to a RecyclerView:" + layout.mRecyclerView.exceptionLabel());
                }

                this.mLayout.setRecyclerView(this);
                if (this.mIsAttached) {
                    this.mLayout.dispatchAttachedToWindow(this);
                }
            }

            this.mRecycler.updateViewCacheSize();
            this.requestLayout();
        }
    }

    public void setOnFlingListener(@Nullable RecyclerView.OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    @Nullable
    public RecyclerView.OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    protected Parcelable onSaveInstanceState() {
        RecyclerView.SavedState state = new RecyclerView.SavedState(super.onSaveInstanceState());
        if (this.mPendingSavedState != null) {
            state.copyFrom(this.mPendingSavedState);
        } else if (this.mLayout != null) {
            state.mLayoutState = this.mLayout.onSaveInstanceState();
        } else {
            state.mLayoutState = null;
        }

        return state;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof RecyclerView.SavedState)) {
            super.onRestoreInstanceState(state);
        } else {
            this.mPendingSavedState = (RecyclerView.SavedState)state;
            super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
            if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
                this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
            }

        }
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        this.dispatchFreezeSelfOnly(container);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        this.dispatchThawSelfOnly(container);
    }

    private void addAnimatingView(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean alreadyParented = view.getParent() == this;
        this.mRecycler.unscrapView(this.getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!alreadyParented) {
            this.mChildHelper.addView(view, true);
        } else {
            this.mChildHelper.hide(view);
        }

    }

    boolean removeAnimatingView(View view) {
        this.startInterceptRequestLayout();
        boolean removed = this.mChildHelper.removeViewIfHidden(view);
        if (removed) {
            RecyclerView.ViewHolder viewHolder = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(viewHolder);
            this.mRecycler.recycleViewHolderInternal(viewHolder);
        }

        this.stopInterceptRequestLayout(!removed);
        return removed;
    }

    @Nullable
    public RecyclerView.LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    @NonNull
    public RecyclerView.RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public void setRecycledViewPool(@Nullable RecyclerView.RecycledViewPool pool) {
        this.mRecycler.setRecycledViewPool(pool);
    }

    public void setViewCacheExtension(@Nullable RecyclerView.ViewCacheExtension extension) {
        this.mRecycler.setViewCacheExtension(extension);
    }

    public void setItemViewCacheSize(int size) {
        this.mRecycler.setViewCacheSize(size);
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    void setScrollState(int state) {
        if (state != this.mScrollState) {
            this.mScrollState = state;
            if (state != 2) {
                this.stopScrollersInternal();
            }

            this.dispatchOnScrollStateChanged(state);
        }
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration decor, int index) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }

        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(false);
        }

        if (index < 0) {
            this.mItemDecorations.add(decor);
        } else {
            this.mItemDecorations.add(index, decor);
        }

        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration decor) {
        this.addItemDecoration(decor, -1);
    }

    @NonNull
    public RecyclerView.ItemDecoration getItemDecorationAt(int index) {
        int size = this.getItemDecorationCount();
        if (index >= 0 && index < size) {
            return (RecyclerView.ItemDecoration)this.mItemDecorations.get(index);
        } else {
            throw new IndexOutOfBoundsException(index + " is an invalid index for size " + size);
        }
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public void removeItemDecorationAt(int index) {
        int size = this.getItemDecorationCount();
        if (index >= 0 && index < size) {
            this.removeItemDecoration(this.getItemDecorationAt(index));
        } else {
            throw new IndexOutOfBoundsException(index + " is an invalid index for size " + size);
        }
    }

    public void removeItemDecoration(@NonNull RecyclerView.ItemDecoration decor) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }

        this.mItemDecorations.remove(decor);
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(this.getOverScrollMode() == 2);
        }

        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }

    public void setChildDrawingOrderCallback(@Nullable RecyclerView.ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = childDrawingOrderCallback;
            this.setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
        }
    }

    /** @deprecated */
    @Deprecated
    public void setOnScrollListener(@Nullable RecyclerView.OnScrollListener listener) {
        this.mScrollListener = listener;
    }

    public void addOnScrollListener(@NonNull RecyclerView.OnScrollListener listener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }

        this.mScrollListeners.add(listener);
    }

    public void removeOnScrollListener(@NonNull RecyclerView.OnScrollListener listener) {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.remove(listener);
        }

    }

    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }

    }

    public void scrollToPosition(int position) {
        if (!this.mLayoutFrozen) {
            this.stopScroll();
            if (this.mLayout == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                this.mLayout.scrollToPosition(position);
                this.awakenScrollBars();
            }
        }
    }

    void jumpToPositionForSmoothScroller(int position) {
        if (this.mLayout != null) {
            this.mLayout.scrollToPosition(position);
            this.awakenScrollBars();
        }
    }

    public void smoothScrollToPosition(int position) {
        if (!this.mLayoutFrozen) {
            if (this.mLayout == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                this.mLayout.smoothScrollToPosition(this, this.mState, position);
            }
        }
    }

    public void scrollTo(int x, int y) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollBy(int x, int y) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            boolean canScrollHorizontal = this.mLayout.canScrollHorizontally();
            boolean canScrollVertical = this.mLayout.canScrollVertically();
            if (canScrollHorizontal || canScrollVertical) {
                this.scrollByInternal(canScrollHorizontal ? x : 0, canScrollVertical ? y : 0, (MotionEvent)null);
            }

        }
    }

    void scrollStep(int dx, int dy, @Nullable int[] consumed) {
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        TraceCompat.beginSection("RV Scroll");
        this.fillRemainingScrollValues(this.mState);
        int consumedX = 0;
        int consumedY = 0;
        if (dx != 0) {
            consumedX = this.mLayout.scrollHorizontallyBy(dx, this.mRecycler, this.mState);
        }

        if (dy != 0) {
            consumedY = this.mLayout.scrollVerticallyBy(dy, this.mRecycler, this.mState);
        }

        TraceCompat.endSection();
        this.repositionShadowingViews();
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        if (consumed != null) {
            consumed[0] = consumedX;
            consumed[1] = consumedY;
        }

    }

    void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
            if (this.mAdapterHelper.hasPendingUpdates()) {
                if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                    TraceCompat.beginSection("RV PartialInvalidate");
                    this.startInterceptRequestLayout();
                    this.onEnterLayoutOrScroll();
                    this.mAdapterHelper.preProcess();
                    if (!this.mLayoutWasDefered) {
                        if (this.hasUpdatedView()) {
                            this.dispatchLayout();
                        } else {
                            this.mAdapterHelper.consumePostponedUpdates();
                        }
                    }

                    this.stopInterceptRequestLayout(true);
                    this.onExitLayoutOrScroll();
                    TraceCompat.endSection();
                } else if (this.mAdapterHelper.hasPendingUpdates()) {
                    TraceCompat.beginSection("RV FullInvalidate");
                    this.dispatchLayout();
                    TraceCompat.endSection();
                }

            }
        } else {
            TraceCompat.beginSection("RV FullInvalidate");
            this.dispatchLayout();
            TraceCompat.endSection();
        }
    }

    private boolean hasUpdatedView() {
        int childCount = this.mChildHelper.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (holder != null && !holder.shouldIgnore() && holder.isUpdated()) {
                return true;
            }
        }

        return false;
    }

    boolean scrollByInternal(int x, int y, MotionEvent ev) {
        int unconsumedX = 0;
        int unconsumedY = 0;
        int consumedX = 0;
        int consumedY = 0;
        this.consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            this.scrollStep(x, y, this.mScrollStepConsumed);
            consumedX = this.mScrollStepConsumed[0];
            consumedY = this.mScrollStepConsumed[1];
            unconsumedX = x - consumedX;
            unconsumedY = y - consumedY;
        }

        if (!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }

        if (this.dispatchNestedScroll(consumedX, consumedY, unconsumedX, unconsumedY, this.mScrollOffset, 0)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (ev != null) {
                ev.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
            }

            this.mNestedOffsets[0] += this.mScrollOffset[0];
            this.mNestedOffsets[1] += this.mScrollOffset[1];
        } else if (this.getOverScrollMode() != 2) {
            if (ev != null && !MotionEventCompat.isFromSource(ev, 8194)) {
                this.pullGlows(ev.getX(), (float)unconsumedX, ev.getY(), (float)unconsumedY);
            }

            this.considerReleasingGlowsOnScroll(x, y);
        }

        if (consumedX != 0 || consumedY != 0) {
            this.dispatchOnScrolled(consumedX, consumedY);
        }

        if (!this.awakenScrollBars()) {
            this.invalidate();
        }

        return consumedX != 0 || consumedY != 0;
    }

    public int computeHorizontalScrollOffset() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollOffset(this.mState) : 0;
        }
    }

    public int computeHorizontalScrollExtent() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollExtent(this.mState) : 0;
        }
    }

    public int computeHorizontalScrollRange() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollHorizontally() ? this.mLayout.computeHorizontalScrollRange(this.mState) : 0;
        }
    }

    public int computeVerticalScrollOffset() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollOffset(this.mState) : 0;
        }
    }

    public int computeVerticalScrollExtent() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollExtent(this.mState) : 0;
        }
    }

    public int computeVerticalScrollRange() {
        if (this.mLayout == null) {
            return 0;
        } else {
            return this.mLayout.canScrollVertically() ? this.mLayout.computeVerticalScrollRange(this.mState) : 0;
        }
    }

    void startInterceptRequestLayout() {
        ++this.mInterceptRequestLayoutDepth;
        if (this.mInterceptRequestLayoutDepth == 1 && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }

    }

    void stopInterceptRequestLayout(boolean performLayoutChildren) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }

        if (!performLayoutChildren && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }

        if (this.mInterceptRequestLayoutDepth == 1) {
            if (performLayoutChildren && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }

            if (!this.mLayoutFrozen) {
                this.mLayoutWasDefered = false;
            }
        }

        --this.mInterceptRequestLayoutDepth;
    }

    public void setLayoutFrozen(boolean frozen) {
        if (frozen != this.mLayoutFrozen) {
            this.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (!frozen) {
                this.mLayoutFrozen = false;
                if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                    this.requestLayout();
                }

                this.mLayoutWasDefered = false;
            } else {
                long now = SystemClock.uptimeMillis();
                MotionEvent cancelEvent = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
                this.onTouchEvent(cancelEvent);
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                this.stopScroll();
            }
        }

    }

    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }

    public void smoothScrollBy(@Px int dx, @Px int dy) {
        this.smoothScrollBy(dx, dy, (Interpolator)null);
    }

    public void smoothScrollBy(@Px int dx, @Px int dy, @Nullable Interpolator interpolator) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            if (!this.mLayout.canScrollHorizontally()) {
                dx = 0;
            }

            if (!this.mLayout.canScrollVertically()) {
                dy = 0;
            }

            if (dx != 0 || dy != 0) {
                this.mViewFlinger.smoothScrollBy(dx, dy, interpolator);
            }

        }
    }

    public boolean fling(int velocityX, int velocityY) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.mLayoutFrozen) {
            return false;
        } else {
            boolean canScrollHorizontal = this.mLayout.canScrollHorizontally();
            boolean canScrollVertical = this.mLayout.canScrollVertically();
            if (!canScrollHorizontal || Math.abs(velocityX) < this.mMinFlingVelocity) {
                velocityX = 0;
            }

            if (!canScrollVertical || Math.abs(velocityY) < this.mMinFlingVelocity) {
                velocityY = 0;
            }

            if (velocityX == 0 && velocityY == 0) {
                return false;
            } else {
                if (!this.dispatchNestedPreFling((float)velocityX, (float)velocityY)) {
                    boolean canScroll = canScrollHorizontal || canScrollVertical;
                    this.dispatchNestedFling((float)velocityX, (float)velocityY, canScroll);
                    if (this.mOnFlingListener != null && this.mOnFlingListener.onFling(velocityX, velocityY)) {
                        return true;
                    }

                    if (canScroll) {
                        int nestedScrollAxis = 0;
                        if (canScrollHorizontal) {
                            nestedScrollAxis |= 1;
                        }

                        if (canScrollVertical) {
                            nestedScrollAxis |= 2;
                        }

                        this.startNestedScroll(nestedScrollAxis, 1);
                        velocityX = Math.max(-this.mMaxFlingVelocity, Math.min(velocityX, this.mMaxFlingVelocity));
                        velocityY = Math.max(-this.mMaxFlingVelocity, Math.min(velocityY, this.mMaxFlingVelocity));
                        this.mViewFlinger.fling(velocityX, velocityY);
                        return true;
                    }
                }

                return false;
            }
        }
    }

    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        if (this.mLayout != null) {
            this.mLayout.stopSmoothScroller();
        }

    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    private void pullGlows(float x, float overscrollX, float y, float overscrollY) {
        boolean invalidate = false;
        if (overscrollX < 0.0F) {
            this.ensureLeftGlow();
            EdgeEffectCompat.onPull(this.mLeftGlow, -overscrollX / (float)this.getWidth(), 1.0F - y / (float)this.getHeight());
            invalidate = true;
        } else if (overscrollX > 0.0F) {
            this.ensureRightGlow();
            EdgeEffectCompat.onPull(this.mRightGlow, overscrollX / (float)this.getWidth(), y / (float)this.getHeight());
            invalidate = true;
        }

        if (overscrollY < 0.0F) {
            this.ensureTopGlow();
            EdgeEffectCompat.onPull(this.mTopGlow, -overscrollY / (float)this.getHeight(), x / (float)this.getWidth());
            invalidate = true;
        } else if (overscrollY > 0.0F) {
            this.ensureBottomGlow();
            EdgeEffectCompat.onPull(this.mBottomGlow, overscrollY / (float)this.getHeight(), 1.0F - x / (float)this.getWidth());
            invalidate = true;
        }

        if (invalidate || overscrollX != 0.0F || overscrollY != 0.0F) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    private void releaseGlows() {
        boolean needsInvalidate = false;
        if (this.mLeftGlow != null) {
            this.mLeftGlow.onRelease();
            needsInvalidate = this.mLeftGlow.isFinished();
        }

        if (this.mTopGlow != null) {
            this.mTopGlow.onRelease();
            needsInvalidate |= this.mTopGlow.isFinished();
        }

        if (this.mRightGlow != null) {
            this.mRightGlow.onRelease();
            needsInvalidate |= this.mRightGlow.isFinished();
        }

        if (this.mBottomGlow != null) {
            this.mBottomGlow.onRelease();
            needsInvalidate |= this.mBottomGlow.isFinished();
        }

        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    void considerReleasingGlowsOnScroll(int dx, int dy) {
        boolean needsInvalidate = false;
        if (this.mLeftGlow != null && !this.mLeftGlow.isFinished() && dx > 0) {
            this.mLeftGlow.onRelease();
            needsInvalidate = this.mLeftGlow.isFinished();
        }

        if (this.mRightGlow != null && !this.mRightGlow.isFinished() && dx < 0) {
            this.mRightGlow.onRelease();
            needsInvalidate |= this.mRightGlow.isFinished();
        }

        if (this.mTopGlow != null && !this.mTopGlow.isFinished() && dy > 0) {
            this.mTopGlow.onRelease();
            needsInvalidate |= this.mTopGlow.isFinished();
        }

        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished() && dy < 0) {
            this.mBottomGlow.onRelease();
            needsInvalidate |= this.mBottomGlow.isFinished();
        }

        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    void absorbGlows(int velocityX, int velocityY) {
        if (velocityX < 0) {
            this.ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-velocityX);
        } else if (velocityX > 0) {
            this.ensureRightGlow();
            this.mRightGlow.onAbsorb(velocityX);
        }

        if (velocityY < 0) {
            this.ensureTopGlow();
            this.mTopGlow.onAbsorb(-velocityY);
        } else if (velocityY > 0) {
            this.ensureBottomGlow();
            this.mBottomGlow.onAbsorb(velocityY);
        }

        if (velocityX != 0 || velocityY != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            this.mLeftGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
            if (this.mClipToPadding) {
                this.mLeftGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            } else {
                this.mLeftGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
            }

        }
    }

    void ensureRightGlow() {
        if (this.mRightGlow == null) {
            this.mRightGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
            if (this.mClipToPadding) {
                this.mRightGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            } else {
                this.mRightGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
            }

        }
    }

    void ensureTopGlow() {
        if (this.mTopGlow == null) {
            this.mTopGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
            if (this.mClipToPadding) {
                this.mTopGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            } else {
                this.mTopGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
            }

        }
    }

    void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            this.mBottomGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
            if (this.mClipToPadding) {
                this.mBottomGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            } else {
                this.mBottomGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
            }

        }
    }

    void invalidateGlows() {
        this.mLeftGlow = this.mRightGlow = this.mTopGlow = this.mBottomGlow = null;
    }

    public void setEdgeEffectFactory(@NonNull RecyclerView.EdgeEffectFactory edgeEffectFactory) {
        Preconditions.checkNotNull(edgeEffectFactory);
        this.mEdgeEffectFactory = edgeEffectFactory;
        this.invalidateGlows();
    }

    @NonNull
    public RecyclerView.EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public View focusSearch(View focused, int direction) {
        View result = this.mLayout.onInterceptFocusSearch(focused, direction);
        if (result != null) {
            return result;
        } else {
            boolean canRunFocusFailure = this.mAdapter != null && this.mLayout != null && !this.isComputingLayout() && !this.mLayoutFrozen;
            FocusFinder ff = FocusFinder.getInstance();
            if (canRunFocusFailure && (direction == 2 || direction == 1)) {
                boolean needsFocusFailureLayout = false;
                if (this.mLayout.canScrollVertically()) {
                    int absDir = direction == 2 ? 130 : 33;
                    View found = ff.findNextFocus(this, focused, absDir);
                    needsFocusFailureLayout = found == null;
                    if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                        direction = absDir;
                    }
                }

                if (!needsFocusFailureLayout && this.mLayout.canScrollHorizontally()) {
                    boolean rtl = this.mLayout.getLayoutDirection() == 1;
                    int absDir = direction == 2 ^ rtl ? 66 : 17;
                    View found = ff.findNextFocus(this, focused, absDir);
                    needsFocusFailureLayout = found == null;
                    if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                        direction = absDir;
                    }
                }

                if (needsFocusFailureLayout) {
                    this.consumePendingUpdateOperations();
                    View focusedItemView = this.findContainingItemView(focused);
                    if (focusedItemView == null) {
                        return null;
                    }

                    this.startInterceptRequestLayout();
                    this.mLayout.onFocusSearchFailed(focused, direction, this.mRecycler, this.mState);
                    this.stopInterceptRequestLayout(false);
                }

                result = ff.findNextFocus(this, focused, direction);
            } else {
                result = ff.findNextFocus(this, focused, direction);
                if (result == null && canRunFocusFailure) {
                    this.consumePendingUpdateOperations();
                    View focusedItemView = this.findContainingItemView(focused);
                    if (focusedItemView == null) {
                        return null;
                    }

                    this.startInterceptRequestLayout();
                    result = this.mLayout.onFocusSearchFailed(focused, direction, this.mRecycler, this.mState);
                    this.stopInterceptRequestLayout(false);
                }
            }

            if (result != null && !result.hasFocusable()) {
                if (this.getFocusedChild() == null) {
                    return super.focusSearch(focused, direction);
                } else {
                    this.requestChildOnScreen(result, (View)null);
                    return focused;
                }
            } else {
                return this.isPreferredNextFocus(focused, result, direction) ? result : super.focusSearch(focused, direction);
            }
        }
    }

    private boolean isPreferredNextFocus(View focused, View next, int direction) {
        if (next != null && next != this) {
            if (this.findContainingItemView(next) == null) {
                return false;
            } else if (focused == null) {
                return true;
            } else if (this.findContainingItemView(focused) == null) {
                return true;
            } else {
                this.mTempRect.set(0, 0, focused.getWidth(), focused.getHeight());
                this.mTempRect2.set(0, 0, next.getWidth(), next.getHeight());
                this.offsetDescendantRectToMyCoords(focused, this.mTempRect);
                this.offsetDescendantRectToMyCoords(next, this.mTempRect2);
                int rtl = this.mLayout.getLayoutDirection() == 1 ? -1 : 1;
                int rightness = 0;
                if ((this.mTempRect.left < this.mTempRect2.left || this.mTempRect.right <= this.mTempRect2.left) && this.mTempRect.right < this.mTempRect2.right) {
                    rightness = 1;
                } else if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
                    rightness = -1;
                }

                int downness = 0;
                if ((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
                    downness = 1;
                } else if ((this.mTempRect.bottom > this.mTempRect2.bottom || this.mTempRect.top >= this.mTempRect2.bottom) && this.mTempRect.top > this.mTempRect2.top) {
                    downness = -1;
                }

                switch(direction) {
                case 1:
                    return downness < 0 || downness == 0 && rightness * rtl <= 0;
                case 2:
                    return downness > 0 || downness == 0 && rightness * rtl >= 0;
                case 17:
                    return rightness < 0;
                case 33:
                    return downness < 0;
                case 66:
                    return rightness > 0;
                case 130:
                    return downness > 0;
                default:
                    throw new IllegalArgumentException("Invalid direction: " + direction + this.exceptionLabel());
                }
            }
        } else {
            return false;
        }
    }

    public void requestChildFocus(View child, View focused) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, child, focused) && focused != null) {
            this.requestChildOnScreen(child, focused);
        }

        super.requestChildFocus(child, focused);
    }

    private void requestChildOnScreen(@NonNull View child, @Nullable View focused) {
        View rectView = focused != null ? focused : child;
        this.mTempRect.set(0, 0, rectView.getWidth(), rectView.getHeight());
        android.view.ViewGroup.LayoutParams focusedLayoutParams = rectView.getLayoutParams();
        if (focusedLayoutParams instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)focusedLayoutParams;
            if (!lp.mInsetsDirty) {
                Rect insets = lp.mDecorInsets;
                this.mTempRect.left -= insets.left;
                this.mTempRect.right += insets.right;
                this.mTempRect.top -= insets.top;
                this.mTempRect.bottom += insets.bottom;
            }
        }

        if (focused != null) {
            this.offsetDescendantRectToMyCoords(focused, this.mTempRect);
            this.offsetRectIntoDescendantCoords(child, this.mTempRect);
        }

        this.mLayout.requestChildRectangleOnScreen(this, child, this.mTempRect, !this.mFirstLayoutComplete, focused == null);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return this.mLayout.requestChildRectangleOnScreen(this, child, rect, immediate);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (this.mLayout == null || !this.mLayout.onAddFocusables(this, views, direction, focusableMode)) {
            super.addFocusables(views, direction, focusableMode);
        }

    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return this.isComputingLayout() ? false : super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !this.isLayoutRequested();
        if (this.mLayout != null) {
            this.mLayout.dispatchAttachedToWindow(this);
        }

        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            this.mGapWorker = (GapWorker)GapWorker.sGapWorker.get();
            if (this.mGapWorker == null) {
                this.mGapWorker = new GapWorker();
                Display display = ViewCompat.getDisplay(this);
                float refreshRate = 60.0F;
                if (!this.isInEditMode() && display != null) {
                    float displayRefreshRate = display.getRefreshRate();
                    if (displayRefreshRate >= 30.0F) {
                        refreshRate = displayRefreshRate;
                    }
                }

                this.mGapWorker.mFrameIntervalNs = (long)(1.0E9F / refreshRate);
                GapWorker.sGapWorker.set(this.mGapWorker);
            }

            this.mGapWorker.add(this);
        }

    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }

        this.stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
        }

        this.mPendingAccessibilityImportanceChange.clear();
        this.removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        if (ALLOW_THREAD_GAP_WORK && this.mGapWorker != null) {
            this.mGapWorker.remove(this);
            this.mGapWorker = null;
        }

    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    void assertInLayoutOrScroll(String message) {
        if (!this.isComputingLayout()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + this.exceptionLabel());
            } else {
                throw new IllegalStateException(message + this.exceptionLabel());
            }
        }
    }

    void assertNotInLayoutOrScroll(String message) {
        if (this.isComputingLayout()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + this.exceptionLabel());
            } else {
                throw new IllegalStateException(message);
            }
        } else {
            if (this.mDispatchScrollCounter > 0) {
                Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + this.exceptionLabel()));
            }

        }
    }

    public void addOnItemTouchListener(@NonNull RecyclerView.OnItemTouchListener listener) {
        this.mOnItemTouchListeners.add(listener);
    }

    public void removeOnItemTouchListener(@NonNull RecyclerView.OnItemTouchListener listener) {
        this.mOnItemTouchListeners.remove(listener);
        if (this.mActiveOnItemTouchListener == listener) {
            this.mActiveOnItemTouchListener = null;
        }

    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent e) {
        int action = e.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }

        int listenerCount = this.mOnItemTouchListeners.size();

        for(int i = 0; i < listenerCount; ++i) {
            RecyclerView.OnItemTouchListener listener = (RecyclerView.OnItemTouchListener)this.mOnItemTouchListeners.get(i);
            if (listener.onInterceptTouchEvent(this, e) && action != 3) {
                this.mActiveOnItemTouchListener = listener;
                return true;
            }
        }

        return false;
    }

    private boolean dispatchOnItemTouch(MotionEvent e) {
        int action = e.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action != 0) {
                this.mActiveOnItemTouchListener.onTouchEvent(this, e);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }

                return true;
            }

            this.mActiveOnItemTouchListener = null;
        }

        if (action != 0) {
            int listenerCount = this.mOnItemTouchListeners.size();

            for(int i = 0; i < listenerCount; ++i) {
                RecyclerView.OnItemTouchListener listener = (RecyclerView.OnItemTouchListener)this.mOnItemTouchListeners.get(i);
                if (listener.onInterceptTouchEvent(this, e)) {
                    this.mActiveOnItemTouchListener = listener;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.mLayoutFrozen) {
            return false;
        } else if (this.dispatchOnItemTouchIntercept(e)) {
            this.cancelTouch();
            return true;
        } else if (this.mLayout == null) {
            return false;
        } else {
            boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }

            this.mVelocityTracker.addMovement(e);
            int action = e.getActionMasked();
            int actionIndex = e.getActionIndex();
            switch(action) {
            case 0:
                if (this.mIgnoreMotionEventTillDown) {
                    this.mIgnoreMotionEventTillDown = false;
                }

                this.mScrollPointerId = e.getPointerId(0);
                this.mInitialTouchX = this.mLastTouchX = (int)(e.getX() + 0.5F);
                this.mInitialTouchY = this.mLastTouchY = (int)(e.getY() + 0.5F);
                if (this.mScrollState == 2) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    this.setScrollState(1);
                }

                this.mNestedOffsets[0] = this.mNestedOffsets[1] = 0;
                int nestedScrollAxis = 0;
                if (canScrollHorizontally) {
                    nestedScrollAxis |= 1;
                }

                if (canScrollVertically) {
                    nestedScrollAxis |= 2;
                }

                this.startNestedScroll(nestedScrollAxis, 0);
                break;
            case 1:
                this.mVelocityTracker.clear();
                this.stopNestedScroll(0);
                break;
            case 2:
                int index = e.findPointerIndex(this.mScrollPointerId);
                if (index < 0) {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }

                int x = (int)(e.getX(index) + 0.5F);
                int y = (int)(e.getY(index) + 0.5F);
                if (this.mScrollState != 1) {
                    int dx = x - this.mInitialTouchX;
                    int dy = y - this.mInitialTouchY;
                    boolean startScroll = false;
                    if (canScrollHorizontally && Math.abs(dx) > this.mTouchSlop) {
                        this.mLastTouchX = x;
                        startScroll = true;
                    }

                    if (canScrollVertically && Math.abs(dy) > this.mTouchSlop) {
                        this.mLastTouchY = y;
                        startScroll = true;
                    }

                    if (startScroll) {
                        this.setScrollState(1);
                    }
                }
                break;
            case 3:
                this.cancelTouch();
            case 4:
            default:
                break;
            case 5:
                this.mScrollPointerId = e.getPointerId(actionIndex);
                this.mInitialTouchX = this.mLastTouchX = (int)(e.getX(actionIndex) + 0.5F);
                this.mInitialTouchY = this.mLastTouchY = (int)(e.getY(actionIndex) + 0.5F);
                break;
            case 6:
                this.onPointerUp(e);
            }

            return this.mScrollState == 1;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        int listenerCount = this.mOnItemTouchListeners.size();

        for(int i = 0; i < listenerCount; ++i) {
            RecyclerView.OnItemTouchListener listener = (RecyclerView.OnItemTouchListener)this.mOnItemTouchListeners.get(i);
            listener.onRequestDisallowInterceptTouchEvent(disallowIntercept);
        }

        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean onTouchEvent(MotionEvent e) {
        if (!this.mLayoutFrozen && !this.mIgnoreMotionEventTillDown) {
            if (this.dispatchOnItemTouch(e)) {
                this.cancelTouch();
                return true;
            } else if (this.mLayout == null) {
                return false;
            } else {
                boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
                boolean canScrollVertically = this.mLayout.canScrollVertically();
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }

                boolean eventAddedToVelocityTracker = false;
                MotionEvent vtev = MotionEvent.obtain(e);
                int action = e.getActionMasked();
                int actionIndex = e.getActionIndex();
                if (action == 0) {
                    this.mNestedOffsets[0] = this.mNestedOffsets[1] = 0;
                }

                vtev.offsetLocation((float)this.mNestedOffsets[0], (float)this.mNestedOffsets[1]);
                int nestedScrollAxis;
                switch(action) {
                case 0:
                    this.mScrollPointerId = e.getPointerId(0);
                    this.mInitialTouchX = this.mLastTouchX = (int)(e.getX() + 0.5F);
                    this.mInitialTouchY = this.mLastTouchY = (int)(e.getY() + 0.5F);
                    nestedScrollAxis = 0;
                    if (canScrollHorizontally) {
                        nestedScrollAxis |= 1;
                    }

                    if (canScrollVertically) {
                        nestedScrollAxis |= 2;
                    }

                    this.startNestedScroll(nestedScrollAxis, 0);
                    break;
                case 1:
                    this.mVelocityTracker.addMovement(vtev);
                    eventAddedToVelocityTracker = true;
                    this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                    float xvel = canScrollHorizontally ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0F;
                    float yvel = canScrollVertically ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0F;
                    if (xvel == 0.0F && yvel == 0.0F || !this.fling((int)xvel, (int)yvel)) {
                        this.setScrollState(0);
                    }

                    this.resetTouch();
                    break;
                case 2:
                    nestedScrollAxis = e.findPointerIndex(this.mScrollPointerId);
                    if (nestedScrollAxis < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }

                    int x = (int)(e.getX(nestedScrollAxis) + 0.5F);
                    int y = (int)(e.getY(nestedScrollAxis) + 0.5F);
                    int dx = this.mLastTouchX - x;
                    int dy = this.mLastTouchY - y;
                    if (this.dispatchNestedPreScroll(dx, dy, this.mScrollConsumed, this.mScrollOffset, 0)) {
                        dx -= this.mScrollConsumed[0];
                        dy -= this.mScrollConsumed[1];
                        vtev.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
                        this.mNestedOffsets[0] += this.mScrollOffset[0];
                        this.mNestedOffsets[1] += this.mScrollOffset[1];
                    }

                    if (this.mScrollState != 1) {
                        boolean startScroll = false;
                        if (canScrollHorizontally && Math.abs(dx) > this.mTouchSlop) {
                            if (dx > 0) {
                                dx -= this.mTouchSlop;
                            } else {
                                dx += this.mTouchSlop;
                            }

                            startScroll = true;
                        }

                        if (canScrollVertically && Math.abs(dy) > this.mTouchSlop) {
                            if (dy > 0) {
                                dy -= this.mTouchSlop;
                            } else {
                                dy += this.mTouchSlop;
                            }

                            startScroll = true;
                        }

                        if (startScroll) {
                            this.setScrollState(1);
                        }
                    }

                    if (this.mScrollState == 1) {
                        this.mLastTouchX = x - this.mScrollOffset[0];
                        this.mLastTouchY = y - this.mScrollOffset[1];
                        if (this.scrollByInternal(canScrollHorizontally ? dx : 0, canScrollVertically ? dy : 0, vtev)) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        }

                        if (this.mGapWorker != null && (dx != 0 || dy != 0)) {
                            this.mGapWorker.postFromTraversal(this, dx, dy);
                        }
                    }
                    break;
                case 3:
                    this.cancelTouch();
                case 4:
                default:
                    break;
                case 5:
                    this.mScrollPointerId = e.getPointerId(actionIndex);
                    this.mInitialTouchX = this.mLastTouchX = (int)(e.getX(actionIndex) + 0.5F);
                    this.mInitialTouchY = this.mLastTouchY = (int)(e.getY(actionIndex) + 0.5F);
                    break;
                case 6:
                    this.onPointerUp(e);
                }

                if (!eventAddedToVelocityTracker) {
                    this.mVelocityTracker.addMovement(vtev);
                }

                vtev.recycle();
                return true;
            }
        } else {
            return false;
        }
    }

    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }

        this.stopNestedScroll(0);
        this.releaseGlows();
    }

    private void cancelTouch() {
        this.resetTouch();
        this.setScrollState(0);
    }

    private void onPointerUp(MotionEvent e) {
        int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == this.mScrollPointerId) {
            int newIndex = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = e.getPointerId(newIndex);
            this.mInitialTouchX = this.mLastTouchX = (int)(e.getX(newIndex) + 0.5F);
            this.mInitialTouchY = this.mLastTouchY = (int)(e.getY(newIndex) + 0.5F);
        }

    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (this.mLayout == null) {
            return false;
        } else if (this.mLayoutFrozen) {
            return false;
        } else {
            if (event.getAction() == 8) {
                float vScroll;
                float hScroll;
                if ((event.getSource() & 2) != 0) {
                    if (this.mLayout.canScrollVertically()) {
                        vScroll = -event.getAxisValue(9);
                    } else {
                        vScroll = 0.0F;
                    }

                    if (this.mLayout.canScrollHorizontally()) {
                        hScroll = event.getAxisValue(10);
                    } else {
                        hScroll = 0.0F;
                    }
                } else if ((event.getSource() & 4194304) != 0) {
                    float axisScroll = event.getAxisValue(26);
                    if (this.mLayout.canScrollVertically()) {
                        vScroll = -axisScroll;
                        hScroll = 0.0F;
                    } else if (this.mLayout.canScrollHorizontally()) {
                        vScroll = 0.0F;
                        hScroll = axisScroll;
                    } else {
                        vScroll = 0.0F;
                        hScroll = 0.0F;
                    }
                } else {
                    vScroll = 0.0F;
                    hScroll = 0.0F;
                }

                if (vScroll != 0.0F || hScroll != 0.0F) {
                    this.scrollByInternal((int)(hScroll * this.mScaledHorizontalScrollFactor), (int)(vScroll * this.mScaledVerticalScrollFactor), event);
                }
            }

            return false;
        }
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        if (this.mLayout == null) {
            this.defaultOnMeasure(widthSpec, heightSpec);
        } else {
            if (!this.mLayout.isAutoMeasureEnabled()) {
                if (this.mHasFixedSize) {
                    this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
                    return;
                }

                if (this.mAdapterUpdateDuringMeasure) {
                    this.startInterceptRequestLayout();
                    this.onEnterLayoutOrScroll();
                    this.processAdapterUpdatesAndSetAnimationFlags();
                    this.onExitLayoutOrScroll();
                    if (this.mState.mRunPredictiveAnimations) {
                        this.mState.mInPreLayout = true;
                    } else {
                        this.mAdapterHelper.consumeUpdatesInOnePass();
                        this.mState.mInPreLayout = false;
                    }

                    this.mAdapterUpdateDuringMeasure = false;
                    this.stopInterceptRequestLayout(false);
                } else if (this.mState.mRunPredictiveAnimations) {
                    this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredHeight());
                    return;
                }

                if (this.mAdapter != null) {
                    this.mState.mItemCount = this.mAdapter.getItemCount();
                } else {
                    this.mState.mItemCount = 0;
                }

                this.startInterceptRequestLayout();
                this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
                this.stopInterceptRequestLayout(false);
                this.mState.mInPreLayout = false;
            } else {
                int widthMode = MeasureSpec.getMode(widthSpec);
                int heightMode = MeasureSpec.getMode(heightSpec);
                this.mLayout.onMeasure(this.mRecycler, this.mState, widthSpec, heightSpec);
                boolean measureSpecModeIsExactly = widthMode == 1073741824 && heightMode == 1073741824;
                if (measureSpecModeIsExactly || this.mAdapter == null) {
                    return;
                }

                if (this.mState.mLayoutStep == 1) {
                    this.dispatchLayoutStep1();
                }

                this.mLayout.setMeasureSpecs(widthSpec, heightSpec);
                this.mState.mIsMeasuring = true;
                this.dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(widthSpec, heightSpec);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    this.dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(widthSpec, heightSpec);
                }
            }

        }
    }

    void defaultOnMeasure(int widthSpec, int heightSpec) {
        int width = RecyclerView.LayoutManager.chooseSize(widthSpec, this.getPaddingLeft() + this.getPaddingRight(), ViewCompat.getMinimumWidth(this));
        int height = RecyclerView.LayoutManager.chooseSize(heightSpec, this.getPaddingTop() + this.getPaddingBottom(), ViewCompat.getMinimumHeight(this));
        this.setMeasuredDimension(width, height);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            this.invalidateGlows();
        }

    }

    public void setItemAnimator(@Nullable RecyclerView.ItemAnimator animator) {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
            this.mItemAnimator.setListener((RecyclerView.ItemAnimator.ItemAnimatorListener)null);
        }

        this.mItemAnimator = animator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.setListener(this.mItemAnimatorListener);
        }

    }

    void onEnterLayoutOrScroll() {
        ++this.mLayoutOrScrollCounter;
    }

    void onExitLayoutOrScroll() {
        this.onExitLayoutOrScroll(true);
    }

    void onExitLayoutOrScroll(boolean enableChangeEvents) {
        --this.mLayoutOrScrollCounter;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (enableChangeEvents) {
                this.dispatchContentChangedIfNecessary();
                this.dispatchPendingImportantForAccessibilityChanges();
            }
        }

    }

    boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }

    private void dispatchContentChangedIfNecessary() {
        int flags = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (flags != 0 && this.isAccessibilityEnabled()) {
            AccessibilityEvent event = AccessibilityEvent.obtain();
            event.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(event, flags);
            this.sendAccessibilityEventUnchecked(event);
        }

    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent event) {
        if (this.isComputingLayout()) {
            int type = 0;
            if (event != null) {
                type = AccessibilityEventCompat.getContentChangeTypes(event);
            }

            if (type == 0) {
                type = 0;
            }

            this.mEatenAccessibilityChangeFlags |= type;
            return true;
        } else {
            return false;
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        if (!this.shouldDeferAccessibilityEvent(event)) {
            super.sendAccessibilityEventUnchecked(event);
        }
    }

    @Nullable
    public RecyclerView.ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }

    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }

        if (this.predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        } else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }

        boolean animationTypeSupported = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.mRunSimpleAnimations = this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || animationTypeSupported || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds());
        this.mState.mRunPredictiveAnimations = this.mState.mRunSimpleAnimations && animationTypeSupported && !this.mDataSetHasChangedAfterLayout && this.predictiveItemAnimationsEnabled();
    }

    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        } else if (this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        } else {
            this.mState.mIsMeasuring = false;
            if (this.mState.mLayoutStep == 1) {
                this.dispatchLayoutStep1();
                this.mLayout.setExactMeasureSpecsFrom(this);
                this.dispatchLayoutStep2();
            } else if (!this.mAdapterHelper.hasUpdates() && this.mLayout.getWidth() == this.getWidth() && this.mLayout.getHeight() == this.getHeight()) {
                this.mLayout.setExactMeasureSpecsFrom(this);
            } else {
                this.mLayout.setExactMeasureSpecsFrom(this);
                this.dispatchLayoutStep2();
            }

            this.dispatchLayoutStep3();
        }
    }

    private void saveFocusInfo() {
        View child = null;
        if (this.mPreserveFocusAfterLayout && this.hasFocus() && this.mAdapter != null) {
            child = this.getFocusedChild();
        }

        RecyclerView.ViewHolder focusedVh = child == null ? null : this.findContainingViewHolder(child);
        if (focusedVh == null) {
            this.resetFocusInfo();
        } else {
            this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? focusedVh.getItemId() : -1L;
            this.mState.mFocusedItemPosition = this.mDataSetHasChangedAfterLayout ? -1 : (focusedVh.isRemoved() ? focusedVh.mOldPosition : focusedVh.getAdapterPosition());
            this.mState.mFocusedSubChildId = this.getDeepestFocusedViewWithId(focusedVh.itemView);
        }

    }

    private void resetFocusInfo() {
        this.mState.mFocusedItemId = -1L;
        this.mState.mFocusedItemPosition = -1;
        this.mState.mFocusedSubChildId = -1;
    }

    @Nullable
    private View findNextViewToFocus() {
        int startFocusSearchIndex = this.mState.mFocusedItemPosition != -1 ? this.mState.mFocusedItemPosition : 0;
        int itemCount = this.mState.getItemCount();

        RecyclerView.ViewHolder nextFocus;
        int limit;
        for(limit = startFocusSearchIndex; limit < itemCount; ++limit) {
            nextFocus = this.findViewHolderForAdapterPosition(limit);
            if (nextFocus == null) {
                break;
            }

            if (nextFocus.itemView.hasFocusable()) {
                return nextFocus.itemView;
            }
        }

        limit = Math.min(itemCount, startFocusSearchIndex);

        for(int i = limit - 1; i >= 0; --i) {
            nextFocus = this.findViewHolderForAdapterPosition(i);
            if (nextFocus == null) {
                return null;
            }

            if (nextFocus.itemView.hasFocusable()) {
                return nextFocus.itemView;
            }
        }

        return null;
    }

    private void recoverFocusFromState() {
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && this.hasFocus() && this.getDescendantFocusability() != 393216 && (this.getDescendantFocusability() != 131072 || !this.isFocused())) {
            if (!this.isFocused()) {
                View focusedChild = this.getFocusedChild();
                if (IGNORE_DETACHED_FOCUSED_CHILD && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
                    if (this.mChildHelper.getChildCount() == 0) {
                        this.requestFocus();
                        return;
                    }
                } else if (!this.mChildHelper.isHidden(focusedChild)) {
                    return;
                }
            }

            RecyclerView.ViewHolder focusTarget = null;
            if (this.mState.mFocusedItemId != -1L && this.mAdapter.hasStableIds()) {
                focusTarget = this.findViewHolderForItemId(this.mState.mFocusedItemId);
            }

            View viewToFocus = null;
            if (focusTarget != null && !this.mChildHelper.isHidden(focusTarget.itemView) && focusTarget.itemView.hasFocusable()) {
                viewToFocus = focusTarget.itemView;
            } else if (this.mChildHelper.getChildCount() > 0) {
                viewToFocus = this.findNextViewToFocus();
            }

            if (viewToFocus != null) {
                if ((long)this.mState.mFocusedSubChildId != -1L) {
                    View child = viewToFocus.findViewById(this.mState.mFocusedSubChildId);
                    if (child != null && child.isFocusable()) {
                        viewToFocus = child;
                    }
                }

                viewToFocus.requestFocus();
            }

        }
    }

    private int getDeepestFocusedViewWithId(View view) {
        int lastKnownId = view.getId();

        while(!view.isFocused() && view instanceof ViewGroup && view.hasFocus()) {
            view = ((ViewGroup)view).getFocusedChild();
            int id = view.getId();
            if (id != -1) {
                lastKnownId = view.getId();
            }
        }

        return lastKnownId;
    }

    final void fillRemainingScrollValues(RecyclerView.State state) {
        if (this.getScrollState() == 2) {
            OverScroller scroller = this.mViewFlinger.mScroller;
            state.mRemainingScrollHorizontal = scroller.getFinalX() - scroller.getCurrX();
            state.mRemainingScrollVertical = scroller.getFinalY() - scroller.getCurrY();
        } else {
            state.mRemainingScrollHorizontal = 0;
            state.mRemainingScrollVertical = 0;
        }

    }

    private void dispatchLayoutStep1() {
        this.mState.assertLayoutStep(1);
        this.fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        this.startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        this.onEnterLayoutOrScroll();
        this.processAdapterUpdatesAndSetAnimationFlags();
        this.saveFocusInfo();
        this.mState.mTrackOldChangeHolders = this.mState.mRunSimpleAnimations && this.mItemsChanged;
        this.mItemsAddedOrRemoved = this.mItemsChanged = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int i;
        if (this.mState.mRunSimpleAnimations) {
            int count = this.mChildHelper.getChildCount();

            for(i = 0; i < count; ++i) {
                RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!holder.shouldIgnore() && (!holder.isInvalid() || this.mAdapter.hasStableIds())) {
                    RecyclerView.ItemAnimator.ItemHolderInfo animationInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, holder, RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(holder), holder.getUnmodifiedPayloads());
                    this.mViewInfoStore.addToPreLayout(holder, animationInfo);
                    if (this.mState.mTrackOldChangeHolders && holder.isUpdated() && !holder.isRemoved() && !holder.shouldIgnore() && !holder.isInvalid()) {
                        long key = this.getChangedHolderKey(holder);
                        this.mViewInfoStore.addToOldChangeHolders(key, holder);
                    }
                }
            }
        }

        if (this.mState.mRunPredictiveAnimations) {
            this.saveOldPositions();
            boolean didStructureChange = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = didStructureChange;

            for(i = 0; i < this.mChildHelper.getChildCount(); ++i) {
                View child = this.mChildHelper.getChildAt(i);
                RecyclerView.ViewHolder viewHolder = getChildViewHolderInt(child);
                if (!viewHolder.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(viewHolder)) {
                    int flags = RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(viewHolder);
                    boolean wasHidden = viewHolder.hasAnyOfTheFlags(8192);
                    if (!wasHidden) {
                        flags |= 4096;
                    }

                    RecyclerView.ItemAnimator.ItemHolderInfo animationInfo = this.mItemAnimator.recordPreLayoutInformation(this.mState, viewHolder, flags, viewHolder.getUnmodifiedPayloads());
                    if (wasHidden) {
                        this.recordAnimationInfoIfBouncedHiddenView(viewHolder, animationInfo);
                    } else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(viewHolder, animationInfo);
                    }
                }
            }

            this.clearOldPositions();
        } else {
            this.clearOldPositions();
        }

        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mPendingSavedState = null;
        this.mState.mRunSimpleAnimations = this.mState.mRunSimpleAnimations && this.mItemAnimator != null;
        this.mState.mLayoutStep = 4;
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        this.startInterceptRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.mLayoutStep = 1;
        if (this.mState.mRunSimpleAnimations) {
            for(int i = this.mChildHelper.getChildCount() - 1; i >= 0; --i) {
                RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!holder.shouldIgnore()) {
                    long key = this.getChangedHolderKey(holder);
                    RecyclerView.ItemAnimator.ItemHolderInfo animationInfo = this.mItemAnimator.recordPostLayoutInformation(this.mState, holder);
                    RecyclerView.ViewHolder oldChangeViewHolder = this.mViewInfoStore.getFromOldChangeHolders(key);
                    if (oldChangeViewHolder != null && !oldChangeViewHolder.shouldIgnore()) {
                        boolean oldDisappearing = this.mViewInfoStore.isDisappearing(oldChangeViewHolder);
                        boolean newDisappearing = this.mViewInfoStore.isDisappearing(holder);
                        if (oldDisappearing && oldChangeViewHolder == holder) {
                            this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                        } else {
                            RecyclerView.ItemAnimator.ItemHolderInfo preInfo = this.mViewInfoStore.popFromPreLayout(oldChangeViewHolder);
                            this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                            RecyclerView.ItemAnimator.ItemHolderInfo postInfo = this.mViewInfoStore.popFromPostLayout(holder);
                            if (preInfo == null) {
                                this.handleMissingPreInfoForChangeError(key, holder, oldChangeViewHolder);
                            } else {
                                this.animateChange(oldChangeViewHolder, holder, preInfo, postInfo, oldDisappearing, newDisappearing);
                            }
                        }
                    } else {
                        this.mViewInfoStore.addToPostLayout(holder, animationInfo);
                    }
                }
            }

            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }

        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if (this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }

        if (this.mLayout.mPrefetchMaxObservedInInitialPrefetch) {
            this.mLayout.mPrefetchMaxCountObserved = 0;
            this.mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            this.mRecycler.updateViewCacheSize();
        }

        this.mLayout.onLayoutCompleted(this.mState);
        this.onExitLayoutOrScroll();
        this.stopInterceptRequestLayout(false);
        this.mViewInfoStore.clear();
        if (this.didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            this.dispatchOnScrolled(0, 0);
        }

        this.recoverFocusFromState();
        this.resetFocusInfo();
    }

    private void handleMissingPreInfoForChangeError(long key, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder oldChangeViewHolder) {
        int childCount = this.mChildHelper.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            View view = this.mChildHelper.getChildAt(i);
            RecyclerView.ViewHolder other = getChildViewHolderInt(view);
            if (other != holder) {
                long otherKey = this.getChangedHolderKey(other);
                if (otherKey == key) {
                    if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
                        throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + other + " \n View Holder 2:" + holder + this.exceptionLabel());
                    }

                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + other + " \n View Holder 2:" + holder + this.exceptionLabel());
                }
            }
        }

        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + oldChangeViewHolder + " cannot be found but it is necessary for " + holder + this.exceptionLabel());
    }

    void recordAnimationInfoIfBouncedHiddenView(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo animationInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            long key = this.getChangedHolderKey(viewHolder);
            this.mViewInfoStore.addToOldChangeHolders(key, viewHolder);
        }

        this.mViewInfoStore.addToPreLayout(viewHolder, animationInfo);
    }

    private void findMinMaxChildLayoutPositions(int[] into) {
        int count = this.mChildHelper.getChildCount();
        if (count == 0) {
            into[0] = -1;
            into[1] = -1;
        } else {
            int minPositionPreLayout = 2147483647;
            int maxPositionPreLayout = -2147483648;

            for(int i = 0; i < count; ++i) {
                RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!holder.shouldIgnore()) {
                    int pos = holder.getLayoutPosition();
                    if (pos < minPositionPreLayout) {
                        minPositionPreLayout = pos;
                    }

                    if (pos > maxPositionPreLayout) {
                        maxPositionPreLayout = pos;
                    }
                }
            }

            into[0] = minPositionPreLayout;
            into[1] = maxPositionPreLayout;
        }
    }

    private boolean didChildRangeChange(int minPositionPreLayout, int maxPositionPreLayout) {
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        return this.mMinMaxLayoutPositions[0] != minPositionPreLayout || this.mMinMaxLayoutPositions[1] != maxPositionPreLayout;
    }

    protected void removeDetachedView(View child, boolean animate) {
        RecyclerView.ViewHolder vh = getChildViewHolderInt(child);
        if (vh != null) {
            if (vh.isTmpDetached()) {
                vh.clearTmpDetachFlag();
            } else if (!vh.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + vh + this.exceptionLabel());
            }
        }

        child.clearAnimation();
        this.dispatchChildDetached(child);
        super.removeDetachedView(child, animate);
    }

    long getChangedHolderKey(RecyclerView.ViewHolder holder) {
        return this.mAdapter.hasStableIds() ? holder.getItemId() : (long)holder.mPosition;
    }

    void animateAppearance(@NonNull RecyclerView.ViewHolder itemHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo) {
        itemHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(itemHolder, preLayoutInfo, postLayoutInfo)) {
            this.postAnimationRunner();
        }

    }

    void animateDisappearance(@NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo) {
        this.addAnimatingView(holder);
        holder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(holder, preLayoutInfo, postLayoutInfo)) {
            this.postAnimationRunner();
        }

    }

    private void animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postInfo, boolean oldHolderDisappearing, boolean newHolderDisappearing) {
        oldHolder.setIsRecyclable(false);
        if (oldHolderDisappearing) {
            this.addAnimatingView(oldHolder);
        }

        if (oldHolder != newHolder) {
            if (newHolderDisappearing) {
                this.addAnimatingView(newHolder);
            }

            oldHolder.mShadowedHolder = newHolder;
            this.addAnimatingView(oldHolder);
            this.mRecycler.unscrapView(oldHolder);
            newHolder.setIsRecyclable(false);
            newHolder.mShadowingHolder = oldHolder;
        }

        if (this.mItemAnimator.animateChange(oldHolder, newHolder, preInfo, postInfo)) {
            this.postAnimationRunner();
        }

    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        TraceCompat.beginSection("RV OnLayout");
        this.dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutFrozen) {
            super.requestLayout();
        } else {
            this.mLayoutWasDefered = true;
        }

    }

    void markItemDecorInsetsDirty() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            View child = this.mChildHelper.getUnfilteredChildAt(i);
            ((RecyclerView.LayoutParams)child.getLayoutParams()).mInsetsDirty = true;
        }

        this.mRecycler.markItemDecorInsetsDirty();
    }

    public void draw(Canvas c) {
        super.draw(c);
        int count = this.mItemDecorations.size();

        for(int i = 0; i < count; ++i) {
            ((RecyclerView.ItemDecoration)this.mItemDecorations.get(i)).onDrawOver(c, this, this.mState);
        }

        boolean needsInvalidate = false;
        int restore;
        int width;
        if (this.mLeftGlow != null && !this.mLeftGlow.isFinished()) {
            restore = c.save();
            width = this.mClipToPadding ? this.getPaddingBottom() : 0;
            c.rotate(270.0F);
            c.translate((float)(-this.getHeight() + width), 0.0F);
            needsInvalidate = this.mLeftGlow != null && this.mLeftGlow.draw(c);
            c.restoreToCount(restore);
        }

        if (this.mTopGlow != null && !this.mTopGlow.isFinished()) {
            restore = c.save();
            if (this.mClipToPadding) {
                c.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
            }

            needsInvalidate |= this.mTopGlow != null && this.mTopGlow.draw(c);
            c.restoreToCount(restore);
        }

        if (this.mRightGlow != null && !this.mRightGlow.isFinished()) {
            restore = c.save();
            width = this.getWidth();
            int padding = this.mClipToPadding ? this.getPaddingTop() : 0;
            c.rotate(90.0F);
            c.translate((float)(-padding), (float)(-width));
            needsInvalidate |= this.mRightGlow != null && this.mRightGlow.draw(c);
            c.restoreToCount(restore);
        }

        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished()) {
            restore = c.save();
            c.rotate(180.0F);
            if (this.mClipToPadding) {
                c.translate((float)(-this.getWidth() + this.getPaddingRight()), (float)(-this.getHeight() + this.getPaddingBottom()));
            } else {
                c.translate((float)(-this.getWidth()), (float)(-this.getHeight()));
            }

            needsInvalidate |= this.mBottomGlow != null && this.mBottomGlow.draw(c);
            c.restoreToCount(restore);
        }

        if (!needsInvalidate && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
            needsInvalidate = true;
        }

        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        int count = this.mItemDecorations.size();

        for(int i = 0; i < count; ++i) {
            ((RecyclerView.ItemDecoration)this.mItemDecorations.get(i)).onDraw(c, this, this.mState);
        }

    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof RecyclerView.LayoutParams && this.mLayout.checkLayoutParams((RecyclerView.LayoutParams)p);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        } else {
            return this.mLayout.generateDefaultLayoutParams();
        }
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        } else {
            return this.mLayout.generateLayoutParams(this.getContext(), attrs);
        }
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
        } else {
            return this.mLayout.generateLayoutParams(p);
        }
    }

    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }

    void saveOldPositions() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!holder.shouldIgnore()) {
                holder.saveOldPosition();
            }
        }

    }

    void clearOldPositions() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!holder.shouldIgnore()) {
                holder.clearOldPosition();
            }
        }

        this.mRecycler.clearOldPositions();
    }

    void offsetPositionRecordsForMove(int from, int to) {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        int start;
        int end;
        byte inBetweenOffset;
        if (from < to) {
            start = from;
            end = to;
            inBetweenOffset = -1;
        } else {
            start = to;
            end = from;
            inBetweenOffset = 1;
        }

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && holder.mPosition >= start && holder.mPosition <= end) {
                if (holder.mPosition == from) {
                    holder.offsetPosition(to - from, false);
                } else {
                    holder.offsetPosition(inBetweenOffset, false);
                }

                this.mState.mStructureChanged = true;
            }
        }

        this.mRecycler.offsetPositionRecordsForMove(from, to);
        this.requestLayout();
    }

    void offsetPositionRecordsForInsert(int positionStart, int itemCount) {
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.shouldIgnore() && holder.mPosition >= positionStart) {
                holder.offsetPosition(itemCount, false);
                this.mState.mStructureChanged = true;
            }
        }

        this.mRecycler.offsetPositionRecordsForInsert(positionStart, itemCount);
        this.requestLayout();
    }

    void offsetPositionRecordsForRemove(int positionStart, int itemCount, boolean applyToPreLayout) {
        int positionEnd = positionStart + itemCount;
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.shouldIgnore()) {
                if (holder.mPosition >= positionEnd) {
                    holder.offsetPosition(-itemCount, applyToPreLayout);
                    this.mState.mStructureChanged = true;
                } else if (holder.mPosition >= positionStart) {
                    holder.flagRemovedAndOffsetPosition(positionStart - 1, -itemCount, applyToPreLayout);
                    this.mState.mStructureChanged = true;
                }
            }
        }

        this.mRecycler.offsetPositionRecordsForRemove(positionStart, itemCount, applyToPreLayout);
        this.requestLayout();
    }

    void viewRangeUpdate(int positionStart, int itemCount, Object payload) {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        int positionEnd = positionStart + itemCount;

        for(int i = 0; i < childCount; ++i) {
            View child = this.mChildHelper.getUnfilteredChildAt(i);
            RecyclerView.ViewHolder holder = getChildViewHolderInt(child);
            if (holder != null && !holder.shouldIgnore() && holder.mPosition >= positionStart && holder.mPosition < positionEnd) {
                holder.addFlags(2);
                holder.addChangePayload(payload);
                ((RecyclerView.LayoutParams)child.getLayoutParams()).mInsetsDirty = true;
            }
        }

        this.mRecycler.viewRangeUpdate(positionStart, itemCount);
    }

    boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }

    void processDataSetCompletelyChanged(boolean dispatchItemsChanged) {
        this.mDispatchItemsChangedEvent |= dispatchItemsChanged;
        this.mDataSetHasChangedAfterLayout = true;
        this.markKnownViewsInvalid();
    }

    void markKnownViewsInvalid() {
        int childCount = this.mChildHelper.getUnfilteredChildCount();

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.shouldIgnore()) {
                holder.addFlags(6);
            }
        }

        this.markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            if (this.mLayout != null) {
                this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }

            this.markItemDecorInsetsDirty();
            this.requestLayout();
        }
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public void setPreserveFocusAfterLayout(boolean preserveFocusAfterLayout) {
        this.mPreserveFocusAfterLayout = preserveFocusAfterLayout;
    }

    public RecyclerView.ViewHolder getChildViewHolder(@NonNull View child) {
        ViewParent parent = child.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + child + " is not a direct child of " + this);
        } else {
            return getChildViewHolderInt(child);
        }
    }

    @Nullable
    public View findContainingItemView(@NonNull View view) {
        ViewParent parent;
        for(parent = view.getParent(); parent != null && parent != this && parent instanceof View; parent = view.getParent()) {
            view = (View)parent;
        }

        return parent == this ? view : null;
    }

    @Nullable
    public RecyclerView.ViewHolder findContainingViewHolder(@NonNull View view) {
        View itemView = this.findContainingItemView(view);
        return itemView == null ? null : this.getChildViewHolder(itemView);
    }

    static RecyclerView.ViewHolder getChildViewHolderInt(View child) {
        return child == null ? null : ((RecyclerView.LayoutParams)child.getLayoutParams()).mViewHolder;
    }

    /** @deprecated */
    @Deprecated
    public int getChildPosition(@NonNull View child) {
        return this.getChildAdapterPosition(child);
    }

    public int getChildAdapterPosition(@NonNull View child) {
        RecyclerView.ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getAdapterPosition() : -1;
    }

    public int getChildLayoutPosition(@NonNull View child) {
        RecyclerView.ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getLayoutPosition() : -1;
    }

    public long getChildItemId(@NonNull View child) {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(child);
            return holder != null ? holder.getItemId() : -1L;
        } else {
            return -1L;
        }
    }

    /** @deprecated */
    @Deprecated
    @Nullable
    public RecyclerView.ViewHolder findViewHolderForPosition(int position) {
        return this.findViewHolderForPosition(position, false);
    }

    @Nullable
    public RecyclerView.ViewHolder findViewHolderForLayoutPosition(int position) {
        return this.findViewHolderForPosition(position, false);
    }

    @Nullable
    public RecyclerView.ViewHolder findViewHolderForAdapterPosition(int position) {
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        } else {
            int childCount = this.mChildHelper.getUnfilteredChildCount();
            RecyclerView.ViewHolder hidden = null;

            for(int i = 0; i < childCount; ++i) {
                RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (holder != null && !holder.isRemoved() && this.getAdapterPositionFor(holder) == position) {
                    if (!this.mChildHelper.isHidden(holder.itemView)) {
                        return holder;
                    }

                    hidden = holder;
                }
            }

            return hidden;
        }
    }

    @Nullable
    RecyclerView.ViewHolder findViewHolderForPosition(int position, boolean checkNewPosition) {
        int childCount = this.mChildHelper.getUnfilteredChildCount();
        RecyclerView.ViewHolder hidden = null;

        for(int i = 0; i < childCount; ++i) {
            RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (holder != null && !holder.isRemoved()) {
                if (checkNewPosition) {
                    if (holder.mPosition != position) {
                        continue;
                    }
                } else if (holder.getLayoutPosition() != position) {
                    continue;
                }

                if (!this.mChildHelper.isHidden(holder.itemView)) {
                    return holder;
                }

                hidden = holder;
            }
        }

        return hidden;
    }

    public RecyclerView.ViewHolder findViewHolderForItemId(long id) {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            int childCount = this.mChildHelper.getUnfilteredChildCount();
            RecyclerView.ViewHolder hidden = null;

            for(int i = 0; i < childCount; ++i) {
                RecyclerView.ViewHolder holder = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (holder != null && !holder.isRemoved() && holder.getItemId() == id) {
                    if (!this.mChildHelper.isHidden(holder.itemView)) {
                        return holder;
                    }

                    hidden = holder;
                }
            }

            return hidden;
        } else {
            return null;
        }
    }

    @Nullable
    public View findChildViewUnder(float x, float y) {
        int count = this.mChildHelper.getChildCount();

        for(int i = count - 1; i >= 0; --i) {
            View child = this.mChildHelper.getChildAt(i);
            float translationX = child.getTranslationX();
            float translationY = child.getTranslationY();
            if (x >= (float)child.getLeft() + translationX && x <= (float)child.getRight() + translationX && y >= (float)child.getTop() + translationY && y <= (float)child.getBottom() + translationY) {
                return child;
            }
        }

        return null;
    }

    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    public void offsetChildrenVertical(@Px int dy) {
        int childCount = this.mChildHelper.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetTopAndBottom(dy);
        }

    }

    public void onChildAttachedToWindow(@NonNull View child) {
    }

    public void onChildDetachedFromWindow(@NonNull View child) {
    }

    public void offsetChildrenHorizontal(@Px int dx) {
        int childCount = this.mChildHelper.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetLeftAndRight(dx);
        }

    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect outBounds) {
        getDecoratedBoundsWithMarginsInt(view, outBounds);
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect outBounds) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)view.getLayoutParams();
        Rect insets = lp.mDecorInsets;
        outBounds.set(view.getLeft() - insets.left - lp.leftMargin, view.getTop() - insets.top - lp.topMargin, view.getRight() + insets.right + lp.rightMargin, view.getBottom() + insets.bottom + lp.bottomMargin);
    }

    Rect getItemDecorInsetsForChild(View child) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
        if (!lp.mInsetsDirty) {
            return lp.mDecorInsets;
        } else if (this.mState.isPreLayout() && (lp.isItemChanged() || lp.isViewInvalid())) {
            return lp.mDecorInsets;
        } else {
            Rect insets = lp.mDecorInsets;
            insets.set(0, 0, 0, 0);
            int decorCount = this.mItemDecorations.size();

            for(int i = 0; i < decorCount; ++i) {
                this.mTempRect.set(0, 0, 0, 0);
                ((RecyclerView.ItemDecoration)this.mItemDecorations.get(i)).getItemOffsets(this.mTempRect, child, this, this.mState);
                insets.left += this.mTempRect.left;
                insets.top += this.mTempRect.top;
                insets.right += this.mTempRect.right;
                insets.bottom += this.mTempRect.bottom;
            }

            lp.mInsetsDirty = false;
            return insets;
        }
    }

    public void onScrolled(@Px int dx, @Px int dy) {
    }

    void dispatchOnScrolled(int hresult, int vresult) {
        ++this.mDispatchScrollCounter;
        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        this.onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        this.onScrolled(hresult, vresult);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrolled(this, hresult, vresult);
        }

        if (this.mScrollListeners != null) {
            for(int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                ((RecyclerView.OnScrollListener)this.mScrollListeners.get(i)).onScrolled(this, hresult, vresult);
            }
        }

        --this.mDispatchScrollCounter;
    }

    public void onScrollStateChanged(int state) {
    }

    void dispatchOnScrollStateChanged(int state) {
        if (this.mLayout != null) {
            this.mLayout.onScrollStateChanged(state);
        }

        this.onScrollStateChanged(state);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, state);
        }

        if (this.mScrollListeners != null) {
            for(int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                ((RecyclerView.OnScrollListener)this.mScrollListeners.get(i)).onScrollStateChanged(this, state);
            }
        }

    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    void repositionShadowingViews() {
        int count = this.mChildHelper.getChildCount();

        for(int i = 0; i < count; ++i) {
            View view = this.mChildHelper.getChildAt(i);
            RecyclerView.ViewHolder holder = this.getChildViewHolder(view);
            if (holder != null && holder.mShadowingHolder != null) {
                View shadowingView = holder.mShadowingHolder.itemView;
                int left = view.getLeft();
                int top = view.getTop();
                if (left != shadowingView.getLeft() || top != shadowingView.getTop()) {
                    shadowingView.layout(left, top, left + shadowingView.getWidth(), top + shadowingView.getHeight());
                }
            }
        }

    }

    @Nullable
    static RecyclerView findNestedRecyclerView(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        } else if (view instanceof RecyclerView) {
            return (RecyclerView)view;
        } else {
            ViewGroup parent = (ViewGroup)view;
            int count = parent.getChildCount();

            for(int i = 0; i < count; ++i) {
                View child = parent.getChildAt(i);
                RecyclerView descendant = findNestedRecyclerView(child);
                if (descendant != null) {
                    return descendant;
                }
            }

            return null;
        }
    }

    static void clearNestedRecyclerViewIfNotNested(@NonNull RecyclerView.ViewHolder holder) {
        if (holder.mNestedRecyclerView != null) {
            View item = (View)holder.mNestedRecyclerView.get();

            while(item != null) {
                if (item == holder.itemView) {
                    return;
                }

                ViewParent parent = item.getParent();
                if (parent instanceof View) {
                    item = (View)parent;
                } else {
                    item = null;
                }
            }

            holder.mNestedRecyclerView = null;
        }

    }

    long getNanoTime() {
        return ALLOW_THREAD_GAP_WORK ? System.nanoTime() : 0L;
    }

    void dispatchChildDetached(View child) {
        RecyclerView.ViewHolder viewHolder = getChildViewHolderInt(child);
        this.onChildDetachedFromWindow(child);
        if (this.mAdapter != null && viewHolder != null) {
            this.mAdapter.onViewDetachedFromWindow(viewHolder);
        }

        if (this.mOnChildAttachStateListeners != null) {
            int cnt = this.mOnChildAttachStateListeners.size();

            for(int i = cnt - 1; i >= 0; --i) {
                ((RecyclerView.OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(child);
            }
        }

    }

    void dispatchChildAttached(View child) {
        RecyclerView.ViewHolder viewHolder = getChildViewHolderInt(child);
        this.onChildAttachedToWindow(child);
        if (this.mAdapter != null && viewHolder != null) {
            this.mAdapter.onViewAttachedToWindow(viewHolder);
        }

        if (this.mOnChildAttachStateListeners != null) {
            int cnt = this.mOnChildAttachStateListeners.size();

            for(int i = cnt - 1; i >= 0; --i) {
                ((RecyclerView.OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(child);
            }
        }

    }

    @VisibleForTesting
    boolean setChildImportantForAccessibilityInternal(RecyclerView.ViewHolder viewHolder, int importantForAccessibility) {
        if (this.isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = importantForAccessibility;
            this.mPendingAccessibilityImportanceChange.add(viewHolder);
            return false;
        } else {
            ViewCompat.setImportantForAccessibility(viewHolder.itemView, importantForAccessibility);
            return true;
        }
    }

    void dispatchPendingImportantForAccessibilityChanges() {
        for(int i = this.mPendingAccessibilityImportanceChange.size() - 1; i >= 0; --i) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)this.mPendingAccessibilityImportanceChange.get(i);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore()) {
                int state = viewHolder.mPendingAccessibilityState;
                if (state != -1) {
                    ViewCompat.setImportantForAccessibility(viewHolder.itemView, state);
                    viewHolder.mPendingAccessibilityState = -1;
                }
            }
        }

        this.mPendingAccessibilityImportanceChange.clear();
    }

    int getAdapterPositionFor(RecyclerView.ViewHolder viewHolder) {
        return !viewHolder.hasAnyOfTheFlags(524) && viewHolder.isBound() ? this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder.mPosition) : -1;
    }

    @VisibleForTesting
    void initFastScroller(StateListDrawable verticalThumbDrawable, Drawable verticalTrackDrawable, StateListDrawable horizontalThumbDrawable, Drawable horizontalTrackDrawable) {
        if (verticalThumbDrawable != null && verticalTrackDrawable != null && horizontalThumbDrawable != null && horizontalTrackDrawable != null) {
            Resources resources = this.getContext().getResources();
            new FastScroller(this, verticalThumbDrawable, verticalTrackDrawable, horizontalThumbDrawable, horizontalTrackDrawable, resources.getDimensionPixelSize(dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(dimen.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + this.exceptionLabel());
        }
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return this.getScrollingChildHelper().startNestedScroll(axes);
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.getScrollingChildHelper().startNestedScroll(axes, type);
    }

    public void stopNestedScroll() {
        this.getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int type) {
        this.getScrollingChildHelper().stopNestedScroll(type);
    }

    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.getScrollingChildHelper().hasNestedScrollingParent(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        return this.mChildDrawingOrderCallback == null ? super.getChildDrawingOrder(childCount, i) : this.mChildDrawingOrderCallback.onGetChildDrawingOrder(childCount, i);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }

        return this.mScrollingChildHelper;
    }

    static {
        FORCE_INVALIDATE_DISPLAY_LIST = VERSION.SDK_INT == 18 || VERSION.SDK_INT == 19 || VERSION.SDK_INT == 20;
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = VERSION.SDK_INT >= 23;
        POST_UPDATES_ON_ANIMATION = VERSION.SDK_INT >= 16;
        ALLOW_THREAD_GAP_WORK = VERSION.SDK_INT >= 21;
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = VERSION.SDK_INT <= 15;
        IGNORE_DETACHED_FOCUSED_CHILD = VERSION.SDK_INT <= 15;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
        sQuinticInterpolator = new Interpolator() {
            public float getInterpolation(float t) {
                --t;
                return t * t * t * t * t + 1.0F;
            }
        };
    }

    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int var1, int var2);
    }

    public abstract static class ItemAnimator {
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_REMOVED = 8;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        private RecyclerView.ItemAnimator.ItemAnimatorListener mListener = null;
        private ArrayList<RecyclerView.ItemAnimator.ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList();
        private long mAddDuration = 120L;
        private long mRemoveDuration = 120L;
        private long mMoveDuration = 250L;
        private long mChangeDuration = 250L;

        public ItemAnimator() {
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public void setMoveDuration(long moveDuration) {
            this.mMoveDuration = moveDuration;
        }

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public void setAddDuration(long addDuration) {
            this.mAddDuration = addDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public void setRemoveDuration(long removeDuration) {
            this.mRemoveDuration = removeDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public void setChangeDuration(long changeDuration) {
            this.mChangeDuration = changeDuration;
        }

        void setListener(RecyclerView.ItemAnimator.ItemAnimatorListener listener) {
            this.mListener = listener;
        }

        @NonNull
        public RecyclerView.ItemAnimator.ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State state, @NonNull RecyclerView.ViewHolder viewHolder, int changeFlags, @NonNull List<Object> payloads) {
            return this.obtainHolderInfo().setFrom(viewHolder);
        }

        @NonNull
        public RecyclerView.ItemAnimator.ItemHolderInfo recordPostLayoutInformation(@NonNull RecyclerView.State state, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.obtainHolderInfo().setFrom(viewHolder);
        }

        public abstract boolean animateDisappearance(@NonNull RecyclerView.ViewHolder var1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var2, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo var3);

        public abstract boolean animateAppearance(@NonNull RecyclerView.ViewHolder var1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo var2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var3);

        public abstract boolean animatePersistence(@NonNull RecyclerView.ViewHolder var1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var3);

        public abstract boolean animateChange(@NonNull RecyclerView.ViewHolder var1, @NonNull RecyclerView.ViewHolder var2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var3, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var4);

        static int buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder viewHolder) {
            int flags = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            } else {
                if ((flags & 4) == 0) {
                    int oldPos = viewHolder.getOldPosition();
                    int pos = viewHolder.getAdapterPosition();
                    if (oldPos != -1 && pos != -1 && oldPos != pos) {
                        flags |= 2048;
                    }
                }

                return flags;
            }
        }

        public abstract void runPendingAnimations();

        public abstract void endAnimation(@NonNull RecyclerView.ViewHolder var1);

        public abstract void endAnimations();

        public abstract boolean isRunning();

        public final void dispatchAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
            this.onAnimationFinished(viewHolder);
            if (this.mListener != null) {
                this.mListener.onAnimationFinished(viewHolder);
            }

        }

        public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
        }

        public final void dispatchAnimationStarted(@NonNull RecyclerView.ViewHolder viewHolder) {
            this.onAnimationStarted(viewHolder);
        }

        public void onAnimationStarted(@NonNull RecyclerView.ViewHolder viewHolder) {
        }

        public final boolean isRunning(@Nullable RecyclerView.ItemAnimator.ItemAnimatorFinishedListener listener) {
            boolean running = this.isRunning();
            if (listener != null) {
                if (!running) {
                    listener.onAnimationsFinished();
                } else {
                    this.mFinishedListeners.add(listener);
                }
            }

            return running;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
            return this.canReuseUpdatedViewHolder(viewHolder);
        }

        public final void dispatchAnimationsFinished() {
            int count = this.mFinishedListeners.size();

            for(int i = 0; i < count; ++i) {
                ((RecyclerView.ItemAnimator.ItemAnimatorFinishedListener)this.mFinishedListeners.get(i)).onAnimationsFinished();
            }

            this.mFinishedListeners.clear();
        }

        @NonNull
        public RecyclerView.ItemAnimator.ItemHolderInfo obtainHolderInfo() {
            return new RecyclerView.ItemAnimator.ItemHolderInfo();
        }

        public static class ItemHolderInfo {
            public int left;
            public int top;
            public int right;
            public int bottom;
            public int changeFlags;

            public ItemHolderInfo() {
            }

            @NonNull
            public RecyclerView.ItemAnimator.ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder holder) {
                return this.setFrom(holder, 0);
            }

            @NonNull
            public RecyclerView.ItemAnimator.ItemHolderInfo setFrom(@NonNull RecyclerView.ViewHolder holder, int flags) {
                View view = holder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                this.right = view.getRight();
                this.bottom = view.getBottom();
                return this;
            }
        }

        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        interface ItemAnimatorListener {
            void onAnimationFinished(@NonNull RecyclerView.ViewHolder var1);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }
    }

    private class ItemAnimatorRestoreListener implements RecyclerView.ItemAnimator.ItemAnimatorListener {
        ItemAnimatorRestoreListener() {
        }

        public void onAnimationFinished(RecyclerView.ViewHolder item) {
            item.setIsRecyclable(true);
            if (item.mShadowedHolder != null && item.mShadowingHolder == null) {
                item.mShadowedHolder = null;
            }

            item.mShadowingHolder = null;
            if (!item.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(item.itemView) && item.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(item.itemView, false);
            }

        }
    }

    public abstract static class OnFlingListener {
        public OnFlingListener() {
        }

        public abstract boolean onFling(int var1, int var2);
    }

    public static class State {
        static final int STEP_START = 1;
        static final int STEP_LAYOUT = 2;
        static final int STEP_ANIMATIONS = 4;
        int mTargetPosition = -1;
        private SparseArray<Object> mData;
        int mPreviousLayoutItemCount = 0;
        int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        int mLayoutStep = 1;
        int mItemCount = 0;
        boolean mStructureChanged = false;
        boolean mInPreLayout = false;
        boolean mTrackOldChangeHolders = false;
        boolean mIsMeasuring = false;
        boolean mRunSimpleAnimations = false;
        boolean mRunPredictiveAnimations = false;
        int mFocusedItemPosition;
        long mFocusedItemId;
        int mFocusedSubChildId;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;

        public State() {
        }

        void assertLayoutStep(int accepted) {
            if ((accepted & this.mLayoutStep) == 0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(accepted) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
            }
        }

        RecyclerView.State reset() {
            this.mTargetPosition = -1;
            if (this.mData != null) {
                this.mData.clear();
            }

            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mIsMeasuring = false;
            return this;
        }

        void prepareForNestedPrefetch(RecyclerView.Adapter adapter) {
            this.mLayoutStep = 1;
            this.mItemCount = adapter.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public boolean isMeasuring() {
            return this.mIsMeasuring;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }

        public void remove(int resourceId) {
            if (this.mData != null) {
                this.mData.remove(resourceId);
            }
        }

        public <T> T get(int resourceId) {
            return this.mData == null ? null : this.mData.get(resourceId);
        }

        public void put(int resourceId, Object data) {
            if (this.mData == null) {
                this.mData = new SparseArray();
            }

            this.mData.put(resourceId, data);
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }

        public boolean didStructureChange() {
            return this.mStructureChanged;
        }

        public int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public int getRemainingScrollHorizontal() {
            return this.mRemainingScrollHorizontal;
        }

        public int getRemainingScrollVertical() {
            return this.mRemainingScrollVertical;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mIsMeasuring=" + this.mIsMeasuring + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class SavedState extends AbsSavedState {
        Parcelable mLayoutState;
        public static final Creator<RecyclerView.SavedState> CREATOR = new ClassLoaderCreator<RecyclerView.SavedState>() {
            public RecyclerView.SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new RecyclerView.SavedState(in, loader);
            }

            public RecyclerView.SavedState createFromParcel(Parcel in) {
                return new RecyclerView.SavedState(in, (ClassLoader)null);
            }

            public RecyclerView.SavedState[] newArray(int size) {
                return new RecyclerView.SavedState[size];
            }
        };

        SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            this.mLayoutState = in.readParcelable(loader != null ? loader : RecyclerView.LayoutManager.class.getClassLoader());
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.mLayoutState, 0);
        }

        void copyFrom(RecyclerView.SavedState other) {
            this.mLayoutState = other.mLayoutState;
        }
    }

    static class AdapterDataObservable extends Observable<RecyclerView.AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for(int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onChanged();
            }

        }

        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            this.notifyItemRangeChanged(positionStart, itemCount, (Object)null);
        }

        public void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            for(int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(positionStart, itemCount, payload);
            }

        }

        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            for(int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(positionStart, itemCount);
            }

        }

        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            for(int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(positionStart, itemCount);
            }

        }

        public void notifyItemMoved(int fromPosition, int toPosition) {
            for(int i = this.mObservers.size() - 1; i >= 0; --i) {
                ((RecyclerView.AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(fromPosition, toPosition, 1);
            }

        }
    }

    public abstract static class SmoothScroller {
        private int mTargetPosition = -1;
        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private boolean mRunning;
        private View mTargetView;
        private final RecyclerView.SmoothScroller.Action mRecyclingAction = new RecyclerView.SmoothScroller.Action(0, 0);
        private boolean mStarted;

        public SmoothScroller() {
        }

        void start(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
            if (this.mStarted) {
                Log.w("RecyclerView", "An instance of " + this.getClass().getSimpleName() + " was started " + "more than once. Each instance of" + this.getClass().getSimpleName() + " " + "is intended to only be used once. You should create a new instance for " + "each use.");
            }

            this.mRecyclerView = recyclerView;
            this.mLayoutManager = layoutManager;
            if (this.mTargetPosition == -1) {
                throw new IllegalArgumentException("Invalid target position");
            } else {
                this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
                this.mRunning = true;
                this.mPendingInitialRun = true;
                this.mTargetView = this.findViewByPosition(this.getTargetPosition());
                this.onStart();
                this.mRecyclerView.mViewFlinger.postOnAnimation();
                this.mStarted = true;
            }
        }

        public void setTargetPosition(int targetPosition) {
            this.mTargetPosition = targetPosition;
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int targetPosition) {
            RecyclerView.LayoutManager layoutManager = this.getLayoutManager();
            if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
                return ((RecyclerView.SmoothScroller.ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(targetPosition);
            } else {
                Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + RecyclerView.SmoothScroller.ScrollVectorProvider.class.getCanonicalName());
                return null;
            }
        }

        @Nullable
        public RecyclerView.LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        protected final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                this.onStop();
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mLayoutManager.onSmoothScrollerStopped(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        void onAnimation(int dx, int dy) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (!this.mRunning || this.mTargetPosition == -1 || recyclerView == null) {
                this.stop();
            }

            if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null) {
                PointF pointF = this.computeScrollVectorForPosition(this.mTargetPosition);
                if (pointF != null && (pointF.x != 0.0F || pointF.y != 0.0F)) {
                    recyclerView.scrollStep((int)Math.signum(pointF.x), (int)Math.signum(pointF.y), (int[])null);
                }
            }

            this.mPendingInitialRun = false;
            if (this.mTargetView != null) {
                if (this.getChildPosition(this.mTargetView) == this.mTargetPosition) {
                    this.onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(recyclerView);
                    this.stop();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }

            if (this.mRunning) {
                this.onSeekTargetStep(dx, dy, recyclerView.mState, this.mRecyclingAction);
                boolean hadJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(recyclerView);
                if (hadJumpTarget) {
                    if (this.mRunning) {
                        this.mPendingInitialRun = true;
                        recyclerView.mViewFlinger.postOnAnimation();
                    } else {
                        this.stop();
                    }
                }
            }

        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public View findViewByPosition(int position) {
            return this.mRecyclerView.mLayout.findViewByPosition(position);
        }

        /** @deprecated */
        @Deprecated
        public void instantScrollToPosition(int position) {
            this.mRecyclerView.scrollToPosition(position);
        }

        protected void onChildAttachedToWindow(View child) {
            if (this.getChildPosition(child) == this.getTargetPosition()) {
                this.mTargetView = child;
            }

        }

        protected void normalize(@NonNull PointF scrollVector) {
            float magnitude = (float)Math.sqrt((double)(scrollVector.x * scrollVector.x + scrollVector.y * scrollVector.y));
            scrollVector.x /= magnitude;
            scrollVector.y /= magnitude;
        }

        protected abstract void onStart();

        protected abstract void onStop();

        protected abstract void onSeekTargetStep(@Px int var1, @Px int var2, @NonNull RecyclerView.State var3, @NonNull RecyclerView.SmoothScroller.Action var4);

        protected abstract void onTargetFound(@NonNull View var1, @NonNull RecyclerView.State var2, @NonNull RecyclerView.SmoothScroller.Action var3);

        public interface ScrollVectorProvider {
            @Nullable
            PointF computeScrollVectorForPosition(int var1);
        }

        public static class Action {
            public static final int UNDEFINED_DURATION = -2147483648;
            private int mDx;
            private int mDy;
            private int mDuration;
            private int mJumpToPosition;
            private Interpolator mInterpolator;
            private boolean mChanged;
            private int mConsecutiveUpdates;

            public Action(@Px int dx, @Px int dy) {
                this(dx, dy, -2147483648, (Interpolator)null);
            }

            public Action(@Px int dx, @Px int dy, int duration) {
                this(dx, dy, duration, (Interpolator)null);
            }

            public Action(@Px int dx, @Px int dy, int duration, @Nullable Interpolator interpolator) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = dx;
                this.mDy = dy;
                this.mDuration = duration;
                this.mInterpolator = interpolator;
            }

            public void jumpTo(int targetPosition) {
                this.mJumpToPosition = targetPosition;
            }

            boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }

            void runIfNecessary(RecyclerView recyclerView) {
                if (this.mJumpToPosition >= 0) {
                    int position = this.mJumpToPosition;
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(position);
                    this.mChanged = false;
                } else {
                    if (this.mChanged) {
                        this.validate();
                        if (this.mInterpolator == null) {
                            if (this.mDuration == -2147483648) {
                                recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                            } else {
                                recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                            }
                        } else {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                        }

                        ++this.mConsecutiveUpdates;
                        if (this.mConsecutiveUpdates > 10) {
                            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                        }

                        this.mChanged = false;
                    } else {
                        this.mConsecutiveUpdates = 0;
                    }

                }
            }

            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                } else if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            @Px
            public int getDx() {
                return this.mDx;
            }

            public void setDx(@Px int dx) {
                this.mChanged = true;
                this.mDx = dx;
            }

            @Px
            public int getDy() {
                return this.mDy;
            }

            public void setDy(@Px int dy) {
                this.mChanged = true;
                this.mDy = dy;
            }

            public int getDuration() {
                return this.mDuration;
            }

            public void setDuration(int duration) {
                this.mChanged = true;
                this.mDuration = duration;
            }

            @Nullable
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }

            public void setInterpolator(@Nullable Interpolator interpolator) {
                this.mChanged = true;
                this.mInterpolator = interpolator;
            }

            public void update(@Px int dx, @Px int dy, int duration, @Nullable Interpolator interpolator) {
                this.mDx = dx;
                this.mDy = dy;
                this.mDuration = duration;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }
    }

    public abstract static class AdapterDataObserver {
        public AdapterDataObserver() {
        }

        public void onChanged() {
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
        }

        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            this.onItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        RecyclerView.ViewHolder mViewHolder;
        final Rect mDecorInsets = new Rect();
        boolean mInsetsDirty = true;
        boolean mPendingInvalidate = false;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        /** @deprecated */
        @Deprecated
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public int getViewAdapterPosition() {
            return this.mViewHolder.getAdapterPosition();
        }
    }

    public abstract static class ViewHolder {
        @NonNull
        public final View itemView;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mPosition = -1;
        int mOldPosition = -1;
        long mItemId = -1L;
        int mItemViewType = -1;
        int mPreLayoutPosition = -1;
        RecyclerView.ViewHolder mShadowedHolder = null;
        RecyclerView.ViewHolder mShadowingHolder = null;
        static final int FLAG_BOUND = 1;
        static final int FLAG_UPDATE = 2;
        static final int FLAG_INVALID = 4;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        int mFlags;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        List<Object> mPayloads = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        RecyclerView.Recycler mScrapContainer = null;
        boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;
        @VisibleForTesting
        int mPendingAccessibilityState = -1;
        RecyclerView mOwnerRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            } else {
                this.itemView = itemView;
            }
        }

        void flagRemovedAndOffsetPosition(int mNewPosition, int offset, boolean applyToPreLayout) {
            this.addFlags(8);
            this.offsetPosition(offset, applyToPreLayout);
            this.mPosition = mNewPosition;
        }

        void offsetPosition(int offset, boolean applyToPreLayout) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }

            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }

            if (applyToPreLayout) {
                this.mPreLayoutPosition += offset;
            }

            this.mPosition += offset;
            if (this.itemView.getLayoutParams() != null) {
                ((RecyclerView.LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
            }

        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }

        }

        boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        /** @deprecated */
        @Deprecated
        public final int getPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getLayoutPosition() {
            return this.mPreLayoutPosition == -1 ? this.mPosition : this.mPreLayoutPosition;
        }

        public final int getAdapterPosition() {
            return this.mOwnerRecyclerView == null ? -1 : this.mOwnerRecyclerView.getAdapterPositionFor(this);
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        boolean isScrap() {
            return this.mScrapContainer != null;
        }

        void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        void stopIgnoring() {
            this.mFlags &= -129;
        }

        void setScrapContainer(RecyclerView.Recycler recycler, boolean isChangeScrap) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = isChangeScrap;
        }

        boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        boolean hasAnyOfTheFlags(int flags) {
            return (this.mFlags & flags) != 0;
        }

        boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || this.isInvalid();
        }

        void setFlags(int flags, int mask) {
            this.mFlags = this.mFlags & ~mask | flags & mask;
        }

        void addFlags(int flags) {
            this.mFlags |= flags;
        }

        void addChangePayload(Object payload) {
            if (payload == null) {
                this.addFlags(1024);
            } else if ((this.mFlags & 1024) == 0) {
                this.createPayloadsIfNeeded();
                this.mPayloads.add(payload);
            }

        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }

        }

        void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }

            this.mFlags &= -1025;
        }

        List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) == 0) {
                return this.mPayloads != null && this.mPayloads.size() != 0 ? this.mUnmodifiedPayloads : FULLUPDATE_PAYLOADS;
            } else {
                return FULLUPDATE_PAYLOADS;
            }
        }

        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        void onEnteredHiddenState(RecyclerView parent) {
            if (this.mPendingAccessibilityState != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = this.mPendingAccessibilityState;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            }

            parent.setChildImportantForAccessibilityInternal(this, 4);
        }

        void onLeftHiddenState(RecyclerView parent) {
            parent.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (this.isScrap()) {
                sb.append(" scrap ").append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }

            if (this.isInvalid()) {
                sb.append(" invalid");
            }

            if (!this.isBound()) {
                sb.append(" unbound");
            }

            if (this.needsUpdate()) {
                sb.append(" update");
            }

            if (this.isRemoved()) {
                sb.append(" removed");
            }

            if (this.shouldIgnore()) {
                sb.append(" ignored");
            }

            if (this.isTmpDetached()) {
                sb.append(" tmpDetached");
            }

            if (!this.isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }

            if (this.isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }

            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }

            sb.append("}");
            return sb.toString();
        }

        public final void setIsRecyclable(boolean recyclable) {
            this.mIsRecyclableCount = recyclable ? this.mIsRecyclableCount - 1 : this.mIsRecyclableCount + 1;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!recyclable && this.mIsRecyclableCount == 1) {
                this.mFlags |= 16;
            } else if (recyclable && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }

        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
        }

        boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }
    }

    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(@NonNull View var1);

        void onChildViewDetachedFromWindow(@NonNull View var1);
    }

    public interface RecyclerListener {
        void onViewRecycled(@NonNull RecyclerView.ViewHolder var1);
    }

    public abstract static class OnScrollListener {
        public OnScrollListener() {
        }

        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        }

        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        }
    }

    public static class SimpleOnItemTouchListener implements RecyclerView.OnItemTouchListener {
        public SimpleOnItemTouchListener() {
        }

        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            return false;
        }

        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(@NonNull RecyclerView var1, @NonNull MotionEvent var2);

        void onTouchEvent(@NonNull RecyclerView var1, @NonNull MotionEvent var2);

        void onRequestDisallowInterceptTouchEvent(boolean var1);
    }

    public abstract static class ItemDecoration {
        public ItemDecoration() {
        }

        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            this.onDraw(c, parent);
        }

        /** @deprecated */
        @Deprecated
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent) {
        }

        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            this.onDrawOver(c, parent);
        }

        /** @deprecated */
        @Deprecated
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent) {
        }

        /** @deprecated */
        @Deprecated
        public void getItemOffsets(@NonNull Rect outRect, int itemPosition, @NonNull RecyclerView parent) {
            outRect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            this.getItemOffsets(outRect, ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), parent);
        }
    }

    public abstract static class LayoutManager {
        ChildHelper mChildHelper;
        RecyclerView mRecyclerView;
        private final android.support.v7.widget.ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new android.support.v7.widget.ViewBoundsCheck.Callback() {
            public int getChildCount() {
                return LayoutManager.this.getChildCount();
            }

            public View getParent() {
                return LayoutManager.this.mRecyclerView;
            }

            public View getChildAt(int index) {
                return LayoutManager.this.getChildAt(index);
            }

            public int getParentStart() {
                return LayoutManager.this.getPaddingLeft();
            }

            public int getParentEnd() {
                return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
            }

            public int getChildStart(View view) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
                return LayoutManager.this.getDecoratedLeft(view) - params.leftMargin;
            }

            public int getChildEnd(View view) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
                return LayoutManager.this.getDecoratedRight(view) + params.rightMargin;
            }
        };
        private final android.support.v7.widget.ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new android.support.v7.widget.ViewBoundsCheck.Callback() {
            public int getChildCount() {
                return LayoutManager.this.getChildCount();
            }

            public View getParent() {
                return LayoutManager.this.mRecyclerView;
            }

            public View getChildAt(int index) {
                return LayoutManager.this.getChildAt(index);
            }

            public int getParentStart() {
                return LayoutManager.this.getPaddingTop();
            }

            public int getParentEnd() {
                return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
            }

            public int getChildStart(View view) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
                return LayoutManager.this.getDecoratedTop(view) - params.topMargin;
            }

            public int getChildEnd(View view) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();
                return LayoutManager.this.getDecoratedBottom(view) + params.bottomMargin;
            }
        };
        ViewBoundsCheck mHorizontalBoundCheck;
        ViewBoundsCheck mVerticalBoundCheck;
        @Nullable
        RecyclerView.SmoothScroller mSmoothScroller;
        boolean mRequestedSimpleAnimations;
        boolean mIsAttachedToWindow;
        boolean mAutoMeasure;
        private boolean mMeasurementCacheEnabled;
        private boolean mItemPrefetchEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        private int mWidthMode;
        private int mHeightMode;
        private int mWidth;
        private int mHeight;

        public LayoutManager() {
            this.mHorizontalBoundCheck = new ViewBoundsCheck(this.mHorizontalBoundCheckCallback);
            this.mVerticalBoundCheck = new ViewBoundsCheck(this.mVerticalBoundCheckCallback);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }

            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        void setMeasureSpecs(int wSpec, int hSpec) {
            this.mWidth = MeasureSpec.getSize(wSpec);
            this.mWidthMode = MeasureSpec.getMode(wSpec);
            if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }

            this.mHeight = MeasureSpec.getSize(hSpec);
            this.mHeightMode = MeasureSpec.getMode(hSpec);
            if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }

        }

        void setMeasuredDimensionFromChildren(int widthSpec, int heightSpec) {
            int count = this.getChildCount();
            if (count == 0) {
                this.mRecyclerView.defaultOnMeasure(widthSpec, heightSpec);
            } else {
                int minX = 2147483647;
                int minY = 2147483647;
                int maxX = -2147483648;
                int maxY = -2147483648;

                for(int i = 0; i < count; ++i) {
                    View child = this.getChildAt(i);
                    Rect bounds = this.mRecyclerView.mTempRect;
                    this.getDecoratedBoundsWithMargins(child, bounds);
                    if (bounds.left < minX) {
                        minX = bounds.left;
                    }

                    if (bounds.right > maxX) {
                        maxX = bounds.right;
                    }

                    if (bounds.top < minY) {
                        minY = bounds.top;
                    }

                    if (bounds.bottom > maxY) {
                        maxY = bounds.bottom;
                    }
                }

                this.mRecyclerView.mTempRect.set(minX, minY, maxX, maxY);
                this.setMeasuredDimension(this.mRecyclerView.mTempRect, widthSpec, heightSpec);
            }
        }

        public void setMeasuredDimension(Rect childrenBounds, int wSpec, int hSpec) {
            int usedWidth = childrenBounds.width() + this.getPaddingLeft() + this.getPaddingRight();
            int usedHeight = childrenBounds.height() + this.getPaddingTop() + this.getPaddingBottom();
            int width = chooseSize(wSpec, usedWidth, this.getMinimumWidth());
            int height = chooseSize(hSpec, usedHeight, this.getMinimumHeight());
            this.setMeasuredDimension(width, height);
        }

        public void requestLayout() {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.requestLayout();
            }

        }

        public void assertInLayoutOrScroll(String message) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertInLayoutOrScroll(message);
            }

        }

        public static int chooseSize(int spec, int desired, int min) {
            int mode = MeasureSpec.getMode(spec);
            int size = MeasureSpec.getSize(spec);
            switch(mode) {
            case -2147483648:
                return Math.min(size, Math.max(desired, min));
            case 0:
            default:
                return Math.max(desired, min);
            case 1073741824:
                return size;
            }
        }

        public void assertNotInLayoutOrScroll(String message) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertNotInLayoutOrScroll(message);
            }

        }

        /** @deprecated */
        @Deprecated
        public void setAutoMeasureEnabled(boolean enabled) {
            this.mAutoMeasure = enabled;
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public final void setItemPrefetchEnabled(boolean enabled) {
            if (enabled != this.mItemPrefetchEnabled) {
                this.mItemPrefetchEnabled = enabled;
                this.mPrefetchMaxCountObserved = 0;
                if (this.mRecyclerView != null) {
                    this.mRecyclerView.mRecycler.updateViewCacheSize();
                }
            }

        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int adapterItemCount, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        void dispatchAttachedToWindow(RecyclerView view) {
            this.mIsAttachedToWindow = true;
            this.onAttachedToWindow(view);
        }

        void dispatchDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
            this.mIsAttachedToWindow = false;
            this.onDetachedFromWindow(view, recycler);
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public void postOnAnimation(Runnable action) {
            if (this.mRecyclerView != null) {
                ViewCompat.postOnAnimation(this.mRecyclerView, action);
            }

        }

        public boolean removeCallbacks(Runnable action) {
            return this.mRecyclerView != null ? this.mRecyclerView.removeCallbacks(action) : false;
        }

        @CallSuper
        public void onAttachedToWindow(RecyclerView view) {
        }

        /** @deprecated */
        @Deprecated
        public void onDetachedFromWindow(RecyclerView view) {
        }

        @CallSuper
        public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
            this.onDetachedFromWindow(view);
        }

        public boolean getClipToPadding() {
            return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
        }

        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(RecyclerView.State state) {
        }

        public abstract RecyclerView.LayoutParams generateDefaultLayoutParams();

        public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
            return lp != null;
        }

        public RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams lp) {
            if (lp instanceof RecyclerView.LayoutParams) {
                return new RecyclerView.LayoutParams((RecyclerView.LayoutParams)lp);
            } else {
                return lp instanceof MarginLayoutParams ? new RecyclerView.LayoutParams((MarginLayoutParams)lp) : new RecyclerView.LayoutParams(lp);
            }
        }

        public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
            return new RecyclerView.LayoutParams(c, attrs);
        }

        public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
            return 0;
        }

        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
            return 0;
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public void scrollToPosition(int position) {
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
            if (this.mSmoothScroller != null && smoothScroller != this.mSmoothScroller && this.mSmoothScroller.isRunning()) {
                this.mSmoothScroller.stop();
            }

            this.mSmoothScroller = smoothScroller;
            this.mSmoothScroller.start(this.mRecyclerView, this);
        }

        public boolean isSmoothScrolling() {
            return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.mRecyclerView);
        }

        public void endAnimation(View view) {
            if (this.mRecyclerView.mItemAnimator != null) {
                this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
            }

        }

        public void addDisappearingView(View child) {
            this.addDisappearingView(child, -1);
        }

        public void addDisappearingView(View child, int index) {
            this.addViewInt(child, index, true);
        }

        public void addView(View child) {
            this.addView(child, -1);
        }

        public void addView(View child, int index) {
            this.addViewInt(child, index, false);
        }

        private void addViewInt(View child, int index, boolean disappearing) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(child);
            if (!disappearing && !holder.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(holder);
            } else {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(holder);
            }

            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
            if (!holder.wasReturnedFromScrap() && !holder.isScrap()) {
                if (child.getParent() == this.mRecyclerView) {
                    int currentIndex = this.mChildHelper.indexOfChild(child);
                    if (index == -1) {
                        index = this.mChildHelper.getChildCount();
                    }

                    if (currentIndex == -1) {
                        throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(child) + this.mRecyclerView.exceptionLabel());
                    }

                    if (currentIndex != index) {
                        this.mRecyclerView.mLayout.moveView(currentIndex, index);
                    }
                } else {
                    this.mChildHelper.addView(child, index, false);
                    lp.mInsetsDirty = true;
                    if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                        this.mSmoothScroller.onChildAttachedToWindow(child);
                    }
                }
            } else {
                if (holder.isScrap()) {
                    holder.unScrap();
                } else {
                    holder.clearReturnedFromScrapFlag();
                }

                this.mChildHelper.attachViewToParent(child, index, child.getLayoutParams(), false);
            }

            if (lp.mPendingInvalidate) {
                holder.itemView.invalidate();
                lp.mPendingInvalidate = false;
            }

        }

        public void removeView(View child) {
            this.mChildHelper.removeView(child);
        }

        public void removeViewAt(int index) {
            View child = this.getChildAt(index);
            if (child != null) {
                this.mChildHelper.removeViewAt(index);
            }

        }

        public void removeAllViews() {
            int childCount = this.getChildCount();

            for(int i = childCount - 1; i >= 0; --i) {
                this.mChildHelper.removeViewAt(i);
            }

        }

        public int getBaseline() {
            return -1;
        }

        public int getPosition(@NonNull View view) {
            return ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getItemViewType(@NonNull View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        @Nullable
        public View findContainingItemView(@NonNull View view) {
            if (this.mRecyclerView == null) {
                return null;
            } else {
                View found = this.mRecyclerView.findContainingItemView(view);
                if (found == null) {
                    return null;
                } else {
                    return this.mChildHelper.isHidden(found) ? null : found;
                }
            }
        }

        @Nullable
        public View findViewByPosition(int position) {
            int childCount = this.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View child = this.getChildAt(i);
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
                if (vh != null && vh.getLayoutPosition() == position && !vh.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !vh.isRemoved())) {
                    return child;
                }
            }

            return null;
        }

        public void detachView(@NonNull View child) {
            int ind = this.mChildHelper.indexOfChild(child);
            if (ind >= 0) {
                this.detachViewInternal(ind, child);
            }

        }

        public void detachViewAt(int index) {
            this.detachViewInternal(index, this.getChildAt(index));
        }

        private void detachViewInternal(int index, @NonNull View view) {
            this.mChildHelper.detachViewFromParent(index);
        }

        public void attachView(@NonNull View child, int index, RecyclerView.LayoutParams lp) {
            RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(child);
            if (vh.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(vh);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(vh);
            }

            this.mChildHelper.attachViewToParent(child, index, lp, vh.isRemoved());
        }

        public void attachView(@NonNull View child, int index) {
            this.attachView(child, index, (RecyclerView.LayoutParams)child.getLayoutParams());
        }

        public void attachView(@NonNull View child) {
            this.attachView(child, -1);
        }

        public void removeDetachedView(@NonNull View child) {
            this.mRecyclerView.removeDetachedView(child, false);
        }

        public void moveView(int fromIndex, int toIndex) {
            View view = this.getChildAt(fromIndex);
            if (view == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + fromIndex + this.mRecyclerView.toString());
            } else {
                this.detachViewAt(fromIndex);
                this.attachView(view, toIndex);
            }
        }

        public void detachAndScrapView(@NonNull View child, @NonNull RecyclerView.Recycler recycler) {
            int index = this.mChildHelper.indexOfChild(child);
            this.scrapOrRecycleView(recycler, index, child);
        }

        public void detachAndScrapViewAt(int index, @NonNull RecyclerView.Recycler recycler) {
            View child = this.getChildAt(index);
            this.scrapOrRecycleView(recycler, index, child);
        }

        public void removeAndRecycleView(@NonNull View child, @NonNull RecyclerView.Recycler recycler) {
            this.removeView(child);
            recycler.recycleView(child);
        }

        public void removeAndRecycleViewAt(int index, @NonNull RecyclerView.Recycler recycler) {
            View view = this.getChildAt(index);
            this.removeViewAt(index);
            recycler.recycleView(view);
        }

        public int getChildCount() {
            return this.mChildHelper != null ? this.mChildHelper.getChildCount() : 0;
        }

        @Nullable
        public View getChildAt(int index) {
            return this.mChildHelper != null ? this.mChildHelper.getChildAt(index) : null;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        @Px
        public int getWidth() {
            return this.mWidth;
        }

        @Px
        public int getHeight() {
            return this.mHeight;
        }

        @Px
        public int getPaddingLeft() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingLeft() : 0;
        }

        @Px
        public int getPaddingTop() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingTop() : 0;
        }

        @Px
        public int getPaddingRight() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingRight() : 0;
        }

        @Px
        public int getPaddingBottom() {
            return this.mRecyclerView != null ? this.mRecyclerView.getPaddingBottom() : 0;
        }

        @Px
        public int getPaddingStart() {
            return this.mRecyclerView != null ? ViewCompat.getPaddingStart(this.mRecyclerView) : 0;
        }

        @Px
        public int getPaddingEnd() {
            return this.mRecyclerView != null ? ViewCompat.getPaddingEnd(this.mRecyclerView) : 0;
        }

        public boolean isFocused() {
            return this.mRecyclerView != null && this.mRecyclerView.isFocused();
        }

        public boolean hasFocus() {
            return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
        }

        @Nullable
        public View getFocusedChild() {
            if (this.mRecyclerView == null) {
                return null;
            } else {
                View focused = this.mRecyclerView.getFocusedChild();
                return focused != null && !this.mChildHelper.isHidden(focused) ? focused : null;
            }
        }

        public int getItemCount() {
            RecyclerView.Adapter a = this.mRecyclerView != null ? this.mRecyclerView.getAdapter() : null;
            return a != null ? a.getItemCount() : 0;
        }

        public void offsetChildrenHorizontal(@Px int dx) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenHorizontal(dx);
            }

        }

        public void offsetChildrenVertical(@Px int dy) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenVertical(dy);
            }

        }

        public void ignoreView(@NonNull View view) {
            if (view.getParent() == this.mRecyclerView && this.mRecyclerView.indexOfChild(view) != -1) {
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(view);
                vh.addFlags(128);
                this.mRecyclerView.mViewInfoStore.removeViewHolder(vh);
            } else {
                throw new IllegalArgumentException("View should be fully attached to be ignored" + this.mRecyclerView.exceptionLabel());
            }
        }

        public void stopIgnoringView(@NonNull View view) {
            RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(view);
            vh.stopIgnoring();
            vh.resetInternal();
            vh.addFlags(4);
        }

        public void detachAndScrapAttachedViews(@NonNull RecyclerView.Recycler recycler) {
            int childCount = this.getChildCount();

            for(int i = childCount - 1; i >= 0; --i) {
                View v = this.getChildAt(i);
                this.scrapOrRecycleView(recycler, i, v);
            }

        }

        private void scrapOrRecycleView(RecyclerView.Recycler recycler, int index, View view) {
            RecyclerView.ViewHolder viewHolder = RecyclerView.getChildViewHolderInt(view);
            if (!viewHolder.shouldIgnore()) {
                if (viewHolder.isInvalid() && !viewHolder.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
                    this.removeViewAt(index);
                    recycler.recycleViewHolderInternal(viewHolder);
                } else {
                    this.detachViewAt(index);
                    recycler.scrapView(view);
                    this.mRecyclerView.mViewInfoStore.onViewDetached(viewHolder);
                }

            }
        }

        void removeAndRecycleScrapInt(RecyclerView.Recycler recycler) {
            int scrapCount = recycler.getScrapCount();

            for(int i = scrapCount - 1; i >= 0; --i) {
                View scrap = recycler.getScrapViewAt(i);
                RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(scrap);
                if (!vh.shouldIgnore()) {
                    vh.setIsRecyclable(false);
                    if (vh.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrap, false);
                    }

                    if (this.mRecyclerView.mItemAnimator != null) {
                        this.mRecyclerView.mItemAnimator.endAnimation(vh);
                    }

                    vh.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(scrap);
                }
            }

            recycler.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }

        }

        public void measureChild(@NonNull View child, int widthUsed, int heightUsed) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
            Rect insets = this.mRecyclerView.getItemDecorInsetsForChild(child);
            widthUsed += insets.left + insets.right;
            heightUsed += insets.top + insets.bottom;
            int widthSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + widthUsed, lp.width, this.canScrollHorizontally());
            int heightSpec = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + heightUsed, lp.height, this.canScrollVertically());
            if (this.shouldMeasureChild(child, widthSpec, heightSpec, lp)) {
                child.measure(widthSpec, heightSpec);
            }

        }

        boolean shouldReMeasureChild(View child, int widthSpec, int heightSpec, RecyclerView.LayoutParams lp) {
            return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(child.getMeasuredWidth(), widthSpec, lp.width) || !isMeasurementUpToDate(child.getMeasuredHeight(), heightSpec, lp.height);
        }

        boolean shouldMeasureChild(View child, int widthSpec, int heightSpec, RecyclerView.LayoutParams lp) {
            return child.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(child.getWidth(), widthSpec, lp.width) || !isMeasurementUpToDate(child.getHeight(), heightSpec, lp.height);
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public void setMeasurementCacheEnabled(boolean measurementCacheEnabled) {
            this.mMeasurementCacheEnabled = measurementCacheEnabled;
        }

        private static boolean isMeasurementUpToDate(int childSize, int spec, int dimension) {
            int specMode = MeasureSpec.getMode(spec);
            int specSize = MeasureSpec.getSize(spec);
            if (dimension > 0 && childSize != dimension) {
                return false;
            } else {
                switch(specMode) {
                case -2147483648:
                    return specSize >= childSize;
                case 0:
                    return true;
                case 1073741824:
                    return specSize == childSize;
                default:
                    return false;
                }
            }
        }

        public void measureChildWithMargins(@NonNull View child, int widthUsed, int heightUsed) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
            Rect insets = this.mRecyclerView.getItemDecorInsetsForChild(child);
            widthUsed += insets.left + insets.right;
            heightUsed += insets.top + insets.bottom;
            int widthSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width, this.canScrollHorizontally());
            int heightSpec = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height, this.canScrollVertically());
            if (this.shouldMeasureChild(child, widthSpec, heightSpec, lp)) {
                child.measure(widthSpec, heightSpec);
            }

        }

        /** @deprecated */
        @Deprecated
        public static int getChildMeasureSpec(int parentSize, int padding, int childDimension, boolean canScroll) {
            int size = Math.max(0, parentSize - padding);
            int resultSize = 0;
            int resultMode = 0;
            if (canScroll) {
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = 1073741824;
                } else {
                    resultSize = 0;
                    resultMode = 0;
                }
            } else if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = 1073741824;
            } else if (childDimension == -1) {
                resultSize = size;
                resultMode = 1073741824;
            } else if (childDimension == -2) {
                resultSize = size;
                resultMode = -2147483648;
            }

            return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
        }

        public static int getChildMeasureSpec(int parentSize, int parentMode, int padding, int childDimension, boolean canScroll) {
            int size = Math.max(0, parentSize - padding);
            int resultSize = 0;
            int resultMode = 0;
            if (canScroll) {
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = 1073741824;
                } else if (childDimension == -1) {
                    switch(parentMode) {
                    case -2147483648:
                    case 1073741824:
                        resultSize = size;
                        resultMode = parentMode;
                        break;
                    case 0:
                        resultSize = 0;
                        resultMode = 0;
                    }
                } else if (childDimension == -2) {
                    resultSize = 0;
                    resultMode = 0;
                }
            } else if (childDimension >= 0) {
                resultSize = childDimension;
                resultMode = 1073741824;
            } else if (childDimension == -1) {
                resultSize = size;
                resultMode = parentMode;
            } else if (childDimension == -2) {
                resultSize = size;
                if (parentMode != -2147483648 && parentMode != 1073741824) {
                    resultMode = 0;
                } else {
                    resultMode = -2147483648;
                }
            }

            return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
        }

        public int getDecoratedMeasuredWidth(@NonNull View child) {
            Rect insets = ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets;
            return child.getMeasuredWidth() + insets.left + insets.right;
        }

        public int getDecoratedMeasuredHeight(@NonNull View child) {
            Rect insets = ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets;
            return child.getMeasuredHeight() + insets.top + insets.bottom;
        }

        public void layoutDecorated(@NonNull View child, int left, int top, int right, int bottom) {
            Rect insets = ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets;
            child.layout(left + insets.left, top + insets.top, right - insets.right, bottom - insets.bottom);
        }

        public void layoutDecoratedWithMargins(@NonNull View child, int left, int top, int right, int bottom) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
            Rect insets = lp.mDecorInsets;
            child.layout(left + insets.left + lp.leftMargin, top + insets.top + lp.topMargin, right - insets.right - lp.rightMargin, bottom - insets.bottom - lp.bottomMargin);
        }

        public void getTransformedBoundingBox(@NonNull View child, boolean includeDecorInsets, @NonNull Rect out) {
            if (includeDecorInsets) {
                Rect insets = ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets;
                out.set(-insets.left, -insets.top, child.getWidth() + insets.right, child.getHeight() + insets.bottom);
            } else {
                out.set(0, 0, child.getWidth(), child.getHeight());
            }

            if (this.mRecyclerView != null) {
                Matrix childMatrix = child.getMatrix();
                if (childMatrix != null && !childMatrix.isIdentity()) {
                    RectF tempRectF = this.mRecyclerView.mTempRectF;
                    tempRectF.set(out);
                    childMatrix.mapRect(tempRectF);
                    out.set((int)Math.floor((double)tempRectF.left), (int)Math.floor((double)tempRectF.top), (int)Math.ceil((double)tempRectF.right), (int)Math.ceil((double)tempRectF.bottom));
                }
            }

            out.offset(child.getLeft(), child.getTop());
        }

        public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect outBounds) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, outBounds);
        }

        public int getDecoratedLeft(@NonNull View child) {
            return child.getLeft() - this.getLeftDecorationWidth(child);
        }

        public int getDecoratedTop(@NonNull View child) {
            return child.getTop() - this.getTopDecorationHeight(child);
        }

        public int getDecoratedRight(@NonNull View child) {
            return child.getRight() + this.getRightDecorationWidth(child);
        }

        public int getDecoratedBottom(@NonNull View child) {
            return child.getBottom() + this.getBottomDecorationHeight(child);
        }

        public void calculateItemDecorationsForChild(@NonNull View child, @NonNull Rect outRect) {
            if (this.mRecyclerView == null) {
                outRect.set(0, 0, 0, 0);
            } else {
                Rect insets = this.mRecyclerView.getItemDecorInsetsForChild(child);
                outRect.set(insets);
            }
        }

        public int getTopDecorationHeight(@NonNull View child) {
            return ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets.top;
        }

        public int getBottomDecorationHeight(@NonNull View child) {
            return ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets.bottom;
        }

        public int getLeftDecorationWidth(@NonNull View child) {
            return ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets.left;
        }

        public int getRightDecorationWidth(@NonNull View child) {
            return ((RecyclerView.LayoutParams)child.getLayoutParams()).mDecorInsets.right;
        }

        @Nullable
        public View onFocusSearchFailed(@NonNull View focused, int direction, @NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
            return null;
        }

        @Nullable
        public View onInterceptFocusSearch(@NonNull View focused, int direction) {
            return null;
        }

        private int[] getChildRectangleOnScreenScrollAmount(RecyclerView parent, View child, Rect rect, boolean immediate) {
            int[] out = new int[2];
            int parentLeft = this.getPaddingLeft();
            int parentTop = this.getPaddingTop();
            int parentRight = this.getWidth() - this.getPaddingRight();
            int parentBottom = this.getHeight() - this.getPaddingBottom();
            int childLeft = child.getLeft() + rect.left - child.getScrollX();
            int childTop = child.getTop() + rect.top - child.getScrollY();
            int childRight = childLeft + rect.width();
            int childBottom = childTop + rect.height();
            int offScreenLeft = Math.min(0, childLeft - parentLeft);
            int offScreenTop = Math.min(0, childTop - parentTop);
            int offScreenRight = Math.max(0, childRight - parentRight);
            int offScreenBottom = Math.max(0, childBottom - parentBottom);
            int dx;
            if (this.getLayoutDirection() == 1) {
                dx = offScreenRight != 0 ? offScreenRight : Math.max(offScreenLeft, childRight - parentRight);
            } else {
                dx = offScreenLeft != 0 ? offScreenLeft : Math.min(childLeft - parentLeft, offScreenRight);
            }

            int dy = offScreenTop != 0 ? offScreenTop : Math.min(childTop - parentTop, offScreenBottom);
            out[0] = dx;
            out[1] = dy;
            return out;
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView parent, @NonNull View child, @NonNull Rect rect, boolean immediate) {
            return this.requestChildRectangleOnScreen(parent, child, rect, immediate, false);
        }

        public boolean requestChildRectangleOnScreen(@NonNull RecyclerView parent, @NonNull View child, @NonNull Rect rect, boolean immediate, boolean focusedChildVisible) {
            int[] scrollAmount = this.getChildRectangleOnScreenScrollAmount(parent, child, rect, immediate);
            int dx = scrollAmount[0];
            int dy = scrollAmount[1];
            if ((!focusedChildVisible || this.isFocusedChildVisibleAfterScrolling(parent, dx, dy)) && (dx != 0 || dy != 0)) {
                if (immediate) {
                    parent.scrollBy(dx, dy);
                } else {
                    parent.smoothScrollBy(dx, dy);
                }

                return true;
            } else {
                return false;
            }
        }

        public boolean isViewPartiallyVisible(@NonNull View child, boolean completelyVisible, boolean acceptEndPointInclusion) {
            int boundsFlag = 24579;
            boolean isViewFullyVisible = this.mHorizontalBoundCheck.isViewWithinBoundFlags(child, boundsFlag) && this.mVerticalBoundCheck.isViewWithinBoundFlags(child, boundsFlag);
            if (completelyVisible) {
                return isViewFullyVisible;
            } else {
                return !isViewFullyVisible;
            }
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView parent, int dx, int dy) {
            View focusedChild = parent.getFocusedChild();
            if (focusedChild == null) {
                return false;
            } else {
                int parentLeft = this.getPaddingLeft();
                int parentTop = this.getPaddingTop();
                int parentRight = this.getWidth() - this.getPaddingRight();
                int parentBottom = this.getHeight() - this.getPaddingBottom();
                Rect bounds = this.mRecyclerView.mTempRect;
                this.getDecoratedBoundsWithMargins(focusedChild, bounds);
                return bounds.left - dx < parentRight && bounds.right - dx > parentLeft && bounds.top - dy < parentBottom && bounds.bottom - dy > parentTop;
            }
        }

        /** @deprecated */
        @Deprecated
        public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull View child, @Nullable View focused) {
            return this.isSmoothScrolling() || parent.isComputingLayout();
        }

        public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull RecyclerView.State state, @NonNull View child, @Nullable View focused) {
            return this.onRequestChildFocus(parent, child, focused);
        }

        public void onAdapterChanged(@Nullable RecyclerView.Adapter oldAdapter, @Nullable RecyclerView.Adapter newAdapter) {
        }

        public boolean onAddFocusables(@NonNull RecyclerView recyclerView, @NonNull ArrayList<View> views, int direction, int focusableMode) {
            return false;
        }

        public void onItemsChanged(@NonNull RecyclerView recyclerView) {
        }

        public void onItemsAdded(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsRemoved(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int positionStart, int itemCount) {
        }

        public void onItemsUpdated(@NonNull RecyclerView recyclerView, int positionStart, int itemCount, @Nullable Object payload) {
            this.onItemsUpdated(recyclerView, positionStart, itemCount);
        }

        public void onItemsMoved(@NonNull RecyclerView recyclerView, int from, int to, int itemCount) {
        }

        public int computeHorizontalScrollExtent(@NonNull RecyclerView.State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(@NonNull RecyclerView.State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(@NonNull RecyclerView.State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(@NonNull RecyclerView.State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(@NonNull RecyclerView.State state) {
            return 0;
        }

        public int computeVerticalScrollRange(@NonNull RecyclerView.State state) {
            return 0;
        }

        public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
            this.mRecyclerView.defaultOnMeasure(widthSpec, heightSpec);
        }

        public void setMeasuredDimension(int widthSize, int heightSize) {
            this.mRecyclerView.setMeasuredDimension(widthSize, heightSize);
        }

        @Px
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        @Px
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        @Nullable
        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onRestoreInstanceState(Parcelable state) {
        }

        void stopSmoothScroller() {
            if (this.mSmoothScroller != null) {
                this.mSmoothScroller.stop();
            }

        }

        void onSmoothScrollerStopped(RecyclerView.SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }

        }

        public void onScrollStateChanged(int state) {
        }

        public void removeAndRecycleAllViews(@NonNull RecyclerView.Recycler recycler) {
            for(int i = this.getChildCount() - 1; i >= 0; --i) {
                View view = this.getChildAt(i);
                if (!RecyclerView.getChildViewHolderInt(view).shouldIgnore()) {
                    this.removeAndRecycleViewAt(i, recycler);
                }
            }

        }

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat info) {
            this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, info);
        }

        public void onInitializeAccessibilityNodeInfo(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull AccessibilityNodeInfoCompat info) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                info.addAction(8192);
                info.setScrollable(true);
            }

            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                info.addAction(4096);
                info.setScrollable(true);
            }

            CollectionInfoCompat collectionInfo = CollectionInfoCompat.obtain(this.getRowCountForAccessibility(recycler, state), this.getColumnCountForAccessibility(recycler, state), this.isLayoutHierarchical(recycler, state), this.getSelectionModeForAccessibility(recycler, state));
            info.setCollectionInfo(collectionInfo);
        }

        public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent event) {
            this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, event);
        }

        public void onInitializeAccessibilityEvent(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull AccessibilityEvent event) {
            if (this.mRecyclerView != null && event != null) {
                event.setScrollable(this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1) || this.mRecyclerView.canScrollHorizontally(1));
                if (this.mRecyclerView.mAdapter != null) {
                    event.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }

            }
        }

        void onInitializeAccessibilityNodeInfoForItem(View host, AccessibilityNodeInfoCompat info) {
            RecyclerView.ViewHolder vh = RecyclerView.getChildViewHolderInt(host);
            if (vh != null && !vh.isRemoved() && !this.mChildHelper.isHidden(vh.itemView)) {
                this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, host, info);
            }

        }

        public void onInitializeAccessibilityNodeInfoForItem(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull View host, @NonNull AccessibilityNodeInfoCompat info) {
            int rowIndexGuess = this.canScrollVertically() ? this.getPosition(host) : 0;
            int columnIndexGuess = this.canScrollHorizontally() ? this.getPosition(host) : 0;
            CollectionItemInfoCompat itemInfo = CollectionItemInfoCompat.obtain(rowIndexGuess, 1, columnIndexGuess, 1, false, false);
            info.setCollectionItemInfo(itemInfo);
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int getSelectionModeForAccessibility(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
            return 0;
        }

        public int getRowCountForAccessibility(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null) {
                return this.canScrollVertically() ? this.mRecyclerView.mAdapter.getItemCount() : 1;
            } else {
                return 1;
            }
        }

        public int getColumnCountForAccessibility(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null) {
                return this.canScrollHorizontally() ? this.mRecyclerView.mAdapter.getItemCount() : 1;
            } else {
                return 1;
            }
        }

        public boolean isLayoutHierarchical(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
            return false;
        }

        boolean performAccessibilityAction(int action, @Nullable Bundle args) {
            return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, action, args);
        }

        public boolean performAccessibilityAction(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int action, @Nullable Bundle args) {
            if (this.mRecyclerView == null) {
                return false;
            } else {
                int vScroll = 0;
                int hScroll = 0;
                switch(action) {
                case 4096:
                    if (this.mRecyclerView.canScrollVertically(1)) {
                        vScroll = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                    }

                    if (this.mRecyclerView.canScrollHorizontally(1)) {
                        hScroll = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                    }
                    break;
                case 8192:
                    if (this.mRecyclerView.canScrollVertically(-1)) {
                        vScroll = -(this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
                    }

                    if (this.mRecyclerView.canScrollHorizontally(-1)) {
                        hScroll = -(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
                    }
                }

                if (vScroll == 0 && hScroll == 0) {
                    return false;
                } else {
                    this.mRecyclerView.smoothScrollBy(hScroll, vScroll);
                    return true;
                }
            }
        }

        boolean performAccessibilityActionForItem(@NonNull View view, int action, @Nullable Bundle args) {
            return this.performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, action, args);
        }

        public boolean performAccessibilityActionForItem(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, @NonNull View view, int action, @Nullable Bundle args) {
            return false;
        }

        public static RecyclerView.LayoutManager.Properties getProperties(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            RecyclerView.LayoutManager.Properties properties = new RecyclerView.LayoutManager.Properties();
            TypedArray a = context.obtainStyledAttributes(attrs, styleable.RecyclerView, defStyleAttr, defStyleRes);
            properties.orientation = a.getInt(styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = a.getInt(styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = a.getBoolean(styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = a.getBoolean(styleable.RecyclerView_stackFromEnd, false);
            a.recycle();
            return properties;
        }

        void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            this.setMeasureSpecs(MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        boolean shouldMeasureTwice() {
            return false;
        }

        boolean hasFlexibleChildInBothOrientations() {
            int childCount = this.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View child = this.getChildAt(i);
                android.view.ViewGroup.LayoutParams lp = child.getLayoutParams();
                if (lp.width < 0 && lp.height < 0) {
                    return true;
                }
            }

            return false;
        }

        public static class Properties {
            public int orientation;
            public int spanCount;
            public boolean reverseLayout;
            public boolean stackFromEnd;

            public Properties() {
            }
        }

        public interface LayoutPrefetchRegistry {
            void addPosition(int var1, int var2);
        }
    }

    public abstract static class Adapter<VH extends RecyclerView.ViewHolder> {
        private final RecyclerView.AdapterDataObservable mObservable = new RecyclerView.AdapterDataObservable();
        private boolean mHasStableIds = false;

        public Adapter() {
        }

        @NonNull
        public abstract VH onCreateViewHolder(@NonNull ViewGroup var1, int var2);

        public abstract void onBindViewHolder(@NonNull VH var1, int var2);

        public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
            this.onBindViewHolder(holder, position);
        }

        @NonNull
        public final VH createViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder var4;
            try {
                TraceCompat.beginSection("RV CreateView");
                VH holder = this.onCreateViewHolder(parent, viewType);
                if (holder.itemView.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }

                holder.mItemViewType = viewType;
                var4 = holder;
            } finally {
                TraceCompat.endSection();
            }

            return var4;
        }

        public final void bindViewHolder(@NonNull VH holder, int position) {
            holder.mPosition = position;
            if (this.hasStableIds()) {
                holder.mItemId = this.getItemId(position);
            }

            holder.setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            this.onBindViewHolder(holder, position, holder.getUnmodifiedPayloads());
            holder.clearPayload();
            android.view.ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                ((RecyclerView.LayoutParams)layoutParams).mInsetsDirty = true;
            }

            TraceCompat.endSection();
        }

        public int getItemViewType(int position) {
            return 0;
        }

        public void setHasStableIds(boolean hasStableIds) {
            if (this.hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            } else {
                this.mHasStableIds = hasStableIds;
            }
        }

        public long getItemId(int position) {
            return -1L;
        }

        public abstract int getItemCount();

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public void onViewRecycled(@NonNull VH holder) {
        }

        public boolean onFailedToRecycleView(@NonNull VH holder) {
            return false;
        }

        public void onViewAttachedToWindow(@NonNull VH holder) {
        }

        public void onViewDetachedFromWindow(@NonNull VH holder) {
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
            this.mObservable.registerObserver(observer);
        }

        public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
            this.mObservable.unregisterObserver(observer);
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int position) {
            this.mObservable.notifyItemRangeChanged(position, 1);
        }

        public final void notifyItemChanged(int position, @Nullable Object payload) {
            this.mObservable.notifyItemRangeChanged(position, 1, payload);
        }

        public final void notifyItemRangeChanged(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeChanged(positionStart, itemCount);
        }

        public final void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            this.mObservable.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        public final void notifyItemInserted(int position) {
            this.mObservable.notifyItemRangeInserted(position, 1);
        }

        public final void notifyItemMoved(int fromPosition, int toPosition) {
            this.mObservable.notifyItemMoved(fromPosition, toPosition);
        }

        public final void notifyItemRangeInserted(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeInserted(positionStart, itemCount);
        }

        public final void notifyItemRemoved(int position) {
            this.mObservable.notifyItemRangeRemoved(position, 1);
        }

        public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
            this.mObservable.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

    public abstract static class ViewCacheExtension {
        public ViewCacheExtension() {
        }

        @Nullable
        public abstract View getViewForPositionAndType(@NonNull RecyclerView.Recycler var1, int var2, int var3);
    }

    public final class Recycler {
        final ArrayList<RecyclerView.ViewHolder> mAttachedScrap = new ArrayList();
        ArrayList<RecyclerView.ViewHolder> mChangedScrap = null;
        final ArrayList<RecyclerView.ViewHolder> mCachedViews = new ArrayList();
        private final List<RecyclerView.ViewHolder> mUnmodifiableAttachedScrap;
        private int mRequestedCacheMax;
        int mViewCacheMax;
        RecyclerView.RecycledViewPool mRecyclerPool;
        private RecyclerView.ViewCacheExtension mViewCacheExtension;
        static final int DEFAULT_CACHE_SIZE = 2;

        public Recycler() {
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(this.mAttachedScrap);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        public void clear() {
            this.mAttachedScrap.clear();
            this.recycleAndClearCachedViews();
        }

        public void setViewCacheSize(int viewCount) {
            this.mRequestedCacheMax = viewCount;
            this.updateViewCacheSize();
        }

        void updateViewCacheSize() {
            int extraCache = RecyclerView.this.mLayout != null ? RecyclerView.this.mLayout.mPrefetchMaxCountObserved : 0;
            this.mViewCacheMax = this.mRequestedCacheMax + extraCache;

            for(int i = this.mCachedViews.size() - 1; i >= 0 && this.mCachedViews.size() > this.mViewCacheMax; --i) {
                this.recycleCachedViewAt(i);
            }

        }

        @NonNull
        public List<RecyclerView.ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        boolean validateViewHolderForOffsetPosition(RecyclerView.ViewHolder holder) {
            if (holder.isRemoved()) {
                return RecyclerView.this.mState.isPreLayout();
            } else if (holder.mPosition >= 0 && holder.mPosition < RecyclerView.this.mAdapter.getItemCount()) {
                if (!RecyclerView.this.mState.isPreLayout()) {
                    int type = RecyclerView.this.mAdapter.getItemViewType(holder.mPosition);
                    if (type != holder.getItemViewType()) {
                        return false;
                    }
                }

                if (RecyclerView.this.mAdapter.hasStableIds()) {
                    return holder.getItemId() == RecyclerView.this.mAdapter.getItemId(holder.mPosition);
                } else {
                    return true;
                }
            } else {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + holder + RecyclerView.this.exceptionLabel());
            }
        }

        private boolean tryBindViewHolderByDeadline(@NonNull RecyclerView.ViewHolder holder, int offsetPosition, int position, long deadlineNs) {
            holder.mOwnerRecyclerView = RecyclerView.this;
            int viewType = holder.getItemViewType();
            long startBindNs = RecyclerView.this.getNanoTime();
            if (deadlineNs != 9223372036854775807L && !this.mRecyclerPool.willBindInTime(viewType, startBindNs, deadlineNs)) {
                return false;
            } else {
                RecyclerView.this.mAdapter.bindViewHolder(holder, offsetPosition);
                long endBindNs = RecyclerView.this.getNanoTime();
                this.mRecyclerPool.factorInBindTime(holder.getItemViewType(), endBindNs - startBindNs);
                this.attachAccessibilityDelegateOnBind(holder);
                if (RecyclerView.this.mState.isPreLayout()) {
                    holder.mPreLayoutPosition = position;
                }

                return true;
            }
        }

        public void bindViewToPosition(@NonNull View view, int position) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            if (holder == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + RecyclerView.this.exceptionLabel());
            } else {
                int offsetPosition = RecyclerView.this.mAdapterHelper.findPositionOffset(position);
                if (offsetPosition >= 0 && offsetPosition < RecyclerView.this.mAdapter.getItemCount()) {
                    this.tryBindViewHolderByDeadline(holder, offsetPosition, position, 9223372036854775807L);
                    android.view.ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                    RecyclerView.LayoutParams rvLayoutParams;
                    if (lp == null) {
                        rvLayoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                        holder.itemView.setLayoutParams(rvLayoutParams);
                    } else if (!RecyclerView.this.checkLayoutParams(lp)) {
                        rvLayoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateLayoutParams(lp);
                        holder.itemView.setLayoutParams(rvLayoutParams);
                    } else {
                        rvLayoutParams = (RecyclerView.LayoutParams)lp;
                    }

                    rvLayoutParams.mInsetsDirty = true;
                    rvLayoutParams.mViewHolder = holder;
                    rvLayoutParams.mPendingInvalidate = holder.itemView.getParent() == null;
                } else {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + position + "(offset:" + offsetPosition + ")." + "state:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
                }
            }
        }

        public int convertPreLayoutPositionToPostLayout(int position) {
            if (position >= 0 && position < RecyclerView.this.mState.getItemCount()) {
                return !RecyclerView.this.mState.isPreLayout() ? position : RecyclerView.this.mAdapterHelper.findPositionOffset(position);
            } else {
                throw new IndexOutOfBoundsException("invalid position " + position + ". State " + "item count is " + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
        }

        @NonNull
        public View getViewForPosition(int position) {
            return this.getViewForPosition(position, false);
        }

        View getViewForPosition(int position, boolean dryRun) {
            return this.tryGetViewHolderForPositionByDeadline(position, dryRun, 9223372036854775807L).itemView;
        }

        @Nullable
        RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int position, boolean dryRun, long deadlineNs) {
            if (position >= 0 && position < RecyclerView.this.mState.getItemCount()) {
                boolean fromScrapOrHiddenOrCache = false;
                RecyclerView.ViewHolder holder = null;
                if (RecyclerView.this.mState.isPreLayout()) {
                    holder = this.getChangedScrapViewForPosition(position);
                    fromScrapOrHiddenOrCache = holder != null;
                }

                if (holder == null) {
                    holder = this.getScrapOrHiddenOrCachedHolderForPosition(position, dryRun);
                    if (holder != null) {
                        if (!this.validateViewHolderForOffsetPosition(holder)) {
                            if (!dryRun) {
                                holder.addFlags(4);
                                if (holder.isScrap()) {
                                    RecyclerView.this.removeDetachedView(holder.itemView, false);
                                    holder.unScrap();
                                } else if (holder.wasReturnedFromScrap()) {
                                    holder.clearReturnedFromScrapFlag();
                                }

                                this.recycleViewHolderInternal(holder);
                            }

                            holder = null;
                        } else {
                            fromScrapOrHiddenOrCache = true;
                        }
                    }
                }

                int offsetPosition;
                int type;
                if (holder == null) {
                    offsetPosition = RecyclerView.this.mAdapterHelper.findPositionOffset(position);
                    if (offsetPosition < 0 || offsetPosition >= RecyclerView.this.mAdapter.getItemCount()) {
                        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + position + "(offset:" + offsetPosition + ")." + "state:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
                    }

                    type = RecyclerView.this.mAdapter.getItemViewType(offsetPosition);
                    if (RecyclerView.this.mAdapter.hasStableIds()) {
                        holder = this.getScrapOrCachedViewForId(RecyclerView.this.mAdapter.getItemId(offsetPosition), type, dryRun);
                        if (holder != null) {
                            holder.mPosition = offsetPosition;
                            fromScrapOrHiddenOrCache = true;
                        }
                    }

                    if (holder == null && this.mViewCacheExtension != null) {
                        View view = this.mViewCacheExtension.getViewForPositionAndType(this, position, type);
                        if (view != null) {
                            holder = RecyclerView.this.getChildViewHolder(view);
                            if (holder == null) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + RecyclerView.this.exceptionLabel());
                            }

                            if (holder.shouldIgnore()) {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + RecyclerView.this.exceptionLabel());
                            }
                        }
                    }

                    if (holder == null) {
                        holder = this.getRecycledViewPool().getRecycledView(type);
                        if (holder != null) {
                            holder.resetInternal();
                            if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                                this.invalidateDisplayListInt(holder);
                            }
                        }
                    }

                    if (holder == null) {
                        long start = RecyclerView.this.getNanoTime();
                        if (deadlineNs != 9223372036854775807L && !this.mRecyclerPool.willCreateInTime(type, start, deadlineNs)) {
                            return null;
                        }

                        holder = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, type);
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                            RecyclerView innerView = RecyclerView.findNestedRecyclerView(holder.itemView);
                            if (innerView != null) {
                                holder.mNestedRecyclerView = new WeakReference(innerView);
                            }
                        }

                        long end = RecyclerView.this.getNanoTime();
                        this.mRecyclerPool.factorInCreateTime(type, end - start);
                    }
                }

                if (fromScrapOrHiddenOrCache && !RecyclerView.this.mState.isPreLayout() && holder.hasAnyOfTheFlags(8192)) {
                    holder.setFlags(0, 8192);
                    if (RecyclerView.this.mState.mRunSimpleAnimations) {
                        offsetPosition = RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(holder);
                        offsetPosition |= 4096;
                        RecyclerView.ItemAnimator.ItemHolderInfo info = RecyclerView.this.mItemAnimator.recordPreLayoutInformation(RecyclerView.this.mState, holder, offsetPosition, holder.getUnmodifiedPayloads());
                        RecyclerView.this.recordAnimationInfoIfBouncedHiddenView(holder, info);
                    }
                }

                boolean bound = false;
                if (RecyclerView.this.mState.isPreLayout() && holder.isBound()) {
                    holder.mPreLayoutPosition = position;
                } else if (!holder.isBound() || holder.needsUpdate() || holder.isInvalid()) {
                    type = RecyclerView.this.mAdapterHelper.findPositionOffset(position);
                    bound = this.tryBindViewHolderByDeadline(holder, type, position, deadlineNs);
                }

                android.view.ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                RecyclerView.LayoutParams rvLayoutParams;
                if (lp == null) {
                    rvLayoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                    holder.itemView.setLayoutParams(rvLayoutParams);
                } else if (!RecyclerView.this.checkLayoutParams(lp)) {
                    rvLayoutParams = (RecyclerView.LayoutParams)RecyclerView.this.generateLayoutParams(lp);
                    holder.itemView.setLayoutParams(rvLayoutParams);
                } else {
                    rvLayoutParams = (RecyclerView.LayoutParams)lp;
                }

                rvLayoutParams.mViewHolder = holder;
                rvLayoutParams.mPendingInvalidate = fromScrapOrHiddenOrCache && bound;
                return holder;
            } else {
                throw new IndexOutOfBoundsException("Invalid item position " + position + "(" + position + "). Item count:" + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
        }

        private void attachAccessibilityDelegateOnBind(RecyclerView.ViewHolder holder) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                View itemView = holder.itemView;
                if (ViewCompat.getImportantForAccessibility(itemView) == 0) {
                    ViewCompat.setImportantForAccessibility(itemView, 1);
                }

                if (!ViewCompat.hasAccessibilityDelegate(itemView)) {
                    holder.addFlags(16384);
                    ViewCompat.setAccessibilityDelegate(itemView, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
                }
            }

        }

        private void invalidateDisplayListInt(RecyclerView.ViewHolder holder) {
            if (holder.itemView instanceof ViewGroup) {
                this.invalidateDisplayListInt((ViewGroup)holder.itemView, false);
            }

        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean invalidateThis) {
            int visibility;
            for(visibility = viewGroup.getChildCount() - 1; visibility >= 0; --visibility) {
                View view = viewGroup.getChildAt(visibility);
                if (view instanceof ViewGroup) {
                    this.invalidateDisplayListInt((ViewGroup)view, true);
                }
            }

            if (invalidateThis) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }

            }
        }

        public void recycleView(@NonNull View view) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            if (holder.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }

            if (holder.isScrap()) {
                holder.unScrap();
            } else if (holder.wasReturnedFromScrap()) {
                holder.clearReturnedFromScrapFlag();
            }

            this.recycleViewHolderInternal(holder);
        }

        void recycleViewInternal(View view) {
            this.recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }

        void recycleAndClearCachedViews() {
            int count = this.mCachedViews.size();

            for(int i = count - 1; i >= 0; --i) {
                this.recycleCachedViewAt(i);
            }

            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
            }

        }

        void recycleCachedViewAt(int cachedViewIndex) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)this.mCachedViews.get(cachedViewIndex);
            this.addViewHolderToRecycledViewPool(viewHolder, true);
            this.mCachedViews.remove(cachedViewIndex);
        }

        void recycleViewHolderInternal(RecyclerView.ViewHolder holder) {
            if (!holder.isScrap() && holder.itemView.getParent() == null) {
                if (holder.isTmpDetached()) {
                    throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + holder + RecyclerView.this.exceptionLabel());
                } else if (holder.shouldIgnore()) {
                    throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.exceptionLabel());
                } else {
                    boolean transientStatePreventsRecycling = holder.doesTransientStatePreventRecycling();
                    boolean forceRecycle = RecyclerView.this.mAdapter != null && transientStatePreventsRecycling && RecyclerView.this.mAdapter.onFailedToRecycleView(holder);
                    boolean cached = false;
                    boolean recycled = false;
                    if (forceRecycle || holder.isRecyclable()) {
                        if (this.mViewCacheMax > 0 && !holder.hasAnyOfTheFlags(526)) {
                            int cachedViewSize = this.mCachedViews.size();
                            if (cachedViewSize >= this.mViewCacheMax && cachedViewSize > 0) {
                                this.recycleCachedViewAt(0);
                                --cachedViewSize;
                            }

                            int targetCacheIndex = cachedViewSize;
                            if (RecyclerView.ALLOW_THREAD_GAP_WORK && cachedViewSize > 0 && !RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(holder.mPosition)) {
                                int cacheIndex;
                                for(cacheIndex = cachedViewSize - 1; cacheIndex >= 0; --cacheIndex) {
                                    int cachedPos = ((RecyclerView.ViewHolder)this.mCachedViews.get(cacheIndex)).mPosition;
                                    if (!RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(cachedPos)) {
                                        break;
                                    }
                                }

                                targetCacheIndex = cacheIndex + 1;
                            }

                            this.mCachedViews.add(targetCacheIndex, holder);
                            cached = true;
                        }

                        if (!cached) {
                            this.addViewHolderToRecycledViewPool(holder, true);
                            recycled = true;
                        }
                    }

                    RecyclerView.this.mViewInfoStore.removeViewHolder(holder);
                    if (!cached && !recycled && transientStatePreventsRecycling) {
                        holder.mOwnerRecyclerView = null;
                    }

                }
            } else {
                throw new IllegalArgumentException("Scrapped or attached views may not be recycled. isScrap:" + holder.isScrap() + " isAttached:" + (holder.itemView.getParent() != null) + RecyclerView.this.exceptionLabel());
            }
        }

        void addViewHolderToRecycledViewPool(@NonNull RecyclerView.ViewHolder holder, boolean dispatchRecycled) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(holder);
            if (holder.hasAnyOfTheFlags(16384)) {
                holder.setFlags(0, 16384);
                ViewCompat.setAccessibilityDelegate(holder.itemView, (AccessibilityDelegateCompat)null);
            }

            if (dispatchRecycled) {
                this.dispatchViewRecycled(holder);
            }

            holder.mOwnerRecyclerView = null;
            this.getRecycledViewPool().putRecycledView(holder);
        }

        void quickRecycleScrapView(View view) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            holder.mScrapContainer = null;
            holder.mInChangeScrap = false;
            holder.clearReturnedFromScrapFlag();
            this.recycleViewHolderInternal(holder);
        }

        void scrapView(View view) {
            RecyclerView.ViewHolder holder = RecyclerView.getChildViewHolderInt(view);
            if (!holder.hasAnyOfTheFlags(12) && holder.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(holder)) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList();
                }

                holder.setScrapContainer(this, true);
                this.mChangedScrap.add(holder);
            } else {
                if (holder.isInvalid() && !holder.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.exceptionLabel());
                }

                holder.setScrapContainer(this, false);
                this.mAttachedScrap.add(holder);
            }

        }

        void unscrapView(RecyclerView.ViewHolder holder) {
            if (holder.mInChangeScrap) {
                this.mChangedScrap.remove(holder);
            } else {
                this.mAttachedScrap.remove(holder);
            }

            holder.mScrapContainer = null;
            holder.mInChangeScrap = false;
            holder.clearReturnedFromScrapFlag();
        }

        int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        View getScrapViewAt(int index) {
            return ((RecyclerView.ViewHolder)this.mAttachedScrap.get(index)).itemView;
        }

        void clearScrap() {
            this.mAttachedScrap.clear();
            if (this.mChangedScrap != null) {
                this.mChangedScrap.clear();
            }

        }

        RecyclerView.ViewHolder getChangedScrapViewForPosition(int position) {
            int changedScrapSize;
            if (this.mChangedScrap != null && (changedScrapSize = this.mChangedScrap.size()) != 0) {
                int offsetPosition;
                for(offsetPosition = 0; offsetPosition < changedScrapSize; ++offsetPosition) {
                    RecyclerView.ViewHolder holderx = (RecyclerView.ViewHolder)this.mChangedScrap.get(offsetPosition);
                    if (!holderx.wasReturnedFromScrap() && holderx.getLayoutPosition() == position) {
                        holderx.addFlags(32);
                        return holderx;
                    }
                }

                if (RecyclerView.this.mAdapter.hasStableIds()) {
                    offsetPosition = RecyclerView.this.mAdapterHelper.findPositionOffset(position);
                    if (offsetPosition > 0 && offsetPosition < RecyclerView.this.mAdapter.getItemCount()) {
                        long id = RecyclerView.this.mAdapter.getItemId(offsetPosition);

                        for(int i = 0; i < changedScrapSize; ++i) {
                            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mChangedScrap.get(i);
                            if (!holder.wasReturnedFromScrap() && holder.getItemId() == id) {
                                holder.addFlags(32);
                                return holder;
                            }
                        }
                    }
                }

                return null;
            } else {
                return null;
            }
        }

        RecyclerView.ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int position, boolean dryRun) {
            int scrapCount = this.mAttachedScrap.size();

            int cacheSize;
            RecyclerView.ViewHolder vh;
            for(cacheSize = 0; cacheSize < scrapCount; ++cacheSize) {
                vh = (RecyclerView.ViewHolder)this.mAttachedScrap.get(cacheSize);
                if (!vh.wasReturnedFromScrap() && vh.getLayoutPosition() == position && !vh.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !vh.isRemoved())) {
                    vh.addFlags(32);
                    return vh;
                }
            }

            if (!dryRun) {
                View view = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(position);
                if (view != null) {
                    vh = RecyclerView.getChildViewHolderInt(view);
                    RecyclerView.this.mChildHelper.unhide(view);
                    int layoutIndex = RecyclerView.this.mChildHelper.indexOfChild(view);
                    if (layoutIndex == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + vh + RecyclerView.this.exceptionLabel());
                    }

                    RecyclerView.this.mChildHelper.detachViewFromParent(layoutIndex);
                    this.scrapView(view);
                    vh.addFlags(8224);
                    return vh;
                }
            }

            cacheSize = this.mCachedViews.size();

            for(int i = 0; i < cacheSize; ++i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (!holder.isInvalid() && holder.getLayoutPosition() == position) {
                    if (!dryRun) {
                        this.mCachedViews.remove(i);
                    }

                    return holder;
                }
            }

            return null;
        }

        RecyclerView.ViewHolder getScrapOrCachedViewForId(long id, int type, boolean dryRun) {
            int count = this.mAttachedScrap.size();

            int i;
            for(i = count - 1; i >= 0; --i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mAttachedScrap.get(i);
                if (holder.getItemId() == id && !holder.wasReturnedFromScrap()) {
                    if (type == holder.getItemViewType()) {
                        holder.addFlags(32);
                        if (holder.isRemoved() && !RecyclerView.this.mState.isPreLayout()) {
                            holder.setFlags(2, 14);
                        }

                        return holder;
                    }

                    if (!dryRun) {
                        this.mAttachedScrap.remove(i);
                        RecyclerView.this.removeDetachedView(holder.itemView, false);
                        this.quickRecycleScrapView(holder.itemView);
                    }
                }
            }

            i = this.mCachedViews.size();

            for(int ix = i - 1; ix >= 0; --ix) {
                RecyclerView.ViewHolder holderx = (RecyclerView.ViewHolder)this.mCachedViews.get(ix);
                if (holderx.getItemId() == id) {
                    if (type == holderx.getItemViewType()) {
                        if (!dryRun) {
                            this.mCachedViews.remove(ix);
                        }

                        return holderx;
                    }

                    if (!dryRun) {
                        this.recycleCachedViewAt(ix);
                        return null;
                    }
                }
            }

            return null;
        }

        void dispatchViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
            if (RecyclerView.this.mRecyclerListener != null) {
                RecyclerView.this.mRecyclerListener.onViewRecycled(holder);
            }

            if (RecyclerView.this.mAdapter != null) {
                RecyclerView.this.mAdapter.onViewRecycled(holder);
            }

            if (RecyclerView.this.mState != null) {
                RecyclerView.this.mViewInfoStore.removeViewHolder(holder);
            }

        }

        void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter, boolean compatibleWithPrevious) {
            this.clear();
            this.getRecycledViewPool().onAdapterChanged(oldAdapter, newAdapter, compatibleWithPrevious);
        }

        void offsetPositionRecordsForMove(int from, int to) {
            int start;
            int end;
            byte inBetweenOffset;
            if (from < to) {
                start = from;
                end = to;
                inBetweenOffset = -1;
            } else {
                start = to;
                end = from;
                inBetweenOffset = 1;
            }

            int cachedCount = this.mCachedViews.size();

            for(int i = 0; i < cachedCount; ++i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (holder != null && holder.mPosition >= start && holder.mPosition <= end) {
                    if (holder.mPosition == from) {
                        holder.offsetPosition(to - from, false);
                    } else {
                        holder.offsetPosition(inBetweenOffset, false);
                    }
                }
            }

        }

        void offsetPositionRecordsForInsert(int insertedAt, int count) {
            int cachedCount = this.mCachedViews.size();

            for(int i = 0; i < cachedCount; ++i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (holder != null && holder.mPosition >= insertedAt) {
                    holder.offsetPosition(count, true);
                }
            }

        }

        void offsetPositionRecordsForRemove(int removedFrom, int count, boolean applyToPreLayout) {
            int removedEnd = removedFrom + count;
            int cachedCount = this.mCachedViews.size();

            for(int i = cachedCount - 1; i >= 0; --i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (holder != null) {
                    if (holder.mPosition >= removedEnd) {
                        holder.offsetPosition(-count, applyToPreLayout);
                    } else if (holder.mPosition >= removedFrom) {
                        holder.addFlags(8);
                        this.recycleCachedViewAt(i);
                    }
                }
            }

        }

        void setViewCacheExtension(RecyclerView.ViewCacheExtension extension) {
            this.mViewCacheExtension = extension;
        }

        void setRecycledViewPool(RecyclerView.RecycledViewPool pool) {
            if (this.mRecyclerPool != null) {
                this.mRecyclerPool.detach();
            }

            this.mRecyclerPool = pool;
            if (this.mRecyclerPool != null && RecyclerView.this.getAdapter() != null) {
                this.mRecyclerPool.attach();
            }

        }

        RecyclerView.RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecyclerView.RecycledViewPool();
            }

            return this.mRecyclerPool;
        }

        void viewRangeUpdate(int positionStart, int itemCount) {
            int positionEnd = positionStart + itemCount;
            int cachedCount = this.mCachedViews.size();

            for(int i = cachedCount - 1; i >= 0; --i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (holder != null) {
                    int pos = holder.mPosition;
                    if (pos >= positionStart && pos < positionEnd) {
                        holder.addFlags(2);
                        this.recycleCachedViewAt(i);
                    }
                }
            }

        }

        void markKnownViewsInvalid() {
            int cachedCount = this.mCachedViews.size();

            for(int i = 0; i < cachedCount; ++i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                if (holder != null) {
                    holder.addFlags(6);
                    holder.addChangePayload((Object)null);
                }
            }

            if (RecyclerView.this.mAdapter == null || !RecyclerView.this.mAdapter.hasStableIds()) {
                this.recycleAndClearCachedViews();
            }

        }

        void clearOldPositions() {
            int cachedCount = this.mCachedViews.size();

            int scrapCount;
            for(scrapCount = 0; scrapCount < cachedCount; ++scrapCount) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(scrapCount);
                holder.clearOldPosition();
            }

            scrapCount = this.mAttachedScrap.size();

            int changedScrapCount;
            for(changedScrapCount = 0; changedScrapCount < scrapCount; ++changedScrapCount) {
                ((RecyclerView.ViewHolder)this.mAttachedScrap.get(changedScrapCount)).clearOldPosition();
            }

            if (this.mChangedScrap != null) {
                changedScrapCount = this.mChangedScrap.size();

                for(int i = 0; i < changedScrapCount; ++i) {
                    ((RecyclerView.ViewHolder)this.mChangedScrap.get(i)).clearOldPosition();
                }
            }

        }

        void markItemDecorInsetsDirty() {
            int cachedCount = this.mCachedViews.size();

            for(int i = 0; i < cachedCount; ++i) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)this.mCachedViews.get(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }

        }
    }

    public static class RecycledViewPool {
        private static final int DEFAULT_MAX_SCRAP = 5;
        SparseArray<RecyclerView.RecycledViewPool.ScrapData> mScrap = new SparseArray();
        private int mAttachCount = 0;

        public RecycledViewPool() {
        }

        public void clear() {
            for(int i = 0; i < this.mScrap.size(); ++i) {
                RecyclerView.RecycledViewPool.ScrapData data = (RecyclerView.RecycledViewPool.ScrapData)this.mScrap.valueAt(i);
                data.mScrapHeap.clear();
            }

        }

        public void setMaxRecycledViews(int viewType, int max) {
            RecyclerView.RecycledViewPool.ScrapData scrapData = this.getScrapDataForType(viewType);
            scrapData.mMaxScrap = max;
            ArrayList scrapHeap = scrapData.mScrapHeap;

            while(scrapHeap.size() > max) {
                scrapHeap.remove(scrapHeap.size() - 1);
            }

        }

        public int getRecycledViewCount(int viewType) {
            return this.getScrapDataForType(viewType).mScrapHeap.size();
        }

        @Nullable
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            RecyclerView.RecycledViewPool.ScrapData scrapData = (RecyclerView.RecycledViewPool.ScrapData)this.mScrap.get(viewType);
            if (scrapData != null && !scrapData.mScrapHeap.isEmpty()) {
                ArrayList<RecyclerView.ViewHolder> scrapHeap = scrapData.mScrapHeap;
                return (RecyclerView.ViewHolder)scrapHeap.remove(scrapHeap.size() - 1);
            } else {
                return null;
            }
        }

        int size() {
            int count = 0;

            for(int i = 0; i < this.mScrap.size(); ++i) {
                ArrayList<RecyclerView.ViewHolder> viewHolders = ((RecyclerView.RecycledViewPool.ScrapData)this.mScrap.valueAt(i)).mScrapHeap;
                if (viewHolders != null) {
                    count += viewHolders.size();
                }
            }

            return count;
        }

        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            int viewType = scrap.getItemViewType();
            ArrayList<RecyclerView.ViewHolder> scrapHeap = this.getScrapDataForType(viewType).mScrapHeap;
            if (((RecyclerView.RecycledViewPool.ScrapData)this.mScrap.get(viewType)).mMaxScrap > scrapHeap.size()) {
                scrap.resetInternal();
                scrapHeap.add(scrap);
            }
        }

        long runningAverage(long oldAverage, long newValue) {
            return oldAverage == 0L ? newValue : oldAverage / 4L * 3L + newValue / 4L;
        }

        void factorInCreateTime(int viewType, long createTimeNs) {
            RecyclerView.RecycledViewPool.ScrapData scrapData = this.getScrapDataForType(viewType);
            scrapData.mCreateRunningAverageNs = this.runningAverage(scrapData.mCreateRunningAverageNs, createTimeNs);
        }

        void factorInBindTime(int viewType, long bindTimeNs) {
            RecyclerView.RecycledViewPool.ScrapData scrapData = this.getScrapDataForType(viewType);
            scrapData.mBindRunningAverageNs = this.runningAverage(scrapData.mBindRunningAverageNs, bindTimeNs);
        }

        boolean willCreateInTime(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = this.getScrapDataForType(viewType).mCreateRunningAverageNs;
            return expectedDurationNs == 0L || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        boolean willBindInTime(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = this.getScrapDataForType(viewType).mBindRunningAverageNs;
            return expectedDurationNs == 0L || approxCurrentNs + expectedDurationNs < deadlineNs;
        }

        void attach() {
            ++this.mAttachCount;
        }

        void detach() {
            --this.mAttachCount;
        }

        void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter, boolean compatibleWithPrevious) {
            if (oldAdapter != null) {
                this.detach();
            }

            if (!compatibleWithPrevious && this.mAttachCount == 0) {
                this.clear();
            }

            if (newAdapter != null) {
                this.attach();
            }

        }

        private RecyclerView.RecycledViewPool.ScrapData getScrapDataForType(int viewType) {
            RecyclerView.RecycledViewPool.ScrapData scrapData = (RecyclerView.RecycledViewPool.ScrapData)this.mScrap.get(viewType);
            if (scrapData == null) {
                scrapData = new RecyclerView.RecycledViewPool.ScrapData();
                this.mScrap.put(viewType, scrapData);
            }

            return scrapData;
        }

        static class ScrapData {
            final ArrayList<RecyclerView.ViewHolder> mScrapHeap = new ArrayList();
            int mMaxScrap = 5;
            long mCreateRunningAverageNs = 0L;
            long mBindRunningAverageNs = 0L;

            ScrapData() {
            }
        }
    }

    public static class EdgeEffectFactory {
        public static final int DIRECTION_LEFT = 0;
        public static final int DIRECTION_TOP = 1;
        public static final int DIRECTION_RIGHT = 2;
        public static final int DIRECTION_BOTTOM = 3;

        public EdgeEffectFactory() {
        }

        @NonNull
        protected EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int direction) {
            return new EdgeEffect(view.getContext());
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface EdgeDirection {
        }
    }

    private class RecyclerViewDataObserver extends RecyclerView.AdapterDataObserver {
        RecyclerViewDataObserver() {
        }

        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll((String)null);
            RecyclerView.this.mState.mStructureChanged = true;
            RecyclerView.this.processDataSetCompletelyChanged(true);
            if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }

        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            RecyclerView.this.assertNotInLayoutOrScroll((String)null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(positionStart, itemCount, payload)) {
                this.triggerUpdateProcessor();
            }

        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            RecyclerView.this.assertNotInLayoutOrScroll((String)null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(positionStart, itemCount)) {
                this.triggerUpdateProcessor();
            }

        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            RecyclerView.this.assertNotInLayoutOrScroll((String)null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(positionStart, itemCount)) {
                this.triggerUpdateProcessor();
            }

        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            RecyclerView.this.assertNotInLayoutOrScroll((String)null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(fromPosition, toPosition, itemCount)) {
                this.triggerUpdateProcessor();
            }

        }

        void triggerUpdateProcessor() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION && RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
                ViewCompat.postOnAnimation(RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
            } else {
                RecyclerView.this.mAdapterUpdateDuringMeasure = true;
                RecyclerView.this.requestLayout();
            }

        }
    }

    class ViewFlinger implements Runnable {
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mScroller;
        Interpolator mInterpolator;
        private boolean mEatRunOnAnimationRequest;
        private boolean mReSchedulePostAnimationCallback;

        ViewFlinger() {
            this.mInterpolator = RecyclerView.sQuinticInterpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }

        public void run() {
            if (RecyclerView.this.mLayout == null) {
                this.stop();
            } else {
                this.disableRunOnAnimationRequests();
                RecyclerView.this.consumePendingUpdateOperations();
                OverScroller scroller = this.mScroller;
                RecyclerView.SmoothScroller smoothScroller = RecyclerView.this.mLayout.mSmoothScroller;
                if (scroller.computeScrollOffset()) {
                    int[] scrollConsumed = RecyclerView.this.mScrollConsumed;
                    int x = scroller.getCurrX();
                    int y = scroller.getCurrY();
                    int dx = x - this.mLastFlingX;
                    int dy = y - this.mLastFlingY;
                    int hresult = 0;
                    int vresult = 0;
                    this.mLastFlingX = x;
                    this.mLastFlingY = y;
                    int overscrollX = 0;
                    int overscrollY = 0;
                    if (RecyclerView.this.dispatchNestedPreScroll(dx, dy, scrollConsumed, (int[])null, 1)) {
                        dx -= scrollConsumed[0];
                        dy -= scrollConsumed[1];
                    }

                    int vel;
                    if (RecyclerView.this.mAdapter != null) {
                        RecyclerView.this.scrollStep(dx, dy, RecyclerView.this.mScrollStepConsumed);
                        hresult = RecyclerView.this.mScrollStepConsumed[0];
                        vresult = RecyclerView.this.mScrollStepConsumed[1];
                        overscrollX = dx - hresult;
                        overscrollY = dy - vresult;
                        if (smoothScroller != null && !smoothScroller.isPendingInitialRun() && smoothScroller.isRunning()) {
                            vel = RecyclerView.this.mState.getItemCount();
                            if (vel == 0) {
                                smoothScroller.stop();
                            } else if (smoothScroller.getTargetPosition() >= vel) {
                                smoothScroller.setTargetPosition(vel - 1);
                                smoothScroller.onAnimation(dx - overscrollX, dy - overscrollY);
                            } else {
                                smoothScroller.onAnimation(dx - overscrollX, dy - overscrollY);
                            }
                        }
                    }

                    if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                        RecyclerView.this.invalidate();
                    }

                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        RecyclerView.this.considerReleasingGlowsOnScroll(dx, dy);
                    }

                    if (!RecyclerView.this.dispatchNestedScroll(hresult, vresult, overscrollX, overscrollY, (int[])null, 1) && (overscrollX != 0 || overscrollY != 0)) {
                        vel = (int)scroller.getCurrVelocity();
                        int velX = 0;
                        if (overscrollX != x) {
                            velX = overscrollX < 0 ? -vel : (overscrollX > 0 ? vel : 0);
                        }

                        int velY = 0;
                        if (overscrollY != y) {
                            velY = overscrollY < 0 ? -vel : (overscrollY > 0 ? vel : 0);
                        }

                        if (RecyclerView.this.getOverScrollMode() != 2) {
                            RecyclerView.this.absorbGlows(velX, velY);
                        }

                        if ((velX != 0 || overscrollX == x || scroller.getFinalX() == 0) && (velY != 0 || overscrollY == y || scroller.getFinalY() == 0)) {
                            scroller.abortAnimation();
                        }
                    }

                    if (hresult != 0 || vresult != 0) {
                        RecyclerView.this.dispatchOnScrolled(hresult, vresult);
                    }

                    if (!RecyclerView.this.awakenScrollBars()) {
                        RecyclerView.this.invalidate();
                    }

                    boolean fullyConsumedVertical = dy != 0 && RecyclerView.this.mLayout.canScrollVertically() && vresult == dy;
                    boolean fullyConsumedHorizontal = dx != 0 && RecyclerView.this.mLayout.canScrollHorizontally() && hresult == dx;
                    boolean fullyConsumedAny = dx == 0 && dy == 0 || fullyConsumedHorizontal || fullyConsumedVertical;
                    if (scroller.isFinished() || !fullyConsumedAny && !RecyclerView.this.hasNestedScrollingParent(1)) {
                        RecyclerView.this.setScrollState(0);
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                            RecyclerView.this.mPrefetchRegistry.clearPrefetchPositions();
                        }

                        RecyclerView.this.stopNestedScroll(1);
                    } else {
                        this.postOnAnimation();
                        if (RecyclerView.this.mGapWorker != null) {
                            RecyclerView.this.mGapWorker.postFromTraversal(RecyclerView.this, dx, dy);
                        }
                    }
                }

                if (smoothScroller != null) {
                    if (smoothScroller.isPendingInitialRun()) {
                        smoothScroller.onAnimation(0, 0);
                    }

                    if (!this.mReSchedulePostAnimationCallback) {
                        smoothScroller.stop();
                    }
                }

                this.enableRunOnAnimationRequests();
            }
        }

        private void disableRunOnAnimationRequests() {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
        }

        private void enableRunOnAnimationRequests() {
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                this.postOnAnimation();
            }

        }

        void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                RecyclerView.this.removeCallbacks(this);
                ViewCompat.postOnAnimation(RecyclerView.this, this);
            }

        }

        public void fling(int velocityX, int velocityY) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingX = this.mLastFlingY = 0;
            this.mScroller.fling(0, 0, velocityX, velocityY, -2147483648, 2147483647, -2147483648, 2147483647);
            this.postOnAnimation();
        }

        public void smoothScrollBy(int dx, int dy) {
            this.smoothScrollBy(dx, dy, 0, 0);
        }

        public void smoothScrollBy(int dx, int dy, int vx, int vy) {
            this.smoothScrollBy(dx, dy, this.computeScrollDuration(dx, dy, vx, vy));
        }

        private float distanceInfluenceForSnapDuration(float f) {
            f -= 0.5F;
            f *= 0.47123894F;
            return (float)Math.sin((double)f);
        }

        private int computeScrollDuration(int dx, int dy, int vx, int vy) {
            int absDx = Math.abs(dx);
            int absDy = Math.abs(dy);
            boolean horizontal = absDx > absDy;
            int velocity = (int)Math.sqrt((double)(vx * vx + vy * vy));
            int delta = (int)Math.sqrt((double)(dx * dx + dy * dy));
            int containerSize = horizontal ? RecyclerView.this.getWidth() : RecyclerView.this.getHeight();
            int halfContainerSize = containerSize / 2;
            float distanceRatio = Math.min(1.0F, 1.0F * (float)delta / (float)containerSize);
            float distance = (float)halfContainerSize + (float)halfContainerSize * this.distanceInfluenceForSnapDuration(distanceRatio);
            int duration;
            if (velocity > 0) {
                duration = 4 * Math.round(1000.0F * Math.abs(distance / (float)velocity));
            } else {
                float absDelta = (float)(horizontal ? absDx : absDy);
                duration = (int)((absDelta / (float)containerSize + 1.0F) * 300.0F);
            }

            return Math.min(duration, 2000);
        }

        public void smoothScrollBy(int dx, int dy, int duration) {
            this.smoothScrollBy(dx, dy, duration, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int dx, int dy, Interpolator interpolator) {
            this.smoothScrollBy(dx, dy, this.computeScrollDuration(dx, dy, 0, 0), interpolator == null ? RecyclerView.sQuinticInterpolator : interpolator);
        }

        public void smoothScrollBy(int dx, int dy, int duration, Interpolator interpolator) {
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }

            RecyclerView.this.setScrollState(2);
            this.mLastFlingX = this.mLastFlingY = 0;
            this.mScroller.startScroll(0, 0, dx, dy, duration);
            if (VERSION.SDK_INT < 23) {
                this.mScroller.computeScrollOffset();
            }

            this.postOnAnimation();
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mScroller.abortAnimation();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public @interface Orientation {
    }
}
