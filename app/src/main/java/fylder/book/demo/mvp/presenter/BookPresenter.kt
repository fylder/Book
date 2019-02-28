package fylder.book.demo.mvp.presenter

import android.widget.Toast
import com.orhanobut.logger.Logger
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import fylder.book.lib.base.BasePresenter
import fylder.book.lib.di.scope.ActivityScope
import fylder.book.lib.http.BookApi
import fylder.book.lib.http.RetrofitClient
import fylder.book.lib.http.tools.SchedulersThread
import fylder.book.demo.mvp.contract.BookContract
import fylder.book.lib.db.BookRepository
import fylder.book.lib.db.entity.Book
import retrofit2.HttpException
import java.lang.StringBuilder
import javax.inject.Inject

@ActivityScope
class BookPresenter @Inject constructor(private var iView: BookContract.View) :
    BasePresenter<BookContract.View>(iView) {

    @Inject
    lateinit var retrofitClient: RetrofitClient
    @Inject
    lateinit var bookRepository: BookRepository

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

    fun add() {
        val book = Book()
        book.name = "gujian"
        book.price = 99
        if (bookRepository.add(iView.getActivity(), book)) {
            Toast.makeText(iView.getActivity(), "add success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(iView.getActivity(), "book has already insert", Toast.LENGTH_SHORT).show()
        }
    }

    fun query() {
        val str = StringBuilder()
        val books = bookRepository.queryAll(iView.getActivity())
        books.forEach {
            str.append("name:${it.name}, price:${it.price}")
        }
        iView.queryContent(str.toString())
    }

    fun delete() {
        if (bookRepository.deleteByName(iView.getActivity(), "gujian")) {
            Toast.makeText(iView.getActivity(), "delete success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(iView.getActivity(), "delete error", Toast.LENGTH_SHORT).show()
        }
    }
}