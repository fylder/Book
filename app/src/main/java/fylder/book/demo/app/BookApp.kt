package fylder.book.demo.app

import android.annotation.SuppressLint
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import fylder.book.lib.app.BaseApplication
import fylder.book.demo.di.AppComponent
import fylder.book.demo.di.AppModule
import fylder.book.demo.di.DaggerAppComponent


@SuppressLint("Registered")
class BookApp : BaseApplication() {

    companion object {
        lateinit var instance: BookApp
    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Logger.addLogAdapter(AndroidLogAdapter())
        setup()
    }

    private fun setup() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        component.inject(this)
    }

}