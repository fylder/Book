package fylder.book.lib.di.common

import dagger.Module
import dagger.Provides
import fylder.book.lib.http.RetrofitClient

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofit(): RetrofitClient {
        return RetrofitClient()
    }

}