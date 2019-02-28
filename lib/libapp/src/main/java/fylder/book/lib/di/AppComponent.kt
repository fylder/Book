package fylder.book.lib.di

import dagger.Component
import fylder.book.lib.app.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: BaseApplication)
}