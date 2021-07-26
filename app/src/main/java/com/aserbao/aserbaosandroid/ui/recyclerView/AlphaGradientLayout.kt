package com.aserbao.aserbaosandroid.ui.recyclerView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.ui.texts.textViews.textShowAnimation.hTextView.base.DisplayUtils

/**
* @Created time:2021/6/21 9:52 PM
* @author: aserbao
* @description: 滑动渐隐父控件
**/
class AlphaGradientLayout : FrameLayout {
    private var gradientSizeRight = 0
    private var gradientPaintRight: Paint? = null
    private var gradientRectRight: Rect? = null
    private var gradientDirtyFlags = 0
    private var disableGradient = false

    var isGradientDisabled: Boolean
        get() = disableGradient
        set(value) {
            disableGradient = value
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, 0)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        val defaultSize = DisplayUtils.dp2px(80.0f);
//        if (attrs != null) {
//            val arr: TypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AlphaGradientLayout, defStyleAttr, 0)
//            val flags: Int = arr.getInt(R.styleable.FadingEdgeLayout_fel_edge, 0)
//            fadeTop = flags and FADE_EDGE_TOP == FADE_EDGE_TOP
//            gradientSizeTop = arr.getDimensionPixelSize(R.styleable.FadingEdgeLayout_fel_size_top, defaultSize)
//            if (fadeTop && gradientSizeTop > 0) {
//                gradientDirtyFlags = gradientDirtyFlags or DIRTY_FLAG_TOP
//            }
//            arr.recycle()
//        } else {
//            gradientSizeTop = defaultSize
//        }
        gradientSizeRight = defaultSize
        gradientDirtyFlags = gradientDirtyFlags or DIRTY_FLAG_TOP
        val mode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        gradientPaintRight = Paint(Paint.ANTI_ALIAS_FLAG)
        gradientPaintRight?.xfermode = mode
        gradientRectRight = Rect()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        if (paddingTop !== top) {
            gradientDirtyFlags = gradientDirtyFlags or DIRTY_FLAG_TOP
        }
        super.setPadding(left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (h != oldh) {
            gradientDirtyFlags = gradientDirtyFlags or DIRTY_FLAG_TOP
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        val newWidth = width
        val newHeight = height
        if (!isVisible || newWidth == 0 || newHeight == 0) {
            super.dispatchDraw(canvas)
            return
        }
//        if (gradientDirtyFlags and DIRTY_FLAG_TOP == DIRTY_FLAG_TOP) {
//            gradientDirtyFlags = gradientDirtyFlags and DIRTY_FLAG_TOP.inv()
        val l = width - gradientSizeRight
        val t = paddingTop
        val r = width
        val b = height
        gradientRectRight?.set(l, t, r, b)
        val gradient = LinearGradient(r.toFloat(), t.toFloat(), l.toFloat(), t.toFloat(), FADE_COLORS, null, Shader.TileMode.CLAMP)
        gradientPaintRight?.shader = gradient
//        }
        val count: Int = canvas.saveLayer(0.0f, 0.0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
        if (!disableGradient) {
            if (gradientSizeRight > 0) {
                canvas.drawRect(gradientRectRight!!, gradientPaintRight!!)
            }
        }
        canvas.restoreToCount(count)
    }

    companion object {
        private const val DIRTY_FLAG_TOP = 1
        private val FADE_COLORS = intArrayOf(Color.TRANSPARENT, Color.BLACK)
    }
}