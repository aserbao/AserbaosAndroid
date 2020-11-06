package com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.wraps

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.aserbao.aserbaosandroid.R
import com.aserbao.aserbaosandroid.databinding.PicTextEditLayoutBinding
import com.aserbao.aserbaosandroid.ui.customView.CircleImageView
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.SoftKeyboardStateHelper
import com.aserbao.aserbaosandroid.ui.texts.editTexts.autofit.LineResizeEditText
import com.example.base.utils.screen.DisplayUtil
import com.getremark.base.kotlin_ext.setVisible
import com.getremark.base.kotlin_ext.singleClick
import kotlinx.android.synthetic.main.pic_text_edit_layout.view.*
import java.util.*

/**
 * Instagram 文字自动排版
 * @property mShadowColor Int
 * @property mHasInputHeightLimitSet Boolean
 * @property rootBinding PicTextEditLayoutBinding
 * @property entranceContent String
 * @property needCallBack Boolean
 * @property contentIsChange Boolean
 * @property isVisiable Boolean
 * @property firstTimeInit Boolean
 * @property paintColor Int
 * @property mColorSelectorList List<TextStickerBean>
 * @property mColorSelector ViewGroup?
 * @property cuurTextSb TextStickerBean
 * @property textStickerIv ImageView?
 */
class TextStickerController : ConstraintLayout {
    companion object {
        private val TAG = TextStickerController::class.java.canonicalName
    }
    var mShadowColor = context.getResources().getColor(R.color.black70)
    private var mHasInputHeightLimitSet: Boolean = false;
    lateinit var rootBinding: PicTextEditLayoutBinding
    var entranceContent:String = "" // 进入时的内容,用来判断内容是否有改变
    var needCallBack:Boolean = false
    open var contentIsChange:Boolean = false
    var isVisiable:Boolean = false

    var firstTimeInit:Boolean = true

    var paintColor:Int = ContextCompat.getColor(context, R.color.paint_color_white);
    val mColorSelectorList :List<TextStickerBean> =
        ArrayList(Arrays.asList(
            TextStickerBean(R.id.color_selector_yellow, R.color.paint_color_yellow,R.color.paint_color_yellow, R.color.black),
            TextStickerBean(R.id.color_selector_cyan, R.color.paint_color_cyan,R.color.paint_color_cyan, R.color.black),
            TextStickerBean(R.id.color_selector_red, R.color.paint_color_red,R.color.paint_color_red, R.color.white),
            TextStickerBean(R.id.color_selector_white, R.color.paint_color_white,R.color.paint_color_white, R.color.black),
            TextStickerBean(R.id.color_selector_black, R.color.paint_color_black,R.color.paint_color_black, R.color.white),
            TextStickerBean(R.id.color_selector_pink, R.color.paint_color_pink,R.color.paint_color_pink, R.color.white),
            TextStickerBean(R.id.color_selector_green, R.color.paint_color_green,R.color.paint_color_green, R.color.black)
    ))

    private var mColorSelector: ViewGroup? = null
    var cuurTextSb :TextStickerBean = TextStickerBean(R.id.color_selector_white, R.color.paint_color_white,R.color.paint_color_white, R.color.black)

    constructor(context: Context) : super(context) {
        initialize(context)
    }
    constructor(context: Context, attrs: AttributeSet) : super(
        context,
        attrs
    ) {
        initialize(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initialize(context)
    }

    fun onResume(){
        needCallBack = true;
    }
    fun onPause(){
        needCallBack = false
    }

    open fun getInputContent():String{
        return inputEt.text.toString().trim()
    }
    open fun clearInputEt(){
        inputEt.setText("")
    }

    private fun initialize(context: Context) {
        rootBinding = PicTextEditLayoutBinding.inflate(LayoutInflater.from(context), this,true)
        initView()
        initEvent()
        resetDefaultColorSelector()

    }

    fun initView() {
        var notePanelShow = false
        onEditorViewUpdated(notePanelShow)
        rootBinding.apply {
            selColorCsl.post(Runnable {
                var marginLayoutParams = selColorCsl.layoutParams as MarginLayoutParams
                marginLayoutParams.topMargin = DisplayUtil.dip2px(16f)
            })
        }
    }

    /**
     *  退出EditText编辑模式
     */
    private fun doneTvDeal(){
    }

    private fun onEditorViewUpdated(panelShown: Boolean) {
        val textColor: Int = context.getResources().getColor(cuurTextSb.textColor)
        val bgColor: Int = context.getResources().getColor(cuurTextSb.textBgColor)
        if(panelShown){
            rootBinding.inputEt.setTextColor(textColor)
            rootBinding.inputEt.updateBgPanelColor(bgColor)
            rootBinding.inputEt.setShadowLayer(0.0f, 0.0f, 0.0f, Color.TRANSPARENT)
        }else{
            rootBinding.inputEt.setTextColor(bgColor)
            rootBinding.inputEt.updateBgPanelColor(Color.TRANSPARENT)
            rootBinding.inputEt.setShadowLayer(0.0f, 0.0f, 0.0f,mShadowColor)
        }
    }

    private fun initEvent() {
        SoftKeyboardStateHelper(this).addSoftKeyboardStateListener(object : SoftKeyboardStateHelper.SoftKeyboardStateListener {
            override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
                if(!needCallBack) return
                val lp = rootBinding.inputEt.getLayoutParams() as FrameLayout.LayoutParams
                if (!mHasInputHeightLimitSet || lp.height <= 0) {
                    rootBinding.inputEt.postDelayed({
                        mHasInputHeightLimitSet = true
                        /*val top: Int = rootBinding.textTypeLl.getTop()
                        val bottom: Int = rootBinding.selColorCsl.getBottom()
                        val paddingTop: Int = rootBinding.inputEt.getPaddingTop()
                        val paddingBottom: Int = rootBinding.inputEt.getPaddingBottom()
                        val lp12 = rootBinding.inputEt.getLayoutParams() as FrameLayout.LayoutParams
                        val height = top - bottom - lp12.topMargin - lp12.bottomMargin - paddingBottom - paddingTop
*/
                        var dip2px = DisplayUtil.dip2px(250f)
                        rootBinding.inputEt.setHeightLimit(dip2px)
                        val lp1 = rootBinding.inputEtCons.getLayoutParams() as FrameLayout.LayoutParams
                        lp1.height = dip2px
                        rootBinding.inputEtCons.setLayoutParams(lp1)
                        // bug 修改【创作】从相册里选一个图片，文字编辑有底板时，默认的底板太小
                        fixBugInputEtInitSoSmall()
                    }, 60)
                }
            }
            override fun onSoftKeyboardClosed() {
                if(needCallBack && isVisiable) {
                    doneTvDeal()
                    isVisiable = false
                }
            }
        })

        for (textSB in mColorSelectorList) {
            rootBinding.root.findViewById<View>(textSB.viewId).singleClick { v: View? ->
                cuurTextSb = textSB
                paintColor = ContextCompat.getColor(context, textSB.viewColor)
                if (mColorSelector != null) {
                    scaleDownColorSelector(mColorSelector!!, 1f, 1f)
                }
                mColorSelector = v as ViewGroup?
                scaleUpColorSelector(mColorSelector, 1.35f, 1.35f)
                onEditorViewUpdated(rootBinding.notePanelIv.isSelected)
            }
        }

        rootBinding.noteEditDoneTv.singleClick { v: View? ->
            doneTvDeal()
        }


        rootBinding.editCons.singleClick {
            setVisible(false)
        }
    }



    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        var inputEt = rootBinding.inputEt

        if(visibility == View.VISIBLE ){
            isVisiable = true
            textStickerIv?.setVisible(false)
            contentIsChange = false
            entranceContent = inputEt.text.toString().trim()
            dealTypeName()
            dealNoteIv()
            inputEt.setVisible(true)
            inputEt.requestFocus()
            inputEt.isCursorVisible= true
            showKeyboard()
        }else {
            var content = inputEt.text.toString().trim()
            if(!entranceContent.equals(content)) contentIsChange = true
                        hideSoftKeyboard()
        }
    }

    /**
     * 处理文字背景模式的图标选择
     */
    private fun dealNoteIv(){
        var notePanelShow = false
        rootBinding.notePanelIv.setSelected(notePanelShow)
        onEditorViewUpdated(notePanelShow)
    }

    /**
     * 处理文字模式图标切换选择
     */
    private fun dealTypeName(){
        when(inputEt.mode){
            LineResizeEditText.MODE_AUTO_SCALE_WITH_HEIGHT -> {
            }
            LineResizeEditText.MODE_AUTO_SCALE_WITH_SIZE -> {
            }
        }
    }

    /**
     * 处理文字模式切换
     */
    private fun onTypesetClick() {
        val lp = rootBinding.inputEt.getLayoutParams() as FrameLayout.LayoutParams
        when(inputEt.mode){
            LineResizeEditText.MODE_AUTO_SCALE_WITH_HEIGHT -> {
                lp.gravity = Gravity.CENTER
                rootBinding.inputEt.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
                rootBinding.inputEt.mode = LineResizeEditText.MODE_AUTO_SCALE_WITH_SIZE
            }
            LineResizeEditText.MODE_AUTO_SCALE_WITH_SIZE -> {
                lp.gravity = Gravity.CENTER_VERTICAL
                rootBinding.inputEt.setGravity(Gravity.CENTER_VERTICAL)
                rootBinding.inputEt.mode = LineResizeEditText.MODE_AUTO_SCALE_WITH_HEIGHT
            }
        }
        rootBinding.inputEt.setLayoutParams(lp)
        dealTypeName()
    }



    private fun showKeyboard(){
        var imm = rootBinding.inputEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(rootBinding.inputEt,0)
    }

    private fun hideSoftKeyboard() {
        if (null != inputEt) {
            val im = inputEt.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im?.hideSoftInputFromWindow(inputEt.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun resetDefaultColorSelector() {
        if (mColorSelector != null) {
            scaleDownColorSelector(mColorSelector!!, 1f, 1f)
        }
        // scale up the default color selector
        mColorSelector = findViewById(cuurTextSb.viewId)
        scaleUpColorSelector(mColorSelector, 1.35f, 1.35f)
    }

    private fun scaleUpColorSelector(colorSelector: ViewGroup?, scaleX: Float, scaleY: Float) {
        val borderColorForSelected = ContextCompat.getColor(context, R.color.black30)
        colorSelector!!.scaleX = scaleX
        colorSelector.scaleY = scaleY
        val imageView = colorSelector.getChildAt(0) as CircleImageView
        imageView.borderWidth = DisplayUtil.dip2px( 3f)
        imageView.borderColor = if (colorSelector.id == R.id.color_selector_white) borderColorForSelected else Color.WHITE
    }

    private fun scaleDownColorSelector(colorSelector: ViewGroup, scaleX: Float, scaleY: Float) {
        val borderColorForSelected = ContextCompat.getColor(
            context,
            R.color.black30
        )
        colorSelector.scaleX = scaleX
        colorSelector.scaleY = scaleY
        val imageView =
            mColorSelector!!.getChildAt(0) as CircleImageView
        imageView.borderWidth = DisplayUtil.dip2px(2f)
        imageView.borderColor =
            if (mColorSelector!!.id == R.id.color_selector_white) borderColorForSelected else Color.WHITE
    }

    var textStickerIv: ImageView?= null


    /**
     * bug 修改【创作】从相册里选一个图片，文字编辑有底板时，默认的底板太小
     */
    fun fixBugInputEtInitSoSmall(){
        // bug 修改【创作】从相册里选一个图片，文字编辑有底板时，默认的底板太小
        if(firstTimeInit){
            if(TextUtils.isEmpty(rootBinding.inputEt.text.toString())){
                rootBinding.inputEt.setText("")
            }
        }
        firstTimeInit = false
    }


    class TextStickerBean(var viewId: Int, var viewColor: Int, var textBgColor: Int, var textColor: Int) {
        var isWhite: Boolean

        init {
            isWhite = textColor == R.color.white
        }
    }
}