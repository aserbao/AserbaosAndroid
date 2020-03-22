package com.example.base.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.base.R
import com.example.base.base.adapters.BaseRecyclerViewActivityAdapter
import com.example.base.base.adapters.BaseSpinnerAdapter
import com.example.base.base.beans.BaseRecyclerBean
import com.example.base.base.interfaces.IBaseRecyclerItemClickListener
import com.example.base.commonData.ASourceUtil
import com.example.base.commonData.StaticFinalValues
import java.util.*

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/19 4:40 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.base
 */
abstract class BaseRecyclerViewActivity : AppCompatActivity(), IBaseRecyclerItemClickListener {
    lateinit var mBaseRecyclerTv: TextView
    var mShowDataContentRv: RecyclerView? = null
    var mOpenglRecyclerView: RecyclerView? = null
    var mBaseRecyclerViewFl: RelativeLayout? = null
    var mBaseRecyclerEmptyContainer: FrameLayout? = null
    var mBaseToolBar: Toolbar? = null
    var mBaseTopTv: TextView? = null
    var mBaseSpinner: Spinner? = null
    var mBaseUpTv: TextView? = null
    var mBaseDownTv: TextView? = null

    init {
        mBaseDownTv = findViewById(R.id.base_down_tv)
        mBaseUpTv = findViewById(R.id.base_up_tv)
        mBaseSpinner = findViewById(R.id.base_spinner)
        mBaseTopTv = findViewById(R.id.base_top_tv)
        mBaseToolBar = findViewById(R.id.base_tool_bar)
        mBaseRecyclerEmptyContainer = findViewById(R.id.base_recycler_empty_container)
        mBaseRecyclerViewFl = findViewById(R.id.base_recycler_view_fl)
        mOpenglRecyclerView = findViewById(R.id.base_recycler_view)
        mShowDataContentRv = findViewById(R.id.show_data_content_rv)
        mBaseRecyclerTv = findViewById(R.id.base_recycler_tv)
    }
    protected var mContext: Context? = null
    protected var mLinearLayoutManager: LinearLayoutManager? = null
    public var mCommonAdapter: BaseRecyclerViewActivityAdapter? = null
    public var mRvOrientation = LinearLayoutManager.VERTICAL
    public var mBaseRecyclerBean: MutableList<BaseRecyclerBean> = ArrayList()
    public var mBaseSpinnerRecyclerBeen: List<BaseRecyclerBean> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslations()
        setContentView(R.layout.base_recyclerview_activity)
        ButterKnife.bind(this)
        mContext = this
        initGetData()
        initViewForLinear()
        initViewTopSpinner()
    }

    protected fun initViewTopSpinner() {
        if (mBaseSpinnerRecyclerBeen.size != 0) {
            mBaseToolBar!!.visibility = View.VISIBLE
            val baseSpinnerAdapter = BaseSpinnerAdapter(this, mBaseSpinnerRecyclerBeen)
            mBaseSpinner!!.adapter = baseSpinnerAdapter
            mBaseSpinner!!.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    itemClickBack(view, position, false, COME_FROM_SPINNER)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    open fun setTranslations() {}
    @JvmField
    var mMode = StaticFinalValues.GRID_LAYOUTMANAGER
    fun changeOrientation(mode: Int, orientation: Int) {
        mMode = mode
        mRvOrientation = orientation
        initViewForLinear()
    }

    abstract fun initGetData()
    open fun initViewForLinear() {
        mCommonAdapter = BaseRecyclerViewActivityAdapter(this, this, mBaseRecyclerBean, this)
        if (mMode == StaticFinalValues.GRID_LAYOUTMANAGER) {
            mLinearLayoutManager = GridLayoutManager(this, 3)
            (mLinearLayoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return mCommonAdapter!!.getSpanSize(position)
                }
            }
        } else {
            mLinearLayoutManager = LinearLayoutManager(this, mRvOrientation, false)
        }
        mOpenglRecyclerView!!.layoutManager = mLinearLayoutManager
        mOpenglRecyclerView!!.adapter = mCommonAdapter
        mBaseRecyclerViewFl!!.setBackgroundResource(ASourceUtil.getRandomImageId())
        mCommonAdapter!!.setmOrientation(mRvOrientation)
        mOpenglRecyclerView!!.post {
            val lastVisibleItemPosition = mLinearLayoutManager!!.findLastVisibleItemPosition()
            if (lastVisibleItemPosition <= 8) {
                mBaseUpTv!!.visibility = View.GONE
                mBaseDownTv!!.visibility = View.GONE
            } else {
                mBaseUpTv!!.visibility = View.VISIBLE
                mBaseDownTv!!.visibility = View.VISIBLE
            }
        }
        mBaseUpTv!!.setOnClickListener(View.OnClickListener {
            if (mLinearLayoutManager!!.findFirstVisibleItemPosition() == 0) return@OnClickListener
            mOpenglRecyclerView!!.smoothScrollToPosition(0)
        })
        mBaseDownTv!!.setOnClickListener { mOpenglRecyclerView!!.smoothScrollToPosition(mCommonAdapter!!.itemCount - 1) }
    }

    private fun addViewToFrameLayout(view: View?, type: Int, bundle: Bundle?) {
        mNeedDirectBack = false
        var layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        if (bundle != null) {
            if (!bundle.getBoolean(IS_MATCH)) {
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            }
            mNeedDirectBack = bundle.getBoolean(NEED_DIRECT_BACK)
            if (bundle.getBoolean(NEED_WH)) {
                layoutParams.width = bundle.getInt(WIDTH, 0)
                layoutParams.height = bundle.getInt(HEIGHT, 0)
            }
        }
        when (type) {
            NOT_FULL_SCREEN -> {
            }
            FULL_SCREEN -> {
                val layoutParams1 = mBaseRecyclerEmptyContainer!!.layoutParams
                if (layoutParams1 is MarginLayoutParams) {
                    layoutParams1.setMargins(0, 0, 0, 0)
                }
                mBaseRecyclerEmptyContainer!!.layoutParams = layoutParams1
            }
        }
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL
        view!!.layoutParams = layoutParams
        mBaseRecyclerEmptyContainer!!.visibility = View.VISIBLE
        mBaseRecyclerEmptyContainer!!.removeAllViews()
        mBaseRecyclerEmptyContainer!!.addView(view)
    }

    /**
     * @param view
     * @param isMatch       是否是LayoutParams.MATCH_PARENT？
     * @param isFullScreen 添加的视图是否需要全屏？
     * @param isDirectBack 点击物理返回键是否直接退出当前界面？
     */
    fun addViewToFrameLayout(view: View?, isMatch: Boolean, isFullScreen: Boolean, isDirectBack: Boolean) {
        val bundle = Bundle()
        bundle.putBoolean(IS_MATCH, isMatch)
        if (isFullScreen) {
            bundle.putBoolean(NEED_DIRECT_BACK, isDirectBack)
            addViewToFrameLayout(view, FULL_SCREEN, bundle)
        } else {
            addViewToFrameLayout(view, NOT_FULL_SCREEN, bundle)
        }
    }

    fun addViewWHToFL(view: View?, resLayout: Int, isMatch: Boolean, isFullScreen: Boolean, width: Int, height: Int, isDirectBack: Boolean): View? {
        var view = view
        if (view == null) view = LayoutInflater.from(mContext).inflate(resLayout, null)
        val bundle = Bundle()
        bundle.putBoolean(IS_MATCH, isMatch)
        bundle.putBoolean(NEED_WH, true)
        bundle.putInt(WIDTH, width)
        bundle.putInt(HEIGHT, height)
        if (isFullScreen) {
            bundle.putBoolean(NEED_DIRECT_BACK, isDirectBack)
            addViewToFrameLayout(view, FULL_SCREEN, bundle)
        } else {
            addViewToFrameLayout(view, NOT_FULL_SCREEN, bundle)
        }
        return view
    }

    fun addLayoutToFrameLayout(resLayout: Int, needFullScreen: Boolean): View {
        val view = LayoutInflater.from(mContext).inflate(resLayout, null)
        if (needFullScreen) {
            addViewToFrameLayout(view, true, true, true)
        } else {
            addViewToFrameLayout(view, true, false, false)
        }
        return view
    }

    protected var mNeedDirectBack = false
    override fun onBackPressed() {
        if (mBaseRecyclerEmptyContainer!!.childCount > 0 && !mNeedDirectBack) {
            mBaseRecyclerEmptyContainer!!.removeAllViews()
            return
        }
        super.onBackPressed()
    }

    companion object {
        protected const val COME_FROM_SPINNER = 1
        const val NOT_FULL_SCREEN = 0
        const val FULL_SCREEN = 1
        const val IS_MATCH = "ismatch"
        const val NEED_DIRECT_BACK = "need_direct_back"
        const val NEED_WH = "need_wh"
        const val WIDTH = "width"
        const val HEIGHT = "height"
    }
}