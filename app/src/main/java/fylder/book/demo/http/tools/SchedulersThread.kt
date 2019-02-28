package fylder.book.demo.http.tools

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersThread {

    /**
     * 线程调度
     */
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return object : ObservableTransformer<T, T> {
            override fun apply(upstream: Observable<T>): ObservableSource<T> {
                return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

}