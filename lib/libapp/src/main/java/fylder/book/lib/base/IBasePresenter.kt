package fylder.book.lib.base


interface IBasePresenter<V : BaseView> {

    fun attachView()

    fun detachView()

}