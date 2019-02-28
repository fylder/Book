package fylder.book.lib.di

import com.roger.catloadinglibrary.CatLoadingView
import dagger.Module
import dagger.Provides
import fylder.book.lib.app.BaseApplication
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: BaseApplication) {

    @Provides
    @Singleton
    fun provideApp(): BaseApplication {
        return baseApp
    }


}