package fylder.book.lib.base


import android.content.pm.ActivityInfo
import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import javax.inject.Inject
import com.roger.catloadinglibrary.CatLoadingView


/**
 * Activity基类
 * Created by admin on 2017/12/21.
 */
abstract class BaseActivity<P : BasePresenter<out BaseView>> : RxAppCompatActivity() {

    protected var mRxPermissions: RxPermissions? = null

    protected var unbinder: Unbinder? = null

    @Inject
    lateinit var loadingView: CatLoadingView

    @Inject
    open lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutResId())
        unbinder = ButterKnife.bind(this)
        mRxPermissions = RxPermissions(this)
        loadFragment(savedInstanceState)
        initService()
        initViews()
        loadData()
    }


    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViews()

    protected abstract fun loadData()

    private fun loadFragment(savedInstanceState: Bundle?) {
    }


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

        unbinder!!.unbind()

        mPresenter.detachView()

        mRxPermissions = null
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
