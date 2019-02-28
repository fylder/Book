package fylder.book.lib.di.common

import com.roger.catloadinglibrary.CatLoadingView
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun provideLoading(): CatLoadingView {
        return CatLoadingView()
    }
}