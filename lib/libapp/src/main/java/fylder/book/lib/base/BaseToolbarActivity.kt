package fylder.book.lib.base;

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.View
import fylder.book.lib.R
import kotlinx.android.synthetic.main.toolbar_lay.*

/**
 *
 * Created by fylder on 2019/2/28
 */

abstract class BaseToolbarActivity<P : BasePresenter<out BaseView>> : BaseActivity<P>() {

    private var toolbarListener: ToolbarListener? = null
    protected var showToolBack = true
    protected var showToolTitle = true
    private val titleStr = "title"

    protected abstract fun initToolbar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar()
        initToolbar()
    }

    private fun setToolBar() {
        if (toolbar_lay == null) {
            return
        }

        setSupportActionBar(toolbar_lay)
        if (showToolBack) {
            toolbar_lay!!.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
            toolbar_lay!!.setNavigationOnClickListener {
                if (toolbarListener == null) {
                    finish()
                } else {
                    toolbarListener!!.backListener()
                }

            }
        }
        if (showToolTitle) {
            toolbar_title.visibility = View.VISIBLE
            if (TextUtils.isEmpty(titleStr)) {
                toolbar_title.visibility = View.GONE
            } else {
                toolbar_title.text = titleStr
                toolbar_lay!!.title = ""
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
        } else {
            toolbar_title.visibility = View.GONE
        }
        toolbar_right_img.visibility = View.GONE
    }

    protected fun hideToolbarTitle() {
        showToolTitle = false
    }

    fun setTitleText(title: String) {
        toolbar_title.text = title
    }

    fun setTitleText(@StringRes titleRes: Int) {
        toolbar_title.text = getString(titleRes)
    }

    /**
     * 右侧图片按钮
     *
     * @param resId    显示图片
     * @param listener 监听事件
     */
    protected fun setRightImg(@DrawableRes resId: Int, listener: View.OnClickListener) {
        toolbar_right_img.visibility = View.VISIBLE
        toolbar_right_img.setImageResource(resId)
        toolbar_right_img.setOnClickListener(listener)
    }

    interface ToolbarListener {
        fun backListener()
    }
}
