package fylder.book.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import fylder.book.lib.config.router.RouterConfig
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = RouterConfig.USER_LOGIN)
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        login_btn.setOnClickListener { ARouter.getInstance().build(RouterConfig.APP_MAIN).navigation(this) }
    }
}
