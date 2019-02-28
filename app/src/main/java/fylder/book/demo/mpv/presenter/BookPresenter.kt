package fylder.book.demo.mpv.presenter

import android.widget.Toast
import com.orhanobut.logger.Logger
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import fylder.book.lib.base.BasePresenter
import fylder.book.lib.di.scope.ActivityScope
import fylder.book.lib.http.BookApi
import fylder.book.lib.http.RetrofitClient
import fylder.book.lib.http.tools.SchedulersThread
import fylder.book.demo.mpv.contract.BookContract
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScope
class BookPresenter @Inject constructor(private var iView: BookContract.View) :
    BasePresenter<BookContract.View>(iView) {

    @Inject
    lateinit var retrofitClient: RetrofitClient

    fun http() {
        iView.showLoading()
        retrofitClient.getInstance(BookApi::class.java).user()
            .bindUntilEvent(iView.getActivity(), ActivityEvent.DESTROY)
            .compose(SchedulersThread.applySchedulers())
            .subscribe({
                iView.hideLoading()
                Logger.w("s:$it")
                iView.success(it.name)
            }, {
                iView.hideLoading()
                Logger.e("error:$it")
                if (it is HttpException) {
                    val response = it.response().errorBody()
                    val msg = response!!.string()
                    Logger.w("error:$msg")
                    Toast.makeText(iView.getActivity(), msg, Toast.LENGTH_SHORT).show()
                    iView.success(msg)
                } else {
                    iView.error(it.message!!)
                }
            })
    }
}