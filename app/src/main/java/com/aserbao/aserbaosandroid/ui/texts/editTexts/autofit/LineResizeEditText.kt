package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Handler
import android.os.Message
import android.text.*
import android.text.style.AbsoluteSizeSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatEditText
import com.aserbao.aserbaosandroid.R
import com.example.base.utils.screen.DisplayUtil
import java.util.*

class LineResizeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private var mMode = MODE_AUTO_SCALE_WITH_SIZE
    var mode: Int
        get() = mMode
        set(mode) {
            if (mMode != mode) {
                mMode = mode
                if (mMode == MODE_AUTO_SCALE_WITH_HEIGHT) {
                    adjustTextSizeWithHeight()
                } else {
                    adjustTextSize()
                }
                val hint = hint
                if (!TextUtils.isEmpty(hint)) {
                    setHintSpan(hint.toString())
                }
            }
        }

    fun setModeForce(mode: Int) {
        mMode = mode
        if (mMode == MODE_AUTO_SCALE_WITH_HEIGHT) {
            adjustTextSizeWithHeight()
        } else {
            adjustTextSize()
        }
        val hint = hint
        if (!TextUtils.isEmpty(hint)) {
            setHintSpan(hint.toString())
        }
    }

    private var mMaxTextSize = 40
    private var mMinTextSize = 12
    private var mMinTextSize2 = 12
    private var mLetterDefaultTextSize = 20
    private var mDefaultTextSize = 20
    // 默认编辑框字体大小，这个值大的话，当输入行数多时，敲入转行符会把第一行字往上顶出界
    private var mDefaultLineBreakTextSize = 6
    // 最后一行转行符光标大小修订值
    private var mLineBreakDiffSize = 6
    private val mSourceTextSize: Float
    private var mWidthPx: Int
    private val mWidthPx2: Int
    private var mHeightPx = DisplayUtil.dip2px(250f)
    /**
     * View 的宽度是否同内容的宽度
     */
    private val mFixWidth: Boolean
    private var mPaddingHorizontal = 0
    private val _sizeTester: SizeTester
    private var mPaint: TextPaint? = null
    private var mInitialized = false
    private var mResizing = false
    private val mResizingHandler: Handler?
    private val mCachedSizes: Map<String, Int> =
        HashMap()

    fun setHeightLimit(heightLimit: Int) {
        mHeightPx = heightLimit
    }

    override fun setTypeface(tf: Typeface?) {
        if (mPaint == null) {
            mPaint = TextPaint(paint)
            mPaint!!.isAntiAlias = true
        }
        mPaint!!.typeface = tf
        super.setTypeface(tf)
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (mResizing) {
            mResizing = false
            return
        }
        if (mMode == MODE_AUTO_SCALE_WITH_HEIGHT) {
            textSize = mLetterDefaultTextSize.toFloat()
            adjustTextSizeWithHeight()
        } else {
            if (text.toString() != null && text.toString().length > 0) { //setTextSize(mDefaultLineBreakTextSize);
            } else {
                textSize = mDefaultTextSize.toFloat()
            }
            adjustTextSize()
        }
    }

    fun adjustTextSizeWithHeight() {
        if (!mInitialized) {
            return
        }
        val measuredWidth = measuredWidth
        if (measuredWidth <= 0) {
            return
        }
        val text = text
        if (TextUtils.isEmpty(text)) {
            val lp = layoutParams
            if (mFixWidth) {
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = mWidthPx2
            }
            layoutParams = lp
            if (mSizeChangeListener != null) {
                mSizeChangeListener!!.onWidthChanged(lp.width)
            }
            return
        }
        val selectionEnd = selectionEnd
        val textSize = binarySearchSizeWithHeight(text.toString())
        //Log.d(TAG, "search size with height == " + textSize);
        mResizing = true
        val lp = layoutParams
        if (mFixWidth) {
            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
        } else {
            lp.width = mWidthPx2
        }
        layoutParams = lp
        val sp = SpannableString(text.toString())
        sp.setSpan(
            AbsoluteSizeSpan(textSize),
            0,
            text!!.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        setText(sp)
        setSelection(selectionEnd)
        if (mSizeChangeListener != null) {
            mSizeChangeListener!!.onWidthChanged(lp.width)
        }
    }

    fun adjustTextSize() {
        if (!mInitialized) {
            return
        }
        // 界面刚启动的时候这里就被调用了，不知道是什么原因，此时 measuredWidth == 0，无法用于计算
        val measuredWidth = measuredWidth
        if (measuredWidth <= 0) {
            return
        }
        val text = text
        // 清空内容的情况
        if (TextUtils.isEmpty(text)) {
            val lp = layoutParams
            if (mFixWidth) {
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = mWidthPx
            }
            layoutParams = lp
            if (mSizeChangeListener != null) {
                mSizeChangeListener!!.onWidthChanged(lp.width)
            }
            return
        }
        val selectionEnd = selectionEnd
        val textStr = text.toString()
        val textPaint = TextPaint(mPaint)
        textPaint.textSize = mMinTextSize.toFloat()
        val layout: Layout = StaticLayout(
            textStr, textPaint, mWidthPx, Layout.Alignment.ALIGN_NORMAL,
            1F /* spacingMult */, 0F /* spacingAdd */, false /*includePad */
        )
        val lineCount = layout.lineCount
        var totalHeight = 0f
        val lineTextSizeList: MutableList<Int> = ArrayList()
        for (i in 0 until lineCount) {
            val lineStart = layout.getLineStart(i)
            val lineEnd = layout.getLineEnd(i)
            val subText = textStr.substring(lineStart, lineEnd).replace("\n", "")
            var lineTextSize = mMinTextSize
            if (!TextUtils.isEmpty(subText)) {
                lineTextSize = binarySearchSize(
                    textPaint,
                    i,
                    subText,
                    mWidthPx,
                    mMinTextSize,
                    mMaxTextSize
                )
            }
            val textPaint1 = TextPaint(mPaint)
            textPaint1.textSize = lineTextSize.toFloat()
            val layout1: Layout = StaticLayout(
                subText, textPaint1, mWidthPx, Layout.Alignment.ALIGN_NORMAL,
                1F /* spacingMult */, 0F /* spacingAdd */, false /*includePad */
            )
            val measureLineHeight =
                layout1.height / layout1.lineCount.toFloat()
            totalHeight += measureLineHeight
            lineTextSizeList.add(lineTextSize)
        }
        var hasScaledByHeight = false
        var heightScale = 1.0f
        if (totalHeight > mHeightPx) { //LogUtil.logInfo(TAG,"adjustTextSize()---   totalHeight > mHeightPx ");
            hasScaledByHeight = true
            heightScale = mHeightPx.toFloat() / totalHeight
            for (i in 0 until lineCount) {
                val integer = lineTextSizeList[i]
                val newSize = Math.floor(integer * heightScale.toDouble()).toInt()
                lineTextSizeList[i] = newSize
            }
        }
        val sp = SpannableString(text.toString())
        var maxWidth = 0.0
        for (i in 0 until lineCount) {
            val lineStart = layout.getLineStart(i)
            val lineEnd = layout.getLineEnd(i)
            val subText = textStr.substring(lineStart, lineEnd).replace("\n", "")
            val lineTextSize = lineTextSizeList[i]
            val textPaint1 = TextPaint(mPaint)
            textPaint1.textSize = lineTextSize.toFloat()
            val measureLineWidth = textPaint1.measureText(subText)
            if (maxWidth < measureLineWidth) {
                maxWidth = measureLineWidth.toDouble()
            }
            Log.d(TAG, "Line " + i + ", final TextSize == " + lineTextSize + ", finalLineWidth == " + measureLineWidth +  ", " + subText);
            sp.setSpan(
                AbsoluteSizeSpan(lineTextSize),
                lineStart,
                lineEnd,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            // 转行符闪动光标 大小
            if (i == lineCount - 1 && TextUtils.isEmpty(subText)) { // 已经重新计算过
                textSize = if (hasScaledByHeight) {
                    if (lineTextSize - mLineBreakDiffSize > 0) { //LogUtil.logInfo(TAG,"adjustTextSize()---   1111 ");
                        lineTextSize - mLineBreakDiffSize.toFloat()
                    } else { //LogUtil.logInfo(TAG,"adjustTextSize()---   2222 ");
                        mDefaultLineBreakTextSize.toFloat()
                    }
                } else {
                    lineTextSize.toFloat()
                }
            }
        }
        var finalWidth = mWidthPx
        if (hasScaledByHeight) {
            finalWidth = Math.floor(maxWidth).toInt()
        }
        val lp = layoutParams
        lp.width = finalWidth + mPaddingHorizontal
        layoutParams = lp
        mResizing = true
        setText(sp)
        setSelection(selectionEnd)
        if (mSizeChangeListener != null) {
            mSizeChangeListener!!.onWidthChanged(lp.width)
        }
    }

    private var mSizeChangeListener: SizeChangeListener? =
        null

    fun setSizeChangeListener(listener: SizeChangeListener?) {
        mSizeChangeListener = listener
    }

    interface SizeChangeListener {
        fun onWidthChanged(newWidth: Int)
    }

    fun createSpanText(text: String, mode: Int): SpannableString {
        return if (mode == MODE_AUTO_SCALE_WITH_SIZE) {
            val textPaint = TextPaint(mPaint)
            textPaint.textSize = mMinTextSize.toFloat()
            val layout: Layout = StaticLayout(
                text, textPaint, mWidthPx, Layout.Alignment.ALIGN_NORMAL,
                1F /* spacingMult */, 0F /* spacingAdd */, false /*includePad */
            )
            val lineCount = layout.lineCount
            val sp = SpannableString(text)
            for (i in 0 until lineCount) {
                val lineStart = layout.getLineStart(i)
                val lineEnd = layout.getLineEnd(i)
                val subText = text.substring(lineStart, lineEnd).replace("\n", "")
                val lineTextSize = binarySearchSize(textPaint, i, subText, mWidthPx, mMinTextSize, mMaxTextSize)
                sp.setSpan(AbsoluteSizeSpan(lineTextSize), lineStart, lineEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            sp
        } else {
            val _availableSpaceRect = RectF()
            _availableSpaceRect.right = mWidthPx2.toFloat()
            _availableSpaceRect.bottom = mHeightPx.toFloat()
            val paint = TextPaint(mPaint)
            val textSize = binarySearch(
                text,
                mMinTextSize2,
                mSourceTextSize.toInt(),
                _sizeTester,
                _availableSpaceRect,
                paint
            )
            val sp = SpannableString(text)
            sp.setSpan(
                AbsoluteSizeSpan(textSize),
                0,
                text.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            sp
        }
    }

    fun getSpanText(text: String): SpannableString {
        return if (mMode == MODE_AUTO_SCALE_WITH_SIZE) {
            val textPaint = TextPaint(mPaint)
            textPaint.textSize = mMinTextSize.toFloat()
            val layout: Layout = StaticLayout(
                text, textPaint, mWidthPx, Layout.Alignment.ALIGN_NORMAL,
                1F /* spacingMult */, 0F /* spacingAdd */, false /*includePad */
            )
            val lineCount = layout.lineCount
            val sp = SpannableString(text)
            for (i in 0 until lineCount) {
                val lineStart = layout.getLineStart(i)
                val lineEnd = layout.getLineEnd(i)
                val subText = text.substring(lineStart, lineEnd).replace("\n", "")
                val lineTextSize = binarySearchSize(textPaint, i, subText, mWidthPx, mMinTextSize, mMaxTextSize)
                sp.setSpan(AbsoluteSizeSpan(lineTextSize), lineStart, lineEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                Log.d(TAG, "getSpanText: 第" +i+ "行 subText=" + subText + " lineTextSize="+ lineTextSize)
            }
            sp
        } else {
            val _availableSpaceRect = RectF()
            _availableSpaceRect.right = mWidthPx2.toFloat()
            _availableSpaceRect.bottom = mHeightPx.toFloat()
            val paint = TextPaint(mPaint)
            val textSize = binarySearch(
                text,
                mMinTextSize2,
                mSourceTextSize.toInt(),
                _sizeTester,
                _availableSpaceRect,
                paint
            )
            val sp = SpannableString(text)
            sp.setSpan(
                AbsoluteSizeSpan(textSize),
                0,
                text.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            sp
        }
    }

    fun setHintSpan(text: String) {
        val sp = getSpanText(text)
        hint = sp
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mResizingHandler?.removeMessages(0)
    }

    private val _availableSpaceRect = RectF()
    private fun binarySearchSizeWithHeight(text: String): Int {
        _availableSpaceRect.right = mWidthPx2.toFloat()
        _availableSpaceRect.bottom = mHeightPx.toFloat()
        val paint = TextPaint(mPaint)
        return binarySearch(
            text,
            mMinTextSize2,
            mSourceTextSize.toInt(),
            _sizeTester,
            _availableSpaceRect,
            paint
        )
    }

    private interface SizeTester {
        fun onTestSize(
            text: String?,
            suggestedSize: Int,
            availableSpace: RectF,
            paint: TextPaint
        ): Int
    }

    var bgPanelColor = Color.TRANSPARENT
    fun updateBgPanelColor(@ColorInt color: Int) {
        if (updateBgPanelColor(this, color)) {
            bgPanelColor = color
        }
    }

    companion object {
        private const val TAG = "LineResizeEditText"
        // 只根据输入框高度调整字体大小，左对齐
        const val MODE_AUTO_SCALE_WITH_HEIGHT = 0
        // 根据输入框高度以及宽度调整字体大小，居中
        const val MODE_AUTO_SCALE_WITH_SIZE = 1
        private const val NO_LINE_LIMIT = -1
        private fun binarySearchSize(
            textPaint: TextPaint,
            linePosition: Int,
            text: String,
            width: Int,
            minSize: Int,
            maxSize: Int
        ): Int {
            var lowSize = minSize
            var highSize = maxSize
            var currentSize =
                lowSize + Math.floor((highSize - lowSize) / 2f.toDouble()).toInt()
            while (lowSize < currentSize) {
                val valCmp = hasLineBreak(
                    textPaint,
                    linePosition,
                    text,
                    currentSize,
                    width
                )
                if (valCmp > 0) {
                    highSize = currentSize
                } else if (valCmp < 0) {
                    lowSize = currentSize
                } else {
                    return currentSize
                }
                currentSize =
                    lowSize + Math.floor((highSize - lowSize) / 2f.toDouble()).toInt()
            }
            //Log.d(TAG, "" + "Line " + linePosition + ", text==" + text + ", finalTextSize==" + currentSize);
            return currentSize
        }

        private fun hasLineBreak(
            textPaint: TextPaint,
            linePosition: Int,
            text: String,
            currentSize: Int,
            width: Int
        ): Int {
            textPaint.textSize = currentSize.toFloat()
            val measureWidth = textPaint.measureText(text)
            return if (measureWidth > width) { //            Log.d(TAG, "" + "Line " + linePosition + ", text==" + text + ", textSize=="
//                    + currentSize + ", measureWidth==" + measureWidth + ", width==" + width + ", return 1");
                1
            } else if (measureWidth < width) { //            Log.d(TAG, "" + "Line " + linePosition + ", text==" + text + ", textSize=="
//                    + currentSize + ", measureWidth==" + measureWidth + ", width==" + width + ", return -1");
                -1
            } else { //            Log.d(TAG, "" + "Line " + linePosition + ", text==" + text + ", textSize=="
//                    + currentSize + ", measureWidth==" + measureWidth + ", width==" + width + ", return 0");
                0
            }
        }

        private fun binarySearch(
            text: String,
            start: Int,
            end: Int,
            sizeTester: SizeTester,
            availableSpace: RectF,
            textPaint: TextPaint
        ): Int {
            var lastBest = start
            var lo = start
            var hi = end - 1
            var mid = 0
            var count = 1
            while (lo <= hi) {
                mid = lo + hi ushr 1
                val midValCmp = sizeTester.onTestSize(text, mid, availableSpace, textPaint)
                //Log.d(TAG, "binarySearch for " + ", result " + midValCmp + ", count==" +  count);
                count++
                if (midValCmp < 0) {
                    lastBest = lo
                    lo = mid + 1
                } else if (midValCmp > 0) {
                    hi = mid - 1
                    lastBest = hi
                } else return mid
            }
            // make sure to return last best
// this is what should always be returned
            return lastBest
        }

        fun updateBgPanelColor(view: View, color: Int): Boolean {
            val bgDrawable = view.background
            return if (bgDrawable is GradientDrawable) {
                bgDrawable.setColor(color)
                true
            } else {
                false
            }
        }

        /**
         * 生成一个富文本，使用的是 [.MODE_AUTO_SCALE_WITH_SIZE] 模式的算法
         * @param text
         * @param minTextSize
         * @param maxTextSize
         * @param viewWidth
         * @return
         */
        fun createSpanTextWidthViewWidth(
            text: String,
            minTextSize: Int,
            maxTextSize: Int,
            viewWidth: Int
        ): SpannableString {
            val textPaint = TextPaint()
            textPaint.textSize = minTextSize.toFloat()
            val layout: Layout = StaticLayout(
                text, textPaint, viewWidth, Layout.Alignment.ALIGN_NORMAL,
                1F /* spacingMult */, 0F /* spacingAdd */, false /*includePad */
            )
            val lineCount = layout.lineCount
            val sp = SpannableString(text)
            for (i in 0 until lineCount) {
                val lineStart = layout.getLineStart(i)
                val lineEnd = layout.getLineEnd(i)
                val subText = text.substring(lineStart, lineEnd).replace("\n", "")
                val lineTextSize = binarySearchSize(
                    textPaint,
                    i,
                    subText,
                    viewWidth,
                    minTextSize,
                    maxTextSize
                )
                sp.setSpan(
                    AbsoluteSizeSpan(lineTextSize),
                    lineStart,
                    lineEnd,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
            return sp
        }

    }

    init {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.LineResizeEditTextWH)
        var maxSize = 0
        var minSize = 0
        var defaultSize = 0
        var etViewWidth = 0 // 编辑视图的最大宽度
        maxSize = a.getDimensionPixelSize(
            R.styleable.LineResizeEditTextWH_text_max_size,
            maxSize
        )
        minSize = a.getDimensionPixelSize(
            R.styleable.LineResizeEditTextWH_text_min_size,
            minSize
        )
        defaultSize = a.getDimensionPixelSize(
            R.styleable.LineResizeEditTextWH_text_default_size,
            defaultSize
        )
        etViewWidth = a.getDimensionPixelSize(
            R.styleable.LineResizeEditTextWH_edit_text_view_width,
            etViewWidth
        )
        mFixWidth = a.getBoolean(R.styleable.LineResizeEditTextWH_fix_width, false)
        a.recycle()
        mPaddingHorizontal = paddingStart + paddingEnd
        //mMaxTextSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
//mMinTextSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        mMaxTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_max_textSize)
        mMinTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_min_textSize)
        mMinTextSize2 = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_min_textSize_mode_2)
        mLetterDefaultTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_letter_default_textSize)
        mDefaultTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_default_textSize)
        mDefaultLineBreakTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_default_line_break_textSize)
        //LogUtil.logInfo(TAG,"LineResizeEditText()---   mDefaultLineBreakTextSize = "+mDefaultLineBreakTextSize);
        mWidthPx = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_width)
        mWidthPx2 = DisplayUtil.getScreenWidth(context) - DisplayUtil.dip2px(16f)
        mSourceTextSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_default_textSize)
            .toFloat()
        //mHeightPx  = context.getResources().getDimensionPixelSize(R.dimen.creator_edit_text_height);
        mLineBreakDiffSize = context.resources
            .getDimensionPixelSize(R.dimen.creator_edit_text_diff_line_break_textSize)
        if (maxSize > 0) {
            mMaxTextSize = maxSize
        }
        if (minSize > 0) {
            mMinTextSize = minSize
        }
        if (defaultSize > 0) {
            mDefaultTextSize = defaultSize
        }
        if (etViewWidth > 0) {
            mWidthPx = etViewWidth
        }
        mResizingHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                adjustTextSize()
            }
        }
        _sizeTester = object : SizeTester {
            val textRect = RectF()
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onTestSize(
                text: String?,
                suggestedSize: Int,
                availableSPace: RectF,
                textPaint: TextPaint
            ): Int {
                textPaint.textSize = suggestedSize.toFloat()
                val singleline = maxLines == 1
                if (singleline) {
                    textRect.bottom = textPaint.fontSpacing
                    textRect.right = textPaint.measureText(text)
                } else {
                    val layout = StaticLayout(
                        text, textPaint,
                        mWidthPx2, Layout.Alignment.ALIGN_NORMAL, 1F, 0F, false
                    )
                    // return early if we have more lines
                    if (maxLines != NO_LINE_LIMIT
                        && layout.lineCount > maxLines
                    ) return 1
                    textRect.bottom = layout.height.toFloat()
                    var maxWidth = -1
                    for (i in 0 until layout.lineCount) if (maxWidth < layout.getLineWidth(i)) maxWidth =
                        layout.getLineWidth(i).toInt()
                    textRect.right = maxWidth.toFloat()
                }
                textRect.offsetTo(0f, 0f)
                return if (availableSPace.contains(textRect)) -1 else 1
                // else, too big
            }
        }
        mInitialized = true
    }
}