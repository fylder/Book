package fylder.book.lib.base


/**
 * Presenter基类
 * <p>
 * Created by Administrator on 2017/1/12 0012.
 */

abstract class BasePresenter<T : BaseView> constructor(iView: BaseView) : IBasePresenter<T> {


    override fun attachView() {

    }

    override fun detachView() {

    }


}
