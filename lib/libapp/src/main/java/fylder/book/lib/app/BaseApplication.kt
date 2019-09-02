package fylder.book.lib.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import fylder.book.lib.BuildConfig
import fylder.book.lib.di.AppComponent
import fylder.book.lib.di.AppModule
import fylder.book.lib.di.DaggerAppComponent
import com.orhanobut.logger.PrettyFormatStrategy
import com.orhanobut.logger.FormatStrategy



/**
 * create by fylder on 2018/7/16
 **/
open class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication
    }

    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(1)         // (Optional) How many method line to show. Default 2
//            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//            .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        setup()
        if (BuildConfig.DEBUG) { //debug模式下
            ARouter.openLog()
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)
    }

    private fun setup() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        component.inject(this)
    }
}