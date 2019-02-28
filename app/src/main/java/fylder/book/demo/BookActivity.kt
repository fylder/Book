package fylder.book.demo

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import fylder.book.demo.di.component.DaggerBookComponent
import fylder.book.demo.mpv.contract.BookContract
import fylder.book.demo.mpv.presenter.BookPresenter
import fylder.book.lib.app.BaseApplication
import fylder.book.lib.base.BaseActivity
import fylder.book.lib.config.router.RouterConfig
import kotlinx.android.synthetic.main.activity_book.*

@Route(path = RouterConfig.APP_BOOK)
class BookActivity : BaseActivity<BookPresenter>(), BookContract.View {

    override fun getLayoutResId(): Int {
        return R.layout.activity_book
    }

    override fun initViews() {
        DaggerBookComponent.builder()
            .appComponent(BaseApplication.instance.component)
            .view(this)
            .build()
            .inject(this)

        book_btn.setOnClickListener {
            mPresenter.http()
        }
        book_test_btn.setOnClickListener {
            ARouter.getInstance().build(RouterConfig.USER_LOGIN)
                .navigation(this)
        }
    }

    override fun loadData() {
    }

    override fun getActivity(): RxAppCompatActivity {
        return this
    }

    override fun showLoading() {
        super.showLoading()
        baseShowLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
        baseHideLoading()
    }

    override fun error(msg: String) {
        Toast.makeText(this, "error:$msg", Toast.LENGTH_SHORT).show()
    }

    override fun success(msg: String) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
