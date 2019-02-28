package fylder.book.demo.di

import dagger.Module
import dagger.Provides
import fylder.book.demo.app.BookApp
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: BookApp) {

    @Provides
    @Singleton
    fun provideApp(): BookApp {
        return baseApp
    }


}