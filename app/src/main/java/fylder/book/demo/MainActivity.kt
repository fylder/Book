package fylder.book.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import fylder.book.demo.R
import fylder.book.lib.config.router.RouterConfig
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterConfig.APP_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            ARouter.getInstance().build(RouterConfig.APP_BOOK).navigation(this)
        }
    }

}
