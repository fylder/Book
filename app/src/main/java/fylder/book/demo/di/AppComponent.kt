package fylder.book.demo.di

import dagger.Component
import fylder.book.demo.app.BookApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: BookApp)
}