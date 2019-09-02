package fylder.book.lib.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.roger.catloadinglibrary.CatLoadingView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import javax.inject.Inject

/**
 * Activity基类
 * Created by admin on 2017/12/21.
 */
abstract class BaseActivity<P : BasePresenter<out BaseView>> : RxAppCompatActivity() {

    protected lateinit var mRxPermissions: RxPermissions

    @Inject
    lateinit var loadingView: CatLoadingView

    @Inject
    open lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutResId())
        mRxPermissions = RxPermissions(this)
        initService()
        initViews()
        loadData()
    }


    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViews()

    protected abstract fun loadData()

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()

    }

    protected fun initService() {
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onRestart() {
        super.onRestart()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter.detachView()
        }
    }

    fun baseShowLoading() {
        loadingView.setCanceledOnTouchOutside(false)
        loadingView.show(supportFragmentManager, "")
    }

    fun baseHideLoading() {
        if (loadingView.showsDialog) {
            loadingView.dismiss()
        }
    }

}
