package fylder.book.demo.mvp.contract

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import fylder.book.lib.base.BaseView

interface BookContract {

    interface View : BaseView {

        fun getActivity(): RxAppCompatActivity

        fun success(msg: String)

        fun error(msg: String)

        fun queryContent(content:String)

    }
}