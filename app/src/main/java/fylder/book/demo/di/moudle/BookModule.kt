package fylder.book.demo.di.moudle

import com.roger.catloadinglibrary.CatLoadingView
import dagger.Module
import dagger.Provides

@Module
class BookModule {
    @Provides
    fun provideLoading(): CatLoadingView {
        return CatLoadingView()
    }
}