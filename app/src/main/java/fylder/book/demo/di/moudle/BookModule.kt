package fylder.book.demo.di.moudle

import dagger.Module
import dagger.Provides
import fylder.book.lib.db.BookRepository

@Module
class BookModule {

    @Provides
    fun provideBookRepository(): BookRepository {
        return BookRepository()
    }
}